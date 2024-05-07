package com.jubayedalam.e_commerce_app.ui.fragment.splashAndWelcome.accountOption


/*class SplashScreenFragment : Fragment() {

    private lateinit var binding: FragmentSpalshScreenBinding
    private val sharedViewModel: SharedViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSpalshScreenBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Handler(Looper.myLooper()!!).postDelayed({
            navigateToNextDestination()
        }, 2000)
    }

*//*
    private fun navigateToNextDestination() {
        if (isOnboardingFinished()) {
            findNavController().navigate(R.id.action_splashScreenFragment_to_accountOptionFragment)
        } else {
            findNavController().navigate(R.id.action_splashScreenFragment_to_viewPagerFragment)
            setOnboardingFinished()
        }
    }
*//*

    private fun navigateToNextDestination() {
        val sharedPreferencesOnboarding = requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)

        if (!sharedPreferencesOnboarding.getBoolean("Finish", false)) {
            // Onboarding is not finished, navigate to viewPagerFragment
            findNavController().navigate(R.id.action_splashScreenFragment_to_viewPagerFragment)
            setOnboardingFinished()
        } else {
            val sharedPreferencesLogin = requireActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
            if (sharedPreferencesLogin.getBoolean("isLoggedIn", false)) {
                // User is logged in, navigate to MainActivity
                startActivity(Intent(requireContext(), MainActivity::class.java))
                requireActivity().finish()
            } else {
                // User is not logged in, navigate to accountOptionFragment
                findNavController().navigate(R.id.action_splashScreenFragment_to_accountOptionFragment)
            }
        }
    }


    private fun isOnboardingFinished(): Boolean {
        val sharedPref = requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        return sharedPref.getBoolean("Finish", false)
    }

    private fun setOnboardingFinished() {
        val sharedPref = requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        with(sharedPref.edit()) {
            putBoolean("Finish", true)
            apply()
        }
    }
}*/


