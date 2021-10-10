package com.frostwizard4.Neutrino.misc;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

@Config(name = "neutrino")
public class NeutrinoConfig implements ConfigData {
    public static String[] attackExcludedEntities;
    public NeutrinoConfig() {
        this.setDefault();
    }
    private void setDefault() {
        attackExcludedEntities = new String[] {"minecraft:slime", "tconstruct:blueslime", "thaumcraft:thaumslime"};
    }

    public enum Flags { On, Off }
    public enum IFlags { Off, Default, Half}

    @ConfigEntry.Category("Settings")
    @ConfigEntry.Gui.Tooltip(count = 1)
    @ConfigEntry.Gui.PrefixText()

    public Flags leavesMode = Flags.On;
    public IFlags iFrames = IFlags.Default;

    public boolean isLeavesOn() {
        return leavesMode == Flags.On;
    }

    public boolean isLeavesOff() {
        return leavesMode == Flags.Off;
    }

    public boolean isIFramesDefault() { return iFrames == IFlags.Default; }
    public boolean isIFramesOff() { return iFrames == IFlags.Off; }
    public boolean isIFramesHalf() { return iFrames == IFlags.Half; }

}

