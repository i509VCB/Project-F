package me.i509.fabric.projectf.registry;

import java.util.Set;
import java.util.function.Predicate;
import com.google.common.collect.ImmutableSet;
import me.i509.fabric.projectf.ProjectF;
import me.i509.fabric.projectf.mixin.accessor.PointOfInterestTypeAccessor;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.poi.PointOfInterestType;

public class PFPointOfInterestTypes {
	public static final PointOfInterestType ALCHEMIST = register("alchemist", getAllStatesOf(Blocks.JUKEBOX), 1, 1);

	private static PointOfInterestType register(String path, Set<BlockState> workStationStates, int ticketCount, int searchDistance) {
		return PointOfInterestTypeAccessor.accessor$setup(Registry.POINT_OF_INTEREST_TYPE.add(ProjectF.id(path), PointOfInterestTypeAccessor.accessor$create(path, workStationStates, ticketCount, searchDistance)));
	}

	private static PointOfInterestType register(String path, Set<BlockState> workStationStates, int ticketCount, Predicate<PointOfInterestType> completionCondition, int searchDistance) {
		return PointOfInterestTypeAccessor.accessor$setup(Registry.POINT_OF_INTEREST_TYPE.add(ProjectF.id(path), PointOfInterestTypeAccessor .accessor$create(path, workStationStates, ticketCount, completionCondition, searchDistance)));
	}

	private static Set<BlockState> getAllStatesOf(Block block) {
		return ImmutableSet.copyOf(block.getStateManager().getStates());
	}

	public static void init() {
		// NO-OP
	}

	// Suppress default constructor to ensure non-instantiability.
	private PFPointOfInterestTypes() {
		throw new AssertionError("You should not be attempting to instantiate this class.");
	}
}
