package no.uib.gameutils.entity;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import no.uib.gameutils.entity.type.Tile;
import no.uib.gameutils.position.Position2D;
import no.uib.gameutils.sprite.SpriteLoader;

public class StaticImage implements Tile {
	private final BufferedImage IMAGE;
	private int xPos, yPos;
	private int tileWidth, tileHeight;



	public StaticImage(SpriteLoader ldr, int imgCol, int imgRow, int xPos, int yPos){
		this.IMAGE = ldr.getImage(imgCol, imgRow);
		this.xPos = xPos;
		this.yPos = yPos;
		this.tileHeight = ldr.tileHeight();
		this.tileWidth = ldr.tileWidth();
	}

	@Override
	public void paint(Graphics gfx) {
		gfx.drawImage(IMAGE, xPos * tileWidth, yPos * tileWidth, null);
	}

	@Override
	public int xPos() {
		return xPos;
	}

	@Override
	public int yPos() {
		return yPos();
	}

	@Override
	public Position2D position() {
		return new Position2D(xPos, yPos);
	}

	@Override
	public void setX(int x) {
		this.xPos = x;
	}

	@Override
	public void setY(int y) {
		this.yPos = y;
	}

	@Override
	public void setPosition(Position2D position) {
		this.xPos = position.getX();
		this.yPos = position.getY();
	}

	public static StaticImageBuilder build(){
		return new StaticImageBuilder();
	}
	
	public static class StaticImageBuilder {
		private int xPos, yPos, imgCol, imgRow;
		private SpriteLoader ldr;

		private void checkLoaderRow(){
			if(imgRow < 0){
				throw new IllegalArgumentException(String.format("Illegal row specified, must be a non-negative number (yours was %d)", imgRow));
			}
			if(null != ldr && !(ldr.numColumns() > imgRow)){
				throw new IllegalArgumentException(String.format("Illegal row specified, largest legal row is %d (yours was %d)",ldr.numRows() - 1, imgRow)); 
			}
		}
		
		private void checkLoaderCol(){
			if(imgCol < 0){
				throw new IllegalArgumentException(String.format("Illegal column specified, must be a non-negative number (yours was %d)", imgCol));
			}
			if(null != ldr && !(ldr.numColumns() > imgCol)){
				throw new IllegalArgumentException(String.format("Illegal column specified, largest legal column is %d (yours was %d)",ldr.numColumns() - 1, imgCol)); 
			}			
		}
		
		public StaticImageBuilder(){
			xPos = yPos = 0;
			ldr = null;
		}

		/**
		 * Sets which column in the {@link SpriteLoader} that's to be used.
		 * @param imgCol the column, no negative values, must be less than what's returned by a call to {@link SpriteLoader#numColumns()}
		 * @return this same StaticImageBuilder so you can chain calls
		 * @throws IllegalArgumentException if the column specified is outside the bounds of the loader
		 * If there is no {@link SpriteLoader}, then the imgCol may be any non-negative value.
		 * If there is a {@link SpriteLoader}, then the imgCol is restrained to be less than the int returned by a call to {@link SpriteLoader#numColumns()}
		 * Should a new {@link SpriteLoader} be set, this check will be ran again.
		 */
		public StaticImageBuilder imgCol(int imgCol){
			this.imgCol = imgCol;
			checkLoaderCol();
			return this;
		}

		/**
		 * Sets which column in the {@link SpriteLoader} that's to be used.
		 * @param imgRow the column, no negative values, must be less than what's returned by a call to {@link SpriteLoader#numRows()}
		 * @return this same {@link StaticImageBuilder} so you can chain calls
		 * @throws IllegalArgumentException if the row specified is outside the bounds of the loader. 
		 * If there is no {@link SpriteLoader}, then the imgRow may be any non-negative value.
		 * If there is a {@link SpriteLoader}, then the imgRow is restrained to be less than the int returned by a call to {@link SpriteLoader#numRows()()}
		 * Should a new {@link SpriteLoader} be set, this check will be ran again.
		 */
		public StaticImageBuilder imgRow(int imgRow){
			this.imgRow = imgRow;
			this.checkLoaderRow();
			return this;
		}
		
		/**
		 * Sets the x-position of the {@link StaticImage}.
		 * Note that you set the tile, not the pixel-position.
		 * @param xPos the position, every int is a legal value to this builder.
		 * @return this {@link StaticImageBuilder} so you can chain calls.
		 */
		public StaticImageBuilder xPos(int xPos){
			this.xPos = xPos;
			return this;
		}
		/**
		 * Sets the y-position of the {@link StaticImage}.
		 * Note that you set the tile, not the pixel-position.
		 * @param yPos the position, every int is a legal value to this builder.
		 * @return this {@link StaticImageBuilder} so you can chain calls.
		 */
		public StaticImageBuilder yPos(int yPos){
			this.yPos = yPos;
			return this;
		}
		
		/**
		 * Sets the {@link SpriteLoader} to be used.
		 * @param loader the {@link SpriteLoader}, may not be null
		 * @return this {@link StaticImageBuilder} so you can chain calls.
		 */
		public StaticImageBuilder loader(SpriteLoader loader){
			if(null == loader){
				throw new IllegalArgumentException("Argument \"loader\" may not be null.");
			}

			this.ldr = loader;
			
			checkLoaderCol();
			checkLoaderRow();
			
			return this;
		}

		/**
		 * Sets the position this builder's going to use, by reading the values from the {@link Position2D} object.
		 * @param pos the {@link Position2D} to be used, may not be null. Any x and y values are valid.
		 * @return this {@link StaticImageBuilder} so you can chain calls.
		 */
		public StaticImageBuilder geoPosition(Position2D pos){
			if(null == pos){
				throw new IllegalArgumentException("Argument \"pos\" may not be null");
			}
			this.xPos = pos.getX();
			this.yPos = pos.getY();
			return this;
		}
		
		/**
		 * Sets which sub-image form the {@link SpriteLoader} that's going to be used.
		 * Neither dimension can be negative, and both must respect the boundaries of the {@link SpriteLoader}.
		 * The x-dimension maps to column and the y-dimension maps to row.
		 * @param pos the {@link Position2D} object to use
		 * @return this {@link StaticImageBuilder} so you can chain calls.
		 */
		public StaticImageBuilder ldrPosition(Position2D pos){
			this.imgCol = pos.getX();
			this.imgRow = pos.getY();
			checkLoaderCol();
			checkLoaderRow();
			return this;
		}

		/**
		 * Will create a new {@link StaticImage} from the arguments given.
		 * Any unset ints (xPos, yPos, imgCol or imgRow) will be 0, and the {@link StaticImage} will be created with these values.
		 * If no {@link SpriteLoader} is set, an {@link IllegalStateException} will be thrown.
		 * @return a new instance of {@link StaticImage}
		 * @throws IllegalStateException if no {@link SpriteLoader} is set.
		 */
		public StaticImage create(){
			if(null == ldr){
				throw new IllegalStateException("No SpriteLoader set");
			}

			return new StaticImage(ldr, imgCol, imgRow, xPos, yPos);
		}
	}

}
