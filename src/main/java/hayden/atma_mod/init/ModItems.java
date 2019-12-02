package hayden.atma_mod.init;

import java.util.ArrayList;
import java.util.List;

import hayden.atma_mod.items.AtmaCoil;
import hayden.atma_mod.items.AtmaCoilBlock;
import hayden.atma_mod.items.AtmaCrystal;
import hayden.atma_mod.items.AtmaRing;
import hayden.atma_mod.items.AtmaVisor;
import hayden.atma_mod.items.Blindfold;
import hayden.atma_mod.items.DashFeather;
import hayden.atma_mod.items.DebugTool;
import hayden.atma_mod.items.ItemBase;
import hayden.atma_mod.items.SpeedCharm;
import net.minecraft.item.Item;

public class ModItems 
{
	public static final List<Item> ITEMS = new ArrayList<Item>();
	
//	Items
	
	public static final DebugTool DEBUGTOOL = new DebugTool("debug_tool");
	
	public static final ItemBase PYRITESHARDS = new ItemBase("pyrite_shards");
	
	public static final AtmaCrystal LESSERATMACRYSTAL = new AtmaCrystal("lesser_atma_crystal", 1000);
	public static final AtmaCrystal ATMACRYSTAL = new AtmaCrystal("atma_crystal", 10000);
	
//	Baubles
	
	public static final DashFeather DASHFEATHER = new DashFeather("dash_feather");
	public static final SpeedCharm SPEEDCHARM =  new SpeedCharm("speed_charm");
	
	public static final Blindfold BLINDFOLD = new Blindfold("blindfold");
	public static final AtmaCoil ATMACOIL = new AtmaCoil("atma_coil");
	public static final AtmaCoilBlock ATMACOILBLOCK = new AtmaCoilBlock("atma_coil_block");
	
	public static final AtmaRing MINORMAXATMARING = new AtmaRing("minor_max_atma_ring", 20F, 0F, 0F);
	public static final AtmaRing MINOREFFICIENCYATMARING = new AtmaRing("minor_atma_efficiency_ring", 0F, 10F, 0F);
	public static final AtmaRing MINORBOOSTATMARING = new AtmaRing("minor_atma_boost_ring", 0F, 0F, 20F);
	
	
	public static final AtmaVisor CRUDEVISOR = new AtmaVisor("crude_visor", 5F, 5F, 5F);
	public static final AtmaVisor TRUEVISOR = new AtmaVisor("true_visor", 10F, 10F, 10F);
	
//	public static final Item BASEBALLBAT = new ItemBase("bat");
	
	
	//materials
	//public static final ToolMaterial MATERIAL_POWER = EnumHelper.addToolMaterial("material_power", 4, 250, 9.0F, 4.0F, 10);
	
	//items
	//public static final Item BASEBALLBAT = new ItemBase("bat");
	//public static final Item POWER_CRYSTAL = new ItemBase("power_crystal");
	//public static final Item PERFECT_CRYSTAL_POWER = new ItemBase("perfect_crystal_power");
	
	//tools
	//public static final ItemTool POWER_HAMMER = new PowerHammer("power_hammer", MATERIAL_POWER);
}
