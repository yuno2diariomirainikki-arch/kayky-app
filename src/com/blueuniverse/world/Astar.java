package com.blueuniverse.world;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * A* otimizado.
 * Antes: a lista aberta era reordenada inteira a cada passo e as buscas "esta na lista?"
 * eram lineares (O(n^2 log n)). Quando o jogador estava inalcancavel a busca varria o mapa
 * inteiro a cada tick, o que travava o jogo. Agora usa PriorityQueue + tabelas de marcacao
 * (praticamente O(n log n)) e tem um limite de nos explorados.
 * O resultado (formato do caminho) e o mesmo de antes: lista invertida, sem o no inicial.
 */
public class Astar {

	public static double lastTime = System.currentTimeMillis();

	/** limite de seguranca de nos explorados por busca */
	private static final int MAX_NODES = 6000;

	private static long seqCounter = 0;

	private static final Comparator<Node> nodeSorter = new Comparator<Node>() {
		@Override
		public int compare(Node n0, Node n1) {
			if(n0.fCost < n1.fCost) return -1;
			if(n0.fCost > n1.fCost) return +1;
			// desempate estavel: quem entrou primeiro sai primeiro (igual ao sort antigo)
			return Long.compare(n0.seq, n1.seq);
		}
	};

	public static boolean clear() {
		if(System.currentTimeMillis() - lastTime >= 1000) {
			return true;
		}return false;
	}

	private static boolean isWall(Tile[] tiles, int w, int h, int x, int y) {
		if(x < 0 || y < 0 || x >= w || y >= h) return true;
		return tiles[x + (y*w)] instanceof WallTile;
	}

	public static List<Node> findPath(World world, Vector2i start, Vector2i end){
		lastTime = System.currentTimeMillis();
		final Tile[] tiles = World.tiles;
		final int w = World.WIDTH, h = World.HEIGHT;

		if(tiles == null || start.x < 0 || start.y < 0 || start.x >= w || start.y >= h
				|| end.x < 0 || end.y < 0 || end.x >= w || end.y >= h) {
			return null;
		}

		PriorityQueue<Node> openList = new PriorityQueue<Node>(64, nodeSorter);
		boolean[] seen = new boolean[w*h]; // aberto OU fechado

		Node current = new Node(start,null,0,getDistance(start,end));
		current.seq = seqCounter++;
		openList.add(current);
		seen[start.x + start.y*w] = true;
		int explored = 0;

		while(!openList.isEmpty()) {
			current = openList.poll();
			if(current.tile.x == end.x && current.tile.y == end.y) {
				List<Node> path = new ArrayList<Node>();
				while(current.parent != null){
					path.add(current);
					current = current.parent;
				}
				return path;
			}
			if(++explored > MAX_NODES) {
				return null;
			}

			int x = current.tile.x;
			int y = current.tile.y;

			for(int i = 0; i < 9; i++) {
				if(i == 4)continue;
				int xi = (i%3) - 1;
				int yi = (i/3) - 1;
				int nx = x+xi;
				int ny = y+yi;
				if(nx < 0 || ny < 0 || nx >= w || ny >= h) continue;
				Tile tile = tiles[nx + (ny*w)];
				if(tile == null)continue;
				if(tile instanceof WallTile)continue;
				if(seen[nx + ny*w]) continue;

				// nao corta quina de parede nas diagonais
				if(i == 0) {
					if(isWall(tiles,w,h,nx+1,ny) || isWall(tiles,w,h,nx,ny+1)) continue;
				}else if(i == 2) {
					if(isWall(tiles,w,h,nx-1,ny) || isWall(tiles,w,h,nx,ny+1)) continue;
				}else if(i == 6) {
					if(isWall(tiles,w,h,nx,ny-1) || isWall(tiles,w,h,nx+1,ny)) continue;
				}else if(i == 8) {
					if(isWall(tiles,w,h,nx,ny-1) || isWall(tiles,w,h,nx-1,ny)) continue;
				}

				Vector2i a = new Vector2i(nx,ny);
				double gCost = current.gCost + getDistance(current.tile, a);
				double hCost = getDistance(a,end);

				Node node = new Node(a, current, gCost, hCost);
				node.seq = seqCounter++;
				seen[nx + ny*w] = true;
				openList.add(node);
			}
		}
		return null;
	}

	private static double getDistance(Vector2i tile, Vector2i goal) {
		double dx = tile.x - goal.x;
		double dy = tile.y - goal.y;

		return Math.sqrt(dx*dx + dy*dy);
	}

}
