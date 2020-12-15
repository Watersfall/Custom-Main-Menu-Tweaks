package net.watersfall.cmmtweaker.mixin;

import lumien.custommainmenu.configuration.elements.Button;
import lumien.custommainmenu.gui.GuiCustomButton;
import lumien.custommainmenu.lib.ANCHOR;
import lumien.custommainmenu.lib.StringReplacer;
import lumien.custommainmenu.lib.textures.ITexture;
import lumien.custommainmenu.util.RenderUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.watersfall.cmmtweaker.accessor.ButtonAccessor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(GuiCustomButton.class)
public abstract class GuiCustomButtonMixin extends GuiButton
{
	@Shadow public Button b;
	@Shadow ITexture texture;
	@Shadow int normalText;
	@Shadow int hoverText;

	@Shadow public abstract void drawCenteredString(FontRenderer fontRendererIn, String text, int x, int y, int color, boolean shadow);

	public GuiCustomButtonMixin(int buttonId, int x, int y, String buttonText)
	{
		super(buttonId, x, y, buttonText);
	}

	/**
	 * @author Watersfall
	 */
	@Overwrite
	public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks)
	{
		if (this.visible)
		{
			if (this.b.name.equals("language") && this.texture == null)
			{
				mc.getTextureManager().bindTexture(GuiButton.BUTTON_TEXTURES);
				GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
				boolean hovering = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
				int k = 106;

				if (hovering)
				{
					k += this.height;
				}

				this.drawTexturedModalRect(this.x, this.y, 0, k, this.width, this.height);
				return;
			}
			FontRenderer fontrenderer = mc.fontRenderer;
			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

			boolean newHovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;

			if (newHovered && !this.hovered && b.hoverSound != null)
			{
				Minecraft.getMinecraft().getSoundHandler().playSound(new PositionedSoundRecord(new ResourceLocation(b.hoverSound), SoundCategory.MASTER, 1F, 1F, false, 0, ISound.AttenuationType.NONE, 0, 0, 0));
			}

			this.hovered = newHovered;
			int k = this.getHoverState(this.hovered);
			GlStateManager.enableBlend();
			GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
			GlStateManager.blendFunc(770, 771);

			if (this.texture != null)
			{
				texture.bind();

				RenderUtil.drawPartialImage(this.x, this.y, 0, (k - 1) * b.imageHeight, b.width, b.height, b.imageWidth, b.imageHeight);
			}
			else
			{
				mc.getTextureManager().bindTexture(BUTTON_TEXTURES);
				this.drawTexturedModalRect(this.x, this.y, 0, 46 + k * 20, this.width / 2, this.height);
				this.drawTexturedModalRect(this.x + this.width / 2, this.y, 200 - this.width / 2, 46 + k * 20, this.width / 2, this.height);
			}

			this.mouseDragged(mc, mouseX, mouseY);
			int l = normalText;

			if (packedFGColour != 0)
			{
				l = packedFGColour;
			}
			else if (!this.enabled)
			{
				l = 10526880;
			}
			else if (this.hovered)
			{
				l = hoverText;
			}
			ButtonAccessor a = (ButtonAccessor)b;
			String text = hovered ?
					I18n.format(StringReplacer.replacePlaceholders(b.hoverText.get()), new Object[0]) :
					I18n.format(StringReplacer.replacePlaceholders(b.text.get()));
			int offsetX = hovered ? a.getHoverTextOffsetX() : b.textOffsetX;
			int offsetY = hovered ? a.getHoverTextOffsetY() : b.textOffsetY;
			if(a.getAnchor() == ANCHOR.MIDDLE)
			{
				this.drawCenteredString(fontrenderer, text, this.x + this.width / 2 + offsetX, this.y + (this.height - 8) / 2 + offsetY, l, b.shadow);
			}
			else if(a.getAnchor() == ANCHOR.START)
			{
				this.drawString(fontrenderer, text, this.x + offsetX, this.y + (this.height - 8) / 2 + offsetY, l);
			}
			else
			{
				this.drawString(fontrenderer, text, this.x + this.width - fontrenderer.getStringWidth(text) + offsetX, this.y + (this.height - 8) / 2 + offsetY, l);
			}
		}
	}
}
