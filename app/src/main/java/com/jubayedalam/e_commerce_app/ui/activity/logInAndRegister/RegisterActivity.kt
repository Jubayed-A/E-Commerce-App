package com.jubayedalam.e_commerce_app.ui.activity.logInAndRegister

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.jubayedalam.e_commerce_app.data.authentication.register.Register
import com.jubayedalam.e_commerce_app.databinding.ActivityRegisterBinding
import com.jubayedalam.e_commerce_app.ui.activity.MainActivity
import com.jubayedalam.e_commerce_app.utils.InputValidator
import com.jubayedalam.e_commerce_app.utils.stateManagement.Response
import com.jubayedalam.e_commerce_app.viewModel.authentication.register.RegistrationViewModel

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private val registrationViewModel by lazy {
        ViewModelProvider(this)[RegistrationViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.apply {
            haveAccountLogin.setOnClickListener {
                startActivity(Intent(this@RegisterActivity, LogInActivity::class.java))
                finish()
            }

            btnSkip.setOnClickListener {
                startActivity(Intent(this@RegisterActivity, MainActivity::class.java))
                finish()
            }

            tvTermsCondition.setOnClickListener {
                val url = "https://demo.alhabibfoodcentre.com/terms-and-conditions"
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                startActivity(intent)
            }
        }


        emailTextWatcher("⚠ Email is required")
        passwordTextWatcher("⚠ Password is required")
        firstNameTextWatcher("⚠ First Name is required")
        lastNameTextWatcher("⚠ Last Name is required")

        binding.btnRegister.setOnClickListener {
            initRegisterViewModel()
        }

    }


    /*    private fun initRegisterViewModel() {
            val firstName = binding.etFirstName.text.toString()
            val lastName = binding.etLastName.text.toString()
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()

            // Validate input fields
            if (firstName.isBlank() || lastName.isBlank() || email.isBlank() || password.isBlank()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                return
            }

            // Create a User object with the input values
            val user = Register(firstName, lastName, email, password, password)

            showProgressbar()
            registrationViewModel.registerUser(user) { result ->
                when (result) {

                    is Response.Success -> {
                        val userResponse = result.data
                        // Handle successful registration
                        Log.d("User_data", user.toString())
                        onRegistrationSuccess()
                        hideProgressbar()
                        Toast.makeText(this, "Registration Successful", Toast.LENGTH_SHORT)
                            .show()
                    }

                    is Response.Error -> {
                        val exception = result.errorMessage
                        if (exception != null) {
                            Log.e("Register_Error", exception)
                        }
                        hideProgressbar()
                        Toast.makeText(this, "Registration Failed", Toast.LENGTH_SHORT)
                            .show()
                    }

                    else -> {
                        Unit
                    }
                }
            }
        }*/

    private fun initRegisterViewModel() {
        val firstName = binding.etFirstName.text.toString()
        val lastName = binding.etLastName.text.toString()
        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()

        // Validate input fields
        if (!validateInputs()) {
            return
        }
        showProgressbar()
        val user = Register(firstName, lastName, email, password, password)

        registrationViewModel.registerUser(user) { result ->
            when (result) {
                is Response.Success -> {
                    // Handle successful registration
                    val userResponse = result.data
                    Log.d("User_data", user.toString())
                    onRegistrationSuccess()
                }

                is Response.Error -> {
                    // Handle registration failure
                    val exception = result.errorMessage ?: "Unknown error"
                    Log.e("Register_Error", exception)
                    onRegistrationFailure()
                }

                else -> {
                    Unit
                }
            }
            hideProgressbar()
        }
    }

    private fun validateInputs(): Boolean {
        var isValid = true
        var emptyFieldCount = 0  // Count the number of empty fields encountered

        // Validate Email
        val email = binding.etEmail.text.toString()
        if (email.isBlank()) {
            emailTextWatcher("⚠ Email is required")
            isValid = false
            emptyFieldCount++
        } else if (email[0].isUpperCase()) {
            emailTextWatcher("⚠ Email must start with a lowercase letter")
            isValid = false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailTextWatcher("⚠ Invalid Email Address")
            isValid = false
        } else {
            binding.etEmailContainer.error = null
        }

        // Validate Password
        val password = binding.etPassword.text.toString()
        if (password.isBlank()) {
            passwordTextWatcher("⚠ Password is required")
            isValid = false
            emptyFieldCount++
        } else if (password.length <= 8) {
            passwordTextWatcher("⚠ Minimum 8 Character Password")
            isValid = false
        }else if(!password.matches(".*\\d.*".toRegex())) {
            passwordTextWatcher( "⚠ At least 1 number is required")
            isValid = false
        }
        else if (!password.matches(".*[A-Z].*".toRegex())) {
            passwordTextWatcher("⚠ Must Contain 1 Upper-case Character")
            isValid = false
        } else if (!password.matches(".*[a-z].*".toRegex())){
            passwordTextWatcher("⚠ Must Contain 1 Lower-case Character")
            isValid = false
        } else if (!password.matches(".*[@#\$%^&+=].*".toRegex())){
            passwordTextWatcher("⚠ Must Contain 1 Special Character (@#\$%^&+=)")
            isValid = false
        }
        else {
            binding.etPasswordContainer.error = null
        }


        // Validate First Name
        val firstName = binding.etFirstName.text.toString()
        if (firstName.isBlank()) {
            firstNameTextWatcher("⚠ First Name is required")
            isValid = false
            emptyFieldCount++
        } else {
            binding.etFirstNameContainer.error = null
        }

        // Validate Last Name
        val lastName = binding.etLastName.text.toString()
        if (lastName.isBlank()) {
            lastNameTextWatcher("⚠ Last Name is required")
            isValid = false
            emptyFieldCount++
        } else {
            binding.etLastNameContainer.error = null
        }

        // Show errors for all inputs if all fields are empty
        if (emptyFieldCount == 4) {
            binding.etFirstNameContainer.helperText = "⚠ First name is required"
            binding.etLastNameContainer.helperText = "⚠ Last name is required"
            binding.etEmailContainer.helperText = "⚠ Email is required"
            binding.etPasswordContainer.helperText = "⚠ Password is required"
        }

        return isValid
    }

    private fun onRegistrationSuccess() {
        Toast.makeText(this, "Registration Successful", Toast.LENGTH_SHORT).show()
        // Navigate to the appropriate screen, e.g., LoginActivity
        startActivity(Intent(this, LogInActivity::class.java))
        finish()
    }

    private fun onRegistrationFailure() {
        Toast.makeText(this, "Registration Failed", Toast.LENGTH_SHORT).show()
        // Optionally, provide additional information to the user about the failure
    }


    // textWatcher
    private fun emailTextWatcher(s: String) {
        binding.etEmail.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Not used in this case
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Not used in this case
            }

            override fun afterTextChanged(s: Editable?) {
                binding.etEmailContainer.helperText = InputValidator.validateEmail(s.toString())
            }
        })
    }

    private fun passwordTextWatcher(s: String) {
        binding.etPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Not used in this case
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Not used in this case
            }

            override fun afterTextChanged(s: Editable?) {
                binding.etPasswordContainer.helperText =
                    InputValidator.validatePassword(s.toString())
            }
        })
    }

    private fun firstNameTextWatcher(s: String) {
        binding.etFirstName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Not used in this case
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Not used in this case
            }

            override fun afterTextChanged(s: Editable?) {
                binding.etFirstNameContainer.helperText =
                    InputValidator.validateFirstName(s.toString())
            }
        })
    }

    private fun lastNameTextWatcher(s: String) {
        binding.etLastName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Not used in this case
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Not used in this case
            }

            override fun afterTextChanged(s: Editable?) {
                binding.etLastNameContainer.helperText =
                    InputValidator.validateLastName(s.toString())
            }
        })
    }

    private fun showProgressbar() {
        binding.registerProgressbar.visibility = View.VISIBLE
    }

    private fun hideProgressbar() {
        binding.registerProgressbar.visibility = View.GONE
    }

}