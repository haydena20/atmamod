package hayden.atma_mod.capabilities;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class CooldownBaubleProvider implements ICapabilitySerializable<NBTBase>
{
	@CapabilityInject(ICooldown.class)
	public static final Capability<ICooldown> COOLDOWN = null;
	
	private ICooldown instance = COOLDOWN.getDefaultInstance();
	
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing)
	{
		return capability == COOLDOWN;
	}
	
	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing)
	{
		return capability == COOLDOWN ? COOLDOWN.<T> cast(this.instance) : null;
	}
	
	@Override
	public NBTBase serializeNBT()
	{
		return COOLDOWN.getStorage().writeNBT(COOLDOWN, this.instance, null);
	}
	
	@Override
	public void deserializeNBT(NBTBase nbt)
	{
		COOLDOWN.getStorage().readNBT(COOLDOWN, this.instance, null, nbt);
	}
}
