package no.uib.gameutils.window;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import no.uib.gameutils.entity.type.Paintable;
import no.uib.gameutils.entity.type.TopLevelPaintable;

public class SimpleWindow implements TopLevelPaintable {
	private JFrame window;
	private GameCanvas canvas;
	private Dimension size;
	private Paintable scene;
	
	public SimpleWindow(String title, Dimension size){
		this.window = new JFrame(title);
		this.canvas = new GameCanvas();
		this.size = size;
		init();
	}
	
	private void init(){
		//Set up canvas first
		canvas.setSize(size);
		
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		window.add(canvas);
		window.pack();
		window.revalidate();
		window.repaint();
		
		window.setVisible(true);
		
		System.out.println(canvas.isDisplayable());
	}
	
	/*
	 * TODO: Add methods to add listeners and such, after I know which ones are needed and useful.
	 */
	
	public SimpleWindow setScene(Paintable scene){
		this.scene = scene;
		return this;
	}
	
	public Paintable getScene(){
		return scene;
	}
	public JFrame getJFrame(){
		return window;
	}
	
	public Canvas getCanvas(){
		return canvas;
	}
	
	/**
	 * This paint method allows you to paint the scene.
	 * The scene itself must know what to paint and in what order.
	 */
	public void paint(){
		canvas.render(scene);
	}

}
