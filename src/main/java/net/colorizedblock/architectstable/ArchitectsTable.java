package net.colorizedblock.architectstable;

import net.colorizedblock.architectstable.block.ModBlocks;
import net.colorizedblock.architectstable.item.ModItemGroup;
import net.colorizedblock.architectstable.item.ModItems;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ArchitectsTable implements ModInitializer {
	public static final String MOD_ID = "architectstable";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItemGroup.registerItemGroups();
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();


	}
}
