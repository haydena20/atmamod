package hayden.atma_mod.capabilities;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class AtmaProvider implements ICapabilitySerializable<NBTBase>
{
	@CapabilityInject(IAtma.class)
	public static final Capability<IAtma> MAX_ATMA = null;
	
	private IAtma instance = MAX_ATMA.getDefaultInstance();
	
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing)
	{
		return capability == MAX_ATMA;
	}
	
	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing)
	{
		return capability == MAX_ATMA ? MAX_ATMA.<T> cast(this.instance) : null;
	}
	
	@Override
	public NBTBase serializeNBT()
	{
		return MAX_ATMA.getStorage().writeNBT(MAX_ATMA, this.instance, null);
	}
	
	@Override
	public void deserializeNBT(NBTBase nbt)
	{
		MAX_ATMA.getStorage().readNBT(MAX_ATMA, this.instance, null, nbt);
	}
}