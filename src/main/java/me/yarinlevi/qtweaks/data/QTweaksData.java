package me.yarinlevi.qtweaks.data;

import me.yarinlevi.qtweaks.QTweaks;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

/**
 * @author YarinQuapi
 **/
public class QTweaksData {

    @Mod.EventBusSubscriber(modid = QTweaks.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class generators {
        @SubscribeEvent
        public static void runGenerator(GatherDataEvent event) {
            DataGenerator generator = event.getGenerator();
            ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

            generator.addProvider(new BlockRecipeGenerator(generator));
            generator.addProvider(new BlockStateGenerator(generator, existingFileHelper));
            generator.addProvider(new LootTableGenerator(generator));
        }
    }
}
