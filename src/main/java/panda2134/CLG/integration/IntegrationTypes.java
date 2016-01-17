/**
 * 
 */
package panda2134.CLG.integration;

import panda2134.CLG.util.Mods;
import panda2134.CLG.util.Mods.Mod;

/**
 * @author panda2134
 *
 */
public enum IntegrationTypes {
	IC2(IC2Module.class, Mods.IC2), CofhAPIEnergy(RFEnergyModule.class,
			Mods.cofhApiEnergy), ThermalExpansion(TEModule.class,
			Mods.ThermalExpansion), Factorization(FZModule.class,
			Mods.Factorization), ImmersiveEngineering(IEModule.class,
			Mods.ImmersiveEngineering), BuildCraft(BCModule.class,
			Mods.BuildCraft);

	public Class<? extends BaseModule> clz;
	public Mods.Mod mod;
	public BaseModule module;

	private IntegrationTypes(Class<? extends BaseModule> clz, Mod mod) {
		this.clz = clz;
		this.mod = mod;
	}

	public BaseModule getModule() {
		if (mod.available && module == null) {
			try {
				module = clz.newInstance();
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}
		return module;
	}
}
