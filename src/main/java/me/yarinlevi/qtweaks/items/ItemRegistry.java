package me.yarinlevi.qtweaks.items;

import me.yarinlevi.qtweaks.QTweaks;
import net.minecraft.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * @author YarinQuapi
 **/
public class ItemRegistry {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, QTweaks.MODID);

    public static Item.Properties defaultProps() {
        return new Item.Properties().tab(QTweaks.GROUP);
    }

    public static void init(IEventBus evt) {
        ITEMS.register(evt);
    }
}
