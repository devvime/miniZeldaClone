package zeldaminiclone;

import java.awt.Graphics;
import java.awt.Rectangle;

public class Player extends Rectangle {
	
	private static final long serialVersionUID = 1L;
	
	public int spd = 4;
	public boolean right, up, down, left;
	
	public int currentAnimation = 0;
	public int currentFrames = 0, targetFrames = 15;
	
	public Player(int x, int y) {
		super(x, y, 32, 32);
	}
	
	public void tick() {
		boolean moved = false;
		
		if (right && World.isFree(x + spd, y)) {
			x+=spd;
			moved = true;
		} else if (left && World.isFree(x - spd, y)) {
			x-=spd;
			moved = true;
		}
		
		if (up && World.isFree(x, y - spd)) {
			y-=spd;
			moved = true;
		} else if (down && World.isFree(x, y + spd)) {
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
	}
	
	public void render(Graphics g) {
//		g.setColor(Color.blue);
//		g.fillRect(x, y, width, height);
		g.drawImage(Spritesheet.player_front[currentAnimation], x, y, 32, 32, null);
	}

}
