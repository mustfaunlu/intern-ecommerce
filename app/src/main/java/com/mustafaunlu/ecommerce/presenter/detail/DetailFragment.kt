package com.mustafaunlu.ecommerce.presenter.detail

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.mustafaunlu.ecommerce.R
import com.mustafaunlu.ecommerce.common.Constants.SHARED_PREF_DEF
import com.mustafaunlu.ecommerce.common.Constants.SHARED_PREF_USERID_KEY
import com.mustafaunlu.ecommerce.common.ScreenState
import com.mustafaunlu.ecommerce.databinding.FragmentDetailBinding
import com.mustafaunlu.ecommerce.domain.entity.UserCartEntity
import com.mustafaunlu.ecommerce.utils.loadImage
import com.mustafaunlu.ecommerce.utils.showToast
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding
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
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        detailViewModel.getProduct(args.productId)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupProductDetail()
        setupAddToCartButton()
        setupShoppingListButton()
    }

    @SuppressLint("SetTextI18n")
    private fun setupProductDetail() {
        detailViewModel.product.observe(viewLifecycleOwner) { productState ->
            when (productState) {
                is ScreenState.Error -> {
                    requireView().showToast(productState.message)
                }

                is ScreenState.Success -> {
                    val product = productState.uiData
                    binding.apply {
                        productTitle.text = product.title
                        productPrice.text = "${product.price} TL"
                        productDescription.text = product.description
                        productImg.loadImage(product.imageUrl)
                        productRating.text = "Rating ${product.rating}"

                        val userId = sharedPref.getString(
                            SHARED_PREF_USERID_KEY,
                            SHARED_PREF_DEF,
                        ) ?: SHARED_PREF_DEF
                        userCart = UserCartEntity(
                            userId = userId.toInt(),
                            productId = product.id,
                            quantity = 1,
                            price = product.price.toInt(),
                            title = product.title,
                            image = product.imageUrl,
                        )
                    }
                }

                else -> {}
            }
        }
    }

    private fun setupAddToCartButton() {
        binding.btnAddToCart.setOnClickListener {
            detailViewModel.addToCart(userCart)
            requireView().showToast(getString(R.string.added_to_cart))
        }
    }

    private fun setupShoppingListButton() {
        binding.btnShoppingList.setOnClickListener {
            findNavController().navigate(
                DetailFragmentDirections.actionDetailFragmentToCartFragment2(),
            )
        }
    }
}