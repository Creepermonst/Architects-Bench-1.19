package net.colorizedblock.architectstable.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import org.jetbrains.annotations.Nullable;

import java.util.stream.Stream;

public class ArchitectTableBlock extends HorizontalFacingBlock {
    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;
    public ArchitectTableBlock(Settings settings) {
        super(settings);
    }

    private static final VoxelShape SHAPE_N = Stream.of(
            Block.createCuboidShape(0, 19, -16, 16, 21, 16),
            Block.createCuboidShape(1, 0, 12, 3, 19, 14),
            Block.createCuboidShape(1, 0, -14, 3, 19, -12),
            Block.createCuboidShape(13, 0, 12, 15, 19, 14),
            Block.createCuboidShape(13, 0, -14, 15, 19, -12),
            Block.createCuboidShape(4, 21, -12, 5, 22, -8),
            Block.createCuboidShape(2, 21, -8, 5, 22, -7),
            Block.createCuboidShape(3, 21, -7, 5, 22, -6),
            Block.createCuboidShape(4, 21, 10, 5, 22, 14),
            Block.createCuboidShape(3, 21, 14, 6, 22, 16)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();



    private static final VoxelShape SHAPE_W = Stream.of(
            Block.createCuboidShape(-16, 19, 0, 16, 21, 16),
            Block.createCuboidShape(12, 0, 13, 14, 19, 15),
            Block.createCuboidShape(-14, 0, 13, -12, 19, 15),
            Block.createCuboidShape(12, 0, 1, 14, 19, 3),
            Block.createCuboidShape(-14, 0, 1, -12, 19, 3),
            Block.createCuboidShape(-8.5, 21, 12.5, -4.5, 22, 13.5),
            Block.createCuboidShape(-4.5, 21, 12.5, -3.5, 22, 15.5),
            Block.createCuboidShape(-3.5, 21, 12.5, -2.5, 22, 14.5),
            Block.createCuboidShape(7.5, 21, 8, 11.5, 22, 9),
            Block.createCuboidShape(11.5, 21, 7, 13.5, 22, 10)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();



    private static final VoxelShape SHAPE_S =Stream.of(
            Block.createCuboidShape(0, 19, 0, 16, 21, 32),
            Block.createCuboidShape(13, 0, 2, 15, 19, 4),
            Block.createCuboidShape(13, 0, 28, 15, 19, 30),
            Block.createCuboidShape(1, 0, 2, 3, 19, 4),
            Block.createCuboidShape(1, 0, 28, 3, 19, 30),
            Block.createCuboidShape(12.5, 21, 20.5, 13.5, 22, 24.5),
            Block.createCuboidShape(12.5, 21, 19.5, 15.5, 22, 20.5),
            Block.createCuboidShape(12.5, 21, 18.5, 14., 22, 19.5),
            Block.createCuboidShape(8, 21, 4.5, 9, 22, 8.5),
            Block.createCuboidShape(7, 21, 2.5, 10, 22, 4.5)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();



    private static final VoxelShape SHAPE_E = Stream.of(
            Block.createCuboidShape(0, 19, 0, 32, 21, 16),
            Block.createCuboidShape(2, 0, 1, 4, 19, 3),
            Block.createCuboidShape(28, 0, 1, 30, 19, 3),
            Block.createCuboidShape(2, 0, 13, 4, 19, 15),
            Block.createCuboidShape(28, 0, 13, 30, 19, 15),
            Block.createCuboidShape(20.5, 21, 2.5, 24.5, 22, 3),
            Block.createCuboidShape(19.5, 21, 0.5, 20.5, 22, 3),
            Block.createCuboidShape(18.5, 21, 1.5, 19.5, 22, 3),
            Block.createCuboidShape(4.5, 21, 7, 8.5, 22, 8),
            Block.createCuboidShape(2.5, 21, 6, 4.5, 22, 9)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();



    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        switch (state.get(FACING)){
            case NORTH:
                return SHAPE_N;
            case SOUTH:
                return SHAPE_S;
            case EAST:
                return SHAPE_E;
            case WEST:
                return SHAPE_W;
            default:
                return SHAPE_N;
        }
    }


    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getPlayerFacing().getOpposite());
    }

    @Override
    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.rotate(mirror.getRotation(state.get(FACING)));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }
}
