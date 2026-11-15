package net.erfmd.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.erfmd.EMDUniverse;
import net.fabricmc.loader.api.FabricLoader;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Config {
    public static void loadConfig() {
        File configFile = new File(FabricLoader.getInstance().getConfigDir().toFile(), EMDUniverse.MOD_ID + "_config.json");
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        if (configFile.exists()) {
            try {
                FileReader fileReader = new FileReader(configFile);
                EMDUniverse.CONFIG = gson.fromJson(fileReader, ConfigHandler.class);
                fileReader.close();
                saveConfig(); //update config
            } catch (IOException e) {
                EMDUniverse.LOGGER.warn("the config was not loaded: " + e.getLocalizedMessage());
            }
        } else {
            EMDUniverse.CONFIG = new ConfigHandler();
            saveConfig();
        }
    }

    public static void saveConfig() {
        File configFile = new File(FabricLoader.getInstance().getConfigDir().toFile(), EMDUniverse.MOD_ID + "_config.json");
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        if (!configFile.getParentFile().exists()) {
            configFile.getParentFile().mkdir();
        }
        try {
            FileWriter fileWriter = new FileWriter(configFile);
            fileWriter.write(gson.toJson(EMDUniverse.CONFIG));
            fileWriter.close();
        } catch (IOException e) {
            EMDUniverse.LOGGER.warn("the config was not saved: " + e.getLocalizedMessage());
        }
    }
}
