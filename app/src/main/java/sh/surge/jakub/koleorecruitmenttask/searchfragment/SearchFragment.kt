package sh.surge.jakub.koleorecruitmenttask.searchfragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import ext.observeText
import kotlinx.android.synthetic.main.fragment_search.*
import sh.surge.jakub.koleorecruitmenttask.R
import sh.surge.jakub.koleorecruitmenttask.di.AppModule
import sh.surge.jakub.koleorecruitmenttask.di.DaggerAppComponent
import javax.inject.Inject

class SearchFragment : Fragment(R.layout.fragment_search) {

    @Inject
    lateinit var viewModel: SearchViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeEditTexts()

        DaggerAppComponent.builder().appModule(AppModule(requireContext())).build().inject(this)

    }

    private fun observeEditTexts() {
        startStationSearchET.observeText(this::getStationWithName)
    }

    private fun getStationWithName(text: CharSequence) {
        viewModel.getStationWithKeyword(text).observe(viewLifecycleOwner, Observer {

        })
    }
}