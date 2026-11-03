package com.blueuniverse.entitys;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.blueuniverse.main.Game;
import com.blueuniverse.world.Camera;

public class Bullet extends Entity{
	
	public static BufferedImage[] spritesMana;
	public int frames = 0, maxFrames = 30, index = 0, maxIndex = 1;
	
	public Bullet(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
		spritesMana = new BufferedImage[2];
		spritesMana[0] = Game.spritesheet.getSprite(0, 16*8, 16, 16);
		spritesMana[1] = Game.spritesheet.getSprite(16, 16*8, 16, 16);
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
		g.drawImage(spritesMana[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
	}
}
