package com.jackyblackson.modfabric.ultimate.gesture.menu;

import com.jackyblackson.modfabric.ultimate.gesture.menu.bstats.BstatsMetrics;
import fi.dy.masa.malilib.event.InitializationHandler;
import net.fabricmc.api.ClientModInitializer;

public class UltimateGestureMenuClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.
		InitializationHandler.getInstance().registerInitializationHandler(new InitHandler());

		int pluginId = 23175; // <-- Replace with the id of your plugin!
		BstatsMetrics metrics = new BstatsMetrics(Reference.MOD_ID, pluginId, true, true, true);
	}
}