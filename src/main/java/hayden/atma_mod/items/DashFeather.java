package hayden.atma_mod.items;

import org.lwjgl.input.Keyboard;

import baubles.api.BaubleType;
import baubles.api.BaublesApi;
import baubles.api.IBauble;
import baubles.api.cap.IBaublesItemHandler;
import baubles.common.Baubles;
import hayden.atma_mod.capabilities.AtmaProvider;
import hayden.atma_mod.capabilities.CooldownBaubleProvider;
import hayden.atma_mod.capabilities.IAtma;
import hayden.atma_mod.capabilities.ICooldown;
import net.minecraft.client.Minecraft;
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
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class DashFeather extends ItemBase implements IBauble
{
	
	
	public DashFeather(String name) 
	{
		super(name);
		this.setMaxStackSize(1);
		this.setHasSubtypes(true);
		this.setMaxDamage(200);
		this.setCreativeTab(CreativeTabs.TOOLS);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
	{
//		ItemStack item = playerIn.getHeldItem(handIn);
//		Vec3d aim = playerIn.getLookVec();
		
//		EntityFireball fireball = new Entity(worldIn, playerIn, 1, 1, 1);
//		
//		fireball.setPosition(playerIn.posX + aim.x * 1.5, playerIn.posY + 1 + aim.y * 1.5, playerIn.posZ + aim.z * 1.5);
//		fireball.accelerationX = aim.x * 0.2;
//		fireball.accelerationY = aim.y * 0.2;
//		fireball.accelerationZ = aim.z * 0.2;
//		
//		worldIn.spawnEntity(fireball);
//		
//		item.damageItem(3, playerIn);
		
//		if(playerIn.isInWater())
//			return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, item);;
//		
//		playerIn.addVelocity(aim.x * 1.1, aim.y * 1.1, aim.z * 1.1);
//		playerIn.fallDistance = -999;
//		
//		IAtma atma = playerIn.getCapability(AtmaProvider.MAX_ATMA, null);
//		
//		atma.removeAtma(450.0F);
//		
//		((EntityPlayer)playerIn).getCooldownTracker().setCooldown(this, 60);
//		
//		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, item);
		
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
		int jump = Minecraft.getMinecraft().gameSettings.keyBindJump.getKeyCode();
		IAtma atma = player.getCapability(AtmaProvider.MAX_ATMA, null);
		ICooldown cd = player.getCapability(CooldownBaubleProvider.COOLDOWN, null);
		Vec3d aim = player.getLookVec();
		
		if(player.world.isRemote)
			return;
		
		cd.setMaxTicks(100.0F);
		
		if (Keyboard.isKeyDown(jump) && (player.fallDistance > 0) && !player.isInWater() && (cd.getTicks() >= cd.getMaxTicks())) 
		{
//			if(!player.isElytraFlying())
//				player.setVelocity(0, 0, 0);
			
			player.addVelocity(aim.x * 1.1, aim.y * 1.2, aim.z * 1.1);
			player.fallDistance = -999;
			
			atma.removeAtma(300.0F);
			itemstack.damageItem(1, player);	
			cd.setTicks(0);
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
