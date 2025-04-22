package shcm.shsupercm.fabric.citresewn.defaults.config;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public class CITResewnDefaultsConfigScreenFactory {

    public static Screen create(Screen parent) {
        CITResewnDefaultsConfig currentConfig = CITResewnDefaultsConfig.INSTANCE, defaultConfig = new CITResewnDefaultsConfig();

        ConfigBuilder builder = ConfigBuilder.create()
                .setParentScreen(parent)
                .setTitle(Text.translatable("config.citresewn-defaults.title"))
                .setSavingRunnable(currentConfig::write);

        ConfigCategory category = builder.getOrCreateCategory(Text.empty());
        ConfigEntryBuilder entryBuilder = builder.entryBuilder();

        category.addEntry(entryBuilder.startFloatField(Text.translatable("config.citresewn-defaults.type_enchantment_scroll_multiplier.title"), currentConfig.type_enchantment_scroll_multiplier)
                .setTooltip(Text.translatable("config.citresewn-defaults.type_enchantment_scroll_multiplier.tooltip"))
                .setSaveConsumer(newConfig -> currentConfig.type_enchantment_scroll_multiplier = newConfig)
                .setDefaultValue(defaultConfig.type_enchantment_scroll_multiplier)
                .build());

        return builder.build();
    }
}
