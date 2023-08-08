package com.mustafaunlu.ecommerce.presentation.forgot_pw

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

        viewModel.forgotPassword.observe(viewLifecycleOwner) {
            when (it) {
                is ScreenState.Success -> {
                    requireView().showToast("Password reset link sent to your email address")
                    findNavController().popBackStack()
                }

                ScreenState.Loading -> {}
                is ScreenState.Error -> {
                    requireView().showToast(it.message)
                }
            }
        }

        binding.btnResetPassword.setOnClickListener {
            checkEmail { email ->
                viewModel.forgotPassword(email)
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