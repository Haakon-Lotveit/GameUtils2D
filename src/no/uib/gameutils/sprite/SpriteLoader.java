package no.uib.gameutils.sprite;

import java.awt.Dimension;
import java.awt.image.BufferedImage;

/**
 * A spriteloader is responsible for loading spritesheets into memory.
 * It also abstracts away the tedium of manually specifying which subset of the image you want to draw
 * by implementing a grid selection scheme. The nature of this grid is left to implementing classes.
 * 
 * @author Haakon Løtveit (email: haakon.lotveit@student.uib.no)
 *
 */
public interface SpriteLoader {
	/**
	 * Gives you a tile from the spritesheet that you can draw to the screen.
	 * The methods may, but are not required to check that the input is valid.
	 * Therefore, the programmer using this method is required to make sure that the input is valid before calling this method.
	 * {@link SpriteLoader#tileHeight() tileHeight()} and {@link SpriteLoader#tileWidth() tileLength()} gives you the number of rows and columns respectively.
	 * The grid is always zero-indexed, so the maximum row is {@link SpriteLoader#tileHeight() tileHeight()} - 1, for example. 
	 *
	 * @param column the column you want to select the image from. Can never be negative or out of bounds.
	 * @param row the row you want to select the image from. Can never be negative or out of bounds.
	 * @return a {@link BufferedImage} representing the tile specified.
	 */
	public BufferedImage getImage(int column, int row);
	
	/**
	 * Gives you a tile from the spritesheet that you can draw to the screen, specified by a pixel-position and a dimension.
	 * This method may, but is not required to validate input. Therefore you the programmer is required to check that the input will produce a valid image.
	 * {@link SpriteLoader#horizontalPixels() horizontalPixels()} and {@link SpriteLoader#verticalPixels() verticalPixels()} will tell you the dimension of the sheet.
	 * Remember that it is zero indexed, so {@link SpriteLoader#horizontalPixels() horizontalPixels()} - 1 is the rightmost pixel you can legally select.
	 * 
	 * @param xPos the x-th pixel from the left, starting at zero, specifying the left boundary of the tile. 
	 * @param yPos the y-th pixel from the top, starting at zero, specifying the top boundary of the tile.
	 * @param size the size of the tile in pixels. this is how far down and to the right we're going to get the image from.
	 * @return a {@link BufferedImage} representing the tile specified
	 */
	public BufferedImage getImage(int xPos, int yPos, Dimension size);
	
	/**
	 * The number of columns in this spritesheet is defined by the horizontal size of a tile (or sub-image if you will) divided by the horizontal number of pixels,
	 * rounded down. This number might be zero.
	 * @return the number of columns in this spritesheet.
	 */
	public int numColumns();
	
	/**
	 * The number of rows in this spritesheet is defined by the vertical size of a tile (or sub-image if you will) divided by the vertical number of pixels,
	 * rounded down. This number might be zero, but will never be negative.
	 * @return the number of rows in this spritesheet.
	 */
	public int numRows();

	/**
	 * This is always a positive number.
	 * @return the length of a tile in pixels.
	 */
	public int tileWidth();
	
	/**
	 * This is always a positive number.
	 * @return the height of a tile in pixels.
	 */
	public int tileHeight();
	
	/**
	 * This is always a positive number
	 * @return The total number of horizontal pixels in the sheet
	 */
	public int horizontalPixels();
	
	/**
	 * This is always a positive number
	 * @return The total number of vertical pixels in the sheet.
	 */
	public int verticalPixels();
	
	/**
	 * This is the same {@link BufferedImage} that is used by the spritesheet.
	 * Changing this will affect the spritesheet.
	 * If you want to have a copy, you must copy it yourself.
	 * @return a {@link BufferedImage} representing the underlying spritesheet.
	 */
	public BufferedImage getSheet();
}
