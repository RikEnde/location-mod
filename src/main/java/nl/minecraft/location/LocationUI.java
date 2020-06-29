package nl.minecraft.location;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraftforge.client.event.DrawHighlightEvent;
import net.minecraftforge.client.event.RenderBlockOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.lwjgl.opengl.GL11;

import static java.lang.String.format;
import static nl.minecraft.location.Settings.*;

public class LocationUI extends AbstractGui {
    private final Minecraft mc;
    private static final String[] directions = new String[]{"N", "NE", "E", "SE", "S", "SW", "W", "NW"};

    private BlockState selectedBlock;
    private String overlay;

    public LocationUI(Minecraft mc) {
        this.mc = mc;
    }

    @SubscribeEvent
    @SuppressWarnings("unused")
    public void renderPlayerInfo(RenderGameOverlayEvent event) {
        if (event.isCancelable() || event.getType() != RenderGameOverlayEvent.ElementType.HOTBAR) {
            return;
        }

        GL11.glEnable(GL11.GL_ALPHA_TEST);
        FontRenderer fr = mc.fontRenderer;
        ClientPlayerEntity player = mc.player;
        Entity renderViewEntity = mc.getRenderViewEntity();

        if (renderViewEntity == null || player == null) {
            return;
        }

        BlockPos block = new BlockPos(renderViewEntity);
        Biome biome = getBiome(block);

        String direction = getDirection(player.rotationYaw);
        String location = format("x: %d y: %d z: %d", block.getX(), block.getY(), block.getZ());

        String terrain = getBiomeName(biome);
        if (showBlockInfo && this.selectedBlock != null) {
            String blockDescription = getSelectedBlockName(this.selectedBlock);
            terrain += " " + blockDescription;
            this.selectedBlock = null;
        }

        if (showOverlay && this.overlay != null) {
            terrain += " " + overlay;
            this.overlay = null;
        }

        if (showDirection) {
            fr.drawStringWithShadow(direction, 0f, 0f, 0xffffff);
        }
        if (showLocation) {
            fr.drawStringWithShadow(location, 15f, 0f, 0xffffff);
        }
        if (showTerrain) {
            fr.drawStringWithShadow(terrain, 0f, 10f, 0xffffff);
        }
    }

    @SubscribeEvent
    @SuppressWarnings("unused")
    public void onRenderBlockOverlay(RenderBlockOverlayEvent event) {
        RenderBlockOverlayEvent.OverlayType type = event.getOverlayType();
        this.overlay = type.name().toLowerCase();
    }

    @SubscribeEvent
    @SuppressWarnings("unused")
    public void onDrawHighlight(DrawHighlightEvent event) {
        RayTraceResult target = event.getTarget();
        if (mc.world != null && target.getType() == RayTraceResult.Type.BLOCK) {
            BlockRayTraceResult brr = (BlockRayTraceResult) target;
            BlockPos blockPos = brr.getPos();
            this.selectedBlock = mc.world.getBlockState(blockPos);
        }
    }

    private String getDirection(float degrees) {
        double point = (double) MathHelper.wrapDegrees(degrees) + 180.0D + 22.5D;
        return directions[MathHelper.floor((point % 360) / 45.0)];
    }

    private Biome getBiome(BlockPos blockPos) {
        if (mc.world != null) {
            return mc.world.func_226691_t_(blockPos);
        }
        return Biomes.PLAINS;
    }

    private String getBiomeName(Biome biome) {
        ResourceLocation reg = biome.getRegistryName();
        if (reg != null) {
            return reg.getPath().replaceAll("_", " ");
        }
        return "";
    }

    private String getSelectedBlockName(BlockState blockState) {
        if (blockState != null) {
            Block block = blockState.getBlock();
            ResourceLocation reg = block.getRegistryName();
            if (reg != null) {
                return reg.getPath().replaceAll("_", " ");
            }
        }
        return "";
    }

}
