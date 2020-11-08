package com.black_dog20.mininglantern.common.data;

import com.black_dog20.mininglantern.MiningLantern;
import com.black_dog20.mininglantern.common.items.ModItems;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ExistingFileHelper;
import net.minecraftforge.client.model.generators.ItemModelProvider;

public class GeneratorItemModels extends ItemModelProvider {

    public GeneratorItemModels(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, MiningLantern.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        String path = ModItems.MINING_LANTERN.get().getRegistryName().getPath();
        singleTexture(path, mcLoc("item/generated"), "layer0", modLoc("item/" + path + "_off"));
        withExistingParent(path, mcLoc("item/generated"))
                .texture("layer0", modLoc("item/" + path))
                .override()
                .predicate(modLoc("state"), 0)
                .model(withExistingParent(path + "off", mcLoc("item/generated"))
                        .texture("layer0", modLoc("item/" + path + "_off")));

    }

    @Override
    public String getName() {
        return "Mining Lantern item models";
    }
}
