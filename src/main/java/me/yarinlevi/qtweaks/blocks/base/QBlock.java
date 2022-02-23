package me.yarinlevi.qtweaks.blocks.base;

import me.yarinlevi.qtweaks.QTweaks;
import net.minecraft.block.GlassBlock;
import net.minecraft.item.Item;

/**
 * @author YarinQuapi
 **/
public class QBlock extends GlassBlock implements IQBlock {
    public QBlock(Properties properties) {
        super(properties);
    }

    @Override
    public QBlockItem createBlockItem() {
        return new QBlockItem(this, this.getItemProperties());
    }

    @Override
    public Item.Properties getItemProperties() {
        return new Item.Properties().tab(QTweaks.GROUP);
    }
}
