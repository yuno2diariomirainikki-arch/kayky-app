package com.blueuniverse.entitys;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.blueuniverse.main.Game;
import com.blueuniverse.main.Sound;
import com.blueuniverse.world.Camera;
import com.blueuniverse.world.World;

public class Bia extends Entity{
	private double speed = 1;
	private int maskx = 8, masky = 8, maskw = 12, maskh =12;
	public static int frames = 0;
	public static int maxFrames = 7;
	public static int index = 0;
	public static int maxIndex = 3;
	private boolean alvo = false;
	public static int dirBia = 1;
	public static boolean mover = true;
	
	private BufferedImage[] sprites;
	private BufferedImage[] sprites2;
	private BufferedImage[] sprites3;
	private BufferedImage[] sprites4;
	
	public static int especial = 0;
	public static double curEspecial = 3;
	public static double maxCurEspecial = 3;
	public static boolean especialSkill = false;
	public static boolean especialBia = false;
	public static boolean especialCura = false;
	
	public static int especialCurTempo = 0;
	public static int especialTempo = 60;
	public static int especialMaxTempo = 5;

	public static double x;
	public static double y;
	
	public Bia(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
		// TODO Auto-generated constructor stub
		Bia.x = x;
		Bia.y = y;
		
		sprites = new BufferedImage[4];
		sprites[0] = Game.spritesheetBias.getSprite(0, 0, 16, 16);
		sprites[1] = Game.spritesheetBias.getSprite(16, 0, 16, 16);
		sprites[2] = Game.spritesheetBias.getSprite(16*2, 0, 16, 16);
		sprites[3] = Game.spritesheetBias.getSprite(16*3, 0, 16, 16);
		
		sprites2 = new BufferedImage[4];
		sprites2[0] = Game.spritesheetBias.getSprite(0, 16, 16, 16);
		sprites2[1] = Game.spritesheetBias.getSprite(16, 16, 16, 16);
		sprites2[2] = Game.spritesheetBias.getSprite(16*2, 16, 16, 16);
		sprites2[3] = Game.spritesheetBias.getSprite(16*3, 16, 16, 16);
		
		sprites3 = new BufferedImage[4];
		sprites3[0] = Game.spritesheetBias.getSprite(0, 16*2, 16, 16);
		sprites3[1] = Game.spritesheetBias.getSprite(16, 16*2, 16, 16);
		sprites3[2] = Game.spritesheetBias.getSprite(16*2, 16*2, 16, 16);
		sprites3[3] = Game.spritesheetBias.getSprite(16*3, 16*2, 16, 16);
		
		sprites4 = new BufferedImage[4];
		sprites4[0] = Game.spritesheetBias.getSprite(0, 16*3, 16, 16);
		sprites4[1] = Game.spritesheetBias.getSprite(16, 16*3, 16, 16);
		sprites4[2] = Game.spritesheetBias.getSprite(16*2, 16*3, 16, 16);
		sprites4[3] = Game.spritesheetBias.getSprite(16*3, 16*3, 16, 16);
	}
	
	
	public void setX(int newX) {
		Bia.x = newX;
	}
	
	public void setY(int newY) {
		Bia.y = newY;
	}
	
	public int getX(){
		return (int)Bia.x;
	}
	
	public int getY(){
		return (int)Bia.y;
	}
	
	public void tick() {
		
		if(this.calculateDistance(this.getX(), this.getY(), Game.player.getX(), Game.player.getY()) < 16*3 && alvo == true
				&& curEspecial >= 1) {
			especialBia = true;
			especialSkill = true;
			
		}else if(this.calculateDistance(this.getX(), this.getY(), Game.player.getX(), Game.player.getY()) > 16*3 && alvo == true
				&& curEspecial >= 1){
			especialBia = true;
			especialSkill = false;
		}
		
		if(this.calculateDistance(this.getX(), this.getY(), Game.player.getX(), Game.player.getY()) < 16*5) {
			alvo = true;
		}else {
			alvo = false;
		}
		
		depth = 2;
		maskx = 2;
		masky = 2;
		maskw = 10;
		maskh = 10;
		//algoritimo inimigos burros
		
		
		if(this.calculateDistance(this.getX(), this.getY(), Game.player.getX(), Game.player.getY()) < 550) {
		if(!isColiddingWithPlayer()) {
			mover = true;
		if((int)x < Game.player.getX() && World.isFree((int)(x+speed), this.getY())
				&& !isColidding((int)(x+speed), this.getY())) {
			x+=speed;
			dirBia = 3;
		}else if((int) x > Game.player.getX() && World.isFree((int)(x-speed), this.getY())
				&& !isColidding((int)(x-speed), this.getY())) {
			x-=speed;
			dirBia = 4;
		}
		
		if((int)y < Game.player.getY() && World.isFree(this.getX(), (int)(y+speed))
				&& !isColidding(this.getX(), (int)(y+speed))) {
			y+=speed;
			dirBia = 1;
		}else if((int) y > Game.player.getY() && World.isFree(this.getX(), (int)(y-speed))
				&& !isColidding(this.getX(), (int)(y-speed))) {
			y-=speed;
			dirBia = 2;
		}
			
		
		
		}else if(isColiddingWithPlayer()) {
			mover = false;
		}
		
	    }
		
		if(mover == true) {
			frames++;
			if(frames == maxFrames) {
				frames = 0;
				index++;
				if(index > maxIndex) 
					index = 0;
			}
		}
		
		especialCurTempo++;
		if(especialCurTempo >= especialTempo) {
			especialCurTempo = 0;
			especialTempo++;
			if(especialTempo > especialMaxTempo) 
				especialTempo = 0;
			    especialCura = true;
		}
		
        if(especial >= 100 ) {
        	especial = 100;
		}
		
        
        if((this.calculateDistance(this.getX(), this.getY(), Game.player.getX(), Game.player.getY()) > 16*14)) {
			destroySelf();
			World.generateParticles(20, (int)Game.player.getX(), (int)Game.player.getY());
			Entity bia = new Bia(Game.player.getX(),Game.player.getY(),16,16,Game.spritesheetBias.getSprite(0, 0, 16, 16));
			Game.entities.add(bia);
			return;
		}
	}
	
	public void destroySelf() {
		//Sound.efect_explosao.start();
		Game.bias.remove(this);
		Game.entities.remove(this);
	}
	
	public static void Especial() {
		if(especial >= 90 && especialSkill == true && curEspecial >= 1 && especialCura == true) {
			Game.player.life += 50;
			Game.player.ammo += 50;
			Game.player.dano += 50;
			curEspecial--;
			especialCura = false;
			especialCurTempo = 0;
			especialTempo = 0;
			especial = 0;
			Sound.efect_aqua.start();
			World.generateParticles(100, Game.player.getX(), Game.player.getY());
		}
	}
	
	public static void biaReset() {
		especialCura = false;
		especialCurTempo = 0;
		especialTempo = 0;
		especial = 0;
	}
	
	public boolean isColiddingWithPlayer() {
		Rectangle enemyCurrent = new Rectangle(this.getX() + maskx,this.getY() + masky,maskw,maskh);
		Rectangle player = new Rectangle(Game.player.getX(),Game.player.getY(),16,16);
		
		return enemyCurrent.intersects(player);
	}
	
	
	
	public void render(Graphics g) {
		if(dirBia == 1) {
			g.drawImage(sprites[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
			}
			if(dirBia == 2) {
				g.drawImage(sprites2[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
			}
			if(dirBia== 3) {
				g.drawImage(sprites3[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
			}
			if(dirBia == 4) {
				g.drawImage(sprites4[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
			}
	}

}
