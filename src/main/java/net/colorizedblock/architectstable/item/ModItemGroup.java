package net.colorizedblock.architectstable.item;

import net.colorizedblock.architectstable.ArchitectsTable;
import net.colorizedblock.architectstable.block.ModBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.impl.itemgroup.FabricItemGroupBuilderImpl;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroup {
    public static ItemGroup ARCHITECT_TABLE;

    public static void registerItemGroups() {
        ARCHITECT_TABLE = FabricItemGroup.builder(new Identifier(ArchitectsTable.MOD_ID, "architect_table"))
                .displayName(Text.translatable("itemgroup.architect_table"))
                .icon(() -> new ItemStack(ModBlocks.ARCHITECT_TABLE)).build();
    }
}

