package lingaraj.hourglass.in.base.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import lingaraj.hourglass.in.base.database.location.Location;
import lingaraj.hourglass.in.base.database.location.LocationDAO;
import lingaraj.hourglass.in.base.database.user.User;
import lingaraj.hourglass.in.base.database.user.UserDao;

@Database(entities =  {
    Location.class
}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
     //dao declarations goes here
  public abstract UserDao userDao();
  public abstract LocationDAO locationDAO();
}
