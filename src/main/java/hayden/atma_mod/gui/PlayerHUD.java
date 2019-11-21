package hayden.atma_mod.gui;

import java.awt.Color;

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
			
		int uiWidth = (width / 4) + 8;
		
		for(int i = (int) (maxA/250); i > 0; i--)
			drawString(mc.fontRenderer, "_", uiWidth, (height - 8) - i, Integer.parseInt("FF0000", 16));
		
		if(!(minA<0))
			{
				for(int i = (int) (minA/250); i > 0; i--)
					drawString(mc.fontRenderer, "_", uiWidth, (height - 8) - i, Integer.parseInt("FFAA00", 16));
			}
			else
			{
				for(int i = (int) Math.abs((minA/250)); i > 0; i--)
					drawString(mc.fontRenderer, "_", uiWidth, (height - 8) - i, Integer.parseInt("FFFFFF", 16));
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
