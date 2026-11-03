package com.blueuniverse.entitys;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.blueuniverse.main.Game;
import com.blueuniverse.main.Sound;
import com.blueuniverse.world.Camera;
import com.blueuniverse.world.World;

public class Barreira2 extends Entity{
	private int maskx = 8, masky = 8, maskw = 12, maskh =12;
	private static int maskx2 = 8, masky2 = 8, maskw2 = 12, maskh2 = 12;
	private static boolean Ligado = false;
	public static boolean Bloqueio = false;
	public static boolean barreiraAtiva = false;

	public Barreira2(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
		// TODO Auto-generated constructor stub
	}
	
	public void tick() {
        depth = 0;
        maskx = 4;
        masky = 0;
        maskw = 8;
        maskh = 16;
        maskx2 = 0;
        masky2 = 3;
        maskw2 = 16;
        maskh2 = 10;

        // Verifica a colisão com o jogador e impede o movimento na direção da barreira
        Player player = Game.player; // Substitua por sua classe de jogador
        if(Ligado == true) {
        if (isCollidingWithPlayer()) {
        	if (player.getY() < this.getY()) {
        		player.setY(this.getY()-16);
            }
        	if (player.getY() > this.getY() && player.getY() < this.getY()+15) {
                player.setY(this.getY()+(16));
            }
        }
        if (isCollidingWithPlayer2()) {
        	if (player.getX() > this.getX()+10) {
        		player.setX(this.getX() + 15);
            }
        	else if (player.getX() < this.getX()) {
               player.setX(this.getX() - 14);
            }
        }
        }
        Ligado();
    }
	
	private boolean isCollidingWithPlayer() {
        Player player = Game.player; // Substitua por sua classe de jogador
        Rectangle boundsBarreira = new Rectangle(this.getX() + maskx, this.getY() + masky, maskw, maskh);
        Rectangle boundsPlayer = new Rectangle(player.getX(), player.getY(), player.getWidth(), player.getHeight());

        return boundsBarreira.intersects(boundsPlayer);
    }
	private boolean isCollidingWithPlayer2() {
        Player player = Game.player; // Substitua por sua classe de jogador
        Rectangle boundsBarreira = new Rectangle(this.getX() + maskx2, this.getY() + masky2, maskw2, maskh2);
        Rectangle boundsPlayer = new Rectangle(player.getX(), player.getY(), player.getWidth(), player.getHeight());

        return boundsBarreira.intersects(boundsPlayer);
    }
	
	public static void Bloqueado() {
			Ligado = false;
			barreiraAtiva = false;
			System.out.println("Bloqueou");
	}
	public static void Aberto() {
			Ligado = true;
			barreiraAtiva = true;
			System.out.println("Abriu");
	}
	
	public static void Ligado() {
		if (Ligado == true) {
			Bloqueio = false;
		}else if(Ligado == false) {
			Bloqueio = false;
		}
	}
	
	public void render(Graphics g) {
		if(barreiraAtiva != false) {
			g.drawImage(Barreira.BARREIRA2_ON, this.getX() - Camera.x, this.getY() - Camera.y, null);
			
		}else{
			g.drawImage(Barreira.BARREIRA2_OFF, this.getX() - Camera.x, this.getY() - Camera.y, null);
		}
	}

}
