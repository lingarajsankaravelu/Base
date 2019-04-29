package lingaraj.hourglass.in.base.database.user;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import io.reactivex.Flowable;
import java.util.List;

@Dao
public interface UserDao {

    @Query("SELECT * from user")
    Flowable<List<User>> getUser();

    @Insert
    void addUser(User user);

}
