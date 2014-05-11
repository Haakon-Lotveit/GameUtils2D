package no.uib.gameutils.entity.type;

/**
 * A tile is a basic unit of geography, and this interface is meant to act as a basic unit of painting this basig unit of geography.
 * In actually readable English, this is a basic interface that allows you to specify how some part of the level is supposed to look.
 * 
 * An implementing class can be animated or static as it itself wishes.
 * The only contract you must fulfill is that it paints the images starting at the tile specified, and then paints
 * downards to the right.
 * 
 * Specify if you paint outside your tile, so that users of your class will know.
 * 
 * @author Haakon Løtveit (email: haakon.lotveit@student.uib.no)
 *
 */
public interface Tile extends Paintable, Geographic{}
