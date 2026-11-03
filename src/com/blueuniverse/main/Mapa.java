package com.blueuniverse.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import com.blueuniverse.graficos.Telas;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import com.blueuniverse.entitys.Player;
import com.blueuniverse.world.EndTile;
import com.blueuniverse.world.EndTile2;
import com.blueuniverse.world.EndTile3;
import com.blueuniverse.world.EndTile4;
import com.blueuniverse.world.WallTile;
import com.blueuniverse.world.World;


public class Mapa {

	public static boolean mapa = false;
	public static int curMapa = 0;
	
	//private BufferedImage[] mapas;

	
	public void tick() {
	}
	
	public static void renderMiniMap() {
		
		
		if(mapa == true) {
			for(int i = 0;  i < Game.minimapaPixels.length; i++) {
				Game.minimapaPixels[i] = 0;
			}
			for(int xx = 0;  xx < World.WIDTH; xx++) {
				for(int yy = 0;  yy < World.HEIGHT; yy++) {
					if(World.tiles[xx + (yy*World.WIDTH)] instanceof WallTile) {
						Game.minimapaPixels[xx + (yy*World.WIDTH)] = 0x404040;
					}
					if(World.tiles[xx + (yy*World.WIDTH)] instanceof EndTile) {
						Game.minimapaPixels[xx + (yy*World.WIDTH)] = 0x00ffff;
					}
					if(World.tiles[xx + (yy*World.WIDTH)] instanceof EndTile2) {
						Game.minimapaPixels[xx + (yy*World.WIDTH)] = 0x00ffff;
					}
					if(World.tiles[xx + (yy*World.WIDTH)] instanceof EndTile3) {
						Game.minimapaPixels[xx + (yy*World.WIDTH)] = 0x00ffff;
					}
					if(World.tiles[xx + (yy*World.WIDTH)] instanceof EndTile4) {
						Game.minimapaPixels[xx + (yy*World.WIDTH)] = 0x00ffff;
					}
				}
			}
			int xPlayer = Game.player.getX()/16;
			int yPlayer = Game.player.getY()/16;
			if(Player.setPlayer == 1) {
				Game.minimapaPixels[xPlayer + (yPlayer*World.WIDTH)] = 0x00ff00;
			}else if(Player.setPlayer == 2) {
				Game.minimapaPixels[xPlayer + (yPlayer*World.WIDTH)] = 0xB200FF;
			}
		}
		
	}
	
	// cache da imagem do mapa (so refaz quando troca de fase)
	private static BufferedImage cacheImg;
	private static Object cacheChave;
	private static java.util.List<int[]> saidas = new java.util.ArrayList<int[]>();
	
	private static boolean ehParede(int x, int y) {
		if(x < 0 || y < 0 || x >= World.WIDTH || y >= World.HEIGHT) return false;
		return World.tiles[x + y*World.WIDTH] instanceof WallTile;
	}
	
	private static boolean ehSaida(Object t) {
		return t instanceof EndTile || t instanceof EndTile2 || t instanceof EndTile3 || t instanceof EndTile4;
	}
	
	private static void montarImagem() {
		int w = World.WIDTH, h = World.HEIGHT;
		cacheImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		saidas.clear();
		for(int y = 0; y < h; y++) {
			for(int x = 0; x < w; x++) {
				Object t = World.tiles[x + y*w];
				int c;
				if(ehSaida(t)) {
					c = 0xFF2EDCFF;   // saida: azul-ciano, como no minimapa antigo
					saidas.add(new int[]{x, y});
				}else if(t instanceof WallTile) {
					boolean borda = !ehParede(x-1,y) && x > 0 || !ehParede(x+1,y) && x < w-1 || !ehParede(x,y-1) && y > 0 || !ehParede(x,y+1) && y < h-1;
					c = borda ? 0xFF5FD8A4 : 0xFF0B1016;   // contorno verde-agua; miolo da parede quase preto
				}else {
					// chao: azul-petroleo escuro com leve xadrez para dar textura
					c = ((x + y) & 1) == 0 ? 0xFF1B2A36 : 0xFF182530;
				}
				cacheImg.setRGB(x, y, c);
			}
		}
	}
	
	public void render(Graphics gr) {
		if(mapa != true) return;
		Graphics2D g = (Graphics2D) gr;
		boolean pt = Game.Idioma == "PT";
		int cw = Game.WIDTH*Game.SCALE, ch = Game.HEIGHT*Game.SCALE;
		double t = System.currentTimeMillis()/1000.0;
		
		if(cacheImg == null || cacheChave != World.tiles || cacheImg.getWidth() != World.WIDTH || cacheImg.getHeight() != World.HEIGHT) {
			montarImagem();
			cacheChave = World.tiles;
		}
		int mw = World.WIDTH, mh = World.HEIGHT;
		
		// escurece o jogo por tras
		g.setColor(new Color(0, 4, 8, 190));
		g.fillRect(0, 0, cw, ch);
		
		// maior escala inteira que cabe (pixels do mapa ficam nitidos)
		int esc = Math.max(1, Math.min(6, Math.min((cw - 120)/mw, (ch - 190)/mh)));
		int iw = mw*esc, ih = mh*esc;
		int pad = 14;
		int px = (cw - iw)/2 - pad, py = 86 - pad + (ch - 190 - ih)/2;
		int pw = iw + pad*2, ph = ih + pad*2;
		
		Telas.menuTitulo(g, pt ? "Mapa" : "Map", px, py - 14);
		Telas.menuCaixa(g, px, py, pw, ph);
		
		int ix = px + pad, iy = py + pad;
		g.drawImage(cacheImg, ix, iy, iw, ih, null);
		
		// brilho pulsante nas saidas
		float pulso = 0.5f + 0.5f*(float)Math.sin(t*3.0);
		int maxBrilho = Math.min(saidas.size(), 400);
		for(int i = 0; i < maxBrilho; i++) {
			int[] s = saidas.get(i);
			int cx = ix + s[0]*esc + esc/2, cy = iy + s[1]*esc + esc/2;
			g.setColor(new Color(46, 220, 255, (int)(34 + 50*pulso)));
			g.fillOval(cx - esc*2, cy - esc*2, esc*4, esc*4);
		}
		
		// jogador: halo pulsante + ponto com contorno (verde = Kayky, magenta = Bia)
		int tx = Game.player.getX()/16, ty = Game.player.getY()/16;
		if(tx >= 0 && ty >= 0 && tx < mw && ty < mh) {
			boolean bia = Player.setPlayer == 2;
			Color cor = bia ? new Color(255, 90, 235) : new Color(110, 255, 90);
			int cx = ix + tx*esc + esc/2, cy = iy + ty*esc + esc/2;
			int r = esc*3 + (int)(esc*2*pulso);
			g.setColor(new Color(cor.getRed(), cor.getGreen(), cor.getBlue(), (int)(40 + 50*(1-pulso))));
			g.fillOval(cx - r, cy - r, r*2, r*2);
			g.setColor(new Color(cor.getRed(), cor.getGreen(), cor.getBlue(), 120));
			g.drawOval(cx - r, cy - r, r*2, r*2);
			int rp = Math.max(3, esc + 1);
			g.setColor(Color.black);
			g.fillOval(cx - rp - 1, cy - rp - 1, rp*2 + 2, rp*2 + 2);
			g.setColor(cor);
			g.fillOval(cx - rp, cy - rp, rp*2, rp*2);
			g.setColor(Color.white);
			g.fillOval(cx - rp/2, cy - rp/2, Math.max(2, rp), Math.max(2, rp));
		}
		
		// legenda
		int ly = py + ph + 30;
		g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
		Color corJ = Player.setPlayer == 2 ? new Color(255, 90, 235) : new Color(110, 255, 90);
		int lx = cw/2 - 190;
		g.setColor(corJ);
		g.fillOval(lx, ly - 11, 12, 12);
		g.setColor(new Color(225, 232, 235));
		g.drawString(pt ? "Voc\u00ea" : "You", lx + 20, ly);
		g.setColor(new Color(46, 220, 255));
		g.fillRect(lx + 110, ly - 11, 12, 12);
		g.setColor(new Color(225, 232, 235));
		g.drawString(pt ? "Sa\u00edda" : "Exit", lx + 130, ly);
		g.setColor(new Color(95, 216, 164));
		g.fillRect(lx + 230, ly - 11, 12, 12);
		g.setColor(new Color(225, 232, 235));
		g.drawString(pt ? "Parede" : "Wall", lx + 250, ly);
		
		Telas.menuTextoCentro(g, pt ? "C para sair" : "C to exit", cw/2, ch - 26, 18, new Color(235, 245, 235));
	}
	
}
