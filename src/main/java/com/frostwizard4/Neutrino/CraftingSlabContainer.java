package com.frostwizard4.Neutrino;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.CraftingScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;

public class CraftingSlabContainer extends CraftingScreenHandler {

    public CraftingSlabContainer(int i, PlayerInventory inv, ScreenHandlerContext Context) {
        super(i, inv, Context);
    }

    @Override
    public boolean canUse(PlayerEntity i) {
        return true;
    }
}

