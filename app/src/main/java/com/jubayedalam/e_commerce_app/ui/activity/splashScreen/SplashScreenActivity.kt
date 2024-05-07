package com.jubayedalam.e_commerce_app.ui.activity.splashScreen

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.jubayedalam.e_commerce_app.databinding.ActivitySplashBinding
import com.jubayedalam.e_commerce_app.ui.activity.MainActivity
import com.jubayedalam.e_commerce_app.ui.activity.logInAndRegister.AccountOptionActivity
import com.jubayedalam.e_commerce_app.ui.fragment.splashAndWelcome.ViewPagerFragment
import com.jubayedalam.e_commerce_app.utils.sharedPreferences.SharedPreferencesManager

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Handler(Looper.myLooper()!!).postDelayed({
            navigateToNextDestination()
        }, 2000)


    }


    private fun navigateToNextDestination() {
        val sharedPreferencesOnboarding = this.getSharedPreferences("onBoarding", Context.MODE_PRIVATE)

        if (!sharedPreferencesOnboarding.getBoolean("Finish", false)) {
            // Onboarding is not finished, navigate to viewPagerFragment
            startActivity(Intent(this, ViewPagerFragment::class.java))
            this.finish()
            setOnboardingFinished()
        } else {
            val sharedPreferencesLogin = SharedPreferencesManager(this)
            if (sharedPreferencesLogin.isLoggedIn()) {
                // User is logged in, navigate to MainActivity
                startActivity(Intent(this, MainActivity::class.java))
                this.finish()
            } else {
                // User is not logged in, navigate to accountOptionFragment
                startActivity(Intent(this, AccountOptionActivity::class.java))
                this.finish()
            }
        }
    }


/*    private fun isOnboardingFinished(): Boolean {
        val sharedPref = requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        return sharedPref.getBoolean("Finish", false)
    }*/

    private fun setOnboardingFinished() {
        val sharedPref = this.getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        with(sharedPref.edit()) {
            putBoolean("Finish", true)
            apply()
        }
    }




}