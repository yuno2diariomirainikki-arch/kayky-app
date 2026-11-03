package com.blueuniverse.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

import com.blueuniverse.entitys.Player;
import com.blueuniverse.graficos.Artes;
import com.blueuniverse.graficos.Telas;
import com.blueuniverse.world.World;

public class Menu {
	
	public String[] options = {"0","1","5","2","3","4"}; // "5" = tela (janela/tela cheia)
	public String[] optionsPT = {"Novo Jogo","Idioma","Carregar","Sair","Continuar"};
	public String[] optionsEN = {"New Game","Language","Load","Exit","Continue"};
	
	public int currentOptions = 0;
	public int maxOptions = options.length -2; // opcoes visiveis: todas menos "Continuar" (a ultima)
	
	//public InputStream stream = ClassLoader.getSystemClassLoader().getResourceAsStream("Pixel_Font.otf");
	//public Font newfont; 
	
	public boolean up,down,enter,voltar;
	
	public static boolean pause = false;
	
	public static boolean saveExists = false;
	public static boolean saveGame = false;
	
	// ---- slots de save ----
	public static final int NUM_SLOTS = 10;
	/** 0 = menu principal, 1 = lista de slots, 2 = caixa de opcoes do slot */
	public int tela = 0;
	public int slotSel = 0;
	public int acaoSel = 0;
	private String[] acoes = {"C","X"};
	/** dados de cada slot: {zona, level(0..3), jogador(1=Kayky, 2=Bia)} ou null se vazio */
	private int[][] slotDados = new int[NUM_SLOTS][];
	private String aviso = "";
	private int avisoFrames = 0;
	
	private static final String[] ZONAS_PT = {"", "Kooba", "Pântano", "Cavernas", "Castelo", "Nevasca", "Vulcão"};
	private static final String[] ZONAS_EN = {"", "Kooba", "Swamp", "Caves", "Castle", "Blizzard", "Volcano"};
	
	/** pasta onde esta o executavel (o .jar); fora de um .jar usa a pasta de trabalho */
	public static File pastaDoJogo() {
		try {
			File f = new File(Game.class.getProtectionDomain().getCodeSource().getLocation().toURI());
			if(f.isFile()) return f.getParentFile();
		}catch(Exception e) {}
		return new File(System.getProperty("user.dir"));
	}
	
	public static File arquivoSlot(int slot) { // slot de 1 a 10
		return new File(pastaDoJogo(), "Save"+slot+".txt");
	}
	
	/** le os 10 arquivos (so quando a tela abre ou algo muda, nunca a cada frame) */
	public void atualizarSlots() {
		for(int i = 0; i < NUM_SLOTS; i++) {
			slotDados[i] = lerSlot(i + 1);
		}
	}
	
	private static int[] lerSlot(int slot) {
		File f = arquivoSlot(slot);
		if(!f.exists()) return null;
		try {
			int zona = -1, level = -1, jogador = 1;
			String[] spl = loadGame(f, 10).split("/");
			for(int i = 0; i < spl.length; i++) {
				String[] kv = spl[i].split(":");
				if(kv.length < 2) continue;
				int v = Integer.parseInt(kv[1].trim());
				if(kv[0].equals("zone")) zona = v;
				else if(kv[0].equals("level")) level = v - 1;
				else if(kv[0].equals("player")) jogador = v;
			}
			if(zona < 1 || zona > 6 || level < 0 || level > 3) return null;
			return new int[]{zona, level, jogador == 2 ? 2 : 1};
		}catch(Exception e) {
			return null;
		}
	}
	
	private void salvarSlot(int i) {
		String[] chaves = {"zone","level","player"};
		int[] valores = {Game.setZone, Game.CUR_LEVEL + 1, Player.setPlayer};
		if(saveGame(arquivoSlot(i + 1), chaves, valores, 10)) {
			mostrarAviso(Game.Idioma == "PT" ? "Jogo salvo no Slot "+(i+1) : "Game saved in Slot "+(i+1));
		}else {
			mostrarAviso(Game.Idioma == "PT" ? "Nao foi possivel salvar" : "Could not save");
		}
		atualizarSlots();
	}
	
	private void mostrarAviso(String txt) {
		aviso = txt;
		avisoFrames = 150;
	}
	
	private void abrirCaixa() {
		if(pause) acoes = new String[]{"S","C","X","N"}; // sobrescrever, carregar, apagar, cancelar
		else acoes = new String[]{"C","X","N"};          // sem jogo em andamento nao ha o que sobrescrever
		acaoSel = pause ? 1 : 0; // comeca em "Carregar" (o mais seguro)
		tela = 2;
	}
	
	private void tickSlots() {
		if(avisoFrames > 0) avisoFrames--;
		
		if(voltar) {
			voltar = false;
			if(tela == 2) tela = 1;
			else tela = 0;
			up = false; down = false; enter = false;
			return;
		}
		
		if(tela == 1) {
			int total = NUM_SLOTS + 1; // + "Voltar"
			if(up) { up = false; slotSel = (slotSel - 1 + total) % total; }
			if(down) { down = false; slotSel = (slotSel + 1) % total; }
			if(enter) {
				enter = false;
				if(slotSel == NUM_SLOTS) {
					tela = 0;
				}else if(slotDados[slotSel] == null) {
					if(pause) salvarSlot(slotSel);
					else mostrarAviso(Game.Idioma == "PT" ? "Inicie um jogo para poder salvar" : "Start a game to be able to save");
				}else {
					abrirCaixa();
				}
			}
		}else if(tela == 2) {
			if(up) { up = false; acaoSel = (acaoSel - 1 + acoes.length) % acoes.length; }
			if(down) { down = false; acaoSel = (acaoSel + 1) % acoes.length; }
			if(enter) {
				enter = false;
				String a = acoes[acaoSel];
				if(a.equals("S")) {
					salvarSlot(slotSel);
					tela = 1;
				}else if(a.equals("C")) {
					int[] d = slotDados[slotSel];
					if(d != null) {
						applySave(loadGame(arquivoSlot(slotSel + 1), 10));
						Sound.startTheme();
						tela = 0;
					}
				}else if(a.equals("X")) {
					arquivoSlot(slotSel + 1).delete();
					atualizarSlots();
					mostrarAviso(Game.Idioma == "PT" ? "Slot "+(slotSel+1)+" apagado" : "Slot "+(slotSel+1)+" deleted");
					tela = 1;
				}else {
					tela = 1;
				}
			}
		}
	}
	
	public void tick() {
		
		if(tela != 0) {
			tickSlots();
			return;
		}
		voltar = false;
		
		if(avisoFrames > 0) avisoFrames--;
		if(up) {
			up = false;
			currentOptions--;
			if(currentOptions < 0) {
				currentOptions = maxOptions;
			}
		}
		if(down) {
			down = false;
			currentOptions++;
			if(currentOptions > maxOptions) {
				currentOptions = 0;
			}
		}
		if(enter) {
			enter = false;
			if(Text.frases == false) {
				if(options[currentOptions] == "0" || options[currentOptions] == "4") {
					Sound.startTema = true;
					Sound.StartTema();
					Sound.startTheme();
					Game.gameState = "NORMAL";
					pause =false;
				}
			}else if(Text.frases == true) {
				if(options[currentOptions] == "0" || options[currentOptions] == "4") {
					Sound.startTema = true;
					Sound.StartTema();
					Sound.startTheme();
					Game.gameState = "TEXT";
					pause =false;
				}
			}
			
			if(options[currentOptions] == "1") {
				Text.curIndexMsg = 0;
				if(Game.Idioma == "PT") {
					Game.Idioma = "EN";
				}else if (Game.Idioma == "EN") {
					Game.Idioma = "PT";
				}
			}
			
			if(options[currentOptions] == "5") {
				Game.alternarTelaCheia();
			}
			
			if(options[currentOptions] == "2") {
				// abre a lista de slots
				atualizarSlots();
				tela = 1;
				slotSel = 0;
			}
			
			if(options[currentOptions] == "3") {
				System.exit(1);
			}
		}
	}
	
	public static void applySave(String str) {
		String[] spl = str.split("/");
		int jogadorSalvo = 0;
		for(int i = 0; i < spl.length; i++) {
			String[] spl2 = spl[i].split(":");
			switch(spl2[0]) {
			    case "zone":
			    	//World.restartGame("level"+spl2[1]+".png");
			    	Game.setZone = Integer.parseInt(spl2[1]);
			    	//Game.player.life = Integer.parseInt(spl2[1]);
			    	break;
			    case "level":
			    	Game.CUR_LEVEL = Integer.parseInt(spl2[1]);
			    	Game.CUR_LEVEL -= 1;
			    	break;
			    case "player":
			    	jogadorSalvo = Integer.parseInt(spl2[1]);
			    	break;
			}
		}
		String newWorld = "level"+Game.setZone+Game.CUR_LEVEL+".png";
		System.out.println("zone �: "+Game.setZone+" cur level � "+Game.CUR_LEVEL);
		World.restartGame(newWorld);
		
		Game.gameState = "NORMAL";
    	pause = false;
    	System.out.println("sauvou e carregou");
		World.setGame();
		if(jogadorSalvo == 1 || jogadorSalvo == 2) Player.setPlayer = jogadorSalvo;
		Sound.endTheme();
		Text.interage = false;
		System.out.println(Game.setZone+""+Game.CUR_LEVEL);
	}
	
	public static String loadGame(File file, int encode) {
		String line = "";
		if(file.exists()) {
			BufferedReader reader = null;
			try {
				String singleLine = null;
				reader = new BufferedReader(new FileReader(file));
				while((singleLine = reader.readLine()) != null) {
					String[] trans = singleLine.split(":");
					if(trans.length < 2) continue;
					char[] val = trans[1].toCharArray();
					trans[1] = "";
					for(int i = 0; i < val.length; i++) {
						val[i]-=encode;
						trans[1]+=val[i];
					}
					line+=trans[0];
					line+= ":";
					line+=trans[1];
					line+="/";
				}
			}catch(IOException e) {
			}finally {
				try { if(reader != null) reader.close(); }catch(IOException e) {}
			}
		}
		return line;
	}
	
	public static boolean saveGame(File file, String[] val1, int[] val2, int encode) {
		BufferedWriter write = null;
		try {
			write = new BufferedWriter(new FileWriter(file));
			for(int i = 0; i < val1.length; i++) {
				String current = val1[i];
				current+=":";
				char[] value = Integer.toString(val2[i]).toCharArray();
				for(int n = 0; n < value.length; n++) {
					value[n]+=encode;
					current+=value[n];
				}
				write.write(current);
				if(i < val1.length -1) write.newLine();
			}
			write.flush();
			return true;
		}catch(IOException e) {
			return false;
		}finally {
			try { if(write != null) write.close(); }catch(IOException e) {}
		}
	}
	
	public void render(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		int cw = Game.WIDTH*Game.SCALE, ch = Game.HEIGHT*Game.SCALE;
		
		g.setColor(Color.black);
		g.fillRect(0, 0, Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height);
		
		g.drawImage(Artes.MENU, 0, 0, Game.MENU_WIDTH, Game.MENU_HEIGHT, null);
		
		// luz piscando, brilho dos olhos, vinheta e poeira por cima da arte
		Telas.menuLuz(g2, Math.max(cw, Game.MENU_WIDTH), Math.max(ch, Game.MENU_HEIGHT));
		
		if(tela != 0) {
			renderSlots(g2, cw, ch);
			g.setColor(new Color(255,255,255,150));
			g.setFont(new Font("arial", Font.BOLD,12));
			g.drawString("V_"+Game.Versao, 20, ch-20);
			return;
		}
		
		Telas.menuTitulo(g2, "Kayky and the Ninja tracking!", cw/2 - 420, ch/2 - 12);
		
		g.setColor(new Color(255,255,255,150));
		g.setFont(new Font("arial", Font.BOLD,12));
		g.drawString("V_"+Game.Versao, 20, ch-20);
		
		String[] rotulos;
		if(Game.Idioma == "PT") {
			rotulos = new String[]{ pause == false ? "Novo Jogo" : "Continuar", "Idioma", Game.telaCheia ? "Tela: Tela cheia" : "Tela: Janela", "Carregar jogo", "Sair" };
		}else {
			rotulos = new String[]{ pause == false ? "New Game" : "Continue", "Language", Game.telaCheia ? "Screen: Fullscreen" : "Screen: Windowed", "Load", "Exit" };
		}
		for(int i = 0; i < rotulos.length; i++) {
			Telas.menuOpcao(g2, rotulos[i], cw/2 - 380, ch/2 + 30 + i*40, currentOptions == i);
		}
	}
	
	private void renderSlots(Graphics2D g, int cw, int ch) {
		boolean pt = Game.Idioma == "PT";
		String[] zonas = pt ? ZONAS_PT : ZONAS_EN;
		int x = cw/2 - 560, w = 640;
		int topo = 112;
		int passo = Math.min(52, (ch - topo - 60)/(NUM_SLOTS + 1));
		int h = passo - 6;
		
		Telas.menuTitulo(g, pt ? "Salvar / Carregar" : "Save / Load", x, 74);
		
		for(int i = 0; i <= NUM_SLOTS; i++) {
			int y = topo + i*passo + (i == NUM_SLOTS ? 8 : 0);
			boolean sel = (tela == 1 && slotSel == i);
			if(i == NUM_SLOTS) {
				Telas.menuSlot(g, x, y, w, h, pt ? "Voltar" : "Back", "", null, Color.WHITE, sel, true);
				continue;
			}
			int[] d = slotDados[i];
			String esq = "Slot " + (i+1);
			if(d == null) {
				Telas.menuSlot(g, x, y, w, h, esq, pt ? "Vazio" : "Empty", null, Color.WHITE, sel, false);
			}else {
				String info = zonas[d[0]] + "  \u00b7  ACT " + (d[1] + 1);
				boolean bia = d[2] == 2;
				Telas.menuSlot(g, x, y, w, h, esq, info, bia ? "Bia" : "Kayky",
						bia ? new Color(255, 130, 238) : new Color(120, 220, 255), sel, true);
			}
		}
		
		// dica de teclas
		g.setColor(new Color(255,255,255,140));
		g.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 13));
		g.drawString(pt ? "W/S ou setas: mover   |   Z ou Enter: confirmar   |   ESC: voltar"
				        : "W/S or arrows: move   |   Z or Enter: confirm   |   ESC: back", x, ch - 20);
		
		if(tela == 2) {
			g.setColor(new Color(0, 0, 0, 150));
			g.fillRect(x - 10, topo - 8, w + 20, passo*(NUM_SLOTS + 1) + 24);
			int bw = 380, bh = 92 + acoes.length*46;
			int bx = x + w/2 - bw/2, by = topo + (passo*(NUM_SLOTS + 1))/2 - bh/2;
			Telas.menuCaixa(g, bx, by, bw, bh);
			int[] d = slotDados[slotSel];
			Telas.menuTextoCentro(g, "Slot " + (slotSel + 1), bx + bw/2, by + 34, 22, new Color(245, 255, 235));
			if(d != null) {
				Telas.menuTextoCentro(g, zonas[d[0]] + "  \u00b7  ACT " + (d[1] + 1) + "  \u00b7  " + (d[2] == 2 ? "Bia" : "Kayky"),
						bx + bw/2, by + 62, 15, new Color(190, 200, 195));
			}
			String[] ptN = {"Sobrescrever", "Carregar", "Apagar", "Cancelar"};
			String[] enN = {"Overwrite", "Load", "Delete", "Cancel"};
			for(int i = 0; i < acoes.length; i++) {
				int idx = acoes[i].equals("S") ? 0 : acoes[i].equals("C") ? 1 : acoes[i].equals("X") ? 2 : 3;
				String nome = pt ? ptN[idx] : enN[idx];
				// centraliza a opcao (menuOpcao desenha a partir do x)
				g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 26));
				int tw = g.getFontMetrics().stringWidth(nome);
				Telas.menuOpcao(g, nome, bx + bw/2 - tw/2, by + 108 + i*46, acaoSel == i);
			}
		}
		
		if(avisoFrames > 0) {
			float a = Math.min(1f, avisoFrames/40f);
			Telas.menuTextoCentro(g, aviso, x + w/2, ch - 52, 20, new Color(170, 255, 120, (int)(255*a)));
		}
	}
}
