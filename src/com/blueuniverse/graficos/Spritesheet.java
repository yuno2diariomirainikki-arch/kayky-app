package com.blueuniverse.graficos;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

public class Spritesheet {
	
	// Cache: antes cada "new Spritesheet" decodificava o PNG de novo na thread do jogo
	// (principalmente as artes 988x555 das cutscenes), causando travadas.
	// SoftReference deixa a JVM descartar se faltar memoria.
	private static final Map<String, SoftReference<BufferedImage>> cache = new HashMap<String, SoftReference<BufferedImage>>();
	
	private BufferedImage spritesheet;
	
	public Spritesheet(String path){
		spritesheet = load(path);
	}
	
	private static BufferedImage load(String path) {
		synchronized(cache) {
			SoftReference<BufferedImage> ref = cache.get(path);
			BufferedImage img = (ref != null) ? ref.get() : null;
			if(img != null) return img;
		}
		BufferedImage img = null;
		try {
			img = ImageIO.read(Spritesheet.class.getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(img != null) {
			synchronized(cache) {
				cache.put(path, new SoftReference<BufferedImage>(img));
			}
		}
		return img;
	}
	
	/** Decodifica as imagens em uma thread de fundo e deixa no cache. */
	public static void preloadAsync(final String... paths) {
		Thread t = new Thread(new Runnable() {
			public void run() {
				for(String p : paths) {
					try { load(p); } catch (Throwable e) { /* ignora */ }
				}
			}
		}, "preload-artes");
		t.setDaemon(true);
		t.setPriority(Thread.MIN_PRIORITY);
		t.start();
	}
	
	public BufferedImage getSprite(int x, int y, int width, int height){
		return spritesheet.getSubimage(x, y, width, height);
	}

	private BufferedImage arteCache;
	private int arteW = -1, arteH = -1;
	
	/**
	 * Devolve a imagem INTEIRA ajustada a largura x altura pedidas, mantendo a proporcao (sobra uma faixa preta
	 * se a proporcao for diferente). Assim as artes podem ter qualquer resolucao: nada e cortado nem estourado.
	 * Se a imagem ja tem o tamanho exato, e devolvida como estava.
	 */
	public BufferedImage getArte(int width, int height) {
		if(spritesheet == null) return null;
		int iw = spritesheet.getWidth(), ih = spritesheet.getHeight();
		if(iw == width && ih == height) return spritesheet;
		if(arteCache != null && arteW == width && arteH == height) return arteCache;
		double esc = Math.min(width/(double)iw, height/(double)ih);
		int dw = Math.max(1, (int)Math.round(iw*esc)), dh = Math.max(1, (int)Math.round(ih*esc));
		// reducao em etapas (no maximo pela metade por vez) para ficar nitida, sem serrilhado
		BufferedImage atual = spritesheet;
		int cw = iw, ch = ih;
		while(cw/2 >= dw && ch/2 >= dh) {
			cw = cw/2; ch = ch/2;
			atual = redimensionar(atual, cw, ch);
		}
		BufferedImage fim = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		java.awt.Graphics2D g = fim.createGraphics();
		g.setColor(java.awt.Color.black);
		g.fillRect(0, 0, width, height);
		g.setRenderingHint(java.awt.RenderingHints.KEY_INTERPOLATION, java.awt.RenderingHints.VALUE_INTERPOLATION_BICUBIC);
		g.setRenderingHint(java.awt.RenderingHints.KEY_RENDERING, java.awt.RenderingHints.VALUE_RENDER_QUALITY);
		g.drawImage(atual, (width - dw)/2, (height - dh)/2, dw, dh, null);
		g.dispose();
		arteCache = fim; arteW = width; arteH = height;
		return fim;
	}
	
	private static BufferedImage redimensionar(BufferedImage src, int w, int h) {
		BufferedImage r = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		java.awt.Graphics2D g = r.createGraphics();
		g.setRenderingHint(java.awt.RenderingHints.KEY_INTERPOLATION, java.awt.RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g.drawImage(src, 0, 0, w, h, null);
		g.dispose();
		return r;
	}

}
