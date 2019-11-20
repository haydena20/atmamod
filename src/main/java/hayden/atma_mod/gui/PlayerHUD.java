package hayden.atma_mod.gui;

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
		
		StringBuilder myName = new StringBuilder("[]");
		for(int i = 0; i < minA/100; i++)
			myName.insert(1, "|");
		
//			if(!(minA<0))
//				drawCenteredString(mc.fontRenderer, (int)minA/10 + "/" + (int)maxA/10, width / 2, (height / 5) - 4, Integer.parseInt("FFAA00", 16));
//			else
//				drawCenteredString(mc.fontRenderer, (int)minA/10 + "/" + (int)maxA/10, width / 2, (height / 5) - 4, Integer.parseInt("FF0000", 16));
			
			if(!(minA<0))
				drawCenteredString(mc.fontRenderer, myName.toString(), width / 2, (height / 5) - 4, Integer.parseInt("FFAA00", 16));
			else
				drawCenteredString(mc.fontRenderer, (int)minA/10 + "/" + (int)maxA/10, width / 2, (height / 5) - 4, Integer.parseInt("FF0000", 16));

			
			if(!(minCd >= maxCd))
				drawCenteredString(mc.fontRenderer, (int)minCd + "/" + (int)maxCd, width / 2, (height / 5) - 15, Integer.parseInt("FF0000", 16));
			else
				drawCenteredString(mc.fontRenderer, "Charm Ready", width / 2, (height / 5) - 15, Integer.parseInt("FFAA00", 16));
	}
}
