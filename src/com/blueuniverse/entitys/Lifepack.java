package com.blueuniverse.entitys;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.blueuniverse.main.Game;
import com.blueuniverse.world.Camera;

public class Lifepack extends Entity{
	
	public static BufferedImage[] spritesLife;
	public int frames = 0, maxFrames = 30, index = 0, maxIndex = 3;

	public Lifepack(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
		spritesLife = new BufferedImage[4];
		spritesLife[0] = Game.spritesheet.getSprite(0, 16*6, 16, 16);
		spritesLife[1] = Game.spritesheet.getSprite(16, 16*6, 16, 16);
		spritesLife[2] = Game.spritesheet.getSprite(16*2, 16*6, 16, 16);
		spritesLife[3] = Game.spritesheet.getSprite(16*3, 16*6, 16, 16);
	}
	
	public void tick() {
		frames++;
		if(frames == maxFrames) {
			frames = 0;
			index++;
			if(index > maxIndex) 
				index = 0;
		}
	}
	
	public void render(Graphics g) {
		g.drawImage(spritesLife[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
	}
}
