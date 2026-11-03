package com.blueuniverse.main;

import java.io.*;
import java.util.Objects;

import javax.sound.sampled.*;

import com.blueuniverse.entitys.Killer;
import com.blueuniverse.entitys.Player;
import com.blueuniverse.world.World;

public class Sound {
	
	public static boolean bloqueio = false;
	public static boolean endTema = false;
	public static boolean startTema = false;
	public static boolean diminuirTema = false;
	public static boolean aumentarTema = false;
	public static int sadTheme = 1;
	public static double Gan = -80; // (informativo) volume atual em dB da musica que entra
	private static double maxGan = -80;
	private static double maxGanStart = 0;
	
	public static class Clips{
		public Clip[] clips;
		private int p;
		private int count;
		
		private FloatControl gainControl;
		private float volume;
		
		// Musicas grandes (.wav de 10-26 MB): carregadas sob demanda, uma copia so, e
		// descarregadas quando param. Antes TODAS eram abertas varias vezes na inicializacao
		// (~540 MB na memoria), o que enchia o heap e causava travadas de GC.
		private String lazyName;
		private boolean lazy = false;
		private boolean loading = false;
		private boolean wantLoop = false;
		
		public Clips(byte[] buffer, int count) throws LineUnavailableException, IOException, UnsupportedAudioFileException {
			if(buffer == null)
				return;
			open(buffer, count);
		}
		
		/** Clips de carregamento preguicoso (musicas). */
		public Clips(String resourceName) {
			this.lazy = true;
			this.lazyName = resourceName;
			this.count = 1;
		}
		
		private void open(byte[] buffer, int count) throws LineUnavailableException, IOException, UnsupportedAudioFileException {
			Clip[] opened = new Clip[count];
			for(int i = 0; i < count; i++) {
				opened[i] = AudioSystem.getClip();
				opened[i].open(AudioSystem.getAudioInputStream(new ByteArrayInputStream(buffer)));
			}
			FloatControl gc = (FloatControl) opened[0].getControl(FloatControl.Type.MASTER_GAIN);
			synchronized(this) {
				this.count = count;
				this.clips = opened;
				this.gainControl = gc;
				this.p = 0;
				if(volume != 0) {
					gc.setValue(Math.max(gc.getMinimum(), Math.min(gc.getMaximum(), volume)));
				}
			}
		}
		
		private void loadLazy() {
			byte[] data = readResource(lazyName);
			if(data == null) return;
			try {
				open(data, 1);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		public synchronized void play() {
			if(clips == null)return;
			clips[p].stop();
			clips[p].setFramePosition(0);
			clips[p].start();
			p++;
			if(p >= count) p = 0;
		}
		
		public void loop() {
			if(!lazy) {
				if(clips == null) return;
				clips[p].loop(300);
				return;
			}
			synchronized(this) {
				wantLoop = true;
				if(clips != null) {
					clips[0].loop(300);
					return;
				}
				if(loading) return;
				loading = true;
			}
			// decodifica o .wav em segundo plano para nao travar o jogo
			Thread t = new Thread(new Runnable() {
				public void run() {
					try {
						loadLazy();
					} finally {
						synchronized(Clips.this) {
							loading = false;
							if(wantLoop && clips != null) {
								clips[0].loop(300);
							}else if(!wantLoop && clips != null) {
								// parou antes de terminar de carregar: libera
								for(Clip c : clips) { try { c.close(); } catch(Exception e) {} }
								clips = null;
								gainControl = null;
							}
						}
					}
				}
			}, "carrega-musica");
			t.setDaemon(true);
			t.start();
		}
		
		public void start() {
			
			Sound.efect_aqua.setVolume((int)-5);
			Sound.efect_hit.setVolume((int)-5);
			Sound.efect_hit2.setVolume((int)-5);
			Sound.efect_explosao.setVolume((int)-10);
			Sound.efect_powerup.setVolume((int)-5);
			
			synchronized(this) {
				if(clips == null) return;
				clips[p].setFramePosition(0);
				clips[p].start();
			}
		}
		
		public void stop() {
			Clip[] toClose = null;
			synchronized(this) {
				wantLoop = false;
				if(clips == null) return;
				clips[p].stop();
				if(lazy) {
					// libera a memoria da musica que parou
					toClose = clips;
					clips = null;
					gainControl = null;
				}
			}
			if(toClose != null) {
				for(Clip c : toClose) {
					try { c.close(); } catch(Exception e) { /* ignora */ }
				}
			}
		}
		
		/** true se esta musica foi mandada tocar (mesmo que ainda esteja carregando) */
		public synchronized boolean tocando() { return wantLoop; }
		/** true se o audio ja foi decodificado e esta tocando */
		public synchronized boolean pronto() { return clips != null; }
		
		public synchronized void setVolume(float vol) {
			if(Math.abs(volume - vol) > 0.01f) {
				volume = vol;
				if(gainControl != null) {
					gainControl.setValue(Math.max(gainControl.getMinimum(), Math.min(gainControl.getMaximum(), volume)));
				}
			}
		}
	}
	
	/** true se algum arquivo de som nao foi encontrado (nem no jar, nem em pastas ao lado do jogo) */
	public static boolean faltandoSons = false;
	
	private static java.io.File[] pastasBusca() {
		java.util.List<java.io.File> l = new java.util.ArrayList<java.io.File>();
		try {
			java.io.File jar = new java.io.File(Sound.class.getProtectionDomain().getCodeSource().getLocation().toURI());
			java.io.File dir = jar.isDirectory() ? jar : jar.getParentFile();
			if(dir != null) {
				l.add(dir);
				l.add(new java.io.File(dir,"res"));
				if(dir.getParentFile() != null) l.add(new java.io.File(dir.getParentFile(),"res"));
			}
		}catch(Exception e) { /* ignora */ }
		java.io.File cwd = new java.io.File(System.getProperty("user.dir"));
		l.add(cwd);
		l.add(new java.io.File(cwd,"res"));
		return l.toArray(new java.io.File[0]);
	}
	
	/** confere (sem carregar) se as musicas existem; marca faltandoSons se nao. */
	public static void verificarMusicas() {
		String[] nomes = {"/music/kayky theme.wav","/music/Bizarro.wav","/music/efects/Hit_Hurt.wav"};
		for(String n : nomes) {
			if(Sound.class.getResource(n) != null) continue;
			boolean achou = false;
			String rel = n.substring(1);
			for(java.io.File base : pastasBusca()) {
				if(new java.io.File(base, rel).isFile()) { achou = true; break; }
			}
			if(!achou) faltandoSons = true;
		}
	}
	
	private static byte[] readResource(String name) {
		try {
			InputStream in = Sound.class.getResourceAsStream(name);
			if(in == null) {
				// nao esta dentro do jar: procura em pastas ao lado do jogo (music/..., res/music/...)
				String rel = name.startsWith("/") ? name.substring(1) : name;
				for(java.io.File base : pastasBusca()) {
					java.io.File f = new java.io.File(base, rel);
					if(f.isFile()) {
						in = new FileInputStream(f);
						break;
					}
				}
			}
			if(in == null) {
				faltandoSons = true;
				System.err.println("Som nao encontrado: "+name);
				return null;
			}
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			DataInputStream dis = new DataInputStream(new BufferedInputStream(in, 65536));
			
			byte[] buffer = new byte[65536];
			int read = 0;
			while((read = dis.read(buffer)) >= 0) {
				baos.write(buffer,0,read);
			}
			dis.close();
			return baos.toByteArray();
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/** indice (0..6) da musica da zona atual, ou -1. 6 = tema triste */
	private static int temaDesejado() {
		switch(Game.setZone) {
			case 1: return 0;
			case 2: return sadTheme == 2 ? 6 : 1;
			case 3: return 2;
			case 4: return sadTheme == 2 ? 6 : 3;
			case 5: return 4;
			case 6: return 5;
		}
		return -1;
	}
	
	private static Clips[] todas() {
		return new Clips[]{music0, music, music2, music3, music4, music5, music6};
	}
	
	public static void startTheme() {
		int d = temaDesejado();
		if(d >= 0) {
			saindoFlag[d] = false; // se ela estava sumindo, volta a ser a musica atual
			temaAtual = d;
			todas()[d].loop();
		}
		trocaPendente = false;
		StartTema();
	}
	
	public static Clips music0 = new Clips("/music/kayky theme.wav");
	public static Clips music = new Clips("/music/aquele que chora.wav");
	public static Clips music2 = new Clips("/music/Trevas Trevozas.wav");
	public static Clips music3 = new Clips("/music/Sally Theme.wav");
	public static Clips music4 = new Clips("/music/neve theme.wav");
	public static Clips music5 = new Clips("/music/Bizarro.wav");
	public static Clips music6 = new Clips("/music/Sad Theme.wav");
	
	
	public static Clips efect_hit = load("/music/efects/Hit_Hurt.wav", 8);
	public static Clips efect_explosao = load("/music/efects/Explosao.wav", 9);
	public static Clips efect_aqua = load("/music/efects/Aqua.wav", 10);
	public static Clips efect_powerup = load("/music/efects/PowerUp.wav", 11);
	public static Clips efect_hit2 = load("/music/efects/Hit_Hurt2.wav", 12);
	public static Clips efect_botao = load("/music/efects/Botao_Sound.wav", 13);
	public static Clips efect_block = load("/music/efects/Block.wav", 14);
	public static Clips efect_lusion = load("/music/efects/Lusion_Sound.wav", 15);
	public static Clips efect_dash = load("/music/efects/Dash.wav", 15);
	
	private static Clips load(String name, int count){
		try {
			byte[] data = readResource(name);
			return new Clips(data,count);
		}catch(Exception e) {
			try {
				return new Clips(null,0);
			}catch(Exception ee) {
				return null;
			}
		}
	}
	
	// ------------------------------------------------------------------------------------------
	// Troca de musicas (crossfade suave)
	// O volume e percebido em escala logaritmica, entao o fade e feito na AMPLITUDE (0..1, com curva
	// suave) e so depois convertido para dB. Antes o fade era linear em dB: o som parecia cortar de
	// repente e so voltar devagar. A musica que sai e a que entra mudam ao mesmo tempo (crossfade).
	// "duck" e o abafado dos dialogos (0 a -10 dB), independente do fade.
	// ------------------------------------------------------------------------------------------
	private static final double TICKS_SAIDA = 84;     // ~1,4 s
	private static final double TICKS_ENTRADA = 96;   // ~1,6 s
	private static final int ESPERA_MAX_MESMO_TEMA = 150; // ticks esperando a zona mudar antes de reiniciar o mesmo tema
	
	private static boolean[] saindoFlag = new boolean[7];
	private static double saida = 0;      // amplitude de quem esta saindo (1 -> 0)
	private static double entrada = 0;    // amplitude de quem esta entrando (0 -> 1)
	private static double duck = 0;       // dB (0 = normal, -10 = abafado)
	private static boolean trocaPendente = false;
	private static int trocaEspera = 0;
	private static int temaAtual = -1;
	private static int esperaCarregar = 0;
	
	/** curva suave (entra e sai devagar, rapida no meio) */
	private static double suave(double a) {
		a = Math.max(0, Math.min(1, a));
		return a*a*(3 - 2*a);
	}
	
	private static float paraDb(double amplitude) {
		if(amplitude <= 0.0005) return -80f;
		return (float)(20*Math.log10(amplitude));
	}
	
	private static void aplicarVolumes() {
		Clips[] t = todas();
		float dbEntrada = paraDb(suave(entrada)) + (float)duck;
		float dbSaida = paraDb(suave(saida)) + (float)duck;
		for(int i = 0; i < t.length; i++) {
			t[i].setVolume(saindoFlag[i] ? dbSaida : dbEntrada);
		}
		Gan = dbEntrada;
	}
	
	/** comeca a sumir com a(s) musica(s) que estao tocando; a proxima entra quando a zona/tema novo for conhecido */
	private static void iniciarSaida() {
		endTema = false;
		Clips[] t = todas();
		boolean algum = false;
		// se ja havia uma saida em andamento, encerra ela de vez
		for(int i = 0; i < t.length; i++) {
			if(saindoFlag[i]) { t[i].stop(); saindoFlag[i] = false; }
		}
		for(int i = 0; i < t.length; i++) {
			if(t[i].tocando()) { saindoFlag[i] = true; algum = true; }
		}
		saida = Math.max(0.02, Math.min(1, entrada)); // continua de onde o volume estava
		entrada = 0;                                  // a proxima musica entra do zero
		startTema = false;
		aumentarTema = false;
		trocaPendente = true;
		trocaEspera = 0;
		if(!algum) trocaEspera = ESPERA_MAX_MESMO_TEMA; // nada tocando: comeca assim que possivel
		aplicarVolumes();
	}
	
	public static void endTheme() {
		endTema = true;
		iniciarSaida();
	}
	
	public static void StartTema() {
		startTema = true;
		aplicarVolumes();
	}
	
	public static void diminuirTheme() {
		diminuirTema = true;
		aplicarVolumes();
	}
	
	public static void aumentarTema() {
		aumentarTema = true;
		aplicarVolumes();
	}
	
	public void tick() {
		if(endTema == true && !trocaPendente) {
			iniciarSaida();
		}
		endTema = false;
		
		// a musica que esta saindo
		boolean saindo = false;
		for(int i = 0; i < saindoFlag.length; i++) if(saindoFlag[i]) saindo = true;
		if(saindo) {
			saida -= 1.0/TICKS_SAIDA;
			if(saida <= 0) {
				saida = 0;
				Clips[] t = todas();
				for(int i = 0; i < t.length; i++) {
					if(saindoFlag[i]) { t[i].stop(); saindoFlag[i] = false; }
				}
			}
		}
		
		// espera a zona/tema novo para entrar com a proxima musica
		if(trocaPendente) {
			trocaEspera++;
			int d = temaDesejado();
			if(d >= 0) {
				boolean mesmaQueSaindo = saindoFlag[d];
				boolean podeEntrar = !mesmaQueSaindo && (d != temaAtual || trocaEspera >= ESPERA_MAX_MESMO_TEMA);
				if(podeEntrar) {
					startTheme(); // zera trocaPendente e liga a entrada
					esperaCarregar = 0;
				}
			}
		}
		
		// abafado dos dialogos
		if(diminuirTema == true) {
			duck -= 0.3;
			if(duck <= -10) {
				duck = -10;
				diminuirTema = false;
			}
		}
		if(aumentarTema == true) {
			duck += 0.3;
			if(duck >= 0) {
				duck = 0;
				aumentarTema = false;
			}
		}
		
		// entrada da musica nova (so comeca a subir quando ja carregou, para o fade nao passar no silencio)
		if(startTema == true) {
			boolean pronta = temaAtual < 0 || todas()[temaAtual].pronto() || ++esperaCarregar > 300;
			if(pronta) {
				entrada += 1.0/TICKS_ENTRADA;
				if(entrada >= 1) {
					entrada = 1;
					startTema = false;
				}
			}
		}
		
		aplicarVolumes();
	}
	
}
