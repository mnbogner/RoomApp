package mnbogner.roomapp;

import android.support.v7.widget.RecyclerView;

public interface StorylinesDragListener {

    // viewHolder specifies the item being dragged
    void onStartDrag(RecyclerView.ViewHolder viewHolder);
}
