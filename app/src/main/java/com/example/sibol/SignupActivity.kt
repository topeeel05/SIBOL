package com.example.sibol

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.sibol.databinding.ActivitySignupBinding

class SignupActivity : AppCompatActivity() {

    // Declare binding and databaseHelper variables
    private lateinit var binding: ActivitySignupBinding
    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate or show the layout using ViewBinding
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize or call the DatabaseHelper
        databaseHelper = DatabaseHelper(this)

        // Set onClickListener sa signupButton
        binding.signupButton.setOnClickListener{

            // Retrieve the entered username and password
            val signupUsername = binding.signupUsername.text.toString()
            val signupPassword = binding.signupPassword.text.toString()

            // Call the signupDatabase function with entered credentials
            signupDatabase(signupUsername, signupPassword)
        }

        // Set onClickListener for the loginRedirect TextView
        binding.loginRedirect.setOnClickListener{

            // Punta login
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    // Function to insert a new user into the database
    private fun signupDatabase(username: String, password: String) {

        // Attempt to insert the user into the database and get the inserted row ID
        val insertedRowId = databaseHelper.insertUser(username, password)

        // Check if the insertion was successful (insertedRowId is not -1)
        if (insertedRowId != -1L) {

            // Message lang
            Toast.makeText(this, "Signup Successful", Toast.LENGTH_SHORT).show()

            // Punta login
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        } else {
            Toast.makeText(this, "Signup Failed", Toast.LENGTH_SHORT).show()
        }
    }
}