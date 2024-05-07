
package com.jubayedalam.e_commerce_app.ui.activity.logInAndRegister

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.jubayedalam.e_commerce_app.databinding.ActivityLogInBinding
import com.jubayedalam.e_commerce_app.network.authentication.login.LogInApiService
import com.jubayedalam.e_commerce_app.repository.authentication.login.LogInRepository
import com.jubayedalam.e_commerce_app.ui.activity.MainActivity
import com.jubayedalam.e_commerce_app.utils.InputValidator
import com.jubayedalam.e_commerce_app.utils.sharedPreferences.SharedPreferencesManager
import com.jubayedalam.e_commerce_app.utils.stateManagement.Response
import com.jubayedalam.e_commerce_app.viewModel.authentication.login.LoginViewModel
import com.jubayedalam.e_commerce_app.viewModel.authentication.login.LoginViewModelFactory

class LogInActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLogInBinding
    private lateinit var logInViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPreferencesManager = SharedPreferencesManager(this)
        val logInRepository = LogInRepository(LogInApiService.create(), sharedPreferencesManager)
        val viewModelFactory = LoginViewModelFactory(logInRepository, sharedPreferencesManager)
        logInViewModel = ViewModelProvider(this, viewModelFactory)[LoginViewModel::class.java]

        setupListeners()
    }

    private fun setupListeners() {
        binding.apply {
            textForgotPassword.setOnClickListener {
                startActivity(Intent(this@LogInActivity, ResetPasswordActivity::class.java))
            }
            textDontHaveAccount.setOnClickListener {
                startActivity(Intent(this@LogInActivity, RegisterActivity::class.java))
                finish()
            }
            btnSkip.setOnClickListener {
                startActivity(Intent(this@LogInActivity, MainActivity::class.java))
                finish()
            }

            emailTextWatcher("⚠ Email is required")
            passwordTextWatcher("⚠ Password is required")

            btnLogIn.setOnClickListener {
                loginUser()
            }
        }
    }

    private fun loginUser() {
        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()

        // Validate input fields
        if (!validateInputs()) {
            return
        }

        showProgressbar()

        logInViewModel.loginUser(email, password) { result ->
            when (result) {
                is Response.Success -> {
                    hideProgressbar()
                    Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()
                    goingMainActivity()
                }

                is Response.Error -> {
                    hideProgressbar()
                    val errorMessage = result.errorMessage ?: "Unknown error"
                    Toast.makeText(this, "Login Failed: $errorMessage", Toast.LENGTH_SHORT).show()
                }

                else -> Unit
            }
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
        } else if (!password.matches(".*[A-Z].*".toRegex())) {
            passwordTextWatcher("⚠ Must Contain 1 Upper-case Character")
            isValid = false
        } else if (!password.matches(".*[a-z].*".toRegex())) {
            passwordTextWatcher("⚠ Must Contain 1 Lower-case Character")
            isValid = false
        } else if (!password.matches(".*[@#\$%^&+=].*".toRegex())) {
            passwordTextWatcher("⚠ Must Contain 1 Special Character (@#\$%^&+=)")
            isValid = false
        } else if(!password.matches(".*\\d.*".toRegex())) {
            passwordTextWatcher( "⚠ At least 1 number is required")
            isValid = false
        } else {
            binding.etPasswordContainer.error = null
        }

        if (emptyFieldCount == 2) {
            binding.etEmailContainer.helperText = "⚠ Email is required"
            binding.etPasswordContainer.helperText = "⚠ Password is required"
        }
        return isValid
    }

    private fun goingMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    private fun showProgressbar() {
        binding.logInProgressbar.visibility = View.VISIBLE
    }

    private fun hideProgressbar() {
        binding.logInProgressbar.visibility = View.GONE
    }

    private fun emailTextWatcher(s: String) {
        binding.etEmail.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                binding.etEmailContainer.helperText = InputValidator.validateEmail(s.toString())
            }
        })
    }

    private fun passwordTextWatcher(s: String) {
        binding.etPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                binding.etPasswordContainer.helperText =
                    InputValidator.validatePassword(s.toString())
            }
        })
    }
}
