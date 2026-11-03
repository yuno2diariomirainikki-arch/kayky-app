package com.blueuniverse.entitys;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.blueuniverse.main.Game;
import com.blueuniverse.main.Sound;
import com.blueuniverse.world.Camera;
import com.blueuniverse.world.World;

public class AtackShootMuriel extends Entity{
	private double dx;
	private double dy;
	private double spd = 2;
	private int maskx = 8, masky = 8, maskw = 4, maskh = 4;
	private int delay = 0, maxDelay = 3, frames = 0, maxFrames = 3, index = 0, maxIndex = 1;
	
	private BufferedImage[] sprites;
	
	private int life = 60, curLife = 0;
	
	public AtackShootMuriel(int x, int y, int width, int height, BufferedImage sprite,double dx, double dy) {
		super(x, y, width, height, sprite);
		this.dx = dx;
		this.dy = dy;
		
		sprites = new BufferedImage[4];
		sprites[0] = Game.spritesheetPunch.getSprite(0, 16*4, 16, 16);
		sprites[1] = Game.spritesheetPunch.getSprite(16, 16*4, 16, 16);
		sprites[2] = Game.spritesheetPunch.getSprite(16*2, 16*4, 16, 16);
		sprites[3] = Game.spritesheetPunch.getSprite(16*3, 16*4, 16, 16);
	}
	
	public void tick() {
		depth = 1;
		
		if(World.isBalaFree((int)(x+(dx*spd)),(int)( y+(dx*spd)), 3, 3)) {
			x+=dx*spd;
			y+=dy*spd;
		}else {
			//Sound.efect_explosao.start();
			Sound.efect_hit2.start();
			Game.atacksMuriel.remove(this);
			return;
		}
		
		curLife++;
		if(curLife == life) {
			Game.atacksMuriel.remove(this);
			return;
		}
		
		delay++;
		if(delay == maxDelay) {
			delay = 0;
			frames++;
			if(frames == maxFrames) {
				frames = 0;
				index++;
				if(index > maxIndex) 
					index = 0;
			}
		}
		
		
		if(isColiddingWithPlayer()) {

				Sound.efect_hit.start();
				Game.atacksMuriel.remove(this);
				Game.player.isDamaged = true;
				Game.player.life-=(Game.rand.nextInt(25));
				System.out.println(Game.player.life);
		}
		
	}
	
	public boolean isColiddingWithPlayer() {
		Rectangle enemyCurrent = new Rectangle(this.getX() + maskx,this.getY() + masky,maskw,maskh);
		Rectangle player = new Rectangle(Game.player.getX(),Game.player.getY(),16,16);
		
		return enemyCurrent.intersects(player);
	}
	
	public void render(Graphics g) {
		
		g.setColor(Color.YELLOW);
		//g.fillOval(this.getX()-Camera.x, this.getY()-Camera.y, width, height);
		//g.fillOval(this.getX()-Camera.x, this.getY()-Camera.y, maskw, maskh);
		
		//g.drawImage(Game.spritesheetPunch.getSprite(0, 16, 16, 16), this.getX()-Camera.x, this.getY()-Camera.y, null);
		g.drawImage(sprites[index], this.getX()-Camera.x, this.getY()-Camera.y, null);
		}
	
}
