package me.i509.fabric.projectf.registry;

import java.util.function.Supplier;
import me.i509.fabric.projectf.ProjectF;
import me.i509.fabric.projectf.block.entity.AlchemicalChestBlockEntity;
import me.i509.fabric.projectf.block.entity.MatterCondenserBlockEntity;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.registry.Registry;

public final class PFBlockEntities {
	public static final BlockEntityType<AlchemicalChestBlockEntity> ALCHEMICAL_CHEST = register("alchemical_chest", AlchemicalChestBlockEntity::new, PFBlocks.ALCHEMICAL_CHEST);

	public static final BlockEntityType<MatterCondenserBlockEntity> MATTER_CONDENSER = register("matter_condenser", MatterCondenserBlockEntity::new, PFBlocks.MATTER_CONDENSER);

	public static <B extends BlockEntity> BlockEntityType<B> register(String path, Supplier<B> supplier, Block... supportedBlocks) {
		return Registry.register(Registry.BLOCK_ENTITY_TYPE, ProjectF.id(path), BlockEntityType.Builder.create(supplier, supportedBlocks).build(null));
	}

	public static void init() {
		// NO-OP
	}

	// Suppress default constructor to ensure non-instantiability.
	private PFBlockEntities() {
		throw new AssertionError("You should not be attempting to instantiate this class.");
	}
}
