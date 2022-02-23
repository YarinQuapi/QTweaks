package me.yarinlevi.qtweaks.data;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import me.yarinlevi.qtweaks.QTweaks;
import me.yarinlevi.qtweaks.blocks.BlockRegistry;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DirectoryCache;
import net.minecraft.data.IDataProvider;
import net.minecraft.data.LootTableProvider;
import net.minecraft.data.loot.BlockLootTables;
import net.minecraft.loot.*;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * @author YarinQuapi
 **/
public class LootTableGenerator extends LootTableProvider implements IDataProvider {
    public LootTableGenerator(DataGenerator generatorIn) {
        super(generatorIn);
    }

    private final List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootParameterSet>> tables = ImmutableList.of(
            Pair.of(LootTableGenerator.BlockTables::new, LootParameterSets.BLOCK)
    );

    @Override
    public List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootParameterSet>> getTables() {
        return tables;
    }

    @Override
    protected void validate(Map<ResourceLocation, LootTable> map, ValidationTracker validationtracker) {
        map.forEach((p_212851_2_, p_212851_3_) -> {
            LootTableManager.validate(validationtracker, p_212851_2_, p_212851_3_);
        });
    }

    public static class BlockTables extends BlockLootTables {
        @Override
        protected void addTables() {
            this.dropSelf(BlockRegistry.GREENHOUSE_GLASS.get());
        }

        @Override
        protected Iterable<Block> getKnownBlocks() {
            return ForgeRegistries.BLOCKS.getValues().stream()
                    .filter(block -> block.getRegistryName().getNamespace().equals(QTweaks.MODID))
                    .collect(Collectors.toList());
        }
    }
}
