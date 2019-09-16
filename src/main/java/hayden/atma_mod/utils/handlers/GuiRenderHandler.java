package hayden.atma_mod.utils.handlers;

import hayden.atma_mod.capabilities.AtmaProvider;
import hayden.atma_mod.capabilities.IAtma;
import hayden.atma_mod.gui.PlayerAtmaGauge;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class GuiRenderHandler 
{
    @SubscribeEvent
    public void onRenderGui(RenderGameOverlayEvent.Post event)
    {    	
    	IAtma atma = Minecraft.getMinecraft().player.getCapability(AtmaProvider.MAX_ATMA, null);
    	
    	if (event.getType() != ElementType.EXPERIENCE) return;
    	
    	new PlayerAtmaGauge(Minecraft.getMinecraft(), atma.getAtma(), atma.getMaxAtma());
    }	
}
