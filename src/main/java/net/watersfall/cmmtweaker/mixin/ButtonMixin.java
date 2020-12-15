package net.watersfall.cmmtweaker.mixin;

import lumien.custommainmenu.configuration.Alignment;
import lumien.custommainmenu.configuration.GuiConfig;
import lumien.custommainmenu.configuration.elements.Button;
import lumien.custommainmenu.configuration.elements.Element;
import lumien.custommainmenu.lib.ANCHOR;
import lumien.custommainmenu.lib.texts.IText;
import net.watersfall.cmmtweaker.accessor.ButtonAccessor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = Button.class, remap = false)
public abstract class ButtonMixin extends Element implements ButtonAccessor
{
	public ANCHOR anchor;
	public int hoverTextOffsetX;
	public int hoverTextOffsetY;

	public ButtonMixin(GuiConfig parent)
	{
		super(parent);
	}

	@Inject(
			method = "<init>(Llumien/custommainmenu/configuration/GuiConfig;Llumien/custommainmenu/lib/texts/IText;IIIILlumien/custommainmenu/configuration/Alignment;)V",
			at = @At("RETURN")
	)
	public void addData(GuiConfig parent, IText text, int posX, int posY, int width, int height, Alignment alignment, CallbackInfo info)
	{
		this.anchor = ANCHOR.MIDDLE;
	}

	public ANCHOR getAnchor()
	{
		return this.anchor;
	}

	public void setAnchor(ANCHOR anchor)
	{
		this.anchor = anchor;
	}

	public int getHoverTextOffsetX()
	{
		return this.hoverTextOffsetX;
	}

	public void setHoverTextOffsetX(int offset)
	{
		this.hoverTextOffsetX = offset;
	}

	public int getHoverTextOffsetY()
	{
		return this.hoverTextOffsetY;
	}

	public void setHoverTextOffsetY(int offset)
	{
		this.hoverTextOffsetY = offset;
	}
}