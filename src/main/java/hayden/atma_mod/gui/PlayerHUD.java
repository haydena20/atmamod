package hayden.atma_mod.gui;

import java.awt.Color;

import baubles.api.BaublesApi;
import hayden.atma_mod.items.AtmaVisor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;

public class PlayerHUD extends Gui
{
	public PlayerHUD(Minecraft mc, float minA, float maxA, float minCd, float maxCd)
	{
		ScaledResolution scaled = new ScaledResolution(mc);
		int width = scaled.getScaledWidth();
		int height = scaled.getScaledHeight();
		
//		StringBuilder maxBrackets = new StringBuilder("");
//		for(int i = 0; i < maxA/100; i++)
//			maxBrackets.insert(i, "\n.");
//		
//		StringBuilder currentMarkers = new StringBuilder("");
//		for(int i = 0; i < Math.abs(minA/100); i++)
//			currentMarkers.insert(i, "\n.");
//		
//			if(!(minA<0))
//				drawCenteredString(mc.fontRenderer, (int)minA/10 + "/" + (int)maxA/10, width / 2, (height / 5) - 4, Integer.parseInt("FFAA00", 16));
//			else
//				drawCenteredString(mc.fontRenderer, (int)minA/10 + "/" + (int)maxA/10, width / 2, (height / 5) - 4, Integer.parseInt("FF0000", 16));
			
		
		if(!(BaublesApi.getBaublesHandler(mc.player).getStackInSlot(4).getItem() instanceof AtmaVisor))
			return;
		
		int uiWidth = width - 6;
		int barHeight = height;
		
		if(minCd < maxCd)
			for(int i = (int) (barHeight * (minCd/maxCd)); i > 0; i--)
				drawString(mc.fontRenderer, "-", uiWidth-2, (height - 2) - i, Integer.parseInt("FFFFFF", 16));
		else
			for(int i = (int) (barHeight * (minCd/maxCd)); i > 0; i--)
				drawString(mc.fontRenderer, "-", uiWidth-2, (height - 2) - i, Integer.parseInt("0000FF", 16));
		
		for(int i = barHeight; i > 0; i--)
			drawString(mc.fontRenderer, "_", uiWidth, (height - 7) - i, Integer.parseInt("FF0000", 16));
		
		
		
		if(!(minA<0))
			{
				if(!(minA > maxA))
				{
					for(int i = (int) (barHeight * (minA/maxA)); i > 0; i--)
						drawString(mc.fontRenderer, "_", uiWidth, (height - 7) - i, Integer.parseInt("FFAA00", 16));
					
					drawCenteredString(mc.fontRenderer,(int) ((minA/maxA)*100) + "%", uiWidth - 15, (height - 10), Integer.parseInt("FFAA00", 16));
				}
				else
				{
					for(int i = barHeight; i > 0; i--)
						drawString(mc.fontRenderer, "_", (int) (uiWidth + Math.random() - (Math.random()*-1)), (height - 7) - i, Integer.parseInt("FFAA00", 16));
					
					drawCenteredString(mc.fontRenderer,(int) ((minA/maxA)*100) + "%", (int) ((uiWidth + Math.random() - (Math.random()*-1)) - 15), (height - 10), Integer.parseInt("FFAA00", 16));
				}
			}
			else
			{
				for(int i = (int) (barHeight * Math.abs(minA/maxA)); i > 0; i--)
					drawString(mc.fontRenderer, "_", (int) (uiWidth + Math.random() - (Math.random()*-1)), (height - 7) - i, Integer.parseInt("FFFFFF", 16));
				
				drawCenteredString(mc.fontRenderer,(int) ((minA/maxA)*100) + "%", (int)(uiWidth - 15 - (Math.random() - (Math.random()*-1))), (height - 10), Integer.parseInt("FFFFFF", 16));
			}
		
//		drawString(mc.fontRenderer, maxBrackets.toString(), width / 2, (height / 5) - 4, Integer.parseInt("FF0000", 16));
//		
//			if(!(minA<0))
//			{
//				drawString(mc.fontRenderer, currentMarkers.toString(), width / 2, (height / 5) - 4, Integer.parseInt("FFAA00", 16));
//			}
//			else
//			{
//				drawString(mc.fontRenderer, currentMarkers.toString(), width / 2, (height / 5) - 4, Integer.parseInt("FFFFFF", 16));
//			}
				

		
			if(!(minCd >= maxCd))
				drawCenteredString(mc.fontRenderer, (int)minCd + "/" + (int)maxCd, width / 2, (height / 5) - 15, Integer.parseInt("FF0000", 16));
			else
				drawCenteredString(mc.fontRenderer, "Charm Ready", width / 2, (height / 5) - 15, Integer.parseInt("FFAA00", 16));
	}
}
