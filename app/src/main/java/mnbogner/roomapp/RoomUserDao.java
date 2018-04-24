package mnbogner.roomapp;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface RoomUserDao {

    @Insert(onConflict = REPLACE)
    void insert(RoomUser ru);

    @Query("SELECT * FROM roomusers WHERE id = :userId")
    LiveData<RoomUser> getUserStr(String userId);

    @Query("SELECT * FROM roomusers WHERE id = :userId")
    LiveData<RoomUser> getUserInt(int userId);

    @Query("SELECT * FROM roomusers")
    LiveData<List<RoomUser>> getLiveUsers();

}
