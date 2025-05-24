package net.damrongmc.glorpcat.block.custom;

import net.damrongmc.glorpcat.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FungusBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.tags.BiomeTags;
import net.minecraft.core.Holder;

public class MysteriousFungalTreatBlock extends Block {
    public MysteriousFungalTreatBlock(Properties properties) {
        super(properties);
    }

    @Override
    public ItemStack getCloneItemStack(LevelReader level, BlockPos pos, BlockState state) {
        return new ItemStack(ModItems.MYSTERIOUS_FUNGAL_TREAT.get());
    }

    protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
        if (state.is(Blocks.DIRT)) {
            if (level instanceof LevelReader levelReader) {
                Holder<Biome> biomeHolder = levelReader.getBiome(pos);
                return biomeHolder.is(BiomeTags.HAS_SWAMP_HUT); // or your desired swamp tag
            }
        }
        return false;
    }
}