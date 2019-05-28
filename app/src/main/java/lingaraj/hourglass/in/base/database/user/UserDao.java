package lingaraj.hourglass.in.base.database.user;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import io.reactivex.Flowable;
import java.util.List;

@Dao
public interface UserDao {

    @Query("SELECT * from user")
    Flowable<List<User>> getUser();

    @Insert
    void addUser(User user);

}
