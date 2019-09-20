package hayden.atma_mod.items;

import hayden.atma_mod.capabilities.AtmaProvider;
import hayden.atma_mod.capabilities.IAtma;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class AtmaCrystal extends ItemBase
{

	public AtmaCrystal(String name, int maxCharge) 
	{
		super(name);
		this.setMaxStackSize(1);
		this.setHasSubtypes(true);
		this.setMaxDamage(maxCharge);
		this.setDamage(getDefaultInstance(), 1);
		this.setCreativeTab(CreativeTabs.TOOLS);
	}
	
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
	{
		IAtma atma = playerIn.getCapability(AtmaProvider.MAX_ATMA, null);
		
		ItemStack item = playerIn.getHeldItem(handIn);
		if((item.getItemDamage() >= item.getMaxDamage()) || (item.getItemDamage()+item.getMaxDamage()/10) >= item.getMaxDamage())
		{
			item.setItemDamage(item.getMaxDamage());
			if(!item.getDisplayName().contains("Drained"))
				item.setStackDisplayName("§rDrained " + item.getDisplayName());
		}
		else
		{
			item.damageItem(item.getMaxDamage()/10, playerIn);
			atma.addAtma((item.getMaxDamage()/10));
			((EntityPlayer)playerIn).getCooldownTracker().setCooldown(this, 5);
		}
			
			
		
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, item);
	}
	
}
