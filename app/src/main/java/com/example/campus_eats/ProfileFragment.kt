// ProfileFragment.kt
package com.example.campus_eats

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class ProfileFragment : Fragment() {
    private lateinit var editTextName: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var buttonChangePassword: Button
    private lateinit var buttonSaveProfile: Button
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        editTextName = view.findViewById(R.id.editTextName)
        editTextPassword = view.findViewById(R.id.editTextPassword)
        buttonChangePassword = view.findViewById(R.id.buttonChangePassword)
        buttonSaveProfile = view.findViewById(R.id.buttonSaveProfile)
        firebaseAuth = FirebaseAuth.getInstance()

        // Set up click listeners
        buttonChangePassword.setOnClickListener {
            // Call a function to change password
            changePassword()
        }

        buttonSaveProfile.setOnClickListener {
            // Call a function to save profile information
            saveProfile()
        }

        return view
    }

    private fun changePassword() {
        // Get new password from editTextPassword
        val newPassword = editTextPassword.text.toString()

        // Check if newPassword is not empty
        if (newPassword.isNotEmpty()) {
            // Update password using Firebase Authentication
            firebaseAuth.currentUser?.updatePassword(newPassword)
                ?.addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        // Password updated successfully
                    } else {
                        // Failed to update password
                    }
                }
        } else {
            // Display an error message or prompt the user to enter a password
        }
    }

    private fun saveProfile() {
        // Get new name from editTextName
        val newName = editTextName.text.toString()

        // Update name in Firebase Realtime Database
        val databaseReference = FirebaseDatabase.getInstance().reference
        val userId = firebaseAuth.currentUser?.uid
        userId?.let {
            databaseReference.child("users").child(it).child("name").setValue(newName)
        }
    }
}
