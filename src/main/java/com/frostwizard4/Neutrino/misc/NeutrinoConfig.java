package com.frostwizard4.Neutrino.misc;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

@Config(name = "neutrino")
public class NeutrinoConfig implements ConfigData {
    public static String[] attackExcludedEntities;
    public static String[] damageSrcWhitelist;

    public NeutrinoConfig() {
        this.setDefault();
    }
    private void setDefault() {
        attackExcludedEntities = new String[] {"minecraft:slime", "tconstruct:blueslime", "thaumcraft:thaumslime"};
        damageSrcWhitelist = new String[] {"inFire", "lava", "cactus", "lightningBolt", "inWall", "hotFloor"};
    }

    public enum Flags { On, Off }
    public enum IFlags { Off, Default, Half}

    @ConfigEntry.Category("Settings")
    @ConfigEntry.Gui.Tooltip(count = 1)
    @ConfigEntry.Gui.PrefixText()

    public Flags leavesMode = Flags.On;
    public IFlags iFrames = IFlags.Default;

    public boolean isIFramesDefault() { return iFrames == IFlags.Default; }
    public boolean isIFramesOff() { return iFrames == IFlags.Off; }
    public boolean isIFramesHalf() { return iFrames == IFlags.Half; }
    @ConfigEntry.Category("Entity_Settings")
    @ConfigEntry.Gui.Tooltip(count = 1)
    @ConfigEntry.Gui.PrefixText()
    public Flags spawnRat = Flags.On;
    @ConfigEntry.Category("Entity_Settings")
    @ConfigEntry.Gui.Tooltip(count = 1)
    public Flags spawnDuck = Flags.On;
    public boolean isRatOn() {
        return spawnRat == Flags.On;
    }
    public boolean isRatOff() {
        return spawnRat == Flags.Off;
    }
    public boolean isDuckOn() {
        return spawnDuck == Flags.On;
    }
    public boolean isDuckOff() {
        return spawnDuck == Flags.Off;
    }
    public boolean isLeavesOn() {
        return leavesMode == Flags.On;
    }
    public boolean isLeavesOff() {
        return leavesMode == Flags.Off;
    }

}

