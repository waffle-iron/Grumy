package org.plexian.pendulum;

public class EntityTextBox {
	public void drawTextBox(String text, Entity e){
		TextRenderer.drawInGameText(text, e.getLocation().getX(), e.getLocation().getY() + e.getSize().getY() + 1f, RGBA.BLACK);
	}
}
