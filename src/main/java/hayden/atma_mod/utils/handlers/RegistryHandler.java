package hayden.atma_mod.utils.handlers;

import hayden.atma_mod.init.ModBlocks;
import hayden.atma_mod.init.ModItems;
import hayden.atma_mod.utils.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber
public class RegistryHandler 
{
	@SubscribeEvent
	public static void onItemRegister(RegistryEvent.Register<Item> event)
	{
		event.getRegistry().registerAll(ModItems.ITEMS.toArray(new Item[0]));
	}
	
	@SubscribeEvent
	public static void onBlockRegister(RegistryEvent.Register<Block> event)
	{
		event.getRegistry().registerAll(ModBlocks.BLOCKS.toArray(new Block[0]));
	}
	
	@SubscribeEvent
	public static void onModelRegister(ModelRegistryEvent event)
	{
		for(Item item:ModItems.ITEMS)
			if(item instanceof IHasModel)
				((IHasModel)item).registerModels();
		
		for(Block block:ModBlocks.BLOCKS)
			if(block instanceof IHasModel)
				((IHasModel)block).registerModels();
	}
	
//	public static void preIntRegistries()
//	{
//		ModDimensions.registerDimensions();	
//	}
	
//	public static void serverRegistries(FMLServerStartingEvent event)
//	{
//		event.registerServerCommand(new CommandDimensionTeleport());
//	}
}
