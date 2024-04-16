package com.example.campus_eats

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class StudentTypeDialog(context: Context) : Dialog(context) {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.custom_dialog_box)

        // Initialize Firebase Auth
        auth = Firebase.auth

        val radioGroup = findViewById<RadioGroup>(R.id.radioGroup)
        val submitButton = findViewById<Button>(R.id.submit_button)

        submitButton.setOnClickListener {
            // Get selected radio button
            val selectedRadioButtonId = radioGroup.checkedRadioButtonId
            val radioButton = findViewById<RadioButton>(selectedRadioButtonId)

            // Save student type to Firebase under user account
            val currentUser = auth.currentUser
            currentUser?.let { user ->
                val studentType = radioButton.text.toString()
                // Save student type to Firebase under user account
                // Example: Save it to a field in Firestore or Realtime Database
            }

            dismiss() // Dismiss the dialog
        }

        val cancelButton = findViewById<Button>(R.id.cancel_button)
        cancelButton.setOnClickListener {
            dismiss() // Dismiss the dialog
        }
    }
}
