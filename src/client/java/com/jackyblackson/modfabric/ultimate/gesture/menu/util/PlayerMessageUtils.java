package com.jackyblackson.modfabric.ultimate.gesture.menu.util;

import com.jackyblackson.modfabric.ultimate.gesture.menu.UltimateGestureMenu;
import com.jackyblackson.modfabric.ultimate.gesture.menu.menu.impl.menu.UserGestureMenu;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.StringHelper;
import org.apache.commons.lang3.StringUtils;

public class PlayerMessageUtils {
    public static boolean sendPublicMessage(String message, boolean addToHistory) {
        message = normalize(message);
        var client = ClientUtils.getClient();
        if(client == null) {
            UltimateGestureMenu.LOGGER.warn("CANNOT SEND PUBLIC MESSAGE BECAUSE CLIENT IS NULL!");
            UltimateGestureMenu.LOGGER.warn("Did you called it in an impropriety time?");
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

    public static void sendPrivateMessage(String message) {

    }

    public static void sendClientMessage(String message) {
        PlayerEntity player = ClientUtils.getClient().player;
        if (player  != null)
            ClientUtils.getClient().player.sendMessage(Text.translatableWithFallback(message, message));
        else {
            UltimateGestureMenu.LOGGER.warn("Cannot send client message because client.player is null!");
            UltimateGestureMenu.LOGGER.warn("Did you called it in an impropriety time?");
            UltimateGestureMenu.LOGGER.warn("The message or translation key: " + message);
        }
    }

    public static String normalize(String chatText) {
        return StringHelper.truncateChat(StringUtils.normalizeSpace(chatText.trim()));
    }
}
