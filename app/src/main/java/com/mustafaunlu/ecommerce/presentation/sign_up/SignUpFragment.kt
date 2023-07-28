package com.mustafaunlu.ecommerce.presentation.sign_up

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.mustafaunlu.ecommerce.R
import com.mustafaunlu.ecommerce.common.ScreenState
import com.mustafaunlu.ecommerce.data.dto.UserSignUp
import com.mustafaunlu.ecommerce.databinding.FragmentSignUpBinding
import com.mustafaunlu.ecommerce.utils.showConfirmationDialog
import com.mustafaunlu.ecommerce.utils.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignupFragment : Fragment() {
    private var _binding: FragmentSignUpBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SignUpViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentSignUpBinding.inflate(inflater, container, false)
        (activity as AppCompatActivity).supportActionBar?.hide()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnCreateAccount.setOnClickListener {
            checkEmptyFields { user ->
                viewModel.signUp(user)
            }
        }
        observer()
    }

    private fun observer() {
        viewModel.signUp.observe(viewLifecycleOwner) {
            when (it) {
                is ScreenState.Loading -> {
                    binding.btnCreateAccount.isEnabled = false
                }
                is ScreenState.Success -> {
                    binding.btnCreateAccount.isEnabled = true
                    requireView().showToast(getString(R.string.sign_up_success))
                    findNavController().navigate(R.id.loginFragment)
                }
                is ScreenState.Error -> {
                    binding.btnCreateAccount.isEnabled = true
                    requireView().showToast(getString(R.string.sign_up_success))
                    findNavController().navigate(R.id.loginFragment)
                }
            }
        }
    }

    private fun checkEmptyFields(
        onSuccess: (UserSignUp) -> Unit,
    ) {
        val email = binding.email.text.toString()
        val password = binding.password.text.toString()
        val name = binding.name.text.toString()
        val surname = binding.surname.text.toString()

        if (email.isNotEmpty() && password.isNotEmpty() && name.isNotEmpty() && surname.isNotEmpty()) {
            onSuccess(
                UserSignUp(
                    name = name,
                    surname = surname,
                    email = email,
                    phone = "",
                    password = password,
                ),
            )
        } else {
            requireView().showToast(getString(R.string.empty_fields))
        }
    }
}
