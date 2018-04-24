package mnbogner.roomapp;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;

import java.util.List;

public class RoomViewModel extends ViewModel {
    private LiveData<RoomUser> ru;
    private LiveData<List<RoomUser>> ruList;
    private RoomRepo rr;

    public RoomViewModel(Context context) {
        this.rr = new RoomRepo(context);
    }

    public void init() {
        ru = rr.getUserInt(1234);
        ruList = rr.getLiveUsers();
    }

    public void update() {

        rr.updateDb();
    }

    public LiveData<RoomUser> getUser() {
        return this.ru;
    }

    public LiveData<List<RoomUser>> getLiveUsers() {
        return this.ruList;
    }
}
