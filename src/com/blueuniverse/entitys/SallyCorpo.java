package com.blueuniverse.entitys;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.blueuniverse.main.Game;
import com.blueuniverse.main.Text;
import com.blueuniverse.world.Camera;

public class SallyCorpo extends Entity{
	
	private int maskx = 8, masky = 8, maskw = 12, maskh =12;
	public static int alcance = 0;

	public SallyCorpo(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
		// TODO Auto-generated constructor stub
	}
	
	public void tick() {
		
		depth = 0;
		maskx = 16;
		masky = 0;
		maskw = 16;
		maskh = 16;
		
		if(this.calculateDistance(this.getX(), this.getY(), Game.player.getX(), Game.player.getY()) < (16*2)) {
			Text.interage = true;
			alcance = 50;
			Text.curText = 4;
		}
		
		if(alcance >= 1) {
			alcance--;
			if(alcance <= 1) {
				Text.interage = false;
			}
		}
	}
	
	public void render(Graphics g) {
		g.drawImage(SallyCorpo.SALLYCORPO_EN, this.getX() - Camera.x, this.getY() - Camera.y, null);
	}
}
