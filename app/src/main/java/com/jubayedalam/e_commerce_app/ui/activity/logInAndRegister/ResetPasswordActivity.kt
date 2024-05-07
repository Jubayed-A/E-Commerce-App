/*
package com.devsstore.devsstore.ui.activity.logInAndRegister

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.devsstore.devsstore.data.authentication.reset.ResetResponse
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.devsstore.devsstore.databinding.FragmentResetPasswordBinding
import com.devsstore.devsstore.utils.InputValidator
import com.devsstore.devsstore.viewModel.authentication.reset.ResetViewModel
import com.devsstore.devsstore.viewModel.authentication.reset.ResetViewModelFactory

class ResetPasswordActivity : AppCompatActivity() {

    private lateinit var binding: FragmentResetPasswordBinding
    private lateinit var viewModel: ResetViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentResetPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        emailTextWatcher()

        // Initialize ViewModel
        initializeViewModel()

        setupViews()
        observeViewModel()

    }

    private fun setupViews() {
        binding.btnSend.setOnClickListener {
            submitForm()
        }
    }
    private fun initializeViewModel() {
        val viewModelFactory = ResetViewModelFactory()
        viewModel = ViewModelProvider(this, viewModelFactory)[ResetViewModel::class.java]
    }

    private fun observeViewModel() {
        viewModel.resetResponse.observe(this) { response ->
            // Handle response
            if (response != null) {
                if (response.data?.status == "success") {
                    val message = response.data.message ?: ""
                    showMessage(message)
                    hideProgressBar()
                    goingNewPasswordActivity(binding.etEmail.text.toString())
                } else {
                    val errorMessage = response.data?.errors?.get("email")?.joinToString("\n") ?: "Unknown error"
                    showError(errorMessage)
                    hideProgressBar()
                }
            }
        }

        viewModel.isLoading.observe(this) { isLoading ->
            showProgressBar()
        }
    }

    private fun showProgressBar() {
        binding.resetProgressbar.visibility = View.VISIBLE
    }
    private fun hideProgressBar() {
        binding.resetProgressbar.visibility = View.GONE
    }

    private fun showMessage(message: String) {
        // Show message to the user (e.g., Verification code has been sent)
        Toast.makeText(this, "Verification code has been sent", Toast.LENGTH_SHORT).show()
    }

    private fun showError(errorMessage: String) {
        // Show error message to the user (e.g., User with email imra1n@gmail.com does not exist)
        Toast.makeText(this, "User not exit!", Toast.LENGTH_SHORT).show()
    }



    private fun submitForm() {
        val email = binding.etEmail.text.toString()
        val validEmail = InputValidator.validateEmail(email)

        if (validEmail == null) {
            viewModel.sendUserEmail(email)
        } else {
            // Handle invalid email
            binding.etEmailContainer.helperText = validEmail
        }
    }

    private fun goingNewPasswordActivity(email: String) {
        val intent = Intent(this, NewPasswordSetActivity::class.java).apply {
            putExtra("EMAIL", email)
        }
        startActivity(intent)
        finish()
    }

    private fun emailTextWatcher() {
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


}*/


package com.jubayedalam.e_commerce_app.ui.activity.logInAndRegister

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.jubayedalam.e_commerce_app.data.authentication.reset.ResetResponse
import com.jubayedalam.e_commerce_app.databinding.FragmentResetPasswordBinding
import com.jubayedalam.e_commerce_app.utils.InputValidator
import com.jubayedalam.e_commerce_app.utils.stateManagement.Response
import com.jubayedalam.e_commerce_app.viewModel.authentication.reset.ResetViewModel
import com.jubayedalam.e_commerce_app.viewModel.authentication.reset.ResetViewModelFactory

class ResetPasswordActivity : AppCompatActivity() {

    private lateinit var binding: FragmentResetPasswordBinding
    private lateinit var viewModel: ResetViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentResetPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViewModel()
        setupViews()
    }

    private fun setupViewModel() {
        val viewModelFactory = ResetViewModelFactory()
        viewModel = ViewModelProvider(this, viewModelFactory).get(ResetViewModel::class.java)
        viewModel.resetResponse.observe(this, Observer { response ->
            response?.let { handleResetResponse(it) }
        })
        viewModel.isLoading.observe(this, Observer { isLoading ->
            isLoading?.let { if (it) showProgressBar() else hideProgressBar() }
        })
    }

    private fun setupViews() {
        binding.btnSend.setOnClickListener { submitForm() }
        binding.etEmail.addTextChangedListener(emailTextWatcher)
    }

    private fun handleResetResponse(response: Response<ResetResponse>) {
        if (response.data?.status == "success") {
            showMessage(response.data.message ?: "Verification code has been sent")
            goingNewPasswordActivity(binding.etEmail.text.toString())
        } else {
            val errorMessage = response.data?.errors?.get("email")?.joinToString("\n") ?: "Unknown error"
            showError(errorMessage)
        }
    }

    private val emailTextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            // Not used in this case
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            // Not used in this case
        }

        override fun afterTextChanged(s: Editable?) {
            binding.etEmailContainer.helperText = InputValidator.validateEmail(s.toString())
        }
    }

    private fun submitForm() {
        val email = binding.etEmail.text.toString()
        val validEmail = InputValidator.validateEmail(email)
        if (validEmail == null) {
            viewModel.sendUserEmail(email)
        } else {
            binding.etEmailContainer.helperText = validEmail
        }
    }

    private fun goingNewPasswordActivity(email: String) {
        val intent = Intent(this, NewPasswordSetActivity::class.java).apply {
            putExtra("EMAIL", email)
        }
        startActivity(intent)
    }

    private fun showProgressBar() {
        binding.resetProgressbar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        binding.resetProgressbar.visibility = View.GONE
    }

    private fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun showError(errorMessage: String) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
    }
}

