package hayden.atma_mod.gui;

import hayden.atma_mod.init.ModItems;
import hayden.atma_mod.utils.Reference;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class CreativeTab extends CreativeTabs
{

	public CreativeTab() 
	{
		super("Atama");		
		setBackgroundImageName("item_search.png");
	}

	@Override
	public ItemStack getTabIconItem() 
	{
		return new ItemStack(ModItems.PYRITESHARDS);
	}
	
	@Override
	public boolean hasSearchBar() 
	{
		return true;
	}

}
