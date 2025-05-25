package net.damrongmc.event;

import net.damrongmc.glorpcat.GlorpCat;
import net.damrongmc.glorpcat.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.BonemealEvent;

@EventBusSubscriber(modid = GlorpCat.MODID)
public class BonemealEventHandler {

    @SubscribeEvent
    public static void onBonemealUse(BonemealEvent event) {
        Level level = event.getLevel();
        BlockPos pos = event.getPos();
        BlockState state = level.getBlockState(pos);

        // Check if it's a grass block
        if (state.is(Blocks.GRASS_BLOCK)) {

            // Check for swamp biome
            if (level instanceof ServerLevel serverLevel && serverLevel.getBiome(pos).is(Biomes.SWAMP)) {

                // Roll chance (e.g., 20% chance)
                if (serverLevel.random.nextFloat() < 0.80f) {

                    // Position above the bonemealed block
                    BlockPos above = pos.above();

                    // Check if air block above
                    if (serverLevel.getBlockState(above).isAir()) {
                        serverLevel.setBlockAndUpdate(above, ModBlocks.MYSTERIOUS_FUNGAL_TREAT.get().defaultBlockState());
                    }
                }
            }
        }
    }
}