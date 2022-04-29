package net.kunmc.lab.sampledimension.util;

import net.kunmc.lab.sampledimension.blocks.SamplePortalBlock;
import net.kunmc.lab.sampledimension.init.BlockInit;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.fluid.Fluids;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.util.ITeleporter;

import java.util.function.Function;

public class SampleTeleporter implements ITeleporter {
    public static BlockPos thisPos = BlockPos.ZERO;
    public static boolean thisIsToSampleDim = true;

    public SampleTeleporter(BlockPos pos, boolean isToSampleDim) {
        thisPos = pos;
        thisIsToSampleDim = isToSampleDim;
    }

    @Override
    public Entity placeEntity(Entity entity, ServerWorld currentWorld, ServerWorld destWorld, float yaw, Function<Boolean, Entity> repositionEntity) {
        entity = repositionEntity.apply(false);
        double y = 61;
        if (!thisIsToSampleDim) {
            y = thisPos.getY();
        }
        BlockPos destPos = new BlockPos(thisPos.getX(), y, thisPos.getZ());
        int tries = 0;
        while ((destWorld.getBlockState(destPos).getMaterial() != Material.AIR && !destWorld.getBlockState(destPos).isReplaceable(Fluids.WATER)) &&
                (destWorld.getBlockState(destPos.up()).getMaterial() != Material.AIR && !destWorld.getBlockState(destPos.up()).isReplaceable(Fluids.WATER)) &&
                tries < 25) {
            destPos = destPos.up(2);
            tries++;
        }
        entity.setPositionAndUpdate(destPos.getX(), destPos.getY(), destPos.getZ());
        if (thisIsToSampleDim) {
            boolean doSetBlock = true;
            for (BlockPos checkPos : BlockPos.getAllInBoxMutable(destPos.down(10).west(10), destPos.up(10).east(10))) {
                if (destWorld.getBlockState(checkPos).getBlock() instanceof SamplePortalBlock) {
                    doSetBlock = false;
                    break;
                }
            }
            if (doSetBlock) {
                destWorld.setBlockState(destPos, BlockInit.SAMPLE_PORTAL.get().getDefaultState());
            }
        }
        return entity;
    }
}
