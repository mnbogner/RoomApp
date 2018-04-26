package mnbogner.roomapp;

public interface RoomItemTouchAdapter {

    // fromPosition The start position of the moved item.
    // toPosition   Then resolved position of the moved item.
    void onItemMove(int fromPosition, int toPosition);

    // position The position of the item dismissed.
    void onItemDismiss(int position);

}
