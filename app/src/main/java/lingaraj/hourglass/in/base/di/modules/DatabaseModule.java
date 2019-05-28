package lingaraj.hourglass.in.base.di.modules;

import androidx.room.Room;
import android.content.Context;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import lingaraj.hourglass.in.base.database.AppDatabase;
import lingaraj.hourglass.in.base.utils.Constants;

@Module
public class DatabaseModule {

    @Provides
    @Singleton
    static AppDatabase provideAppDatabase(Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, Constants.DATBASE_NAME).allowMainThreadQueries().build();
    }

}
