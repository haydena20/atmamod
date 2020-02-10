package hayden.atma_mod.proxy;

import hayden.atma_mod.blocks.tileentities.TileEntityAccumulator;
import hayden.atma_mod.blocks.tileentities.render.TESRAccumulator;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class ClientProxy extends CommonProxy
{
	public void registerItemRenderer(Item item, int meta, String id)
	{
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(item.getRegistryName(), id));
	}
	
	public String localize(String unlocalized, Object... args) 
	{
		return I18n.format(unlocalized, args);
	}
	@Override
	public void registerRenderers() 
	{
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityAccumulator.class, new TESRAccumulator());
	}
	
	
}
	