package com.blueuniverse.entitys;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.blueuniverse.graficos.Spritesheet;
import com.blueuniverse.main.Game;
import com.blueuniverse.main.Sound;
import com.blueuniverse.main.Text;
import com.blueuniverse.main.Tranzi;
import com.blueuniverse.world.Camera;
import com.blueuniverse.world.World;

public class Abobora extends Entity{
	
	
	private double speed = 1;
	private int maskx = 0, masky = -16, maskw = 16, maskh = 32;
	public static int frames = 0;
	public static int maxFrames = 7;
	public static int index = 0;
	public static int maxIndex = 3;
	private int inicioMovimento = 0, maxMovimento = 90;
	private boolean movimento = false;
	
	private int inicioMovimento2 = 0, maxMovimento2 = 10;
	private boolean movimento2 = false;
	
	public static int dirAbobora = 0;
	
	public boolean rigth = false, left = false;
	public boolean up = false, down = false;
	
	private BufferedImage[] sprites;
	private BufferedImage[] sprites2;
	private BufferedImage[] sprites3;
	private BufferedImage[] sprites4;
	
	public static int life = 350;
	
	private boolean isDamage = false;
	private int damageFrames = 10, damageCurrent = 0;
	
	public static double x;
	public static double y;

	public Abobora(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
		// TODO Auto-generated constructor stub
		Abobora.x = x;
		Abobora.y = y;
		
		sprites = new BufferedImage[4];
		sprites[0] = Game.spritesheetAboboras.getSprite(0, 0, 16, 32);
		sprites[1] = Game.spritesheetAboboras.getSprite(16, 0, 16, 32);
		sprites[2] = Game.spritesheetAboboras.getSprite(16*2, 0, 16, 32);
		sprites[3] = Game.spritesheetAboboras.getSprite(16*3, 0, 16, 32);
		
		sprites2 = new BufferedImage[4];
		sprites2[0] = Game.spritesheetAboboras.getSprite(0, 32, 16, 32);
		sprites2[1] = Game.spritesheetAboboras.getSprite(16, 32, 16, 32);
		sprites2[2] = Game.spritesheetAboboras.getSprite(16*2, 32, 16, 32);
		sprites2[3] = Game.spritesheetAboboras.getSprite(16*3, 32, 16, 32);
		
		sprites3 = new BufferedImage[4];
		sprites3[0] = Game.spritesheetAboboras.getSprite(0, 32*2, 16, 32);
		sprites3[1] = Game.spritesheetAboboras.getSprite(16, 32*2, 16, 32);
		sprites3[2] = Game.spritesheetAboboras.getSprite(16*2, 32*2, 16, 32);
		sprites3[3] = Game.spritesheetAboboras.getSprite(16*3, 32*2, 16, 32);
		
		sprites4 = new BufferedImage[4];
		sprites4[0] = Game.spritesheetAboboras.getSprite(0, 32*3, 16, 32);
		sprites4[1] = Game.spritesheetAboboras.getSprite(16, 32*3, 16, 32);
		sprites4[2] = Game.spritesheetAboboras.getSprite(16*2, 32*3, 16, 32);
		sprites4[3] = Game.spritesheetAboboras.getSprite(16*3, 32*3, 16, 32);
		
	}
	
	public void setX(int newX) {
		Abobora.x = newX;
	}
	
	public void setY(int newY) {
		Abobora.y = newY;
	}
	
	public int getX(){
		return (int)Abobora.x;
	}
	
	public int getY(){
		return (int)Abobora.y;
	}
	
	public void tick() {
		depth = 5;
		maskx = 0;
		masky = -16;
		maskw = 16;
		maskh = 32;
		//algoritimo inimigos burros

			//if(Game.rand.nextInt(100) < 90) {}

		
		if(!World.isFree(this.getX(), (int)(y-16)) && !isColidding(this.getX(), (int)(y-16))) {
			movimento = true;
			down = true;
		} /*else if(!World.isFree(this.getX()+1, (int)(y-16)) && !isColidding(this.getX()+1, (int)(y-16))) {
			movimento = true;
			down = true;
		} else if(!World.isFree(this.getX()-1, (int)(y-16)) && !isColidding(this.getX()-1, (int)(y-16))) {
			movimento = true;
			down = true;
		}*/
		
		if(!World.isFree(this.getX(), (int)(y+1)) && !isColidding(this.getX(), (int)(y+1))) {
			movimento = true;
			up = true;
		} /*else if(!World.isFree(this.getX()+1, (int)(y+1)) && !isColidding(this.getX()+1, (int)(y+1))) {
			movimento = true;
			up = true;
		} else if(!World.isFree(this.getX()-1, (int)(y+1)) && !isColidding(this.getX()-1, (int)(y+1))) {
			movimento = true;
			up = true;
		}*/
		
		if(!World.isFree((int)(x+1), this.getY()) && !isColidding((int)(x+1), (this.getY())) ) {
			movimento = true;
			left = true;
		}
		
		if(!World.isFree((int)(x-1), this.getY()) && !isColidding((int)(x-1), (this.getY())) ) {
			movimento = true;
			rigth = true;
		}
		
		
		if(up && movimento == true) {
			y-=speed;
			inicioMovimento++;
			if(inicioMovimento >= maxMovimento){
				up = false;
				movimento = false;
				inicioMovimento = 0;
			}
		}else if(down && movimento == true) {
			y+=speed;
			inicioMovimento++;
			if(inicioMovimento >= maxMovimento){
				down = false;
				movimento = false;
				inicioMovimento = 0;
			}
		}
		if(rigth && movimento == true) {
			x+=speed;
			inicioMovimento++;
			if(inicioMovimento >= maxMovimento){
				rigth = false;
				movimento = false;
				inicioMovimento = 0;
			}
		}else if(left && movimento == true) {
			x-=speed;
			inicioMovimento++;
			if(inicioMovimento >= maxMovimento){
				left = false;
				movimento = false;
				inicioMovimento = 0;
			}
		}
		
		
		if(movimento == false) {
			if(Game.rand.nextInt(100) > 80) {
				if(up && movimento2 == true) {
					y-=speed;
					inicioMovimento2++;
					if(inicioMovimento2 >= maxMovimento2){
						up = false;
						movimento2 = false;
						inicioMovimento2 = 0;
					}
				}else if(down && movimento2 == true) {
					y+=speed;
					inicioMovimento2++;
					if(inicioMovimento2 >= maxMovimento2){
						down = false;
						movimento2 = false;
						inicioMovimento2 = 0;
					}
				}
				if(rigth && movimento2 == true) {
					x+=speed;
					inicioMovimento2++;
					if(inicioMovimento2 >= maxMovimento2){
						rigth = false;
						movimento2 = false;
						inicioMovimento2 = 0;
					}
				}else if(left && movimento2 == true) {
					x-=speed;
					inicioMovimento2++;
					if(inicioMovimento2 >= maxMovimento2){
						left = false;
						movimento2 = false;
						inicioMovimento2 = 0;
					}
				}
			}
		}
		
		if(!World.isFree((int)(x+16), this.getY())
				&& !World.isFree((int)(x-16), this.getY())
				&& !World.isFree(this.getX(), (int)(y+16))
				&& !World.isFree(this.getX(), (int)(y+16))) {
			destroySelf();
			
		}		
		
		
		
		if(!isColiddingWithPlayer()) {
			
		if(Game.rand.nextInt(100) < 70) {
		if((int)x < Game.player.getX() && World.isFree((int)(x+speed), this.getY())
				&& !isColiddingAbobora((int)(x+speed), this.getY())) {
			x+=speed;
			dirAbobora = 0;
		}else if((int) x > Game.player.getX() && World.isFree((int)(x-speed), this.getY())
				&& !isColiddingAbobora((int)(x-speed), this.getY())) {
			x-=speed;
			dirAbobora = 1;
		}
		
		if((int)y < Game.player.getY() && World.isFree(this.getX(), (int)(y+speed))
				&& !isColiddingAbobora(this.getX(), (int)(y+speed))) {
			y+=speed;
			dirAbobora = 2;
		}else if((int) y > Game.player.getY() && World.isFree(this.getX(), (int)(y-speed))
				&& !isColiddingAbobora(this.getX(), (int)(y-speed))) {
			y-=speed;
			dirAbobora = 3;
		}
		//colidindo
		if((int)x < Game.player.getX() && World.isFree((int)(x+speed), this.getY())
				&& isColiddingAbobora((int)(x+speed), this.getY())) {
			movimento2 = true;
			left = true;
		}else if((int) x > Game.player.getX() && World.isFree((int)(x-speed), this.getY())
				&& isColiddingAbobora((int)(x-speed), this.getY())) {
			
			movimento2 = true;
			rigth = true;
		}
		
		if((int)y < Game.player.getY() && World.isFree(this.getX(), (int)(y+speed))
				&& isColiddingAbobora(this.getX(), (int)(y+speed))) {
			movimento2 = true;
			up = true;
		}else if((int) y > Game.player.getY() && World.isFree(this.getX(), (int)(y-speed))
				&& isColiddingAbobora(this.getX(), (int)(y-speed))) {
			movimento2 = true;
			down = true;
		}
			
		}
		
		}else {
			if(Game.rand.nextInt(100) < 15) {
				Game.player.isDamaged = true;
				Game.player.life-=(Game.rand.nextInt(10));
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
			if(Game.CUR_LEVEL == 2 && Game.setZone == 3) {
				Sound.diminuirTema = true;
			    Text.arteView = true;
			    Text.curText = 3;
				Game.gameState = "TEXT";
			    Text.frases = true;
			    Text.showMessage = true;
			}
			if(Game.CUR_LEVEL == 2 && Game.setZone == 5) {
				Sound.endTheme();
				Tranzi.setTranzi = 2;
				Tranzi.StartArt();
				Game.gameState = "TRANZI";
				Muriel.murielReset();
			}
			return;
		}
		
       collidingPunch();
		
		if(life <= 0) {
			destroySelf();
			if(Game.CUR_LEVEL == 2 && Game.setZone == 3) {
				Sound.diminuirTema = true;
				Text.arteView = true;
			    Text.curText = 3;
				Game.gameState = "TEXT";
			    Text.frases = true;
			    Text.showMessage = true;
			}
			if(Game.CUR_LEVEL == 2 && Game.setZone == 5) {
				Sound.endTheme();
				Tranzi.setTranzi = 2;
				Tranzi.StartArt();
				Game.gameState = "TRANZI";
				Muriel.murielReset();
			}
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
		World.generateParticles(100, (int)x, (int)y);
		Sound.efect_explosao.start();
		Game.aboboras.remove(this);
		Game.entities.remove(this);
	}
	
	//colision normal with retangle
	public void collidingBullet() {
		for(int i = 0; i < Game.bullets.size(); i++) {
			Entity e = Game.bullets.get(i);
			if(e instanceof BulletShoot) {
				if(Entity.isColiddingAlto(this, e)) {
					isDamage = true;
					if(Game.CUR_LEVEL == 1 && Game.setZone == 3){
						life = 350;
					}
					life-=(Game.rand.nextInt(8));
					Sound.efect_hit.start();
					Game.player.dano++;
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
					if(Game.CUR_LEVEL == 1 && Game.setZone == 3){
						life = 350;
					}
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
	
	public boolean isColiddingAbobora(int xnext, int ynext) {
		Rectangle enemyCurrent = new Rectangle(xnext + maskx,ynext + masky,maskw,maskh);
		for(int i = 0; i < Game.aboboras.size(); i++) {
			Abobora e = Game.aboboras.get(i);
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
			if(dirAbobora == 0) {
				g.drawImage(sprites[index],this.getX() - Camera.x,(this.getY()-16) - Camera.y,null);
			}
			if(dirAbobora == 1) {
				g.drawImage(sprites2[index],this.getX() - Camera.x,(this.getY()-16) - Camera.y,null);
			}
			if(dirAbobora == 2) {
				g.drawImage(sprites3[index],this.getX() - Camera.x,(this.getY()-16) - Camera.y,null);
			}
			if(dirAbobora == 3) {
				g.drawImage(sprites4[index],this.getX() - Camera.x,(this.getY()-16) - Camera.y,null);
			}
		}else {
			if(dirAbobora == 0) {
				g.drawImage(Abobora.ABOBORA_FEEDBACK, this.getX() - Camera.x, (this.getY()-16) - Camera.y, null);
			}else if(dirAbobora == 1) {
				g.drawImage(Abobora.ABOBORA_FEEDBACK2, this.getX() - Camera.x, (this.getY()-16) - Camera.y, null);
			}else if(dirAbobora == 2) {
				g.drawImage(Abobora.ABOBORA_FEEDBACK3, this.getX() - Camera.x, (this.getY()-16) - Camera.y, null);
			}else if(dirAbobora == 3) {
				g.drawImage(Abobora.ABOBORA_FEEDBACK4, this.getX() - Camera.x, (this.getY()-16) - Camera.y, null);
			}
			
		}
		

		//g.setColor(new Color(0,0,200));
		//g.fillRect(((this.getX()+16) - Camera.x)+ maskx,(this.getY() - Camera.y) + masky ,maskw,maskh);
	}
	
}
