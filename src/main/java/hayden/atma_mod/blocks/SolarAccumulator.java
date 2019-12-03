package hayden.atma_mod.blocks;

import hayden.atma_mod.Main;
import hayden.atma_mod.init.ModBlocks;
import hayden.atma_mod.init.ModItems;
import hayden.atma_mod.utils.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public class SolarAccumulator extends AdvBlockBase
{
	public SolarAccumulator(String name)
	{
		super(name, Material.ROCK, SoundType.STONE, 2F, 3F, "pickaxe", 1, 0F, 0, CreativeTabs.DECORATIONS);
	}

	//https://mcforge.readthedocs.io/en/latest/tileentities/tileentity/
	
	@Override
	public void registerModels() 
	{
		Main.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");		
	}
}
