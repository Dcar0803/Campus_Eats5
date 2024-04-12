package com.example.campus_eats

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.RatingBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class ReviewFragment : Fragment() {
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var databaseReference: DatabaseReference
    private lateinit var reviewsAdapter: ReviewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_review, container, false)

        firebaseAuth = FirebaseAuth.getInstance()
        databaseReference = FirebaseDatabase.getInstance().reference

        // Initialize views and adapters
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewReviews)
        reviewsAdapter = ReviewsAdapter()
        recyclerView.adapter = reviewsAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Load reviews initially (all locations)
        loadReviews(null)

        // Set up listeners for writing reviews and filtering
        view.findViewById<Button>(R.id.buttonSubmitReview).setOnClickListener {
            writeReview()
        }

        view.findViewById<RadioGroup>(R.id.radioGroupLocations).setOnCheckedChangeListener { _, checkedId ->
            val location = when (checkedId) {
                R.id.radioButtonJones -> "Jones"
                R.id.radioButtonGateway -> "Gateway"
                else -> null
            }
            loadReviews(location)
        }

        return view
    }

    private fun loadReviews(location: String?) {
        databaseReference.child("reviews").apply {
            if (location != null) {
                orderByChild("location").equalTo(location)
            }
        }.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val reviews = mutableListOf<Review>()
                snapshot.children.forEach { dataSnapshot ->
                    dataSnapshot.getValue(Review::class.java)?.let { review ->
                        reviews.add(review)
                    }
                }
                reviewsAdapter.submitList(reviews)
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle error
                Log.e(TAG, "Failed to load reviews: $error")
            }
        })
    }

    private fun writeReview() {
        // Get user ID and review data
        val userId = firebaseAuth.currentUser?.uid
        val username = firebaseAuth.currentUser?.displayName
        val location = when (view?.findViewById<RadioGroup>(R.id.radioGroupLocations)?.checkedRadioButtonId) {
            R.id.radioButtonJones -> "Jones"
            R.id.radioButtonGateway -> "Gateway"
            else -> null
        }
        val rating = view?.findViewById<RatingBar>(R.id.ratingBar)?.rating ?: 0.0f // Default value if not selected
        val reviewText = view?.findViewById<EditText>(R.id.editTextReview)?.text.toString()

        // Save review to Firebase
        if (userId != null && username != null && location != null && reviewText.isNotEmpty()) {
            val review = Review("", userId, username, location, rating, reviewText)
            val reviewId = databaseReference.child("reviews").push().key
            reviewId?.let {
                databaseReference.child("reviews").child(it).setValue(review)
                    .addOnSuccessListener {
                        // Review saved successfully
                        Log.d(TAG, "Review submitted successfully")
                    }
                    .addOnFailureListener { exception ->
                        // Failed to save review, handle the error
                        Log.e(TAG, "Failed to write review: $exception")
                    }
            }
        } else {
            // Display an error message or handle incomplete review data
            Log.e(TAG, "Incomplete review data")
        }
    }

    companion object {
        private const val TAG = "ReviewFragment"
    }
}
