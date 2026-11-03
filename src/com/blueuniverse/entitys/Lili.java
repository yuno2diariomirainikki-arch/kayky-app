package com.blueuniverse.entitys;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import com.blueuniverse.main.Game;
import com.blueuniverse.main.Sound;
import com.blueuniverse.main.Text;
import com.blueuniverse.world.Astar;
import com.blueuniverse.world.Camera;
import com.blueuniverse.world.Vector2i;
import com.blueuniverse.world.World;

public class Lili extends Entity{

	private double speed = 1.0;
	private int maskx = 8, masky = 8, maskw = 12, maskh =12;
	private int frames = 0, maxFrames = 7, index = 0, maxIndex = 3;
	public static int dirLili = 1;
	public static boolean moved = false;
	
	private BufferedImage[] sprites;
	private BufferedImage[] sprites2;
	private BufferedImage[] sprites3;
	private BufferedImage[] sprites4;
	
	public static int life = 950;
	
	private boolean isDamage = false;
	private int damageFrames = 10, damageCurrent = 0;
	
	private boolean alvoLili = false;
	public static boolean LiliCheck = true;

	public Lili(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, null);
		sprites = new BufferedImage[4];
		sprites[0] = Game.spritesheetLilis.getSprite(0, 0, 16, 16);
		sprites[1] = Game.spritesheetLilis.getSprite(16, 0, 16, 16);
		sprites[2] = Game.spritesheetLilis.getSprite(16*2, 0, 16, 16);
		sprites[3] = Game.spritesheetLilis.getSprite(16*3, 0, 16, 16);
		
		sprites2 = new BufferedImage[4];
		sprites2[0] = Game.spritesheetLilis.getSprite(0, 16, 16, 16);
		sprites2[1] = Game.spritesheetLilis.getSprite(16, 16, 16, 16);
		sprites2[2] = Game.spritesheetLilis.getSprite(16*2, 16, 16, 16);
		sprites2[3] = Game.spritesheetLilis.getSprite(16*3, 16, 16, 16);
		
		sprites3 = new BufferedImage[4];
		sprites3[0] = Game.spritesheetLilis.getSprite(0, 16*2, 16, 16);
		sprites3[1] = Game.spritesheetLilis.getSprite(16, 16*2, 16, 16);
		sprites3[2] = Game.spritesheetLilis.getSprite(16*2, 16*2, 16, 16);
		sprites3[3] = Game.spritesheetLilis.getSprite(16*3, 16*2, 16, 16);
		
		sprites4 = new BufferedImage[4];
		sprites4[0] = Game.spritesheetLilis.getSprite(0, 16*3, 16, 16);
		sprites4[1] = Game.spritesheetLilis.getSprite(16, 16*3, 16, 16);
		sprites4[2] = Game.spritesheetLilis.getSprite(16*2, 16*3, 16, 16);
		sprites4[3] = Game.spritesheetLilis.getSprite(16*3, 16*3, 16, 16);
		// TODO Auto-generated constructor stub
	}
		
	public void tick() {
		depth = 1;
		maskx = 2;
		masky = 2;
		maskw = 10;
		maskh = 10;
		
		if(this.calculateDistance(this.getX(), this.getY(), Game.player.getX(), Game.player.getY()) < 16*7) {
			alvoLili = true;
		}else {
			alvoLili = false;
		}
		
		if((Game.CUR_LEVEL == 0) && LiliCheck == true) {
			
			if(this.calculateDistance(this.getX(), this.getY(), Game.player.getX(), Game.player.getY()) < 16*5 && alvoLili == true) {
				Text.arteView = true;
				Text.curText = 2;
				Game.gameState = "TEXT";
				Sound.diminuirTema = true;
			    Text.frases = true;
			    Text.showMessage = true;	
			}
		}else if((Game.CUR_LEVEL == 0) && LiliCheck == false) {
			if(this.calculateDistance(this.getX(), this.getY(), Game.player.getX(), Game.player.getY()) < 16*4 && alvoLili == true) {
				Text.interage = true;
				Text.curText = 4;	
			}else {
				Text.interage = false;
			}
		}
		
		
		if(!isColiddingWithPlayer()) {
			if(Game.rand.nextInt(100) < 70) {
				if(alvoLili == true) {
					moved = true;
					if(Game.rand.nextInt(100) < 70) {
					if((int)x < Game.player.getX() && World.isFree((int)(x+speed), this.getY())
							&& !isColidding((int)(x+speed), this.getY())) {
						dirLili = 3;
						x+=speed;
					}else if((int) x > Game.player.getX() && World.isFree((int)(x-speed), this.getY())
							&& !isColidding((int)(x-speed), this.getY())) {
						dirLili = 4;
						x-=speed;
					}
					
					if((int)y < Game.player.getY() && World.isFree(this.getX(), (int)(y+speed))
							&& !isColidding(this.getX(), (int)(y+speed))) {
						dirLili = 1;
						y+=speed;
					}else if((int) y > Game.player.getY() && World.isFree(this.getX(), (int)(y-speed))
							&& !isColidding(this.getX(), (int)(y-speed))) {
						dirLili = 2;
						y-=speed;
					}
						
					}
					}else {
						moved = false;
					}
					
				}
		}
		
		if(moved == true) {
		frames++;
		if(frames == maxFrames) {
			frames = 0;
			index++;
			if(index > maxIndex) 
				index = 0;
		}
		}
		
		
		collidingBullet();
		
		if(life <= 0) {
			World.generateParticles(100, (int)x, (int)y);
			    destroySelf();
			return;
		}
		
       collidingPunch();
		
		if(life <= 0) {
			World.generateParticles(100, (int)x, (int)y);
			    destroySelf();	
			return;
		}
		
		if(isDamage) {
			this.damageCurrent++;
			if(this.damageCurrent >= this.damageFrames) {
				this.damageCurrent = 0;
				this.isDamage = false;
			}
		}
	}
	
	public void destroySelf() {
		Sound.efect_explosao.start();
		Game.lilis.remove(this);
		Game.entities.remove(this);
	}
	
	//colision normal with retangle
	public void collidingBullet() {
		for(int i = 0; i < Game.bullets.size(); i++) {
			Entity e = Game.bullets.get(i);
			if(e instanceof BulletShoot) {
				if(Entity.isColidding(this, e)) {
					isDamage = true;
					//life-=(Game.rand.nextInt(8));
					Sound.efect_hit.start();
					//Game.player.dano++;
					Game.bullets.remove(e);
					return;
				}
			}
		}
	}
	
	public void collidingPunch() {
		for(int i = 0; i < Game.punchs.size(); i++) {
			Entity e = Game.punchs.get(i);
			if(e instanceof PunchShoot) {
				if(Entity.isColidding(this, e)) {
					isDamage = true;
					life-=(Game.rand.nextInt(3))+ Game.player.dano;
					Sound.efect_hit.start();
					Game.punchs.remove(e);
					Game.player.dano-=10;
					return;
				}
			}
		}
	}
	
	public boolean isColiddingWithPlayer() {
		Rectangle enemyCurrent = new Rectangle(this.getX() + maskx,this.getY() + masky,maskw,maskh);
		Rectangle player = new Rectangle(Game.player.getX(),Game.player.getY(),16,16);
		
		return enemyCurrent.intersects(player);
	}
	
	
	


	public boolean isColidding(int xnext, int ynext) {
		Rectangle enemyCurrent = new Rectangle(xnext + maskx,ynext + masky,maskw,maskh);
		for(int i = 0; i < Game.lilis.size(); i++) {
			Lili e = Game.lilis.get(i);
			if(e == this)
				continue;
			Rectangle targetEnemy = new Rectangle(e.getX() + maskx,e.getY() + masky,maskw,maskh);
			if(enemyCurrent.intersects(targetEnemy)) {
				return true;
			}
			
		}
		
		return false;
	}
	
	public void render(Graphics g) {
		if(!isDamage) {
			if(dirLili == 1) {
			g.drawImage(sprites[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
			}
			if(dirLili == 2) {
				g.drawImage(sprites2[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
			}
			if(dirLili == 3) {
				g.drawImage(sprites3[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
			}
			if(dirLili == 4) {
				g.drawImage(sprites4[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
			}
		}else {
			if(dirLili == 2) {
				g.drawImage(Flocos.LILI_FEEDBACK, this.getX() - Camera.x, this.getY() - Camera.y, null);
			}
			if(dirLili == 1) {
				g.drawImage(Flocos.LILI_FEEDBACK2, this.getX() - Camera.x, this.getY() - Camera.y, null);
			}
			if(dirLili == 3) {
				g.drawImage(Flocos.LILI_FEEDBACK3, this.getX() - Camera.x, this.getY() - Camera.y, null);
			}
			if(dirLili == 4) {
				g.drawImage(Flocos.LILI_FEEDBACK4, this.getX() - Camera.x, this.getY() - Camera.y, null);
			}
		}
		//g.setColor(new Color(0,0,200));
		//g.fillRect((this.getX() - Camera.x)+ maskx,(this.getY() - Camera.y) + masky ,maskw,maskh);
	}
	
}