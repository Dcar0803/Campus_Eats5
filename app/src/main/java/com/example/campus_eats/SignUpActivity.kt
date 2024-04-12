package com.example.campus_eats

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class SignUpActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signup_activity)

        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()

        // Set up sign-up button click listener
        findViewById<Button>(R.id.buttonSignUp).setOnClickListener {
            signUp()
        }
    }

    private fun signUp() {
        val email = findViewById<EditText>(R.id.editTextNewEmail).text.toString()
        val password = findViewById<EditText>(R.id.editTextNewPassword).text.toString()
        val confirmPassword = findViewById<EditText>(R.id.editTextConfirmPassword).text.toString()
        val phoneNumber = findViewById<EditText>(R.id.editTextPhoneNumber).text.toString()

        // Validate input fields
        if (email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || phoneNumber.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            return
        }

        if (password != confirmPassword) {
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
            return
        }

        // Create user with email and password
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign-up success, navigate to MainActivity (homepage)
                    Toast.makeText(this, "Sign up successful", Toast.LENGTH_SHORT).show()
                    navigateToMainActivity()
                } else {
                    // Sign-up failed
                    Toast.makeText(this, "Sign up failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun navigateToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish() // Optional: finish SignUpActivity to prevent going back to it when pressing back button from MainActivity
    }
}
