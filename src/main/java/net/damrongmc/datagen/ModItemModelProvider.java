package net.damrongmc.datagen;

import net.damrongmc.glorpcat.GlorpCat;
import net.damrongmc.glorpcat.item.ModItems;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, GlorpCat.MODID, existingFileHelper);
    }
    @Override
    protected void registerModels() {
        withExistingParent("mysterious_fungal_treat", modLoc("block/mysterious_fungal_treat"));
    }

}