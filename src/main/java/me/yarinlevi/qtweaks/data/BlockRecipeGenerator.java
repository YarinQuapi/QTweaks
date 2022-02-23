package me.yarinlevi.qtweaks.data;

import me.yarinlevi.qtweaks.blocks.BlockRegistry;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.item.Items;
import net.minecraftforge.common.Tags;

import java.util.function.Consumer;

/**
 * @author YarinQuapi
 **/
public class BlockRecipeGenerator extends RecipeProvider {

    public BlockRecipeGenerator(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    protected void buildShapelessRecipes(Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(BlockRegistry.GREENHOUSE_GLASS.get(), 4)
                .pattern("XGX")
                .pattern("GSG")
                .pattern("XGX")
                .define('X', Items.IRON_INGOT)
                .define('G', Items.GLASS)
                .define('S', Items.NETHER_STAR)
                .unlockedBy("has_nether_star", has(Tags.Items.NETHER_STARS))
                .save(consumer);
    }
}
