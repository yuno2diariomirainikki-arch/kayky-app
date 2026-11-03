package com.blueuniverse.main;


import java.awt.Graphics;
import java.awt.Toolkit;

import com.blueuniverse.entitys.Bia;
import com.blueuniverse.entitys.Killer;
import com.blueuniverse.entitys.Muriel;
import com.blueuniverse.entitys.Player;
import com.blueuniverse.entitys.Sally;
import com.blueuniverse.graficos.Artes;
import com.blueuniverse.graficos.Spritesheet;
import com.blueuniverse.world.World;

public class Tranzi {
	private static int animationFinal = ((Game.WIDTH*Game.SCALE)/2) -((Game.ART_WIDTH)/2)+1250;
	public static boolean arteView = false;
	public static int xArte = -2950;
	public static int setTranzi = 1;
	public static boolean startArt = false;
	public static boolean startWorld = false;
	public static boolean checkArt = false;
	private static int setZoneOn = 1;
	
	public static void StartArt() {
		if(setTranzi == 3) {
			xArte = -4950;
			//startWorld = true;
		}	
		if(setTranzi == 2) {
				xArte = -4950;
				//startWorld = true;
			}
			if(setTranzi == 1) {
				xArte = -2950;
			}
			checkArt = true;
	}
	
	public static void startNewWorld() {
		if(startWorld == true) {
			if(Game.CUR_LEVEL == 0 && Game.setZone == 2) {
				String newWorld = "level"+Game.setZone+Game.CUR_LEVEL+".png";
				System.out.println("cur level � "+Game.CUR_LEVEL);
				World.restartGame(newWorld);
				startWorld = false;
			} else if((Game.CUR_LEVEL == 1 || Game.CUR_LEVEL == 2 || Game.CUR_LEVEL == 3) && Game.setZone == 2) {
				Player.proximoLevel = false;
				Player.proximoLevelBoos = false;
				if(Game.CUR_LEVEL == 2) {
					Game.spritesheetArtes = new Spritesheet("/art/"+21+".png");
				}
				
				if(Game.CUR_LEVEL == 3 && Player.proximoLevel == true) {
					Game.setZone = 3;
					Game.CUR_LEVEL = 3;
				}

				String newWorld = "level"+Game.setZone+Game.CUR_LEVEL+".png";
				World.restartGame(newWorld);
				System.out.println("cur level � "+Game.CUR_LEVEL);
				startWorld = false;
			} else if(Game.setZone == 4) {
				if((Game.CUR_LEVEL == 0 || Game.CUR_LEVEL == 1)) {
					Game.CUR_LEVEL ++;
					Player.proximoLevel = false;
					String newWorld = "level"+Game.CUR_LEVEL+".png";
					System.out.println("cur level � "+Game.CUR_LEVEL);
					World.restartGame(newWorld);
					startWorld = false;
				}if(Game.CUR_LEVEL == 3) {
					Game.CUR_LEVEL = 0;
					Game.setZone = 5;
					Player.setPlayer = 1;
					Player.proximoLevel = false;
					String newWorld = "level"+Game.CUR_LEVEL+".png";
					System.out.println("cur level � "+Game.CUR_LEVEL);
					World.restartGame(newWorld);
					startWorld = false;
				}
				
			}else if(Game.setZone == 5 && setZoneOn == 1) {
				setZoneOn = 0;
				if(Game.CUR_LEVEL == 0) {
				Game.CUR_LEVEL++;
				Player.setPlayer = 1;
				Player.proximoLevel = false;
				String newWorld = "level"+Game.CUR_LEVEL+".png";
				System.out.println("cur level � "+Game.CUR_LEVEL);
				World.restartGame(newWorld);
				startWorld = false;
				}else if(Game.CUR_LEVEL == 1) {
					Game.CUR_LEVEL++;
					Player.proximoLevel = false;
					String newWorld = "level"+Game.CUR_LEVEL+".png";
					System.out.println("cur level � "+Game.CUR_LEVEL);
					World.restartGame(newWorld);
					startWorld = false;
				}else if(Game.CUR_LEVEL == 2) {
					Game.setZone = 6;
					Game.CUR_LEVEL = 0;
					Player.proximoLevel = false;
					Sound.endTheme();
					String newWorld = "level"+Game.CUR_LEVEL+".png";
					System.out.println("cur level � "+Game.CUR_LEVEL);
					World.restartGame(newWorld);
					startWorld = false;
					Bia.biaReset();
					
				}
			}  else if(Game.setZone == 6 && setZoneOn == 1) {
				System.out.println("cur level � "+Game.CUR_LEVEL);
				setZoneOn = 0;
				if(Game.CUR_LEVEL == 0){
					Text.curText = 2;
					Game.CUR_LEVEL++;
					Player.proximoLevel = false;
					String newWorld = "level"+Game.CUR_LEVEL+".png";
					System.out.println("cur level � "+Game.CUR_LEVEL);
					World.restartGame(newWorld);
					startWorld = false;
					Bia.biaReset();
					Muriel.MurielCheck = true;
				}else if(Game.CUR_LEVEL == 1){
					Text.curText = 2;
					Game.CUR_LEVEL++;
					Player.proximoLevel = false;
					String newWorld = "level"+Game.CUR_LEVEL+".png";
					System.out.println("cur level � "+Game.CUR_LEVEL);
					World.restartGame(newWorld);
					startWorld = false;
					Bia.biaReset();
				}
				
			}
		}
		startWorld = false;
		checkArt = false;
	}
	
	public static void tranzition() {
	
		if(Game.gameState == "TRANZI") {
			if(setTranzi == 1) {
				startArt = true;
				//Sound.endTema = true;
				//StartArt();
				if(Game.setZone == 1 && setZoneOn == 1) {
					if(Game.CUR_LEVEL == 0) {
						Game.CUR_LEVEL++;
						String newWorld = Game.setZone+Game.CUR_LEVEL+".png";
						World.restartGame(newWorld);
					}
					if(Game.CUR_LEVEL == 2) {
						Game.setZone = 2;
						Game.CUR_LEVEL = 3;
						Sound.endTheme();
						String newWorld = "level"+Game.CUR_LEVEL+".png";
						World.restartGame(newWorld);
						//Sound.aumentarTema = true;
					}
					if(Game.CUR_LEVEL == 3) {
						String newWorld = Game.setZone+Game.CUR_LEVEL+".png";
						World.restartGame(newWorld);
					}
					setZoneOn = 0;
				}
				else if(Game.setZone == 2 && setZoneOn == 1) {
					if(Game.CUR_LEVEL == 2) {
						Game.CUR_LEVEL = 0;
						Game.setZone = 3;
						Game.spritesheetArtes = new Spritesheet("/art/"+30+".png");
						Artes.ARTS0 = Game.spritesheetArtes.getArte(Game.ART_WIDTH, Game.ART_HEIGHT);
						String newWorld = Game.setZone+Game.CUR_LEVEL+".png";
						World.restartGame(newWorld);
					}
					if(Game.CUR_LEVEL == 3) {
						Game.setZone = 3;
						Game.CUR_LEVEL = 3;
						String newWorld = Game.setZone+Game.CUR_LEVEL+".png";
						World.restartGame(newWorld);
					}
					setZoneOn = 0;
				}
				else if(Game.setZone == 3 && setZoneOn == 1) {
					if(Game.CUR_LEVEL == 2) {
						Game.setZone = 4;
						Game.CUR_LEVEL = 0;
						Game.spritesheetArtes = new Spritesheet("/art/"+40+".png");
						Artes.ARTS0 = Game.spritesheetArtes.getArte(Game.ART_WIDTH, Game.ART_HEIGHT);
						String newWorld = Game.setZone+Game.CUR_LEVEL+".png";
						World.restartGame(newWorld);
					}
					if(Game.CUR_LEVEL == 3) {
						Text.curText = 1;
						Game.setZone = 4;
						Game.CUR_LEVEL = 3;
						String newWorld = Game.setZone+Game.CUR_LEVEL+".png";
						World.restartGame(newWorld);
						Game.player.dir = 1;
					}
					setZoneOn = 0;
				}
				
				else if(Game.CUR_LEVEL == 2 && Game.setZone == 4 && setZoneOn == 1) {
					Game.setZone = 1;
					Game.CUR_LEVEL = 2;
					Player.setPlayer = 2;
					//Game.CUR_LEVEL++;
					String newWorld = Game.setZone+Game.CUR_LEVEL+".png";
					World.restartGame(newWorld);
					setZoneOn = 0;
					Game.player.dir = 3;
				}
				else if(Game.CUR_LEVEL == 3 && Game.setZone == 1 && setZoneOn == 1) {
					//Game.spritesheetArtes = new Spritesheet("/art/"+13+".png");
					//Artes.ARTS0 = Game.spritesheetArtes.getArte(Game.ART_WIDTH, Game.ART_HEIGHT);
					String newWorld = Game.setZone+Game.CUR_LEVEL+".png";
					World.restartGame(newWorld);
					setZoneOn = 0;
				}

				
				
				if(xArte <= animationFinal && xArte >= (animationFinal-900)) {
					xArte+=32+16;
				}
				if(xArte <= (animationFinal-900)) {
				    arteView = true;
				    xArte+=32;    
				}
				if(xArte >= animationFinal) {{
					arteView = false;
					xArte = -2950;
					Game.gameState = "NORMAL";
					setZoneOn = 1;
					//Sound.startTema = true;
					//Sound.startTheme();
					//Sound.StartTema();
				}
			}
			}if(setTranzi == 2) {
					startArt = true;
					
					if(xArte <= animationFinal && xArte >= (animationFinal-900)) {
						xArte+=32+16;
					}
					if(xArte <= (animationFinal-900)) {
					    arteView = true;
					    xArte+=32;
					}
					if(xArte >= (animationFinal-2300)) {
						if(checkArt == true) {
							startWorld = true;
						    startNewWorld();
						}
					}
					if(xArte >= animationFinal) {{
						arteView = false;
						xArte = -2950;
						Game.gameState = "NORMAL";
						setTranzi = 1;
						setZoneOn = 1;
						if(Game.CUR_LEVEL == 2 && Game.setZone == 5) {
							
							Sound.diminuirTema = true;
							Text.arteView = true;
							Text.curText = 2;
							Game.gameState = "TEXT";
						    Text.frases = true;
						    Text.showMessage = true;
							Muriel.dirMuriel = 4;
							
							
						}else if(Game.CUR_LEVEL == 2 && Game.setZone == 6) {
							
							Sound.diminuirTema = true;
							Text.arteView = true;
							Text.curText = 2;
							Game.gameState = "TEXT";
						    Text.frases = true;
						    Text.showMessage = true;
							Muriel.dirMuriel = 4;
							Anima.suportSpawn = true;
							Muriel.MurielCheck = false;
							Game.gameState = "ANIMA";
							Anima.Animation();
						}
					}
				}
			}
			if(setTranzi == 3) {
				startArt = true;
				//Sound.endTema = true;
				Sound.sadTheme = 2;
				Sound.endTheme();
				//StartArt();
				
				
				if(xArte <= (animationFinal-900)) {
				    arteView = true;
				    xArte+=20;    
				}
				if(xArte >= (animationFinal-2400)) {{
					arteView = false;
					xArte = -2950;
					//Game.gameState = "NORMAL";
					if(Game.CUR_LEVEL == 2 && Game.setZone == 2) {
						Text.curIndex = false;
						Text.arteView = true;
					    Text.curText = 3;
						Game.gameState = "TEXT";
						Killer.killerCheck = true;
					    Text.frases = true;
					    Text.showMessage = true;
					}
					if(Game.CUR_LEVEL == 2 && Game.setZone == 4) {
						Game.spritesheetArtes = new Spritesheet("/art/"+42+".png");
						Artes.ARTS0 = Game.spritesheetArtes.getArte(Game.ART_WIDTH, Game.ART_HEIGHT);
					    Sound.diminuirTema = true;
					    Text.arteView = true;
					    Text.curText = 3;
						Game.gameState = "TEXT";
						Sally.SallyCheck = true;
					    Text.frases = true;
					    Text.showMessage = true;
					}
				}
			}
			}
		}
	}
	
	public void tick() {
		tranzition();
	}
	
	public void render(Graphics g) {
			if(arteView == true) {
			g.drawImage(Artes.TRANZI, xArte, 0, 1830*Game.SCALE, Toolkit.getDefaultToolkit().getScreenSize().height, null);
			}
	}
}
