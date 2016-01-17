package panda2134.CLG.integration;

import panda2134.CLG.util.CLGReference;
import panda2134.CLG.util.Mods;

public class IEModule extends BaseModule {
	@Override
	public void postInit() {
		if (CLGReference.recipeState.get(Mods.IDs.IE))
			setIECompatRecipes();
	}

	private void setIECompatRecipes() {
		// TODO Auto-generated method stub
		// TODO finish this
	}
}
