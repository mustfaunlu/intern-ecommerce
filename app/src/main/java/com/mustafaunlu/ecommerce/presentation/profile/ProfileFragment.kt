package com.mustafaunlu.ecommerce.presentation.profile

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.mustafaunlu.ecommerce.R
import com.mustafaunlu.ecommerce.common.Constants
import com.mustafaunlu.ecommerce.common.ScreenState
import com.mustafaunlu.ecommerce.databinding.FragmentProfileBinding
import com.mustafaunlu.ecommerce.utils.checkInternetConnection
import com.mustafaunlu.ecommerce.utils.gone
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
        fetchUserInfo()
        observeUserInfo()
        checkInternetConnection()

        displayProfilePicture()

        binding.pfpImage.setOnClickListener {
            openImagePicker()
        }

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

    private fun openImagePicker() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, REQUEST_IMAGE_PICKER)
    }

    private fun fetchUserInfo() {
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
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_PICKER && resultCode == Activity.RESULT_OK && data != null) {
            val selectedImageUri: Uri? = data.data
            // Do something with the selected image URI, for example, set it to the ImageView
            binding.pfpImage.setImageURI(selectedImageUri)

            // Save the selected image as a Base64 string in SharedPreferences
            selectedImageUri?.let {
                val base64Image = convertImageToBase64(requireContext(), it)
                sharedPrefs.edit().putString(Constants.PREF_SELECTED_IMAGE_URI, base64Image).apply()
            }
        }
    }

    private fun convertImageToBase64(context: Context, imageUri: Uri): String {
        val inputStream = context.contentResolver.openInputStream(imageUri)
        val imageByteArray = inputStream?.readBytes()
        inputStream?.close()

        return if (imageByteArray != null) {
            Base64.encodeToString(imageByteArray, Base64.DEFAULT)
        } else {
            ""
        }
    }

    private fun displayProfilePicture() {
        val base64Image = sharedPrefs.getString(Constants.PREF_SELECTED_IMAGE_URI, null)
        if (!base64Image.isNullOrEmpty()) {
            val byteArray = Base64.decode(base64Image, Base64.DEFAULT)
            val bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
            binding.pfpImage.setImageBitmap(bitmap)
        } else {
            // If there's no stored profile picture, set the default "+" drawable
            binding.pfpImage.setImageResource(R.drawable.ic_plus)
        }
    }

    companion object {
        private const val REQUEST_IMAGE_PICKER = 100
    }
}
