package com.blueuniverse.entitys;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.blueuniverse.main.Game;
import com.blueuniverse.main.Sound;
import com.blueuniverse.main.Text;
import com.blueuniverse.main.Tranzi;
import com.blueuniverse.world.Camera;
import com.blueuniverse.world.World;

public class Lusion extends Entity{
	
	private double speed = 1;
	private int maskx = 0, masky = -16, maskw = 16, maskh = 32;
	public static int frames = 0;
	public static int maxFrames = 7;
	public static int index = 0;
	public static int maxIndex = 3;
	private int indexMovimento = 0, maxMovimento = 50;
	private int indexUivo = 0, Uivo = 100, maxUivo = 15;
	private static boolean movimentoFocado = false;
	private boolean movimento = false, delay = false, delayUivo = false;
	public static boolean spawnLusion = true;
	
	public static int dirLusion = 0;
	
	public boolean rigth = false, left = false;
	public boolean up = false, down = false;
	
	private BufferedImage[] sprites;
	private BufferedImage[] sprites2;
	private BufferedImage[] sprites3;
	private BufferedImage[] sprites4;
	
	private BufferedImage[] spritesHage;
	private BufferedImage[] spritesHage2;
	private BufferedImage[] spritesHage3;
	private BufferedImage[] spritesHage4;
	
	public static double life = 70;
	
	private boolean isDamage = false;
	private int damageFrames = 10, damageCurrent = 0;

	public Lusion(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
		
		sprites = new BufferedImage[4];
		sprites[0] = Game.spritesheetLusions.getSprite(0, 0, 16, 32);
		sprites[1] = Game.spritesheetLusions.getSprite(16, 0, 16, 32);
		sprites[2] = Game.spritesheetLusions.getSprite(16*2, 0, 16, 32);
		sprites[3] = Game.spritesheetLusions.getSprite(16*3, 0, 16, 32);
		
		sprites2 = new BufferedImage[4];
		sprites2[0] = Game.spritesheetLusions.getSprite(0, 32, 16, 32);
		sprites2[1] = Game.spritesheetLusions.getSprite(16, 32, 16, 32);
		sprites2[2] = Game.spritesheetLusions.getSprite(16*2, 32, 16, 32);
		sprites2[3] = Game.spritesheetLusions.getSprite(16*3, 32, 16, 32);
		
		sprites3 = new BufferedImage[4];
		sprites3[0] = Game.spritesheetLusions.getSprite(0, 32*2, 16, 32);
		sprites3[1] = Game.spritesheetLusions.getSprite(16, 32*2, 16, 32);
		sprites3[2] = Game.spritesheetLusions.getSprite(16*2, 32*2, 16, 32);
		sprites3[3] = Game.spritesheetLusions.getSprite(16*3, 32*2, 16, 32);
		
		sprites4 = new BufferedImage[4];
		sprites4[0] = Game.spritesheetLusions.getSprite(0, 32*3, 16, 32);
		sprites4[1] = Game.spritesheetLusions.getSprite(16, 32*3, 16, 32);
		sprites4[2] = Game.spritesheetLusions.getSprite(16*2, 32*3, 16, 32);
		sprites4[3] = Game.spritesheetLusions.getSprite(16*3, 32*3, 16, 32);
		
		spritesHage = new BufferedImage[4];
		spritesHage[0] = Game.spritesheetLusions.getSprite(16*5, 0, 16, 32);
		spritesHage[1] = Game.spritesheetLusions.getSprite(16*6, 0, 16, 32);
		spritesHage[2] = Game.spritesheetLusions.getSprite(16*7, 0, 16, 32);
		spritesHage[3] = Game.spritesheetLusions.getSprite(16*8, 0, 16, 32);
		
		spritesHage2 = new BufferedImage[4];
		spritesHage2[0] = Game.spritesheetLusions.getSprite(16*5, 32, 16, 32);
		spritesHage2[1] = Game.spritesheetLusions.getSprite(16*6, 32, 16, 32);
		spritesHage2[2] = Game.spritesheetLusions.getSprite(16*7, 32, 16, 32);
		spritesHage2[3] = Game.spritesheetLusions.getSprite(16*8, 32, 16, 32);
		
		spritesHage3 = new BufferedImage[4];
		spritesHage3[0] = Game.spritesheetLusions.getSprite(16*5, 32*2, 16, 32);
		spritesHage3[1] = Game.spritesheetLusions.getSprite(16*6, 32*2, 16, 32);
		spritesHage3[2] = Game.spritesheetLusions.getSprite(16*7, 32*2, 16, 32);
		spritesHage3[3] = Game.spritesheetLusions.getSprite(16*8, 32*2, 16, 32);
		
		spritesHage4 = new BufferedImage[4];
		spritesHage4[0] = Game.spritesheetLusions.getSprite(16*5, 32*3, 16, 32);
		spritesHage4[1] = Game.spritesheetLusions.getSprite(16*6, 32*3, 16, 32);
		spritesHage4[2] = Game.spritesheetLusions.getSprite(16*7, 32*3, 16, 32);
		spritesHage4[3] = Game.spritesheetLusions.getSprite(16*8, 32*3, 16, 32);
		// TODO Auto-generated constructor stub
	}
	
	public void tick() {
		depth = 5;
		maskx = 0;
		masky = 0;
		maskw = 16;
		maskh = 32;
		//algoritimo inimigos burros
		if(delayUivo == true) {
		indexUivo ++;
		if(indexUivo >= Uivo) {
			Uivo++;
			indexUivo = 0;
			if(Uivo >= maxUivo) {
				Uivo = 0;
				delayUivo = false;
			}
		}
		}
		if(this.calculateDistance(this.getX(), this.getY(), Game.player.getX(), Game.player.getY()) < 16*4 && delay == false) {
			movimento = true;
			delay = true;
			if(delayUivo == false) {
			Sound.efect_lusion.start();
			delayUivo = true;
			}
		}
		
		if(movimento == false) {
			speed = 1;
			if(Game.rand.nextInt(100) < 70) {
				if((int)x < Game.player.getX() && World.isFree((int)(x+speed), this.getY())
						&& !isColidding((int)(x+speed), this.getY())) {
					x+=speed;
					dirLusion = 0;
				}
				if((int) x > Game.player.getX() && World.isFree((int)(x-speed), this.getY())
						&& !isColidding((int)(x-speed), this.getY())) {
					x-=speed;
					dirLusion = 1;
				}
				
				if((int)y < Game.player.getY() && World.isFree(this.getX(), (int)(y+speed))
						&& !isColidding(this.getX(), (int)(y+speed))) {
					y+=speed;
					dirLusion = 2;
				}
				if((int) y > Game.player.getY() && World.isFree(this.getX(), (int)(y-speed))
						&& !isColidding(this.getX(), (int)(y-speed))) {
					y-=speed;
					dirLusion = 3;
				}
				
			}
		}else if(movimento == true){
			indexMovimento++;
			if(indexMovimento >= maxMovimento) {
				indexMovimento = 0;
				movimento = false;
				delay = false;
			}
			speed = 2;
			if(movimentoFocado == true){
			if((int)x < Game.player.getX() && World.isFree((int)(x+speed), this.getY())
					&& !isColidding((int)(x+speed), this.getY())) {
				dirLusion = 0;
				movimentoFocado = false;
			}else if((int) x > Game.player.getX() && World.isFree((int)(x-speed), this.getY())
					&& !isColidding((int)(x-speed), this.getY())) {
				dirLusion = 1;
				movimentoFocado = false;
			}else if((int)y < Game.player.getY() && World.isFree(this.getX(), (int)(y+speed))
					&& !isColidding(this.getX(), (int)(y+speed))) {
				dirLusion = 2;
				movimentoFocado = false;
			}else if((int) y > Game.player.getY() && World.isFree(this.getX(), (int)(y-speed))
					&& !isColidding(this.getX(), (int)(y-speed))) {
				dirLusion = 3;
				movimentoFocado = false;
			}
			}
			if(dirLusion == 0 && World.isFree((int)(x+speed), this.getY())) {
				x+=speed;
			}else if(dirLusion == 1 && World.isFree((int)(x-speed), this.getY())) {
				x-=speed;
			}else if(dirLusion == 2 && World.isFree(this.getX(), (int)(y+speed))) {
				y+=speed;
			}else if(dirLusion == 3 && World.isFree(this.getX(), (int)(y-speed))) {
				y-=speed;
			}
		}
		
		
		if(isColiddingWithPlayer()) {
			if(Game.rand.nextInt(100) < 15) {
				Game.player.isDamaged = true;
				if(movimento == true) {
				Game.player.life-=(Game.rand.nextInt(30));
				World.generateParticles2(100, (int)x, (int)y);
				}else if(movimento == false) {
					Game.player.life-=(Game.rand.nextInt(10));
				}
				Sound.efect_hit.start();
				//System.out.println(Game.player.life);
			}
		
		}
		
		frames++;
		if(frames == maxFrames) {
			frames = 0;
			index++;
			if(index > maxIndex) 
				index = 0;
		}
		
		collidingBullet();
		if(life <= 0) {
			destroySelf();
			
			return;
		}
		
       collidingPunch();
		if(life <= 0) {
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
	
	public static void resetSelf() {
		life = 70;
		//Lusion.spawnLusion = true;
	}
	
	public void destroySelf() {
		World.generateParticles2(100, (int)x, (int)y);
		Sound.efect_explosao.start();
		Game.lusions.remove(this);
		Game.entities.remove(this);
		resetSelf();
		if(Game.setZone == 4 && Game.CUR_LEVEL == 3) {
			Botão.apertarBotão();
		}
	}
	
	public void collidingBullet() {
		for(int i = 0; i < Game.bullets.size(); i++) {
			Entity e = Game.bullets.get(i);
			if(e instanceof BulletShoot) {
				if(Entity.isColiddingAlto(this, e)) {
					isDamage = true;
					Sound.efect_hit.start();
					if(Player.setPlayer == 1) {
						life-=(Game.rand.nextInt(8));
						Game.player.dano++;
					}else if (Player.setPlayer == 2) {
						life-=(Game.rand.nextInt(20));
					}
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
				if(Entity.isColiddingAlto(this, e)) {
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
	
	public void render(Graphics g) {
		if(!isDamage) {
			if(movimento == false) {
			if(dirLusion == 0) {
				g.drawImage(sprites[index],this.getX() - Camera.x,(this.getY()-16) - Camera.y,null);
			}
			if(dirLusion == 1) {
				g.drawImage(sprites2[index],this.getX() - Camera.x,(this.getY()-16) - Camera.y,null);
			}
			if(dirLusion == 2) {
				g.drawImage(sprites3[index],this.getX() - Camera.x,(this.getY()-16) - Camera.y,null);
			}
			if(dirLusion == 3) {
				g.drawImage(sprites4[index],this.getX() - Camera.x,(this.getY()-16) - Camera.y,null);
			}
			}else if(movimento == true) {
				if(dirLusion == 0) {
					g.drawImage(spritesHage[index],this.getX() - Camera.x,(this.getY()-16) - Camera.y,null);
				}
				if(dirLusion == 1) {
					g.drawImage(spritesHage2[index],this.getX() - Camera.x,(this.getY()-16) - Camera.y,null);
				}
				if(dirLusion == 2) {
					g.drawImage(spritesHage3[index],this.getX() - Camera.x,(this.getY()-16) - Camera.y,null);
				}
				if(dirLusion == 3) {
					g.drawImage(spritesHage4[index],this.getX() - Camera.x,(this.getY()-16) - Camera.y,null);
				}
			}
		}else {
			if(dirLusion == 0) {
				g.drawImage(Lusion.LUSION_FEEDBACK, this.getX() - Camera.x, (this.getY()-16) - Camera.y, null);
			}else if(dirLusion == 1) {
				g.drawImage(Lusion.LUSION_FEEDBACK2, this.getX() - Camera.x, (this.getY()-16) - Camera.y, null);
			}else if(dirLusion == 2) {
				g.drawImage(Lusion.LUSION_FEEDBACK3, this.getX() - Camera.x, (this.getY()-16) - Camera.y, null);
			}else if(dirLusion == 3) {
				g.drawImage(Lusion.LUSION_FEEDBACK4, this.getX() - Camera.x, (this.getY()-16) - Camera.y, null);
			}
			
		}
		
		if(Game.CUR_LEVEL == 3) {
			g.setColor(Color.red);
			g.fillRect((this.getX() - Camera.x)-2,(this.getY() - Camera.y)-20,20,1);
			g.setColor(Color.green);
			g.fillRect((this.getX() - Camera.x)-2,(this.getY() - Camera.y)-20,(int)((life/70)*20),1);
		}

		//g.setColor(new Color(0,0,200));
		//g.fillRect((this.getX() - Camera.x)+ maskx,((this.getY()-16) - Camera.y) + masky ,maskw,maskh);
	}
	

}
