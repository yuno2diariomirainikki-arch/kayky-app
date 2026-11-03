package com.blueuniverse.entitys;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.blueuniverse.main.Game;
import com.blueuniverse.world.Camera;

public class Weapon extends Entity{
	
	public static BufferedImage[] spritesBullet;
	public int frames = 0, maxFrames = 7, index = 0, maxIndex = 3;

	public Weapon(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
		spritesBullet = new BufferedImage[4];
		spritesBullet[0] = Game.spritesheet.getSprite(16, 16*7, 16, 16);
		spritesBullet[1] = Game.spritesheet.getSprite(16*2, 16*7, 16, 16);
		spritesBullet[2] = Game.spritesheet.getSprite(16*3, 16*7, 16, 16);
		spritesBullet[3] = Game.spritesheet.getSprite(16*4, 16*7, 16, 16);
		// TODO Auto-generated constructor stub
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
		g.drawImage(spritesBullet[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
	}
}
