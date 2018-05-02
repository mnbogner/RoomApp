package mnbogner.roomapp;

import android.support.v7.widget.RecyclerView;

public interface RoomDragListener {

    // viewHolder The holder of the view to drag.
    void onStartDrag(RecyclerView.ViewHolder viewHolder);
}
