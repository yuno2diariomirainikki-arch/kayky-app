package com.blueuniverse.entitys;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.blueuniverse.main.Game;
import com.blueuniverse.main.Sound;
import com.blueuniverse.main.Text;
import com.blueuniverse.world.Camera;
import com.blueuniverse.world.WallTile;
import com.blueuniverse.world.World;

public class Botão extends Entity {
	
	private int maskx = 8, masky = 8, maskw = 12, maskh =12;
	public static boolean alvo = false;
	public static int check = 0;
	public static int delayAtivo = 0;
	public static int alcance = 0;
	public static int delay = 0, maxdelay = 1000;
	
	private static boolean atack = false;
	private int timeAtack = 0, maxAtack = 100, fogoAtack = 5;

	public Botão(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
		// TODO Auto-generated constructor stub
	}
	
	public void tick() {
		
		depth = 0;
		maskx = -2;
		masky = -2;
		maskw = 20;
		maskh = 20;
		
		
		if(isColiddingWithPlayer() && delayAtivo == 0) {
			Text.interage = true;
			alcance = 100;
		}
		if(delayAtivo == 1) {
			delay++;
			if(delay >= maxdelay) {
				delay = 0;
				delayAtivo = 0;
				//atack = true;
			}
		}
		if(alcance >= 1) {
			alcance--;
			if(alcance <= 1) {
				Text.interage = false;
			}
		}
		
		
		if(atack == true &&
				(this.calculateDistance(this.getX(), this.getY(), Game.player.getX(), Game.player.getY()) < 32)) {
			atack = false;
			System.out.println("atacou");
			int dx = 1;
			int dy = 0;
			int px = 5;
			int py = 1;
			
			int dx2 = -1;
			int dy2 = 0;
			int px2 = -3;
			int py2 = 1;
			
			int dx3 = 0;
			int dy3 = -1;
			int px3 = 1;
			int py3 = 1;
			
			int dx4 = 0;
			int dy4 = 1;
			int px4 = -1;
			int py4 = 5;
			
			BulletShoot atack = new BulletShoot(this.getX()+px,this.getY()+py,4,4,null,dx,dy);
			Game.bullets.add(atack);
			//Game.atacks.add(atackVamp);
			BulletShoot atack2 = new BulletShoot(this.getX()+px2,this.getY()+py2,4,4,null,dx2,dy2);
			Game.bullets.add(atack2);
			BulletShoot atack3 = new BulletShoot(this.getX()+px3,this.getY()+py3,4,4,null,dx3,dy3);
			Game.bullets.add(atack3);
			BulletShoot atack4 = new BulletShoot(this.getX()+px4,this.getY()+py4,4,4,null,dx4,dy4);
			Game.bullets.add(atack4);
			//Game.atack.add(atackVamp);
			
			//BulletShoot bullet = new BulletShoot(this.getX()+px,this.getY()+py,4,4,null,dx,dy);
			//Game.bullets.add(bullet);
		}
	}
	
	public static void apertarBotão() {
		//Botão.check = false;
		Text.interage = false;
		Botão.delayAtivo = 1;
		Sound.efect_botao.start();
			if(Botão.check == 0) {
				//Text.interage = false;
				Game.gameState = "NORMAL";
				Barreira.Bloqueado();
			    Barreira2.Aberto();
			    System.out.println("Apertou o Botão");
				Botão.check = 1;
				
			}else if(Botão.check == 1) {
				//Text.interage = false;
				Game.gameState = "NORMAL";
				Barreira.Aberto();
			    Barreira2.Bloqueado();
			    System.out.println("Apertou o Botão");
			    Botão.check = 0;
			}
	}
	public static void apertarBotão2() {
		//Botão.check = false;
		Text.interage = false;
		Botão.delayAtivo = 1;
		Sound.efect_botao.start();
		atack = true;	
	}


	public boolean isColiddingWithPlayer() {
		Rectangle enemyCurrent = new Rectangle(this.getX() + maskx,this.getY() + masky,maskw,maskh);
		Rectangle player = new Rectangle(Game.player.getX(),Game.player.getY(),16,16);
		
		return enemyCurrent.intersects(player);
	}

	
	public void render(Graphics g) {
		g.drawImage(Botão.BOTAO_ON, this.getX() - Camera.x, this.getY() - Camera.y, null);
	}
	
}
