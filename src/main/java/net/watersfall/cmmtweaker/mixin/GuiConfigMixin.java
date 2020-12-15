package net.watersfall.cmmtweaker.mixin;

import com.google.gson.JsonObject;
import lumien.custommainmenu.configuration.GuiConfig;
import lumien.custommainmenu.configuration.elements.Button;
import lumien.custommainmenu.lib.ANCHOR;
import net.watersfall.cmmtweaker.accessor.ButtonAccessor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(value = GuiConfig.class, remap = false)
public class GuiConfigMixin
{
	@Inject(method = "getButton(Lcom/google/gson/JsonObject;)Llumien/custommainmenu/configuration/elements/Button;", at = @At("RETURN"), locals = LocalCapture.CAPTURE_FAILHARD)
	public void addData(JsonObject jsonObject, CallbackInfoReturnable<Button> button, Button b)
	{
		ButtonAccessor accessor = (ButtonAccessor) b;

		accessor.setHoverTextOffsetX(b.textOffsetX);
		accessor.setHoverTextOffsetY(b.textOffsetY);
		if(jsonObject.has("hoverTextOffsetX"))
		{
			accessor.setHoverTextOffsetX(jsonObject.get("hoverTextOffsetX").getAsInt());
		}

		if(jsonObject.has("hoverTextOffsetY"))
		{
			accessor.setHoverTextOffsetY(jsonObject.get("hoverTextOffsetY").getAsInt());
		}

		if(jsonObject.has("anchor"))
		{
			String stringAnchor = jsonObject.get("anchor").getAsString();

			if (stringAnchor.equals("start"))
			{
				accessor.setAnchor(ANCHOR.START);
			}
			else if (stringAnchor.equals("middle"))
			{
				accessor.setAnchor(ANCHOR.MIDDLE);
			}
			else if (stringAnchor.equals("end"))
			{
				accessor.setAnchor(ANCHOR.END);
			}
		}
	}
}
