package com.mustafaunlu.ecommerce.presentation

import android.content.Context
import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import com.mustafaunlu.ecommerce.R
import com.mustafaunlu.ecommerce.databinding.ActivityMainBinding
import com.mustafaunlu.ecommerce.utils.TokenManager
import com.mustafaunlu.ecommerce.utils.gone
import com.mustafaunlu.ecommerce.utils.visible
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var tokenManager: TokenManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupFCMToken()
        setupNavigation()
    }

    override fun onResume() {
        super.onResume()
        if (!tokenManager.isTokenValid()) {
            navController.navigate(R.id.action_global_loginFragment)
        }
    }
    private fun setupFCMToken() {
        FirebaseMessaging.getInstance().token.addOnCompleteListener(
            OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    Log.w("Fail", "Fetching FCM registration token failed", task.exception)
                    return@OnCompleteListener
                }

                // Get new FCM registration token
                val token = task.result

                // Log
                val msg = token.toString()
                Log.d("Token", msg)
            },
        )
    }

    private fun setupNavigation() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        setupWithNavController(binding.bottomNavigation, navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.homeFragment -> {
                    updateBottomNavigationVisibility(true)
                    supportActionBar?.hide()
                }
                R.id.cartFragment -> {
                    updateBottomNavigationVisibility(true)
                    setActionBarProperties(getString(R.string.title_shopping_cart))
                }
                R.id.profileFragment -> {
                    updateBottomNavigationVisibility(true)
                    setActionBarProperties(getString(R.string.title_profile))
                }
                R.id.detailFragment -> {
                    updateBottomNavigationVisibility(true)
                    setActionBarProperties(getString(R.string.title_product_info))
                }
                R.id.favoriteFragment -> {
                    updateBottomNavigationVisibility(true)
                    setActionBarProperties(getString(R.string.title_favorite))
                }
                else -> {
                    updateBottomNavigationVisibility(false)
                    supportActionBar?.hide()
                }
            }
        }
    }

    private fun updateBottomNavigationVisibility(isVisible: Boolean) {
        if (isVisible) {
            binding.bottomNavigation.visible()
        } else {
            binding.bottomNavigation.gone()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    private fun setActionBarProperties(title: String = "") {
        supportActionBar?.apply {
            this.title = title
            this.show()
            setHomeButtonEnabled(true)
            setDisplayHomeAsUpEnabled(true)
        }
    }

    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {
            val v = currentFocus
            if (v is EditText) {
                val outRect = Rect()
                v.getGlobalVisibleRect(outRect)
                if (!outRect.contains(event.rawX.toInt(), event.rawY.toInt())) {
                    v.clearFocus()
                    val imm =
                        getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0)
                }
            }
        }
        return super.dispatchTouchEvent(event)
    }
}
