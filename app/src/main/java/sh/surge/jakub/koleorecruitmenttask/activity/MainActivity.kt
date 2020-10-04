package sh.surge.jakub.koleorecruitmenttask.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import sh.surge.jakub.koleorecruitmenttask.R

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController

    private val navigationListener =
        NavController.OnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.splashFragment -> hideStatusBar()
                else -> showStatusBar()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setNavController()
    }

    private fun setNavController() {
        navController = findNavController(R.id.navFragmentContainer)
    }

    override fun onResume() {
        super.onResume()
        navController.addOnDestinationChangedListener(navigationListener)
    }

    override fun onPause() {
        super.onPause()
        navController.removeOnDestinationChangedListener(navigationListener)
    }

    private fun hideStatusBar() = window.addFlags(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)

    private fun showStatusBar() = window.clearFlags(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
}