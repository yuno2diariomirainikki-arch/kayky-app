package com.blueuniverse.entitys;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.blueuniverse.main.Game;
import com.blueuniverse.main.Sound;
import com.blueuniverse.world.Camera;
import com.blueuniverse.world.World;

//public class Espinhos extends Entity {
public class Espinhos extends Entity {
	
	private int maskx = 8, masky = 8, maskw = 12, maskh =12;
	private int life = 50;
	private boolean isDamage = false;
	private int damageFrames = 10, damageCurrent = 0;

	public Espinhos(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
		// TODO Auto-generated constructor stub
	}
	
	public void tick() {
		depth = 0;
		maskx = 2;
		masky = 2;
		maskw = 10;
		maskh = 10;
		
		if(isColiddingWithPlayer()) {
			if(Game.rand.nextInt(100) < 35) {
				Sound.efect_hit.start();
				Game.player.isDamaged = true;
				Game.player.life-=(Game.rand.nextInt(10));
				System.out.println(Game.player.life);
			}
		}
		
		
		collidingAbobora();
		collidingBullet();
		
		if(life <= 0) {
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
		
		//teste 3 fail
		/*if(isColiddingWithAbobora) {
			
		}*/
		
		// o inimigo abobora deve destruir os espinhos
		// inimigos normais levam dano
		/*if(isColiddingWithAbobora()){
			if(Game.rand.nextInt(100) < 35) {
				destroySelf();
				World.generateParticles(100, (int)x, (int)y);
				return;
			}
		}*/
		
	}
	
	public boolean isColiddingWithPlayer() {
		Rectangle enemyCurrent = new Rectangle(this.getX() + maskx,this.getY() + masky,maskw,maskh);
		Rectangle player = new Rectangle(Game.player.getX(),Game.player.getY(),16,16);
		
		return enemyCurrent.intersects(player);
	}
	
	
	public void collidingAbobora() {
		for(int i = 0; i < Game.aboboras.size(); i++) {
			Entity e = Game.aboboras.get(i);
			if(e instanceof Abobora) {
				if(Entity.isColidding(this, e)) {
					//Game.espinhos.remove(e);
					//System.out.println("colidiu");
					if(Game.rand.nextInt(100) < 35) {
						destroySelf();
						World.generateParticles(100, (int)x, (int)y);
						return;
					}
					return;
				}
			}
		}
	}
	
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
	
	
	
	//teste 3 fail
	/*public boolean isColiddingWithAbobora(int xnext, int ynext) {
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
	}*/
	
	
	//teste 2 fail
	/*public void isColiddingWithAbobora() {
		for(int i = 0; i < Game.entities.size(); i++) {
			Entity atual = Game.entities.get(i);
			if(atual instanceof Espinhos){
				if(Entity.isColidding(this, atual)) {
					//Game.entities.remove(atual);
					System.out.println("colidiu");
				}
			}
		}
	}*/
	
	
	//teste 1 fail
	/*public boolean isColiddingWithAbobora(int xnext, int ynext) {
		Rectangle enemyCurrent = new Rectangle(this.getX() + maskx,this.getY() + masky,maskw,maskh);
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
	}*/
	
	
	public void destroySelf() {
		Sound.efect_explosao.start();
		Game.espinhos.remove(this);
		Game.entities.remove(this);
	}
	
	public void render(Graphics g) {
		if(!isDamage) {
			g.drawImage(Espinhos.ESPINHOS_EN, this.getX() - Camera.x, this.getY() - Camera.y, null);
			
		}else {
			g.drawImage(Espinhos.ESPINHOS_FEEDBACK, this.getX() - Camera.x, this.getY() - Camera.y, null);
		}
	}

}
