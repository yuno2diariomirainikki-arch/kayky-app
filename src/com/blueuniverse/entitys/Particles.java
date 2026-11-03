package com.blueuniverse.entitys;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import com.blueuniverse.main.Game;
import com.blueuniverse.main.Sound;
import com.blueuniverse.world.Camera;

public class Particles extends Entity{
	
	private static final Color COLOR = new Color(0,255,144);
	
	public int lifeTime = 10;
	public  int curLife = 0;
	
	public int spd = 2;
	public double dx = 0;
	public double dy = 0;

	public Particles(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
		// TODO Auto-generated constructor stub
		
		dx = Game.rand.nextGaussian();
		dy = Game.rand.nextGaussian();
	}
	
	public void tick() {
		x+=dx*spd;
		y+=dy*spd;
		curLife++;
		if(lifeTime<=curLife) {
			Game.entities.remove(this);
		}
		
	}
	
	
	public void render(Graphics g) {
		g.setColor(COLOR);
		//g.fillOval(this.getX()-Camera.x, this.getY()-Camera.y, width, height);
		g.fillRect(this.getX()-Camera.x, this.getY()-Camera.y, width, height);
	}
}
