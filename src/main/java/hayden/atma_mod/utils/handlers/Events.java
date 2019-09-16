package hayden.atma_mod.utils.handlers;

import hayden.atma_mod.capabilities.AtmaProvider;
import hayden.atma_mod.capabilities.CooldownBaubleProvider;
import hayden.atma_mod.capabilities.IAtma;
import hayden.atma_mod.capabilities.ICooldown;
import hayden.atma_mod.init.ModItems;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.command.ICommand;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntityWitherSkeleton;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
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
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;

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
		
		if(true)
		{
			atma.addAtma(player.getCooledAttackStrength(0)*300);
		}
		else
			return;
		
		//Explosion explosion = new Explosion(event.getTarget().world, event.getEntityPlayer(), event.getTarget().posX, event.getTarget().posY, event.getTarget().posZ, 3.0F, false, false);
		//explosion.doExplosionB(true);
		//explosion.doExplosionA();
	}
	

	
	@SubscribeEvent
	public void perPlayerTick(PlayerTickEvent event)
	{	
		EntityPlayer player = event.player;
		IAtma atma = player.getCapability(AtmaProvider.MAX_ATMA, null);
		ICooldown charmcd = player.getCapability(CooldownBaubleProvider.COOLDOWN, null);
				
		if(charmcd.getTicks() < charmcd.getMaxTicks());
			charmcd.addTicks();
		
		if(player.getEntityWorld().isDaytime() && (atma.getAtma() < atma.getMaxAtma()))
			atma.addAtma(1.0F);
		
		if(atma.getAtma() > atma.getMaxAtma())
		{
			player.setFire(1);
		}
		
		if(atma.getAtma() < 0.0F)
		{
			atma.removeAtma(0.5F);
			player.addPotionEffect(new PotionEffect(MobEffects.HUNGER));
			player.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS));
			if(atma.getAtma() < -1000)
			{
				player.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS));
				player.addPotionEffect(new PotionEffect(MobEffects.WITHER));
				if(atma.getAtma() < -2000)
				{
					player.setHealth(0);
					player.setDead();
				}
					
			}
			else
				player.removePotionEffect(MobEffects.HEALTH_BOOST);
		}
		
			
	}
		
	@SubscribeEvent
	public void onPlayerLogsIn(PlayerLoggedInEvent event)
	{
		EntityPlayer player = event.player;
		IAtma atma = player.getCapability(AtmaProvider.MAX_ATMA, null);
		
		if(player.hasCapability(AtmaProvider.MAX_ATMA, null))
		{
			event.player.sendMessage(new TextComponentString(Float.toString(atma.getAtma())));
		}
		else
			event.player.sendMessage(new TextComponentString("fuck"));
		
		//player.sendMessage(new TextComponentString(message));
	}
	@SubscribeEvent
	public void onPlayerClone(PlayerEvent.PlayerRespawnEvent event)
	{
		EntityPlayer player = event.player;
		IAtma atma = player.getCapability(AtmaProvider.MAX_ATMA, null);
		
		event.player.sendMessage(new TextComponentString(Float.toString(atma.getAtma())));
	}
}
