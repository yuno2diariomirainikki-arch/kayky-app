package com.blueuniverse.graficos;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import com.blueuniverse.entitys.Bia;
import com.blueuniverse.entitys.Killer;
import com.blueuniverse.entitys.Muriel;
import com.blueuniverse.entitys.Sally;
import com.blueuniverse.main.Game;

/**
 * Interface (HUD) desenhada na resolucao interna (426x240, depois ampliada).
 * - Painel do jogador: Vida (com "rastro" de dano), Mana, Dano acumulado e barra da Bia (com cargas)
 * - Barras dos chefes (Dujhin, Sally, Muriel) no topo, centralizadas
 */
public class Ui {

	// valores "suavizados" (a barra anda ate o valor real) e rastros de dano
	private double lifeShown = -1, lifeTrail = -1;
	private double manaShown = -1;
	private double danoShown = -1;
	private double biaShown = -1;

	// chefes: 0 = Dujhin (Killer), 1 = Sally, 2 = Muriel
	private final double[] bossShown = {-1, -1, -1};
	private final double[] bossTrail = {-1, -1, -1};
	private final double[] bossMax = {350, 150, 900};

	private Font fontePixel;
	private int frame = 0;
	private int bossY0 = 40; // y onde comecam as barras dos chefes (abaixo do painel do jogador)

	private static final String[] ICONE_VIDA = {
		".XX.XX.",
		"XXXXXXX",
		"XXXXXXX",
		".XXXXX.",
		"..XXX..",
		"...X...",
	};
	private static final String[] ICONE_MANA = {
		"...X...",
		"..XXX..",
		".XXXXX.",
		"XXXXXXX",
		".XXXXX.",
		"..XXX..",
		"...X...",
	};
	private static final String[] ICONE_DANO = {
		"...XX",
		"..XX.",
		".XXXX",
		"..XX.",
		".XX..",
		"XX...",
	};
	private static final String[] ICONE_CURA = {
		".XXX.",
		".XXX.",
		"XXXXX",
		"XXXXX",
		".XXX.",
		".XXX.",
	};
	private static final String[] ICONE_CAVEIRA = {
		".XXXXX.",
		"XXXXXXX",
		"X.XXX.X",
		"XXXXXXX",
		".XXXXX.",
		".X.X.X.",
	};

	private Font fonte() {
		if(fontePixel == null) {
			// fonte pequena e legivel na resolucao interna (a Pixel_Font fica ilegivel em tamanho tao pequeno)
			fontePixel = new Font(Font.SANS_SERIF, Font.BOLD, 8);
		}
		return fontePixel;
	}
	
	private static double clamp01(double v) {
		if(v < 0) return 0;
		if(v > 1) return 1;
		return v;
	}

	/** aproxima "atual" do "alvo" suavemente */
	private static double suavizar(double atual, double alvo, double vel) {
		if(atual < 0) return alvo;
		double d = alvo - atual;
		if(Math.abs(d) < 0.002) return alvo;
		return atual + d*vel;
	}

	// ---------- desenho basico ----------

	private static void icone(Graphics g, String[] mapa, int x, int y, Color cor, Color sombra) {
		for(int yy = 0; yy < mapa.length; yy++) {
			for(int xx = 0; xx < mapa[yy].length(); xx++) {
				if(mapa[yy].charAt(xx) == 'X') {
					g.setColor(sombra);
					g.fillRect(x + xx + 1, y + yy + 1, 1, 1);
				}
			}
		}
		for(int yy = 0; yy < mapa.length; yy++) {
			for(int xx = 0; xx < mapa[yy].length(); xx++) {
				if(mapa[yy].charAt(xx) == 'X') {
					g.setColor(cor);
					g.fillRect(x + xx, y + yy, 1, 1);
				}
			}
		}
	}

	private static Color mix(Color a, Color b, float t) {
		return new Color(
			(int)(a.getRed() + (b.getRed()-a.getRed())*t),
			(int)(a.getGreen() + (b.getGreen()-a.getGreen())*t),
			(int)(a.getBlue() + (b.getBlue()-a.getBlue())*t));
	}

	/** moldura escura + fundo */
	private static void moldura(Graphics g, int x, int y, int w, int h, Color borda) {
		g.setColor(new Color(0,0,0,230));
		g.fillRect(x-2, y-1, w+4, h+2);
		g.fillRect(x-1, y-2, w+2, h+4);
		g.setColor(borda);
		g.fillRect(x-1, y-1, w+2, h+2);
		g.setColor(new Color(14,14,22));
		g.fillRect(x, y, w, h);
	}

	/** enche a barra com gradiente vertical, brilho no topo e divisorias */
	private static void preencher(Graphics g, int x, int y, int w, int h, Color topo, Color base, int divisoes) {
		if(w <= 0) return;
		for(int i = 0; i < h; i++) {
			float t = h <= 1 ? 0 : (float)i/(h-1);
			g.setColor(mix(topo, base, t));
			g.fillRect(x, y+i, w, 1);
		}
		g.setColor(new Color(255,255,255,90));
		g.fillRect(x, y, w, 1);
	}
	
	private static void divisorias(Graphics g, int x, int y, int w, int h, int n) {
		g.setColor(new Color(0,0,0,80));
		for(int d = 1; d < n; d++) {
			g.fillRect(x + (int)((long)w*d/n), y, 1, h);
		}
	}

	private static void textoSombra(Graphics g, String s, int x, int y, Color cor) {
		g.setColor(new Color(0,0,0,220));
		g.drawString(s, x+1, y+1);
		g.setColor(cor);
		g.drawString(s, x, y);
	}

	/**
	 * barra completa.
	 * @param ratio  valor atual (0..1, ja suavizado)
	 * @param rastro valor do rastro de dano (>= ratio) ou -1 para nao desenhar
	 */
	private void barra(Graphics g, int x, int y, int w, int h, double ratio, double rastro,
			Color topo, Color base, Color borda, Color corRastro, int divisoes) {
		moldura(g, x, y, w, h, borda);
		int fw = (int)Math.round(w * clamp01(ratio));
		if(rastro > ratio) {
			int rw = (int)Math.round(w * clamp01(rastro));
			g.setColor(corRastro);
			g.fillRect(x + fw, y, Math.max(0, rw - fw), h);
		}
		preencher(g, x, y, fw, h, topo, base, 0);
		if(divisoes > 1) divisorias(g, x, y, w, h, divisoes);
	}

	// ---------- HUD do jogador ----------

	public void render(Graphics g) {
		frame++;
		Font fonteAntiga = g.getFont();
		g.setFont(fonte());

		double vida = clamp01(Game.player.life / Game.player.maxlife);
		double mana = clamp01(Game.player.ammo / Game.player.maxAmmo);
		double dano = clamp01(Game.player.dano / Game.player.maxDano);

		lifeShown = suavizar(lifeShown, vida, 0.25);
		manaShown = suavizar(manaShown, mana, 0.25);
		danoShown = suavizar(danoShown, dano, 0.25);
		// rastro: sobe junto se a vida aumenta; desce devagar depois de levar dano
		if(lifeTrail < 0 || vida > lifeTrail) lifeTrail = vida;
		else lifeTrail = Math.max(vida, lifeTrail - 0.004);

		boolean mostrarBia = ((Bia.especialBia == true && Game.CUR_LEVEL == 2) && Game.setZone == 5) || Game.setZone == 6;

		int px = 5, py = 5;
		int w = 78;
		int linhas = mostrarBia ? 4 : 3;
		int alturaPainel = 30 + (mostrarBia ? 11 : 0);

		// painel de fundo translucido
		g.setColor(new Color(6,6,14,150));
		g.fillRect(px-2, py-2, w+24, alturaPainel);
		g.setColor(new Color(255,255,255,40));
		g.fillRect(px-2, py-2, w+24, 1);
		g.setColor(new Color(0,0,0,120));
		g.fillRect(px-2, py-2+alturaPainel-1, w+24, 1);

		int bx = px + 10;

		// --- VIDA ---
		boolean critica = vida < 0.25 && vida > 0;
		float pulso = critica ? (float)(0.5 + 0.5*Math.sin(frame*0.25)) : 0f;
		Color hp1 = Color.getHSBColor((float)(0.01 + 0.30*lifeShown), 0.85f, 1.0f);
		Color hp2 = Color.getHSBColor((float)(0.01 + 0.30*lifeShown), 0.95f, 0.62f);
		Color bordaVida = critica ? mix(new Color(70,70,90), new Color(255,40,40), pulso) : new Color(70,70,90);
		icone(g, ICONE_VIDA, px, py, critica ? mix(new Color(255,90,110), Color.white, pulso) : new Color(255,80,100), new Color(60,0,10));
		barra(g, bx, py, w-12, 8, lifeShown, lifeTrail, hp1, hp2, bordaVida, new Color(255,235,235,200), 5);
		String txtVida = (int)Math.max(0, Math.ceil(Game.player.life)) + "";
		textoSombra(g, txtVida, bx + w - 8, py + 7, Color.white);

		// --- MANA ---
		int my = py + 11;
		icone(g, ICONE_MANA, px, my, new Color(90,200,255), new Color(0,20,60));
		barra(g, bx, my + 1, w-12, 6, manaShown, -1, new Color(120,225,255), new Color(30,90,230), new Color(60,70,100), null, 5);

		// --- DANO ACUMULADO ---
		int dy = py + 21;
		icone(g, ICONE_DANO, px + 1, dy, new Color(255,215,90), new Color(60,30,0));
		barra(g, bx, dy + 1, w-12, 4, danoShown, -1, new Color(255,245,170), new Color(240,150,30), new Color(70,60,50), null, 5);

		// --- BIA (cura especial) ---
		if(mostrarBia) {
			double especial = clamp01(Bia.especial / 100.0);
			biaShown = suavizar(biaShown, especial, 0.2);
			boolean pronto = Bia.especial >= 90 && Bia.curEspecial >= 1;
			int by = py + 32;
			Color cura = pronto ? mix(new Color(255,150,230), Color.white, (float)(0.5 + 0.5*Math.sin(frame*0.2))) : new Color(255,120,215);
			icone(g, ICONE_CURA, px + 1, by, cura, new Color(60,0,50));
			barra(g, bx, by + 1, w-12, 5, biaShown, -1, new Color(255,170,240), new Color(170,40,190),
				pronto ? new Color(255,200,245) : new Color(90,60,100), null, 0);
			// cargas (bolinhas)
			int cargas = (int)Math.max(0, Math.floor(Bia.curEspecial));
			int maxCargas = (int)Bia.maxCurEspecial;
			for(int i = 0; i < maxCargas; i++) {
				int cx = bx + w - 9 + i*6;
				g.setColor(new Color(0,0,0,220));
				g.fillRect(cx - 1, by, 5, 5);
				g.setColor(i < cargas ? new Color(255,140,235) : new Color(60,40,70));
				g.fillRect(cx, by + 1, 3, 3);
				if(i < cargas) {
					g.setColor(new Color(255,255,255,170));
					g.fillRect(cx, by + 1, 1, 1);
				}
			}
		}

		// --- CHEFES ---
		bossY0 = py - 2 + alturaPainel + 4;
		int slot = 0;
		if(Game.gameState == "NORMAL" || Game.gameState == "TEXT") {
			if(!Game.killers.isEmpty() && Killer.killerCheck == false && Killer.life > 0
					&& Game.setZone == 2 && Game.CUR_LEVEL == 2) { // so na fase em que o Dujhin morre
				slot = chefe(g, slot, 0, "DUJHIN", Killer.life, new Color(255,120,70), new Color(170,20,20));
			}
			if(!Game.sallys.isEmpty() && Sally.SallyCheck == false && Sally.life > 0
					&& Game.setZone == 4 && Game.CUR_LEVEL == 2) { // so na fase em que a Sally morre
				slot = chefe(g, slot, 1, "SALLY", Sally.life, new Color(255,120,220), new Color(150,20,140));
			}
			if(!Game.muriels.isEmpty() && Muriel.MurielCheck == false && Muriel.life > 0
					&& ((Game.setZone == 6 && Game.CUR_LEVEL == 1) || (Game.setZone == 5 && Game.CUR_LEVEL == 3))) { // fases em que a Muriel morre
				bossMax[2] = Math.max(bossMax[2], Muriel.maxlife);
				slot = chefe(g, slot, 2, "MURIEL", Muriel.life, new Color(255,190,70), new Color(190,70,10));
			}
		}
		if(slot == 0) {
			// nenhum chefe na tela: reinicia os rastros
			for(int i = 0; i < 3; i++) { bossShown[i] = -1; bossTrail[i] = -1; }
		}

		g.setFont(fonteAntiga);
	}

	/** desenha uma barra de chefe no canto SUPERIOR DIREITO (espelho do painel do jogador). Retorna o proximo slot livre. */
	private int chefe(Graphics g, int slot, int id, String nome, double vida, Color claro, Color escuro) {
		if(vida > bossMax[id]) bossMax[id] = vida;
		double ratio = clamp01(vida / bossMax[id]);
		bossShown[id] = suavizar(bossShown[id], ratio, 0.15);
		if(bossTrail[id] < 0 || ratio > bossTrail[id]) bossTrail[id] = ratio;
		else bossTrail[id] = Math.max(ratio, bossTrail[id] - 0.003);
		
		int w = 78, h = 8;
		// largura realmente visivel na tela, em pixels internos (a imagem e ampliada por ZOOM no canvas SCALE)
		int visivel = (Game.WIDTH * Game.SCALE) / Game.ZOOM;
		int iconeX = visivel - 5 - 7;           // icone na borda direita (espelho do coracao do jogador)
		int x = iconeX - 3 - w;                 // barra logo a esquerda do icone
		int y = 16 + slot * 21;                 // mesma altura do painel do jogador (+nome acima)
		
		FontMetrics fm = g.getFontMetrics();
		// painel de fundo, espelhado
		int pl = visivel - 3 - (w + 24);
		g.setColor(new Color(6,6,14,150));
		g.fillRect(pl, y - 13, w + 24, h + 17);
		g.setColor(new Color(255,255,255,40));
		g.fillRect(pl, y - 13, w + 24, 1);
		
		icone(g, ICONE_CAVEIRA, iconeX, y + 1, claro, new Color(30,0,0));
		// nome alinhado a direita
		textoSombra(g, nome, x + w - fm.stringWidth(nome), y - 4, Color.white);
		
		// barra enchendo da direita para a esquerda
		moldura(g, x, y, w, h, new Color(110,90,100));
		int fw = (int)Math.round(w * clamp01(bossShown[id]));
		if(bossTrail[id] > bossShown[id]) {
			int rw = (int)Math.round(w * clamp01(bossTrail[id]));
			g.setColor(new Color(255,235,220,210));
			g.fillRect(x + w - rw, y, Math.max(0, rw - fw), h);
		}
		preencher(g, x + w - fw, y, fw, h, claro, escuro, 0);
		divisorias(g, x, y, w, h, 5);
		
		String pct = (int)Math.ceil(ratio*100) + "%";
		int pw = fm.stringWidth(pct);
		textoSombra(g, pct, x + (w - pw)/2, y + h - 1, new Color(255,255,255,230));
		return slot + 1;
	}

}
