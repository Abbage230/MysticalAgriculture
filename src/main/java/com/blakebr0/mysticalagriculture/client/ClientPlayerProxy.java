package com.blakebr0.mysticalagriculture.client;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;

// workaround for https://github.com/BlakeBr0/MysticalAgriculture/issues/722
// prevents loading the Minecraft class in ModTooltips
public final class ClientPlayerProxy {
    public static Player getPlayer() {
        return Minecraft.getInstance().player;
    }
}
