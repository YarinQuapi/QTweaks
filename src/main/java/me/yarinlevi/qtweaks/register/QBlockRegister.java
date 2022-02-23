package me.yarinlevi.qtweaks.register;

import me.yarinlevi.qtweaks.blocks.BlockRegistry;
import me.yarinlevi.qtweaks.blocks.base.QBlockItem;
import me.yarinlevi.qtweaks.items.ItemRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author YarinQuapi
 **/
public class QBlockRegister<B extends Block, I extends Item, T extends TileEntity> implements Supplier<Block> {
    private final String name;
    private RegistryObject<B> block;
    private RegistryObject<I> item;
    private RegistryObject<TileEntityType<T>> tileEntityType;

    public QBlockRegister(String name, Supplier<B> blockSupplier, Function<B, I> itemSupplier, Supplier<T> tileSupplier) {
        this.name = name;
        this.block = BlockRegistry.BLOCKS.register(name, blockSupplier);
        this.item = ItemRegistry.ITEMS.register(name, () -> itemSupplier.apply(block.get()));
    }
    public QBlockRegister(String name, Supplier<B> blockSupplier, Function<B, I> itemSupplier) {
        this.name = name;
        this.block = BlockRegistry.BLOCKS.register(name, blockSupplier);
        this.item = ItemRegistry.ITEMS.register(name, () -> itemSupplier.apply(block.get()));
    }

    public String getName() {return name;}

    @Override
    public Block get() {
        return block.get();
    }

    public B getBlock() {
        return block.get();
    }

    public I getItem() {
        return item.get();
    }

    @Nonnull
    public TileEntityType<T> getTileEntityType() { return Objects.requireNonNull(tileEntityType.get());}


    public static class BlockBuilder {
        private final String name;
        private Supplier<Block> blockSupplier;
        private Function<Block, Item> itemSupplier = (b) -> new QBlockItem(b, ItemRegistry.defaultProps());
        private boolean hasTile = false;
        private Supplier<TileEntityType<?>> tileSupplier;

        private BlockBuilder(String nameIn, Supplier<Block> blockSupplierIn) {
            this.name = nameIn;
            this.blockSupplier = blockSupplierIn;
        }

        public static BlockBuilder block(String nameIn, Supplier<Block> blockSupplierIn) {
            return new BlockBuilder(nameIn, blockSupplierIn);
        }

        public BlockBuilder item(Function<Block, Item> itemSupplierIn) {
            itemSupplier = itemSupplierIn;

            return this;
        }

        public BlockBuilder tile(Supplier<TileEntityType<?>> tileSupplierIn) {
            tileSupplier = tileSupplierIn;
            hasTile = true;

            return this;
        }
    }
}
