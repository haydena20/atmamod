package hayden.atma_mod.blocks.tileentities;

import javax.annotation.Nullable;

import hayden.atma_mod.blocks.AdvBlockBase;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public abstract class BlockTileEntity<TE extends TileEntity> extends AdvBlockBase
{

	public BlockTileEntity(String name, Material material, SoundType soundType, float hardness, float resistance,
			String tool, int harvestLevel, CreativeTabs tab) 
	{
		super(name, material, soundType, hardness, resistance, tool, harvestLevel, tab);
	}
	
	public abstract Class<TE> getTileEntityClass();
	
	public TE getTileEntity(IBlockAccess world, BlockPos pos) 
	{
		return (TE)world.getTileEntity(pos);
	}
	
	@Override
	public boolean hasTileEntity(IBlockState state) 
	{
		return true;
	}
	
	@Nullable
	@Override
	public abstract TE createTileEntity(World world, IBlockState state);
	
}
