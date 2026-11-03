package com.blueuniverse.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;

import com.blueuniverse.graficos.Artes;

public class Creditos {
	
	public static int curText = 0;
	public static boolean frases = false;
	
	public static int yArte = Game.HEIGHT*Game.SCALE +40;
	public static int minyArte = 1; 
	//public static int xArte = (((Game.WIDTH*Game.SCALE)/2)-((Game.CRE_WIDTH)/2))+(((Game.CRE_WIDTH)/4)/10)-20;
	public static int xArte = 0;
	
	private int faded = 2;
	private int fadedN = 0, fadedNMax = 255;
	public static boolean fadedAct = false;
	private int selectCredito = 1;
	
	public void tick() {
		
		if(faded == 0) {
			Text.curText = 5;
			Text.xArte = 0;
		    Text.frases = true;
		    Text.showMessage = true;
		    Text.arteView = true;
		    Sound.diminuirTema = true;
		    Game.gameState = "TEXT";
		}
		
		if(frases == true && Game.gameState == "CREDITOS" && yArte >= minyArte) {
			yArte -= 0.5;
		}else if(frases == true && Game.gameState == "CREDITOS" && yArte <= minyArte) {
			fadedAct = true;
			
		}
		
		if(fadedAct == true) {
			fadedN++;
			if(fadedN >= fadedNMax) {
				fadedN = 0;
				yArte = Game.HEIGHT*Game.SCALE +40;
				faded--;
				selectCredito++;
				minyArte = 10;
				fadedAct = false;
			}
		}
		
	}
	
	public void render(Graphics g) {
		
		if(frases == true) {
			if(curText == 1) {
			    g.setColor(new Color(0,0,0,255));
			    g.fillRect(0, 0, Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height);
			    
			    if(selectCredito == 1) {
			    	g.drawImage(Artes.CREDITOS, xArte, yArte, Game.CRE_WIDTH, Game.CRE_HEIGHT, null);
			    }
			    if(selectCredito == 2) {
			    	g.drawImage(Artes.CREDITOS2, xArte, yArte, Game.CRE_WIDTH, Game.CRE_HEIGHT, null);
			    }
			    g.setColor(new Color(0,0,0,fadedN));
			    g.fillRect(0, 0, Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height);
			    
				
			}
		}
		
		//
		
	}
}
