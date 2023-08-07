package com.mustafaunlu.ecommerce.presentation.favorite

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.mustafaunlu.ecommerce.R
import com.mustafaunlu.ecommerce.common.Constants
import com.mustafaunlu.ecommerce.common.ScreenState
import com.mustafaunlu.ecommerce.databinding.FragmentFavoriteBinding
import com.mustafaunlu.ecommerce.utils.gone
import com.mustafaunlu.ecommerce.utils.showConfirmationDialog
import com.mustafaunlu.ecommerce.utils.showToast
import com.mustafaunlu.ecommerce.utils.visible
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!
    private val viewModel: FavoriteViewModel by viewModels()
    private lateinit var adapter: FavoriteListAdapter

    @Inject
    lateinit var sharedPref: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        adapter = FavoriteListAdapter(::onItemLongClicked, ::onItemShortClicked)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getFavoriteProducts(getUserIdFromSharedPref())
        setupObserver()
    }

    private fun setupObserver() {
        viewModel.favoriteCarts.observe(viewLifecycleOwner) { favoriteItem ->
            when (favoriteItem) {
                is ScreenState.Error -> {
                    binding.progressBar.gone()
                    requireView().showToast(favoriteItem.message)
                }

                ScreenState.Loading -> binding.progressBar.visible()
                is ScreenState.Success -> {
                    binding.progressBar.gone()
                    adapter.submitList(favoriteItem.uiData)
                    binding.favoriteListview.adapter = adapter
                }
            }
        }
    }

    private fun getUserIdFromSharedPref(): String {
        val apiUserId = sharedPref.getString(
            Constants.SHARED_PREF_USERID_KEY,
            Constants.SHARED_PREF_DEF,
        ) ?: Constants.SHARED_PREF_DEF

        val firebaseUserId = sharedPref.getString(
            Constants.SHARED_PREF_FIREBASE_USERID_KEY,
            Constants.SHARED_PREF_DEF,
        ) ?: Constants.SHARED_PREF_DEF

        val isFirebaseUser = sharedPref.getBoolean(
            Constants.SHARED_PREF_IS_FIREBASE_USER,
            false,
        )
        return if (isFirebaseUser) {
            firebaseUserId
        } else {
            apiUserId
        }
    }

    private fun onItemLongClicked(favoriteUiData: FavoriteUiData) {
        this.showConfirmationDialog(getString(R.string.favorite_list_delete_warn)) {
            viewModel.deleteFavoriteItem(favoriteUiData)
            val newList =
                adapter.currentList.filter { it.productId != favoriteUiData.productId }
            adapter.submitList(newList)
            requireView().showToast(getString(R.string.deleted_favorite_list))
        }
    }

    private fun onItemShortClicked(favoriteUiData: FavoriteUiData) {
        val action =
            FavoriteFragmentDirections.actionFavoriteFragmentToDetailFragment(favoriteUiData.productId)
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
