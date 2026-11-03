package com.blueuniverse.entitys;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.blueuniverse.main.Game;
import com.blueuniverse.main.Sound;
import com.blueuniverse.world.Camera;
import com.blueuniverse.world.World;

public class Flocos extends Entity{

	private double speed = 0.5;
	private int maskx = 8, masky = 8, maskw = 12, maskh =12;
	private int frames = 0, maxFrames = 7, index = 0, maxIndex = 3;
	private int inicioMovimento = 0, maxMovimento = 100;
	private boolean alvo = false;
	
	public boolean rigth = true, left = false;
	public boolean up = true, down = false;
	
	private BufferedImage[] sprites;
	private BufferedImage[] sprites2;
	private BufferedImage[] sprites3;
	private BufferedImage[] sprites4;
	
	private int life = 50;
	
	// ---- habilidade: DASH em linha reta a cada 15 segundos ----
	public static final int DASH_COOLDOWN = 60*15;   // 15 s
	public static final int DASH_DANO_MIN = 10;      // dano do dash: aleatorio entre 10 e 20
	public static final int DASH_DANO_MAX = 20;
	private static final double DASH_VEL = 4.6;
	private int dashEstado = 0;                      // 0 normal, 1 preparando (aviso), 2 dando o dash
	private int dashCooldown = 240 + Game.rand.nextInt(240);   // o primeiro dash vem em 4 a 8 s
	private int dashPreparo = 0, dashTicks = 0;
	private double dashDx = 0, dashDy = 0;
	private boolean dashAcertou = false;
	
	public int getDashEstado() { return dashEstado; }
	private int dir = 1;
	
	private boolean isDamage = false;
	private int damageFrames = 10, damageCurrent = 0;

	public Flocos(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, null);
		sprites = new BufferedImage[4];
		sprites[0] = Game.spritesheetFlocos.getSprite(0, 0, 16, 16);
		sprites[1] = Game.spritesheetFlocos.getSprite(16, 0, 16, 16);
		sprites[2] = Game.spritesheetFlocos.getSprite(16*2, 0, 16, 16);
		sprites[3] = Game.spritesheetFlocos.getSprite(16*3, 0, 16, 16);
		
		sprites2 = new BufferedImage[4];
		sprites2[0] = Game.spritesheetFlocos.getSprite(0, 16, 16, 16);
		sprites2[1] = Game.spritesheetFlocos.getSprite(16, 16, 16, 16);
		sprites2[2] = Game.spritesheetFlocos.getSprite(16*2, 16, 16, 16);
		sprites2[3] = Game.spritesheetFlocos.getSprite(16*3, 16, 16, 16);
		
		sprites3 = new BufferedImage[4];
		sprites3[0] = Game.spritesheetFlocos.getSprite(0, 16*2, 16, 16);
		sprites3[1] = Game.spritesheetFlocos.getSprite(16, 16*2, 16, 16);
		sprites3[2] = Game.spritesheetFlocos.getSprite(16*2, 16*2, 16, 16);
		sprites3[3] = Game.spritesheetFlocos.getSprite(16*3, 16*2, 16, 16);
		
		sprites4 = new BufferedImage[4];
		sprites4[0] = Game.spritesheetFlocos.getSprite(0, 16*3, 16, 16);
		sprites4[1] = Game.spritesheetFlocos.getSprite(16, 16*3, 16, 16);
		sprites4[2] = Game.spritesheetFlocos.getSprite(16*2, 16*3, 16, 16);
		sprites4[3] = Game.spritesheetFlocos.getSprite(16*3, 16*3, 16, 16);
		
		// TODO Auto-generated constructor stub
	}
	
	public void tick() {
		depth = 0;
		maskx = 2;
		masky = 2;
		maskw = 16;
		maskh = 16;
		boolean ocupado = tickDash();
		if(!ocupado) {
		//algoritimo inimigos burros
		
		
		if(alvo == false) {

		if(Game.rand.nextInt(100) < 30) {
		if(rigth && World.isFree((int)(x+speed), this.getY())) {
			x+=speed;
			dir = 2;
			inicioMovimento++;
			if(inicioMovimento >= maxMovimento){
				rigth = false;
				left = true;
				inicioMovimento = 0;
			}
		}
		if(left && World.isFree((int)(x-speed), this.getY())) {
			x-=speed;
			dir = 1;
			inicioMovimento++;
			if(inicioMovimento >= maxMovimento){
				rigth = true;
				left = false;
				inicioMovimento = 0;
			}
		}
		if(Game.rand.nextInt(100) < 50) {
		if(up && World.isFree(this.getX(), (int)(y+speed))) {
			y+=speed;
			inicioMovimento++;
			if(inicioMovimento >= maxMovimento){
				up = false;
				down = true;
				inicioMovimento = 0;
			}
		}
		if(down && World.isFree(this.getX(), (int)(y-speed))) {
			y-=speed;
			inicioMovimento++;
			if(inicioMovimento >= maxMovimento){
				up = true;
				down = false;
				inicioMovimento = 0;
			}
		}
		}
		}
		}
		
		if(this.calculateDistance(this.getX(), this.getY(), Game.player.getX(), Game.player.getY()) < 150) {
			alvo = true;
			speed = 1.3;
		if(!isColiddingWithPlayer()) {
			if(alvo == true) {
		if(Game.rand.nextInt(100) < 70) {
		if((int)x < Game.player.getX() && World.isFree((int)(x+speed), this.getY())
				&& !isColidding((int)(x+speed), this.getY())) {
			dir = 2;
			x+=speed;
		}else if((int) x > Game.player.getX() && World.isFree((int)(x-speed), this.getY())
				&& !isColidding((int)(x-speed), this.getY())) {
			dir = 1;
			x-=speed;
		}
		
		if((int)y < Game.player.getY() && World.isFree(this.getX(), (int)(y+speed))
				&& !isColidding(this.getX(), (int)(y+speed))) {
			dir = 4;
			y+=speed;
		}else if((int) y > Game.player.getY() && World.isFree(this.getX(), (int)(y-speed))
				&& !isColidding(this.getX(), (int)(y-speed))) {
			dir = 3;
			y-=speed;
		}
			
		}
		}
		
		}else {
			if(Game.rand.nextInt(100) < 8) {
				Sound.efect_hit.start();
				Game.player.isDamaged = true;
				Game.player.life-=(3 + Game.rand.nextInt(6));
				System.out.println(Game.player.life);
			}
		}
		}else {
			alvo = false;
			speed = 0.5;
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
	
	/**
	 * Controla o dash. Retorna true se o inimigo esta preparando/dando o dash
	 * (nesse caso o movimento normal e pulado).
	 */
	private boolean tickDash() {
		if(dashEstado == 0) {
			if(dashCooldown > 0) {
				dashCooldown--;
				return false;
			}
			double dist = this.calculateDistance(this.getX(), this.getY(), Game.player.getX(), Game.player.getY());
			if(dist < 16*9 && dist > 16*2) {
				dashEstado = 1;
				dashPreparo = 34;      // ~0,5 s de aviso piscando
				alvo = true;
			}
			return false;
		}
		
		if(dashEstado == 1) {
			dashPreparo--;
			// encara o jogador
			double ddx = Game.player.getX() - x, ddy = Game.player.getY() - y;
			if(Math.abs(ddx) > Math.abs(ddy)) dir = ddx > 0 ? 2 : 1;
			else dir = ddy > 0 ? 4 : 3;
			if(dashPreparo <= 0) {
				double len = Math.sqrt(ddx*ddx + ddy*ddy);
				if(len < 1) len = 1;
				dashDx = ddx/len;   // direcao fixa: linha reta
				dashDy = ddy/len;
				dashEstado = 2;
				dashTicks = 0;
				dashAcertou = false;
				Sound.efect_dash.start();
			}
			return true;
		}
		
		// dashEstado == 2
		boolean mexeu = false;
		double nx = x + dashDx*DASH_VEL;
		if(World.isFree((int)nx, this.getY())) { x = nx; mexeu = true; }
		double ny = y + dashDy*DASH_VEL;
		if(World.isFree(this.getX(), (int)ny)) { y = ny; mexeu = true; }
		dashTicks++;
		if(Math.abs(dashDx) > Math.abs(dashDy)) dir = dashDx > 0 ? 2 : 1;
		else dir = dashDy > 0 ? 4 : 3;
		
		VelParticle.rastro(x + 8, y + 8, dashDx, dashDy, 3, new Color(150, 225, 255));
		
		if(!dashAcertou && isColiddingWithPlayer()) {
			dashAcertou = true;
			Sound.efect_hit.start();
			Game.player.isDamaged = true;
			Game.player.life -= DASH_DANO_MIN + Game.rand.nextInt(DASH_DANO_MAX - DASH_DANO_MIN + 1);
		}
		
		if(!mexeu || dashTicks >= 26) {   // ~120 px de alcance
			dashEstado = 0;
			dashCooldown = DASH_COOLDOWN;
		}
		return true;
	}
	
	public void destroySelf() {
		Sound.efect_explosao.start();
		Game.flocos.remove(this);
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
		for(int i = 0; i < Game.flocos.size(); i++) {
			Flocos e = Game.flocos.get(i);
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
		boolean pisca = dashEstado == 1 && (dashPreparo/3)%2 == 0;
		if(!isDamage && !pisca) {
			if(dir == 1) {
				g.drawImage(sprites[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
			}else if(dir == 2) {
				g.drawImage(sprites2[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
			}else if(dir == 3) {
				g.drawImage(sprites3[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
			}else if(dir == 4) {
				g.drawImage(sprites4[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
			}
		}else {
		if(dir == 2) {
			g.drawImage(Flocos.FLOCOS_FEEDBACK, this.getX() - Camera.x, this.getY() - Camera.y, null);
		}
		if(dir == 1) {
			g.drawImage(Flocos.FLOCOS_FEEDBACK2, this.getX() - Camera.x, this.getY() - Camera.y, null);
		}
		if(dir == 3) {
			g.drawImage(Flocos.FLOCOS_FEEDBACK3, this.getX() - Camera.x, this.getY() - Camera.y, null);
		}
		if(dir == 4) {
			g.drawImage(Flocos.FLOCOS_FEEDBACK4, this.getX() - Camera.x, this.getY() - Camera.y, null);
		}
		//g.setColor(new Color(0,0,200));
		//g.fillRect((this.getX() - Camera.x)+ maskx,(this.getY() - Camera.y) + masky ,maskw,maskh);
	}
	}
}