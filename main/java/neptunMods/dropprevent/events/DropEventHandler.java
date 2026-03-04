package neptunMods.dropprevent.events;

import neptunMods.dropprevent.config.ModConfig;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.screen.v1.ScreenEvents;
import net.fabricmc.fabric.api.client.screen.v1.ScreenKeyboardEvents;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;

public class DropEventHandler {

    public static void register() {
        ClientTickEvents.START_CLIENT_TICK.register(client -> {
            if (client.player == null) return;

            if (client.screen == null && ModConfig.INSTANCE.hotbarProtection) {
                while (client.options.keyDrop.consumeClick()) {
                    if (ModConfig.INSTANCE.showMessage) {
                        client.player.displayClientMessage(
                                Component.literal("You disabled dropping items!").withStyle(ChatFormatting.RED),
                                true
                        );
                    }
                }
            }
        });

        ScreenEvents.BEFORE_INIT.register((client, screen, scaledWidth, scaledHeight) -> {

            ScreenKeyboardEvents.allowKeyPress(screen).register((scr, event) -> {

                if (ModConfig.INSTANCE.inventoryProtection) {

                    if (client.options.keyDrop.matches(event)) {
                        if (client.player != null && ModConfig.INSTANCE.showMessage) {
                            client.player.displayClientMessage(
                                    Component.literal("You disabled dropping items!").withStyle(ChatFormatting.RED),
                                    true
                            );
                        }
                        return false;
                    }
                }
                return true;
            });
        });
    }
}
