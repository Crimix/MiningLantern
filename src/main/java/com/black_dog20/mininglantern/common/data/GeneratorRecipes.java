package com.black_dog20.mininglantern.common.data;

import com.black_dog20.mininglantern.common.items.ModItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.item.Items;
import net.minecraftforge.common.Tags;

import java.util.function.Consumer;

public class GeneratorRecipes extends RecipeProvider {
    public GeneratorRecipes(DataGenerator generator) {
        super(generator);
    }

    @Override
    protected void registerRecipes(Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shapedRecipe(ModItems.MINING_LANTERN.get())
                .key('i', Tags.Items.INGOTS_IRON)
                .key('g', Tags.Items.GLASS_PANES)
                .key('t', Items.TORCH)
                .key('f', Items.FLINT_AND_STEEL)
                .patternLine("iii")
                .patternLine("gtg")
                .patternLine("ifi")
                .addCriterion("has_iron", hasItem(Tags.Items.INGOTS_IRON))
                .build(consumer);
    }
}
