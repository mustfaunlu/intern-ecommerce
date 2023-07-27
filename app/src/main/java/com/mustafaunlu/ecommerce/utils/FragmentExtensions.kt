package com.mustafaunlu.ecommerce.utils

import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import com.mustafaunlu.ecommerce.R
import com.mustafaunlu.ecommerce.common.PermissionManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun Fragment.showConfirmationDialog(message: String, onConfirm: () -> Unit) {
    AlertDialog.Builder(requireContext())
        .setMessage(message)
        .setPositiveButton("Yes") { _, _ -> onConfirm.invoke() }
        .setNegativeButton("No", null)
        .show()
}

fun NavController.safeNavigate(direction: NavDirections) {
    currentDestination?.getAction(direction.actionId)?.run { navigate(direction) }
}

fun Fragment.checkInternetConnection() {
    viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {
        val context = context ?: return@launch

        val isInternetAvailable = PermissionManager(requireActivity()).isInternetAvailable(context)

        requireActivity().runOnUiThread {
            if (isInternetAvailable) {
                // Toast.makeText(context, "İnternet bağlantısı var", Toast.LENGTH_SHORT).show()
            } else {
                showConfirmationDialog(
                    getString(R.string.no_internet_connection_dialog),
                    onConfirm = {
                        if (isAdded) {
                            PermissionManager(requireActivity()).enableInternet()
                        }
                    },
                )
            }
        }
    }
}
