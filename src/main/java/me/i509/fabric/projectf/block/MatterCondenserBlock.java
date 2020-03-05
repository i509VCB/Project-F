package me.i509.fabric.projectf.block;

import me.i509.fabric.projectf.api.util.CallBlockStateInstead;
import me.i509.fabric.projectf.block.entity.MatterCondenserBlockEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockPlacementEnvironment;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.Waterloggable;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.container.Container;
import net.minecraft.entity.EntityContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class MatterCondenserBlock extends Block implements BlockEntityProvider, Waterloggable {
	private static final VoxelShape SHAPE = Block.createCuboidShape(1.0D, 0.0D, 1.0D, 15.0D, 14.0D, 15.0D);
	private static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;
	private static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;

	public MatterCondenserBlock(Settings settings) {
		super(settings);
		this.setDefaultState(this.getDefaultState().with(FACING, Direction.NORTH).with(WATERLOGGED, false));
	}

	@Override
	@CallBlockStateInstead
	public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, EntityContext context) {
		return MatterCondenserBlock.SHAPE;
	}

	@Override
	@CallBlockStateInstead
	public boolean canPlaceAtSide(BlockState world, BlockView view, BlockPos pos, BlockPlacementEnvironment env) {
		return false;
	}

	@Override
	@CallBlockStateInstead
	public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
		if (world.isClient()) {
			return super.onUse(state, world, pos, player, hand, hit);
		}

		BlockPos above = pos.up();
		if (world.getBlockState(above).isSimpleFullBlock(world, pos)) {
			return ActionResult.FAIL;
		}

		BlockEntity blockEntity = world.getBlockEntity(pos);

		if (blockEntity instanceof MatterCondenserBlockEntity) {
			MatterCondenserBlockEntity matterCondenserBlockEntity = (MatterCondenserBlockEntity) blockEntity;
			if (matterCondenserBlockEntity.checkUnlocked(player)) {
				matterCondenserBlockEntity.openContainer(pos, player, matterCondenserBlockEntity.getName());
			}
		}

		return ActionResult.PASS;
	}

	@Override
	@CallBlockStateInstead
	public BlockRenderType getRenderType(BlockState state) {
		return BlockRenderType.ENTITYBLOCK_ANIMATED;
	}

	@Override
	@CallBlockStateInstead
	public BlockState rotate(BlockState state, BlockRotation rotation) {
		return state.with(FACING, rotation.rotate(state.get(FACING)));
	}

	@Override
	@CallBlockStateInstead
	public BlockState mirror(BlockState state, BlockMirror mirror) {
		return state.rotate(mirror.getRotation(state.get(FACING)));
	}

	@Override
	@CallBlockStateInstead
	public FluidState getFluidState(BlockState state) {
		return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
	}

	@Override
	@CallBlockStateInstead
	public boolean hasComparatorOutput(BlockState state) {
		return true;
	}

	@Override
	@CallBlockStateInstead
	public int getComparatorOutput(BlockState state, World world, BlockPos pos) {
		if (world.getBlockEntity(pos) instanceof MatterCondenserBlockEntity) {
			return Container.calculateComparatorOutput(((MatterCondenserBlockEntity) world.getBlockEntity(pos)).getInventory());
		}

		return 0;
	}

	@Override
	public BlockState getPlacementState(ItemPlacementContext ctx) {
		Direction direction = ctx.getPlayerFacing().getOpposite();
		FluidState fluidState = ctx.getWorld().getFluidState(ctx.getBlockPos());

		return this.getDefaultState().with(FACING, direction).with(WATERLOGGED, fluidState.getFluid() == Fluids.WATER);
	}

	@Override
	protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
		super.appendProperties(builder);
		builder.add(FACING);
		builder.add(WATERLOGGED);
	}

	@Override
	public BlockEntity createBlockEntity(BlockView view) {
		return new MatterCondenserBlockEntity();
	}
}
