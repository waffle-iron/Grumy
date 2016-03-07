package org.plexian.pendulum;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

public class Inventory {
	private ArrayList<Item> items;
	private Entity owner;
	private int maxSize;
	
	public Inventory(int size, Entity owner){
		this(size, owner, new ArrayList<Item>(size));
	}
	
	public Inventory(int size, Entity owner, ArrayList<Item> initialItems){
		this.maxSize = size;
		this.owner = owner;
		this.items = initialItems;
	}
	
	public void addItem(Item item){
		if(this.items.size() + 1 > maxSize){
			return;
		}
		
		this.items.add(item);
	}
	
	public Item getItemAt(int slot){
		return items.get(slot);
	}
	
	public void clear(){
		items.clear();
	}
	
	public boolean contains(Item item){
		for(Item i : this.items){
			if(i.getId() == item.getId()){
				return true;
			}
		}
		
		return false;
	}
	
	public void remove(Item item){
		this.items.remove(item);
	}
	
	public void render(){
		int beginningX = (Display.getWidth() / 2) - (5 * (int)Game.TILE_SIZE);
		int beginningY = (int)Game.TILE_SIZE;
		
		int itemRenderCount = 0;
		
		for(Object i : items){
			if(itemRenderCount < 10){
				Item item = (Item)i;
				int x = beginningX + (itemRenderCount * (int)Game.TILE_SIZE);
			
				if(item.getName() != "air"){
					float[] textureCoordinates = item.getTextureCoordinates();
					textureCoordinates[0] *= SpriteSheet.tileTextures.uniformSize();
					textureCoordinates[1] *= SpriteSheet.tileTextures.uniformSize();
					
					GL11.glBegin(GL11.GL_QUADS);
					GL11.glTexCoord2f(textureCoordinates[0], textureCoordinates[1] + SpriteSheet.tileTextures.uniformSize());
						GL11.glVertex2f(x, beginningY);
					GL11.glTexCoord2f(textureCoordinates[0] + SpriteSheet.tileTextures.uniformSize(), textureCoordinates[1] + SpriteSheet.tileTextures.uniformSize());
						GL11.glVertex2f(x + Game.TILE_SIZE, beginningY);
					GL11.glTexCoord2f(textureCoordinates[0] + SpriteSheet.tileTextures.uniformSize(), textureCoordinates[1]);
						GL11.glVertex2f(x + Game.TILE_SIZE, beginningY + Game.TILE_SIZE);
					GL11.glTexCoord2f(textureCoordinates[0], textureCoordinates[1]);
						GL11.glVertex2f(x, beginningY + Game.TILE_SIZE);
					GL11.glEnd();
					
				}
				
				itemRenderCount++;
			}
		}
		
		for(int i = 0; i < 10; i++){
			int x = beginningX + (i * (int)Game.TILE_SIZE);
			
			float[] textureCoordinates = Tile.ITEM_SLOT.getTextureCoordinates();
			textureCoordinates[0] *= SpriteSheet.tileTextures.uniformSize();
			textureCoordinates[1] *= SpriteSheet.tileTextures.uniformSize();
			
			GL11.glBegin(GL11.GL_QUADS);
				GL11.glTexCoord2f(textureCoordinates[0], textureCoordinates[1] + SpriteSheet.tileTextures.uniformSize());
					GL11.glVertex2f(x, beginningY);
				GL11.glTexCoord2f(textureCoordinates[0] + SpriteSheet.tileTextures.uniformSize(), textureCoordinates[1] + SpriteSheet.tileTextures.uniformSize());
					GL11.glVertex2f(x + Game.TILE_SIZE, beginningY);
				GL11.glTexCoord2f(textureCoordinates[0] + SpriteSheet.tileTextures.uniformSize(), textureCoordinates[1]);
					GL11.glVertex2f(x + Game.TILE_SIZE, beginningY + Game.TILE_SIZE);
				GL11.glTexCoord2f(textureCoordinates[0], textureCoordinates[1]);
					GL11.glVertex2f(x, beginningY + Game.TILE_SIZE);
			GL11.glEnd();
		}
	}
}
