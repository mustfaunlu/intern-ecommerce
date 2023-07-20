package com.mustafaunlu.ecommerce.presentation.login

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.mustafaunlu.ecommerce.R
import com.mustafaunlu.ecommerce.common.Constants.SHARED_PREF_USERID_KEY
import com.mustafaunlu.ecommerce.common.ScreenState
import com.mustafaunlu.ecommerce.data.dto.User
import com.mustafaunlu.ecommerce.databinding.FragmentLoginBinding
import com.mustafaunlu.ecommerce.utils.gone
import com.mustafaunlu.ecommerce.utils.safeNavigate
import com.mustafaunlu.ecommerce.utils.showToast
import com.mustafaunlu.ecommerce.utils.visible
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private val viewModel: LoginViewModel by viewModels()

    @Inject
    lateinit var sharedPref: SharedPreferences

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

        viewModel.loginState.observe(viewLifecycleOwner) { loginState ->
            when (loginState) {
                ScreenState.Loading -> {
                    binding.loading.visible()
                    binding.loginBtn.isEnabled = false
                }

                is ScreenState.Success -> {
                    binding.loading.gone()
                    binding.loginBtn.isEnabled = true
                    findNavController().safeNavigate(LoginFragmentDirections.actionLoginFragmentToHomeFragment())
                    requireView().showToast("Welcome ${loginState.uiData.username}")
                    sharedPref.edit()
                        .putString(SHARED_PREF_USERID_KEY, loginState.uiData.id.toString())
                        .apply()
                }

                is ScreenState.Error -> {
                    binding.loading.gone()
                    binding.loginBtn.isEnabled = true
                    requireView().showToast(getString(R.string.check_username_pass))
                }
            }
        }
    }
    private fun setupLoginButton() {
        binding.loginBtn.setOnClickListener { loginLogic() }
    }

    private fun loginLogic() {
        val username = binding.username.text.toString().trim()
        val password = binding.password.text.toString().trim()

        if (username.isBlank() || password.isBlank()) {
            requireView().showToast(getString(R.string.please_not_blanks))
            return
        }

        val user = User(username, password)
        viewModel.login(user)
    }
}
