package mnbogner.roomapp;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.annotation.NonNull;

import java.util.List;
import java.util.Set;
import java.util.concurrent.Executor;

public class RoomRepo {

    private RoomDb rdb;

    private Boolean toggle = true;

    private Executor ex;

    public RoomRepo(Context context) {

        rdb = Room.databaseBuilder(context, RoomDb.class, "roomdb").build();

        String[] foo = {"roomusers"};

        rdb.getInvalidationTracker().addObserver(new InvalidationTracker.Observer(foo) {
                                                     @Override
                                                     public void onInvalidated(@NonNull Set<String> set) {

                                                         System.err.println("FOO - TABLE INVALIDATED!");
                                                     }
                                                 }
        );

        ex = new Executor() {
            @Override
            public void execute(@NonNull Runnable command) {
                new Thread(command).start();
            }
        };

        populateDb();
    }

    public void populateDb() {

        ex.execute(new Runnable() {
            @Override
            public void run() {
                RoomUser ru1 = new RoomUser();
                ru1.setId(123);
                ru1.setFirstName("Matthew");
                ru1.setLastName("Bogner");

                rdb.ruDao().insert(ru1);

                RoomUser ru2 = new RoomUser();
                ru2.setId(456);
                ru2.setFirstName("Josh");
                ru2.setLastName("Steiner");

                rdb.ruDao().insert(ru2);

                RoomUser ru3 = new RoomUser();
                ru3.setId(789);
                ru3.setFirstName("John");
                ru3.setLastName("Doe");

                rdb.ruDao().insert(ru3);

                System.err.println("FOO - VALUES INSERTED?");            }
        });
    }

    public void updateDb() {

        ex.execute(new Runnable() {
            @Override
            public void run() {
                RoomUser ru = new RoomUser();
                ru.setId(1234);

                if (toggle) {
                    ru.setFirstName("Matthew");
                    ru.setLastName("N. Bogner");
                } else {
                    ru.setFirstName("mnbogner");
                    ru.setLastName("");
                }

                rdb.ruDao().insert(ru);

                System.err.println("FOO - VALUE UPDATED?");

                toggle = !toggle;
            }
        });
    }

    public LiveData<RoomUser> getUserStr(String userId) {
        return rdb.ruDao().getUserStr(userId);
    }

    public LiveData<RoomUser> getUserInt(int userId) {
        return rdb.ruDao().getUserInt(userId);
    }

    public LiveData<List<RoomUser>> getLiveUsers() {
        return rdb.ruDao().getLiveUsers();
    }
}
