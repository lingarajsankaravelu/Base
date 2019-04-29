package lingaraj.hourglass.in.base.database.user;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class User {

    @PrimaryKey
    private final long id;

    public User(long id) {
        this.id = id;
    }


    public long getId() {
        return id;
    }
}
