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

public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.RoomHolder> implements RoomItemTouchAdapter{

    private List<RoomUser> ruList;
    private RoomDragListener rdListener;


    public RoomAdapter(List<RoomUser> ruList, RoomDragListener rdListener) {
        this.ruList = ruList;
        this.rdListener = rdListener;
        System.err.println("FOO - CREATE ADAPTER FOR LIST OF SIZE " + this.ruList.size());
    }

    @NonNull
    @Override
    public RoomHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RoomHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.room_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RoomHolder holder, int position) {
        RoomUser ru = ruList.get(position);
        holder.name.setText(ru.getLastName() + ", " + ru.getFirstName());
        holder.handle.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
                    rdListener.onStartDrag(holder);
                }
                return false;
            }
        });
        System.err.println("FOO - BIND HOLDER FOR POSITION " + position);
    }

    @Override
    public int getItemCount() {
        return ruList.size();
    }

    public void addItems(List<RoomUser> ruList) {
        this.ruList = ruList;
        notifyDataSetChanged();
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        if (fromPosition < toPosition) {
            for (int i = fromPosition; i < toPosition; i++) {
                Collections.swap(ruList, i, i + 1);
            }
        } else {
            for (int i = fromPosition; i > toPosition; i--) {
                Collections.swap(ruList, i, i - 1);
            }
        }
        notifyItemMoved(fromPosition, toPosition);
    }

    @Override
    public void onItemDismiss(int position) {
        ruList.remove(position);
        notifyItemRemoved(position);
    }

    static class RoomHolder extends RecyclerView.ViewHolder {

        private TextView name;
        private ImageView handle;

        RoomHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.text_name);
            handle = (ImageView) view.findViewById(R.id.handle);

        }
    }
}
