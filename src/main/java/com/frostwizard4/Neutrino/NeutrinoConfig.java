package com.frostwizard4.Neutrino;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

@Config(name = NeutrinoMain.MOD_ID)
public class NeutrinoConfig implements ConfigData {
    public enum Flags { On, Off }

    @ConfigEntry.Category("Leaf Settings")
    @ConfigEntry.Gui.Tooltip(count = 1)
    @ConfigEntry.Gui.PrefixText()
    public Flags leavesMode = Flags.On;

    public boolean isLeavesOn() {
        return leavesMode == Flags.On;
    }

    public boolean isLeavesOff() {
        return leavesMode == Flags.Off;
    }

}

