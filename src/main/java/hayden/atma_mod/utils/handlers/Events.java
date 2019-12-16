package hayden.atma_mod.utils.handlers;

import baubles.api.BaublesApi;
import hayden.atma_mod.capabilities.AtmaProvider;
import hayden.atma_mod.capabilities.CooldownBaubleProvider;
import hayden.atma_mod.capabilities.IAtma;
import hayden.atma_mod.capabilities.ICooldown;
import hayden.atma_mod.init.ModItems;
import hayden.atma_mod.items.AtmaCoil;
import hayden.atma_mod.items.AtmaRing;
import hayden.atma_mod.items.ItemBase;
import hayden.atma_mod.items.SpeedCharm;
import hayden.atma_mod.messages.MyMessage;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.command.ICommand;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntityWitherSkeleton;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.*;
import net.minecraftforge.event.CommandEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import net.minecraft.item.Item;

public class Events 
{
	@SubscribeEvent(priority=EventPriority.LOWEST)
	public void onJump(LivingJumpEvent event)
	{
		if(event.getEntity() instanceof EntityPlayer && BaublesApi.isBaubleEquipped((EntityPlayer)event.getEntity(), ModItems.SPEEDCHARM) >= 0 && event.getEntity().isSprinting())
				event.getEntity().setVelocity(event.getEntity().motionX, 0, event.getEntity().motionZ);
	}
	
	@SubscribeEvent
	public void onItemPickup(EntityItemPickupEvent event)
	{
		
	}
	@SubscribeEvent
	public void onHit(AttackEntityEvent event)
	{
		EntityPlayer player = event.getEntityPlayer();
		IAtma atma = player.getCapability(AtmaProvider.MAX_ATMA, null);
		ICooldown charmcd = player.getCapability(CooldownBaubleProvider.COOLDOWN, null);
		
//		if(player.world.isRemote)
//			return;
		
		if(!(BaublesApi.isBaubleEquipped(player, ModItems.ATMACOIL) == -1) && charmcd.getTicks() >= charmcd.getMaxTicks())
		{
			player.addPotionEffect(new PotionEffect(MobEffects.SPEED,60,1,false,false));
			atma.addAtma(500);
			charmcd.setTicks(0);
			
			Events.updatePlayerAtma((EntityPlayer) player);
		}
		//Explosion explosion = new Explosion(event.getTarget().world, event.getEntityPlayer(), event.getTarget().posX, event.getTarget().posY, event.getTarget().posZ, 3.0F, false, false);
		//explosion.doExplosionB(true);
		//explosion.doExplosionA();
	}
	@SubscribeEvent
	public void onMine(BreakEvent event)
	{
		EntityPlayer player = event.getPlayer();
		IAtma atma = player.getCapability(AtmaProvider.MAX_ATMA, null);
		ICooldown charmcd = player.getCapability(CooldownBaubleProvider.COOLDOWN, null);
		
//		if(player.world.isRemote)
//			return;
		
		if(!(BaublesApi.isBaubleEquipped(player, ModItems.ATMACOILBLOCK) == -1) && charmcd.getTicks() >= charmcd.getMaxTicks())
		{
			player.addPotionEffect(new PotionEffect(MobEffects.HASTE,15,2,false,false));
			atma.addAtma(20);
			charmcd.setTicks(0);
			
			Events.updatePlayerAtma((EntityPlayer) player);
		}
				
		if(event.getWorld().getBlockState(event.getPos()).getBlock().canHarvestBlock(event.getWorld(), event.getPos(), player))
		{
//			int index = 1; //up
//			
//			while(event.getWorld().getBlockState(event.getPos()) == event.getWorld().getBlockState(event.getPos().up(index)))
//			{
//				event.getWorld().destroyBlock(event.getPos().up(index), true);
//				event.getPlayer().getHeldItemMainhand().damageItem(1, player);
//				
//				veinMine(event.getWorld(), event.getPlayer(), event.getPos().up(index));
//				index++;
//			}
//			
//			index = 1; //down
//			
//			while(event.getWorld().getBlockState(event.getPos()) == event.getWorld().getBlockState(event.getPos().down(index)))
//			{
//				event.getWorld().destroyBlock(event.getPos().down(index), true);
//				event.getPlayer().getHeldItemMainhand().damageItem(1, player);
//				
//				veinMine(event.getWorld(), event.getPlayer(), event.getPos().down(index));
//				index++;
//			}
//			
//			index = 1; //east
//			
//			while(event.getWorld().getBlockState(event.getPos()) == event.getWorld().getBlockState(event.getPos().east(index)))
//			{
//				event.getWorld().destroyBlock(event.getPos().east(index), true);
//				event.getPlayer().getHeldItemMainhand().damageItem(1, player);
//				
//				veinMine(event.getWorld(), event.getPlayer(), event.getPos().east(index));
//				index++;
//			}
//			
//			index = 1; //west
//			
//			while(event.getWorld().getBlockState(event.getPos()) == event.getWorld().getBlockState(event.getPos().west(index)))
//			{
//				event.getWorld().destroyBlock(event.getPos().west(index), true);
//				event.getPlayer().getHeldItemMainhand().damageItem(1, player);
//				
//				veinMine(event.getWorld(), event.getPlayer(), event.getPos().west(index));
//				index++;
//			}
//			
//			index = 1; //north
//			
//			while(event.getWorld().getBlockState(event.getPos()) == event.getWorld().getBlockState(event.getPos().north(index)))
//			{
//				event.getWorld().destroyBlock(event.getPos().north(index), true);
//				event.getPlayer().getHeldItemMainhand().damageItem(1, player);
//				
//				veinMine(event.getWorld(), event.getPlayer(), event.getPos().north(index));
//				index++;
//			}
//			
//			index = 1; //south
//			
//			while(event.getWorld().getBlockState(event.getPos()) == event.getWorld().getBlockState(event.getPos().south(index)))
//			{
//				event.getWorld().destroyBlock(event.getPos().south(index), true);
//				event.getPlayer().getHeldItemMainhand().damageItem(1, player);
//				
//				veinMine(event.getWorld(), event.getPlayer(), event.getPos().south(index));
//				index++;	
//			}
			veinMine(event.getWorld(), event.getPlayer(), event.getPos(), event.getWorld().getBlockState(event.getPos()), 2);
			
		}
			
		
		
		//Explosion explosion = new Explosion(event.getTarget().world, event.getEntityPlayer(), event.getTarget().posX, event.getTarget().posY, event.getTarget().posZ, 3.0F, false, false);
		//explosion.doExplosionB(true);
		//explosion.doExplosionA();
	}
	public void veinMine(World worldIn, EntityPlayer playerIn, BlockPos blockPos, IBlockState blockType, int max)
	{
		if(max <= 0)
			return;
		
		Explosion explosion = new Explosion(worldIn, playerIn, blockPos.getX(), blockPos.getY(), blockPos.getZ(), 1, false, false);
		
		int index = 1; //up
		
		while(blockType == worldIn.getBlockState(blockPos.up(index)))
		{
			if(index > max) break;
			
			worldIn.destroyBlock(blockPos.up(index), true);
			playerIn.getHeldItemMainhand().damageItem(1, playerIn);
			explosion.doExplosionB(false);
			
			index++;
			veinMine(worldIn, playerIn, blockPos.up(), blockType, max--);
		}
		
		index = 1; //down
		
		while(blockType == worldIn.getBlockState(blockPos.down(index)))
		{
			if(index > max) break;
			
			worldIn.destroyBlock(blockPos.down(index), true);
			playerIn.getHeldItemMainhand().damageItem(1, playerIn);
			explosion.doExplosionB(false);
			
			index++;
			veinMine(worldIn, playerIn, blockPos.down(), blockType, max--);
		}
		
		index = 1; //east
		
		while(blockType == worldIn.getBlockState(blockPos.east(index)))
		{
			if(index > max) break;
			
			worldIn.destroyBlock(blockPos.east(index), true);
			playerIn.getHeldItemMainhand().damageItem(1, playerIn);
			explosion.doExplosionB(false);
			
			index++;
			veinMine(worldIn, playerIn, blockPos.east(), blockType, max--);
		}
		
		index = 1; //west
		
		while(blockType == worldIn.getBlockState(blockPos.west(index)))
		{
			if(index > max) break;
			
			worldIn.destroyBlock(blockPos.west(index), true);
			playerIn.getHeldItemMainhand().damageItem(1, playerIn);
			explosion.doExplosionB(false);
			
			index++;
			veinMine(worldIn, playerIn, blockPos.west(), blockType, max--);
		}
		
		index = 1; //north
		
		while(blockType == worldIn.getBlockState(blockPos.north(index)))
		{
			if(index > max) break;
			
			worldIn.destroyBlock(blockPos.north(index), true);
			playerIn.getHeldItemMainhand().damageItem(1, playerIn);
			explosion.doExplosionB(false);
			
			index++;
			veinMine(worldIn, playerIn, blockPos.north(), blockType, max--);
		}
		
		index = 1; //south
		
		while(blockType == worldIn.getBlockState(blockPos.south(index)))
		{
			if(index > max) break;
			
			worldIn.destroyBlock(blockPos.south(index), true);
			playerIn.getHeldItemMainhand().damageItem(1, playerIn);
			explosion.doExplosionB(false);
			
			index++;
			veinMine(worldIn, playerIn, blockPos.south(), blockType, max--);
		}
	}
	

	@SubscribeEvent
	public void perPlayerTickServer(PlayerTickEvent event)
	{		
		EntityPlayer player = event.player;
		IAtma atma = player.getCapability(AtmaProvider.MAX_ATMA, null);
		ICooldown charmcd = player.getCapability(CooldownBaubleProvider.COOLDOWN, null);
				
		
//		Checking and setting max Atma
		
		//Todo
		
//		Capability Tick Increases
		
		if(charmcd.getTicks() < charmcd.getMaxTicks());
			charmcd.addTicks();
			
		float maxBoost = 0;
		float effBoost = 0;
		float gainBoost = 0;
		for(int i = 0; i < 7; i++)
			if(BaublesApi.getBaublesHandler(player).getStackInSlot(i).getItem() instanceof AtmaRing)
			{
				maxBoost += ((AtmaRing) BaublesApi.getBaublesHandler(player).getStackInSlot(i).getItem()).getMaxBoost();
				effBoost += ((AtmaRing) BaublesApi.getBaublesHandler(player).getStackInSlot(i).getItem()).getEffBoost();
				gainBoost += ((AtmaRing) BaublesApi.getBaublesHandler(player).getStackInSlot(i).getItem()).getGainBoost();
			}
		atma.setMaxBoost(maxBoost);
		atma.setAtmaEff(effBoost);
		atma.setAtmaBoost(gainBoost);
			
//		if(player.ticksExisted%40==0)
//			Events.updatePlayerAtma((EntityPlayer) player);
			
			//Anything below this line runs exclusively on the server
			if(player.world.isRemote)
				return;
			
			
		if(player.ticksExisted%30==0)
		{
			
//			Atma Sunlight Gain (Friendly increase; will never go above max)
			if(player.getEntityWorld().canBlockSeeSky(player.getPosition()) && player.getEntityWorld().isDaytime() && (atma.getAtma() < atma.getMaxAtma()))
			{
				atma.addAtma(50F / player.world.getSunBrightnessFactor(0));
				if((atma.getAtma()+500 > atma.getMaxAtma()) && (atma.getAtma()+500 < atma.getMaxAtma()+500))
					atma.setAtma(atma.getMaxAtma());

			}
			
//			Atma Overload, ignites when above max
			if(atma.getAtma() > atma.getMaxAtma())
			{
				player.removePotionEffect(MobEffects.FIRE_RESISTANCE);
				player.setFire(60);
			}
			
//			Negative Atma Effects, causes debuffs and 'corruption' (negative atma regen)
			if(atma.getAtma() < 0.0F) 
			{
				atma.removeAtma((float) Math.abs(atma.getAtma() * 0.02));
				player.addPotionEffect(new PotionEffect(MobEffects.HUNGER,60,(int) Math.abs(atma.getAtma() * 0.001),true,true));
				player.removePotionEffect(MobEffects.HEALTH_BOOST);
				player.addPotionEffect(new PotionEffect(MobEffects.HEALTH_BOOST,60,(int)(atma.getAtma() * 0.0005)-1,false,false));
				
				if(atma.getAtma() < atma.getMaxAtma()*-1)
					player.onKillCommand();
			}
//			else if(atma.getAtma() <= -1000.0F && atma.getAtma() > -2000.0F)
//			{
//				atma.removeAtma(30F);
//				player.addPotionEffect(new PotionEffect(MobEffects.HUNGER,60,1,true,true));
//				player.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS,60,1,true,true));
//				player.removePotionEffect(MobEffects.HEALTH_BOOST);
//				player.addPotionEffect(new PotionEffect(MobEffects.HEALTH_BOOST,60,-3,false,false));
//			}
//			else if(atma.getAtma() <= -2000.0F)
//			{
//				atma.removeAtma(40F);
//				player.addPotionEffect(new PotionEffect(MobEffects.HUNGER,60,2,true,true));
//				player.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS,60,2,true,true));
//				player.addPotionEffect(new PotionEffect(MobEffects.BLINDNESS,100,0,false,false));
//				player.removePotionEffect(MobEffects.HEALTH_BOOST);
//				player.addPotionEffect(new PotionEffect(MobEffects.HEALTH_BOOST,60,-5,false,false));
//			}
			
			Events.updatePlayerAtma((EntityPlayer) player);
		}


		

	}
		
//	@SubscribeEvent	
//	public void updateClient(PlayerTickEvent event)
//	{
//		if(event.phase != Phase.END) return;
//		updatePlayerAtma(event.player);		
//	}
	
	public static void updatePlayerAtma(EntityPlayer player) 
	{
		if(!player.world.isRemote) 
		{
			IAtma mh = player.getCapability(AtmaProvider.MAX_ATMA, null);
			if(player instanceof EntityPlayerMP)
				mh.updateClient(player);
		}
	}
	
	
	@SubscribeEvent
	public void onPlayerLogsIn(PlayerLoggedInEvent event)
	{
		Events.updatePlayerAtma((EntityPlayer) event.player);
	}
	@SubscribeEvent
	public void onPlayerClone(PlayerEvent.PlayerRespawnEvent event)
	{
		IAtma atma = event.player.getCapability(AtmaProvider.MAX_ATMA, null);
		atma.setAtma(0F);
		
		Events.updatePlayerAtma((EntityPlayer) event.player);
	}
}
