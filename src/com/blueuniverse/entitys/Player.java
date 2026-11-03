package com.blueuniverse.entitys;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.blueuniverse.graficos.Spritesheet;
import com.blueuniverse.main.Game;
import com.blueuniverse.main.Sound;
import com.blueuniverse.main.Text;
import com.blueuniverse.main.Tranzi;
import com.blueuniverse.world.Camera;
import com.blueuniverse.world.World;

public class Player extends Entity{
	
	public int stamina;
	public int staminaRecharge;
	public int staminaRechargeDelay;
	public int score;
	public int maxStamina = 5;
	
	public boolean right,up,left,down;
	public static boolean obst = false;
	public int right_dir = 0, left_dir = 1, up_dir = 2, down_dir = 3;
	public int dir = right_dir;
	public static int setPlayer = 1;
	public static double speed = 1.4;
	public static boolean correndo = false; // true enquanto o jogador segura Z como Bia
	
	public static int frames = 0;
	public static int maxFrames = 5;
	public int framesBullet = 0, maxFramesBullet = 7, indexBullet = 0, maxIndexBullet = 3;
	static boolean moved = false;
	public static boolean proximoLevel = false;
	public static boolean proximoLevelBoos = false;
	
	public static int index = 0;
	public static int maxIndex = 3;
	private BufferedImage[] rightPlayer;
	private BufferedImage[] leftPlayer;
	private BufferedImage[] upPlayer;         
	private BufferedImage[] downPlayer;
	public static int index2 = 0;
	public static int maxIndex2 = 3;
	private BufferedImage[] spritesBia;
	private BufferedImage[] spritesBia2;
	private BufferedImage[] spritesBia3;
	private BufferedImage[] spritesBia4;
	private BufferedImage[] spritesBia5;
	public static BufferedImage[] spritesBullet;
	
	private BufferedImage playerDamage;
	private BufferedImage playerDamage2;
	
	//ammo = muni��o em ingles
	
	private boolean arma = false;
	
	//public int ammo = 0;
	
	public boolean isDamaged = false;
	private int damageFrame = 0;
	
	public static boolean shoot = false;
	public boolean mouseShoot = false;
	public boolean punch = false;
	
	public double life = 100, maxlife = 100;
	public double ammo = 0, maxAmmo = 100;
	public double dano = 0, maxDano = 100;
	public int mx, my;

	public Player(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
		
		stamina = maxStamina;
		//kayky
		rightPlayer = new BufferedImage[4];
		leftPlayer = new BufferedImage[4];
		upPlayer = new BufferedImage[4];
		downPlayer = new BufferedImage[4];
		playerDamage = Game.spritesheet.getSprite(0, 16, 16, 16);
		playerDamage2 = Game.spritesheet.getSprite(16, 16, 16, 16);
		// bia
		spritesBia = new BufferedImage[4];
		spritesBia[0] = Game.spritesheetBias.getSprite(0, 0, 16, 16);
		spritesBia[1] = Game.spritesheetBias.getSprite(16, 0, 16, 16);
		spritesBia[2] = Game.spritesheetBias.getSprite(16*2, 0, 16, 16);
		spritesBia[3] = Game.spritesheetBias.getSprite(16*3, 0, 16, 16);
		
		spritesBia2 = new BufferedImage[4];
		spritesBia2[0] = Game.spritesheetBias.getSprite(0, 16, 16, 16);
		spritesBia2[1] = Game.spritesheetBias.getSprite(16, 16, 16, 16);
		spritesBia2[2] = Game.spritesheetBias.getSprite(16*2, 16, 16, 16);
		spritesBia2[3] = Game.spritesheetBias.getSprite(16*3, 16, 16, 16);
		
		spritesBia3 = new BufferedImage[4];
		spritesBia3[0] = Game.spritesheetBias.getSprite(0, 16*2, 16, 16);
		spritesBia3[1] = Game.spritesheetBias.getSprite(16, 16*2, 16, 16);
		spritesBia3[2] = Game.spritesheetBias.getSprite(16*2, 16*2, 16, 16);
		spritesBia3[3] = Game.spritesheetBias.getSprite(16*3, 16*2, 16, 16);
		
		spritesBia4 = new BufferedImage[4];
		spritesBia4[0] = Game.spritesheetBias.getSprite(0, 16*3, 16, 16);
		spritesBia4[1] = Game.spritesheetBias.getSprite(16, 16*3, 16, 16);
		spritesBia4[2] = Game.spritesheetBias.getSprite(16*2, 16*3, 16, 16);
		spritesBia4[3] = Game.spritesheetBias.getSprite(16*3, 16*3, 16, 16);
		
		spritesBia5 = new BufferedImage[4];
		spritesBia5[0] = Game.spritesheetBias.getSprite(16*4, 0, 16, 16);
		spritesBia5[1] = Game.spritesheetBias.getSprite(16*4, 16, 16, 16);
		spritesBia5[2] = Game.spritesheetBias.getSprite(16*4, 16*2, 16, 16);
		spritesBia5[3] = Game.spritesheetBias.getSprite(16*4, 16*3, 16, 16);
		
		spritesBullet = new BufferedImage[4];
		spritesBullet[0] = Game.spritesheet.getSprite(16, 16*7, 16, 16);
		spritesBullet[1] = Game.spritesheet.getSprite(16*2, 16*7, 16, 16);
		spritesBullet[2] = Game.spritesheet.getSprite(16*3, 16*7, 16, 16);
		spritesBullet[3] = Game.spritesheet.getSprite(16*4, 16*7, 16, 16);
		
		for(int i = 0; i < 4; i++) {
			rightPlayer[i] = Game.spritesheet.getSprite(0+(i*16),64,16,16);
		}
		for(int i = 0; i < 4; i++) {
			leftPlayer[i] = Game.spritesheet.getSprite(0+(i*16),80,16,16);
		}
		for(int i = 0; i < 4; i++) {
			upPlayer[i] = Game.spritesheet.getSprite(0+(i*16),48,16,16);
		}
		for(int i = 0; i < 4; i++) {
			downPlayer[i] = Game.spritesheet.getSprite(0+(i*16),32,16,16);
		}
		// TODO Auto-generated constructor stub
	}
	
	public void tick() {
		depth = 4;
		moved = false;
		// Bia correndo (segurando Z): solta particulas de velocidade
		if(setPlayer == 2 && correndo && (right || left || up || down) && Game.gameState == "NORMAL") {
			double rdx = (right ? 1 : 0) - (left ? 1 : 0);
			double rdy = (down ? 1 : 0) - (up ? 1 : 0);
			if(rdx != 0 || rdy != 0) {
				VelParticle.rastro(x + 8, y + 10, rdx, rdy, 2 + Game.rand.nextInt(2), new Color(255, 120, 235));
			}
		}
		proximoLevel = false;
		if(obst != true) {
		if(right && World.isFree((int)(x+speed),this.getY())) {
			moved = true;
			dir = right_dir;
			x += speed;
		}
		else if(left && World.isFree((int)(x-speed),this.getY())) {
			moved = true;
			dir = left_dir;
			x -= speed;
		}if(up && World.isFree(this.getX(),(int)(y-speed))) {
			moved = true;
			dir = up_dir;
			y -= speed;
		}
		else if(down && World.isFree(this.getX(), (int)(y+speed))) {
			moved = true;
			dir = down_dir;
			y += speed;
		}
		
		////proximo level
		if(right && World.isNextLevel((int)(x+speed),this.getY())) {
			proximoLevel = true;
			if((Game.CUR_LEVEL == 0 && Game.setZone == 3) || Game.CUR_LEVEL == 10) {
				Sound.diminuirTema = true;
				Text.xArte = 0;
				Text.arteView = true;
				Text.curText = 1;
				Game.gameState = "TEXT";
			    Text.frases = true;
			    Text.showMessage = true;
			}
		}
		else if(left && World.isNextLevel((int)(x-speed),this.getY())) {
			proximoLevel = true;
			if((Game.CUR_LEVEL == 0 && Game.setZone == 3) || Game.CUR_LEVEL == 10) {
				Sound.diminuirTema = true;
				Text.xArte = 0;
				Text.arteView = true;
				Text.curText = 1;
				Game.gameState = "TEXT";
			    Text.frases = true;
			    Text.showMessage = true;
			}
		}
		
		if(up && World.isNextLevel(this.getX(),(int)(y-speed))) {
			proximoLevel = true;
			if((Game.CUR_LEVEL == 0 && Game.setZone == 3) || Game.CUR_LEVEL == 10) {
				Sound.diminuirTema = true;
				Text.xArte = 0;
				Text.arteView = true;
				Text.curText = 1;
				Game.gameState = "TEXT";
			    Text.frases = true;
			    Text.showMessage = true;
			}
		}
		else if(down && World.isNextLevel(this.getX(), (int)(y+speed))) {
			proximoLevel = true;
			if((Game.CUR_LEVEL == 0 && Game.setZone == 3) || Game.CUR_LEVEL == 10) {
				Sound.diminuirTema = true;
				Text.xArte = 0;
				Text.arteView = true;
				Text.curText = 1;
				Game.gameState = "TEXT";
			    Text.frases = true;
			    Text.showMessage = true;
			}
		}
		}
		if(Game. setZone == 5) {
			if(World.isIce((int)(x+speed),this.getY())) {
				Game.player.life--;
				Game.player.speed = 0.4;
			}else if(!World.isIce((int)(x+speed),this.getY())) {
				// fora do gelo: a Bia continua podendo correr (Z); antes isso zerava a corrida dela nesta zona
				Game.player.speed = (setPlayer == 2 && correndo) ? 2.0 : 1.4;
			}
		}
		
		
		if(moved) {
			frames++;
			if(frames == maxFrames) {
				frames = 0;
				index++;
				if(index > maxIndex) {
					index = 0;
				}
			}
			

		}
		
		CheckColisionsLifepack();
		CheckColisionAmmo();
		CheckColisionGun();
		
		if(isDamaged) {
			this.damageFrame++;
			if(this.damageFrame == 7) {
				this.damageFrame = 0;
				isDamaged = false;
			}
		}
		
		if(shoot) {
			
			if(arma && ammo > 0) {
				shoot = false;
			ammo--;
			Bia.especial++;
			Sound.efect_aqua.start();
			int dx = 0;
			int dy = 0;
			int px = 0;
			int py = 0;
			if(dir == right_dir) {
				dx = 1;
				px = 7;
				py = 6;
			}else if(dir == left_dir) {
				dx = -1;
				px = -1;
				py = 6;
			}
			if(dir == up_dir) {
				dy = -1;
				px = 10;
				py = 1;
			}else if(dir == down_dir) {
				dy = 1;
				px = 1;
				py = 5;
			}
			
			BulletShoot bullet = new BulletShoot(this.getX()+px,this.getY()+py,4,4,null,dx,dy);
			Game.bullets.add(bullet);
			}
		}
		
		if(punch) {
			punch = false;
			int dx = 0;
			int dy = 0;
			int px = 0;
			int py = 0;
			Sound.efect_aqua.start();
			if(dir == right_dir) {
				dx = 1;
				px = 5;
				py = 1;
			}else if(dir == left_dir) {
				dx = -1;
				px = -3;
				py = 1;
			}
			if(dir == up_dir) {
				dy = -1;
				px = 1;
				py = 1;
			}else if(dir == down_dir) {
				dy = 1;
				px = -1;
				py = 5;
			}
			
			PunchShoot punch = new PunchShoot(this.getX()+px,this.getY()+py,4,4,null,dx,dy);
			Game.punchs.add(punch);
		}
		
		if(mouseShoot) {
			//System.out.println("atirou");
			mouseShoot = false;
			
			if(arma && ammo > 0) {
			//ammo--;
			double angle =0;
			int px = 0;
			int py = 0;
			if(dir == right_dir) {
				px = 20;
				py = 8;
				angle = Math.atan2(my - ((this.getY() + 8) - Camera.y), mx - ((this.getX() + px) - Camera.x));
			}else if(dir == left_dir) {
				px = -10;
				py = 8;
				angle = Math.atan2(my - ((this.getY() + 8) - Camera.y), mx - ((this.getX() + px) - Camera.x));
			}
			if(dir == up_dir) {
				px = 10;
				py = -3;
				angle = Math.atan2(my - ((this.getY() + 8) - Camera.y), mx - ((this.getX() + px) - Camera.x));
			}else if(dir == down_dir) {
				px = 2;
				py = 15;
				angle = Math.atan2(my - ((this.getY() + 8) - Camera.y), mx - ((this.getX() + px) - Camera.x));
			}
			double dx = Math.cos(angle);
			double dy = Math.sin(angle);
			
			BulletShoot bullet = new BulletShoot(this.getX()+px,this.getY()+py,4,4,null,dx,dy);
			Game.bullets.add(bullet);
			}
		}
		
		if(life <= 60 && Game.CUR_LEVEL == 1 && Game.setZone == 5) {
			Game.spritesheetArtes = new Spritesheet("/art/"+51+".png");
			Muriel.isMurielLazer = false;
			Muriel.murielLazserTimer = 10;
			Muriel.atack = false;
			Tranzi.setTranzi = 2;
			Tranzi.StartArt();
			Game.gameState = "TRANZI";
		}
		
		if(life <= 0) {
			
			if(Game.CUR_LEVEL == 2 && Game.setZone == 2) {
				Killer.life = 350;
			}
			if((Game.CUR_LEVEL == 2 && Game.setZone == 2) || (Game.CUR_LEVEL == 2 && Game.setZone == 5)) {
				Abobora.life = 350;
			}
			if(Game.CUR_LEVEL == 2 && Game.setZone == 4) {
				Sally.life = 150;
			}
			if(Game.CUR_LEVEL == 0 && Game.setZone == 5) {
				Lili.life = 950;
			}
			if(Game.setZone == 5 || Game.setZone == 6) {
				Bia.especial = 0;
				Bia.curEspecial = 3;
				Muriel.life = 1500;
				Muriel.isMurielLazer = false;
				Muriel.murielLazserTimer = 10;
				Muriel.atack = false;
			}
			
			life = 0;
			
			Game.gameState ="GAME_OVER";
			if(Game.setZone == 5) {
				if(Game.CUR_LEVEL == 2 || Game.CUR_LEVEL == 13) {
					Bia.biaReset();
					Muriel.life = 1500;
				}
			}
			
		}
		if(dano <= 0) {
			dano = 0;
		}
		if(dano >= maxDano) {
			dano = 100;
		}
		if(ammo >= maxAmmo) {
			ammo = 100;
		}
		if(life >= maxlife) {
			life = 100;
		}
		
		if (stamina <= 0 && staminaRechargeDelay == 0 && staminaRecharge == 0) {
			staminaRechargeDelay = 40;
		}

		if (staminaRechargeDelay > 0) {
			staminaRechargeDelay--;
		}

		if (staminaRechargeDelay == 0) {
			staminaRecharge++;
			while (staminaRecharge > 10) {
				staminaRecharge -= 10;
				if (stamina < maxStamina) stamina++;
			}
		}
		
		updateCamera();
		
		framesBullet++;
		if(framesBullet == maxFramesBullet) {
			framesBullet = 0;
			indexBullet++;
			if(indexBullet > maxIndexBullet) 
				indexBullet = 0;
		}
	}
	
	public void updateCamera() {
		Camera.x = Camera.clamp((this.getX()+8) - ((Game.WIDTH/2)-Game.WIDTH/(Game.ZOOM*2)), 0, World.WIDTH*16 - ((Game.WIDTH)-Game.WIDTH/(Game.ZOOM)));
		Camera.y = Camera.clamp((this.getY()+8) - ((Game.HEIGHT/2)-Game.HEIGHT/(Game.ZOOM*2)), 0, World.HEIGHT*16 - ((Game.HEIGHT)-Game.HEIGHT/(Game.ZOOM)));
	}
	
	public void CheckColisionGun() {
		for(int i = 0; i < Game.entities.size(); i++) {
			Entity atual = Game.entities.get(i);
			if(atual instanceof Weapon){
				if(Entity.isColidding(this, atual)) {
					Sound.efect_powerup.start();
					arma = true;
					ammo+=2;
					Game.entities.remove(atual);
					//System.out.println("pegou a arma");
					if(ammo < 1) {
						arma = false;
						return;
					}
					System.out.println("mana: "+ammo);
				}
			}
		}
	}
	
	public void CheckColisionAmmo() {
		for(int i = 0; i < Game.entities.size(); i++) {
			Entity atual = Game.entities.get(i);
			if(atual instanceof Bullet){
				if(Entity.isColidding(this, atual)) {
					ammo+=10;
					Sound.efect_powerup.start();
					Game.entities.remove(atual);
					if(ammo >= 100) {
						ammo = 100;
						return;
					}
					System.out.println("mana: "+ammo);
				}
			}
		}
	}
	
	public void CheckColisionsLifepack() {
		for(int i = 0; i < Game.entities.size(); i++) {
			Entity atual = Game.entities.get(i);
			if(atual instanceof Lifepack){
				if(Entity.isColidding(this, atual)) {
					life+=10;
					Sound.efect_powerup.start();
					Game.entities.remove(atual);
					if(life >=100) {
						life =100;
						return;
					}
				}
			}
		}
	}
	

	
	public void render(Graphics g) {
		if(!isDamaged && setPlayer == 1) {
			
		if(dir == right_dir) {
			g.drawImage(rightPlayer[index],this.getX() - Camera.x,this.getY() - Camera.y,null);
			if(arma && ammo > 0) {
				//arma para direita
				g.drawImage(Weapon.spritesBullet[indexBullet], this.getX() - Camera.x+1, this.getY() - Camera.y+2, null);
				//g.drawImage(Entity.GUN_RIGHT, this.getX() - Camera.x+8, this.getY()+1 - Camera.y, null);
			}
		}
		else if(dir == left_dir) {
			if(arma && ammo > 0) {
				//arma para esquerda
				g.drawImage(Weapon.spritesBullet[indexBullet], this.getX() - Camera.x-2, this.getY() - Camera.y-2, null);
				//g.drawImage(Entity.GUN_LEFT, this.getX() - Camera.x-8, this.getY()+1 - Camera.y, null);
			}
			g.drawImage(leftPlayer[index],this.getX() - Camera.x,this.getY() - Camera.y,null);
		}
		else if(dir == up_dir) {
			g.drawImage(upPlayer[index],this.getX() - Camera.x,this.getY() - Camera.y,null);
			if(arma && ammo > 0) {
				//arma para cima
				g.drawImage(Weapon.spritesBullet[indexBullet], this.getX() - Camera.x+5, this.getY() - Camera.y-1, null);
				//g.drawImage(Entity.GUN_UP, this.getX() - Camera.x+4, this.getY()-1 - Camera.y, null);
			}
		}
		else if(dir == down_dir) {
			g.drawImage(downPlayer[index],this.getX() - Camera.x,this.getY() - Camera.y,null);
			if(arma && ammo > 0) {
				//arma para baixo
				g.drawImage(Weapon.spritesBullet[indexBullet], this.getX() - Camera.x-5, this.getY() - Camera.y+1, null);
				//g.drawImage(Entity.GUN_DOWN, this.getX() - Camera.x-4, this.getY()+8 - Camera.y, null);
			}
		}
		}else if((dir == down_dir || dir == up_dir) && setPlayer == 1) {
			g.drawImage(playerDamage, this.getX()-Camera.x, this.getY()-Camera.y, null);
		}else if((dir == left_dir || dir == right_dir) && setPlayer == 1 ) {
			g.drawImage(playerDamage2, this.getX()-Camera.x, this.getY()-Camera.y, null);
		}
		/// bia
		else if(!isDamaged && setPlayer == 2) {
			
		if(dir == right_dir) {
			g.drawImage(spritesBia3[index],this.getX() - Camera.x,this.getY() - Camera.y,null);
		}
		else if(dir == left_dir) {
			g.drawImage(spritesBia4[index],this.getX() - Camera.x,this.getY() - Camera.y,null);
		}
		else if(dir == up_dir) {
			g.drawImage(spritesBia2[index],this.getX() - Camera.x,this.getY() - Camera.y,null);
		}
		else if(dir == down_dir) {
			g.drawImage(spritesBia[index],this.getX() - Camera.x,this.getY() - Camera.y,null);
		}
		//
		}else if(dir == right_dir && setPlayer == 2) {
			g.drawImage(spritesBia5[2],this.getX() - Camera.x,this.getY() - Camera.y,null);
		}
		else if(dir == left_dir && setPlayer == 2) {
			g.drawImage(spritesBia5[3],this.getX() - Camera.x,this.getY() - Camera.y,null);
		}
		else if(dir == up_dir && setPlayer == 2) {
			g.drawImage(spritesBia5[1],this.getX() - Camera.x,this.getY() - Camera.y,null);
		}
		else if(dir == down_dir && setPlayer == 2) {
			g.drawImage(spritesBia5[0],this.getX() - Camera.x,this.getY() - Camera.y,null);
		}
		
		if(Muriel.isMurielLazer == true) {
			g.setColor(Color.red);
			g.drawLine((int)(this.getX()-Camera.x)+8,(int) (this.getY()-Camera.y)+8, (Muriel.xTarget-Camera.x)+8, (Muriel.yTarget-Camera.y)+8);
		}
	}

}
