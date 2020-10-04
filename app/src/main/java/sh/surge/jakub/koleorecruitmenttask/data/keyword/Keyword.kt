package sh.surge.jakub.koleorecruitmenttask.data.keyword

import androidx.annotation.Keep
import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Keep
@Entity(tableName = "keyword")
data class Keyword(
    val id: Int,
    val keyword: String,
    @SerializedName("station_id")
    val stationId: Int
)