package net.watersfall.cmmtweaker.core;

import net.minecraft.launchwrapper.LaunchClassLoader;
import net.minecraftforge.fml.relauncher.CoreModManager;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import org.spongepowered.asm.launch.MixinBootstrap;

import javax.annotation.Nullable;
import java.io.File;
import java.util.Map;

@IFMLLoadingPlugin.MCVersion("1.12.2")
public class CoreMod implements IFMLLoadingPlugin {

    public CoreMod()
    {
        try
        {
            File thisFile = new File(CoreMod.class.getProtectionDomain().getCodeSource().getLocation().toURI());
            File ccmFile = new File(thisFile.getParent(), "CustomMainMenu-MC1.12.2-2.0.9.1.jar");
            if(!ccmFile.exists())
            {
                throw new RuntimeException("CustomMainMenu-MC1.12.2-2.0.9.1.jar not found in the mods folder! Please install it to use this mod!");
            }
            loadModJar(ccmFile);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        MixinBootstrap.init();
    }

    private void loadModJar(File jar) throws Exception
    {
        ((LaunchClassLoader) getClass().getClassLoader()).addURL(jar.toURI().toURL());
        CoreModManager.getReparseableCoremods().add(jar.getName());
    }

    @Override
    public String[] getASMTransformerClass() {
        return new String[0];
    }

    @Override
    public String getModContainerClass() {
        return null;
    }

    @Nullable
    @Override
    public String getSetupClass() {
        return null;
    }

    @Override
    public void injectData(Map<String, Object> data) {
    }

    @Override
    public String getAccessTransformerClass() {
        return null;
    }
}
