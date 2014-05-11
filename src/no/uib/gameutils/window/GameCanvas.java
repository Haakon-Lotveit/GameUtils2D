package no.uib.gameutils.window;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import no.uib.gameutils.entity.type.Paintable;

public class GameCanvas extends Canvas {
	public GameCanvas(){
		super();
		this.setBackground(new Color(40, 80, 80));
	}

	public void render(Paintable topLevel){
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null){
			this.createBufferStrategy(2);
			return;
		}

		Graphics g = bs.getDrawGraphics();
		
		topLevel.paint(g);
		
		g.dispose();
		
		bs.show();
		
	}
}
