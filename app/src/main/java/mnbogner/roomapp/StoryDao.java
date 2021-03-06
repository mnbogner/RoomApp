package mnbogner.roomapp;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface StoryDao {

    @Insert(onConflict = REPLACE)
    long insert(Story newStory);

    @Query("SELECT * FROM story WHERE id = :storyId")
    LiveData<Story> getLiveStoryById(long storyId);

    @Query("SELECT * FROM story")
    LiveData<List<Story>> getLiveStoryList();
}
