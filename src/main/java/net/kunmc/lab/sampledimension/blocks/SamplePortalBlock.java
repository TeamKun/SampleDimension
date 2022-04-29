package net.kunmc.lab.sampledimension.blocks;

import net.kunmc.lab.sampledimension.SampleDimension;
import net.kunmc.lab.sampledimension.util.SampleTeleporter;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class SamplePortalBlock extends Block {
    public SamplePortalBlock(Properties properties) {
        super(properties);
    }

    // ブロックが右クリックされたときに呼ばれるメソッド
    // onBlockActivated はDeplicatedされているが代わりになるものはない
    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity playerEntity, Hand handIn, BlockRayTraceResult hit){
        // 論理サーバかクライアントかチェック、サーバのみで実行
        if(!worldIn.isRemote()) { //See: https://qiita.com/Yz_4230/items/a221ed0536ae3dcdb354, https://mcforge.readthedocs.io/en/latest/concepts/sides/
            if (!playerEntity.isCrouching()){
                MinecraftServer server = worldIn.getServer();
                if (server != null) {
                    if (worldIn.getDimensionKey() == SampleDimension.SAMPLE_DIMENSION) {
                        ServerWorld overworld = server.getWorld(World.OVERWORLD);
                        if (overworld != null) {
                            playerEntity.changeDimension(overworld, new SampleTeleporter(pos, false));
                        }
                    } else {
                        ServerWorld sampleWorld = server.getWorld(SampleDimension.SAMPLE_DIMENSION);
                        if (sampleWorld != null) {
                            playerEntity.changeDimension(sampleWorld, new SampleTeleporter(pos, true));
                        }
                    }
                    return ActionResultType.SUCCESS;
                }
            }
        }
        // ActionResultType.PASSが返るだけ
        return super.onBlockActivated(state, worldIn, pos, playerEntity, handIn, hit);
    }
}
