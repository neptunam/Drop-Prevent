package neptunMods.dropprevent.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class ModConfig {
    public boolean hotbarProtection = true;
    public boolean inventoryProtection = false;

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final File FILE = new File(FabricLoader.getInstance().getConfigDir().toFile(), "dropprevent.json");
    public static ModConfig INSTANCE = new ModConfig();

    public static void load() {
        try {
            if (FILE.exists()) {
                INSTANCE = GSON.fromJson(new FileReader(FILE), ModConfig.class);
            }
        } catch (Exception e) {
            System.err.println("Failed to load configuration!");
        }
    }

    public static void save() {
        try (FileWriter writer = new FileWriter(FILE)) {
            GSON.toJson(INSTANCE, writer);
        } catch (Exception e) {
            System.err.println("Nie udało się zapisać konfiguracji DropPrevent!");
        }
    }
}