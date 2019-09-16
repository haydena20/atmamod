package hayden.atma_mod.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;

public class PlayerHUD extends Gui
{
	public PlayerHUD(Minecraft mc, float minA, float maxA, float minCd, float maxCd)
	{
		ScaledResolution scaled = new ScaledResolution(mc);
		int width = scaled.getScaledWidth();
		int height = scaled.getScaledHeight();
		
		drawCenteredString(mc.fontRenderer, (int)minA/10 + "/" + (int)maxA/10, width / 2, (height / 5) - 4, Integer.parseInt("FFAA00", 16));
		
		drawCenteredString(mc.fontRenderer, (int)minCd + "/" + (int)maxCd, width / 2, (height / 5) - 15, Integer.parseInt("FFAA00", 16));
	}
}
