package com.blueuniverse.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import com.blueuniverse.entitys.*;
import com.blueuniverse.graficos.Artes;
import com.blueuniverse.graficos.Spritesheet;
import com.blueuniverse.main.Game;
import com.blueuniverse.main.Mapa;
import com.blueuniverse.main.Sound;
import com.blueuniverse.main.Text;

public class World {
	
	public static Tile[] tiles;
	public static int WIDTH ,HEIGHT;
	public static final int TILE_SIZE = 16;
	
	public World(String path) {
		try {
			BufferedImage map = ImageIO.read(getClass().getResource(path));
			int[] pixels = new int[map.getWidth() * map.getHeight()];
			tiles = new Tile[map.getWidth() * map.getHeight()];
			WIDTH = map.getWidth();
			HEIGHT = map.getHeight();
			map.getRGB(0, 0, map.getWidth(), map.getHeight(), pixels, 0, map.getWidth());
			for(int xx = 0; xx < map.getWidth(); xx++) {
				for(int yy = 0; yy < map.getHeight(); yy++) {
					
					int pixelAtual = pixels[xx + (yy*map.getWidth())];
					
					//tiles
					
					//if (Game.CUR_LEVEL == 0 || Game.CUR_LEVEL == 14) {
					if (Game.setZone == 1) {
						tiles[xx + (yy*WIDTH)] = new FloorTile(xx*16,yy*16,Tile.TILE_FLOORLV0);
						if(pixelAtual == 0xFF00FF21) {
							// chao
							tiles[xx + (yy*WIDTH)] = new FloorTile(xx*16,yy*16,Tile.TILE_FLOORLV0);
						}
						else if(pixelAtual == 0xFF404040) {
							//parede
							tiles[xx + (yy*WIDTH)] = new WallTile(xx*16,yy*16,Tile.TILE_WALLLV0);
						}
						else if(pixelAtual == 0xFF003D3D) {
							//bloco invisivel
							tiles[xx + (yy*WIDTH)] = new WallTile(xx*16,yy*16,Tile.TILE_FLOORLV0);
						}
						else if(pixelAtual == 0xFF007F7F) {
							//janela
							tiles[xx + (yy*WIDTH)] = new WallTile(xx*16,yy*16,Tile.TILE_JANELALV0);
						}
						if(pixelAtual == 0xFF00AFAF) {
							// chao2
							tiles[xx + (yy*WIDTH)] = new FloorTile(xx*16,yy*16,Tile.TILE_FLOOR2LV0);
						}
						if(pixelAtual == 0xFFFF7FB6) {
							// vaso
							tiles[xx + (yy*WIDTH)] = new WallTile(xx*16,yy*16,Tile.TILE_FLOORLV0VASO);
						}
						else if(pixelAtual == 0xFF7F0000){
							//porta
							PortaDireita en = new PortaDireita(xx*16,yy*16,16,16,PortaDireita.PORTA_DIREITA);
							Game.entities.add(en);
							Game.portasDireitas.add(en);
						}
						else if(pixelAtual == 0xFFFF0000){
							//porta
							PortaEsquerda en = new PortaEsquerda(xx*16,yy*16,16,16,PortaDireita.PORTA_ESQUERDA);
							Game.entities.add(en);
							Game.portasEsquerdas.add(en);
						}
						else if(pixelAtual == 0xFFFF006E){
							Npc1 en = new Npc1(xx*16,yy*16,16,16,Entity.NPC1_EN);
							Game.entities.add(en);
							Game.npcs1.add(en);
						}else if(pixelAtual == 0xFF007F0E){
							tiles[xx + (yy*WIDTH)] = new FloorTile(xx*16,yy*16,Tile.TILE_FLOORLV0);
							Barreira en = new Barreira(xx*16,yy*16,16,16,Entity.BARREIRA_ON);
							Game.entities.add(en);
							Game.barreiras.add(en);
						}else if(pixelAtual == 0xFF0094FF){
							tiles[xx + (yy*WIDTH)] = new FloorTile(xx*16,yy*16,Tile.TILE_FLOORLV0);
							Barreira2 en = new Barreira2(xx*16,yy*16,16,16,Entity.BARREIRA2_ON);
							Game.entities.add(en);
							Game.barreiras2.add(en);
						}else if(pixelAtual == 0xFFAF5298){
							tiles[xx + (yy*WIDTH)] = new WallTile(xx*16,yy*16,Tile.TILE_FLOORLV0);
							Botão en = new Botão(xx*16,yy*16,16,16,Entity.BOTAO_ON);
							Game.entities.add(en);
							Game.botoes.add(en);
						}else if(pixelAtual == 0xFF9E0044){
							tiles[xx + (yy*WIDTH)] = new FloorTile(xx*16,yy*16,Tile.TILE_FLOORLV0);
							Boneco en = new Boneco (xx*16,yy*16,16,16,Entity.BONECO_EN);
							Game.entities.add(en);
							Game.bonecos.add(en);
						}
					}
					
					//if (Game.CUR_LEVEL >=1 && Game.CUR_LEVEL <= 3) {
					if (Game.setZone == 2) {
						tiles[xx + (yy*WIDTH)] = new FloorTile(xx*16,yy*16,Tile.TILE_FLOOR);
						if(pixelAtual == 0xFF000000) {
							//tiles[xx + (yy*WIDTH)] = new FloorTile(xx*16,yy*16,Tile.TILE_FLOOR);
							tiles[xx + (yy*WIDTH)] = new WallTile(xx*16,yy*16,Tile.TILE_WALL);
							}
						else if((pixelAtual == 0xFF404040 || pixelAtual == 0xFF00FFFF) &&
								xx-1 > 0 && yy-1 > 0
								&& pixels[xx +( (yy-1)*map.getWidth())] == 0xFF00FF21
								&& pixels[xx +( (yy+1)*map.getWidth())] == 0xFF404040
								&& pixels[(xx+1) +( (yy)*map.getWidth())] == 0xFF00ff21
								&& pixels[(xx-1) +( (yy)*map.getWidth())] == 0xFF404040) {
							//paredes dc
							tiles[xx + (yy*WIDTH)] = new WallTile(xx*16,yy*16,Tile.TILE_WALLDC);
						}
						else if((pixelAtual == 0xFF404040 || pixelAtual == 0xFF00FFFF) &&
								xx-1 > 0 && yy-1 > 0
								&& pixels[xx +( (yy-1)*map.getWidth())] == 0xFF00FF21
								&& pixels[xx +( (yy+1)*map.getWidth())] == 0xFF404040
								&& pixels[(xx-1) +( (yy)*map.getWidth())] == 0xFF00ff21
								&& pixels[(xx+1) +( (yy)*map.getWidth())] == 0xFF404040) {
							//paredes ce
							tiles[xx + (yy*WIDTH)] = new WallTile(xx*16,yy*16,Tile.TILE_WALLCE);
						}
						//baixo
						else if((pixelAtual == 0xFF404040 || pixelAtual == 0xFF00FFFF) &&
								xx-1 > 0 && yy-1 > 0
								&& pixels[xx +( (yy+1)*map.getWidth())] == 0xFF00FF21
								&& pixels[xx +( (yy-1)*map.getWidth())] == 0xFF404040
								&& pixels[(xx-1) +( (yy)*map.getWidth())] == 0xFF00ff21
								&& pixels[(xx+1) +( (yy)*map.getWidth())] == 0xFF404040) {
							//paredes be
							tiles[xx + (yy*WIDTH)] = new WallTile(xx*16,yy*16,Tile.TILE_WALLBE);
						}
						else if((pixelAtual == 0xFF404040 || pixelAtual == 0xFF00FFFF) &&
								xx-1 > 0 && yy-1 > 0
								&& pixels[xx +( (yy+1)*map.getWidth())] == 0xFF00FF21
								&& pixels[xx +( (yy-1)*map.getWidth())] == 0xFF404040
								&& pixels[(xx+1) +( (yy)*map.getWidth())] == 0xFF00ff21
								&& pixels[(xx-1) +( (yy)*map.getWidth())] == 0xFF404040) {
							//paredes bd
							tiles[xx + (yy*WIDTH)] = new WallTile(xx*16,yy*16,Tile.TILE_WALLDB);
						}
						//padr�o
						else if((pixelAtual == 0xFF404040 || pixelAtual == 0xFF00FFFF) &&
								xx-1 > 0 && yy-1 > 0
								&& pixels[xx +( (yy-1)*map.getWidth())] == 0xFF00FF21
								&& pixels[xx +( (yy+1)*map.getWidth())] == 0xFF404040) {
							//paredes c
							tiles[xx + (yy*WIDTH)] = new WallTile(xx*16,yy*16,Tile.TILE_WALLC);
						}
						else if((pixelAtual == 0xFF404040 || pixelAtual == 0xFF00FFFF) &&
								xx-1 > 0 && yy-1 > 0
								&& pixels[xx +( (yy+1)*map.getWidth())] == 0xFF00FF21
								&& pixels[xx +( (yy-1)*map.getWidth())] == 0xFF404040) {
							//paredes b
							tiles[xx + (yy*WIDTH)] = new WallTile(xx*16,yy*16,Tile.TILE_WAllB);
						}
						else if((pixelAtual == 0xFF404040 || pixelAtual == 0xFF00FFFF) &&
								xx-1 > 0 && yy-1 > 0
								&& pixels[(xx+1) +( (yy)*map.getWidth())] == 0xFF00ff21
								&& pixels[(xx-1) +( (yy)*map.getWidth())] == 0xFF404040) {
							//paredes d
							tiles[xx + (yy*WIDTH)] = new WallTile(xx*16,yy*16,Tile.TILE_WALLD);
						}
						else if((pixelAtual == 0xFF404040 || pixelAtual == 0xFF00FFFF) &&
								xx-1 > 0 && yy-1 > 0
								&& pixels[(xx-1) +( (yy)*map.getWidth())] == 0xFF00ff21
								&& pixels[(xx+1) +( (yy)*map.getWidth())] == 0xFF404040) {
							//paredes e
							tiles[xx + (yy*WIDTH)] = new WallTile(xx*16,yy*16,Tile.TILE_WALLE);
						}
						
						
						else if(pixelAtual == 0xFF7F0000) {
							// flor
							tiles[xx + (yy*WIDTH)] = new FloorTile(xx*16,yy*16,Tile.TILE_FLOORFLOR);
						}
						else if(pixelAtual == 0xFFA00000) {
							// pedra
							tiles[xx + (yy*WIDTH)] = new FloorTile(xx*16,yy*16,Tile.TILE_FLOORPEDRA);
						}
						else if(pixelAtual == 0xFFC60000) {
							// fantasma
							tiles[xx + (yy*WIDTH)] = new FloorTile(xx*16,yy*16,Tile.TILE_FLOORFANTASMA);
						}
						
						else if(pixelAtual == 0xFF404040) {
							//paredes
							tiles[xx + (yy*WIDTH)] = new WallTile(xx*16,yy*16,Tile.TILE_WALL);
						}
						//fim
						else if(pixelAtual == 0xFF00FFFF) {
							//final
							tiles[xx + (yy*WIDTH)] = new EndTile(xx*16,yy*16,Tile.TILE_END);
						}
						else if(pixelAtual == 0xFFFF0000){
							//enemy
							Enemy en = new Enemy(xx*16,yy*16,16,16,Entity.ENEMY_EN);
							Game.entities.add(en);
							Game.enemies.add(en);
						}
						else if(pixelAtual == 0xFF4800FF){
							//Killer
							Killer en = new Killer(xx*16,yy*16,16,16,Entity.KILLER_EN);
							Game.entities.add(en);
							Game.killers.add(en);
						}
					}
					
					//if (Game.CUR_LEVEL >= 4 && Game.CUR_LEVEL <= 6) {
					if (Game.setZone == 3) {
						tiles[xx + (yy*WIDTH)] = new FloorTile(xx*16,yy*16,Tile.TILE_FLOOR2);
						//cima
						if((pixelAtual == 0xFF404040 || pixelAtual == 0xFF00FFFF) &&
								xx-1 > 0 && yy-1 > 0
								&& pixels[xx +( (yy-1)*map.getWidth())] == 0xFF00FF21
								&& pixels[xx +( (yy+1)*map.getWidth())] == 0xFF404040
								&& pixels[(xx+1) +( (yy)*map.getWidth())] == 0xFF00ff21
								&& pixels[(xx-1) +( (yy)*map.getWidth())] == 0xFF404040) {
							//paredes dc
							tiles[xx + (yy*WIDTH)] = new WallTile(xx*16,yy*16,Tile.TILE_WALLCD2);
						}
						else if((pixelAtual == 0xFF404040 || pixelAtual == 0xFF00FFFF) &&
								xx-1 > 0 && yy-1 > 0
								&& pixels[xx +( (yy-1)*map.getWidth())] == 0xFF00FF21
								&& pixels[xx +( (yy+1)*map.getWidth())] == 0xFF404040
								&& pixels[(xx-1) +( (yy)*map.getWidth())] == 0xFF00ff21
								&& pixels[(xx+1) +( (yy)*map.getWidth())] == 0xFF404040) {
							//paredes ce
							tiles[xx + (yy*WIDTH)] = new WallTile(xx*16,yy*16,Tile.TILE_WALLCE2);
						}
						//baixo
						else if((pixelAtual == 0xFF404040 || pixelAtual == 0xFF00FFFF) &&
								xx-1 > 0 && yy-1 > 0
								&& pixels[xx +( (yy+1)*map.getWidth())] == 0xFF00FF21
								&& pixels[xx +( (yy-1)*map.getWidth())] == 0xFF404040
								&& pixels[(xx-1) +( (yy)*map.getWidth())] == 0xFF00ff21
								&& pixels[(xx+1) +( (yy)*map.getWidth())] == 0xFF404040) {
							//paredes be
							tiles[xx + (yy*WIDTH)] = new WallTile(xx*16,yy*16,Tile.TILE_WALLBE2);
						}
						else if((pixelAtual == 0xFF404040 || pixelAtual == 0xFF00FFFF) &&
								xx-1 > 0 && yy-1 > 0
								&& pixels[xx +( (yy+1)*map.getWidth())] == 0xFF00FF21
								&& pixels[xx +( (yy-1)*map.getWidth())] == 0xFF404040
								&& pixels[(xx+1) +( (yy)*map.getWidth())] == 0xFF00ff21
								&& pixels[(xx-1) +( (yy)*map.getWidth())] == 0xFF404040) {
							//paredes bd
							tiles[xx + (yy*WIDTH)] = new WallTile(xx*16,yy*16,Tile.TILE_WALLBD2);
						}
						else if((pixelAtual == 0xFF404040 || pixelAtual == 0xFF00FFFF) &&
								xx-1 > 0 && yy-1 > 0
								&& pixels[xx +( (yy-1)*map.getWidth())] == 0xFF00FF21
								&& pixels[xx +( (yy+1)*map.getWidth())] == 0xFF404040) {
							//paredes c
							tiles[xx + (yy*WIDTH)] = new WallTile(xx*16,yy*16,Tile.TILE_WALLC2);
						}
						else if((pixelAtual == 0xFF404040 || pixelAtual == 0xFF00FFFF) &&
								xx-1 > 0 && yy-1 > 0
								&& pixels[xx +( (yy+1)*map.getWidth())] == 0xFF00FF21
								&& pixels[xx +( (yy-1)*map.getWidth())] == 0xFF404040) {
							//paredes b
							tiles[xx + (yy*WIDTH)] = new WallTile(xx*16,yy*16,Tile.TILE_WALLB2);
						}
						else if((pixelAtual == 0xFF404040 || pixelAtual == 0xFF00FFFF) &&
								xx-1 > 0 && yy-1 > 0
								&& pixels[(xx+1) +( (yy)*map.getWidth())] == 0xFF00ff21
								&& pixels[(xx-1) +( (yy)*map.getWidth())] == 0xFF404040) {
							//paredes d
							tiles[xx + (yy*WIDTH)] = new WallTile(xx*16,yy*16,Tile.TILE_WALLD2);
						}
						else if((pixelAtual == 0xFF404040 || pixelAtual == 0xFF00FFFF) &&
								xx-1 > 0 && yy-1 > 0
								&& pixels[(xx-1) +( (yy)*map.getWidth())] == 0xFF00ff21
								&& pixels[(xx+1) +( (yy)*map.getWidth())] == 0xFF404040) {
							//paredes e
							tiles[xx + (yy*WIDTH)] = new WallTile(xx*16,yy*16,Tile.TILE_WALLE2);
						}
						
						
						else if(pixelAtual == 0xFF00FF21) {
							// chao
							tiles[xx + (yy*WIDTH)] = new FloorTile(xx*16,yy*16,Tile.TILE_FLOOR2);
						}
						else if(pixelAtual == 0xFF7F0000) {
							// flor
							tiles[xx + (yy*WIDTH)] = new FloorTile(xx*16,yy*16,Tile.TILE_FLOORAZUL);
						}
						else if(pixelAtual == 0xFFA00000) {
							// pedra
							tiles[xx + (yy*WIDTH)] = new FloorTile(xx*16,yy*16,Tile.TILE_FLOORRED);
						}
						else if(pixelAtual == 0xFFC60000) {
							// fantasma
							tiles[xx + (yy*WIDTH)] = new FloorTile(xx*16,yy*16,Tile.TILE_FLOORDIMA);
						}
						else if(pixelAtual == 0xFF404040 || pixelAtual ==0xFF000000) {
							//paredes
							tiles[xx + (yy*WIDTH)] = new WallTile(xx*16,yy*16,Tile.TILE_WALL2);
						}
						
						//fim
						else if(pixelAtual == 0xFF00FFFF) {
							//final
							tiles[xx + (yy*WIDTH)] = new EndTile2(xx*16,yy*16,Tile.TILE_END2);
						}
						else if(pixelAtual == 0xFFFF0000){
							//enemy
							Enemy2 en = new Enemy2(xx*16,yy*16,16,16,Entity.ENEMY2_EN);
							Game.entities.add(en);
							Game.enemies2.add(en);
						}
						else if(pixelAtual == 0xFF4800FF){
							//Abobora
							Abobora en = new Abobora(xx*16,yy*16,16,32,Entity.ABOBORA_EN);
							Game.entities.add(en);
							Game.aboboras.add(en);
						}
						else if(pixelAtual == 0xFFB200FF){
							//obstaculo
							Espinhos en = new Espinhos(xx*16,yy*16,16,16,Entity.ESPINHOS_EN);
							Game.entities.add(en);
							Game.espinhos.add(en);
						}
					}
					
					//if (Game.CUR_LEVEL >= 7 && Game.CUR_LEVEL <= 9) {
					// zone 4
					if (Game.setZone == 4) {
						tiles[xx + (yy*WIDTH)] = new FloorTile(xx*16,yy*16,Tile.TILE_FLOOR3);
						if(pixelAtual == 0xFF00FF21) {
							tiles[xx + (yy*WIDTH)] = new FloorTile(xx*16,yy*16,Tile.TILE_FLOOR3);
						}
						else if(pixelAtual == 0xFF7F0000) {
							tiles[xx + (yy*WIDTH)] = new WallTile(xx*16,yy*16,Tile.TILE_BANCO);
						}
						else if(pixelAtual == 0xFFA00000) {
							tiles[xx + (yy*WIDTH)] = new WallTile(xx*16,yy*16,Tile.TILE_VASO);
						}
						else if(pixelAtual == 0xFFC60000) {
							tiles[xx + (yy*WIDTH)] = new FloorTile(xx*16,yy*16,Tile.TILE_PANO);
						}
						else if(pixelAtual == 0xFF005B5B) {
							tiles[xx + (yy*WIDTH)] = new FloorTile(xx*16,yy*16,Tile.TILE_FLOORGRAMA);
						}
						else if(pixelAtual == 0xFFFF7FB6) {
							tiles[xx + (yy*WIDTH)] = new FloorTile(xx*16,yy*16,Tile.TILE_TAPETE);
						}
						else if(pixelAtual == 0xFF00CE25) {
							tiles[xx + (yy*WIDTH)] = new FloorTile(xx*16,yy*16,Tile.TILE_TAPETEC);
						}
						else if(pixelAtual == 0xFF00BC22) {
							tiles[xx + (yy*WIDTH)] = new FloorTile(xx*16,yy*16,Tile.TILE_TAPETEB);
						}
						else if(pixelAtual == 0xFF004A0E) {
							tiles[xx + (yy*WIDTH)] = new FloorTile(xx*16,yy*16,Tile.TILE_TAPETEE);
						}
						else if(pixelAtual == 0xFFB7B7B7) {
							tiles[xx + (yy*WIDTH)] = new FloorTile(xx*16,yy*16,Tile.TILE_TAPETED);
						}
						else if(pixelAtual == 0xFF00A01D) {
							tiles[xx + (yy*WIDTH)] = new FloorTile(xx*16,yy*16,Tile.TILE_TAPETEEC);
						}
						else if(pixelAtual == 0xFF007215) {
							tiles[xx + (yy*WIDTH)] = new FloorTile(xx*16,yy*16,Tile.TILE_TAPETEDC);
						}
						else if(pixelAtual == 0xFF5B5B5B) {
							tiles[xx + (yy*WIDTH)] = new FloorTile(xx*16,yy*16,Tile.TILE_TAPETEEB);
						}
						else if(pixelAtual == 0xFF7A7A7A) {
							tiles[xx + (yy*WIDTH)] = new FloorTile(xx*16,yy*16,Tile.TILE_TAPETEDB);
						}
						
						
						else if(pixelAtual == 0xFF404040) {
							//paredes
							tiles[xx + (yy*WIDTH)] = new WallTile(xx*16,yy*16,Tile.TILE_WALL3);
						}
						else if(pixelAtual == 0xFF007F7F) {
							//paredes
							tiles[xx + (yy*WIDTH)] = new WallTile(xx*16,yy*16,Tile.TILE_WALL3JANELA);
						}
						else if(pixelAtual == 0xFF00AFAF) {
							//paredes
							tiles[xx + (yy*WIDTH)] = new WallTile(xx*16,yy*16,Tile.TILE_WALL3QUADRO);
						}
						//fim
						else if(pixelAtual == 0xFF00FFFF) {
							//final
							tiles[xx + (yy*WIDTH)] = new EndTile3(xx*16,yy*16,Tile.TILE_END3);
						}
						else if(pixelAtual == 0xFFFF0000){
							//enemy
							Enemy3 en = new Enemy3(xx*16,yy*16,16,16,Entity.MORCEGOS_EN);
							Game.entities.add(en);
							Game.enemies3.add(en);
						}
						else if(pixelAtual == 0xFFFF6A00){
							//enemy
							Vampiros en = new Vampiros(xx*16,yy*16,16,16,Entity.VAMPIROS_EN);
							Game.entities.add(en);
							Game.vampiros.add(en);
						}
						else if(pixelAtual == 0xFF003D3D){
							//enemy
							tiles[xx + (yy*WIDTH)] = new FloorTile(xx*16,yy*16,Tile.TILE_FLOORGRAMA);
							Arvore en = new Arvore(xx*16,yy*16,16,32,Entity.ARVORE_EN);
							Game.entities.add(en);
							Game.arvores.add(en);
						}
						else if(pixelAtual == 0xFF57007F){
							tiles[xx + (yy*WIDTH)] = new FloorTile(xx*16,yy*16,Tile.TILE_FLOOR3);
							Spawner en = new Spawner(xx*16,yy*16,16,16,null);
							Game.entities.add(en);
						}
						else if(pixelAtual == 0xFF4800FF){
							Sally en = new Sally(xx*16,yy*16,16,16,Entity.SALLY_EN);
							Game.entities.add(en);
							Game.sallys.add(en);
						}
						else if(pixelAtual == 0xFF007F0E){
							Barreira en = new Barreira(xx*16,yy*16,16,16,Entity.BARREIRA_ON);
							Game.entities.add(en);
							Game.barreiras.add(en);
						}else if(pixelAtual == 0xFF0094FF){
							Barreira2 en = new Barreira2(xx*16,yy*16,16,16,Entity.BARREIRA2_ON);
							Game.entities.add(en);
							Game.barreiras2.add(en);
						}else if(pixelAtual == 0xFFAF5298){
							Botão en = new Botão(xx*16,yy*16,16,16,Entity.BOTAO_ON);
							Game.entities.add(en);
							Game.botoes.add(en);
						}
						else if(pixelAtual == 0xFFFFB091){
							tiles[xx + (yy*WIDTH)] = new WallTile(xx*16,yy*16,Tile.TILE_WALL3);
							Quadro en = new Quadro(xx*16,yy*16,16,16,Entity.QUADRO_ON);
							Game.entities.add(en);
							Game.quadros.add(en);
						}
						else if(pixelAtual == 0xFFA163FF){
							SallyCorpo en = new SallyCorpo(xx*16,yy*16,16,16,Entity.SALLYCORPO_EN);
							Game.entities.add(en);
							Game.sallyCorpos.add(en);
						}
					}
					
					
					//if (Game.CUR_LEVEL >= 10 && Game.CUR_LEVEL <= 12) {
					if (Game.setZone == 5) {
						tiles[xx + (yy*WIDTH)] = new FloorTile(xx*16,yy*16,Tile.TILE_FLOOR4);
						if(pixelAtual == 0xFF00ff21 &&
								xx-1 > 0 && yy-1 > 0
								&& pixels[xx +( (yy+1)*map.getWidth())] == 0xFF00FF21
								&& pixels[xx +( (yy-1)*map.getWidth())] == 0xFF404040
								&& pixels[(xx+1) +( (yy)*map.getWidth())] == 0xFF00ff21
								&& pixels[(xx-1) +( (yy)*map.getWidth())] == 0xFF404040) {					
								tiles[xx + (yy*WIDTH)] = new FloorTile(xx*16,yy*16,Tile.TILE_FLOOR4CE);
						}
						else if(pixelAtual == 0xFF00ff21 &&
								xx-1 > 0 && yy-1 > 0
								&& pixels[xx +( (yy+1)*map.getWidth())] == 0xFF00FF21
								&& pixels[xx +( (yy-1)*map.getWidth())] == 0xFF404040
								&& pixels[(xx-1) +( (yy)*map.getWidth())] == 0xFF00ff21
								&& pixels[(xx+1) +( (yy)*map.getWidth())] == 0xFF404040) {
								tiles[xx + (yy*WIDTH)] = new FloorTile(xx*16,yy*16,Tile.TILE_FLOOR4CD);
						}
						else if(pixelAtual == 0xFF00ff21 &&
								xx-1 > 0 && yy-1 > 0
								&& pixels[xx +( (yy-1)*map.getWidth())] == 0xFF00FF21
								&& pixels[xx +( (yy+1)*map.getWidth())] == 0xFF404040
								&& pixels[(xx+1) +( (yy)*map.getWidth())] == 0xFF00ff21
								&& pixels[(xx-1) +( (yy)*map.getWidth())] == 0xFF404040) {
								tiles[xx + (yy*WIDTH)] = new FloorTile(xx*16,yy*16,Tile.TILE_FLOOR4BE);
						}
						else if(pixelAtual == 0xFF00ff21 &&
								xx-1 > 0 && yy-1 > 0
								&& pixels[xx +( (yy-1)*map.getWidth())] == 0xFF00FF21
								&& pixels[xx +( (yy+1)*map.getWidth())] == 0xFF404040
								&& pixels[(xx-1) +( (yy)*map.getWidth())] == 0xFF00ff21
								&& pixels[(xx+1) +( (yy)*map.getWidth())] == 0xFF404040) {
								tiles[xx + (yy*WIDTH)] = new FloorTile(xx*16,yy*16,Tile.TILE_FLOOR4BD);
						}
						else if(pixelAtual == 0xFF00ff21 &&
								xx-1 > 0 && yy-1 > 0
								&& pixels[xx +( (yy+1)*map.getWidth())] == 0xFF00FF21
								&& pixels[xx +( (yy-1)*map.getWidth())] == 0xFF404040) {
								tiles[xx + (yy*WIDTH)] = new FloorTile(xx*16,yy*16,Tile.TILE_FLOOR4C);
						}
						else if(pixelAtual == 0xFF00ff21 &&
								xx-1 > 0 && yy-1 > 0
								&& pixels[xx +( (yy-1)*map.getWidth())] == 0xFF00FF21
								&& pixels[xx +( (yy+1)*map.getWidth())] == 0xFF404040) {
								tiles[xx + (yy*WIDTH)] = new FloorTile(xx*16,yy*16,Tile.TILE_FLOOR4B);
						}
						else if(pixelAtual == 0xFF00ff21 &&
								xx-1 > 0 && yy-1 > 0
								&& pixels[(xx+1) +( (yy)*map.getWidth())] == 0xFF00ff21
								&& pixels[(xx-1) +( (yy)*map.getWidth())] == 0xFF404040) {
								tiles[xx + (yy*WIDTH)] = new FloorTile(xx*16,yy*16,Tile.TILE_FLOOR4E);
						}
						else if(pixelAtual == 0xFF00ff21 &&
								xx-1 > 0 && yy-1 > 0
								&& pixels[(xx-1) +( (yy)*map.getWidth())] == 0xFF00ff21
								&& pixels[(xx+1) +( (yy)*map.getWidth())] == 0xFF404040) {
								tiles[xx + (yy*WIDTH)] = new FloorTile(xx*16,yy*16,Tile.TILE_FLOOR4D);
						}
						////gelos
						else if(pixelAtual == 0xFFB200FF &&
								xx-1 > 0 && yy-1 > 0
								&& pixels[xx +( (yy+1)*map.getWidth())] == 0xFFB200FF
								&& pixels[xx +( (yy-1)*map.getWidth())] == 0xFF00FF21
								&& pixels[(xx+1) +( (yy)*map.getWidth())] == 0xFFB200FF
								&& pixels[(xx-1) +( (yy)*map.getWidth())] == 0xFF00FF21) {					
								tiles[xx + (yy*WIDTH)] = new FloorTile(xx*16,yy*16,Tile.TILE_FLOOR4GELOcimaE);
						}
						else if(pixelAtual == 0xFFB200FF &&
								xx-1 > 0 && yy-1 > 0
								&& pixels[xx +( (yy+1)*map.getWidth())] == 0xFFB200FF
								&& pixels[xx +( (yy-1)*map.getWidth())] == 0xFF00FF21
								&& pixels[(xx-1) +( (yy)*map.getWidth())] == 0xFFB200FF
								&& pixels[(xx+1) +( (yy)*map.getWidth())] == 0xFF00FF21) {
								tiles[xx + (yy*WIDTH)] = new FloorTile(xx*16,yy*16,Tile.TILE_FLOOR4GELOcimaD);
						}
						else if(pixelAtual == 0xFFB200FF &&
								xx-1 > 0 && yy-1 > 0
								&& pixels[xx +( (yy-1)*map.getWidth())] == 0xFFB200FF
								&& pixels[xx +( (yy+1)*map.getWidth())] == 0xFF00FF21
								&& pixels[(xx+1) +( (yy)*map.getWidth())] == 0xFFB200FF
								&& pixels[(xx-1) +( (yy)*map.getWidth())] == 0xFF00FF21) {
								tiles[xx + (yy*WIDTH)] = new FloorTile(xx*16,yy*16,Tile.TILE_FLOOR4GELObaixoE);
						}
						else if(pixelAtual == 0xFFB200FF &&
								xx-1 > 0 && yy-1 > 0
								&& pixels[xx +( (yy-1)*map.getWidth())] == 0xFFB200FF
								&& pixels[xx +( (yy+1)*map.getWidth())] == 0xFF00FF21
								&& pixels[(xx-1) +( (yy)*map.getWidth())] == 0xFFB200FF
								&& pixels[(xx+1) +( (yy)*map.getWidth())] == 0xFF00FF21) {
								tiles[xx + (yy*WIDTH)] = new FloorTile(xx*16,yy*16,Tile.TILE_FLOOR4GELObaixoD);
						}
						
						else if(pixelAtual == 0xFFB200FF &&
								xx-1 > 0 && yy-1 > 0
								&& pixels[xx +( (yy+1)*map.getWidth())] == 0xFFB200FF
								&& pixels[xx +( (yy-1)*map.getWidth())] == 0xFF00ff21) {
								tiles[xx + (yy*WIDTH)] = new FloorTile(xx*16,yy*16,Tile.TILE_FLOOR4GELOcima);
						}
						else if(pixelAtual == 0xFFB200FF &&
								xx-1 > 0 && yy-1 > 0
								&& pixels[xx +( (yy-1)*map.getWidth())] == 0xFFB200FF
								&& pixels[xx +( (yy+1)*map.getWidth())] == 0xFF00ff21) {
								tiles[xx + (yy*WIDTH)] = new FloorTile(xx*16,yy*16,Tile.TILE_FLOOR4GELObaixo);
						}
						else if(pixelAtual == 0xFFB200FF &&
								xx-1 > 0 && yy-1 > 0
								&& pixels[(xx+1) +( (yy)*map.getWidth())] == 0xFFB200FF
								&& pixels[(xx-1) +( (yy)*map.getWidth())] == 0xFF00ff21) {
								tiles[xx + (yy*WIDTH)] = new FloorTile(xx*16,yy*16,Tile.TILE_FLOOR4GELOesquerda);
						}
						else if(pixelAtual == 0xFFB200FF &&
								xx-1 > 0 && yy-1 > 0
								&& pixels[(xx-1) +( (yy)*map.getWidth())] == 0xFFB200FF
								&& pixels[(xx+1) +( (yy)*map.getWidth())] == 0xFF00ff21) {
								tiles[xx + (yy*WIDTH)] = new FloorTile(xx*16,yy*16,Tile.TILE_FLOOR4GELOdireita);
						}
						
						
						else if(pixelAtual == 0xFFB200FF) {
							tiles[xx + (yy*WIDTH)] = new FloorTile(xx*16,yy*16,Tile.TILE_FLOOR4GELO);
						}
						else if(pixelAtual == 0xFFA00000) {
							tiles[xx + (yy*WIDTH)] = new IceTile(xx*16,yy*16,Tile.TILE_FLOOR4GELO2);
						}
						else if(pixelAtual == 0xFFC684AE) {
							tiles[xx + (yy*WIDTH)] = new FloorTile(xx*16,yy*16,Tile.TILE_FLOOR4GELONEVE);
						}
						else if(pixelAtual == 0xFF404040) {
							tiles[xx + (yy*WIDTH)] = new WallTile(xx*16,yy*16,Tile.TILE_WALL4);
						}
						else if(pixelAtual == 0xFFC60000) {
							tiles[xx + (yy*WIDTH)] = new WallTile(xx*16,yy*16,Tile.TILE_WALLDIAMANTE);
						}
						else if(pixelAtual == 0xFF00FFFF) {
							//final
							tiles[xx + (yy*WIDTH)] = new EndTile3(xx*16,yy*16,Tile.TILE_END4);
						}
						else if(pixelAtual == 0xFFFF0000){
							//enemy
							Flocos en = new Flocos(xx*16,yy*16,16,16,Entity.FLOCOS_EN);
							Game.entities.add(en);
							Game.flocos.add(en);
						}
						else if(pixelAtual == 0xFFFF6A00){
							//enemy
							Lili en = new Lili(xx*16,yy*16,16,16,Entity.LILI_EN);
							Game.entities.add(en);
							Game.lilis.add(en);
						}
						else if(pixelAtual == 0xFFBF133B){
							//enemy
							Muriel en = new Muriel(xx*16,yy*16,16,16,Entity.MURIEL_EN);
							Game.entities.add(en);
							Game.muriels.add(en);
						}
						else if(pixelAtual == 0xFF00FF21) {tiles[xx + (yy*WIDTH)] = new FloorTile(xx*16,yy*16,Tile.TILE_FLOOR4);}
					}
					
					//if (Game.CUR_LEVEL == 13) {
					if (Game.setZone == 6) {
						tiles[xx + (yy*WIDTH)] = new FloorTile(xx*16,yy*16,Tile.TILE_FLOOR5);
						if(pixelAtual == 0xFF00FF21) {
							tiles[xx + (yy*WIDTH)] = new FloorTile(xx*16,yy*16,Tile.TILE_FLOOR5);
						}else if(pixelAtual == 0xFFB200FF) {
							tiles[xx + (yy*WIDTH)] = new FloorTile(xx*16,yy*16,Tile.TILE_FLOOR5GRAMA);
						}else if(pixelAtual == 0xFF7F0000) {
							tiles[xx + (yy*WIDTH)] = new FloorTile(xx*16,yy*16,Tile.TILE_FLOOR5CACTO);
						}
						
						if(pixelAtual == 0xFF404040 &&
								xx-1 > 0 && yy-1 > 0
								&& pixels[xx +( (yy-1)*map.getWidth())] == 0xFF00FF21
								&& pixels[xx +( (yy+1)*map.getWidth())] == 0xFF404040
								&& pixels[(xx-1) +( (yy)*map.getWidth())] == 0xFF00ff21
								&& pixels[(xx+1) +( (yy)*map.getWidth())] == 0xFF404040) {					
							//baixo direita
							tiles[xx + (yy*WIDTH)] = new WallTile(xx*16,yy*16,Tile.TILE_WALL5CE);
						} else if(pixelAtual == 0xFF404040 &&
								xx-1 > 0 && yy-1 > 0
								&& pixels[xx +( (yy-1)*map.getWidth())] == 0xFF00FF21
								&& pixels[xx +( (yy+1)*map.getWidth())] == 0xFF404040
								&& pixels[(xx+1) +( (yy)*map.getWidth())] == 0xFF00ff21
								&& pixels[(xx-1) +( (yy)*map.getWidth())] == 0xFF404040) {					
							//baixo esquerda
							tiles[xx + (yy*WIDTH)] = new WallTile(xx*16,yy*16,Tile.TILE_WALL5CD);
						}else if(pixelAtual == 0xFF404040 &&
								xx-1 > 0 && yy-1 > 0
								&& pixels[xx +( (yy+1)*map.getWidth())] == 0xFF00FF21
								&& pixels[xx +( (yy-1)*map.getWidth())] == 0xFF404040
								&& pixels[(xx-1) +( (yy)*map.getWidth())] == 0xFF00ff21
								&& pixels[(xx+1) +( (yy)*map.getWidth())] == 0xFF404040) {					
							//cima direita
							tiles[xx + (yy*WIDTH)] = new WallTile(xx*16,yy*16,Tile.TILE_WALL5BE);
						} else if(pixelAtual == 0xFF404040 &&
								xx-1 > 0 && yy-1 > 0
								&& pixels[xx +( (yy+1)*map.getWidth())] == 0xFF00FF21
								&& pixels[xx +( (yy-1)*map.getWidth())] == 0xFF404040
								&& pixels[(xx+1) +( (yy)*map.getWidth())] == 0xFF00ff21
								&& pixels[(xx-1) +( (yy)*map.getWidth())] == 0xFF404040) {					
							//cima esquerda
							tiles[xx + (yy*WIDTH)] = new WallTile(xx*16,yy*16,Tile.TILE_WALL5BD);
						}else if(pixelAtual == 0xFF404040 &&
								xx-1 > 0 && yy-1 > 0
								&& pixels[xx +( (yy+1)*map.getWidth())] == 0xFF00FF21
								&& pixels[xx +( (yy-1)*map.getWidth())] == 0xFF404040) {					
							//cima 
							tiles[xx + (yy*WIDTH)] = new WallTile(xx*16,yy*16,Tile.TILE_WALL5B);
						}else if(pixelAtual == 0xFF404040 &&
								xx-1 > 0 && yy-1 > 0
								&& pixels[xx +( (yy-1)*map.getWidth())] == 0xFF00FF21
								&& pixels[xx +( (yy+1)*map.getWidth())] == 0xFF404040) {					
							//baixo 
							tiles[xx + (yy*WIDTH)] = new WallTile(xx*16,yy*16,Tile.TILE_WALL5C);
						}else if(pixelAtual == 0xFF404040 &&
								xx-1 > 0 && yy-1 > 0
								&& pixels[(xx+1) +( (yy)*map.getWidth())] == 0xFF00ff21
								&& pixels[(xx-1) +( (yy)*map.getWidth())] == 0xFF404040) {					
							//direita
							tiles[xx + (yy*WIDTH)] = new WallTile(xx*16,yy*16,Tile.TILE_WALL5D);
						}else if(pixelAtual == 0xFF404040 &&
								xx-1 > 0 && yy-1 > 0
								&& pixels[(xx-1) +( (yy)*map.getWidth())] == 0xFF00ff21
								&& pixels[(xx+1) +( (yy)*map.getWidth())] == 0xFF404040) {					
							//esquerda
							tiles[xx + (yy*WIDTH)] = new WallTile(xx*16,yy*16,Tile.TILE_WALL5E);
						}
						else if(pixelAtual == 0xFF404040) {
							tiles[xx + (yy*WIDTH)] = new WallTile(xx*16,yy*16,Tile.TILE_WALL5);
						}
						else if(pixelAtual == 0xFF000000) {
							tiles[xx + (yy*WIDTH)] = new WallTile(xx*16,yy*16,Tile.TILE_WALL5);
						}
						else if(pixelAtual == 0xFFBF133B){
							Muriel en = new Muriel(xx*16,yy*16,16,16,Entity.MURIEL_EN);
							Game.entities.add(en);
							Game.muriels.add(en);
						}
						else if(pixelAtual == 0xFF003D3D){
							Bia en = new Bia(xx*16,yy*16,16,16,Entity.BIA_EN);
							Game.entities.add(en);
							Game.bias.add(en);
						}
						else if(pixelAtual == 0xFF57007F){
							tiles[xx + (yy*WIDTH)] = new FloorTile(xx*16,yy*16,Tile.TILE_FLOOR5);
							Spawner en = new Spawner(xx*16,yy*16,16,16,null);
							Game.entities.add(en);
						}
						else if(pixelAtual == 0xFFA09A61){
							Lusion en = new Lusion(xx*16,yy*16,16,32,Entity.LUSION_EN);
							Game.entities.add(en);
							Game.lusions.add(en);
						}else if(pixelAtual == 0xFFFF2700){
							Abobora2 en = new Abobora2(xx*16,yy*16,16,16,Entity.ABOBORA2_EN);
							Game.entities.add(en);
							Game.aboboras2.add(en);
						}
					}
					
					
					if(pixelAtual == 0xFF0026FF) {
						//player
						Game.player.setX(xx*16);
						Game.player.setY(yy*16);
					}
					else if(pixelAtual == 0xFF7F3300) {
						//weapon
						Game.entities.add(new Weapon(xx*16,yy*16,16,16,Entity.WEAPON_EN));
					}else if(pixelAtual == 0xFFC0C0C0) {
						//lifepack
						Lifepack pack = new Lifepack(xx*16,yy*16,16,16,Entity.LIFEPACK_EN);
						Game.entities.add(pack);
					}else if(pixelAtual == 0xFFFFD800) {
						//bullet
						Game.entities.add(new Bullet(xx*16,yy*16,16,16,Entity.BULLET_EN));
					}
					
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static boolean isFree(int xnext,int ynext) {
		int x1 = xnext / TILE_SIZE;
		int y1 = ynext / TILE_SIZE;
		
		int x2 = (xnext+TILE_SIZE-1) / TILE_SIZE;
		int y2 = ynext / TILE_SIZE;
		
		int x3 = xnext / TILE_SIZE;
		int y3 = (ynext+TILE_SIZE-1) / TILE_SIZE;
		
		int x4 = (xnext+TILE_SIZE-1) / TILE_SIZE;
		int y4 = (ynext+TILE_SIZE-1) / TILE_SIZE;
		
		return !((tiles[x1 + (y1* World.WIDTH)] instanceof WallTile) 
				|| (tiles[x2 + (y2* World.WIDTH)] instanceof WallTile)
				|| (tiles[x3 + (y3* World.WIDTH)] instanceof WallTile) 
				|| (tiles[x4 + (y4* World.WIDTH)] instanceof WallTile)); 
	}
	
	public static boolean isNextLevel(int xnext,int ynext) {
		int x1 = xnext / TILE_SIZE;
		int y1 = ynext / TILE_SIZE;
		
		int x2 = (xnext+TILE_SIZE-1) / TILE_SIZE;
		int y2 = ynext / TILE_SIZE;
		
		int x3 = xnext / TILE_SIZE;
		int y3 = (ynext+TILE_SIZE-1) / TILE_SIZE;
		
		int x4 = (xnext+TILE_SIZE-1) / TILE_SIZE;
		int y4 = (ynext+TILE_SIZE-1) / TILE_SIZE;
		
		return ((tiles[x1 + (y1* World.WIDTH)] instanceof EndTile) 
				|| (tiles[x2 + (y2* World.WIDTH)] instanceof EndTile)
				|| (tiles[x3 + (y3* World.WIDTH)] instanceof EndTile) 
				|| (tiles[x4 + (y4* World.WIDTH)] instanceof EndTile)
				|| (tiles[x1 + (y1* World.WIDTH)] instanceof EndTile2) 
				|| (tiles[x2 + (y2* World.WIDTH)] instanceof EndTile2)
				|| (tiles[x3 + (y3* World.WIDTH)] instanceof EndTile2) 
				|| (tiles[x4 + (y4* World.WIDTH)] instanceof EndTile2)
				|| (tiles[x1 + (y1* World.WIDTH)] instanceof EndTile3) 
				|| (tiles[x2 + (y2* World.WIDTH)] instanceof EndTile3)
				|| (tiles[x3 + (y3* World.WIDTH)] instanceof EndTile3) 
				|| (tiles[x4 + (y4* World.WIDTH)] instanceof EndTile3))
				; 
	}
	
	public static boolean isIce(int xnext,int ynext) {
		int x1 = xnext / TILE_SIZE;
		int y1 = ynext / TILE_SIZE;
		
		int x2 = (xnext+TILE_SIZE-1) / TILE_SIZE;
		int y2 = ynext / TILE_SIZE;
		
		int x3 = xnext / TILE_SIZE;
		int y3 = (ynext+TILE_SIZE-1) / TILE_SIZE;
		
		int x4 = (xnext+TILE_SIZE-1) / TILE_SIZE;
		int y4 = (ynext+TILE_SIZE-1) / TILE_SIZE;
		
		return ((tiles[x1 + (y1* World.WIDTH)] instanceof IceTile) 
				|| (tiles[x2 + (y2* World.WIDTH)] instanceof IceTile)
				|| (tiles[x3 + (y3* World.WIDTH)] instanceof IceTile) 
				|| (tiles[x4 + (y4* World.WIDTH)] instanceof IceTile));  
	}
	
	public static void generateParticles(int amount, int x, int y) {
		for(int i = 0; i < amount; i++) {
			Game.entities.add(new Particles(x,y,1,1,null));
		}
	}
	public static void generateParticles2(int amount, int x, int y) {
		for(int i = 0; i < amount; i++) {
			Game.entities.add(new Particles2(x,y,1,1,null));
		}
	}
	
	public static boolean isBalaFree(int xnext,int ynext, int whidt, int height) {
		int x1 = xnext / TILE_SIZE;
		int y1 = ynext / TILE_SIZE;
		
		int x2 = (xnext+whidt-1) / TILE_SIZE;
		int y2 = ynext / TILE_SIZE;
		
		int x3 = xnext / TILE_SIZE;
		int y3 = (ynext+height-1) / TILE_SIZE;
		
		int x4 = (xnext+whidt-1) / TILE_SIZE;
		int y4 = (ynext+height-1) / TILE_SIZE;
		
		return !((tiles[x1 + (y1* World.WIDTH)] instanceof WallTile) 
				|| (tiles[x2 + (y2* World.WIDTH)] instanceof WallTile)
				|| (tiles[x3 + (y3* World.WIDTH)] instanceof WallTile) 
				|| (tiles[x4 + (y4* World.WIDTH)] instanceof WallTile)); 
	}
	
	public static void restartGame(String level){
		
		Game.entities.clear();
		Game.bonecos.clear();
		Game.botoes.clear();
		Game.barreiras.clear();
		Game.barreiras2.clear();
		Game.quadros.clear();
		Game.sallyCorpos.clear();
		Game.enemies.clear();
		Game.enemies2.clear();
		Game.enemies3.clear();
		Game.bullets.clear();
		Game.killers.clear();
		Game.sallys.clear();
		Game.muriels.clear();
		Game.aboboras.clear();
		Game.aboboras2.clear();
		Game.espinhos.clear();
		Game.vampiros.clear();
		Game.lusions.clear();
		Game.arvores.clear();
		Game.npcs1.clear();
		Game.bias.clear();
		
		Game.entities = new ArrayList<Entity>();
		Game.bonecos = new ArrayList<Boneco>();
		Game.botoes = new ArrayList<Botão>();
		Game.barreiras = new ArrayList<Barreira>();
		Game.barreiras2 = new ArrayList<Barreira2>();
		Game.quadros = new ArrayList<Quadro>();
		Game.sallyCorpos = new ArrayList<SallyCorpo>();
		Game.enemies = new ArrayList<Enemy>();
		Game.enemies2 = new ArrayList<Enemy2>();
		Game.enemies3 = new ArrayList<Enemy3>();
		Game.killers = new ArrayList<Killer>();
		Game.sallys = new ArrayList<Sally>();
		Game.muriels = new ArrayList<Muriel>();
		Game.aboboras = new ArrayList<Abobora>();
		Game.aboboras2 = new ArrayList<Abobora2>();
		Game.espinhos = new ArrayList<Espinhos>();
		Game.vampiros = new ArrayList<Vampiros>();
		Game.lusions = new ArrayList<Lusion>();
		Game.npcs1 = new ArrayList<Npc1>();
		Game.bias = new ArrayList<Bia>();
		Game.arvores = new ArrayList<Arvore>();
		Game.bullets = new ArrayList<BulletShoot>();
		Game.punchs = new ArrayList<PunchShoot>();
		
		
		Game.spritesheet = new Spritesheet("/img/spritesheet.png");
		
		Game.player = new Player(0,0,16,16,Game.spritesheet.getSprite(0, 64, 16, 16));
		Game.entities.add(Game.player);
		Game.world = new World("/leveis/"+Game.setZone+Game.CUR_LEVEL+".png");
		//Game.spritesheetArtes = new Spritesheet("/art/"+Game.setZone+Game.CUR_ART+".png");
		Artes.ARTS0 = Game.spritesheetArtes.getArte(Game.ART_WIDTH, Game.ART_HEIGHT);
		
		Game.minimapa = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		Game.minimapaPixels = ((DataBufferInt)Game.minimapa.getRaster().getDataBuffer()).getData();
		
		
		return;
	}
	
	public static void setGame() {
		if(Game.setZone == 1) {
			if(Game.CUR_LEVEL == 0) {
				Game.player.y = (16*4);
				Game.player.x = (16*2);
				Game.spritesheetArtes = new Spritesheet("/art/"+10+".png");
				Artes.ARTS0 = Game.spritesheetArtes.getArte(Game.ART_WIDTH, Game.ART_HEIGHT);
			}else if(Game.CUR_LEVEL == 1) {
				Text.curText = 2;
				Botão.check = 0;
			}else if(Game.CUR_LEVEL == 2) {
				Game.spritesheetArtes = new Spritesheet("/art/"+12+".png");
				Artes.ARTS0 = Game.spritesheetArtes.getArte(Game.ART_WIDTH, Game.ART_HEIGHT);
				Player.setPlayer = 2;
				Game.player.dir = 3;
			}else if(Game.CUR_LEVEL == 3) {
				Game.spritesheetArtes = new Spritesheet("/art/"+13+".png");
				Artes.ARTS0 = Game.spritesheetArtes.getArte(Game.ART_WIDTH, Game.ART_HEIGHT);
			}
		}else if(Game.setZone == 2) {
			if(Game.CUR_LEVEL == 0) {
				Game.spritesheetArtes = new Spritesheet("/art/"+20+".png");
				Artes.ARTS0 = Game.spritesheetArtes.getArte(Game.ART_WIDTH, Game.ART_HEIGHT);
			}else if(Game.CUR_LEVEL == 2) {
				Game.spritesheetArtes = new Spritesheet("/art/"+21+".png");
				Artes.ARTS0 = Game.spritesheetArtes.getArte(Game.ART_WIDTH, Game.ART_HEIGHT);
			}else if(Game.CUR_LEVEL == 3) {
				Player.setPlayer = 2;
			}
		}else if(Game.setZone == 3) {
			if(Game.CUR_LEVEL == 0) {
				Game.spritesheetArtes = new Spritesheet("/art/"+30+".png");
				Artes.ARTS0 = Game.spritesheetArtes.getArte(Game.ART_WIDTH, Game.ART_HEIGHT);
			}else if(Game.CUR_LEVEL == 1) {
				Game.spritesheetArtes = new Spritesheet("/art/"+31+".png");
				Artes.ARTS0 = Game.spritesheetArtes.getArte(Game.ART_WIDTH, Game.ART_HEIGHT);
			}else if(Game.CUR_LEVEL == 2) {
				Game.spritesheetArtes = new Spritesheet("/art/"+32+".png");
				Artes.ARTS0 = Game.spritesheetArtes.getArte(Game.ART_WIDTH, Game.ART_HEIGHT);
			}else if(Game.CUR_LEVEL == 3) {
				Player.setPlayer = 2;
			}
		}else if(Game.setZone == 4) {
			if(Game.CUR_LEVEL == 0) {
				Game.spritesheetArtes = new Spritesheet("/art/"+40+".png");
				Artes.ARTS0 = Game.spritesheetArtes.getArte(Game.ART_WIDTH, Game.ART_HEIGHT);
				Botão.check = 0;
			}else if(Game.CUR_LEVEL == 1) {
				Botão.check = 0;
			}else if(Game.CUR_LEVEL == 2) {
				Game.spritesheetArtes = new Spritesheet("/art/"+41+".png");
				Artes.ARTS0 = Game.spritesheetArtes.getArte(Game.ART_WIDTH, Game.ART_HEIGHT);
				Text.curText = 3;
			}else if(Game.CUR_LEVEL == 3) {
				Text.curText = 1;
				Player.setPlayer = 2;
				Game.player.dir = 1;
				Botão.check = 0;
				Lusion.spawnLusion = true;
			}
		}else if(Game.setZone == 5) {
			if(Game.CUR_LEVEL == 0) {
				Text.interage = false;
			}else if(Game.CUR_LEVEL == 1) {
				Game.spritesheetArtes = new Spritesheet("/art/"+51+".png");
				Artes.ARTS0 = Game.spritesheetArtes.getArte(Game.ART_WIDTH, Game.ART_HEIGHT);
			}else if(Game.CUR_LEVEL == 2) {
				Muriel.remove();
				Entity bia = new Bia(Game.player.getX()-(16*2),Game.player.getY()-(16),16,16,Game.spritesheetBias.getSprite(0, 0, 16, 16));
				Game.entities.add(bia);
				Abobora en = new Abobora(Game.player.getX()+(16*5),Game.player.getY(),16,32,Entity.ABOBORA_EN);
				Game.entities.add(en);
				Game.aboboras.add(en);
			}
		}else if(Game.setZone == 6) {
			if(Game.CUR_LEVEL == 1) {
				Game.spritesheetArtes = new Spritesheet("/art/"+61+".png");
				Artes.ARTS0 = Game.spritesheetArtes.getArte(Game.ART_WIDTH, Game.ART_HEIGHT);
			}else if(Game.CUR_LEVEL == 2) {
				Text.arteView = true;
				Text.curText = 1;
				Text.xArte = 0;
				Game.gameState = "TEXT";
			    Text.frases = true;
			    Text.showMessage = true;
			    Bia.biaReset();
			}
		}
		
		
	} 
	
	public void render(Graphics g) {
		
		int xstart = Camera.x >> 4;
		int ystart = Camera.y >> 4;
		
		int xfinal = xstart + (Game.WIDTH >> 4) + 2;
		int yfinal = ystart + (Game.HEIGHT >> 4) + 2;
		
		for(int xx = xstart; xx <= xfinal; xx++) {
			for(int yy = ystart; yy <= yfinal; yy++) {
				if(xx < 0 || yy < 0 || xx >= WIDTH || yy >= HEIGHT)
					continue;
				Tile tile = tiles[xx + (yy*WIDTH)];
				tile.render(g);
			}
		}
	}


}
