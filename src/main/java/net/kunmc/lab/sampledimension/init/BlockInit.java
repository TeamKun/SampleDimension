package net.kunmc.lab.sampledimension.init;

import net.kunmc.lab.sampledimension.SampleDimension;
import net.kunmc.lab.sampledimension.blocks.SamplePortalBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockInit {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, SampleDimension.modid);

    public static final RegistryObject<SamplePortalBlock> SAMPLE_PORTAL = BLOCKS.register("sample_portal",
            () -> new SamplePortalBlock(
                    AbstractBlock.Properties
                    .create(Material.IRON)
                    .sound(SoundType.METAL)
                    .hardnessAndResistance(5f,6f)
                    .harvestTool(ToolType.PICKAXE)
            ));
}
