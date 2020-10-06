package sh.surge.jakub.koleorecruitmenttask.data.keyword

import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Keep
@Entity(tableName = "keywords")
data class Keyword(
    @PrimaryKey
    val id: Int,
    val keyword: String,
    @SerializedName("station_id")
    val stationId: Int
)