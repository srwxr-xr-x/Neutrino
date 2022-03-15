package com.frostwizard4.Neutrino.misc;

import com.google.gson.Gson;
import net.fabricmc.loader.api.FabricLoader;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class GsonConfigWriter {


    public static void init() {
        final File configDir = new File(String.valueOf(FabricLoader.getInstance().getConfigDir()), "neutrino");
        if (!configDir.exists()) {
            configDir.mkdirs();
        }
        final File nConfig = new File(configDir, "neutrino.json");
        if (!nConfig.exists()) {
            try {
                nConfig.createNewFile();
            } catch (IOException e) {
                System.err.println("Can't find neutrino.json");
            }
            writeData(nConfig);
        }
    }
    private static void writeData(File config) {
        Config values = new Config();
        values.duckSpawning = true;
        values.coldBiomeFreezing = true;
        values.slowLeaves = true;
        values.invincibilityFrameMode = InvincibilityFrameMode.ON;
        values.burnInDesert = true;
        values.naturalLeafCollision = true;
        values.duckSpawnRate = 10;
        values.ratSpawning = true;
        values.ratSpawnRate = 5;
        try {
            // create a writer
            Writer writer = new FileWriter(config);

            // convert map to JSON File
            new Gson().toJson(values, writer);

            // close the writer
            writer.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}