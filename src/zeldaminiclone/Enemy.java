package zeldaminiclone;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

public class Enemy extends Rectangle {
	
	private static final long serialVersionUID = 1L;
	
	public int spd = 4;
	public int right = 1, up = 0, down = 0, left = 0;
	public boolean shoot;
	
	public int currentAnimation = 0;
	public int currentFrames = 0, targetFrames = 15;
	
	public static List<Bullet> bullets = new ArrayList<Bullet>();
	
	public int dir = 1;
	
	public Enemy(int x, int y) {
		super(x, y, 32, 32);
	}
	
	public void tick() {
		boolean moved = false;
		
		if (right == 1 && World.isFree(x + spd, y)) {
			x+=spd;
			moved = true;
			dir = 1;
		} else if (left == 1 && World.isFree(x - spd, y)) {
			x-=spd;
			moved = true;
			dir = -1;
		}
		
		if (up == 1 && World.isFree(x, y - spd)) {
			y-=spd;
			moved = true;
		} else if (down == 1 && World.isFree(x, y + spd)) {
			y+=spd;
			moved = true;
		}
		
		if (moved) {
			currentFrames++;
			if (currentFrames == targetFrames) {
				currentFrames = 0;
				currentAnimation++;
				if (currentAnimation == Spritesheet.player_front.length) {
					currentAnimation = 0;
				}
			}
		}
		
		if (shoot) {
			shoot = false;
			bullets.add(new Bullet(x, y, dir));
		}
		
		for (int i = 0; i < bullets.size(); i++) {
			bullets.get(i).tick();
		}
	}
	
	public void render(Graphics g) {
//		g.setColor(Color.blue);
//		g.fillRect(x, y, width, height);
		g.drawImage(Spritesheet.enemy_front[currentAnimation], x, y, 32, 32, null);
		
		for (int i = 0; i < bullets.size(); i++) {
			bullets.get(i).render(g);
		}
	}

}
