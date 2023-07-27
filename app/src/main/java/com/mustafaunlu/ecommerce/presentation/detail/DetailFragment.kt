package com.mustafaunlu.ecommerce.presentation.detail

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context.NOTIFICATION_SERVICE
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavDeepLinkBuilder
import androidx.navigation.fragment.navArgs
import com.mustafaunlu.ecommerce.R
import com.mustafaunlu.ecommerce.common.Constants
import com.mustafaunlu.ecommerce.common.Constants.SHARED_PREF_DEF
import com.mustafaunlu.ecommerce.common.Constants.SHARED_PREF_USERID_KEY
import com.mustafaunlu.ecommerce.common.ScreenState
import com.mustafaunlu.ecommerce.common.SingleProductUiData
import com.mustafaunlu.ecommerce.databinding.FragmentDetailBinding
import com.mustafaunlu.ecommerce.domain.entity.UserCartEntity
import com.mustafaunlu.ecommerce.utils.checkInternetConnection
import com.mustafaunlu.ecommerce.utils.gone
import com.mustafaunlu.ecommerce.utils.loadImage
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
        //setupReminderButton()

        binding.favoriteBtn.setOnClickListener {
            addToFavorite()
        }
    }
    private fun setupProductDetail() {
        detailViewModel.product.observe(viewLifecycleOwner) { productState ->
            when (productState) {
                is ScreenState.Error -> {
                    binding.progressBar.gone()
                    requireView().showToast(productState.message)
                }

                is ScreenState.Success -> {
                    binding.progressBar.gone()
                    val product = productState.uiData
                    bindProductDetailToView(product)
                }

                ScreenState.Loading -> binding.progressBar.visible()
            }
        }
    }

    /*@SuppressLint("MissingPermission")
    private fun setupReminderButton() {
        binding.reminderBtn?.setOnClickListener {
            val pendingIntent: PendingIntent = NavDeepLinkBuilder(requireContext())
                .setComponentName(MainActivity::class.java)
                .setGraph(R.navigation.nav_graph)
                .setDestination(R.id.detailFragment) // Eğer bildirim tıklandığında hangi fragment açılacaksa buraya uygun fragment belirtin
                .createPendingIntent()

            val CHANNEL_ID = "com.mustafaunlu.ecommerce"
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                // Create the NotificationChannel.
                val name = "Reminder Channel"
                val descriptionText = "getString(R.string.channel_description)"
                val importance = NotificationManager.IMPORTANCE_DEFAULT
                val mChannel = NotificationChannel(CHANNEL_ID, name, importance)
                mChannel.description = descriptionText
                // Register the channel with the system. You can't change the importance
                // or other notification behaviors after this.
                val notificationManager = requireContext().getSystemService(NOTIFICATION_SERVICE) as NotificationManager
                notificationManager.createNotificationChannel(mChannel)
            }

            val builder = NotificationCompat.Builder(requireContext(), CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Title")
                .setContentText("Content Text")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)

            with(NotificationManagerCompat.from(requireContext())) {
                // notificationId is a unique int for each notification that you must define
                notify(5432, builder.build())
            }
        }
    }*/

    @SuppressLint("SetTextI18n")
    private fun bindProductDetailToView(product: SingleProductUiData) {
        binding.apply {
            productTitle.text = product.title
            productPrice.text = "${product.price} TL"
            productDescription.text = product.description
            productImg.loadImage(product.imageUrl)
            productRating.text = "Rating ${product.rating}"

            val userId = getUserIdFromSharedPref()
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

    private fun getUserIdFromSharedPref(): String {
        return sharedPref.getString(
            SHARED_PREF_USERID_KEY,
            SHARED_PREF_DEF,
        ) ?: SHARED_PREF_DEF
    }

    private fun addToFavorite() {
        detailViewModel.addToFavorite(userCart)
    }

    private fun setupAddToCartButton() {
        binding.btnAddToCart.setOnClickListener {
            detailViewModel.addToCart(userCart)
            requireView().showToast(getString(R.string.added_to_cart))
            showBadgeVisibility(true)
            sharedPref.edit().putBoolean(Constants.SHARED_PREF_BADGE, true).apply()
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
