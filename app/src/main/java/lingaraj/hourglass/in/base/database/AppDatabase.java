package lingaraj.hourglass.in.base.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import lingaraj.hourglass.in.base.database.user.User;
import lingaraj.hourglass.in.base.database.user.UserDao;

@Database(entities =  {
    User.class
}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
     //dao declarations goes here
  public abstract UserDao userDao();

}
