package com.blueuniverse.entitys;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import com.blueuniverse.graficos.Spritesheet;
import com.blueuniverse.main.Game;
import com.blueuniverse.main.Sound;
import com.blueuniverse.main.Text;
import com.blueuniverse.main.Tranzi;
import com.blueuniverse.world.Astar;
import com.blueuniverse.world.Camera;
import com.blueuniverse.world.Vector2i;
import com.blueuniverse.world.World;

public class Killer extends Entity{
	
	private double speed = 1.2;
	private int maskx = 8, masky = 8, maskw = 12, maskh =12;
	private int frames = 0, maxFrames = 7, index = 0, maxIndex = 3;
	public static int dirKiller = 1;
	
	private BufferedImage[] sprites;
	private BufferedImage[] sprites2;
	private BufferedImage[] sprites3;
	private BufferedImage[] sprites4;
	
	public static double life = 350;
	public static boolean killerBatle = false;
	public static boolean killerVivo = true;
	
	private boolean isDamage = false;
	private int damageFrames = 10, damageCurrent = 0;
	
	private boolean alvoKiller = false;
	public static boolean killerCheck = true;

	public Killer(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, null);
		sprites = new BufferedImage[4];
		sprites[0] = Game.spritesheetKiller.getSprite(0, 0, 16, 16);
		sprites[1] = Game.spritesheetKiller.getSprite(16, 0, 16, 16);
		sprites[2] = Game.spritesheetKiller.getSprite(16*2, 0, 16, 16);
		sprites[3] = Game.spritesheetKiller.getSprite(16*3, 0, 16, 16);
		
		sprites2 = new BufferedImage[4];
		sprites2[0] = Game.spritesheetKiller.getSprite(0, 16, 16, 16);
		sprites2[1] = Game.spritesheetKiller.getSprite(16, 16, 16, 16);
		sprites2[2] = Game.spritesheetKiller.getSprite(16*2, 16, 16, 16);
		sprites2[3] = Game.spritesheetKiller.getSprite(16*3, 16, 16, 16);
		
		sprites3 = new BufferedImage[4];
		sprites3[0] = Game.spritesheetKiller.getSprite(0, 16*2, 16, 16);
		sprites3[1] = Game.spritesheetKiller.getSprite(16, 16*2, 16, 16);
		sprites3[2] = Game.spritesheetKiller.getSprite(16*2, 16*2, 16, 16);
		sprites3[3] = Game.spritesheetKiller.getSprite(16*3, 16*2, 16, 16);
		
		sprites4 = new BufferedImage[4];
		sprites4[0] = Game.spritesheetKiller.getSprite(0, 16*3, 16, 16);
		sprites4[1] = Game.spritesheetKiller.getSprite(16, 16*3, 16, 16);
		sprites4[2] = Game.spritesheetKiller.getSprite(16*2, 16*3, 16, 16);
		sprites4[3] = Game.spritesheetKiller.getSprite(16*3, 16*3, 16, 16);
		// TODO Auto-generated constructor stub
	}
		
	public void tick() {
		depth = 3;
		maskx = 2;
		masky = 2;
		maskw = 10;
		maskh = 10;
		
		if(((Game.CUR_LEVEL >= 0 && Game.CUR_LEVEL <= 2) && Game.setZone == 2) && killerCheck == true) {
			if(this.calculateDistance(this.getX(), this.getY(), Game.player.getX(), Game.player.getY()) < 16*3) {
				alvoKiller = true;
			}else {
				alvoKiller = false;
			}
			
			if(this.calculateDistance(this.getX(), this.getY(), Game.player.getX(), Game.player.getY()) < 16*3 && alvoKiller == true) {
				if((Game.CUR_LEVEL == 0 || Game.CUR_LEVEL == 2) && Game.setZone == 2) {
					Sound.diminuirTema = true;
					Text.arteView = true;
					Text.curText = 1;
					Game.gameState = "TEXT";
				    Text.frases = true;
				    Text.showMessage = true;
				    Text.xArte = 0;
				}
				if(Game.CUR_LEVEL == 1) {
					Sound.diminuirTema = true;
					Text.arteView = true;
					Text.curText = 2;
					Game.gameState = "TEXT";
				    Text.frases = true;
				    Text.showMessage = true;
				    Text.xArte = 0;
				}
			}
		}
		
		
		/*if((int)y < Game.player.getY()-48 && World.isFree((int)(x+speed), this.getY())
				&& !isColidding((int)(x+speed), this.getY())) {
			//dirKiller = 1;
		}else if((int) y > Game.player.getY()+48 && World.isFree((int)(x-speed), this.getY())
				&& !isColidding((int)(x-speed), this.getY())) {
			//dirKiller = 2;
		}else if((int)x < Game.player.getX()-48 && World.isFree(this.getX(), (int)(y+speed))
				&& !isColidding(this.getX(), (int)(y+speed))) {
			//dirKiller = 3;
		}else if((int) x > Game.player.getX()+48 && World.isFree(this.getX(), (int)(y-speed))
				&& !isColidding(this.getX(), (int)(y-speed))) {
			//dirKiller = 4;
		}*/
		
		
		//algoritimo inimigos burros
		
		if(!isColiddingWithPlayer()) {
		
		//agora algoritimo A* | inimigos inteligentes
		if(path == null || path.size() == 0) {
			Vector2i start = new Vector2i((int)(x/16), (int)(y/16));
			Vector2i end = new Vector2i((int)(Game.player.x/16), (int)(Game.player.y/16));
			path = Astar.findPath(Game.world, start, end);
		}
		}else {
			if(new Random().nextInt(100) < 5) {
				Sound.efect_hit.start();
				Game.player.life-=Game.rand.nextInt(16);
				Game.player.isDamaged = true;
			}
		}
		
		if(new Random().nextInt(100) < 70)
		    followPath(path);
		if(new Random().nextInt(100) < 2) {
			Vector2i start = new Vector2i((int)(x/16), (int)(y/16));
			Vector2i end = new Vector2i((int)(Game.player.x/16), (int)(Game.player.y/16));
			path = Astar.findPath(Game.world, start, end);
		}
		frames++;
		if(frames == maxFrames) {
			frames = 0;
			index++;
			if(index > maxIndex) 
				index = 0;
		}
		
		collidingBullet();
		
		if(life <= 0 && killerVivo == true) {
			destroySelf();
			World.generateParticles(100, (int)x, (int)y);
			Player.proximoLevelBoos = true;
			
			if(Game.CUR_LEVEL == 2) {
				    destroySelf();
			}
			return;
		}
		
       collidingPunch();
		
		if(life <= 0 && killerVivo == true) {
			destroySelf();
			World.generateParticles(100, (int)x, (int)y);
			Player.proximoLevelBoos = true;
			if(Game.CUR_LEVEL == 2) {
				    destroySelf();
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
			killerVivo = false;
			World.generateParticles(100, (int)x, (int)y);
			Tranzi.setTranzi = 3;
			Tranzi.StartArt();
			Game.gameState = "TRANZI";
			Game.killers.remove(this);
			Game.entities.remove(this);
	}
	
	//colision normal with retangle
	public void collidingBullet() {
		for(int i = 0; i < Game.bullets.size(); i++) {
			Entity e = Game.bullets.get(i);
			if(e instanceof BulletShoot) {
				if(Entity.isColidding(this, e)) {
					isDamage = true;
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
		for(int i = 0; i < Game.killers.size(); i++) {
			Killer e = Game.killers.get(i);
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
			if(dirKiller == 1) {
			g.drawImage(sprites[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
			}
			if(dirKiller == 2) {
				g.drawImage(sprites2[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
			}
			if(dirKiller == 3) {
				g.drawImage(sprites3[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
			}
			if(dirKiller == 4) {
				g.drawImage(sprites4[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
			}
		}else {
			if(dirKiller == 1) {
				g.drawImage(Flocos.KILLER_FEEDBACK, this.getX() - Camera.x, this.getY() - Camera.y, null);
			}
			if(dirKiller == 2) {
				g.drawImage(Flocos.KILLER_FEEDBACK2, this.getX() - Camera.x, this.getY() - Camera.y, null);
			}
			if(dirKiller == 3) {
				g.drawImage(Flocos.KILLER_FEEDBACK3, this.getX() - Camera.x, this.getY() - Camera.y, null);
			}
			if(dirKiller == 4) {
				g.drawImage(Flocos.KILLER_FEEDBACK4, this.getX() - Camera.x, this.getY() - Camera.y, null);
			}
		}
		//g.setColor(new Color(0,0,200));
		//g.fillRect((this.getX() - Camera.x)+ maskx,(this.getY() - Camera.y) + masky ,maskw,maskh);
				// (barra de vida sobre a cabeca removida: agora a vida do chefe fica no HUD)
	}
	
}
