package nl.minecraft.location;

import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("location")
public class Location {
    public static final LocationUI gui = new LocationUI(Minecraft.getInstance());

    private static final Logger LOGGER = LogManager.getLogger();

    public Location() {
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(new LocationUI(Minecraft.getInstance()));
    }
}
