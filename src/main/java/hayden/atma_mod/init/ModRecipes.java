package hayden.atma_mod.init;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModRecipes 
{
	public static void init()
	{
		ModBlocks.oreQuartz.initOreDict();
		
		GameRegistry.addSmelting(ModItems.CARMINE, new ItemStack(ModItems.CARMINE_BRICK, 1), 10f);
	}
}
