package hayden.atma_mod.utils.handlers;

import hayden.atma_mod.init.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
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
		Explosion explosion = new Explosion(event.getTarget().world, event.getEntityPlayer(), event.getTarget().posX, event.getTarget().posY, event.getTarget().posZ, 3.0F, false, false);
		explosion.doExplosionB(true);
		explosion.doExplosionA();
	}
		
}
