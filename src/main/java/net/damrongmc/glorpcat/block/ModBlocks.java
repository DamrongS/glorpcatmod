package net.damrongmc.glorpcat.block;

import net.damrongmc.glorpcat.GlorpCat;
import net.damrongmc.glorpcat.block.custom.MysteriousFungalTreatBlock;
import net.damrongmc.glorpcat.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.minecraft.world.level.block.Blocks;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(GlorpCat.MODID);

    public static final DeferredBlock<Block> MYSTERIOUS_FUNGAL_TREAT = BLOCKS.register("mysterious_fungal_treat",
            () -> new MysteriousFungalTreatBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WARPED_FUNGUS)));

    private static <T extends  Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }

}
