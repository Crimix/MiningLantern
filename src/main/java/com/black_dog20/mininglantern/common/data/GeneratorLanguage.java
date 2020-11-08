package com.black_dog20.mininglantern.common.data;

import com.black_dog20.mininglantern.MiningLantern;
import com.black_dog20.mininglantern.common.items.ModItems;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;

public class GeneratorLanguage extends LanguageProvider {

    public GeneratorLanguage(DataGenerator gen) {
        super(gen, MiningLantern.MOD_ID, "en_us");
    }

    @Override
    protected void addTranslations() {
        add("itemGroup.mininglantern", "Mining Lantern");
        add("keys.mininglantern.category", "Mining Lantern");
        add("keys.mininglantern.light", "Toggle lantern on/off");
        add(ModItems.MINING_LANTERN.get(), "Mining Lantern");
        add("tooltip.mininglantern:activate", "Press %s to toggle the light on/off");
    }
}
