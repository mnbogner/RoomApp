package mnbogner.roomapp;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.List;
import java.util.Set;
import java.util.concurrent.Executor;

public class StorylinesRepository {

    private StorylinesDatabase database;
    private Executor executor;

    public StorylinesRepository(Context context) {

        database = Room.databaseBuilder(context, StorylinesDatabase.class, "storylinesdb").build();

        String[] databaseTables = {"story", "question"};

        // invalidation tracker notifies callbacks about modified tables
        database.getInvalidationTracker().addObserver(new InvalidationTracker.Observer(databaseTables) {
            @Override
            public void onInvalidated(@NonNull Set<String> set) {
                // no-op?
            }
        });

        // executor asynchronously runs database queries
        executor = new Executor() {
            @Override
            public void execute(@NonNull Runnable command) {
                new Thread(command).start();
            }
        };

        initializeDatabase();
    }

    public void initializeDatabase() {

        // temporary implementation, adds default data for testing
        executor.execute(new Runnable() {
            @Override
            public void run() {

                Story story1 = new Story();
                story1.setTitle("First New Story");

                long storyId1 = database.storyDao().insert(story1);

                Log.d("FOO", "STORY ID INSERT: " + storyId1);

                Story story2 = new Story();
                story2.setTitle("Second New Story");

                long storyId2 = database.storyDao().insert(story2);

                Story story3 = new Story();
                story3.setTitle("Third New Story");

                long storyId3 = database.storyDao().insert(story3);

                Question question1 = new Question();
                question1.setStoryId(storyId1);
                question1.setQuestionText("Who is it?");

                long questionId1 = database.questionDao().insert(question1);

                Log.d("FOO", "QUESTION ID INSERT: " + questionId1);

                Question question2 = new Question();
                question2.setStoryId(storyId1);
                question2.setQuestionText("What is it?");

                long questionId2 = database.questionDao().insert(question2);

                Log.d("FOO", "QUESTION ID INSERT: " + questionId2);

                Question question3 = new Question();
                question3.setStoryId(storyId1);
                question3.setQuestionText("Where is it?");

                long questionId3 = database.questionDao().insert(question3);

                Log.d("FOO", "QUESTION ID INSERT: " + questionId3);
            }
        });
    }

    public void insertStory(Story newStory) {
        database.storyDao().insert(newStory);
    }

    public LiveData<Story> getLiveStoryById(int storyId) {
        return database.storyDao().getLiveStoryById(storyId);
    }

    public LiveData<List<Story>> getLiveStoryList() {
        return database.storyDao().getLiveStoryList();
    }

    public void insertQuestion(Question newQuestion) {
        database.questionDao().insert(newQuestion);
    }

    public LiveData<Question> getLiveQuestionById(int questionId) {
        return database.questionDao().getLiveQuestionById(questionId);
    }

    public LiveData<List<Question>> getLiveQuestionListByStoryId(long storyId) {
        return database.questionDao().getLiveQuestionListByStoryId(storyId);
    }

    public LiveData<List<Question>> getLiveQuestionList() {
        return database.questionDao().getLiveQuestionList();
    }
}
