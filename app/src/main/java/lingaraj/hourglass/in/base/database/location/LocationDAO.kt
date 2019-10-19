package lingaraj.hourglass.`in`.base.database.location

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Flowable

interface LocationDAO {

    @Query("SELECT * from Location")
    fun  getLocations():Flowable<List<Location>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(locations:List<Location>)


}