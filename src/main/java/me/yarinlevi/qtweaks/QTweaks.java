package me.yarinlevi.qtweaks;

import me.yarinlevi.qtweaks.blocks.BlockRegistry;
import me.yarinlevi.qtweaks.items.ItemRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("qtweaks")
public class QTweaks {
    public static final String MODID = "qtweaks";

    public static final ItemGroup GROUP = new ItemGroup(MODID) {
        @OnlyIn(Dist.CLIENT)
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(BlockRegistry.GREENHOUSE_GLASS.getItem());
        }
    };

    public QTweaks() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        eventBus.addListener(this::setup);

        BlockRegistry.init(eventBus);
        ItemRegistry.init(eventBus);

        MinecraftForge.EVENT_BUS.register(this);
    }

    public void setup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            RenderTypeLookup.setRenderLayer(BlockRegistry.GREENHOUSE_GLASS.getBlock(), RenderType.cutoutMipped());
        });
    }

    public static Block.Properties defaultPickProps(int harvestLevel, float hardness, float resistance) {
        return Block.Properties.of(Material.STONE).harvestLevel(harvestLevel).harvestTool(ToolType.PICKAXE).strength(hardness, resistance).sound(SoundType.STONE).noOcclusion();
    }
}
