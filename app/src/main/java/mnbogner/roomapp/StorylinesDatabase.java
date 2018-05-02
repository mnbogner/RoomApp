package mnbogner.roomapp;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {Story.class, Question.class}, version = 1)
public abstract class StorylinesDatabase extends RoomDatabase {
    public abstract StoryDao storyDao();
    public abstract QuestionDao questionDao();
}
