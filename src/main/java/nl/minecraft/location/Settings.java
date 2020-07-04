package nl.minecraft.location;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;

import java.awt.event.KeyEvent;

public class Settings {

    public static KeyBinding toggleShowBlockInfo =
            new KeyBinding("key.toggle.block",
                    KeyEvent.VK_OPEN_BRACKET,
                    "key.categories.location");

    public static KeyBinding toggleShowTerrain =
            new KeyBinding("key.toggle.terrain",
                    KeyEvent.VK_CLOSE_BRACKET,
                    "key.categories.location");

    public static KeyBinding toggleShowLocation =
            new KeyBinding("key.toggle.location",
                    KeyEvent.VK_EQUALS,
                    "key.categories.location");

    public static boolean showBlockInfo = true;
    public static boolean showTerrain = true;
    public static boolean showLocation = true;

    static {
        ClientRegistry.registerKeyBinding(toggleShowBlockInfo);
        ClientRegistry.registerKeyBinding(toggleShowTerrain);
        ClientRegistry.registerKeyBinding(toggleShowLocation);
    }

    @SubscribeEvent
    public void onTickEvent(TickEvent.ClientTickEvent event) {
        if (toggleShowBlockInfo.isPressed()) {
            showBlockInfo = !showBlockInfo;
        }
        if (toggleShowTerrain.isPressed()) {
            showTerrain = !showTerrain;
        }
        if (toggleShowLocation.isPressed()) {
            showLocation = !showLocation;
        }
    }
}
