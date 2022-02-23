package me.yarinlevi.qtweaks.blocks;

import net.minecraft.block.Block;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;

import java.util.stream.Stream;

/**
 * @author YarinQuapi
 **/
public class Shapes {
    static final VoxelShape GLASS_SHAPE = Stream.of(Block.box(15, 0, 1, 16, 1, 15), Block.box(1, 1, 1, 15, 15, 15), Block.box(0, 0, 0, 16, 1, 1), Block.box(0, 0, 15, 16, 1, 16), Block.box(0, 15, 0, 16, 16, 1), Block.box(0, 15, 15, 16, 16, 16), Block.box(0, 0, 1, 1, 1, 15), Block.box(0, 15, 1, 1, 16, 15), Block.box(15, 15, 1, 16, 16, 15), Block.box(0, 1, 0, 1, 15, 1), Block.box(0, 1, 15, 1, 15, 16), Block.box(15, 1, 15, 16, 15, 16), Block.box(15, 1, 0, 16, 15, 1)).reduce((v1, v2) -> VoxelShapes.join(v1, v2, IBooleanFunction.OR)).get();
}
