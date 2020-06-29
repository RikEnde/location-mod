package nl.minecraft.location;

import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;

@Mod("location")
public class Location {

    public Location() {
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(new Settings());
        MinecraftForge.EVENT_BUS.register(new LocationUI(Minecraft.getInstance()));
    }
}
