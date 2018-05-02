package mnbogner.roomapp;

import android.support.annotation.NonNull;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

public class StorylinesAdapter extends RecyclerView.Adapter<StorylinesAdapter.QuestionHolder> implements StorylinesTouchAdapter {

    private List<Question> questionList;
    private StorylinesDragListener dragListener;

    public StorylinesAdapter(List<Question> questionList, StorylinesDragListener dragListener) {
        this.questionList = questionList;
        this.dragListener = dragListener;
    }

    @NonNull
    @Override
    public StorylinesAdapter.QuestionHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new QuestionHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.question_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionHolder holder, int position) {
        Question question = questionList.get(position);
        holder.question.setText(question.getQuestionText());
        holder.handle.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
                    dragListener.onStartDrag(holder);
                }
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return questionList.size();
    }

    public void addItems(List<Question> questionList) {
        this.questionList = questionList;
        notifyDataSetChanged();
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        if (fromPosition < toPosition) {
            for (int i = fromPosition; i < toPosition; i++) {
                Collections.swap(questionList, i, i + 1);
            }
        } else {
            for (int i = fromPosition; i > toPosition; i--) {
                Collections.swap(questionList, i, i - 1);
            }
        }
        notifyItemMoved(fromPosition, toPosition);
    }

    @Override
    public void onItemDismiss(int position) {
        questionList.remove(position);
        notifyItemRemoved(position);
    }

    static class QuestionHolder extends RecyclerView.ViewHolder {

        private TextView question;
        private ImageView handle;

        QuestionHolder(View view) {
            super(view);
            question = (TextView) view.findViewById(R.id.question_text);
            handle = (ImageView) view.findViewById(R.id.question_handle);
        }
    }
}
