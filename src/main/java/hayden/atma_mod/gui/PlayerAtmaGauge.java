package hayden.atma_mod.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;

public class PlayerAtmaGauge extends Gui
{
	public PlayerAtmaGauge(Minecraft mc, float min, float max)
	{
		ScaledResolution scaled = new ScaledResolution(mc);
		int width = scaled.getScaledWidth();
		int height = scaled.getScaledHeight();
		
		drawCenteredString(mc.fontRenderer, (int)min/10 + "/" + (int)max/10, width / 2, (height / 5) - 4, Integer.parseInt("FFAA00", 16));
	}
}
