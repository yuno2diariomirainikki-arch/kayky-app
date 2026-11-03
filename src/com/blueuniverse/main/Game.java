package com.blueuniverse.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import java.awt.image.DataBufferInt;

import com.blueuniverse.entitys.Abobora;
import com.blueuniverse.entitys.Abobora2;
import com.blueuniverse.entitys.Arvore;
import com.blueuniverse.entitys.AtackShoot;
import com.blueuniverse.entitys.AtackShootMuriel;
import com.blueuniverse.entitys.AtackShootSally;
import com.blueuniverse.entitys.Barreira;
import com.blueuniverse.entitys.Barreira2;
import com.blueuniverse.entitys.Bia;
import com.blueuniverse.entitys.Boneco;
import com.blueuniverse.entitys.Botão;
import com.blueuniverse.entitys.BulletShoot;
import com.blueuniverse.entitys.Enemy;
import com.blueuniverse.entitys.Enemy2;
import com.blueuniverse.entitys.Enemy3;
import com.blueuniverse.entitys.Entity;
import com.blueuniverse.entitys.Espinhos;
import com.blueuniverse.entitys.Flocos;
import com.blueuniverse.entitys.Killer;
import com.blueuniverse.entitys.Lili;
import com.blueuniverse.entitys.Lusion;
import com.blueuniverse.entitys.Muriel;
import com.blueuniverse.entitys.Npc1;
import com.blueuniverse.entitys.Particles;
import com.blueuniverse.entitys.Particles2;
import com.blueuniverse.entitys.Player;
import com.blueuniverse.entitys.PortaDireita;
import com.blueuniverse.entitys.PortaEsquerda;
import com.blueuniverse.entitys.PunchShoot;
import com.blueuniverse.entitys.Quadro;
import com.blueuniverse.entitys.Sally;
import com.blueuniverse.entitys.SallyCorpo;
import com.blueuniverse.entitys.Vampiros;
import com.blueuniverse.graficos.Artes;
import com.blueuniverse.graficos.Efeitos;
import com.blueuniverse.graficos.Telas;
import com.blueuniverse.world.Camera;
import com.blueuniverse.graficos.Spritesheet;
import com.blueuniverse.graficos.Ui;
import com.blueuniverse.world.World;

public class Game extends Canvas implements Runnable,KeyListener,MouseListener{

	private static final long serialVersionUID = 1L;
	public static JFrame frame;
	private Thread thread;
	private boolean isRunning = true;
	
	public static double Versao = 0.4;
	public static String Idioma = "EN";
	
	public static int CUR_LEVEL = 0, MAX_LEVEL = 14;
	public static int CUR_ART = 0;
	public static int setZone = 1, maxZone = 8;
	
	public static final int WIDTH = 426;
	public static final int HEIGHT = 240;
	public static final int SCALE = 3;
	public static final int ZOOM = 4;
	public static final int SCALE_MULTI = 300;
	
	public static final int ART_WIDTH = 988;
	public static final int ART_HEIGHT = 555;
	
	public static final int MENU_WIDTH = 1740;
	public static final int MENU_HEIGHT = 1080;
	
	public static final int TRA_WIDTH = 1830;
	
	public static final int PERS_WIDTH = 540;
	public static final int PERS_HEIGHT = 870;
	
	public static final int PERSF_WIDTH = 340;
	public static final int PERSF_HEIGHT = 547;
	
	public static final int CRE_WIDTH = 1280;
	public static final int CRE_HEIGHT = 750;
	
	
	private BufferedImage image;
	
	public static List<Entity> entities;
	public static List<Boneco> bonecos;
	public static List<Botão> botoes;
	public static List<Barreira> barreiras;
	public static List<Barreira2> barreiras2;
	public static List<Quadro> quadros;
	public static List<SallyCorpo> sallyCorpos;
	public static List<Enemy> enemies;
	public static List<Enemy2> enemies2;
	public static List<Enemy3> enemies3;
	public static List<Vampiros> vampiros;
	public static List<Lusion> lusions;
	public static List<Flocos> flocos;
	public static List<Killer> killers;
	public static List<Sally> sallys;
	public static List<Muriel> muriels;
	public static List<Abobora> aboboras;
	public static List<Abobora2> aboboras2;
	public static List<Espinhos> espinhos;
	public static List<Arvore> arvores;
	public static List<PortaDireita> portasDireitas;
	public static List<PortaEsquerda> portasEsquerdas;
	public static List<Npc1> npcs1;
	public static List<Lili> lilis;
	public static List<Bia> bias;
	public static List<BulletShoot> bullets;
	public static List<PunchShoot> punchs;
	public static List<AtackShoot> atacks;
	public static List<AtackShootSally> atacksSally;
	public static List<AtackShootMuriel> atacksMuriel;
	public static Spritesheet spritesheet;
	public static Spritesheet spritesheetTileLv0;
	public static Spritesheet spritesheetTileLv1;
	public static Spritesheet spritesheetTileLv2;
	public static Spritesheet spritesheetTileLv3;
	public static Spritesheet spritesheetTileLv4;
	public static Spritesheet spritesheetTileLv5;
	public static Spritesheet spritesheetKiller;
	public static Spritesheet spritesheetSally;
	public static Spritesheet spritesheetMuriel;
	public static Spritesheet spritesheetAboboras;
	public static Spritesheet spritesheetAboboras2;
	public static Spritesheet spritesheetEspinhos;
	public static Spritesheet spritesheetNpcs;
	public static Spritesheet spritesheetLilis;
	public static Spritesheet spritesheetBias;
	public static Spritesheet spritesheetEnemies;
	public static Spritesheet spritesheetMorcegos;
	public static Spritesheet spritesheetVampiros;
	public static Spritesheet spritesheetLusions;
	public static Spritesheet spritesheetFlocos;
	public static Spritesheet spritesheetPunch;

	public static Spritesheet spritesheetTranzi;
	public static Spritesheet spritesheetCreditos;
	public static Spritesheet spritesheetCreditos2;
	public static Spritesheet spritesheetMenu;
	public static Spritesheet spritesheetArtes;
	
	public static Spritesheet spritesheetArtesKB;
	public static Spritesheet spritesheetArtesKC;
	public static Spritesheet spritesheetArtesKN;
	public static Spritesheet spritesheetArtesDN;
	public static Spritesheet spritesheetArtesLB;
	public static Spritesheet spritesheetArtesLC;
	public static Spritesheet spritesheetArtesLN;
	public static Spritesheet spritesheetArtesMB;
	public static Spritesheet spritesheetArtesMC;
	public static Spritesheet spritesheetArtesMN;
	public static Spritesheet spritesheetArtesMS;
	public static Spritesheet spritesheetArtesBN;
	public static Spritesheet spritesheetArtesBC;
	public static Spritesheet spritesheetArtesBB;
	public static Spritesheet spritesheetArtesBM;
	public static Spritesheet spritesheetArtesBS;
	
	public static World world;
	
	public static Player player;
	
	public static Random rand;
	
	public Ui ui;
	
	public InputStream stream = ClassLoader.getSystemClassLoader().getResourceAsStream("Pixel_Font.otf");
	public static Font newfont; 
	
	public static String gameState = "MENU";
	private boolean showMessageGameOver = true;
	private int framesGameOver = 0;
	private boolean restartGame = false;
	
	public static int cena_entrada = 1;
	public static int cena_saida = 2;
	public static int cena_jogando = 3;
	public static int estado_cena = cena_entrada;
	public int timeCena = 0, maxTimeCena = 60*3;
	
	public Menu menu;
	public Mapa mapa;
	public Text text;
	public Creditos creditos;
	public Tranzi tranzi;
	public Anima anima;
	public Particles particles;
	public Particles2 particles2;
	public Sound sound;
	
	public static int[] pixels;
	
	public BufferedImage lightmap;
	
	public int[] lightMapPixels;
	public static int[] minimapaPixels;
	
	public BufferedImage sprite1;
	public BufferedImage sprite2;
	public int x1,y1;
	public int x2,y2;
	public int[] pixels1;
	public int[] pixels2;
	
	
	public static BufferedImage minimapa;
	
	public boolean saveGame = false;
	private boolean menuTeclaSegurada = false;
	private boolean zHeld = false;
	public static boolean colizao = false;
	
	// ---- tremor de tela (shake) ----
	public static int shakeTempo = 0, shakeMax = 1;
	public static double shakeForca = 0;
	
	/** treme a tela. forca em pixels internos (a imagem e ampliada depois); o tremor mais forte vence. */
	public static void tremer(double forca, int frames) {
		double atual = (shakeTempo > 0) ? shakeForca * shakeTempo / (double)shakeMax : 0;
		if(forca >= atual) {
			shakeForca = forca;
			shakeMax = frames;
			shakeTempo = frames;
		}
	}
	
	// fontes reutilizadas (antes eram recriadas a cada frame)
	public static final Font FONT_18 = new Font("arial", Font.BOLD,18);
	public static final Font FONT_28 = new Font("arial", Font.BOLD,28);
	
	public Game(){
		rand = new Random();
		instancia = this;
		addKeyListener(this);
		addMouseListener(this);

		setPreferredSize(new Dimension(WIDTH*SCALE,HEIGHT*SCALE));

		initFrame();
		//Inicializando objetos
		ui = new Ui();
		image = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);

		entities = new ArrayList<Entity>();
		bonecos = new ArrayList<Boneco>();
		botoes = new ArrayList<Botão>();
		barreiras = new ArrayList<Barreira>();
		barreiras2 = new ArrayList<Barreira2>();
		quadros = new ArrayList<Quadro>();
		sallyCorpos = new ArrayList<SallyCorpo>();
		enemies = new ArrayList<Enemy>();
		enemies2 = new ArrayList<Enemy2>();
		enemies3 = new ArrayList<Enemy3>();
		vampiros = new ArrayList<Vampiros>();
		lusions = new ArrayList<Lusion>();
		flocos = new ArrayList<Flocos>();
		killers = new ArrayList<Killer>();
		sallys = new ArrayList<Sally>();
		muriels = new ArrayList<Muriel>();
		aboboras = new ArrayList<Abobora>();
		aboboras2 = new ArrayList<Abobora2>();
		espinhos = new ArrayList<Espinhos>();
		arvores = new ArrayList<Arvore>();
		portasDireitas = new ArrayList<PortaDireita>();
		portasEsquerdas = new ArrayList<PortaEsquerda>();
		npcs1 = new ArrayList<Npc1>();
		lilis = new ArrayList<Lili>();
		bias = new ArrayList<Bia>();
		bullets = new ArrayList<BulletShoot>();
		punchs = new ArrayList<PunchShoot>();
		atacks = new ArrayList<AtackShoot>();
		atacksSally = new ArrayList<AtackShootSally>();
		atacksMuriel = new ArrayList<AtackShootMuriel>();
		
		spritesheet = new Spritesheet("/img/spritesheet.png");
		spritesheetTileLv0 = new Spritesheet("/img/tiles_lv0.png");
		spritesheetTileLv1 = new Spritesheet("/img/tiles_lv1.png");
		spritesheetTileLv2 = new Spritesheet("/img/tiles_lv2.png");
		spritesheetTileLv3 = new Spritesheet("/img/tiles_lv3.png");
		spritesheetTileLv4 = new Spritesheet("/img/tiles_lv4.png");
		spritesheetTileLv5 = new Spritesheet("/img/tiles_lv5.png");
		spritesheetKiller = new Spritesheet("/img/killer.png");
		spritesheetSally = new Spritesheet("/img/Sally.png");
		spritesheetMuriel = new Spritesheet("/img/muriel.png");
		spritesheetAboboras = new Spritesheet("/img/abobora.png");
		spritesheetAboboras2 = new Spritesheet("/img/abobora2.png");
		spritesheetEspinhos = new Spritesheet("/img/tiles_lv2.png");
		spritesheetNpcs = new Spritesheet("/img/npcs.png");
		spritesheetLilis = new Spritesheet("/img/lili.png");
		spritesheetBias = new Spritesheet("/img/bia.png");
		spritesheetEnemies = new Spritesheet("/img/enemys.png");
		spritesheetMorcegos = new Spritesheet("/img/morcegos.png");
		spritesheetVampiros = new Spritesheet("/img/vampiros.png");
		spritesheetLusions = new Spritesheet("/img/lusion.png");
		spritesheetFlocos = new Spritesheet("/img/flocos.png");
		spritesheetPunch = new Spritesheet("/img/atack.png");
		
		spritesheetTranzi = new Spritesheet("/img/tranzi.png");
		spritesheetCreditos = new Spritesheet("/creditos/creditos.png");
		spritesheetCreditos2 = new Spritesheet("/creditos/creditos2.png");
		spritesheetMenu = new Spritesheet("/img/menu.png");
		spritesheetArtes = new Spritesheet("/art/"+10+".png");
		
		spritesheetArtesKB = new Spritesheet("/pers/KaykyB.png");
		spritesheetArtesKC = new Spritesheet("/pers/KaykyC.png");
		spritesheetArtesKN = new Spritesheet("/pers/KaykyN.png");
		spritesheetArtesDN = new Spritesheet("/pers/DujhinN.png");
		spritesheetArtesLB = new Spritesheet("/pers/LiliB.png");
		spritesheetArtesLC = new Spritesheet("/pers/LiliC.png");
		spritesheetArtesLN = new Spritesheet("/pers/LiliN.png");
		spritesheetArtesMB = new Spritesheet("/pers/MurielB.png");
		spritesheetArtesMC = new Spritesheet("/pers/MurielC.png");
		spritesheetArtesMN = new Spritesheet("/pers/MurielN.png");
		spritesheetArtesMS = new Spritesheet("/pers/MurielS.png");
		spritesheetArtesBN = new Spritesheet("/pers/BiaN.png");
		spritesheetArtesBC = new Spritesheet("/pers/BiaC.png");
		spritesheetArtesBB = new Spritesheet("/pers/BiaB.png");
		spritesheetArtesBM = new Spritesheet("/pers/BiaM.png");
		spritesheetArtesBS = new Spritesheet("/pers/BiaS.png");
		
		player = new Player(16,16*4,16,16,spritesheet.getSprite(0, 64, 16, 16));
		entities.add(player);
		world = new World("/leveis/"+setZone+CUR_LEVEL+".png");
		
		minimapa = new BufferedImage(World.WIDTH,World.HEIGHT,BufferedImage.TYPE_INT_RGB);
		minimapaPixels = ((DataBufferInt)minimapa.getRaster().getDataBuffer()).getData();
		
		try {
			newfont = Font.createFont(Font.TRUETYPE_FONT, stream).deriveFont(16f);
		} catch (FontFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		menu = new Menu();
		mapa = new Mapa();
		text = new Text();
		creditos = new Creditos();
		tranzi = new Tranzi();
		anima = new Anima();
		sound = new Sound();
		
		Image imagem = null;
		try {
		imagem = ImageIO.read(getClass().getResource("/img/icon.png"));
		}catch(IOException e){
			e.printStackTrace();
		}
		Toolkit toolkit = java.awt.Toolkit.getDefaultToolkit();
		Image image = toolkit.getImage(getClass().getResource("/img/cursor.png"));
		Cursor c = toolkit.createCustomCursor(image, new Point(0,0), "img");
		
		frame.setCursor(c);
		frame.setIconImage(imagem);
		if(imagem != null) {
			// varios tamanhos para o icone ficar nitido na barra de tarefas, no alt+tab etc.
			java.util.List<Image> icones = new java.util.ArrayList<Image>();
			int[] tams = {16, 24, 32, 48, 64, 128, 256};
			for(int t : tams) icones.add(imagem.getScaledInstance(t, t, Image.SCALE_AREA_AVERAGING));
			frame.setIconImages(icones);
		}
		frame.setAlwaysOnTop(true);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		Sound.verificarMusicas();
		if(Sound.faltandoSons) {
			frame.setAlwaysOnTop(false);
			javax.swing.JOptionPane.showMessageDialog(frame,
				"Nao encontrei as musicas e os sons do jogo.\n\n"
				+ "Coloque a pasta \"music\" (a que fica dentro de res no seu projeto)\n"
				+ "NA MESMA PASTA do Kayky.jar e abra o jogo de novo.\n\n"
				+ "O jogo vai continuar sem som.",
				"Kayky - sons nao encontrados", javax.swing.JOptionPane.WARNING_MESSAGE);
			frame.setAlwaysOnTop(true);
		}
	}
	
	public synchronized void start(){
		thread = new Thread(this);
		isRunning = true;
		thread.start();
	}
	
	public synchronized void stop(){
		isRunning = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void initFrame(){
		frame = new JFrame("Kayky");
		frame.add(this);
		frame.setUndecorated(true);
		frame.setResizable(false);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	/*game main*/
	public static void main(String args[]){
		boolean debug = false;
		for(String a : args) {
			if(a.equals("--debug")) debug = true;
		}
		if(!debug) {
			// os System.out.println espalhados pelo codigo (alguns rodam todo tick) deixam
			// o jogo lento, principalmente quando abre com console. So imprime com --debug.
			System.setOut(new java.io.PrintStream(new java.io.OutputStream() {
				public void write(int b) {}
				public void write(byte[] b, int off, int len) {}
			}));
		}
		Game game = new Game();
		game.start();
		World.setGame();
		// carrega as artes grandes em segundo plano, para as cutscenes nao travarem
		Spritesheet.preloadAsync("/art/10.png","/art/12.png","/art/13.png","/art/14.png",
				"/art/20.png","/art/21.png","/art/22.png","/art/30.png","/art/31.png","/art/32.png",
				"/art/40.png","/art/41.png","/art/42.png","/art/43.png","/art/51.png","/art/60.png","/art/61.png");
	}

	
	public void tick(){
		if(shakeTempo > 0) shakeTempo--;
		
		if(gameState == "NORMAL") {
			if(setZone == 1 && CUR_LEVEL == 1) {
				if(player.getX() >= (16*3) && player.getY() >= (16*9) && Text.curText == 2) {
					Text.ativarDialogo();
				}
				if(player.getX() >= (16*14) && player.getY() >= (16*17) && Text.curText == 4) {
					Text.ativarDialogo();
				}
				if(player.getX() >= (16*70) && player.getY() <= (16*12) && Text.curText == 6) {
					Text.ativarDialogo();
				}
			}else if(setZone == 4 && CUR_LEVEL == 3) {
				if(player.getX() >= (16*39) && (player.getY() >= (16*6) && player.getY() <= (16*38))) {
					if(Game.lusions.size() == 0 && Lusion.spawnLusion == true && Text.curText != 2) {
						Sound.efect_lusion.start();
						Sound.diminuirTema = true;
						Text.arteView = true;
						Text.curText = 2;
						Game.gameState = "TEXT";
					    Text.frases = true;
					    Text.showMessage = true;
					    Text.xArte = 0;
					
					}else if(Game.lusions.size() == 0 && Lusion.spawnLusion == true  &&  Text.curText == 2) {
						Lusion.life = 70;
						Botão.apertarBotão();
						Lusion.spawnLusion = false;
						Lusion en = new Lusion ((Game.player.getX()+(16*16)),Game.player.getY(),16,32,Entity.LUSION_EN);
						Game.entities.add(en);
						Game.lusions.add(en);
						
						}
				}
			}else if(setZone == 5 && CUR_LEVEL == 0) {
				if(player.getX() >= (16*43) && player.getY() <= (16*12) && Lili.LiliCheck == true) {
					Text.curText = 6;
					Text.arteView = true;
					Game.gameState = "TEXT";
					Sound.diminuirTema = true;
				    Text.frases = true;
				    Text.showMessage = true;	
				}
			}else if(setZone == 6 && CUR_LEVEL == 0) {
				if(player.getX() >= (16*138)) {
					Game.spritesheetArtes = new Spritesheet("/art/"+60+".png");
					Artes.ARTS0 = Game.spritesheetArtes.getArte(Game.ART_WIDTH, Game.ART_HEIGHT);
					Tranzi.setTranzi = 2;
					Tranzi.StartArt();
					gameState = "TRANZI";
				}
			}
			
			
		if(Game.estado_cena == Game.cena_jogando){
		for(int i = 0; i < entities.size(); i++){
				Entity e = entities.get(i);
				e.tick();
				e.applyKnockback();
				// se a entidade removeu a si mesma, a proxima desceu para o indice i: nao pula o tick dela
				if(i < entities.size() && entities.get(i) != e && !entities.contains(e)) {
					i--;
				}
			}
		
		for(int i = 0; i < bullets.size(); i++){
			bullets.get(i).tick();
		}
		
		}else{
			if(Game.estado_cena == Game.cena_entrada && Game.setZone == 1 && Game.CUR_LEVEL == 0) {
				if(Player.frames == Player.maxFrames) {
					Player.frames = 0;
					Player.index++;
					if(Player.index > Player.maxIndex) {
						Player.index = 0;
					}
				}
				if((player.getX() < (16*4) && Game.setZone == 1 && Game.CUR_LEVEL == 0)) {
					player.x++;
					Player.frames++;
				}else {
					Game.estado_cena = Game.cena_saida;			
			     }
			}else {
				Game.estado_cena = Game.cena_saida;	
			}
		}
		if(Game.estado_cena == Game.cena_saida) {
			timeCena++;
			if(Game.setZone == 1 && Game.CUR_LEVEL == 0){
				if(timeCena >= maxTimeCena ) {
					   Game.estado_cena = Game.cena_jogando;
						}
			}
			else if(Game.setZone != 1 && Game.CUR_LEVEL != 0) {
				Game.estado_cena = Game.cena_jogando;
				System.out.println("outros lvs");
			}else {
				Game.estado_cena = Game.cena_jogando;
				System.out.println("outros lvs");
			}
		}
		
		
		for(int i = 0; i < punchs.size(); i++){
			punchs.get(i).tick();
		}
		for(int i = 0; i < atacks.size(); i++){
			atacks.get(i).tick();
		}
		for(int i = 0; i < atacksSally.size(); i++){
			atacksSally.get(i).tick();
		}
		for(int i = 0; i < atacksMuriel.size(); i++){
			atacksMuriel.get(i).tick();
		}
		nextLevel();
		
		}else if(gameState == "GAME_OVER") {
			this.framesGameOver++;
			if(this.framesGameOver >= 35) {
				this.framesGameOver = 0;
				if(this.showMessageGameOver) 
					this.showMessageGameOver = false;
					else this.showMessageGameOver = true;
				
			}
			
			if(restartGame) {
				this.restartGame = false;
				this.gameState = "NORMAL";
				String newWorld = setZone+CUR_LEVEL+".png";
				World.restartGame(newWorld);
				
				if(Game.CUR_LEVEL == 3 && Game.setZone == 4) {
					Lusion.spawnLusion = true;
				}
				if(Game.CUR_LEVEL == 2 && Game.setZone == 5) {
					Muriel.remove();
					Entity bia = new Bia(Game.player.getX()-(16*2),Game.player.getY()-(16),16,16,Game.spritesheetBias.getSprite(0, 0, 16, 16));
					Game.entities.add(bia);
					
					Abobora en = new Abobora(Game.player.getX()+(16*5),Game.player.getY(),16,32,Entity.ABOBORA_EN);
					Game.entities.add(en);
					Game.aboboras.add(en);
				}
			}
		}else if(gameState == "MENU") {
			menu.tick();
		}
		else if(gameState == "TEXT") {
			text.tick();
		}
		else if(gameState == "TRANZI") {
			tranzi.tick();
		}
		else if(gameState == "ANIMA") {
			anima.tick();
			
		}else if(gameState == "CREDITOS") {
			creditos.tick();
		}
		sound.tick();
	
	}
	
	public void render(){
		// se a janela mudou de tamanho (tela cheia <-> janela), recria o buffer de video
		if(getWidth() != ultimaLarg || getHeight() != ultimaAlt) {
			ultimaLarg = getWidth();
			ultimaAlt = getHeight();
			if(ultimaLarg > 0 && ultimaAlt > 0) this.createBufferStrategy(3);
		}
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = image.getGraphics();
		g.setColor(new Color(0,0,0));
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		// tremor: desloca a camera so durante o desenho do mundo/entidades/efeitos (o HUD nao treme)
		int camXOriginal = Camera.x, camYOriginal = Camera.y;
		if(shakeTempo > 0) {
			double amp = shakeForca * shakeTempo / (double)shakeMax;
			Camera.x += (int)Math.round((rand.nextDouble()*2 - 1) * amp);
			Camera.y += (int)Math.round((rand.nextDouble()*2 - 1) * amp);
		}
		// cena de arte em tela cheia (dialogos com imagem grande): o texto cobre a tela inteira com fundo opaco,
		// entao nao precisa desenhar mundo, entidades, nevoa e HUD (era o que deixava essas cenas lentas)
		boolean cenaOpaca = gameState == "TEXT" && Text.frases && (Text.curText == 1 || Text.curText == 3 || Text.curText == 5);
		if(!cenaOpaca) {
		world.render(g);
		Collections.sort(entities,Entity.nodeSorter);
		for(int i = 0; i < entities.size(); i++){
			Entity e = entities.get(i);
			e.render(g);
		}
		for(int i = 0; i < bullets.size(); i++){
			bullets.get(i).render(g);
		}
		for(int i = 0; i < punchs.size(); i++){
			punchs.get(i).render(g);
		}
		for(int i = 0; i < atacks.size(); i++){
			atacks.get(i).render(g);
		}
		for(int i = 0; i < atacksSally.size(); i++){
			atacksSally.get(i).render(g);
		}
		for(int i = 0; i < atacksMuriel.size(); i++){
			atacksMuriel.get(i).render(g);
		}
		if(gameState != "MENU" && gameState != "CREDITOS") {   // inclui "ANIMA": as animacoes (resgate da Bia etc.) tambem tem nevoa/luzes
			Efeitos.render((Graphics2D)g, setZone, player.getX()-Camera.x+8, player.getY()-Camera.y+8, Camera.x, Camera.y, WIDTH, HEIGHT);
		}
		ui.render(g);
		}
		Camera.x = camXOriginal;
		Camera.y = camYOriginal;
		g.dispose();
		// Todo o jogo e desenhado numa tela virtual de tamanho fixo (WIDTH*SCALE x HEIGHT*SCALE) e so no final
		// ela e ampliada para a janela mantendo a proporcao (barras pretas se a tela nao for 16:9).
		if(telaVirtual == null) telaVirtual = new BufferedImage(WIDTH*SCALE, HEIGHT*SCALE, BufferedImage.TYPE_INT_RGB);
		g = telaVirtual.createGraphics();
		if(!cenaOpaca) g.drawImage(image, 0, 0, (WIDTH*ZOOM), (HEIGHT*ZOOM), null);
		
		if(gameState != "GAME_OVER") {
			Telas.saiuDoGameOver();
		}
		if(gameState == "GAME_OVER") {
			Telas.gameOver((Graphics2D) g, WIDTH*SCALE, HEIGHT*SCALE, Idioma);
		}else if(gameState == "MENU") {
			player.updateCamera();
			menu.render(g);
		}else if(gameState == "MAPA") {
			player.updateCamera();
			mapa.render(g);
		}else if(gameState == "TEXT") {
			player.updateCamera();
			text.render(g);
		}else if(gameState == "TRANZI") {
			player.updateCamera();
			tranzi.render(g);
		}else if(gameState == "CREDITOS") {
			player.updateCamera();
			creditos.render(g);
		}
		
		if(Text.interage == true && gameState == "NORMAL") {
			g.setColor(Color.white);
			g.setFont(FONT_18);
			if(Game.Idioma == "PT") {
				g.drawString("Z para interagir",((Game.WIDTH*Game.SCALE)/2)-45, (Game.HEIGHT*Game.SCALE)-50);
			}else if (Game.Idioma == "EN") {
				g.drawString("Z to interact",((Game.WIDTH*Game.SCALE)/2)-45, (Game.HEIGHT*Game.SCALE)-50);
			}
		}else {
			//
		}
		
		if(Bia.especial >= 90 && Bia.curEspecial >= 1 && Bia.especialBia == true && Bia.especialCura == true && Bia.especialSkill == true && gameState == "NORMAL") {
			g.setColor(Color.white);
			g.setFont(FONT_18);
			if(Game.Idioma == "PT") {
				g.drawString("S para curar",((Game.WIDTH*Game.SCALE)/2)-35, (Game.HEIGHT*Game.SCALE)-50);
			}else if (Game.Idioma == "EN") {
				g.drawString("S to heal",((Game.WIDTH*Game.SCALE)/2)-35, (Game.HEIGHT*Game.SCALE)-50);
			}
		}else {
			//
		}
		g.dispose();
		Graphics2D saida = (Graphics2D) bs.getDrawGraphics();
		int cw = getWidth(), ch = getHeight();
		int vw = telaVirtual.getWidth(), vh = telaVirtual.getHeight();
		if(cw == vw && ch == vh) {
			saida.drawImage(telaVirtual, 0, 0, null);
		}else {
			double esc = Math.min(cw/(double)vw, ch/(double)vh);   // mesma escala nos dois eixos: sem distorcer
			int dw = (int)Math.round(vw*esc), dh = (int)Math.round(vh*esc);
			int dx = (cw - dw)/2, dy = (ch - dh)/2;
			saida.setColor(Color.black);
			saida.fillRect(0, 0, cw, ch);
			boolean inteira = Math.abs(esc - Math.round(esc)) < 0.001;
			saida.setRenderingHint(java.awt.RenderingHints.KEY_INTERPOLATION,
					inteira ? java.awt.RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR : java.awt.RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			saida.drawImage(telaVirtual, dx, dy, dw, dh, null);
		}
		saida.dispose();
		bs.show();
	}
	
	// ---- tela cheia ----
	private BufferedImage telaVirtual;
	private int ultimaLarg = -1, ultimaAlt = -1;
	public static boolean telaCheia = false;
	private static Game instancia;
	
	/** alterna entre janela e tela cheia (a proporcao do jogo e mantida) */
	public static void alternarTelaCheia() {
		try {
			java.awt.GraphicsDevice gd = frame.getGraphicsConfiguration().getDevice();
			telaCheia = !telaCheia;
			if(telaCheia) {
				if(gd.isFullScreenSupported()) {
					gd.setFullScreenWindow(frame);
				}else {
					frame.setBounds(gd.getDefaultConfiguration().getBounds());
				}
			}else {
				if(gd.getFullScreenWindow() == frame) gd.setFullScreenWindow(null);
				frame.pack();
				frame.setLocationRelativeTo(null);
			}
			frame.setAlwaysOnTop(true);
			if(instancia != null) instancia.requestFocus();
		}catch(Exception e) {
			telaCheia = false;
		}
	}
	
	public static void nextLevelReset() {
		if(setZone == 2 && (CUR_LEVEL == 0 || CUR_LEVEL == 1 || CUR_LEVEL == 2)) {
			Text.xArte = 0;
			Killer.killerCheck = true;
		}
		if(setZone == 4 && CUR_LEVEL == 6) {
			Sound.diminuirTema = true;
			Text.xArte = 0;
			Text.arteView = true;
			Text.curText = 1;
			Game.gameState = "TEXT";
		    Text.frases = true;
		    Text.showMessage = true;
		}
		if(setZone == 4 && (CUR_LEVEL == 0 || CUR_LEVEL == 2)) {
			Text.xArte = 0;
			Sally.SallyCheck = true;
		}
		if(Game.setZone == 5 && (CUR_LEVEL == 1 || CUR_LEVEL == 2)) {
			Muriel.MurielCheck = true;
			Text.xArte = 0;
		}
		if(Game.setZone == 6) {
			Muriel.MurielCheck = true;
			Text.xArte = 0;
			Bia.biaReset();
		}
	}
	
	public static void nextLevel() {
		
		if(Player.proximoLevel == true || Player.proximoLevelBoos == true) {
			if(setZone == 2 && Player.proximoLevel == true) {
				if(CUR_LEVEL <= 2) {
					CUR_LEVEL++;
					Killer.life = 350;
					nextLevelReset();
					if(CUR_LEVEL == 1) {
						Tranzi.setTranzi = 2;
						Tranzi.StartArt();
						gameState = "TRANZI";
					}else if(CUR_LEVEL == 2) {
						Tranzi.setTranzi = 2;
						Tranzi.StartArt();
						gameState = "TRANZI";
					}
				}
				if(CUR_LEVEL == 3) {
					CUR_LEVEL = 3;
					Text.xArte = 0;
					Text.curText = 2;
					gameState = "TEXT";
				    Text.frases = true;
				    Text.showMessage = true;
				    Text.arteView = true;
				    Sound.diminuirTheme();
				}
				
			}
			if(Player.proximoLevel == true  && Game.setZone == 3) {
				if(CUR_LEVEL == 1) {
					Text.xArte = 0;
					Text.curText = 1;
					gameState = "TEXT";
				    Text.frases = true;
				    Text.showMessage = true;
				    Text.arteView = true;
				    Sound.diminuirTheme();
				}else if(CUR_LEVEL == 3){
					Text.xArte = 0;
					Text.curText = 2;
					gameState = "TEXT";
				    Text.frases = true;
				    Text.showMessage = true;
				    Text.arteView = true;
				    Sound.diminuirTheme();
				}
				
			}
			
			if(Player.proximoLevel == true && (setZone == 4 && CUR_LEVEL <= 3)) {
				Sally.life = 150;
				nextLevelReset();
				if(CUR_LEVEL == 0) {
					Text.curText = 1;
					Tranzi.setTranzi = 2;
					Tranzi.StartArt();
					gameState = "TRANZI";
				}else if(CUR_LEVEL == 1) {
					Game.spritesheetArtes = new Spritesheet("/art/"+41+".png");
					Artes.ARTS0 = Game.spritesheetArtes.getArte(Game.ART_WIDTH, Game.ART_HEIGHT);
					Tranzi.setTranzi = 2;
					Tranzi.StartArt();
					gameState = "TRANZI";
				}
			}
			
			if(Player.proximoLevel == true && (setZone == 5 && CUR_LEVEL <= 3)) {
				nextLevelReset();
				if(CUR_LEVEL == 0) {
					Text.curText = 1;
					Tranzi.setTranzi = 2;
					Tranzi.StartArt();
					gameState = "TRANZI";
				}
			}
			
			if(setZone == 7 && Player.proximoLevel == true && CUR_LEVEL == 11) {
				CUR_LEVEL++;
				nextLevelReset();
				if(CUR_LEVEL > MAX_LEVEL) {
					CUR_LEVEL = 0;
				}
				if(CUR_LEVEL == 12) {
					Tranzi.setTranzi = 2;
					Tranzi.StartArt();
					gameState = "TRANZI";
				}
			}
			
		}
	} 
	
	public static void interação() {
		if(Text.interage == true && setZone == 1 && (CUR_LEVEL == 0 || CUR_LEVEL == 2 || CUR_LEVEL == 3) && Text.curText == 1) {
			if(gameState == "NORMAL") {
				Text.xArte = 0;
			}
			if(CUR_LEVEL == 2) {
				Game.spritesheetArtes = new Spritesheet("/art/"+12+".png");
				Artes.ARTS0 = Game.spritesheetArtes.getArte(Game.ART_WIDTH, Game.ART_HEIGHT);
			}
			if(CUR_LEVEL == 3) {
				Game.spritesheetArtes = new Spritesheet("/art/"+13+".png");
				Artes.ARTS0 = Game.spritesheetArtes.getArte(Game.ART_WIDTH, Game.ART_HEIGHT);
			}
			gameState = "TEXT";
		    Text.frases = true;
		    Text.showMessage = true;
		    Text.arteView = true;
		    Sound.diminuirTheme();
		}
		if(Text.interage == true && setZone == 1 && (CUR_LEVEL == 0 || CUR_LEVEL == 3)&& (Text.curText == 2 || Text.curText == 4)) {
			if(CUR_LEVEL == 3) {
				Game.spritesheetArtes = new Spritesheet("/art/"+13+".png");
				Artes.ARTS0 = Game.spritesheetArtes.getArte(Game.ART_WIDTH, Game.ART_HEIGHT);
			}
			gameState = "TEXT";
		    Text.frases = true;
		    Text.showMessage = true;
		    Sound.diminuirTheme();
		}
		if(Text.interage == true && ((setZone == 1 && CUR_LEVEL == 1) || (setZone == 4))) {
			System.out.println(Text.curText);
			System.out.println(CUR_LEVEL);
			System.out.println(setZone);
			Text.interage = false;
			if(setZone == 1 && CUR_LEVEL == 1) {
				Botão.apertarBotão();
			}else if(setZone == 4 && CUR_LEVEL != 3) {
				Botão.apertarBotão();
			}else if(setZone == 4 && CUR_LEVEL == 3 && (Text.curText !=1 && Text.curText !=4 )) {
				Botão.apertarBotão2();
			}else if(setZone == 4 && CUR_LEVEL == 3 && Text.curText == 1) {
				Game.spritesheetArtes = new Spritesheet("/art/"+43+".png");
				Artes.ARTS0 = Game.spritesheetArtes.getArte(Game.ART_WIDTH, Game.ART_HEIGHT);
				Sound.diminuirTheme();
				Text.arteView = true;
				Quadro.check = 1;
				Game.gameState = "TEXT";
			    Text.frases = true;
			    Text.showMessage = true;
			    Text.xArte = 0;
			}else if(setZone == 4 && CUR_LEVEL == 3 && Text.curText == 2) {
				Sound.diminuirTheme();
				Text.arteView = true;
				Game.gameState = "TEXT";
			    Text.frases = true;
			    Text.showMessage = true;
			    Text.xArte = 0;
			}else if(setZone == 4 && CUR_LEVEL == 3 && Text.curText == 4) {
				Sound.diminuirTheme();
				Text.arteView = true;
				Text.curText = 4;
				Game.gameState = "TEXT";
			    Text.frases = true;
			    Text.showMessage = true;
			    Text.xArte = 0;
			}
		}
		if(Text.interage == true && setZone == 5 && CUR_LEVEL == 0 && (Text.curText == 2 || Text.curText == 4)) {
			gameState = "TEXT";
		    Text.frases = true;
		    Text.showMessage = true;
		    Sound.diminuirTheme();
		}
	}
	
	
	@Override
	public void run() {
		long lastTime = System.nanoTime();
		final double amoutOfTicks = 60.0;
		final double ns = 1000000000 / amoutOfTicks;
		// Maximo de ticks "atrasados" que o jogo tenta recuperar de uma vez.
		// ANTES nao havia limite: depois de uma travada (cutscene carregando imagem, GC, etc.)
		// o delta ficava enorme e o jogo rodava dezenas de ticks seguidos, fazendo o
		// personagem andar super rapido por alguns segundos.
		final double MAX_CATCHUP = 3.0;
		double delta = 0;
		int frames = 0;
		double timer = System.currentTimeMillis(); 
		requestFocus();
		
		while(isRunning){
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if(delta >= 1){
				if(delta > MAX_CATCHUP) {
					// travada grande: descarta o tempo perdido em vez de acelerar o jogo
					delta = 1;
				}
				while(delta >= 1) {
					tick();
					delta--;
				}
				render();
				frames++;
			}else {
				// dorme ate perto do proximo tick em vez de girar a 100% de CPU
				long remaining = (long)((1 - delta) * ns);
				if(remaining > 2000000L) {
					java.util.concurrent.locks.LockSupport.parkNanos(remaining - 1500000L);
				}else {
					Thread.yield();
				}
			}
			if(System.currentTimeMillis() - timer >= 1000){
				System.out.println("fps: "+frames);
				frames = 0;
				timer += 1000;
			}
		}
		stop();
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			player.right = true;
		}
		else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			player.left = true;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			player.up = true;
		}
		else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			player.down = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_Z && gameState == "MENU") {
			// Z confirma no menu, igual ao Enter (ignora tecla segurada)
			if(!menuTeclaSegurada) menu.enter = true;
			menuTeclaSegurada = true;
			return;
		}
		if(e.getKeyCode() == KeyEvent.VK_Z && gameState == "TEXT") {
			// Z avanca os dialogos, igual ao Enter (ignora tecla segurada para nao pular falas)
			if(!zHeld) {
				Text.curIndex = true;
			}
			zHeld = true;
			return;
		}
		if(e.getKeyCode() == KeyEvent.VK_Z) {
			zHeld = true;
			if(Player.setPlayer == 2) Player.correndo = true;
			if (player.stamina >= 5 && Text.interage == false && player.ammo >= 1 && Game.gameState == "NORMAL") {
				player.stamina--;
				player.staminaRecharge = 0;
				player.shoot = true;
			}
			interação();
			if(Player.setPlayer == 2){
				Player.speed = 2.0;
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_S) {
			if (Bia.especialBia = true) {
				Bia.Especial();
			}
		}
		
		if(e.getKeyCode() == KeyEvent.VK_X) {
			if (player.stamina >= 5 && Game.gameState == "NORMAL" && Player.setPlayer == 1) {
				player.stamina--;
				player.staminaRecharge = 0;
				player.punch = true;
			}
		}
		
		if(e.getKeyCode() == KeyEvent.VK_C) {
			if(gameState == "NORMAL") {
					Mapa.mapa = true;
					gameState = "MAPA";
			}else if(gameState == "MAPA") {
					Mapa.mapa = false;
					gameState = "NORMAL";
			}
					
		}
		
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			if(gameState == "GAME_OVER") {
				this.restartGame = true;
				if(Game.setZone == 4 && Botão.check != 0){
					Botão.apertarBotão();
					if(CUR_LEVEL == 3) {
						Lusion.resetSelf();
					}
				}
			}
			if(gameState == "MENU") {
				if(!menuTeclaSegurada) menu.enter = true;
				menuTeclaSegurada = true;
			}
			if(gameState == "TEXT") {
				Text.curIndex = true;
			}	
			
		}
		if(e.getKeyCode() == KeyEvent.VK_F11) {
			alternarTelaCheia();
		}
		if(e.getKeyCode() == KeyEvent.VK_F2) {
			Efeitos.ativo = !Efeitos.ativo; // liga/desliga nevoa e vinheta
		}
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			if(gameState == "MENU") {
				menu.voltar = true; // volta uma aba (slots -> menu principal)
			}
			if(gameState != "CREDITOS" && gameState != "ANIMA" && gameState != "MENU") {
				gameState = "MENU";
				menu.pause = true;
			}
		}
		
		
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
			player.right = false;
		}
		else if(e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
			player.left = false;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
			player.up = false;
			
			if(gameState == "MENU") {
				menu.up = true;
				Sound.efect_aqua.start();
			}
		}
		
		if(e.getKeyCode() == KeyEvent.VK_Z || e.getKeyCode() == KeyEvent.VK_ENTER) {
			menuTeclaSegurada = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_Z) {
			zHeld = false;
			Player.correndo = false;
			if(Player.setPlayer == 2){
				Player.speed = 1.4;
			}
		}
		
		else if(e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
			player.down = false;
			
			if(gameState == "MENU") {
				menu.down = true;
				Sound.efect_aqua.start();
			}
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
