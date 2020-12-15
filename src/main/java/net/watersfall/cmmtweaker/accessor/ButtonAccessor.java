package net.watersfall.cmmtweaker.accessor;

import lumien.custommainmenu.lib.ANCHOR;

public interface ButtonAccessor
{
	ANCHOR getAnchor();

	void setAnchor(ANCHOR anchor);

	int getHoverTextOffsetX();

	void setHoverTextOffsetX(int offset);

	int getHoverTextOffsetY();

	void setHoverTextOffsetY(int offset);
}
