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

    @Override
    public boolean canSurvive(BlockState state, LevelReader worldIn, BlockPos pos) {
        BlockPos below = pos.below();
        BlockState belowState = worldIn.getBlockState(below);
        return belowState.is(Blocks.DIRT)
                || belowState.is(Blocks.COARSE_DIRT)
                || belowState.is(Blocks.PODZOL)
                || belowState.is(Blocks.FARMLAND)
                || belowState.is(Blocks.GRASS_BLOCK);
    }
}