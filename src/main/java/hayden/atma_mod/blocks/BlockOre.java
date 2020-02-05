package hayden.atma_mod.blocks;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.oredict.OreDictionary;

public class BlockOre extends AdvBlockBase
{
	private String oreName;
	private String oreDictName;
	
	public BlockOre(String name, Material material, SoundType soundType, float hardness, float resistance, String tool,
			int harvestLevel, float lightLevel, int lightOpacity, CreativeTabs tab, Item toDrop, int minDrop,
			int maxDrop) 
	{
		super(name, material, soundType, hardness, resistance, tool, harvestLevel, lightLevel, lightOpacity, tab, toDrop,
				minDrop, maxDrop);
		this.oreName = name;
	}
	
	public void initOreDict() 
	{
		OreDictionary.registerOre(this.oreName, this);
	}
	
}
