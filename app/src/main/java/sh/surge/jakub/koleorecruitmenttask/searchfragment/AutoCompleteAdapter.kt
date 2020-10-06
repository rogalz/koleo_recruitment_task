package sh.surge.jakub.koleorecruitmenttask.searchfragment

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Filter
import android.widget.TextView
import sh.surge.jakub.koleorecruitmenttask.R
import sh.surge.jakub.koleorecruitmenttask.data.station.Station
import java.util.*

class AutoCompleteAdapter(context: Context) : ArrayAdapter<Station>(context, R.layout.item_station) {
    private val stationList = mutableListOf<Station>()

    fun updateList(list: List<Station>) {
        stationList.clear()
        stationList.addAll(list)
        notifyDataSetChanged()
    }

    override fun getCount() = stationList.size
    override fun getItem(position: Int): Station? = stationList[position]

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.item_station, parent, false)

        val stationName = view.findViewById<TextView>(R.id.stationName)
        val stationLocation = view.findViewById<TextView>(R.id.stationLocation)

        val station: Station? = stationList[position]
        station?.let {
            stationName.text = it.name
            val stationLocationText = "${it.city} ,  ${it.country}"
            stationLocation.text = stationLocationText
        }

        return view
    }

    override fun getFilter(): Filter {
        return filter
    }

    private val filter = object : Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val filterResults = FilterResults()
            val suggestions: MutableList<Station> = ArrayList<Station>()

            suggestions.addAll(stationList)
            filterResults.values = suggestions
            filterResults.count = suggestions.size

            return filterResults
        }

        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            clear()
            addAll(results!!.values as List<Station>)
            notifyDataSetChanged()
        }

    }
}

