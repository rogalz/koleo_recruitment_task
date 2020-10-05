package sh.surge.jakub.koleorecruitmenttask.data.keyword

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface KeywordDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(keywords: List<Keyword>)

    @Query("SELECT * FROM keyword")
    fun getAllKeywords(): LiveData<List<Keyword>>
}