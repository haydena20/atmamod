package hayden.atma_mod.blocks;

import java.util.Random;

import javax.annotation.Nullable;

import hayden.atma_mod.Main;
import hayden.atma_mod.blocks.tileentities.BlockTileEntity;
import hayden.atma_mod.blocks.tileentities.TileEntityAccumulator;
import hayden.atma_mod.items.AtmaCrystal;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.ParticleFlame;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class SolarAccumulator extends BlockTileEntity<TileEntityAccumulator>
{
	public SolarAccumulator()
	{
		super("solar_accumulator", Material.ROCK, SoundType.STONE, 2F, 3F, "pickaxe", 1, CreativeTabs.DECORATIONS);
	}

	//https://mcforge.readthedocs.io/en/latest/tileentities/tileentity/
	
	public Class<TileEntityAccumulator> getTileEntityClass() 
	{
		return TileEntityAccumulator.class;
	}
	
	@Nullable
	@Override
	public TileEntityAccumulator createTileEntity(World world, IBlockState state) 
	{
		return new TileEntityAccumulator();
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
			TileEntityAccumulator tile = getTileEntity(world, pos);
			IItemHandler itemHandler = tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, side);
			if (!player.isSneaking()) 
			{
				if (player.getHeldItem(hand).isEmpty()) 
				{
					player.setHeldItem(hand, itemHandler.extractItem(0, 64, false));
				} else 
				{
					if(player.getHeldItemMainhand().getItem() instanceof AtmaCrystal)
						player.setHeldItem(hand, itemHandler.insertItem(0, player.getHeldItem(hand), false));
				}
				tile.markDirty();
			} else
			{
				ItemStack stack = itemHandler.getStackInSlot(0);
				if (!stack.isEmpty()) 
				{
					String localized = Main.proxy.localize(stack.getUnlocalizedName() + ".name");
					player.sendMessage(new TextComponentString(/*stack.getCount() + "x " + */ localized + ": " + (stack.getMaxDamage() - stack.getItemDamage()) + "/" + stack.getMaxDamage()));
				} else 
				{
					player.sendMessage(new TextComponentString("Empty"));
				}
				tile.markDirty();
			}
		}
		return true;
	}

	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState state) 
	{
		TileEntityAccumulator tile = getTileEntity(world, pos);
		IItemHandler itemHandler = tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.NORTH);
		ItemStack stack = itemHandler.getStackInSlot(0);
		if (!stack.isEmpty()) 
		{
			EntityItem item = new EntityItem(world, pos.getX(), pos.getY(), pos.getZ(), stack);
			world.spawnEntity(item);
			world.spawnEntity(new EntityItem(world, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(this)));
		} else world.spawnEntity(new EntityItem(world, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(this)));
		super.breakBlock(world, pos, state);
	}
	
	@SideOnly(Side.CLIENT)
	public static void randomDisplayTick(World world, int x, int y, int z, Random random)
	{		float fx = (float)x + .5F;
		float fy = (float)y + 0.9F;
		float fz = (float)z + .5F;
		
		float fx1 = random.nextFloat() * 0.6F - 0.3F;
		float fz1 = random.nextFloat() * -0.6F - -0.3F;
		
		Minecraft.getMinecraft().world.spawnParticle(EnumParticleTypes.FLAME, (double)(fx + fx1), (double)fy, (double)(fz + fz1), 0.0D, 0.01D, 0.0D);

	}
	
	public static void setState(boolean burning, World world, BlockPos pos) 
	{
		
	}
	
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
}
