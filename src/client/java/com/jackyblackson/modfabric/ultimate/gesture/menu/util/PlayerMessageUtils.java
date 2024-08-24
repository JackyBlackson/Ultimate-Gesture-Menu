package com.jackyblackson.modfabric.ultimate.gesture.menu.util;

import com.jackyblackson.modfabric.ultimate.gesture.menu.UltimateGestureMenu;
import net.minecraft.util.StringHelper;
import org.apache.commons.lang3.StringUtils;

public class PlayerMessageUtils {
    public static boolean sendMessage(String message, boolean addToHistory) {
        message = normalize(message);
        var client = ClientUtils.getClient();
        if(client == null) {
            UltimateGestureMenu.LOGGER.warn("CANNOT SEND MESSAGE BECAUSE CLIENT IS NULL!");
            UltimateGestureMenu.LOGGER.warn("Message: " + message);
            return false;
        }
        if (!message.isEmpty()) {
            if (client.player == null) {
                UltimateGestureMenu.LOGGER.warn("CANNOT SEND MESSAGE BECAUSE CLIENT.PLAYER IS NULL!");
                UltimateGestureMenu.LOGGER.warn("Message: " + message);
                return false;
            }
            if (addToHistory) {
                client.inGameHud.getChatHud().addToMessageHistory(message);
            }

            if (message.startsWith("/")) {
                client.player.networkHandler.sendChatCommand(message.substring(1));
            } else {
                client.player.networkHandler.sendChatMessage(message);
            }

        }
        return true;
    }

    public static String normalize(String chatText) {
        return StringHelper.truncateChat(StringUtils.normalizeSpace(chatText.trim()));
    }
}
