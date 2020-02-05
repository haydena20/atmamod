package hayden.atma_mod.items;

import org.lwjgl.input.Keyboard;

import baubles.api.BaubleType;
import baubles.api.BaublesApi;
import baubles.api.IBauble;
import baubles.api.cap.IBaublesItemHandler;
import baubles.common.Baubles;
import hayden.atma_mod.Main;
import hayden.atma_mod.capabilities.AtmaProvider;
import hayden.atma_mod.capabilities.CooldownBaubleProvider;
import hayden.atma_mod.capabilities.IAtma;
import hayden.atma_mod.capabilities.ICooldown;
import hayden.atma_mod.utils.handlers.Events;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.client.event.sound.SoundEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class SpeedCharm extends ItemBase implements IBauble
{
	
	
	public SpeedCharm(String name) 
	{
		super(name);
		this.setMaxStackSize(1);
		this.setHasSubtypes(true);
		this.setMaxDamage(1600);
		setCreativeTab(Main.creativeTab);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
	{		
		if(!worldIn.isRemote) { 
			IBaublesItemHandler baubles = BaublesApi.getBaublesHandler(playerIn);
			for(int i = 0; i < baubles.getSlots(); i++) 
				if((baubles.getStackInSlot(i) == null || baubles.getStackInSlot(i).isEmpty()) && baubles.isItemValidForSlot(i, playerIn.getHeldItem(handIn), playerIn)) {
					baubles.setStackInSlot(i, playerIn.getHeldItem(handIn).copy());
					if(!playerIn.capabilities.isCreativeMode){
						playerIn.inventory.setInventorySlotContents(playerIn.inventory.currentItem, ItemStack.EMPTY);
					}
					onEquipped(playerIn.getHeldItem(handIn), playerIn);
					break;
				}
		}
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
	}

	public void onWornTick(ItemStack itemstack, EntityLivingBase player) 
	{
		IAtma atma = player.getCapability(AtmaProvider.MAX_ATMA, null);
		ICooldown cd = player.getCapability(CooldownBaubleProvider.COOLDOWN, null);
		Vec3d aim = player.getLookVec();
		
		cd.setMaxTicks(350.0F);
		
		if (player.isSprinting() && !(player.fallDistance > 0) && !player.isInWater()) 
		{			
			player.addVelocity(aim.x * (1.01 * ((cd.getTicks()/cd.getMaxTicks())+0.1)), 0, aim.z * (1.01 * ((cd.getTicks()/cd.getMaxTicks())+0.1)));
				
			IBlockState blockBelow = player.world.getBlockState(player.getPosition().down());
			
			if(blockBelow.getMaterial() == Material.WATER || blockBelow.getMaterial() == Material.LAVA)
				player.setVelocity(player.motionX + 0.01, 0, player.motionZ + 0.01);
			
			if(player.ticksExisted % 3 == 0)
				player.playSound(SoundEvents.ITEM_FLINTANDSTEEL_USE, 1, 2);
			
			atma.removeAtma(50.0F);
			itemstack.damageItem(1, player);	
			
			if(cd.getTicks() > cd.getMaxTicks())
				cd.setTicks(cd.getMaxTicks());
			
			cd.setTicks(cd.getTicks()-10);
			
			if(cd.getTicks() < 0)
				cd.setTicks(0);
			
			Events.updatePlayerAtma((EntityPlayer) player);
		}
		
	}
	
	@Override
	public BaubleType getBaubleType(ItemStack itemstack) 
	{	
		return BaubleType.CHARM;
	}

	@Override
	public void onEquipped(ItemStack itemstack, EntityLivingBase player) 
	{
		player.playSound(SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, .75F, 1.9f);
	}

	@Override
	public void onUnequipped(ItemStack itemstack, EntityLivingBase player) 
	{
		player.playSound(SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, .75F, 2f);
	}
}
