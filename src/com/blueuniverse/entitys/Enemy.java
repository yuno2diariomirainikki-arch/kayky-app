package com.blueuniverse.entitys;

import java.awt.Color;
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

public class Enemy extends Entity{
	
	private double speed = 0.8;
	private int maskx = 8, masky = 8, maskw = 12, maskh =12;
	private int frames = 0, maxFrames = 7, index = 0, maxIndex = 3;
	
	private BufferedImage[] sprites;
	
	private int life = 5;
	
	private boolean isDamage = false;
	private int damageFrames = 10, damageCurrent = 0;

	public Enemy(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, null);
		sprites = new BufferedImage[4];
		sprites[0] = Game.spritesheetEnemies.getSprite(0, 0, 16, 16);
		sprites[1] = Game.spritesheetEnemies.getSprite(16, 0, 16, 16);
		sprites[2] = Game.spritesheetEnemies.getSprite(16*2, 0, 16, 16);
		sprites[3] = Game.spritesheetEnemies.getSprite(16*3, 0, 16, 16);
		// TODO Auto-generated constructor stub
	}
	
	public void tick() {
		depth = 0;
		maskx = 2;
		masky = 2;
		maskw = 10;
		maskh = 10;
		
		
		//algoritimo inimigos burros
		if(this.calculateDistance(this.getX(), this.getY(), Game.player.getX(), Game.player.getY()) < 70) {
		if(!isColiddingWithPlayer()) {
			
		if(Game.rand.nextInt(100) < 70) {
		if((int)x < Game.player.getX() && World.isFree((int)(x+speed), this.getY())
				&& !isColidding((int)(x+speed), this.getY())) {
			x+=speed;
		}else if((int) x > Game.player.getX() && World.isFree((int)(x-speed), this.getY())
				&& !isColidding((int)(x-speed), this.getY())) {
			x-=speed;
		}
		
		if((int)y < Game.player.getY() && World.isFree(this.getX(), (int)(y+speed))
				&& !isColidding(this.getX(), (int)(y+speed))) {
			y+=speed;
		}else if((int) y > Game.player.getY() && World.isFree(this.getX(), (int)(y-speed))
				&& !isColidding(this.getX(), (int)(y-speed))) {
			y-=speed;
		}
			
		}
		
		}else {
			if(Game.rand.nextInt(100) < 5) {
				Sound.efect_hit.start();
				Game.player.isDamaged = true;
				Game.player.life-=(Game.rand.nextInt(5));
				System.out.println(Game.player.life);
			}
		}
	    }
		
		/*agora algoritimo A* | inimigos inteligentes
		if(path == null || path.size() == 0) {
			Vector2i start = new Vector2i((int)(x/16), (int)(y/16));
			Vector2i end = new Vector2i((int)(Game.player.x/16), (int)(Game.player.y/16));
			path = Astar.findPath(Game.world, start, end);
		}
		}else {
			if(new Random().nextInt(100) < 5) {
				Game.player.life-=Game.rand.nextInt(5);
				Game.player.isDamaged = true;
				//System.out.println(Game.player.life);
			}
		}*/
		
		//}
		
		/*if(new Random().nextInt(100) < 40)
		    followPath(path);
		if(new Random().nextInt(100) < 2) {
			Vector2i start = new Vector2i((int)(x/16), (int)(y/16));
			Vector2i end = new Vector2i((int)(Game.player.x/16), (int)(Game.player.y/16));
			path = Astar.findPath(Game.world, start, end);
		}*/
		frames++;
		if(frames == maxFrames) {
			frames = 0;
			index++;
			if(index > maxIndex) 
				index = 0;
		}
		
		collidingBullet();
		
		if(life <= 0) {
			Game.tremer(2.5, 12);   // tremor de tela: inimigo destruido por tiro
			destroySelf();
			World.generateParticles(100, (int)x, (int)y);
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
		Sound.efect_explosao.start();
		Game.enemies.remove(this);
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
					knockbackDeBala((BulletShoot)e);
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
					life-=(Game.rand.nextInt(3)) + Game.player.dano;
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
		for(int i = 0; i < Game.enemies.size(); i++) {
			Enemy e = Game.enemies.get(i);
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
			g.drawImage(sprites[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
		}else {
			g.drawImage(Enemy.ENEMY_FEEDBACK, this.getX() - Camera.x, this.getY() - Camera.y, null);
		}
		//g.setColor(new Color(0,0,200));
		//g.fillRect((this.getX() - Camera.x)+ maskx,(this.getY() - Camera.y) + masky ,maskw,maskh);
	}
	
	
}
