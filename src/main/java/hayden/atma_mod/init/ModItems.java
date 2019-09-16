package hayden.atma_mod.init;

import java.util.ArrayList;
import java.util.List;

import hayden.atma_mod.items.ItemBase;
import hayden.atma_mod.items.DashFeather;
import hayden.atma_mod.items.tools.ToolSword;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraftforge.common.util.EnumHelper;

public class ModItems 
{
	public static final List<Item> ITEMS = new ArrayList<Item>();
	
	public static final DashFeather DASHFEATHER = new DashFeather("dash_feather");
	public static final Item BASEBALLBAT = new ItemBase("bat");
	
	
	//materials
	//public static final ToolMaterial MATERIAL_POWER = EnumHelper.addToolMaterial("material_power", 4, 250, 9.0F, 4.0F, 10);
	
	//items
	//public static final Item BASEBALLBAT = new ItemBase("bat");
	//public static final Item POWER_CRYSTAL = new ItemBase("power_crystal");
	//public static final Item PERFECT_CRYSTAL_POWER = new ItemBase("perfect_crystal_power");
	
	//tools
	//public static final ItemTool POWER_HAMMER = new PowerHammer("power_hammer", MATERIAL_POWER);
}
