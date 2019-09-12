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

public class AdvBlockBase extends Block implements IHasModel
{
	public AdvBlockBase(String name, Material material, SoundType soundType, float hardness, float resistance, String tool, int harvestLevel, float lightLevel, int lightOpacity, CreativeTabs tab)
	{
		super(material);
		setUnlocalizedName(name);
		setRegistryName(name);
		
		setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
		
		ModBlocks.BLOCKS.add(this);
		ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
		
		setSoundType(soundType);
		setHardness(hardness);
		setResistance(resistance);
		setHarvestLevel(tool, harvestLevel);
		setLightLevel(lightLevel);
		setLightOpacity(lightOpacity);
	}

	@Override
	public void registerModels() 
	{
		Main.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");		
	}
}
