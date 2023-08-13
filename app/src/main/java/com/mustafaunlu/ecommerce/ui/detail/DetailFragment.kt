package com.mustafaunlu.ecommerce.ui.detail

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.google.android.material.tabs.TabLayoutMediator
import com.mustafaunlu.ecommerce.R
import com.mustafaunlu.ecommerce.common.ScreenState
import com.mustafaunlu.ecommerce.databinding.FragmentDetailBinding
import com.mustafaunlu.ecommerce.domain.entity.cart.UserCartBadgeEntity
import com.mustafaunlu.ecommerce.domain.entity.cart.UserCartEntity
import com.mustafaunlu.ecommerce.utils.checkInternetConnection
import com.mustafaunlu.ecommerce.utils.getUserIdFromSharedPref
import com.mustafaunlu.ecommerce.utils.gone
import com.mustafaunlu.ecommerce.utils.showBadgeVisibility
import com.mustafaunlu.ecommerce.utils.showToast
import com.mustafaunlu.ecommerce.utils.visible
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private val detailViewModel: DetailViewModel by viewModels()
    private val args: DetailFragmentArgs by navArgs()
    private lateinit var userCart: UserCartEntity

    @Inject
    lateinit var sharedPref: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        detailViewModel.getProduct(args.productId)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkInternetConnection()
        setupProductDetail()
        setupAddToCartButton()
        binding.btnAddToFav.setOnClickListener {
            addToFavorite()
        }


    }

    private fun setupProductDetail() {
        detailViewModel.product.observe(viewLifecycleOwner) { productState ->
            when (productState) {
                is ScreenState.Error -> {
                    binding.detailProgressBar.gone()
                    requireView().showToast(productState.message)
                }

                is ScreenState.Success -> {
                    binding.detailProgressBar.gone()
                    val product = productState.uiData
                    bindProductDetailToView(product)
                    viewPagerSetup(product)
                }

                ScreenState.Loading -> binding.detailProgressBar.visible()
            }
        }
    }

    private fun viewPagerSetup(product: DetailProductUiData) {
        binding.viewPager.adapter = DetailImageViewPagerAdapter(product.imageUrl)
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { _, _ -> }.attach()
    }

    @SuppressLint("SetTextI18n")
    private fun bindProductDetailToView(product: DetailProductUiData) {
        binding.apply {
            detailProductTitle.text = product.title
            detailProductPrice.text = "${product.price} TL"
            detailProductDescription.text = product.description
            detailProductRatingTxt.text = product.rating
            detailProductRating.rating = product.rating.toFloat()

            val userId = getUserIdFromSharedPref(sharedPref)
            userCart = UserCartEntity(
                userId = userId,
                productId = product.id,
                quantity = 1,
                price = product.price.toInt(),
                title = product.title,
                image = product.imageUrl[0],
            )
        }
    }

    private fun addToFavorite() {
        detailViewModel.addToFavorite(userCart)
        requireView().showToast(getString(R.string.added_to_favorite))
    }

    private fun setupAddToCartButton() {
        binding.btnAddToCart.setOnClickListener {
            detailViewModel.addToCart(userCart)
            requireView().showToast(getString(R.string.added_to_cart))
            val badgeEntity = UserCartBadgeEntity(
                userUniqueInfo = userCart.userId,
                hasBadge = true,
            )
            detailViewModel.insertBadgeStatusToDb(badgeEntity)
            showBadgeVisibility(badgeEntity.hasBadge)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
