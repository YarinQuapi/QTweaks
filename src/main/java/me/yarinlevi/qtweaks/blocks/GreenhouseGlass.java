package me.yarinlevi.qtweaks.blocks;

import me.yarinlevi.qtweaks.QTweaks;
import me.yarinlevi.qtweaks.blocks.base.QBlock;
import net.minecraft.block.*;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3i;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.apache.commons.lang3.tuple.Triple;

import java.util.Random;

/**
 * @author YarinQuapi
 **/
public class GreenhouseGlass extends QBlock {
    public GreenhouseGlass() {
        super(QTweaks.defaultPickProps(0, 0.5F, 10.0F).noOcclusion().sound(SoundType.GLASS).randomTicks());
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random rand) {
        if (world.isClientSide) {
            return;
        }
        if (world.canSeeSkyFromBelowWater(pos) && world.isDay()) {
            Triple<BlockPos, BlockState, IGrowable> trip = this.firstBlock(world, pos);
            boolean once = false;
            if (trip != null) {
                for (int i = 0; i < 3; i++) {
                    BlockState growState = i == 0
                            ? trip.getMiddle()
                            : world.getBlockState(trip.getLeft());
                    if (growState.getBlock() == trip.getRight() && trip.getRight().isValidBonemealTarget(world, trip.getLeft(), growState, false)) {
                        trip.getRight().performBonemeal(world, rand, trip.getLeft(), growState);
                        once = true;
                    }
                }
            }
            if (once) {
                world.levelEvent(2005, trip.getMiddle().isSolidRender(world, trip.getLeft())
                        ? trip.getLeft().above()
                        : trip.getLeft(), 0);
            }
        }
    }

    public Triple<BlockPos, BlockState, IGrowable> firstBlock(World world, BlockPos glassPos) {
        BlockPos.Mutable mut = new BlockPos(glassPos).mutable();
        while (true) {
            mut.set(mut.getX(), mut.getY() - 1, mut.getZ());
            if (mut.getY() < 0) {
                return null;
            }
            BlockState state = world.getBlockState(mut);
            if (state.isSolidRender(world, mut) || state.getBlock() instanceof IGrowable || state.getBlock() == this) {
                if (state.getBlock() instanceof IGrowable) {
                    return Triple.of(mut.immutable(), state, (IGrowable) state.getBlock());
                } else {
                    return null;
                }
            }
        }
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return Shapes.GLASS_SHAPE;
    }
}
