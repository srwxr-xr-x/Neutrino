package com.frostwizard4.Neutrino;

import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class SoundRegister {
    public static final Identifier DAGGER_STAB_ID = new Identifier("neutrino:dagger_stab");
    public static SoundEvent DAGGER_STAB = new SoundEvent(DAGGER_STAB_ID);

    public static final Identifier ENCHANTERS_TOME_ACTIVATE_ID = new Identifier("neutrino:enchanters_tome_activate");
    public static SoundEvent ENCHANTERS_TOME_ACTIVATE = new SoundEvent(ENCHANTERS_TOME_ACTIVATE_ID);

    public static final Identifier HARVESTER_ACTIVATE_ID = new Identifier("neutrino:harvester_activate");
    public static SoundEvent HARVESTER_ACTIVATE = new SoundEvent(HARVESTER_ACTIVATE_ID);

    public static final Identifier EAT_DEATH_CAP_MUSHROOM_ID = new Identifier("neutrino:eat_death_cap_mushroom");
    public static SoundEvent EAT_DEATH_CAP_MUSHROOM = new SoundEvent(EAT_DEATH_CAP_MUSHROOM_ID);

    public static final Identifier LIGHTNING_ROD_ACTIVATE_ID = new Identifier("neutrino:lightning_rod_activate");
    public static SoundEvent LIGHTNING_ROD_ACTIVATE = new SoundEvent(LIGHTNING_ROD_ACTIVATE_ID);

}
