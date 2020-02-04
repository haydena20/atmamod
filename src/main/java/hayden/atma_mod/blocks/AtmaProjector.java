package hayden.atma_mod.blocks;

import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class AtmaProjector extends BlockBase
{
	public static final AxisAlignedBB ATMA_PROJECTOR_AABB = new AxisAlignedBB(0.1875D, 0, 0.1875D, 0.8125D, 0.625D, 0.8125D);
//	public static final PropertyDirection FACING = BlockHorizontal.FACING;
	
	public AtmaProjector(String name, Material material) 
	{
		super(name, material);
//		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
	}
	
//	@Override
//	protected BlockStateContainer createBlockState() {
//		 return new BlockStateContainer(this, new IProperty[] {FACING});
//	}
	
//	@Override
//	public IBlockState getStateFromMeta(int meta) {
//		
//		EnumFacing enumfacing = EnumFacing.getFront(meta);
//
//	        if (enumfacing.getAxis() == EnumFacing.Axis.Y)
//	        {
//	            enumfacing = EnumFacing.NORTH;
//	        }
//	        
//	        return this.getDefaultState().withProperty(FACING, enumfacing);
//	}
//	@Override
//	public int getMetaFromState(IBlockState state) {
//		
//		EnumFacing facing=state.getValue(FACING);
//		
//		int meta=((EnumFacing)state.getValue(FACING)).getIndex();
//		
//		return meta;
//	}
//	
	@Override
	public boolean isOpaqueCube(IBlockState state)
	{
		return false;
	}
	
	@Override
	public boolean isFullCube(IBlockState state)
	{
		return false;
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
	{
		return ATMA_PROJECTOR_AABB;
	}
}
