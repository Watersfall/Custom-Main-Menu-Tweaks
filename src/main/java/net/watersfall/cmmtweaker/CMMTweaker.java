package net.watersfall.cmmtweaker;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.spongepowered.asm.launch.MixinBootstrap;

@Mod(modid = CMMTweaker.MOD_ID, name = CMMTweaker.NAME, version = CMMTweaker.VERSION)
public class CMMTweaker
{
    public static final String MOD_ID = "cmmtweaker";
    public static final String NAME = "Custom Main Menu Tweaker";
    public static final String VERSION = "1.0.0";
}
