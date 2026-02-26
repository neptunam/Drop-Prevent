package neptunMods.dropprevent;

import neptunMods.dropprevent.config.ModConfig;
import neptunMods.dropprevent.events.DropEventHandler;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class DropPreventClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ModConfig.load();

        DropEventHandler.register();
    }
}