package net.damrongmc.glorpcat.item;

import net.damrongmc.entity.ModEntities;
import net.damrongmc.glorpcat.GlorpCat;
import net.damrongmc.glorpcat.block.ModBlocks;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(GlorpCat.MODID);

    //public static final DeferredItem<Item> MYSTERIOUSFUNGALTREAT = ITEMS.register("mysterious_fungal_treat",
            //()-> new Item(new Item.Properties()));

    public static final DeferredItem<Item> GLORPCORE = ITEMS.register("glorp_core",
            ()-> new Item(new Item.Properties()));

    public static final DeferredItem<Item> MYSTERIOUS_FUNGAL_TREAT = ITEMS.register("mysterious_fungal_treat",
            () -> new ItemNameBlockItem(ModBlocks.MYSTERIOUS_FUNGAL_TREAT.get(), new Item.Properties()));

    public static final DeferredItem<Item> GLORP_SPAWN_EGG = ITEMS.register("glorp_spawn_egg",
            () -> new DeferredSpawnEggItem(ModEntities.GLORP, 0x31afaf, 0xffac00,
                    new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

}
