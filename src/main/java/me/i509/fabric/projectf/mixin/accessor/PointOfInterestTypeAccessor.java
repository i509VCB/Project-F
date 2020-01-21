package me.i509.fabric.projectf.mixin.accessor;

import java.util.Set;
import java.util.function.Predicate;
import net.minecraft.block.BlockState;
import net.minecraft.world.poi.PointOfInterestType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(PointOfInterestType.class)
public interface PointOfInterestTypeAccessor {
	@Invoker("<init>")
	static PointOfInterestType accessor$create(String id, Set<BlockState> blockStates, int ticketCount, Predicate<PointOfInterestType> completionCondition, int searchDistance) {
		throw new AssertionError("Untransformed Accessor!");
	}

	@Invoker("<init>")
	static PointOfInterestType accessor$create(String id, Set<BlockState> blockStates, int ticketCount, int searchDistance) {
		throw new AssertionError("Untransformed Accessor!");
	}

	@Invoker("setup")
	static PointOfInterestType accessor$setup(PointOfInterestType pointOfInterestType) {
		throw new AssertionError("Untransformed Accessor!");
	}
}
