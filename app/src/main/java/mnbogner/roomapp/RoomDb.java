package mnbogner.roomapp;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {RoomUser.class}, version = 1)
public abstract class RoomDb extends RoomDatabase {
    public abstract RoomUserDao ruDao();
}
