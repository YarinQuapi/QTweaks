package me.yarinlevi.qtweaks.blocks;

import me.yarinlevi.qtweaks.QTweaks;
import me.yarinlevi.qtweaks.blocks.base.QBlockItem;
import me.yarinlevi.qtweaks.register.QBlockRegister;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * @author YarinQuapi
 **/
public class BlockRegistry {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, QTweaks.MODID);

    private static final Item.Properties defaultBlockItemProperties = new Item.Properties().tab(QTweaks.GROUP).stacksTo(64);
    public static final AbstractBlock.Properties miscBlockProperties = AbstractBlock.Properties.of(Material.STONE).harvestLevel(1).harvestTool(ToolType.PICKAXE).strength(1.5f, 10f);

    public static final QBlockRegister<GreenhouseGlass, QBlockItem, ?> GREENHOUSE_GLASS = new QBlockRegister<>("greenhouse_glass", GreenhouseGlass::new,
            (b) -> new QBlockItem(b, defaultBlockItemProperties));

    public static void init(IEventBus evt) {
        BLOCKS.register(evt);
    }
}
