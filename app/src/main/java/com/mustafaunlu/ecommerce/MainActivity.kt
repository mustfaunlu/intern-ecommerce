package com.mustafaunlu.ecommerce

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.mustafaunlu.ecommerce.databinding.ActivityMainBinding
import com.mustafaunlu.ecommerce.utils.gone
import com.mustafaunlu.ecommerce.utils.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /*FirebaseMessaging.getInstance().token.addOnCompleteListener(
            OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    Log.w("Fail", "Fetching FCM registration token failed", task.exception)
                    return@OnCompleteListener
                }

                // Get new FCM registration token
                val token = task.result

                // Log and toast
                val msg = token.toString()
                Log.d("Token", msg)
            },
        )
*/
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        setupWithNavController(binding.bottomNavigation, navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.loginFragment -> {
                    binding.bottomNavigation.gone()
                    supportActionBar?.hide()
                }
                R.id.homeFragment -> {
                    binding.bottomNavigation.visible()
                    supportActionBar?.hide()
                }
                R.id.cartFragment -> {
                    binding.bottomNavigation.visible()
                    setActionBarProperties(getString(R.string.title_shopping_cart), true)
                }
                R.id.profileFragment -> {
                    binding.bottomNavigation.visible()
                    setActionBarProperties(getString(R.string.title_profile), true)
                }
                R.id.detailFragment -> {
                    binding.bottomNavigation.visible()
                    setActionBarProperties(getString(R.string.title_product_info), true)
                }
                else -> {
                    binding.bottomNavigation.visible()
                    supportActionBar?.show()
                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
    private fun setActionBarProperties(title: String, showHomeButton: Boolean = false) {
        supportActionBar?.apply {
            this.title = title
            setHomeButtonEnabled(showHomeButton)
            setDisplayHomeAsUpEnabled(showHomeButton)
        }
        if (showHomeButton) {
            supportActionBar?.show()
        } else {
            supportActionBar?.hide()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
