package com.mustafaunlu.ecommerce.presentation.profile

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.mustafaunlu.ecommerce.common.Constants
import com.mustafaunlu.ecommerce.common.ScreenState
import com.mustafaunlu.ecommerce.databinding.FragmentProfileBinding
import com.mustafaunlu.ecommerce.utils.loadImage
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ProfileViewModel by viewModels()

    @Inject
    lateinit var sharedPref: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val userId = sharedPref.getString(
            Constants.SHARED_PREF_USERID_KEY,
            Constants.SHARED_PREF_DEF,
        )!!
        viewModel.getUserInfos(userId.toInt())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.userInformation.observe(viewLifecycleOwner) {
            when (it) {
                ScreenState.Loading -> {
                }
                is ScreenState.Success -> {
                    binding.apply {
                        profileName.text = it.uiData.name
                        profileSurname.text = it.uiData.surname
                        profileMail.text = it.uiData.email
                        profileGender.text = it.uiData.phone
                        pfpImage.loadImage(it.uiData.image)
                    }
                }
                is ScreenState.Error -> {
                }
            }
        }

        binding.btnExit.setOnClickListener {
            findNavController().apply {
                val action = ProfileFragmentDirections.actionProfileFragmentToLoginFragment()
                navigate(action)
            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
