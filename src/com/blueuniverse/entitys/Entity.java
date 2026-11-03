package com.blueuniverse.entitys;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Comparator;
import java.util.List;

import com.blueuniverse.main.Game;
import com.blueuniverse.world.Camera;
import com.blueuniverse.world.Node;
import com.blueuniverse.world.Vector2i;
import com.blueuniverse.world.World;

public class Entity {
	
	public static BufferedImage LIFEPACK_EN = Game.spritesheet.getSprite(0, 16*6, 16, 16);
	public static BufferedImage WEAPON_EN = Game.spritesheet.getSprite(0, 16*7, 16, 16);
	public static BufferedImage BULLET_EN = Game.spritesheet.getSprite(0, 16*8, 16, 16);
	public static BufferedImage ENEMY_EN = Game.spritesheetEnemies.getSprite(0, 0, 16, 16);
	public static BufferedImage ENEMY_FEEDBACK = Game.spritesheetEnemies.getSprite(16*4, 0, 16, 16);
	public static BufferedImage ENEMY2_EN = Game.spritesheetEnemies.getSprite(0, 16, 16, 16);
	public static BufferedImage ENEMY2_FEEDBACK = Game.spritesheetEnemies.getSprite(16*4, 16, 16, 16);
	public static BufferedImage BARREIRA_ON = Game.spritesheetTileLv0.getSprite(16*2, 16, 16, 16);
	public static BufferedImage BARREIRA_OFF = Game.spritesheetTileLv0.getSprite(16*2, 16*3, 16, 16);
	public static BufferedImage BARREIRA2_ON = Game.spritesheetTileLv0.getSprite(16*2, 16, 16, 16);
	public static BufferedImage BARREIRA2_OFF = Game.spritesheetTileLv0.getSprite(16*2, 16*3, 16, 16);
	public static BufferedImage BOTAO_ON = Game.spritesheetTileLv0.getSprite(16*2, 16*2, 16, 16);
	public static BufferedImage BONECO_EN = Game.spritesheetTileLv0.getSprite(16*3, 0, 16, 16);
	public static BufferedImage BONECO_FEEDBACK = Game.spritesheetTileLv0.getSprite(16*3, 16, 16, 16);
	public static BufferedImage QUADRO_ON = Game.spritesheetTileLv3.getSprite(16*4, 16*4, 32, 16);
	public static BufferedImage SALLYCORPO_EN = Game.spritesheetSally.getSprite(16*5, 0, 16, 16);
	
	public static BufferedImage KILLER_EN = Game.spritesheetKiller.getSprite(0, 0, 16, 16);
	public static BufferedImage KILLER_FEEDBACK = Game.spritesheetKiller.getSprite(16*4, 0, 16, 16);
	public static BufferedImage KILLER_FEEDBACK2 = Game.spritesheetKiller.getSprite(16*4, 16, 16, 16);
	public static BufferedImage KILLER_FEEDBACK3 = Game.spritesheetKiller.getSprite(16*4, 16*2, 16, 16);
	public static BufferedImage KILLER_FEEDBACK4 = Game.spritesheetKiller.getSprite(16*4, 16*3, 16, 16);
	
	public static BufferedImage SALLY_EN = Game.spritesheetSally.getSprite(0, 0, 16, 16);
	public static BufferedImage SALLY_FEEDBACK = Game.spritesheetSally.getSprite(16*4, 0, 16, 16);
	public static BufferedImage SALLY_FEEDBACK2 = Game.spritesheetSally.getSprite(16*4, 16, 16, 16);
	public static BufferedImage SALLY_FEEDBACK3 = Game.spritesheetSally.getSprite(16*4, 16*2, 16, 16);
	public static BufferedImage SALLY_FEEDBACK4 = Game.spritesheetSally.getSprite(16*4, 16*3, 16, 16);
	
	public static BufferedImage MURIEL_EN = Game.spritesheetMuriel.getSprite(0, 0, 16, 16);
	public static BufferedImage MURIEL_FEEDBACK = Game.spritesheetMuriel.getSprite(16*4, 0, 16, 16);
	public static BufferedImage MURIEL_FEEDBACK2 = Game.spritesheetMuriel.getSprite(16*4, 16, 16, 16);
	public static BufferedImage MURIEL_FEEDBACK3 = Game.spritesheetMuriel.getSprite(16*4, 16*2, 16, 16);
	public static BufferedImage MURIEL_FEEDBACK4 = Game.spritesheetMuriel.getSprite(16*4, 16*3, 16, 16);
	
	public static BufferedImage ABOBORA_EN = Game.spritesheetAboboras.getSprite(0, 0, 16, 32);
	public static BufferedImage ABOBORA_FEEDBACK = Game.spritesheetAboboras.getSprite(16*4, 0, 16, 32);
	public static BufferedImage ABOBORA_FEEDBACK2 = Game.spritesheetAboboras.getSprite(16*4, 32, 16, 32);
	public static BufferedImage ABOBORA_FEEDBACK3 = Game.spritesheetAboboras.getSprite(16*4, 32*2, 16, 32);
	public static BufferedImage ABOBORA_FEEDBACK4 = Game.spritesheetAboboras.getSprite(16*4, 32*3, 16, 32);
	
	public static BufferedImage ABOBORA2_EN = Game.spritesheetAboboras2.getSprite(0, 0, 16, 16);
	public static BufferedImage ABOBORA2_FEEDBACK = Game.spritesheetAboboras2.getSprite(16*4, 0, 16, 16);
	public static BufferedImage ABOBORA2_FEEDBACK2 = Game.spritesheetAboboras2.getSprite(16*4, 16, 16, 16);
	
	public static BufferedImage ESPINHOS_EN = Game.spritesheetTileLv2.getSprite(16*2, 16*2, 16, 16);
	public static BufferedImage ESPINHOS_FEEDBACK = Game.spritesheetTileLv2.getSprite(16*3, 16*2, 16, 16);
	
	public static BufferedImage ARVORE_EN = Game.spritesheetTileLv3.getSprite(16*3, 16*2, 16, 32);
	public static BufferedImage ARVORE_FEEDBACK = Game.spritesheetTileLv3.getSprite(16*4, 16*2, 16, 32);
	
	public static BufferedImage PORTA_DIREITA = Game.spritesheetTileLv0.getSprite(16, 16*2, 16, 32);
	public static BufferedImage PORTA_ESQUERDA = Game.spritesheetTileLv0.getSprite(0, 16*2, 16, 32);
	
	public static BufferedImage MORCEGOS_EN = Game.spritesheetMorcegos.getSprite(0, 0, 16, 16);
	public static BufferedImage MORCEGOS_FEEDBACK = Game.spritesheetMorcegos.getSprite(16*4, 0, 16, 16);
	public static BufferedImage MORCEGOS_FEEDBACK2 = Game.spritesheetMorcegos.getSprite(16*4, 16, 16, 16);
	
	public static BufferedImage VAMPIROS_EN = Game.spritesheetVampiros.getSprite(0, 0, 16, 16);
	public static BufferedImage VAMPIROS_FEEDBACK = Game.spritesheetVampiros.getSprite(16*4, 0, 16, 16);
	
	public static BufferedImage LUSION_EN = Game.spritesheetLusions.getSprite(0, 0, 16, 32);
	public static BufferedImage LUSION_FEEDBACK = Game.spritesheetLusions.getSprite(16*4, 0, 16, 32);
	public static BufferedImage LUSION_FEEDBACK2 = Game.spritesheetLusions.getSprite(16*4, 32, 16, 32);
	public static BufferedImage LUSION_FEEDBACK3 = Game.spritesheetLusions.getSprite(16*4, 32*2, 16, 32);
	public static BufferedImage LUSION_FEEDBACK4 = Game.spritesheetLusions.getSprite(16*4, 32*3, 16, 32);
	
	public static BufferedImage FLOCOS_EN = Game.spritesheetFlocos.getSprite(0, 0, 16, 16);
	public static BufferedImage FLOCOS_FEEDBACK = Game.spritesheetFlocos.getSprite(16*4, 16, 16, 16);
	public static BufferedImage FLOCOS_FEEDBACK2 = Game.spritesheetFlocos.getSprite(16*4, 0, 16, 16);
	public static BufferedImage FLOCOS_FEEDBACK3 = Game.spritesheetFlocos.getSprite(16*4, 16*2, 16, 16);
	public static BufferedImage FLOCOS_FEEDBACK4 = Game.spritesheetFlocos.getSprite(16*4, 16*3, 16, 16);
	
	public static BufferedImage LILI_EN = Game.spritesheetLilis.getSprite(0, 0, 16, 16);
	public static BufferedImage LILI_FEEDBACK = Game.spritesheetLilis.getSprite(16*4, 16, 16, 16);
	public static BufferedImage LILI_FEEDBACK2 = Game.spritesheetLilis.getSprite(16*4, 0, 16, 16);
	public static BufferedImage LILI_FEEDBACK3 = Game.spritesheetLilis.getSprite(16*4, 16*2, 16, 16);
	public static BufferedImage LILI_FEEDBACK4 = Game.spritesheetLilis.getSprite(16*4, 16*3, 16, 16);
	
	public static BufferedImage BIA_EN = Game.spritesheetBias.getSprite(0, 0, 16, 16);
	public static BufferedImage NPC1_EN = Game.spritesheetNpcs.getSprite(0, 0, 16, 16);
	public static BufferedImage NPC1_FEEDBACK = Game.spritesheetNpcs.getSprite(16*4, 0, 16, 16);
	public static BufferedImage NPC1_FEEDBACK2 = Game.spritesheetNpcs.getSprite(16*4, 0, 16, 16);
	public static BufferedImage NPC1_FEEDBACK3 = Game.spritesheetNpcs.getSprite(16*4, 16*2, 16, 16);
	public static BufferedImage NPC1_FEEDBACK4 = Game.spritesheetNpcs.getSprite(16*4, 16*3, 16, 16);
	
	public static BufferedImage GUN_RIGHT = Game.spritesheet.getSprite(16, 16*7, 16, 16);
	public static BufferedImage GUN_LEFT = Game.spritesheet.getSprite(16*2, 16*7, 16, 16);
	public static BufferedImage GUN_DOWN = Game.spritesheet.getSprite(16*3, 16*7, 16, 16);
	public static BufferedImage GUN_UP = Game.spritesheet.getSprite(16*4, 16*7, 16, 16);
	

	public double x;
	public double y;
	protected int width;
	protected int height;
	
	public int depth;
	
	// empurrao (knockback) recebido de tiros do Kayky
	public double kbx = 0, kby = 0;
	
	/** empurra o inimigo na direcao (dx,dy) */
	public void knockback(double dx, double dy, double forca) {
		double len = Math.sqrt(dx*dx + dy*dy);
		if(len < 0.0001) return;
		kbx = dx/len*forca;
		kby = dy/len*forca;
	}
	
	/** chamado pelo tiro do jogador: so o Kayky empurra */
	public void knockbackDeBala(BulletShoot b) {
		if(Player.setPlayer == 1) {
			knockback(b.getDx(), b.getDy(), 4.2);
		}
	}
	
	/** aplica e reduz o empurrao a cada tick (respeita paredes) */
	public void applyKnockback() {
		if(kbx == 0 && kby == 0) return;
		double nx = x + kbx;
		if(World.isFree((int)nx, (int)y)) x = nx; else kbx = 0;
		double ny = y + kby;
		if(World.isFree((int)x, (int)ny)) y = ny; else kby = 0;
		kbx *= 0.8; kby *= 0.8;
		if(Math.abs(kbx) < 0.15) kbx = 0;
		if(Math.abs(kby) < 0.15) kby = 0;
	}
	
	protected List<Node> path;
	
	private BufferedImage sprite;
	
	public int maskx, masky, mwidth, mheight;
	
	public Entity(int x, int y, int width, int height,BufferedImage sprite) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.sprite = sprite;
		
		this.maskx = 0;
		this.masky =0;
		this.mwidth = width;
		this.mheight = height;
	}
	
     public static Comparator<Entity> nodeSorter = new Comparator<Entity>() {
		
		@Override
		
		public int compare(Entity n0, Entity n1) {
			if(n1.depth < n0.depth) return +1;
			if(n1.depth > n0.depth) return -1;
			return 0;
		}
		
	};
	
	public void setMask(int maskx, int masky, int mwidth, int mheight) {
		this.maskx = maskx;
		this.masky = masky;
		this.mwidth = mwidth;
		this.mheight = mheight;
	}
	
	public void setX(int newX) {
		this.x = newX;
	}
	
	public void setY(int newY) {
		this.y = newY;
	}
	
	public int getX(){
		return (int)this.x;
	}
	
	public int getY(){
		return (int)this.y;
	}
	
	public int getWidth(){
		return this.width;
	}
	
	public int getHeight(){
		return this.height;
	}
	
	public void tick() {
		
	}
	
	public boolean isColidding(int xnext, int ynext) {
		Rectangle enemyCurrent = new Rectangle(xnext + maskx,ynext + masky,mwidth,mheight);
		for(int i = 0; i < Game.enemies.size(); i++) {
			Enemy e = Game.enemies.get(i);
			if(e == this)
				continue;
			Rectangle targetEnemy = new Rectangle(e.getX() + maskx,e.getY() + masky,mwidth,mheight);
			if(enemyCurrent.intersects(targetEnemy)) {
				return true;
			}
			
		}
		
		return false;
	}
	
	
	public void followPath(List<Node> path) {
		if(path != null) {
			if(path.size() > 0) {
				Vector2i target = path.get(path.size() -1).tile;
				//xprev = x;
				//yprev = y;
				if(x < target.x*16 && !isColidding(this.getX()+1,this.getY())) {
					x++;
					Muriel.XX++;
					Killer.dirKiller = 3;
					Sally.dirSally = 3;
					Muriel.dirMuriel = 3;
					Npc1.dirNpc = 3;
				}else if(x > target.x*16 && !isColidding(this.getX()-1,this.getY()))  {
					x--;
					Muriel.XX--;
					Killer.dirKiller = 4;
					Sally.dirSally = 4;
					Muriel.dirMuriel = 4;
					Npc1.dirNpc = 4;
				}
				
				if(y < target.y*16 && !isColidding(this.getX(),this.getY()+1))  {
					y++;
					Muriel.YY++;
					Killer.dirKiller = 1;
					Sally.dirSally = 1;
					Muriel.dirMuriel = 1;
					Npc1.dirNpc = 1;
				}else if(y > target.y*16 && !isColidding(this.getX(),this.getY()-1)) {
					y--;
					Muriel.YY--;
					Killer.dirKiller = 2;
					Sally.dirSally = 2;
					Muriel.dirMuriel = 2;
					Npc1.dirNpc =2;
				}
				
				if(x == target.x*16 && y == target.y*16) {
					path.remove(path.size()-1);
				}
			}
		}
	}
	
	public double calculateDistance(int x1, int y1, int x2, int y2) {
		return Math.sqrt((x1-x2) * (x1-x2) + (y1-y2) * (y1-y2));
	}
	
	public static boolean isColidding(Entity e1, Entity e2) {
		Rectangle e1Mask = new Rectangle(e1.getX() + e1.maskx,e1.getY() + e1.masky,e1.mwidth,e1.mheight);
		Rectangle e2Mask = new Rectangle(e2.getX() + e2.maskx,e2.getY() + e2.masky,e2.mwidth,e2.mheight);
		
		return e1Mask.intersects(e2Mask);
	}
	
	public static boolean isColiddingAlto(Entity e1, Entity e2) {
		Rectangle e1Mask = new Rectangle(e1.getX() + e1.maskx,(e1.getY()-16) + e1.masky,e1.mwidth,e1.mheight);
		Rectangle e2Mask = new Rectangle(e2.getX() + e2.maskx,e2.getY() + e2.masky,e2.mwidth,e2.mheight);
		
		return e1Mask.intersects(e2Mask);
	}
	
	public void render(Graphics g) {
		g.drawImage(sprite, this.getX() - Camera.x, this.getY() -  Camera.y, null);
	}

}
