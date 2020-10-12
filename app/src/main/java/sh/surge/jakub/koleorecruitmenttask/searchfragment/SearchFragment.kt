package sh.surge.jakub.koleorecruitmenttask.searchfragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import ext.observeText
import ext.setOnclickListenerAndGetItem
import ext.zipLiveData
import kotlinx.android.synthetic.main.fragment_search.*
import sh.surge.jakub.koleorecruitmenttask.R
import sh.surge.jakub.koleorecruitmenttask.data.station.Station
import sh.surge.jakub.koleorecruitmenttask.di.AppModule
import sh.surge.jakub.koleorecruitmenttask.di.DaggerAppComponent
import javax.inject.Inject

class SearchFragment : Fragment(R.layout.fragment_search) {

    @Inject
    lateinit var viewModel: SearchViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupDagger()
        observeEditTexts()
        onListenOnItemClicks()
    }

    private fun setupDagger() = DaggerAppComponent.builder().appModule(AppModule(requireContext())).build().inject(this)

    private fun observeEditTexts() {
        startStationSearchET.observeText(this::getStationWithNameForStartStation)
        endStationSearchET.observeText(this::getStationWithNameForEndStation)
    }

    private fun getStationWithNameForStartStation(text: CharSequence) {
        viewModel.getStationWithKeyword(text).observe(viewLifecycleOwner, {
            val adapter = AutoCompleteAdapter(this.requireContext())
            adapter.updateList(it)
            startStationSearchET.setAdapter(adapter)
        })
    }

    private fun getStationWithNameForEndStation(text: CharSequence) {
        viewModel.getStationWithKeyword(text).observe(viewLifecycleOwner, {
            val adapter = AutoCompleteAdapter(this.requireContext())
            adapter.updateList(it)
            endStationSearchET.setAdapter(adapter)
        })
    }

    private fun onListenOnItemClicks() {
        startStationSearchET.setOnclickListenerAndGetItem().zipLiveData(endStationSearchET.setOnclickListenerAndGetItem()).observe(viewLifecycleOwner,
            {
                calculateDistance(it.first, it.second)
            })
    }

    private fun calculateDistance(startStation: Station?, endStation: Station?) {
        distanceBetweenStationsValue.text = viewModel.getDistance(startStation, endStation)
        distanceBetweenStationsDescription.visibility = View.VISIBLE

    }
}