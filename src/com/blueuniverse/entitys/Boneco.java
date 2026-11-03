package com.blueuniverse.entitys;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.blueuniverse.main.Game;
import com.blueuniverse.main.Sound;
import com.blueuniverse.world.Camera;
import com.blueuniverse.world.World;

public class Boneco extends Entity{
	
	private int maskx = 8, masky = 8, maskw = 12, maskh =12;
	private int life = 50;
	private boolean isDamage = false;
	private int damageFrames = 10, damageCurrent = 0;

	public Boneco(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
		// TODO Auto-generated constructor stub
	}
	
	public void tick() {
		depth = 0;
		maskx = 2;
		masky = 2;
		maskw = 10;
		maskh = 10;
		
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
	
	public void collidingBullet() {
		for(int i = 0; i < Game.bullets.size(); i++) {
			Entity e = Game.bullets.get(i);
			if(e instanceof BulletShoot) {
				if(Entity.isColidding(this, e)) {
					isDamage = true;
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
					Sound.efect_hit.start();
					Game.punchs.remove(e);
					return;
				}
			}
		}
	}
	
	
	public void destroySelf() {
		Sound.efect_explosao.start();
		Game.bonecos.remove(this);
		Game.entities.remove(this);
	}
	
	public void render(Graphics g) {
		if(!isDamage) {
			g.drawImage(Boneco.BONECO_EN, this.getX() - Camera.x, this.getY() - Camera.y, null);
			
		}else {
			g.drawImage(Boneco.BONECO_FEEDBACK, this.getX() - Camera.x, this.getY() - Camera.y, null);
		}
	}

}
