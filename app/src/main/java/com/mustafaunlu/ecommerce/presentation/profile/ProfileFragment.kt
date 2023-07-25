package com.mustafaunlu.ecommerce.presentation.profile

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.mustafaunlu.ecommerce.common.Constants
import com.mustafaunlu.ecommerce.common.ScreenState
import com.mustafaunlu.ecommerce.databinding.FragmentProfileBinding
import com.mustafaunlu.ecommerce.utils.gone
import com.mustafaunlu.ecommerce.utils.loadImage
import com.mustafaunlu.ecommerce.utils.visible
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ProfileViewModel by viewModels()

    @Inject
    lateinit var sharedPrefs: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()
        observeUserInfo()

        binding.themeSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                sharedPrefs.edit().putBoolean(Constants.PREF_THEME_KEY, true).apply()
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                sharedPrefs.edit().putBoolean(Constants.PREF_THEME_KEY, false).apply()
            }
        }

        binding.btnExit.setOnClickListener {
            findNavController().navigate(ProfileFragmentDirections.actionProfileFragmentToLoginFragment())
        }
    }

    private fun setupViews() {
        val userId = sharedPrefs.getString(
            Constants.SHARED_PREF_USERID_KEY,
            Constants.SHARED_PREF_DEF,
        ) ?: Constants.SHARED_PREF_DEF

        viewModel.getUserInfos(userId.toInt())
    }

    private fun observeUserInfo() {
        viewModel.userInformation.observe(viewLifecycleOwner) { userInfoState ->
            when (userInfoState) {
                ScreenState.Loading -> binding.progressBar.visible()
                is ScreenState.Success -> {
                    binding.progressBar.gone()
                    val userInfo = userInfoState.uiData
                    binding.apply {
                        profileName.text = userInfo.name
                        profileSurname.text = userInfo.surname
                        profileMail.text = userInfo.email
                        profilePhone.text = userInfo.phone
                        pfpImage.loadImage(userInfo.image)
                    }
                }
                is ScreenState.Error -> binding.progressBar.gone()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
