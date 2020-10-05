package sh.surge.jakub.koleorecruitmenttask.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import sh.surge.jakub.koleorecruitmenttask.R
import sh.surge.jakub.koleorecruitmenttask.activity.vm.MainViewModel
import sh.surge.jakub.koleorecruitmenttask.di.AppModule
import sh.surge.jakub.koleorecruitmenttask.di.DaggerAppComponent
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModel: MainViewModel
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

        setupDependencyInjection()
        setNavController()
        getDataAndSkipSplash()
    }

    private fun setupDependencyInjection() {
        DaggerAppComponent.builder().appModule(AppModule()).build().inject(this)
    }

    private fun setNavController() {
        navController = findNavController(R.id.navFragmentContainer)
    }

    private fun getDataAndSkipSplash() {
        viewModel.liveData.observe(this, {
            if (!it.isNullOrEmpty()) navController.navigate(R.id.action_splashFragment_to_searchFragment)
        })
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