package panda2134.CLG.integration;

public class EnergySwitch {
	public static EnergyUnit switchUnit(EnergyUnit u){
		if(u.ordinal()+1 < EnergyUnit.values().length)
			return EnergyUnit.values()[u.ordinal()+1];
		else
			return EnergyUnit.values()[0];
	}
}
