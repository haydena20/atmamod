package hayden.atma_mod.blocks;

import hayden.atma_mod.Main;
//import hayden.atma_mod.blocks.tileentities.AccumulatorEntity;
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
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

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
	
//	public TileEntity createNewTileEntity(World worldIn, int meta)
//	{
//		return new AccumulatorEntity();
//		
//	}

	public static void setState(boolean burning, World world, BlockPos pos) 
	{
		
	}
}
