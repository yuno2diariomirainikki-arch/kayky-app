package com.blueuniverse.graficos;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.RadialGradientPaint;
import java.awt.RenderingHints;
import java.awt.geom.Point2D;

import com.blueuniverse.main.Game;

/** Efeitos das telas de Game Over e Menu (tudo em Java2D, animado pelo relogio). */
public class Telas {
	
	private static long inicioGameOver = 0;
	private static boolean estavaEmGameOver = false;
	private static final long T0 = System.currentTimeMillis();
	
	private static double agora() {
		return (System.currentTimeMillis() - T0) / 1000.0;
	}
	
	private static Font fontePixel(float tam) {
		if(Game.newfont != null) return Game.newfont.deriveFont(tam);
		return new Font(Font.SERIF, Font.BOLD, (int)tam);
	}
	
	/** desenha texto letra a letra com espacamento extra; retorna a largura total */
	private static int textoEspacado(Graphics2D g, String t, int x, int y, int espaco, boolean desenhar) {
		FontMetrics fm = g.getFontMetrics();
		int cx = x;
		for(int i = 0; i < t.length(); i++) {
			String c = t.substring(i, i+1);
			if(desenhar) g.drawString(c, cx, y);
			cx += fm.stringWidth(c) + espaco;
		}
		return cx - x - espaco;
	}
	
	private static final java.util.Map<Long, java.awt.image.BufferedImage> cacheLuz = new java.util.HashMap<Long, java.awt.image.BufferedImage>();
	
	/** disco de luz pre-calculado (centro opaco -> borda transparente) */
	private static java.awt.image.BufferedImage discoLuz(int raio, int rgb) {
		long key = ((long)raio << 32) | (rgb & 0xFFFFFFL);
		java.awt.image.BufferedImage img = cacheLuz.get(key);
		if(img != null) return img;
		int d = raio*2;
		img = new java.awt.image.BufferedImage(d, d, java.awt.image.BufferedImage.TYPE_INT_ARGB);
		for(int y = 0; y < d; y++) {
			for(int x = 0; x < d; x++) {
				float dx = x + 0.5f - raio, dy = y + 0.5f - raio;
				float t = (float)Math.sqrt(dx*dx + dy*dy) / raio;
				if(t > 1) t = 1;
				float a = (1 - t); a = a*a;
				img.setRGB(x, y, ((int)(a*255) << 24) | (rgb & 0xFFFFFF));
			}
		}
		cacheLuz.put(key, img);
		return img;
	}
	
	private static void luzRadial(Graphics2D g, float cx, float cy, float raio, Color centro, float alfaMax) {
		if(alfaMax <= 0) return;
		if(alfaMax > 1) alfaMax = 1;
		int r = (int)raio;
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alfaMax));
		g.drawImage(discoLuz(r, centro.getRGB()), (int)cx - r, (int)cy - r, null);
		g.setComposite(AlphaComposite.SrcOver);
	}
	
	private static java.awt.image.BufferedImage vinhetaGO, vinhetaMenu;
	private static int vgw, vgh, vmw, vmh;
	
	private static java.awt.image.BufferedImage gerarVinheta(int w, int h, float cxRel, float raioRel, int[] argbCentro, int[] argbMeio, int[] argbBorda, float meio) {
		java.awt.image.BufferedImage img = new java.awt.image.BufferedImage(w, h, java.awt.image.BufferedImage.TYPE_INT_ARGB);
		Graphics2D gi = img.createGraphics();
		gi.setPaint(new RadialGradientPaint(new Point2D.Float(w*cxRel, h/2f), Math.max(w, h)*raioRel,
			new float[]{0f, meio, 1f},
			new Color[]{new Color(argbCentro[0],argbCentro[1],argbCentro[2],argbCentro[3]), new Color(argbMeio[0],argbMeio[1],argbMeio[2],argbMeio[3]), new Color(argbBorda[0],argbBorda[1],argbBorda[2],argbBorda[3])}));
		gi.fillRect(0, 0, w, h);
		gi.dispose();
		return img;
	}
	
	private static float ruido(double t, double semente) {
		// "numero aleatorio" suave e deterministico
		return (float)(0.5 + 0.5*(Math.sin(t*1.7 + semente)*0.5 + Math.sin(t*3.1 + semente*2.3)*0.3 + Math.sin(t*7.3 + semente*0.7)*0.2));
	}
	
	/** particulas (brasas) subindo; cor e velocidade variam por particula */
	private static void brasas(Graphics2D g, int w, int h, double t, int qtd, Color cor, float alfaGeral) {
		for(int i = 0; i < qtd; i++) {
			double vel = 18 + (i*37 % 40);
			double x0 = (i * 211) % w;
			double y = h - ((t*vel + i*97) % (h + 20)) + 10;
			double x = x0 + Math.sin(t*0.8 + i) * 14;
			float vida = (float)(y / h);                       // some perto do topo
			float a = Math.min(1f, vida*1.4f) * alfaGeral * (0.4f + 0.6f*ruido(t*2, i));
			int tam = 2 + (i % 3);
			g.setColor(new Color(cor.getRed(), cor.getGreen(), cor.getBlue(), (int)(255*Math.max(0, Math.min(1, a)))));
			g.fillRect((int)x, (int)y, tam, tam);
		}
	}
	
	// ---------------------------------------------------------------- GAME OVER
	
	public static void gameOver(Graphics2D g, int w, int h, String idioma) {
		if(!estavaEmGameOver) {
			estavaEmGameOver = true;
			inicioGameOver = System.currentTimeMillis();
		}
		double t = (System.currentTimeMillis() - inicioGameOver) / 1000.0;
		float fade = (float)Math.min(1.0, t / 0.9);
		double tg = agora();
		
		Object aaTexto = g.getRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING);
		Object aa = g.getRenderingHint(RenderingHints.KEY_ANTIALIASING);
		java.awt.Paint paintAntigo = g.getPaint();
		java.awt.Composite compAntigo = g.getComposite();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		// escurece a cena e tinge de vermelho nas bordas
		if(vinhetaGO == null || vgw != w || vgh != h) {
			vgw = w; vgh = h;
			vinhetaGO = gerarVinheta(w, h, 0.5f, 0.78f, new int[]{0,0,0,165}, new int[]{20,0,4,185}, new int[]{70,0,12,238}, 0.5f);
		}
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, fade));
		g.drawImage(vinhetaGO, 0, 0, null);
		g.setComposite(AlphaComposite.SrcOver);
		
		// brilho vermelho pulsando atras do titulo
		float pulso = 0.5f + 0.5f*(float)Math.sin(tg*2.2);
		luzRadial(g, w/2f, h/2f - 10, 330, new Color(220, 20, 40), (0.20f + 0.12f*pulso) * fade);
		
		brasas(g, w, h, tg, 46, new Color(255, 110, 60), 0.9f*fade);
		
		// titulo "GAME OVER": entra aumentando e clareando
		String titulo = "GAME OVER";
		float ease = (float)(1 - Math.pow(1 - Math.min(1.0, t/0.7), 3));
		float escala = 1.35f - 0.35f*ease;
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		Font f = new Font(Font.SERIF, Font.BOLD, (int)(104 * escala));
		g.setFont(f);
		FontMetrics fm = g.getFontMetrics();
		int espaco = (int)(14 * escala);
		int tw = textoEspacado(g, titulo, 0, 0, espaco, false);
		int tx = (w - tw)/2;
		int ty = h/2 + fm.getAscent()/3 - 6;
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, fade));
		// halo vermelho pulsando
		g.setColor(new Color(255, 30, 50, (int)(30 + 22*pulso)));
		int[][] offs = {{-5,0},{5,0},{0,-5},{0,5}};
		for(int[] o : offs) textoEspacado(g, titulo, tx + o[0], ty + o[1], espaco, true);
		// sombra e preenchimento em gradiente (branco-rosado -> vermelho escuro)
		g.setColor(new Color(0, 0, 0, 230));
		textoEspacado(g, titulo, tx + 5, ty + 5, espaco, true);
		g.setPaint(new GradientPaint(0, ty - fm.getAscent()*0.75f, new Color(255, 240, 240), 0, ty, new Color(205, 18, 38)));
		textoEspacado(g, titulo, tx, ty, espaco, true);
		g.setComposite(compAntigo);
		
		// ornamento: linha com losango
		int ly = ty + 34;
		int lw = Math.min(tw, 520) / 2;
		float la = fade * Math.min(1f, (float)Math.max(0, (t - 0.4) / 0.6));
		if(la > 0) {
			g.setPaint(new GradientPaint(w/2f - lw, 0, new Color(255,60,70,0), w/2f - 14, 0, new Color(255,90,100,(int)(230*la))));
			g.fillRect(w/2 - lw, ly, lw - 14, 2);
			g.setPaint(new GradientPaint(w/2f + 14, 0, new Color(255,90,100,(int)(230*la)), w/2f + lw, 0, new Color(255,60,70,0)));
			g.fillRect(w/2 + 14, ly, lw - 14, 2);
			g.setColor(new Color(255, 120, 130, (int)(255*la)));
			g.fill(new Polygon(new int[]{w/2, w/2 + 8, w/2, w/2 - 8}, new int[]{ly - 7, ly + 1, ly + 9, ly + 1}, 4));
		}
		
		// instrucao piscando (aparece depois de ~1s)
		if(t > 1.0) {
			String sub = "PT".equals(idioma) ? "Pressione ENTER para tentar novamente" : "Press ENTER to try again";
			g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
			g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 22));
			FontMetrics fm2 = g.getFontMetrics();
			float a = (float)(0.45 + 0.55*(0.5 + 0.5*Math.sin(t*3.4)));
			int sx = (w - fm2.stringWidth(sub))/2;
			int sy = ly + 52;
			g.setColor(new Color(0, 0, 0, (int)(200*a)));
			g.drawString(sub, sx + 2, sy + 2);
			g.setColor(new Color(255, 235, 235, (int)(255*a)));
			g.drawString(sub, sx, sy);
		}
		
		g.setPaint(paintAntigo);
		g.setComposite(compAntigo);
		if(aaTexto != null) g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, aaTexto);
		if(aa != null) g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, aa);
	}
	
	/** chamar quando NAO estiver em game over, para a animacao recomecar na proxima vez */
	public static void saiuDoGameOver() {
		estavaEmGameOver = false;
	}
	
	// ---------------------------------------------------------------- MENU
	
	/** fator de "lampada falhando": quase sempre alto, com quedas bruscas ocasionais */
	public static float piscar(double t) {
		float base = 0.80f + 0.12f*(float)Math.sin(t*5.0) + 0.08f*(float)Math.sin(t*13.0);
		// a cada ~3.5s ocorre uma falha curta e dupla
		double ciclo = t % 3.5;
		if((ciclo > 2.60 && ciclo < 2.68) || (ciclo > 2.80 && ciclo < 2.86)) base *= 0.25f;
		else if(ciclo > 2.68 && ciclo < 2.80) base *= 0.9f;
		return Math.max(0.1f, Math.min(1f, base));
	}
	
	/** luzes por cima da arte do menu: brilho dos olhos, luz do capuz, escuridao que pisca e poeira */
	public static void menuLuz(Graphics2D g, int w, int h) {
		double t = agora();
		float f = piscar(t);
		java.awt.Paint paintAntigo = g.getPaint();
		java.awt.Composite compAntigo = g.getComposite();
		
		// brilho dos olhos magenta (posicoes na arte 1740x1080)
		luzRadial(g, 1245, 590, 170, new Color(255, 40, 255), 0.55f*f);
		luzRadial(g, 1500, 612, 170, new Color(255, 40, 255), 0.55f*f);
		// luz verde do capuz
		luzRadial(g, 1250, 120, 460, new Color(120, 255, 40), 0.20f*f);
		luzRadial(g, 1000, 800, 380, new Color(90, 255, 60), 0.12f*f);
		// escurece quando a luz falha (e um leve "respirar" constante)
		float escuro = (1f - f)*0.70f + 0.06f;
		g.setColor(new Color(0, 0, 0, (int)(255*Math.min(0.85f, escuro))));
		g.fillRect(0, 0, w, h);
		// vinheta (pre-calculada)
		if(vinhetaMenu == null || vmw != w || vmh != h) {
			vmw = w; vmh = h;
			vinhetaMenu = gerarVinheta(w, h, 0.62f, 0.75f, new int[]{0,0,0,0}, new int[]{0,0,0,40}, new int[]{0,0,0,190}, 0.6f);
		}
		g.drawImage(vinhetaMenu, 0, 0, null);
		
		brasas(g, w, h, t, 34, new Color(150, 255, 90), 0.8f*f);
		
		g.setPaint(paintAntigo);
		g.setComposite(compAntigo);
	}
	
	/** titulo do menu com brilho verde pulsante */
	public static void menuTitulo(Graphics2D g, String texto, int x, int y) {
		double t = agora();
		float f = piscar(t);
		Object aaTexto = g.getRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING);
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 34));
		int larg = textoEspacado(g, texto, 0, 0, 2, false);
		g.setColor(new Color(110, 255, 50, (int)(40*f)));
		int[][] offs = {{-3,0},{3,0},{0,-3},{0,3}};
		for(int[] o : offs) textoEspacado(g, texto, x + o[0], y + o[1], 2, true);
		g.setColor(new Color(0, 0, 0, 220));
		textoEspacado(g, texto, x + 3, y + 3, 2, true);
		g.setColor(new Color(235, 255, 225, (int)(215 + 40*f)));
		textoEspacado(g, texto, x, y, 2, true);
		// linha de luz sob o titulo
		g.setPaint(new GradientPaint(x, 0, new Color(130, 255, 70, (int)(210*f)), x + larg, 0, new Color(130, 255, 70, 0)));
		g.fillRect(x, y + 14, larg, 2);
		if(aaTexto != null) g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, aaTexto);
	}
	
	/** uma opcao do menu; a selecionada brilha e tem seta animada */
	public static void menuOpcao(Graphics2D g, String texto, int x, int y, boolean selecionada) {
		double t = agora();
		float f = piscar(t);
		Object aaTexto = g.getRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING);
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, selecionada ? 26 : 21));
		if(selecionada) {
			FontMetrics fm = g.getFontMetrics();
			int tw = fm.stringWidth(texto);
			// faixa de luz atras da opcao
			g.setPaint(new GradientPaint(x - 34, 0, new Color(120, 255, 60, (int)(70*f)), x + tw + 90, 0, new Color(120, 255, 60, 0)));
			g.fillRect(x - 34, y - fm.getAscent() - 2, tw + 124, fm.getAscent() + 14);
			// seta
			int bob = (int)(3*Math.sin(t*6));
			g.setColor(new Color(150, 255, 90, (int)(200 + 55*f)));
			g.fill(new Polygon(new int[]{x - 26 + bob, x - 26 + bob, x - 14 + bob}, new int[]{y - 16, y, y - 8}, 3));
			g.setColor(new Color(110, 255, 50, (int)(45*f)));
			g.drawString(texto, x - 3, y); g.drawString(texto, x + 3, y); g.drawString(texto, x, y - 3); g.drawString(texto, x, y + 3);
			g.setColor(new Color(0, 0, 0, 220));
			g.drawString(texto, x + 2, y + 2);
			g.setColor(new Color(245, 255, 235, (int)(215 + 40*f)));
		}else {
			g.setColor(new Color(0, 0, 0, 200));
			g.drawString(texto, x + 2, y + 2);
			g.setColor(new Color(185, 190, 200, 190));
		}
		g.drawString(texto, x, y);
		if(aaTexto != null) g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, aaTexto);
	}

	// ---------------------------------------------------------------- MENU: SLOTS DE SAVE
	
	/** uma linha da lista de slots. esq = "Slot 1", meio = zona/ACT (ou "Vazio"), dir = jogador (colorido) */
	public static void menuSlot(Graphics2D g, int x, int y, int w, int h, String esq, String meio, String dir, Color corDir, boolean selecionado, boolean preenchido) {
		double t = agora();
		float f = piscar(t);
		Object aaTexto = g.getRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING);
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g.setColor(new Color(4, 8, 4, selecionado ? 215 : 165));
		g.fillRect(x, y, w, h);
		if(selecionado) {
			g.setPaint(new GradientPaint(x, 0, new Color(120, 255, 60, (int)(95*f)), x + w, 0, new Color(120, 255, 60, 0)));
			g.fillRect(x, y, w, h);
			g.setColor(new Color(140, 255, 80, (int)(120 + 110*f)));
			g.drawRect(x, y, w - 1, h - 1);
			int bob = (int)(3*Math.sin(t*6));
			g.setColor(new Color(150, 255, 90, (int)(200 + 55*f)));
			g.fill(new Polygon(new int[]{x + 8 + bob, x + 8 + bob, x + 18 + bob}, new int[]{y + h/2 - 7, y + h/2 + 7, y + h/2}, 3));
		}else {
			g.setColor(new Color(90, 110, 90, 70));
			g.drawRect(x, y, w - 1, h - 1);
		}
		g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, selecionado ? 20 : 18));
		FontMetrics fm = g.getFontMetrics();
		int by = y + (h + fm.getAscent() - fm.getDescent())/2;
		int tx = x + 30;
		g.setColor(new Color(0, 0, 0, 200));
		g.drawString(esq, tx + 2, by + 2);
		g.setColor(selecionado ? new Color(245, 255, 235) : new Color(200, 205, 210));
		g.drawString(esq, tx, by);
		int mx = x + 140;
		g.setColor(new Color(0, 0, 0, 200));
		g.drawString(meio, mx + 2, by + 2);
		g.setColor(preenchido ? (selecionado ? new Color(255, 255, 255) : new Color(225, 230, 235)) : new Color(130, 135, 145));
		g.drawString(meio, mx, by);
		if(dir != null) {
			int dx = x + w - 24 - fm.stringWidth(dir);
			g.setColor(new Color(0, 0, 0, 200));
			g.drawString(dir, dx + 2, by + 2);
			g.setColor(corDir);
			g.drawString(dir, dx, by);
		}
		if(aaTexto != null) g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, aaTexto);
	}
	
	/** fundo da caixa de opcoes (Sobrescrever / Carregar / Apagar / Cancelar) */
	public static void menuCaixa(Graphics2D g, int x, int y, int w, int h) {
		float f = piscar(agora());
		g.setColor(new Color(0, 0, 0, 120));
		g.fillRect(x - 6, y - 6, w + 12, h + 12);
		g.setColor(new Color(5, 9, 5, 240));
		g.fillRect(x, y, w, h);
		g.setPaint(new GradientPaint(0, y, new Color(120, 255, 60, (int)(40*f)), 0, y + h, new Color(120, 255, 60, 0)));
		g.fillRect(x, y, w, h);
		g.setColor(new Color(140, 255, 80, (int)(140 + 90*f)));
		g.drawRect(x, y, w - 1, h - 1);
		g.setColor(new Color(140, 255, 80, (int)(40*f)));
		g.drawRect(x - 2, y - 2, w + 3, h + 3);
	}
	
	/** texto simples centralizado em torno de cx, com sombra */
	public static void menuTextoCentro(Graphics2D g, String texto, int cx, int y, int tam, Color cor) {
		Object aaTexto = g.getRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING);
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, tam));
		int tw = g.getFontMetrics().stringWidth(texto);
		g.setColor(new Color(0, 0, 0, 210));
		g.drawString(texto, cx - tw/2 + 2, y + 2);
		g.setColor(cor);
		g.drawString(texto, cx - tw/2, y);
		if(aaTexto != null) g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, aaTexto);
	}
}
