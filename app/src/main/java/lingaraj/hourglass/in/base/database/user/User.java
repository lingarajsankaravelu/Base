package lingaraj.hourglass.in.base.database.user;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

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
