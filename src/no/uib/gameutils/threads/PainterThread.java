package no.uib.gameutils.threads;

import no.uib.gameutils.entity.type.TopLevelPaintable;

public class PainterThread extends Thread {
	private final TopLevelPaintable CANVAS;
	private final int FPS;
	
	public PainterThread(TopLevelPaintable topLevel, int targetFramesPerSecond) {
		super();
		if(null == topLevel){
			throw new IllegalArgumentException("topLevel cannot be null");
		}
		if(targetFramesPerSecond < 1){
			throw new IllegalArgumentException(String.format("targetFramesPerSecond must be positive! (was %d)%n", targetFramesPerSecond));
		}
		this.FPS = targetFramesPerSecond;
		this.CANVAS = topLevel;
		this.start();
	}

	@Override
	public void run(){
		System.out.println("PaintingThread started");

		long timestamp = System.nanoTime();
		long paintFrequency = 1_000_000_000L / FPS; /* Locks your frames per second to a maximum. */

		while(!Thread.interrupted()){ /* If you send an interrupt, you'll kill the thread. */
			long timeSinceLastPaint = System.nanoTime() - timestamp;

			if(timeSinceLastPaint >= paintFrequency){
				CANVAS.paint();
				timestamp = System.nanoTime();
			}
		}

	}
}
