package com.blueuniverse.main;

import com.blueuniverse.entitys.Abobora;
import com.blueuniverse.entitys.Bia;
import com.blueuniverse.entitys.Entity;
import com.blueuniverse.entitys.Muriel;
import com.blueuniverse.entitys.Player;
import com.blueuniverse.entitys.Weapon;
import com.blueuniverse.world.World;

public class Anima {
	
	public static boolean suportSpawn = false;
	public static boolean aboboraSpawn = false;
	public static boolean murielSpawn = false;
	public static boolean moved = false;
	public static int setMoved = 0;
	public static int curAbobora = 2;
	
	public void tick() {
		Animation();
	}
	
	public static void Animation() {
		
		if(Game.CUR_LEVEL == 2 && Game.gameState == "ANIMA" && Text.curText == 2 && Game.setZone == 5) {
			AddSuport();
			if(Bia.x < Game.player.getX()+16 && Text.curText == 2) {
				Bia.x++;
				moved = true;
			}else if(Bia.x >= Game.player.getX()+16 && Text.curText == 2) {
				moved = false;
				Text.arteView = true;
				Text.curText = 1;
				Game.gameState = "TEXT";
			    Text.frases = true;
			    Text.showMessage = true;
				
			}
		}
		if(Game.CUR_LEVEL == 2 && Game.gameState == "ANIMA" && Text.curText == 40 && Game.setZone == 5) {
			addAbobora();
			if(Abobora.x > Game.player.getX()+(16*3)) {
				Abobora.x--;
				moved = true;
			} else if(Abobora.x <= Game.player.getX()+(16*3)) {
				moved = false;
				Text.arteView = true;
				Game.gameState = "TEXT";
			    Text.frases = true;
			    Text.showMessage = true;
				Text.curText = 4;
			}
		}
		
		if(Game.CUR_LEVEL == 2 && Game.gameState == "ANIMA" && Text.curText == 6 && Game.setZone == 5) {
			if(Muriel.XX < Game.player.getX()+(16*15)) {
				Muriel.XX++;
				setMoved = 3;
				Muriel.dirMuriel = 3;
				moved = true;
				murielSpawn = true;
			} else if(Muriel.XX >= Game.player.getX()+(16*7) && murielSpawn == true) {
				moved = false;
				Text.arteView = false;
				Game.gameState = "NORMAL";
			    Text.frases = false;
			    Text.showMessage = false;
				Text.curText = 0;
				Muriel.remove();
				Bia.especial = 0;
				Abobora.life = 350;
				Game.entities.add(new Weapon(Game.player.getX()-(16*2),Game.player.getY(),16,16,Entity.WEAPON_EN));
				murielSpawn = false;
				Sound.aumentarTema = true;
				//Sound.aumentarTema();
			}
		}
		
		if(Game.CUR_LEVEL == 2 && Game.gameState == "ANIMA" && Text.curText == 2 && Game.setZone == 6) {
			//AddSuport();
			setMoved = 1;
			if(Bia.x < Game.player.getX()+(16*7) && Text.curText == 2) {
				Bia.dirBia = 3;
				Bia.x++;
				moved = true;
			}else if(Bia.x >= Game.player.getX()+16 && Text.curText == 2) {
				moved = false;
				Text.arteView = true;
				Text.curText = 2;
				Game.gameState = "TEXT";
			    Text.frases = true;
			    Text.showMessage = true;
				
			}
		}
		
		
		if(moved == true) {
			if(setMoved == 1) {
				Bia.frames++;
				if(Bia.frames == Bia.maxFrames) {
					Bia.frames = 0;
					Bia.index++;
					if(Bia.index > Bia.maxIndex) 
						Bia.index = 0;
				}
			}
			if(setMoved == 2) {
				Abobora.frames++;
				if(Abobora.frames == Abobora.maxFrames) {
					Abobora.frames = 0;
					Abobora.index++;
					if(Abobora.index > Abobora.maxIndex) 
						Abobora.index = 0;
				}
			}
			if(setMoved == 3) {
				Muriel.frames++;
				if(Muriel.frames == Muriel.maxFrames) {
					Muriel.frames = 0;
					Muriel.index++;
					if(Muriel.index > Muriel.maxIndex) 
						Muriel.index = 0;
				}
			}
		}
	}
	
	public static void AddSuport() {
		if(suportSpawn == true) {
			if(Game.setZone == 5) {
				Entity bia = new Bia(Game.player.getX()-(16*15),Game.player.getY()-(16),16,16,Game.spritesheetBias.getSprite(0, 0, 16, 16));
				Game.entities.add(bia);
				setMoved = 1;
				Bia.dirBia = 3;
				Text.xArte = 0;
				suportSpawn = false;
			}
			
		}
	}
	
	public static void addAbobora() {
		if(aboboraSpawn == true) {
			Abobora en = new Abobora(Game.player.getX()+(16*7),Game.player.getY()-(16),16,32,Entity.ABOBORA_EN);
			Game.entities.add(en);
			Game.aboboras.add(en);
			setMoved = 2;
			Abobora.dirAbobora = 1;
			aboboraSpawn = false;
		}
	}
}
