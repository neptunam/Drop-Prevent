package neptunMods.dropprevent.config;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class ConfigScreenBuilder {

    public static Screen build(Screen parent) {
        ConfigBuilder builder = ConfigBuilder.create()
                .setParentScreen(parent)
                .setTitle(Component.literal("Drop Prevent configuration"));

        ConfigCategory category = builder.getOrCreateCategory(Component.literal("General"));
        ConfigEntryBuilder entryBuilder = builder.entryBuilder();

        category.addEntry(entryBuilder.startBooleanToggle(Component.literal("Hotbar drop protection"), ModConfig.INSTANCE.hotbarProtection)
                .setDefaultValue(true)
                .setSaveConsumer(newValue -> ModConfig.INSTANCE.hotbarProtection = newValue)
                .build());

        category.addEntry(entryBuilder.startBooleanToggle(Component.literal("Inventory drop protection"), ModConfig.INSTANCE.inventoryProtection)
                .setDefaultValue(false)
                .setSaveConsumer(newValue -> ModConfig.INSTANCE.inventoryProtection = newValue)
                .build());

        category.addEntry(entryBuilder.startBooleanToggle(Component.literal("Show actionbar message"), ModConfig.INSTANCE.showMessage)
                .setDefaultValue(true)
                .setSaveConsumer(newValue -> ModConfig.INSTANCE.showMessage = newValue)
                .build());

        builder.setSavingRunnable(ModConfig::save);

        return builder.build();
    }
}
