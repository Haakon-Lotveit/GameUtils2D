package no.uib.gameutils.sprite;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class GenericSpriteLoader implements SpriteLoader {
	protected final BufferedImage SHEET;
	protected final int TILE_WIDTH;
	protected final int TILE_HEIGHT;
	
	
	/**
	 * Static factory method for GenericSpriteLoaders
	 * @param imagePath the path to the image file to load.
	 * @param tileSize the height and width (in pixels) of a tile. 64 is a 64x64 tile.
	 * @return a new (every time) GenericSpriteLoader
	 * @throws IOException if the image file could not be read.
	 * @throws IllegalArgumentException if tileSize is below 1, or imageFile is a null value.
	 */
	public static GenericSpriteLoader create(String imagePath, int tileSize) throws IOException{
		return create(new File(imagePath), tileSize);
	}
	
	/**
	 * Static factory method for GenericSpriteLoaders
	 * @param imageFile the image file to load.
	 * @param tileSize the height and width (in pixels) of a tile. 64 is a 64x64 tile.
	 * @return a new (every time) GenericSpriteLoader
	 * @throws IOException if the image file could not be read.
	 * @throws IllegalArgumentException if tileSize is below 1, or imageFile is a null value.
	 */
	public static GenericSpriteLoader create(File imageFile, int tileSize) throws IOException{
		GenericSpriteLoader ldr = null;
		try{
			 ldr = create(imageFile, tileSize, tileSize);
		}
		catch(IllegalArgumentException iae){
			/*
			 *  A hack to intercept IllegalArgumentExceptions resulting from invalid tileSizes,
			 * so we can give back better error-messages.
			 */
			if(iae.getMessage().contains("must be a positive number!")){
				throw new IllegalArgumentException(String.format("tileSize must be a positive number! (was %d)%n", tileSize), iae);
			}
		}
		
		return ldr;
		
	}
	
	/**
	 * Static factory method for GenericSpriteLoaders
	 * @param imagePath the path to the image file to load.
	 * @param tileWidth the width (in pixels) of a tile.
	 * @param tileHeight the height (in pixels) of a tile.
	 * @return a new (every time) GenericSpriteLoader
	 * @throws IOException if the imagefile could not be read.
	 * @throws IllegalArgumentException if either tileWidth or tileHeight are below 1, or imagepath is a null value.
	 */
	public static GenericSpriteLoader create(String imagePath, int tileWidth, int tileHeight) throws IOException{
		if(null == imagePath){
			throw new IllegalArgumentException("Imagepath cannot be a null-value");
		}
		return create(new File(imagePath), tileWidth, tileHeight);
	}
	
	/**
	 * Static factory method for GenericSpriteLoaders
	 * @param imageFile the image file to load.
	 * @param tileWidth the width (in pixels) of a tile.
	 * @param tileHeight the height (in pixels) of a tile.
	 * @return a new (every time) GenericSpriteLoader
	 * @throws IOException if the image file could not be read.
	 * @throws IllegalArgumentException if either tileWidth or tileHeight are below 1, or imageFile is a null value.
	 */
	public static GenericSpriteLoader create(File imageFile, int tileWidth, int tileHeight) throws IOException{
		if(null == imageFile){
			throw new IllegalArgumentException("imageFile cannot be a null value!");
		}
		if(tileWidth < 1){
			System.out.printf("tileWidth must be a positive number! (was %d)%n", tileWidth);
		}
		if(tileHeight < 1){
			System.out.printf("tileHeight must be a positive number! (was %d)%n", tileHeight);
		}
		BufferedImage sheet = ImageIO.read(imageFile);
		if(null == sheet){
			throw new IOException("Could not read imagefile. Is a correct ImageReader registered? check ImageIO's docs for details.");
		}
		
		return new GenericSpriteLoader(sheet, tileWidth, tileHeight);
	}
	private GenericSpriteLoader(BufferedImage spriteSheet, int tileWidth, int tileHeight){
		this.SHEET = spriteSheet;
		this.TILE_HEIGHT = tileHeight;
		this.TILE_WIDTH = tileWidth;
	}
	
	@Override
	public BufferedImage getImage(int column, int row) {
		return SHEET.getSubimage(column * TILE_WIDTH, row * TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT);
	}

	@Override
	public BufferedImage getImage(int xPos, int yPos, Dimension size) {
		return SHEET.getSubimage(xPos, yPos, size.width, size.height);
	}
	
	@Override
	public int numColumns() {
		return verticalPixels() / tileWidth();
	}

	@Override
	public int numRows() {
		return horizontalPixels() / tileHeight();
	}

	@Override
	public int tileWidth() {
		return TILE_WIDTH;
	}

	@Override
	public int tileHeight() {
		return TILE_HEIGHT;
	}

	@Override
	public int horizontalPixels() {
		return SHEET.getHeight();
	}

	@Override
	public int verticalPixels() {
		return SHEET.getWidth();
	}

	@Override
	public BufferedImage getSheet() {
		return SHEET;
	}
}
