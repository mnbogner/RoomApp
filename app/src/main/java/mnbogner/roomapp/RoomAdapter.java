package mnbogner.roomapp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.RoomHolder> implements RoomItemTouchAdapter{

    private List<RoomUser> ruList;

    public RoomAdapter(List<RoomUser> ruList) {
        this.ruList = ruList;
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
        holder.firstName.setText(ru.getFirstName());
        holder.lastName.setText(ru.getLastName());
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
        private TextView firstName;
        private TextView lastName;

        RoomHolder(View view) {
            super(view);
            firstName = (TextView) view.findViewById(R.id.first_name);
            lastName = (TextView) view.findViewById(R.id.last_name);
        }
    }
}
