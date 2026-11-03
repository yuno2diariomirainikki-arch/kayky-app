package com.blueuniverse.entitys;

import java.awt.Color;
import java.awt.Graphics;

import com.blueuniverse.main.Game;
import com.blueuniverse.world.Camera;

/**
 * Particula de velocidade: um risquinho que fica para tras de quem corre / da dash.
 * Usada no dash dos inimigos de gelo e na corrida da Bia.
 */
public class VelParticle extends Entity {
	
	private double vx, vy;      // movimento da particula
	private double lx, ly;      // direcao do risco (sentido contrario ao movimento de quem corre)
	private int vida, maxVida;
	private int comprimento;
	private Color cor;
	
	public VelParticle(double x, double y, double vx, double vy, double dirx, double diry, int vida, int comprimento, Color cor) {
		super((int)x, (int)y, 1, 1, null);
		this.x = x;
		this.y = y;
		this.vx = vx;
		this.vy = vy;
		this.lx = dirx;
		this.ly = diry;
		this.vida = this.maxVida = vida;
		this.comprimento = comprimento;
		this.cor = cor;
		this.depth = 0;
	}
	
	public void tick() {
		x += vx;
		y += vy;
		vida--;
		if(vida <= 0) {
			Game.entities.remove(this);
		}
	}
	
	public void render(Graphics g) {
		float t = Math.max(0f, (float)vida / maxVida);
		int x0 = (int)x - Camera.x, y0 = (int)y - Camera.y;
		int x1 = x0 + (int)Math.round(lx * comprimento * (0.4 + 0.6*t));
		int y1 = y0 + (int)Math.round(ly * comprimento * (0.4 + 0.6*t));
		// sombra escura (da contraste em chao claro) + nucleo brilhante
		g.setColor(new Color(30, 20, 70, (int)(150 * t)));
		g.drawLine(x0, y0 + 1, x1, y1 + 1);
		g.drawLine(x0 + 1, y0, x1 + 1, y1);
		g.setColor(new Color(cor.getRed(), cor.getGreen(), cor.getBlue(), (int)(255 * Math.min(1f, t * 1.4f))));
		g.drawLine(x0, y0, x1, y1);
	}
	
	/**
	 * Solta particulas de velocidade atras de quem se move.
	 * @param cx,cy  centro de quem corre
	 * @param dx,dy  direcao do movimento (nao precisa estar normalizada)
	 */
	public static void rastro(double cx, double cy, double dx, double dy, int qtd, Color cor) {
		double len = Math.sqrt(dx*dx + dy*dy);
		if(len < 0.0001) return;
		dx /= len; dy /= len;
		double px = -dy, py = dx; // perpendicular
		for(int i = 0; i < qtd; i++) {
			double lado = (Game.rand.nextDouble() - 0.5) * 12;
			double atras = Game.rand.nextDouble() * 5;
			double vel = 0.3 + Game.rand.nextDouble() * 0.7;
			VelParticle p = new VelParticle(
				cx + px*lado - dx*atras, cy + py*lado - dy*atras,
				-dx*vel, -dy*vel,
				-dx, -dy,
				8 + Game.rand.nextInt(7),
				6 + Game.rand.nextInt(7),
				cor);
			Game.entities.add(p);
		}
	}
}
