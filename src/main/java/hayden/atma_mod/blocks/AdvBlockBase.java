package hayden.atma_mod.blocks;

import java.util.Random;

import hayden.atma_mod.Main;
import hayden.atma_mod.init.ModBlocks;
import hayden.atma_mod.init.ModItems;
import hayden.atma_mod.utils.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class AdvBlockBase extends Block implements IHasModel
{
	private Item todrop = null;
	private int minDrop;
	private int maxDrop;
	
	//drops item
	public AdvBlockBase(String name, Material material, SoundType soundType, float hardness, float resistance, String tool, int harvestLevel, float lightLevel, int lightOpacity, CreativeTabs tab, Item toDrop, int minDrop, int maxDrop)
	{
		super(material);
		setUnlocalizedName(name);
		setRegistryName(name);
		
		setCreativeTab(Main.creativeTab);
		
		ModBlocks.BLOCKS.add(this);
		ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
		
		setSoundType(soundType);
		setHardness(hardness);
		setResistance(resistance);
		setHarvestLevel(tool, harvestLevel);
		setLightLevel(lightLevel);
		setLightOpacity(lightOpacity);
		this.todrop = toDrop;
		this.minDrop = minDrop;
		this.maxDrop = maxDrop;
	}
	
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

	//drops item
	public AdvBlockBase(String name, Material material, SoundType soundType, float hardness, float resistance, String tool, int harvestLevel, CreativeTabs tab, Item toDrop, int minDrop, int maxDrop)
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
		this.todrop = toDrop;
		this.minDrop = minDrop;
		this.maxDrop = maxDrop;
	}
	
	public AdvBlockBase(String name, Material material, SoundType soundType, float hardness, float resistance, String tool, int harvestLevel, CreativeTabs tab)
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
	}
	
	@Override
	public Item getItemDropped(IBlockState state, Random object, int i)
	{
		return this.todrop==null?Item.getItemFromBlock(this):this.todrop;
	}
	
	@Override
	public int quantityDropped(Random random)
	{
	    if(this.minDrop>this.maxDrop) {
	        int i = this.minDrop;
	        this.minDrop=this.maxDrop;
	        this.minDrop=i;
	    }
	    return this.minDrop + random.nextInt(this.maxDrop - this.minDrop + 1);
	}
	
	@Override
	public void registerModels() 
	{
		Main.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");		
	}
	
}
