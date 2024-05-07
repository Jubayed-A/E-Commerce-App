package com.jubayedalam.e_commerce_app.ui.activity.logInAndRegister

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.jubayedalam.e_commerce_app.databinding.FragmentNewPasswordSetBinding
import com.jubayedalam.e_commerce_app.network.authentication.newPassword.NewPasswordRetrofitInstance
import com.jubayedalam.e_commerce_app.repository.authentication.newPassword.NewPasswordRepository
import com.jubayedalam.e_commerce_app.utils.InputValidator
import com.jubayedalam.e_commerce_app.viewModel.authentication.newPassword.NewPasswordViewModel
import com.jubayedalam.e_commerce_app.viewModel.authentication.newPassword.NewPasswordViewModelFactory

class NewPasswordSetActivity : AppCompatActivity() {

    private lateinit var binding: FragmentNewPasswordSetBinding
    private lateinit var viewModel: NewPasswordViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentNewPasswordSetBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repository = NewPasswordRepository(NewPasswordRetrofitInstance.newPasswordApiService)
        val factory = NewPasswordViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory)[NewPasswordViewModel::class.java]

        setupViews()
        passwordTextWatcher("⚠ Password is required")
        otpTextWatcher("⚠ otp is required")

    }

    private fun setupViews() {
        // Set the email from intent (assuming "EMAIL" is the key for email in intent extras)
        val email = intent.getStringExtra("EMAIL")
        binding.etEmail.setText(email)
        binding.etEmail.isEnabled = false // Disable editing

        binding.btnSavePassword.setOnClickListener {
            val code = binding.etOtp.text.toString()
            val password = binding.etNewPassword.text.toString()

            if (!validateInputs()) {
                return@setOnClickListener
            } else {
                // Perform API call to check OTP and set password
                if (email != null) {
                    viewModel.setNewPassword(email, code, password)
                }
                showProgressBar()
            }
        }


        // Observe the response from ViewModel
        viewModel.newPasswordResponse.observe(this) { response ->
            response?.let {
                if (it.data != null) {
                    if (it.data.status == "success") {
                        // Password set successfully, navigate to LoginActivity
                        val intent = Intent(this, LogInActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        // Show error message if OTP is invalid
                        val errorMessage = it.data.errors?.flatMap { it.value }?.joinToString("\n")
                            ?: "Unknown error"
                        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
                        Log.d("Response error", errorMessage)
                    }
                } else {
                    // Handle case where response data is null
                    val errorMessage = "Response data is null"
                    Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
                    Toast.makeText(this, "Correct your Info!", Toast.LENGTH_SHORT).show()
                    Log.d("Response error", errorMessage)
                }
            } ?: run {
                // Handle case where response is null
                val errorMessage = "Response is null"
                Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
                Log.d("Response error", errorMessage)
            }
            hideProgressBar()
        }


    }

    private fun validateInputs(): Boolean {
        var isValid = true
        var emptyFieldCount = 0  // Count the number of empty fields encountered

        // Validate otp
        val otp = binding.etOtp.text.toString()
        if (otp.isBlank()) {
            otpTextWatcher("⚠ OTP is required")
            isValid = false
            emptyFieldCount++
        } else if (otp.length != 6) {
            otpTextWatcher("⚠ OTP must be 6 digits long")
            isValid = false
        } else if (!otp.matches("\\d+".toRegex())) {
            otpTextWatcher("⚠ OTP must contain only digits")
            isValid = false
        } else {
            binding.etOTPContainer.error = null
        }

        // Validate Password
        val password = binding.etNewPassword.text.toString()
        if (password.isBlank()) {
            passwordTextWatcher("⚠ Password is required")
            isValid = false
            emptyFieldCount++
        } else if (password.length <= 8) {
            passwordTextWatcher("⚠ Minimum 8 Character Password")
            isValid = false
        } else if(!password.matches(".*\\d.*".toRegex())) {
            passwordTextWatcher( "⚠ At least 1 number is required")
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
        } else {
            binding.etNewPassContainer.error = null
        }

        if (emptyFieldCount == 2) {
            binding.etOTPContainer.helperText = "⚠ OTP is required"
            binding.etNewPassContainer.helperText = "⚠ Password is required"
        }
        return isValid
    }

    private fun passwordTextWatcher(s: String) {
        binding.etNewPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                binding.etNewPassContainer.helperText =
                    InputValidator.validatePassword(s.toString())
            }
        })
    }

    private fun otpTextWatcher(s: String) {
        binding.etOtp.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                binding.etOTPContainer.helperText =
                    InputValidator.validateOTP(s.toString())
            }
        })
    }

    private fun showProgressBar() {
        binding.newPasswordProgressbar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        binding.newPasswordProgressbar.visibility = View.GONE
    }

}