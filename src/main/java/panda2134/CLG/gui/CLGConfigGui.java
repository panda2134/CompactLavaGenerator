package panda2134.CLG.gui;

import panda2134.CLG.util.CLGReference;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import cpw.mods.fml.client.config.GuiConfig;


public class CLGConfigGui extends GuiConfig {
  public CLGConfigGui(GuiScreen parent) {
    super(parent,
        new ConfigElement(CLGReference.cfg.getCategory(Configuration.CATEGORY_GENERAL)).getChildElements(),
        CLGReference.modid, false, false, GuiConfig.getAbridgedConfigPath(CLGReference.cfg.toString()));
  }
}