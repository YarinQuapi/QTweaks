package me.yarinlevi.qtweaks.data;

import me.yarinlevi.qtweaks.QTweaks;
import me.yarinlevi.qtweaks.blocks.BlockRegistry;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.function.Supplier;

/**
 * @author YarinQuapi
 **/
public class BlockStateGenerator extends BlockStateProvider {
    public BlockStateGenerator(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, QTweaks.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        standardBlock(BlockRegistry.GREENHOUSE_GLASS);
    }

    private void buildCubeAll(Supplier<Block> block) {
        simpleBlock(block.get());
    }


    private void standardBlock(Supplier<Block> block) {
        ResourceLocation name = block.get().getRegistryName();
        ModelFile model = new ModelFile.UncheckedModelFile(modLoc("block/" + name.getPath()));

        simpleBlock(block.get(), model);
        simpleBlockItem(block.get(), model);
    }
}
