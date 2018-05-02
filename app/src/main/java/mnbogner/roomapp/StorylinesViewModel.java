package mnbogner.roomapp;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;

import java.util.List;

public class StorylinesViewModel extends ViewModel {

    private StorylinesRepository repository;

    private LiveData<List<Story>> storyList = null;
    private LiveData<List<Question>> questionList = null;
    private long storyId = -1;


    public StorylinesViewModel(Context context) {
        this.repository = new StorylinesRepository(context);
    }

    public LiveData<List<Story>> getStories() {
        if (storyList == null) {
            storyList = repository.getLiveStoryList();
        }
        return storyList;
    }

    public LiveData<List<Question>> getQuestions(long storyId) {
        if ((questionList == null) || (this.storyId != storyId)) {
            questionList = repository.getLiveQuestionListByStoryId(storyId);
            this.storyId = storyId;
        }
        return questionList;
    }
}
