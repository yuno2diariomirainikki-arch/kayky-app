package com.blueuniverse.entitys;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.blueuniverse.main.Game;
import com.blueuniverse.main.Sound;
import com.blueuniverse.world.Camera;
import com.blueuniverse.world.World;

public class BulletShoot extends Entity {
	
	private double dx;
	private double dy;
	private double spd = 4;
	private int maskx = 8, masky = 8, maskw = 4, maskh =4;
	private int frames = 0, maxFrames = 1, index = 0, maxIndex = 2;
	
	private BufferedImage[] sprites;
	
	private int life = 30, curLife = 0;
	
	public BulletShoot(int x, int y, int width, int height, BufferedImage sprite,double dx, double dy) {
		super(x, y, width, height, sprite);
		this.dx = dx;
		this.dy = dy;
		
		sprites = new BufferedImage[4];
		sprites[0] = Game.spritesheetPunch.getSprite(0, 0, 16, 16);
		sprites[1] = Game.spritesheetPunch.getSprite(16, 0, 16, 16);
		sprites[2] = Game.spritesheetPunch.getSprite(16*2, 0, 16, 16);
		sprites[3] = Game.spritesheetPunch.getSprite(16*3, 0, 16, 16);
	}
	
	public double getDx() { return dx; }
	public double getDy() { return dy; }
	
	public void tick() {
		depth = 1;
		if(World.isBalaFree((int)(x+(dx*spd)),(int)( y+(dx*spd)), 3, 3)) {
			x+=dx*spd;
			y+=dy*spd;
		}else {
			Sound.efect_explosao.setVolume((int)-10);
			Sound.efect_explosao.start();
			Game.bullets.remove(this);
			World.generateParticles(100, (int)x, (int)y);
			return;
		}
		curLife++;
		if(curLife == life) {
			Game.bullets.remove(this);
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
		g.drawImage(sprites[index], (this.getX()-Camera.x)-4, (this.getY()-Camera.y)-4, null);
			
	}
	
	
	
}
