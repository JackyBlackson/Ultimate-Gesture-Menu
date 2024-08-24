package com.jackyblackson.modfabric.ultimate.gesture.menu.event;

import fi.dy.masa.malilib.interfaces.IWorldLoadListener;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.world.ClientWorld;
import org.jetbrains.annotations.Nullable;

public class WorldLoadListener implements IWorldLoadListener
{
    @Override
    public void onWorldLoadPre(@Nullable ClientWorld worldBefore, @Nullable ClientWorld worldAfter, MinecraftClient mc)
    {
        // Quitting to main menu, save the settings before the integrated server gets shut down
        if (worldBefore != null && worldAfter == null)
        {
            // DO SOMETHING
        }
    }

    @Override
    public void onWorldLoadPost(@Nullable ClientWorld worldBefore, @Nullable ClientWorld worldAfter, MinecraftClient mc)
    {
        // Logging in to a world, load the data
        if (worldBefore == null && worldAfter != null)
        {
            // DO SOMETHING
        }

        // Logging out
        if (worldAfter == null)
        {
            // DO SOMETHING
        }
    }
}
