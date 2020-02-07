package hayden.atma_mod.blocks.tileentities;

import javax.annotation.Nullable;

import hayden.atma_mod.Main;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

public class BlockCounter extends BlockTileEntity
{

	public BlockCounter()
	{
		super("block_counter", Material.WOOD, SoundType.WOOD, 0, 0, "axe", 0, Main.creativeTab);
	}
	
	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) 
	{
		if (!world.isRemote) 
		{
			TileEntityCounter tile = (TileEntityCounter) getTileEntity(world, pos);
			if (side == EnumFacing.DOWN) 
			{
				tile.decrementCount();
			} else if (side == EnumFacing.UP) 
			{
				tile.incrementCount();
			}
			player.sendMessage(new TextComponentString("Count: " + tile.getCount()));
		}
		return true;
	}
	
	@Override
	public Class<TileEntityCounter> getTileEntityClass() 
	{
		return TileEntityCounter.class;
	}
	
	@Nullable
	@Override
	public TileEntityCounter createTileEntity(World world, IBlockState state) 
	{
		return new TileEntityCounter();
	}


}
