package com.blueuniverse.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.blueuniverse.main.Game;

public class Tile {
	
	//lv0
	public static BufferedImage TILE_FLOORLV0 = Game.spritesheetTileLv0.getSprite(0, 0, 16, 16);
	public static BufferedImage TILE_FLOOR2LV0 = Game.spritesheetTileLv0.getSprite(16, 0, 16, 16);
	public static BufferedImage TILE_FLOORLV0VASO = Game.spritesheetTileLv0.getSprite(16*2, 0, 16, 16);
	public static BufferedImage TILE_WALLLV0 = Game.spritesheetTileLv0.getSprite(0, 16, 16, 16);
	public static BufferedImage TILE_JANELALV0 = Game.spritesheetTileLv0.getSprite(16, 16, 16, 16);
	
	//lv 1
	//floors
	public static BufferedImage TILE_FLOOR = Game.spritesheetTileLv1.getSprite(0, 0, 16, 16);
	public static BufferedImage TILE_FLOORFLOR = Game.spritesheetTileLv1.getSprite(16*2, 0, 16, 16);
	public static BufferedImage TILE_FLOORPEDRA = Game.spritesheetTileLv1.getSprite(16*3, 0, 16, 16);
	public static BufferedImage TILE_FLOORFANTASMA = Game.spritesheetTileLv1.getSprite(16*4, 0, 16, 16);
	
	// walls
	public static BufferedImage TILE_WALL = Game.spritesheetTileLv1.getSprite(0, 16, 16, 16);
	public static BufferedImage TILE_WALLD = Game.spritesheetTileLv1.getSprite(16, 16, 16, 16);
	public static BufferedImage TILE_WALLE = Game.spritesheetTileLv1.getSprite(16*2, 16, 16, 16);
	public static BufferedImage TILE_WALLC = Game.spritesheetTileLv1.getSprite(16*3, 16, 16, 16);
	public static BufferedImage TILE_WAllB = Game.spritesheetTileLv1.getSprite(16*4, 16, 16, 16);
	//cima
	public static BufferedImage TILE_WALLDC = Game.spritesheetTileLv1.getSprite(16*2, 16*2, 16, 16);
	public static BufferedImage TILE_WALLDCE = Game.spritesheetTileLv1.getSprite(16, 16*2, 16, 16);
	public static BufferedImage TILE_WALLCE = Game.spritesheetTileLv1.getSprite(0, 16*2, 16, 16);
	//baixo
	public static BufferedImage TILE_WALLDB = Game.spritesheetTileLv1.getSprite(16*2, 16*3, 16, 16);
	public static BufferedImage TILE_WALLDBE = Game.spritesheetTileLv1.getSprite(16, 16*3, 16, 16);
	public static BufferedImage TILE_WALLBE = Game.spritesheetTileLv1.getSprite(0, 16*3, 16, 16);
	//cb ed
	public static BufferedImage TILE_WALLED = Game.spritesheetTileLv1.getSprite(0, 16*4, 16, 16);
	public static BufferedImage TILE_WALLBC = Game.spritesheetTileLv1.getSprite(16, 16*4, 16, 16);
	//ends
	public static BufferedImage TILE_END = Game.spritesheetTileLv1.getSprite(16, 0, 16, 16);
	
	//lv 2
	public static BufferedImage TILE_FLOOR2 = Game.spritesheetTileLv2.getSprite(0, 0, 16, 16);
	public static BufferedImage TILE_WALL2 = Game.spritesheetTileLv2.getSprite(0, 16, 16, 16);
	public static BufferedImage TILE_END2 = Game.spritesheetTileLv2.getSprite(16, 0, 16, 16);
	public static BufferedImage TILE_FLOORAZUL = Game.spritesheetTileLv2.getSprite(16*2, 0, 16, 16);
	public static BufferedImage TILE_FLOORRED = Game.spritesheetTileLv2.getSprite(16*3, 0, 16, 16);
	public static BufferedImage TILE_FLOORDIMA = Game.spritesheetTileLv2.getSprite(16*4, 0, 16, 16);
	public static BufferedImage TILE_WALLD2 = Game.spritesheetTileLv2.getSprite(16, 16, 16, 16);
	public static BufferedImage TILE_WALLE2 = Game.spritesheetTileLv2.getSprite(16*2, 16, 16, 16);
	public static BufferedImage TILE_WALLC2 = Game.spritesheetTileLv2.getSprite(16*3, 16, 16, 16);
	public static BufferedImage TILE_WALLB2 = Game.spritesheetTileLv2.getSprite(16*4, 16, 16, 16);
	public static BufferedImage TILE_WALLCE2 = Game.spritesheetTileLv2.getSprite(0, 16*2, 16, 16);
	public static BufferedImage TILE_WALLCD2 = Game.spritesheetTileLv2.getSprite(16, 16*2, 16, 16);
	public static BufferedImage TILE_WALLBE2 = Game.spritesheetTileLv2.getSprite(0, 16*3, 16, 16);
	public static BufferedImage TILE_WALLBD2 = Game.spritesheetTileLv2.getSprite(16, 16*3, 16, 16);
	
	///lv 3
	public static BufferedImage TILE_FLOOR3 = Game.spritesheetTileLv3.getSprite(0, 0, 16, 16);
	public static BufferedImage TILE_WALL3 = Game.spritesheetTileLv3.getSprite(0, 16*4, 16, 16);
	public static BufferedImage TILE_WALL3QUADRO = Game.spritesheetTileLv3.getSprite(16, 16*4, 16, 16);
	public static BufferedImage TILE_WALL3JANELA = Game.spritesheetTileLv3.getSprite(16*2, 16*4, 16, 16);
	public static BufferedImage TILE_FLOORGRAMA = Game.spritesheetTileLv3.getSprite(16*3, 16*4, 16, 16);
	public static BufferedImage TILE_END3 = Game.spritesheetTileLv3.getSprite(16*4, 0, 16, 16);
	public static BufferedImage TILE_BANCO = Game.spritesheetTileLv3.getSprite(16, 0, 16, 16);
	public static BufferedImage TILE_VASO = Game.spritesheetTileLv3.getSprite(16*2, 0, 16, 16);
	public static BufferedImage TILE_PANO = Game.spritesheetTileLv3.getSprite(16*3, 0, 16, 16);
	public static BufferedImage TILE_TAPETE = Game.spritesheetTileLv3.getSprite(0, 16, 16, 16);
	public static BufferedImage TILE_TAPETED = Game.spritesheetTileLv3.getSprite(16, 16, 16, 16);
	public static BufferedImage TILE_TAPETEE = Game.spritesheetTileLv3.getSprite(16*2, 16, 16, 16);
	public static BufferedImage TILE_TAPETEC = Game.spritesheetTileLv3.getSprite(16*3, 16, 16, 16);
	public static BufferedImage TILE_TAPETEB = Game.spritesheetTileLv3.getSprite(16*4, 16, 16, 16);
	public static BufferedImage TILE_TAPETEEC = Game.spritesheetTileLv3.getSprite(0, 16*2, 16, 16);
	public static BufferedImage TILE_TAPETEDC = Game.spritesheetTileLv3.getSprite(16, 16*2, 16, 16);
	public static BufferedImage TILE_TAPETEEB = Game.spritesheetTileLv3.getSprite(0, 16*3, 16, 16);
	public static BufferedImage TILE_TAPETEDB = Game.spritesheetTileLv3.getSprite(16, 16*3, 16, 16);
	
	
	///lv 4
	public static BufferedImage TILE_FLOOR4 = Game.spritesheetTileLv4.getSprite(0, 0, 16, 16);
	public static BufferedImage TILE_WALL4 = Game.spritesheetTileLv4.getSprite(16*3, 0, 16, 16);
	public static BufferedImage TILE_END4 = Game.spritesheetTileLv4.getSprite(16, 0, 16, 16);
	public static BufferedImage TILE_WALLDIAMANTE = Game.spritesheetTileLv4.getSprite(16*4, 0, 16, 16);
	public static BufferedImage TILE_FLOOR4C = Game.spritesheetTileLv4.getSprite(16*3, 16, 16, 16);
	public static BufferedImage TILE_FLOOR4CE = Game.spritesheetTileLv4.getSprite(0, 16*2, 16, 16);
	public static BufferedImage TILE_FLOOR4CD = Game.spritesheetTileLv4.getSprite(16, 16*2, 16, 16);
	public static BufferedImage TILE_FLOOR4E = Game.spritesheetTileLv4.getSprite(16*2, 16, 16, 16);
	public static BufferedImage TILE_FLOOR4D = Game.spritesheetTileLv4.getSprite(16, 16, 16, 16);
	public static BufferedImage TILE_FLOOR4B = Game.spritesheetTileLv4.getSprite(16*4, 16, 16, 16);
	public static BufferedImage TILE_FLOOR4BE = Game.spritesheetTileLv4.getSprite(0, 16*3, 16, 16);
	public static BufferedImage TILE_FLOOR4BD = Game.spritesheetTileLv4.getSprite(16, 16*3, 16, 16);
	public static BufferedImage TILE_FLOOR4GELO = Game.spritesheetTileLv4.getSprite(0, 16, 16, 16);
	public static BufferedImage TILE_FLOOR4GELONEVE = Game.spritesheetTileLv4.getSprite(16*2, 0, 16, 16);
	public static BufferedImage TILE_FLOOR4GELO2 = Game.spritesheetTileLv4.getSprite(0, 16*4, 16, 16);
	public static BufferedImage TILE_FLOOR4GELOcimaE = Game.spritesheetTileLv4.getSprite(16*2, 16*3, 16, 16);
	public static BufferedImage TILE_FLOOR4GELOcimaD = Game.spritesheetTileLv4.getSprite(16*3, 16*3, 16, 16);
	public static BufferedImage TILE_FLOOR4GELObaixoE = Game.spritesheetTileLv4.getSprite(16*2, 16*4, 16, 16);
	public static BufferedImage TILE_FLOOR4GELObaixoD = Game.spritesheetTileLv4.getSprite(16*3, 16*4, 16, 16);
	
	public static BufferedImage TILE_FLOOR4GELOcima = Game.spritesheetTileLv4.getSprite(16, 16*4, 16, 16);
	public static BufferedImage TILE_FLOOR4GELObaixo = Game.spritesheetTileLv4.getSprite(16*4, 16*3, 16, 16);
	public static BufferedImage TILE_FLOOR4GELOesquerda = Game.spritesheetTileLv4.getSprite(16*4, 16*2, 16, 16);
	public static BufferedImage TILE_FLOOR4GELOdireita = Game.spritesheetTileLv4.getSprite(16*4, 16*4, 16, 16);
	
	//lv 5
	public static BufferedImage TILE_FLOOR5 = Game.spritesheetTileLv5.getSprite(0, 0, 16, 16);
	public static BufferedImage TILE_FLOOR5GRAMA = Game.spritesheetTileLv5.getSprite(16, 0, 16, 16);
	public static BufferedImage TILE_FLOOR5CACTO = Game.spritesheetTileLv5.getSprite(16*2, 0, 16, 16);
	public static BufferedImage TILE_WALL5 = Game.spritesheetTileLv5.getSprite(0, 16, 16, 16);
	public static BufferedImage TILE_WALL5C = Game.spritesheetTileLv5.getSprite(16*3, 16, 16, 16);
	public static BufferedImage TILE_WALL5CE = Game.spritesheetTileLv5.getSprite(0, 16*2, 16, 16);
	public static BufferedImage TILE_WALL5CD = Game.spritesheetTileLv5.getSprite(16, 16*2, 16, 16);
	public static BufferedImage TILE_WALL5B = Game.spritesheetTileLv5.getSprite(16*4, 16, 16, 16);
	public static BufferedImage TILE_WALL5BE = Game.spritesheetTileLv5.getSprite(0, 16*3, 16, 16);
	public static BufferedImage TILE_WALL5BD = Game.spritesheetTileLv5.getSprite(16, 16*3, 16, 16);
	public static BufferedImage TILE_WALL5E = Game.spritesheetTileLv5.getSprite(16*2, 16, 16, 16);
	public static BufferedImage TILE_WALL5D = Game.spritesheetTileLv5.getSprite(16, 16, 16, 16);
	
	private BufferedImage sprite;
	private int x,y;
	
	public Tile(int x, int y, BufferedImage sprite) {
		this.x = x;
		this.y = y;
		this.sprite = sprite;
	}
	
	public void render(Graphics g) {
		g.drawImage(sprite, x - Camera.x, y - Camera.y, null);
	}
}
