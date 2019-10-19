package lingaraj.hourglass.`in`.base.database.location

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Flowable

@Dao
interface LocationDAO {

    @Query("SELECT * from Location")
    fun  getLocations():Flowable<List<Location>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(locations:List<Location>)

    @Query("SELECT * from LOCATION WHERE id=:rowId")
    fun getLocation(rowId:Long):Location

}