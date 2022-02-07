package com.frostwizard4.Neutrino.misc;

import net.fabricmc.loader.api.FabricLoader;

import java.io.*;
import java.util.ArrayList;

public class Config {
    public static ArrayList<String> lines = new ArrayList<>();

    public static void init() {

        final File configDir = new File(String.valueOf(FabricLoader.getInstance().getConfigDir()), "neutrino");
        if (!configDir.exists()){
            configDir.mkdirs();
        }
        final File nConfig = new File(configDir, "neutrino.conf");
        if(!nConfig.exists()) {
            try {
                nConfig.createNewFile();
            } catch (IOException e) {
                System.err.println("Can't find neutrino.conf");
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
                System.err.println("Can't find neutrino.conf");
            }
        }
    }

    private static void writeData(File config) {
        try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(config)))) {
            out.println("## Auto generated Neutrino Config! ##");
            out.println("Slow Leaves: On");
            out.println("Invincibility Frames: On");
            out.println("Rat Spawning: On");
            out.println("Duck Spawning: On");
            out.println("Freeze in Cold Biomes: On");
            out.println("Burn in Deserts: On");
            out.println("Rat Spawn Rate: 5");
            out.println("Duck Spawn Rate: 10");
            out.println("Solid Player Placed Leaves: Off");
        } catch (IOException e) {
            System.err.println("Can't find neutrino.conf");
        }
    }
}
