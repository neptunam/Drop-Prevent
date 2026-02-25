package neptunMods.dropprevent;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;

@Environment(EnvType.CLIENT)
public class DropPreventClient implements ClientModInitializer {

    private boolean isEnabled = true;

    @Override
    public void onInitializeClient() {

        ClientTickEvents.START_CLIENT_TICK.register(this::onStartTick);

        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
            dispatcher.register(ClientCommandManager.literal("dropprevent")
                    .then(ClientCommandManager.literal("on")
                            .executes(context -> {
                                isEnabled = true;
                                context.getSource().sendFeedback(
                                        Component.literal("Hotbar drop protection enabled!")
                                                .withStyle(ChatFormatting.GREEN)
                                );
                                return 1;
                            }))
                    .then(ClientCommandManager.literal("off")
                            .executes(context -> {
                                isEnabled = false;
                                context.getSource().sendFeedback(
                                        Component.literal("Hotbar drop protection disabled!")
                                                .withStyle(ChatFormatting.RED)
                                );
                                return 1;
                            }))
            );
        });
    }

    private void onStartTick(Minecraft client) {
        if (!isEnabled) return;

        if (client.player == null) return;

        if (client.screen == null) {
            while (client.options.keyDrop.consumeClick()) {
            }
        }
    }
}