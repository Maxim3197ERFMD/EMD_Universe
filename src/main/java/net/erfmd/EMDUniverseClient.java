package net.erfmd;

import net.erfmd.block.EMDBlocks;
import net.erfmd.client.RailgunBeamRenderer;
import net.erfmd.network.RailgunBeamPayload;
import net.erfmd.util.EMDModelPredicates;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.render.RenderLayer;

public class EMDUniverseClient implements ClientModInitializer {
    /**
     * Клиетский регистратор
     */
    @Override
    public void onInitializeClient() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.world != null) {
                EMDUniverse.setGameRules(client.world.getGameRules());
            }
        });

        BlockRenderLayerMap.INSTANCE.putBlock(EMDBlocks.IRON_BAR_DOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(EMDBlocks.IRON_BAR_TRAPDOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(EMDBlocks.IRON_BAR_SLAB, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(EMDBlocks.IRON_BAR_STAIRS, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(EMDBlocks.IRON_BAR_BLOCK, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(EMDBlocks.IRON_BAR_WALL, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(EMDBlocks.HONEYSUCKLE_BERRY_BUSH, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(EMDBlocks.PARSLEY_CROP, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(EMDBlocks.GOLDEN_FLOWER_CROP, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(EMDBlocks.GOLDEN_FLOWER, RenderLayer.getCutout());

        EMDModelPredicates.registerModelPredicates();

        RailgunBeamRenderer.register();

        ClientPlayNetworking.registerGlobalReceiver(RailgunBeamPayload.ID, (payload, context) -> {
            context.client().execute(() -> {
                RailgunBeamRenderer.update(payload);
            });
        });
    }
}
