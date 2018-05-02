package mnbogner.roomapp;

public interface StorylinesTouchAdapter {

    // fromPosition specifies the initial position of the moved item
    // toPosition specifies the final position of the moved item
    void onItemMove(int fromPosition, int toPosition);

    // position specifies the position of the deleted item
    void onItemDismiss(int position);
}
