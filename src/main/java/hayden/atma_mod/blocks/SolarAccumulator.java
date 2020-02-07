package hayden.atma_mod.blocks;

import javax.annotation.Nullable;

import hayden.atma_mod.Main;
import hayden.atma_mod.blocks.tileentities.AccumulatorEntity;
import hayden.atma_mod.blocks.tileentities.BlockTileEntity;
import hayden.atma_mod.utils.Reference;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class SolarAccumulator extends BlockTileEntity<AccumulatorEntity>
{
	public SolarAccumulator(String name)
	{
		super(name, Material.ROCK, SoundType.STONE, 2F, 3F, "pickaxe", 1, CreativeTabs.DECORATIONS);
	}

	//https://mcforge.readthedocs.io/en/latest/tileentities/tileentity/
	
	public Class<AccumulatorEntity> getTileEntityClass() 
	{
		return AccumulatorEntity.class;
	}
	
	@Nullable
	@Override
	public AccumulatorEntity createTileEntity(World world, IBlockState state) 
	{
		return new AccumulatorEntity();
	}
	
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
	
	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) 
	{
		if (!world.isRemote) 
		{
			AccumulatorEntity tile = getTileEntity(world, pos);
			IItemHandler itemHandler = tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, side);
			if (!player.isSneaking()) 
			{
				if (player.getHeldItem(hand).isEmpty()) 
				{
					player.setHeldItem(hand, itemHandler.extractItem(0, 64, false));
				} else 
				{
					player.setHeldItem(hand, itemHandler.insertItem(0, player.getHeldItem(hand), false));
				}
				tile.markDirty();
			} else
			{
				ItemStack stack = itemHandler.getStackInSlot(0);
				if (!stack.isEmpty()) 
				{
					String localized = Main.proxy.localize(stack.getUnlocalizedName() + ".name");
					player.sendMessage(new TextComponentString(stack.getCount() + "x " + localized));
				} else 
				{
					player.sendMessage(new TextComponentString("Empty"));
				}
			}
		}
		return true;
	}

	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState state) 
	{
		AccumulatorEntity tile = getTileEntity(world, pos);
		IItemHandler itemHandler = tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.NORTH);
		ItemStack stack = itemHandler.getStackInSlot(0);
		if (!stack.isEmpty()) 
		{
			EntityItem item = new EntityItem(world, pos.getX(), pos.getY(), pos.getZ(), stack);
			world.spawnEntity(item);
		}
		super.breakBlock(world, pos, state);
	}
	
	public static void setState(boolean burning, World world, BlockPos pos) 
	{
		
	}
}
