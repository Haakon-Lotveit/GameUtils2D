package no.uib.gameutils.entity.type;

import no.uib.gameutils.position.Position2D;

/**
 * A Geographic entity is an entity that has a position in a two-dimensional space.
 * It does not have to have any graphical representation in this two-dimensional space,
 * merely some abstract sense of belonging on some place.
 * A map-tile has a geographical location AND a graphical representation,
 * but a trigger does not have a graphical representation.
 * 
 * @author Haakon Løtveit (email: haakon.lotveit@student.uib.no)
 *
 */
public interface Geographic {
	
	/**
	 * The position of an entity is not given in pixels, but in the abstract form of a "tile".
	 * If you consider the entirety of a finite level a table, it would be the position in the table.
	 * This would be which column it belongs to.
	 * @return the horizontal component of this entity's position. Can in some cases be negative.
	 */
	public int xPos();
	/**
	 * The position of an entity is not given in pixels, but in the abstract form of a "tile".
	 * If you consider the entirety of a finite level a table, it would be the position in the table.
	 * This would be which row it belongs to.
	 * @return the vertical component of this entity's position. Can in some cases be negative.
	 */
	public int yPos();
	
	/**
	 * This lets you get the position as one simple package, which is handy if you want to deal with
	 * both coordinates in an easy and friendly manner.
	 * The coordinates are not given in pixels, but in the abstract term of which tile they're on.
	 * You can view an entire finite level as a table, and think of the x as the horizontal component,
	 * and y as the vertical one.
	 * Note that due to the possibility of "infinite" (although with integer overflows it would be looping) levels,
	 * these numbers may very well be negative. 
	 * @return the coordinates of the Geographic entity.
	 */
	public Position2D position();
	
	/**
	 * Sets the horizontal position to a tile on the map.
	 * Negative numbers may or may not be allowed, this depends on the implementing classes view of the world,
	 * and you must consult their philosophies on the subject.
	 * 
	 * If no such documentation exist, slap them with trouts.
	 * 
	 * @param x the horizontal position, given in tiles, not pixels.
	 */
	public void setX(int x);
	
	/**
	 * Sets the vertical position to a tile on the map.
	 * Negative numbers may or may not be allowed, this depends on the implementing classes view of the world,
	 * and you must consult their philosophies on the subject.
	 * 
	 * If no such documentation exist, slap them with trouts.
	 * 
	 * @param y the vertical position, given in tiles, not pixels.
	 */	
	public void setY(int y);
	
	/**
	 * Set the position of this entity with a Position2D.
	 * Negative numbers may be allowed in any, all or none of the dimensions.
	 * If this is not documented by the implementing class, tickle the author mercilessly until they change their wicked ways.
	 * 
	 * @param position the positional object that I'm convinced you've defensively vetted before passing along to an implementing class.
	 */
	public void setPosition(Position2D position);
	
}
