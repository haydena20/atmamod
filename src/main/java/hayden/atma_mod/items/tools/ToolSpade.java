package hayden.atma_mod.items.tools;

import hayden.atma_mod.Main;
import hayden.atma_mod.init.ModItems;
import hayden.atma_mod.items.ItemBase;
import hayden.atma_mod.utils.IHasModel;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemSword;

public class ToolSpade extends ItemSpade implements IHasModel
{
	public ToolSpade(String name, ToolMaterial material)
	{
		super(material);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(CreativeTabs.TOOLS); 
		
		ModItems.ITEMS.add(this);
	}
	
	@Override
	public void registerModels()
	{
		Main.proxy.registerItemRenderer(this, 0, "inventory");
	}
}
