package mnbogner.roomapp;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class RoomActivityFragment extends Fragment implements StorylinesDragListener{

    //private RoomViewModel rvm;
    StorylinesViewModel svm;
    private RecyclerView rv;
    private LinearLayoutManager llm;
    //protected RoomAdapter ra;
    protected StorylinesAdapter sa;
    private ItemTouchHelper touchHelper;

    public RoomActivityFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        svm = new StorylinesViewModel(getContext());
    }

    // onresume - need to retrieve existing veiw model???

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.room_fragment, container, false);

        rv = (RecyclerView) root.findViewById(R.id.room_recycler_view);
        llm = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(llm);
        sa = new StorylinesAdapter(new ArrayList<Question>(), this);
        rv.setAdapter(sa);

        ItemTouchHelper.Callback callback = new StorylinesTouchCallback(sa);
        touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(rv);

        // what triggers observers?  ie: get by id triggered by unrelated db insert?

        // card view???  replace view holder

        final Observer<List<Story>> storyObserver = new Observer<List<Story>>() {

            final Observer<List<Question>> questionObserver = new Observer<List<Question>>() {
                @Override
                public void onChanged(@Nullable final List<Question> questionList) {
                    if (questionList != null) {

                        Log.d("FOO", "QUESTION COUNT: " + questionList.size());

                        sa.addItems(questionList);
                    }
                }
            };

            @Override
            public void onChanged(@Nullable final List<Story> storyList) {
                if ((storyList != null) && (storyList.size() > 0)) {
                    long storyId = storyList.get(0).getId();

                    Log.d("FOO", "STORY ID QUERY: " + storyId);

                    svm.getQuestions(storyId).observe(RoomActivityFragment.this, questionObserver);
                }
            }
        };

        /*
        final Observer<List<Question>> questionObserver = new Observer<List<Question>>() {
            @Override
            public void onChanged(@Nullable final List<Question> questionList) {
                if (questionList != null) {
                    sa.addItems(questionList);
                }
            }
        };
        */

        svm = new StorylinesViewModel(getContext());

        svm.getStories().observe(this, storyObserver);

        return root;
    }

    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        touchHelper.startDrag(viewHolder);
    }
}
