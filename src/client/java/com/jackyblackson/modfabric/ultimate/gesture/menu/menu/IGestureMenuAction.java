package com.jackyblackson.modfabric.ultimate.gesture.menu.menu;

public interface IGestureMenuAction {
    /**
     * Do the selected item's action
     * @return Null, if success; the message to display, otherwise.
     */
    public String act(IGestureMenuItem item);
}
