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

public class Npc1 extends Entity{
	
	private double speed = 0.8;
	private int maskx = 8, masky = 8, maskw = 12, maskh =12;
	private int frames = 0, maxFrames = 7, index = 0, maxIndex = 3;
	private boolean alvo = false;
	public static int dirNpc = 1;
	public static int checkDialogo = 1;
	public static boolean mover = true;
	
	private BufferedImage[] sprites;
	private BufferedImage[] sprites2;
	private BufferedImage[] sprites3;
	private BufferedImage[] sprites4;
	
	private int life = 950;
	
	private boolean isDamage = false;
	private int damageFrames = 10, damageCurrent = 0;

	public Npc1(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
		// TODO Auto-generated constructor stub
		
		sprites = new BufferedImage[4];
		sprites[0] = Game.spritesheetNpcs.getSprite(0, 0, 16, 16);
		sprites[1] = Game.spritesheetNpcs.getSprite(16, 0, 16, 16);
		sprites[2] = Game.spritesheetNpcs.getSprite(16*2, 0, 16, 16);
		sprites[3] = Game.spritesheetNpcs.getSprite(16*3, 0, 16, 16);
		
		sprites2 = new BufferedImage[4];
		sprites2[0] = Game.spritesheetNpcs.getSprite(0, 16, 16, 16);
		sprites2[1] = Game.spritesheetNpcs.getSprite(16, 16, 16, 16);
		sprites2[2] = Game.spritesheetNpcs.getSprite(16*2, 16, 16, 16);
		sprites2[3] = Game.spritesheetNpcs.getSprite(16*3, 16, 16, 16);
		
		sprites3 = new BufferedImage[4];
		sprites3[0] = Game.spritesheetNpcs.getSprite(0, 16*2, 16, 16);
		sprites3[1] = Game.spritesheetNpcs.getSprite(16, 16*2, 16, 16);
		sprites3[2] = Game.spritesheetNpcs.getSprite(16*2, 16*2, 16, 16);
		sprites3[3] = Game.spritesheetNpcs.getSprite(16*3, 16*2, 16, 16);
		
		sprites4 = new BufferedImage[4];
		sprites4[0] = Game.spritesheetNpcs.getSprite(0, 16*3, 16, 16);
		sprites4[1] = Game.spritesheetNpcs.getSprite(16, 16*3, 16, 16);
		sprites4[2] = Game.spritesheetNpcs.getSprite(16*2, 16*3, 16, 16);
		sprites4[3] = Game.spritesheetNpcs.getSprite(16*3, 16*3, 16, 16);
	}
	
	public void tick() {
		
		if(this.calculateDistance(this.getX(), this.getY(), Game.player.getX(), Game.player.getY()) < 16*3 && alvo == true && checkDialogo == 1 
				&& (Game.CUR_LEVEL == 0 || Game.CUR_LEVEL == 3)) {
			Text.interage = true;
			if((Game.setZone == 1 && Game.CUR_LEVEL == 0) || (Game.setZone == 1 && Game.CUR_LEVEL == 3)) {
				Text.curText = 2;
			}
			//System.out.println("interagiu");
			//System.out.println(Text.interage);
			
		}else if(this.calculateDistance(this.getX(), this.getY(), Game.player.getX(), Game.player.getY()) > 16*3 && alvo == true){
			Text.interage = false;
			if((Game.setZone == 1 && Game.CUR_LEVEL == 0) || (Game.setZone == 1 && Game.CUR_LEVEL == 3)) {
				Text.curText = 0;
			}
		}
		
		if((this.calculateDistance(this.getX(), this.getY(), Game.player.getX(), Game.player.getY()) < 16*4)) {
			alvo = true;
		}else {
			alvo = false;
		}
		
		depth = 1;
		maskx = -8;
		masky = -8;
		maskw = 32;
		maskh = 32;
		//algoritimo inimigos burros
		
		
		/*if(this.calculateDistance(this.getX(), this.getY(), Game.player.getX(), Game.player.getY()) < 250) {
		if(!isColiddingWithPlayer()) {
			mover = true;
		if(Game.rand.nextInt(100) < 70) {
		if((int)x < Game.player.getX() && World.isFree((int)(x+speed), this.getY())
				&& !isColidding((int)(x+speed), this.getY())) {
			x+=speed;
			dirNpc = 3;
		}else if((int) x > Game.player.getX() && World.isFree((int)(x-speed), this.getY())
				&& !isColidding((int)(x-speed), this.getY())) {
			x-=speed;
			dirNpc = 4;
		}
		
		if((int)y < Game.player.getY() && World.isFree(this.getX(), (int)(y+speed))
				&& !isColidding(this.getX(), (int)(y+speed))) {
			y+=speed;
			dirNpc = 1;
		}else if((int) y > Game.player.getY() && World.isFree(this.getX(), (int)(y-speed))
				&& !isColidding(this.getX(), (int)(y-speed))) {
			y-=speed;
			dirNpc = 2;
		}
			
		}
		
		}else if(isColiddingWithPlayer()) {
			mover = false;
		}
		
	    }*/
		
		
		if(!isColiddingWithPlayer()) {
			mover = true;
			//agora algoritimo A* | inimigos inteligentes
			if(path == null || path.size() == 0) {
				Vector2i start = new Vector2i((int)(x/16), (int)(y/16));
				Vector2i end = new Vector2i((int)(Game.player.x/16), (int)(Game.player.y/16));
				path = Astar.findPath(Game.world, start, end);
			}
			}else if(isColiddingWithPlayer()) {
				mover = false;
			}
			
			if(new Random().nextInt(100) < 70)
			    followPath(path);
			if(new Random().nextInt(100) < 2) {
				Vector2i start = new Vector2i((int)(x/16), (int)(y/16));
				Vector2i end = new Vector2i((int)(Game.player.x/16), (int)(Game.player.y/16));
				path = Astar.findPath(Game.world, start, end);
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
		collidingBullet();
		
		if(life <= 0) {
			destroySelf();
			World.generateParticles(100, (int)x, (int)y);
			return;
		}
		
		if((this.calculateDistance(this.getX(), this.getY(), Game.player.getX(), Game.player.getY()) > 16*14)&&(Game.CUR_LEVEL == 1)) {
			destroySelf();
			World.generateParticles(20, (int)Game.player.getX(), (int)Game.player.getY());
			Entity npc = new Npc1(Game.player.getX(),Game.player.getY(),16,16,Game.spritesheetNpcs.getSprite(0, 0, 16, 16));
			Game.entities.add(npc);
			return;
		}
		
       collidingPunch();
		
		if(life <= 0) {
			destroySelf();
			World.generateParticles(100, (int)x, (int)y);
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
		//Sound.efect_explosao.start();
		Game.npcs1.remove(this);
		Game.entities.remove(this);
	}
	
	//colision normal with retangle
	public void collidingBullet() {
		for(int i = 0; i < Game.bullets.size(); i++) {
			Entity e = Game.bullets.get(i);
			if(e instanceof BulletShoot) {
				if(Entity.isColidding(this, e)) {
					isDamage = true;
					Sound.efect_hit.setVolume((int)-3);
					Sound.efect_hit.start();
					//life-=(Game.rand.nextInt(8));
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
					//life-=(Game.rand.nextInt(3));
					Sound.efect_hit.setVolume((int)-3);
					Sound.efect_hit.start();
					Game.punchs.remove(e);
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
		for(int i = 0; i < Game.npcs1.size(); i++) {
			Npc1 e = Game.npcs1.get(i);
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
			if(dirNpc == 1) {
				g.drawImage(sprites[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
				}
			if(dirNpc == 2) {
					g.drawImage(sprites2[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
				}
			if(dirNpc == 3) {
					g.drawImage(sprites3[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
				}
			if(dirNpc == 4) {
					g.drawImage(sprites4[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
				}
		}else {
			if(dirNpc == 1) {
				g.drawImage(Npc1.NPC1_FEEDBACK, this.getX() - Camera.x, this.getY() - Camera.y, null);
			}
			if(dirNpc == 2) {
				g.drawImage(Flocos.NPC1_FEEDBACK2, this.getX() - Camera.x, this.getY() - Camera.y, null);
			}
			if(dirNpc == 3) {
				g.drawImage(Flocos.NPC1_FEEDBACK3, this.getX() - Camera.x, this.getY() - Camera.y, null);
			}
			if(dirNpc == 4) {
				g.drawImage(Flocos.NPC1_FEEDBACK4, this.getX() - Camera.x, this.getY() - Camera.y, null);
		}
		}
		//g.setColor(new Color(0,0,200));
		//g.fillRect((this.getX() - Camera.x)+ maskx,(this.getY() - Camera.y) + masky ,maskw,maskh);
	}

}
