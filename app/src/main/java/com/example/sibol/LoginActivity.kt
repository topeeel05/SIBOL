package com.example.sibol

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.sibol.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    // Declare binding and databaseHelper variables
    private lateinit var binding: ActivityLoginBinding
    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {

        // Inflate or show the layout using ViewBinding
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize or call the DatabaseHelper
        databaseHelper = DatabaseHelper(this)

        // login button
        binding.loginButton.setOnClickListener{
            // Retrieve the entered username and password
            val loginUsername = binding.loginUsername.text.toString()
            val loginPassword = binding.loginPassword.text.toString()
            // Call the loginDatabase function with entered credentials
            loginDatabase(loginUsername, loginPassword)
        }

        // Punta sa signup page
        binding.signupRedirect.setOnClickListener{
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
    // Function to check login credentials against the database
    private fun loginDatabase(username: String, password: String){
        // Check if the entered acc exist in database
        val userExists = databaseHelper.readUser(username, password)
        if (userExists){
            Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()

            // Punta sa main activity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        } else {
            Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show()
        }
    }
}