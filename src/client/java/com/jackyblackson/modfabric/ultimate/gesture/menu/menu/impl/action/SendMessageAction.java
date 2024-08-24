package com.jackyblackson.modfabric.ultimate.gesture.menu.menu.impl.action;

import com.jackyblackson.modfabric.ultimate.gesture.menu.menu.IGestureMenuItem;
import com.jackyblackson.modfabric.ultimate.gesture.menu.menu.impl.item.BaseMenuItem;
import com.jackyblackson.modfabric.ultimate.gesture.menu.util.PlayerMessageUtils;

public class SendMessageAction extends BaseMenuAction {
    private String message;

    public SendMessageAction(String message) {
        this.message = message;
    }

    @Override
    public String act(IGestureMenuItem item) {
        boolean success = PlayerMessageUtils.sendMessage(this.message, true);
        if(success) {
            return null;
        } else {
            return "Cannot send message!";
        }
    }
}
