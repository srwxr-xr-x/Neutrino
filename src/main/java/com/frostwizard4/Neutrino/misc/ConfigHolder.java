package com.frostwizard4.Neutrino.misc;

import com.google.gson.Gson;
import net.fabricmc.loader.api.FabricLoader;

import java.io.File;
import java.io.Reader;
import java.nio.file.Files;

public class ConfigHolder {
    static final File CONFIG_DIR = new File(String.valueOf(FabricLoader.getInstance().getConfigDir()), "neutrino");
    public static Config config;
    static final Gson GSON = new Gson();

    static {
        ConfigHolder.init();
    }
    public static void init() {
        try {
            final File nConfig = new File(CONFIG_DIR, "neutrino.json");
            GsonConfigWriter.init();

            Reader reader = Files.newBufferedReader(nConfig.toPath());
            config = GSON.fromJson(reader, Config.class);

            reader.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
