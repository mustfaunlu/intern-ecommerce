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
import androidx.activity.result.contract.ActivityResultContracts
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
import com.mustafaunlu.ecommerce.utils.showConfirmationDialog
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

    private val imagePickerLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult(),
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val selectedImageUri: Uri? = result.data?.data
            binding.pfpImage.setImageURI(selectedImageUri)

            selectedImageUri?.let {
                val base64Image = convertImageToBase64(requireContext(), it)
                sharedPrefs.edit().putString(Constants.PREF_SELECTED_IMAGE_URI, base64Image).apply()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        imagePickerLauncher
    }

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
        checkInternetConnection()
        fetchUserInfo()
        observeUserInfo()
        displayProfilePicture()

        binding.pfpImage.setOnClickListener {
            openImagePicker()
        }

        binding.pfpImage.setOnLongClickListener {
            showConfirmationDialog(getString(R.string.do_you_want_to_delete_profile_picture)) {
                deleteProfilePicture()
            }
            true
        }

        binding.themeSwitch.setOnCheckedChangeListener { _, isChecked ->
            themeSwitchLogic(isChecked)
        }

        binding.btnExit.setOnClickListener {
            showConfirmationDialog(getString(R.string.do_you_want_to_exit)) {
                findNavController().navigate(ProfileFragmentDirections.actionProfileFragmentToLoginFragment())
            }
        }
    }

    private fun themeSwitchLogic(isChecked: Boolean) {
        if (isChecked) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            sharedPrefs.edit().putBoolean(Constants.PREF_THEME_KEY, true).apply()
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            sharedPrefs.edit().putBoolean(Constants.PREF_THEME_KEY, false).apply()
        }
    }

    private fun openImagePicker() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        imagePickerLauncher.launch(intent)
    }

    private fun fetchUserInfo() {
        val userId = getUserIdFromSharedPref()
        val isFirebaseUser = sharedPrefs.getBoolean(
            Constants.SHARED_PREF_IS_FIREBASE_USER,
            false,
        )
        if (isFirebaseUser) {
            viewModel.getUserInfosFromFirebase(userId)
        } else {
            viewModel.getUserInfos(userId)
        }
    }

    private fun getUserIdFromSharedPref(): String {
        val apiUserId = sharedPrefs.getString(
            Constants.SHARED_PREF_USERID_KEY,
            Constants.SHARED_PREF_DEF,
        ) ?: Constants.SHARED_PREF_DEF

        val firebaseUserId = sharedPrefs.getString(
            Constants.SHARED_PREF_FIREBASE_USERID_KEY,
            Constants.SHARED_PREF_DEF,
        ) ?: Constants.SHARED_PREF_DEF

        val isFirebaseUser = sharedPrefs.getBoolean(
            Constants.SHARED_PREF_IS_FIREBASE_USER,
            false,
        )
        return if (isFirebaseUser) {
            firebaseUserId
        } else {
            apiUserId
        }
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
            val bitmap = BitmapFactory.decodeByteArray(byteArray, OFFSET, byteArray.size)
            binding.pfpImage.setImageBitmap(bitmap)
        } else {
            binding.pfpImage.setImageResource(R.drawable.ic_plus)
        }
    }

    private fun deleteProfilePicture() {
        sharedPrefs.edit().remove(Constants.PREF_SELECTED_IMAGE_URI).apply()
        binding.pfpImage.setImageResource(R.drawable.ic_plus)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val OFFSET = 0
    }
}
