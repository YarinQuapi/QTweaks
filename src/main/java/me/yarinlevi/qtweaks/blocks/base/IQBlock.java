package me.yarinlevi.qtweaks.blocks.base;

import net.minecraft.item.Item;

public interface IQBlock {
    QBlockItem createBlockItem();

    Item.Properties getItemProperties();
}
