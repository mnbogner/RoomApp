package mnbogner.roomapp;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface QuestionDao {

    @Insert(onConflict = REPLACE)
    long insert(Question newQuestion);

    @Query("SELECT * FROM question WHERE id = :questionId")
    LiveData<Question> getLiveQuestionById(long questionId);

    @Query("SELECT * FROM question WHERE storyId = :storyId")
    LiveData<List<Question>> getLiveQuestionListByStoryId(long storyId);

    @Query("SELECT * FROM question")
    LiveData<List<Question>> getLiveQuestionList();
}
