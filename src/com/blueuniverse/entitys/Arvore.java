package com.blueuniverse.entitys;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.blueuniverse.main.Game;
import com.blueuniverse.main.Sound;
import com.blueuniverse.world.Camera;
import com.blueuniverse.world.FloorTile;
import com.blueuniverse.world.Tile;
import com.blueuniverse.world.World;

//public class Espinhos extends Entity {
public class Arvore extends Entity {
	
	private int maskx = 8, masky = 8, maskw = 12, maskh =12;
	private int life = 50;
	private boolean isDamage = false;
	private int damageFrames = 10, damageCurrent = 0;

	public Arvore(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
		// TODO Auto-generated constructor stub
	}
	
	public void tick() {
		if(!isColiddingWithPlayer()) {
		depth = 0;
		}else {
			depth = 5;
		}
		maskx = 0;
		masky = -32;
		maskw = 16;
		maskh = 32;

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
		
	}
	
	public boolean isColiddingWithPlayer() {
		Rectangle enemyCurrent = new Rectangle(this.getX() + maskx,this.getY() + masky,maskw,maskh);
		Rectangle player = new Rectangle(Game.player.getX(),Game.player.getY(),16,16);
		
		return enemyCurrent.intersects(player);
	}
	
	
	public void collidingBullet() {
		for(int i = 0; i < Game.bullets.size(); i++) {
			Entity e = Game.bullets.get(i);
			if(e instanceof BulletShoot) {
				if(Entity.isColiddingAlto(this, e)) {
					isDamage = true;
					life-=(Game.rand.nextInt(8));
					Sound.efect_hit.start();
					if(Player.setPlayer == 1) {
					Game.player.dano++;
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
	
	public void destroySelf() {
		Sound.efect_explosao.start();
		Game.arvores.remove(this);
		Game.entities.remove(this);
	}
	
	public void render(Graphics g) {
		if(!isDamage) {
			g.drawImage(Espinhos.ARVORE_EN, this.getX() - Camera.x, (this.getY()-16) - Camera.y, null);
			
		}else {
			g.drawImage(Espinhos.ARVORE_FEEDBACK, this.getX() - Camera.x, (this.getY()-16) - Camera.y, null);
		}
		
		//g.setColor(new Color(0,0,200));
		//g.fillRect(((this.getX()+16) - Camera.x)+ maskx,(this.getY() - Camera.y) + masky ,maskw,maskh);
	}

}
