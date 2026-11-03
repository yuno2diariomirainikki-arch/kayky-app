package com.blueuniverse.entitys;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.blueuniverse.main.Game;
import com.blueuniverse.world.Camera;

public class PortaEsquerda extends Entity {
	
	private int maskx = 8, masky = 8, maskw = 12, maskh =12;

	public PortaEsquerda(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
		// TODO Auto-generated constructor stub
	}
	
	public void tick() {
		if(!isColiddingWithPlayer()) {
		depth = 1;
		}else {
			depth = 3;
		}
		maskx = 0;
		masky = -32;
		maskw = 16;
		maskh = 32;
		
	}


	public boolean isColiddingWithPlayer() {
		Rectangle enemyCurrent = new Rectangle(this.getX() + maskx,this.getY() + masky,maskw,maskh);
		Rectangle player = new Rectangle(Game.player.getX(),Game.player.getY(),16,16);
		
		return enemyCurrent.intersects(player);
	}

	
	public void render(Graphics g) {
		g.drawImage(PortaDireita.PORTA_ESQUERDA, this.getX() - Camera.x, (this.getY()-16) - Camera.y, null);
			
		
		//g.setColor(new Color(0,0,200));
		//g.fillRect(((this.getX()+16) - Camera.x)+ maskx,(this.getY() - Camera.y) + masky ,maskw,maskh);
	}

}