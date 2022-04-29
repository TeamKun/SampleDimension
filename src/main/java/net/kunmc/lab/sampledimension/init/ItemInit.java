package net.kunmc.lab.sampledimension.init;

import net.kunmc.lab.sampledimension.SampleDimension;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemInit {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, SampleDimension.modid);

    public static final RegistryObject<BlockItem> SAMPLE_PORTAL = ITEMS.register("sample_portal",
            () -> new BlockItem(
                    BlockInit.SAMPLE_PORTAL.get(),
                    new Item.Properties().group(ItemGroup.TOOLS)
            ));
}
