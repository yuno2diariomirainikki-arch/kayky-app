package com.blueuniverse.graficos;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.blueuniverse.entitys.Bia;
import com.blueuniverse.entitys.Boneco;
import com.blueuniverse.entitys.Entity;
import com.blueuniverse.entitys.Espinhos;
import com.blueuniverse.entitys.Flocos;
import com.blueuniverse.entitys.Bullet;
import com.blueuniverse.entitys.Lifepack;
import com.blueuniverse.entitys.Lili;
import com.blueuniverse.entitys.Weapon;
import com.blueuniverse.entitys.Npc1;
import com.blueuniverse.entitys.Player;
import com.blueuniverse.main.Game;

/**
 * Pos-processamento (Java2D, sem GPU):
 *  1) nevoa animada em 2 camadas (cor por zona)
 *  2) brilho colorido em volta do jogador, inimigos, chefes e disparos
 *  3) escuridao ambiente por zona, com "buracos de luz" onde ha fontes de luz
 * Tudo que e pesado e pre-calculado/cacheado; por frame so desenha imagens pequenas.
 */
public class Efeitos {

	public static boolean ativo = true;

	private static final int TEX = 128;
	private static BufferedImage[] tingidasA = new BufferedImage[7];
	private static BufferedImage[] tingidasB = new BufferedImage[7];
	private static long tick = 0;

	// nevoa: cor e intensidade por zona (indice = setZone)
	private static final int[][] COR = {
		{200,200,210}, {190,205,225}, {170,120,210}, { 90,130,140}, {200, 80, 95}, {225,238,255}, {150,210,180},
	};
	private static final float[] FORCA = {0.26f, 0.23f, 0.27f, 0.29f, 0.27f, 0.36f, 0.27f};

	// escuridao ambiente: cor RGB e opacidade (0..255) por zona
	private static final int[][] AMBIENTE = {
		{ 8,10,28}, { 8,10,30}, {20, 6,34}, { 4,14,20}, {30, 5,12}, {10,18,42}, { 6,22,18},
	};
	private static final int[] ESCURIDAO = {165, 170, 180, 195, 182, 160, 185};

	private static BufferedImage lightmap;
	private static Graphics2D lg;
	private static int lw = 0, lh = 0;

	private static final Map<Integer, BufferedImage> buracos = new HashMap<Integer, BufferedImage>();
	private static final Map<Long, BufferedImage> brilhos = new HashMap<Long, BufferedImage>();

	static {
		BufferedImage a = gerarRuido(1234L, 4);
		BufferedImage b = gerarRuido(98765L, 3);
		for(int z = 0; z < 7; z++) {
			tingidasA[z] = tingir(a, COR[z]);
			tingidasB[z] = tingir(b, COR[z]);
		}
	}

	// ---------- geracao de texturas ----------

	private static BufferedImage gerarRuido(long seed, int baseCells) {
		Random r = new Random(seed);
		float[] v = new float[TEX*TEX];
		float amp = 1f, total = 0f;
		for(int oct = 0; oct < 4; oct++) {
			int cells = baseCells << oct;
			float[] grid = new float[cells*cells];
			for(int i = 0; i < grid.length; i++) grid[i] = r.nextFloat();
			for(int y = 0; y < TEX; y++) {
				for(int x = 0; x < TEX; x++) {
					float gx = (float)x / TEX * cells;
					float gy = (float)y / TEX * cells;
					int x0 = (int)gx, y0 = (int)gy;
					float fx = gx - x0, fy = gy - y0;
					fx = fx*fx*(3-2*fx); fy = fy*fy*(3-2*fy);
					int x1 = (x0+1) % cells, y1 = (y0+1) % cells;
					x0 %= cells; y0 %= cells;
					float a = grid[x0 + y0*cells], b = grid[x1 + y0*cells];
					float c = grid[x0 + y1*cells], d = grid[x1 + y1*cells];
					float top = a + (b-a)*fx, bot = c + (d-c)*fx;
					v[x + y*TEX] += (top + (bot-top)*fy) * amp;
				}
			}
			total += amp;
			amp *= 0.5f;
		}
		BufferedImage img = new BufferedImage(TEX, TEX, BufferedImage.TYPE_INT_ARGB);
		for(int i = 0; i < v.length; i++) {
			float n = v[i] / total;
			n = (n - 0.38f) / 0.5f;
			if(n < 0) n = 0; if(n > 1) n = 1;
			n = n*n*(3-2*n);
			int g = (int)(n*255);
			img.setRGB(i % TEX, i / TEX, (g << 24) | 0xFFFFFF);
		}
		return img;
	}

	private static BufferedImage tingir(BufferedImage mask, int[] cor) {
		BufferedImage out = new BufferedImage(TEX, TEX, BufferedImage.TYPE_INT_ARGB);
		for(int y = 0; y < TEX; y++) {
			for(int x = 0; x < TEX; x++) {
				int a = (mask.getRGB(x, y) >>> 24);
				out.setRGB(x, y, (a << 24) | (cor[0] << 16) | (cor[1] << 8) | cor[2]);
			}
		}
		return out;
	}

	/** disco branco com borda suave (alfa 255 no centro -> 0 na borda), usado para apagar a escuridao */
	private static BufferedImage buraco(int raio) {
		BufferedImage img = buracos.get(raio);
		if(img != null) return img;
		int d = raio*2;
		img = new BufferedImage(d, d, BufferedImage.TYPE_INT_ARGB);
		for(int y = 0; y < d; y++) {
			for(int x = 0; x < d; x++) {
				float dx = x + 0.5f - raio, dy = y + 0.5f - raio;
				float t = (float)Math.sqrt(dx*dx + dy*dy) / raio;
				if(t > 1) t = 1;
				float f = 1 - t;
				f = f*f*(3-2*f);          // smoothstep invertido
				img.setRGB(x, y, ((int)(f*255) << 24) | 0xFFFFFF);
			}
		}
		buracos.put(raio, img);
		return img;
	}

	/** brilho colorido (centro intenso, cai rapido) */
	private static BufferedImage brilho(int raio, int rgb) {
		long key = ((long)raio << 32) | (rgb & 0xFFFFFFL);
		BufferedImage img = brilhos.get(key);
		if(img != null) return img;
		int d = raio*2;
		img = new BufferedImage(d, d, BufferedImage.TYPE_INT_ARGB);
		for(int y = 0; y < d; y++) {
			for(int x = 0; x < d; x++) {
				float dx = x + 0.5f - raio, dy = y + 0.5f - raio;
				float t = (float)Math.sqrt(dx*dx + dy*dy) / raio;
				if(t > 1) t = 1;
				float f = (1 - t);
				f = f*f*f;
				img.setRGB(x, y, ((int)(f*255) << 24) | (rgb & 0xFFFFFF));
			}
		}
		brilhos.put(key, img);
		return img;
	}

	// ---------- fontes de luz ----------

	private static void luz(Graphics2D g, int cx, int cy, int raioLuz, float forcaLuz, int raioBrilho, int rgb, float forcaBrilho, int largura, int altura) {
		if(cx + raioLuz < 0 || cy + raioLuz < 0 || cx - raioLuz > largura || cy - raioLuz > altura) return;
		// brilho colorido sobre a cena
		if(raioBrilho > 0 && forcaBrilho > 0) {
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, forcaBrilho));
			g.drawImage(brilho(raioBrilho, rgb), cx - raioBrilho, cy - raioBrilho, null);
		}
		// buraco na escuridao
		if(forcaLuz > 0) {
			lg.setComposite(AlphaComposite.getInstance(AlphaComposite.DST_OUT, forcaLuz));
			lg.drawImage(buraco(raioLuz), cx - raioLuz, cy - raioLuz, null);
		}
	}

	private static void grupo(Graphics2D g, List<? extends Entity> lista, int camX, int camY,
			int raioLuz, float forcaLuz, int raioBrilho, int rgb, float forcaBrilho, int largura, int altura) {
		if(lista == null) return;
		for(int i = 0; i < lista.size(); i++) {
			Entity e = lista.get(i);
			if(e == null) continue;
			int cx = e.getX() - camX + e.getWidth()/2;
			int cy = e.getY() - camY + e.getHeight()/2;
			luz(g, cx, cy, raioLuz, forcaLuz, raioBrilho, rgb, forcaBrilho, largura, altura);
		}
	}

	// ---------- render ----------

	public static void render(Graphics2D g, int zona, int px, int py, int camX, int camY, int largura, int altura) {
		if(!ativo) return;
		tick++;
		if(zona < 1 || zona > 6) zona = 1;

		if(lightmap == null || lw != largura || lh != altura) {
			lw = largura; lh = altura;
			lightmap = new BufferedImage(largura, altura, BufferedImage.TYPE_INT_ARGB);
			lg = lightmap.createGraphics();
		}

		Object oldInterp = g.getRenderingHint(RenderingHints.KEY_INTERPOLATION);
		Composite oldComp = g.getComposite();
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

		// 2+3) escuridao ambiente + luzes
		int[] amb = AMBIENTE[zona];
		lg.setComposite(AlphaComposite.Src);
		lg.setColor(new Color(amb[0], amb[1], amb[2], ESCURIDAO[zona]));
		lg.fillRect(0, 0, largura, altura);

		float tremulo = 0.94f + 0.03f*(float)Math.sin(tick*0.07) + 0.02f*(float)Math.sin(tick*0.19);

		// jogador: luz grande e suave + nucleo brilhante
		boolean bia = (Player.setPlayer == 2);
		int corPlayer = bia ? 0xFFB4E6 : 0xFFF0C0;
		luz(g, px, py, 92, 0.97f*tremulo, 40, corPlayer, 0.45f, largura, altura);
		luz(g, px, py, 26, 0.55f, 20, 0xFFFFFF, 0.30f, largura, altura);

		// inimigos comuns (vermelho)
		int vermelho = 0xFF3C3C;
		grupo(g, Game.enemies,  camX, camY, 40, 0.85f, 26, vermelho, 0.50f, largura, altura);
		grupo(g, Game.enemies2, camX, camY, 40, 0.85f, 26, vermelho, 0.50f, largura, altura);
		grupo(g, Game.enemies3, camX, camY, 40, 0.85f, 26, vermelho, 0.50f, largura, altura);
		grupo(g, Game.vampiros, camX, camY, 40, 0.85f, 26, 0xE0305A, 0.50f, largura, altura);
		grupo(g, Game.flocos,   camX, camY, 40, 0.85f, 26, 0x9FE8FF, 0.50f, largura, altura);
		grupo(g, Game.aboboras, camX, camY, 44, 0.85f, 30, 0xFF9A1E, 0.55f, largura, altura);
		grupo(g, Game.aboboras2,camX, camY, 40, 0.85f, 26, 0xFF9A1E, 0.50f, largura, altura);
		grupo(g, Game.lusions,  camX, camY, 60, 0.9f,  36, 0xC8B4FF, 0.55f, largura, altura);
		// chefes (luz maior e mais forte)
		grupo(g, Game.killers,  camX, camY, 66, 0.95f, 40, 0xFF4A2A, 0.60f, largura, altura);
		grupo(g, Game.sallys,   camX, camY, 66, 0.95f, 40, 0xFF5AC8, 0.60f, largura, altura);
		grupo(g, Game.muriels,  camX, camY, 66, 0.95f, 40, 0xFFA03C, 0.60f, largura, altura);
		// aliados e bonecos de treino: percorre TODAS as entidades (e nao as listas), porque o Kalhiko e a Bia
		// reaparecem como objetos novos que nao entram nas listas, e o brilho sumia.
		for(int i = 0; i < Game.entities.size(); i++) {
			Entity e = Game.entities.get(i);
			int cx = e.getX() - camX + e.getWidth()/2;
			int cy = e.getY() - camY + e.getHeight()/2;
			if(e instanceof Bia) {
				luz(g, cx, cy, 44, 0.85f, 28, 0xFFB4E6, 0.55f, largura, altura);
			}else if(e instanceof Npc1) {
				luz(g, cx, cy, 44, 0.85f, 28, 0xFFE6B4, 0.50f, largura, altura);
			}else if(e instanceof Lili) {
				luz(g, cx, cy, 44, 0.85f, 28, 0xB4FFD2, 0.50f, largura, altura);
			}else if(e instanceof Espinhos) {
				// espinhos: brilho azul-gelo pulsando, cada um com fase diferente
				float pulso = 0.5f + 0.5f*(float)Math.sin(tick*0.08 + e.getX()*0.37 + e.getY()*0.21);
				luz(g, cx, cy, 36, 0.80f, 26, 0x5AB4FF, 0.40f + 0.30f*pulso, largura, altura);
			}else if(e instanceof Lifepack) {
				// item de vida: brilho rosado-vermelho pulsando
				float pulso = 0.5f + 0.5f*(float)Math.sin(tick*0.10 + e.getX()*0.3);
				luz(g, cx, cy, 40, 0.80f, 30, 0xFF5A78, 0.55f + 0.30f*pulso, largura, altura);
				luz(g, cx, cy, 14, 0.5f, 12, 0xFFFFFF, 0.35f*pulso, largura, altura);
			}else if(e instanceof Bullet) {
				// mana espalhado pelo mapa: brilho verde-agua forte e bem visivel, com centro branco e "piscadas"
				float pulso = 0.5f + 0.5f*(float)Math.sin(tick*0.12 + e.getX()*0.31 + e.getY()*0.17);
				luz(g, cx, cy, 56, 0.95f, 40, 0x3CFFA0, 0.70f + 0.30f*pulso, largura, altura);
				luz(g, cx, cy, 22, 0.80f, 16, 0xD8FFEC, 0.55f + 0.35f*pulso, largura, altura);
			}else if(e instanceof Weapon) {
				// item de mana: brilho ciano pulsando
				float pulso = 0.5f + 0.5f*(float)Math.sin(tick*0.10 + e.getY()*0.3);
				luz(g, cx, cy, 40, 0.80f, 30, 0x4CD2FF, 0.55f + 0.30f*pulso, largura, altura);
				luz(g, cx, cy, 14, 0.5f, 12, 0xFFFFFF, 0.35f*pulso, largura, altura);
			}else if(e instanceof Flocos && ((Flocos)e).getDashEstado() > 0) {
				// inimigo de gelo preparando/dando o dash: clarao branco-azulado (aviso para o jogador)
				float aviso = (((Flocos)e).getDashEstado() == 1) ? (0.55f + 0.45f*(float)Math.sin(tick*0.6)) : 1f;
				luz(g, cx, cy, 58, 0.95f, 40, 0xCFF0FF, 0.85f*Math.max(0.3f, aviso), largura, altura);
			}else if(e instanceof Boneco) {
				luz(g, cx, cy, 40, 0.85f, 26, 0xFFD27A, 0.50f, largura, altura);
			}
		}
		
		// disparos (pequenos e bem brilhantes)
		grupo(g, Game.bullets,      camX, camY, 36, 0.95f, 26, 0x78E6FF, 0.75f, largura, altura);
		grupo(g, Game.punchs,       camX, camY, 34, 0.9f,  24, 0xFFBE5A, 0.70f, largura, altura);
		grupo(g, Game.atacks,       camX, camY, 36, 0.95f, 26, 0xFFE664, 0.75f, largura, altura);
		grupo(g, Game.atacksSally,  camX, camY, 36, 0.95f, 26, 0xFF5AC8, 0.75f, largura, altura);
		grupo(g, Game.atacksMuriel, camX, camY, 36, 0.95f, 26, 0xFF9632, 0.75f, largura, altura);

		// aplica a escuridao (com os buracos de luz) sobre a cena
		g.setComposite(AlphaComposite.SrcOver);
		g.drawImage(lightmap, 0, 0, null);

		// nevoa desenhada por cima da escuridao, para aparecer mesmo nas partes escuras
				float forca = FORCA[zona];
		desenharCamada(g, tingidasA[zona], 3.0f, (tick*0.10f) + camX*0.35f, (tick*0.03f) + camY*0.35f, forca, largura, altura);
		desenharCamada(g, tingidasB[zona], 2.0f, -(tick*0.22f) + camX*0.7f, (tick*0.05f) + camY*0.7f, forca*0.8f, largura, altura);

		g.setComposite(oldComp);
		if(oldInterp != null) g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, oldInterp);
	}

	private static void desenharCamada(Graphics2D g, BufferedImage tex, float escala, float ox, float oy, float alpha, int w, int h) {
		int tam = (int)(TEX * escala);
		int offX = (int)(-(ox % tam)); if(offX > 0) offX -= tam;
		int offY = (int)(-(oy % tam)); if(offY > 0) offY -= tam;
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
		for(int y = offY; y < h; y += tam) {
			for(int x = offX; x < w; x += tam) {
				g.drawImage(tex, x, y, tam, tam, null);
			}
		}
	}
}
