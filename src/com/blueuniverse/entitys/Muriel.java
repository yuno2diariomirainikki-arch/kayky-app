package com.blueuniverse.entitys;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import com.blueuniverse.graficos.Artes;
import com.blueuniverse.graficos.Spritesheet;
import com.blueuniverse.main.Game;
import com.blueuniverse.main.Sound;
import com.blueuniverse.main.Text;
import com.blueuniverse.main.Tranzi;
import com.blueuniverse.world.Astar;
import com.blueuniverse.world.Camera;
import com.blueuniverse.world.Vector2i;
import com.blueuniverse.world.World;

public class Muriel extends Entity{

	private double speed = 1.2;
	private int maskx = 8, masky = 8, maskw = 12, maskh =12;
	public static int frames = 0;
	public static int maxFrames = 7;
	public static int index = 0;
	public static int maxIndex = 3;
	public static int dirMuriel = 1;
	
	private BufferedImage[] sprites;
	private BufferedImage[] sprites2;
	private BufferedImage[] sprites3;
	private BufferedImage[] sprites4;
	
	public static double life = 900;
	public static double maxlife = 900;
	
	private boolean isDamage = false;
	private int damageFrames = 10, damageCurrent = 0;
	
	private boolean alvo = false;
	public static boolean atack = false;
	private int timeAtack = 0, maxAtack = 60*3, fogoAtack = 0, maxFogoAtack = 2;
	
	private boolean alvoMuriel = false;
	public static boolean MurielCheck = true;
	
	public static int XX;
	public static int YY;
	
	public static boolean isMurielLazer = false;
	public static double murielLazserTimer = 10;
	public static int xTarget;
	public static int yTarget;

	public Muriel(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, null);
		
		Muriel.XX = x;
		//x = (int) XX;
		//y = (int) YY;
		Muriel.YY = y;
		//Muriel.xTarget = x;
		//Muriel.yTarget =y;
		
		sprites = new BufferedImage[4];
		sprites[0] = Game.spritesheetMuriel.getSprite(0, 0, 16, 16);
		sprites[1] = Game.spritesheetMuriel.getSprite(16, 0, 16, 16);
		sprites[2] = Game.spritesheetMuriel.getSprite(16*2, 0, 16, 16);
		sprites[3] = Game.spritesheetMuriel.getSprite(16*3, 0, 16, 16);
		
		sprites2 = new BufferedImage[4];
		sprites2[0] = Game.spritesheetMuriel.getSprite(0, 16, 16, 16);
		sprites2[1] = Game.spritesheetMuriel.getSprite(16, 16, 16, 16);
		sprites2[2] = Game.spritesheetMuriel.getSprite(16*2, 16, 16, 16);
		sprites2[3] = Game.spritesheetMuriel.getSprite(16*3, 16, 16, 16);
		
		sprites3 = new BufferedImage[4];
		sprites3[0] = Game.spritesheetMuriel.getSprite(0, 16*2, 16, 16);
		sprites3[1] = Game.spritesheetMuriel.getSprite(16, 16*2, 16, 16);
		sprites3[2] = Game.spritesheetMuriel.getSprite(16*2, 16*2, 16, 16);
		sprites3[3] = Game.spritesheetMuriel.getSprite(16*3, 16*2, 16, 16);
		
		sprites4 = new BufferedImage[4];
		sprites4[0] = Game.spritesheetMuriel.getSprite(0, 16*3, 16, 16);
		sprites4[1] = Game.spritesheetMuriel.getSprite(16, 16*3, 16, 16);
		sprites4[2] = Game.spritesheetMuriel.getSprite(16*2, 16*3, 16, 16);
		sprites4[3] = Game.spritesheetMuriel.getSprite(16*3, 16*3, 16, 16);
		// TODO Auto-generated constructor stub
	}
	
	public void setX(int newX) {
		Muriel.XX = newX;
	}
	
	public void setY(int newY) {
		Muriel.YY = newY;
	}
	
	public int getX(){
		return (int)Muriel.XX;
	}
	
	public int getY(){
		return (int)Muriel.YY;
	}
		
	public void tick() {
		depth = 3;
		maskx = 2;
		masky = 2;
		maskw = 10;
		maskh = 10;
		
		if(life > maxlife) {
			life = maxlife;
		}
		
		if((Game.setZone == 5 || Game.setZone == 6) && (Game.CUR_LEVEL == 1 || Game.CUR_LEVEL == 3) && MurielCheck == true) {
			if(this.calculateDistance(this.getX(), this.getY(), Game.player.getX(), Game.player.getY()) < 16*5) {
				alvoMuriel = true;
			}else {
				alvoMuriel = false;
			}
			
			if(this.calculateDistance(this.getX(), this.getY(), Game.player.getX(), Game.player.getY()) < 16*5 && alvoMuriel == true) {
				//Text.interage = true;
				Sound.diminuirTema = true;
				Text.arteView = true;
				Text.curText = 2;
				Game.gameState = "TEXT";
			    Text.frases = true;
			    Text.showMessage = true;
			}
		}
		
		
		if(!isColiddingWithPlayer()) {
		
		//agora algoritimo A* | inimigos inteligentes
		if(path == null || path.size() == 0) {
			Vector2i start = new Vector2i((int)(x/16), (int)(y/16));
			Vector2i end = new Vector2i((int)(Game.player.x/16), (int)(Game.player.y/16));
			path = Astar.findPath(Game.world, start, end);
		}
		}else {
			if(new Random().nextInt(100) < 5) {
				Sound.efect_hit.start();
				Game.player.life-=Game.rand.nextInt(16);
				Game.player.isDamaged = true;
			}
		}
		
		if(new Random().nextInt(100) < 70)
		    followPath(path);
		if(new Random().nextInt(100) < 2) {
			Vector2i start = new Vector2i((int)(x/16), (int)(y/16));
			Vector2i end = new Vector2i((int)(Game.player.x/16), (int)(Game.player.y/16));
			path = Astar.findPath(Game.world, start, end);
		}
		frames++;
		if(frames == maxFrames) {
			frames = 0;
			index++;
			if(index > maxIndex) 
				index = 0;
		}
		timeAtack++;
		if(timeAtack >= maxAtack) {
			timeAtack = 0;
			fogoAtack++;
			if(fogoAtack >= maxFogoAtack) {
				fogoAtack = 0;
				skillsMuriel();
			}
		}
		
		if(this.calculateDistance(this.getX(), this.getY(), Game.player.getX(), Game.player.getY()) < 350) {
			alvo = true;
		}else {
			alvo = false;
		}
		
		if(atack && alvo == true && MurielCheck == false) {
			atack = false;
			int dx = 0;
			int dy = 0;
			int px = 0;
			int py = 0;
			
			if(dirMuriel == 3) {
				dx = 1;
				px = 5;
				py = 4;
			}else if(dirMuriel == 4) {
				dx = -1;
				px = -3;
				py = 4;
			}
			if(dirMuriel == 2) {
				dy = -1;
				px = 1;
				py = 1;
			}else if(dirMuriel == 1) {
				dy = 1;
				px = -1;
				py = 5;
			}
			
			AtackShootMuriel atackMuriel = new AtackShootMuriel(this.getX()+px,this.getY()+py,4,4,null,dx,dy);
			Game.atacksMuriel.add(atackMuriel);

		}
		collidingBullet();
		
		if(life <= 0) {
			World.generateParticles(100, (int)x, (int)y);
			destroySelf();
			if(Game.CUR_LEVEL == 1 && Game.setZone == 6) {
			    destroySelf();
			    Tranzi.setTranzi = 2;
				Tranzi.StartArt();
				Game.gameState = "TRANZI";
				Game.spritesheetArtes = new Spritesheet("/art/"+60+".png");
				Artes.ARTS0 = Game.spritesheetArtes.getArte(Game.ART_WIDTH, Game.ART_HEIGHT);
			}
			if(Game.CUR_LEVEL == 3 && Game.setZone == 5) {
				Sound.diminuirTema = true;
				Text.arteView = true;
				Text.curText = 1;
				Text.xArte = 0;
				Game.gameState = "TEXT";
			    Text.frases = true;
			    Text.showMessage = true;
			    Bia.biaReset();
			}
			return;
		}
		
       collidingPunch();
		
		if(life <= 0) {
			destroySelf();
			World.generateParticles(100, (int)x, (int)y);
			if(Game.CUR_LEVEL == 1 && Game.setZone == 6) {
			    destroySelf();
			    Tranzi.setTranzi = 2;
				Tranzi.StartArt();
				Game.gameState = "TRANZI";
				Game.spritesheetArtes = new Spritesheet("/art/"+60+".png");
				Artes.ARTS0 = Game.spritesheetArtes.getArte(Game.ART_WIDTH, Game.ART_HEIGHT);
			}
			if(Game.CUR_LEVEL == 3 && Game.setZone == 5) {
				Sound.diminuirTema = true;
				Text.arteView = true;
				Text.curText = 1;
				Text.xArte = 0;
				Game.gameState = "TEXT";
			    Text.frases = true;
			    Text.showMessage = true;
			    Bia.biaReset();
			}
			return;
		}
		
		if(isDamage) {
			this.damageCurrent++;
			if(this.damageCurrent >= this.damageFrames) {
				this.damageCurrent = 0;
				this.isDamage = false;
			}
		}
		
		
		
		if(Muriel.isMurielLazer == true) {
			Muriel.xTarget = this.getX();
			Muriel.yTarget = this.getY();
			Muriel.murielLazserTimer -= 0.05;
			Game.player.life-= 0.1;
			if(Muriel.murielLazserTimer <= 0) {
				Muriel.isMurielLazer = false;
				Muriel.murielLazserTimer = 10;
			}
		}
	}
	
	public void skillsMuriel() {
		
		int selectSkill = new Random().nextInt(4);
		int skillPorcentagem = new Random().nextInt(100);
		
		if(selectSkill == 0) {
			atack = true;
		}else if(selectSkill == 1) {
			if(skillPorcentagem >= 80) {
				if(Game.aboboras.size() == 0) {
				World.generateParticles2(100, (int)x, (int)y);
				Abobora.life = 350;
				Abobora en = new Abobora(this.getX(),this.getY(),16,32,Entity.ABOBORA_EN);
				Game.entities.add(en);
				Game.aboboras.add(en);
				}else if(Game.aboboras.size() != 0) {
					World.generateParticles2(100, (int)x, (int)y);
					Abobora2 en = new Abobora2(this.getX(),this.getY(),16,32,Entity.ABOBORA2_EN);
					Game.entities.add(en);
					Game.aboboras2.add(en);
					Muriel.life += 50;
					}
			} else if(skillPorcentagem <= 79) {
				World.generateParticles2(100, (int)x, (int)y);
				Abobora2 en = new Abobora2(this.getX(),this.getY(),16,32,Entity.ABOBORA2_EN);
				Game.entities.add(en);
				Game.aboboras2.add(en);
				Muriel.life += 50;
			}
		}else if(selectSkill == 2) {
			atack = true;
			if(skillPorcentagem >= 20) {
				World.generateParticles2(100, (int)x, (int)y);
				Abobora2 en = new Abobora2(this.getX(),this.getY(),16,32,Entity.ABOBORA2_EN);
				Game.entities.add(en);
				Game.aboboras2.add(en);
				}
		}else if(selectSkill == 3) {
			if(skillPorcentagem >= 50) {
				atack = true;
				World.generateParticles2(100, Game.player.getX(), Game.player.getY());
				isMurielLazer = true;
				Sound.efect_hit.start();
				
				World.generateParticles2(100, (int)x, (int)y);
				Abobora2 en = new Abobora2(this.getX(),this.getY(),16,32,Entity.ABOBORA2_EN);
				Game.entities.add(en);
				Game.aboboras2.add(en);
				
				Muriel.life += 50;
				}
		}
	}
	
	public void destroySelf() {
		Game.muriels.remove(this);
		Game.entities.remove(this);
	}
	
	public static void remove() {
		if(Game.CUR_LEVEL == 2 && Game.setZone == 5) {
			life = 1;
			life-=10;
			Game.muriels.clear();
		}
	}
	
	public static void murielReset() {
		life = maxlife;
	}
	
	//colision normal with retangle
	public void collidingBullet() {
		for(int i = 0; i < Game.bullets.size(); i++) {
			Entity e = Game.bullets.get(i);
			if(e instanceof BulletShoot) {
				if(Entity.isColidding(this, e)) {
					isDamage = true;
					if(Game.CUR_LEVEL == 1 && Game.setZone == 6) {
					life-=(Game.rand.nextInt(8));
					}
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
					if(Game.CUR_LEVEL == 1 && Game.setZone == 6) {
					life-=(Game.rand.nextInt(3))+ Game.player.dano;
					}
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
		for(int i = 0; i < Game.muriels.size(); i++) {
			Muriel e = Game.muriels.get(i);
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
		if(!isDamage) {
			if(dirMuriel == 1) {
			g.drawImage(sprites[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
			}
			if(dirMuriel == 2) {
				g.drawImage(sprites2[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
			}
			if(dirMuriel == 3) {
				g.drawImage(sprites3[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
			}
			if(dirMuriel == 4) {
				g.drawImage(sprites4[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
			}
		}else {
			if(dirMuriel == 1) {
				g.drawImage(Muriel.MURIEL_FEEDBACK, this.getX() - Camera.x, this.getY() - Camera.y, null);
			}
			if(dirMuriel == 2) {
				g.drawImage(Muriel.MURIEL_FEEDBACK2, this.getX() - Camera.x, this.getY() - Camera.y, null);
			}
			if(dirMuriel == 3) {
				g.drawImage(Muriel.MURIEL_FEEDBACK3, this.getX() - Camera.x, this.getY() - Camera.y, null);
			}
			if(dirMuriel == 4) {
				g.drawImage(Muriel.MURIEL_FEEDBACK4, this.getX() - Camera.x, this.getY() - Camera.y, null);
			}
		}
		//g.setColor(new Color(0,0,200));
		//g.fillRect((this.getX() - Camera.x)+ maskx,(this.getY() - Camera.y) + masky ,maskw,maskh);
	}
	
}