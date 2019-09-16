package hayden.atma_mod.utils.handlers;

import hayden.atma_mod.Main;
import hayden.atma_mod.capabilities.AtmaProvider;
import hayden.atma_mod.capabilities.CooldownBaubleProvider;
import hayden.atma_mod.utils.Reference;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class CapabilityHandler
{
	public static final ResourceLocation MAX_ATMA = new ResourceLocation(Reference.MODID, "atma");
	public static final ResourceLocation COOLDOWN = new ResourceLocation(Reference.MODID, "cooldown");
	
	@SubscribeEvent
	public void attachCapability(AttachCapabilitiesEvent<Entity> event)
	{
		//if (!(event.getObject() instanceof EntityPlayer)) return;
		
		event.addCapability(MAX_ATMA, new AtmaProvider());
		
		event.addCapability(COOLDOWN, new CooldownBaubleProvider());
	}
}
