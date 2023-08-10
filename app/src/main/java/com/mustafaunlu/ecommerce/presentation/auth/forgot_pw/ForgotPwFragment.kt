package com.mustafaunlu.ecommerce.presentation.auth.forgot_pw

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.mustafaunlu.ecommerce.R
import com.mustafaunlu.ecommerce.common.ScreenState
import com.mustafaunlu.ecommerce.databinding.FragmentForgotPwBinding
import com.mustafaunlu.ecommerce.utils.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ForgotPwFragment : BottomSheetDialogFragment() {
    lateinit var binding: FragmentForgotPwBinding

    private val viewModel: ForgotPwViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentForgotPwBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observer()

        binding.btnResetPassword.setOnClickListener {
            checkEmail { email ->
                viewModel.forgotPassword(email)
            }
        }
    }

    private fun observer() {
        viewModel.forgotPassword.observe(viewLifecycleOwner) { forgotPwState ->
            when (forgotPwState) {
                is ScreenState.Success -> {
                    requireView().showToast(getString(R.string.password_reset_link_sent))
                    findNavController().popBackStack()
                }

                ScreenState.Loading -> {}
                is ScreenState.Error -> {
                    requireView().showToast(forgotPwState.message)
                }
            }
        }
    }

    private fun checkEmail(
        onSuccess: (String) -> Unit,
    ) {
        val email = binding.forgotMailEdtxt.text.toString()

        if (email.isNotEmpty()) {
            onSuccess(email)
        } else {
            requireView().showToast(getString(R.string.please_not_blanks))
        }
    }
}