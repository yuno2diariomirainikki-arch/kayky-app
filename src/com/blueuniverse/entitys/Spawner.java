package com.blueuniverse.entitys;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.blueuniverse.main.Game;

public class Spawner extends Entity{
	
	private int timer = 60 + (60*9);
	private int curTimer = 0;

	public Spawner(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
		// TODO Auto-generated constructor stub
	}
	
	public void tick() {
		curTimer++;
		if(curTimer >= timer) {
			curTimer = 0;
			
			Bullet pack = new Bullet((int)x,(int)y,16,16,Entity.BULLET_EN);
			Game.entities.add(pack);
			//Game.entities.add(new Bullet((int)x,(int)y,16,16,Entity.BULLET_EN));
		}
	}
	
	public void render(Graphics g) {
		//g.setColor(Color.red);
		//g.fillRect((int)x, (int)y, width, height);
	}
}
