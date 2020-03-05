package me.i509.fabric.projectf.block.entity;

import net.minecraft.entity.player.PlayerEntity;

public interface LockableBlockEntity {
	boolean checkUnlocked(PlayerEntity player);
}
