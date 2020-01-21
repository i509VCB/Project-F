package me.i509.fabric.projectf.registry;

import com.google.common.collect.ImmutableSet;
import me.i509.fabric.projectf.ProjectF;
import me.i509.fabric.projectf.mixin.accessor.VillagerProfessionAccessor;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.registry.Registry;
import net.minecraft.village.VillagerProfession;
import net.minecraft.world.poi.PointOfInterestType;
import org.checkerframework.checker.nullness.qual.Nullable;

public class PFVillagers {
	public static final VillagerProfession ALCHEMIST_PROFESSION = register("alchemist", PFPointOfInterestTypes.ALCHEMIST, ImmutableSet.of(), ImmutableSet.of(), null);

	public static VillagerProfession register(String path, PointOfInterestType type, ImmutableSet<Item> gatherableItems, ImmutableSet<Block> secondaryJobSites, @Nullable SoundEvent soundEvent) {
		return Registry.register(Registry.VILLAGER_PROFESSION, ProjectF.id(path), VillagerProfessionAccessor.accessor$create(ProjectF.id(path).toString(), type, gatherableItems, secondaryJobSites, soundEvent));
	}

	public static void init() {
		// NO-OP
	}

	// Suppress default constructor to ensure non-instantiability.
	private PFVillagers() {
		throw new AssertionError("You should not be attempting to instantiate this class.");
	}
}
