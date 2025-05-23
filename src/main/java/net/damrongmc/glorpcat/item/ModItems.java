package net.damrongmc.glorpcat.item;

import net.damrongmc.glorpcat.GlorpCat;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(GlorpCat.MODID);

    //public static final DeferredItem<Item> MYSTERIOUSFUNGALTREAT = ITEMS.register("mysterious_fungal_treat",
            //()-> new Item(new Item.Properties()));

    public static final DeferredItem<Item> GLORPCORE = ITEMS.register("glorp_core",
            ()-> new Item(new Item.Properties()));




    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

}
