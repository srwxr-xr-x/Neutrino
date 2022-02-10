package com.frostwizard4.Neutrino.misc;

import net.fabricmc.loader.api.FabricLoader;

import java.io.*;
import java.util.ArrayList;

public class MinecraftDungeonsArtifactsConfig {
    public static ArrayList<String> lines = new ArrayList<>();

    public static void init() {
        final File configDir = new File(String.valueOf(FabricLoader.getInstance().getConfigDir()), "neutrino");
        if (!configDir.exists()){
            configDir.mkdirs();
        }
        final File nConfig = new File(configDir, "neutrino-dungeons.conf");
        if(!nConfig.exists()) {
            try {
                nConfig.createNewFile();
            } catch (IOException e) {
                System.err.println("Can't find neutrino-dungeons.conf");
            }
            writeData(nConfig);
        }

        if(nConfig.exists() && nConfig.isFile() && nConfig.canRead()) {
            try(BufferedReader in = new BufferedReader(new FileReader(nConfig))) {
                String currentLine;
                while((currentLine = in.readLine()) != null) {
                    lines.add(currentLine);
                }
            } catch (IOException e) {
                System.err.println("Can't find neutrino-dungeons.conf");
            }
        }
    }

    private static void writeData(File config) {
        try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(config)))) {
            out.println("## Auto generated Neutrino Dungeons Config! ##");
            out.println("Enchanters Tome: On");
            out.println("Harvester: On");
            out.println("Lightning Rod: On");
            out.println("Updraft Tome: On");
            out.println("Soul Healer: On");
            out.println("Soul Pouch: On");
        } catch (IOException e) {
            System.err.println("Can't find neutrino-dungeons.conf");
        }
    }
}
