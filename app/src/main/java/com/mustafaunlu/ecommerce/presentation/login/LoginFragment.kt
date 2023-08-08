package com.mustafaunlu.ecommerce.presentation.login

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import arrow.core.getOrElse
import com.mustafaunlu.ecommerce.R
import com.mustafaunlu.ecommerce.common.Constants.SHARED_PREF_FIREBASE_USERID_KEY
import com.mustafaunlu.ecommerce.common.Constants.SHARED_PREF_IS_FIREBASE_USER
import com.mustafaunlu.ecommerce.common.Constants.SHARED_PREF_USERID_KEY
import com.mustafaunlu.ecommerce.common.ScreenState
import com.mustafaunlu.ecommerce.data.dto.User
import com.mustafaunlu.ecommerce.databinding.FragmentLoginBinding
import com.mustafaunlu.ecommerce.domain.entity.FirebaseSignInUserEntity
import com.mustafaunlu.ecommerce.utils.TokenManager
import com.mustafaunlu.ecommerce.utils.checkInternetConnection
import com.mustafaunlu.ecommerce.utils.gone
import com.mustafaunlu.ecommerce.utils.safeNavigate
import com.mustafaunlu.ecommerce.utils.showToast
import com.mustafaunlu.ecommerce.utils.visible
import dagger.hilt.android.AndroidEntryPoint
import io.github.nefilim.kjwt.JWT
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private val viewModel: LoginViewModel by viewModels()

    @Inject
    lateinit var sharedPref: SharedPreferences

    @Inject
    lateinit var tokenManager: TokenManager

    private lateinit var expirationTime: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupLoginButton()
        setupObservers()

        binding.signUpBtn.setOnClickListener {
            val action = LoginFragmentDirections.actionLoginFragmentToSignupFragment()
            findNavController().navigate(action)
        }

        binding.btnForgotPw.setOnClickListener {
            requireView().showToast("Coming soon...")
        }
    }

    private fun setupObservers() {
        viewModel.loginState.observe(viewLifecycleOwner) { loginState ->
            when (loginState) {
                ScreenState.Loading -> {
                    binding.apply {
                        loading.visible()
                        loginBtn.isEnabled = false
                    }
                }

                is ScreenState.Success -> {
                    extractExpirationTimeFromToken(loginState.uiData.token)
                    tokenManager.saveToken(loginState.uiData.token, expirationTime.toLong())
                    binding.apply {
                        loading.gone()
                        loginBtn.isEnabled = true
                    }
                    navigateToHomeScreen()
                    requireView().showToast("Welcome ${loginState.uiData.username}")
                    saveUserIdToSharedPref(loginState.uiData.id.toString())
                }

                is ScreenState.Error -> {
                    binding.apply {
                        loading.gone()
                        loginBtn.isEnabled = true
                    }
                    checkInternetConnection()
                    requireView().showToast(getString(R.string.check_username_pass))
                }
            }
        }

        viewModel.firebaseLoginState.observe(viewLifecycleOwner) { firebaseLoginState ->
            when (firebaseLoginState) {
                ScreenState.Loading -> {
                    binding.apply {
                        loading.visible()
                        loginBtn.isEnabled = false
                    }
                }
                is ScreenState.Success -> {
                    binding.apply {
                        loading.gone()
                        loginBtn.isEnabled = true
                    }
                    navigateToHomeScreen()
                    requireView().showToast("Welcome ${firebaseLoginState.uiData.email}")
                    saveUserIdToSharedPref(firebaseLoginState.uiData.email)
                }
                is ScreenState.Error -> {
                    binding.apply {
                        loading.gone()
                        loginBtn.isEnabled = true
                    }
                    checkInternetConnection()
                    requireView().showToast(firebaseLoginState.message)
                }
            }
        }
    }

    private fun saveUserIdToSharedPref(id: String) {
        if (binding.firebaseLoginCheckbox.isChecked) {
            sharedPref.edit().putString(SHARED_PREF_FIREBASE_USERID_KEY, id).apply()
            sharedPref.edit().putBoolean(SHARED_PREF_IS_FIREBASE_USER, binding.firebaseLoginCheckbox.isChecked).apply()
        } else {
            sharedPref.edit().putString(SHARED_PREF_USERID_KEY, id).apply()
            sharedPref.edit().putBoolean(SHARED_PREF_IS_FIREBASE_USER, binding.firebaseLoginCheckbox.isChecked).apply()
        }
    }

    private fun navigateToHomeScreen() {
        findNavController().safeNavigate(LoginFragmentDirections.actionLoginFragmentToHomeFragment())
    }

    private fun extractExpirationTimeFromToken(token: String) {
        JWT.decode(
            token,
        ).also {
            it.tap { decodedJWT ->
                expirationTime = decodedJWT.claimValueAsLong("exp").getOrElse { 0L }.toString()
                Log.d("LoginFragment", "Expiration time: $expirationTime")
            }
        }
    }

    private fun setupLoginButton() {
        binding.loginBtn.setOnClickListener {
            if (binding.firebaseLoginCheckbox.isChecked) {
                firebaseLoginLogic()
            } else {
                apiLoginLogic()
            }
        }
    }

    private fun apiLoginLogic() {
        val username = binding.username.text.toString().trim()
        val password = binding.password.text.toString().trim()

        if (username.isBlank() || password.isBlank()) {
            requireView().showToast(getString(R.string.please_not_blanks))
            return
        }

        val user = User(username, password)
        viewModel.login(user)
    }

    private fun firebaseLoginLogic() {
        val email = binding.username.text.toString().trim()
        val password = binding.password.text.toString().trim()

        if (email.isBlank() || password.isBlank()) {
            requireView().showToast(getString(R.string.please_not_blanks))
            return
        }
        val user  = FirebaseSignInUserEntity(email, password)
        viewModel.loginWithFirebase(user)
    }
}
