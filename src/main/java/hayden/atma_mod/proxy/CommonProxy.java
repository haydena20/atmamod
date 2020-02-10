package hayden.atma_mod.proxy;

import hayden.atma_mod.Main;
import hayden.atma_mod.blocks.SolarAccumulator;
import hayden.atma_mod.blocks.tileentities.TileEntityAccumulator;
import hayden.atma_mod.init.ModBlocks;
//import hayden.atma_mod.capabilities.PlayerAtma.MessageUpdateClientAtma;
import hayden.atma_mod.items.ItemBase;
import hayden.atma_mod.utils.Reference;
import hayden.atma_mod.utils.handlers.PacketHandler;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;


public class CommonProxy 
{
	
	public static final SimpleNetworkWrapper network = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.MODID);
	
	public void registerItemRenderer(Item item, int meta, String id) 
	{
		
	}
	
	public String localize(String unlocalized, Object... args) 
	{
		return I18n.translateToLocalFormatted(unlocalized, args);
	}
	
	private void registerNetworkPackets() 
	{
		PacketHandler.init();
	}
	
	 @SubscribeEvent
	    public static void registerBlocks(RegistryEvent.Register<Block> event) 
	 {
	        event.getRegistry().register(new SolarAccumulator());
	        GameRegistry.registerTileEntity(TileEntityAccumulator.class, Reference.MODID + "_accumulatorblock");
	    }

	    @SubscribeEvent
	    public static void registerItems(RegistryEvent.Register<Item> event) 
	    {
	        event.getRegistry().register(new ItemBlock(ModBlocks.accumulator).setRegistryName(ModBlocks.accumulator.getRegistryName()));
	    }
	
	public void registerRenderers() {}
}
