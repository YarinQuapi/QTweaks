package me.yarinlevi.qtweaks.blocks.base;

import me.yarinlevi.qtweaks.QTweaks;
import me.yarinlevi.qtweaks.items.ItemBlockBase;
import net.minecraft.block.Block;
import net.minecraft.item.Item;

/**
 * @author YarinQuapi
 **/
public class BaseBlock extends Block {
    public BaseBlock(Properties properties) {
        super(properties);
    }

    protected ItemBlockBase getItemBlock() {
        return new ItemBlockBase(this, new Item.Properties().tab(QTweaks.GROUP));
    }

    public boolean shouldAddCreative() {
        return true;
    }
}