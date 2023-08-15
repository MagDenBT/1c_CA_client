package ch.magdenbt.a1ccaclient.presentations

import android.R
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import ch.magdenbt.a1ccaclient.R.*
import ch.magdenbt.a1ccaclient.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //initStatusBarForApiBelowKITKAT_WATCH()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(id.fragmentContainer) as NavHostFragment
        val navController = navHostFragment.navController

        binding.bottomNavigationView.setupWithNavController(navController)
    }



//    private fun initStatusBarForApiBelowKITKAT_WATCH() {
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
//            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
//            val tintManager = SystemBarTintManager(this)
//            tintManager.isStatusBarTintEnabled = true
//            tintManager.setTintColor(ContextCompat.getColor(this, color.black))
//        }
//
//    }
}