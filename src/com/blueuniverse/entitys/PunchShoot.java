package com.blueuniverse.entitys;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import com.blueuniverse.main.Game;
import com.blueuniverse.world.Camera;
import com.blueuniverse.world.World;

public class PunchShoot extends Entity {

	private double dx;
	private double dy;
	private double spd = 0.03;
	private int maskx = 8, masky = 8, maskw = 8, maskh = 8;
	private int frames = 0, maxFrames = 1, index = 0, maxIndex = 3;
	
	private BufferedImage[] sprites;
	
	private int life = 50, curLife = 0, timeLife = 0, maxTimeLife = 2;
	
	public PunchShoot(int x, int y, int width, int height, BufferedImage sprite,double dx, double dy) {
		super(x, y, width, height, sprite);
		this.dx = dx;
		this.dy = dy;
		
		sprites = new BufferedImage[4];
		sprites[0] = Game.spritesheetPunch.getSprite(0, 16, 16, 16);
		sprites[1] = Game.spritesheetPunch.getSprite(16, 16, 16, 16);
		sprites[2] = Game.spritesheetPunch.getSprite(16*2, 16, 16, 16);
		sprites[3] = Game.spritesheetPunch.getSprite(16*3, 16, 16, 16);
	}
	
	public void tick() {
		depth = 1;
		x+=dx*spd;
		y+=dy*spd;
		curLife++;
		if(curLife >= life) {
			curLife = 0;
			timeLife++;
		}
		if(timeLife == maxTimeLife) {
			Game.punchs.remove(this);
			return;
		}
		
		frames++;
		if(frames == maxFrames) {
			frames = 0;
			index++;
			if(index > maxIndex) 
				index = 0;
		}
		
	}
	
	
	public void render(Graphics g) {
		
		g.setColor(Color.YELLOW);
		//g.fillOval(this.getX()-Camera.x, this.getY()-Camera.y, width, height);
		//g.fillOval(this.getX()-Camera.x, this.getY()-Camera.y, maskw, maskh);
		
		//g.drawImage(Game.spritesheetPunch.getSprite(0, 16, 16, 16), this.getX()-Camera.x, this.getY()-Camera.y, null);
		g.drawImage(sprites[index], this.getX()-Camera.x, this.getY()-Camera.y, null);
		//(new Weapon(xx*16,yy*16,16,16,Entity.WEAPON_EN));
		}
	
}
