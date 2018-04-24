package mnbogner.roomapp;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class RoomActivityFragment extends Fragment implements View.OnClickListener{

    private RoomViewModel rvm;
    private RecyclerView rv;
    private LinearLayoutManager llm;
    protected RoomAdapter ra;

    public RoomActivityFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        rvm = new RoomViewModel(getContext());
        rvm.init();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.room_fragment, container, false);

        rv = (RecyclerView) root.findViewById(R.id.room_recycler_view);
        llm = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(llm);
        ra = new RoomAdapter(new ArrayList<RoomUser>());
        rv.setAdapter(ra);

        final Observer<List<RoomUser>> roomObserver = new Observer<List<RoomUser>>() {
            @Override
            public void onChanged(@Nullable final List<RoomUser> ruList) {
                System.err.println("FOO - LIST CHANGED?");

                if (ruList != null) {
                    ra.addItems(ruList);
                }
            }
        };

        rvm = new RoomViewModel(getContext());

        rvm.init();
        rvm.getLiveUsers().observe(this, roomObserver);

        return root;
    }

    @Override
    public void onClick(View v) {
        rvm.update();
    }
}