package hayden.atma_mod.utils.handlers;

import baubles.api.BaublesApi;
import hayden.atma_mod.capabilities.AtmaProvider;
import hayden.atma_mod.capabilities.CooldownBaubleProvider;
import hayden.atma_mod.capabilities.IAtma;
import hayden.atma_mod.capabilities.ICooldown;
import hayden.atma_mod.init.ModItems;
import hayden.atma_mod.items.AtmaCoil;
import hayden.atma_mod.items.ItemBase;
import hayden.atma_mod.messages.MyMessage;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.command.ICommand;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntityWitherSkeleton;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.*;
import net.minecraftforge.event.CommandEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import net.minecraft.item.Item;

public class Events 
{
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
			
//		if(player.ticksExisted%40==0)
//			Events.updatePlayerAtma((EntityPlayer) player);
			
			//Anything below this line runs exclusively on the server
			if(player.world.isRemote)
				return;
			
			
		if(player.ticksExisted%60==0)
		{
			
//			Atma Sunlight Gain (Friendly increase; will never go above max)
			if(player.getEntityWorld().canBlockSeeSky(player.getPosition()) && player.getEntityWorld().isDaytime() && (atma.getAtma() < atma.getMaxAtma()))
			{
				if((atma.getAtma()+50 > atma.getMaxAtma()) && (atma.getAtma()+50 < atma.getMaxAtma()+50))
					atma.setAtma(atma.getMaxAtma());
				
				atma.addAtma(50F);
			}
			
//			Atma Overload, ignites when above max
			if(atma.getAtma() > atma.getMaxAtma())
			{
				player.removePotionEffect(MobEffects.FIRE_RESISTANCE);
				player.setFire(60);
			}
			
//			Negative Atma Effects, causes debuffs and 'corruption' (negative atma regen)
			if(atma.getAtma() < 0.0F && atma.getAtma() > -1000.0F) 
			{
				atma.removeAtma(20F);
				player.addPotionEffect(new PotionEffect(MobEffects.HUNGER,60,0,true,true));
				player.removePotionEffect(MobEffects.HEALTH_BOOST);
				player.addPotionEffect(new PotionEffect(MobEffects.HEALTH_BOOST,60,-1,false,false));
			}
			else if(atma.getAtma() <= -1000.0F && atma.getAtma() > -2000.0F)
			{
				atma.removeAtma(30F);
				player.addPotionEffect(new PotionEffect(MobEffects.HUNGER,60,1,true,true));
				player.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS,60,1,true,true));
				player.removePotionEffect(MobEffects.HEALTH_BOOST);
				player.addPotionEffect(new PotionEffect(MobEffects.HEALTH_BOOST,60,-3,false,false));
			}
			else if(atma.getAtma() <= -2000.0F)
			{
				atma.removeAtma(40F);
				player.addPotionEffect(new PotionEffect(MobEffects.HUNGER,60,2,true,true));
				player.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS,60,2,true,true));
				player.addPotionEffect(new PotionEffect(MobEffects.BLINDNESS,100,0,false,false));
				player.removePotionEffect(MobEffects.HEALTH_BOOST);
				player.addPotionEffect(new PotionEffect(MobEffects.HEALTH_BOOST,60,-5,false,false));
			}
			
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
