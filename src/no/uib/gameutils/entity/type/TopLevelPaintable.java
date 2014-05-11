package no.uib.gameutils.entity.type;

import java.awt.Graphics;

/**
 * A TopLevelPaintable is something that can be told to paint,
 * and can produce its own means with which to do so.
 * 
 * While a normal {@link Paintable} needs to get a {@link Graphics} to paint with,
 * TopLevelPaintable can produce its own.
 * The idea is that you build a tree with a TopLevelPaintable on top, which produces a {@link Graphics}
 * that can be handed down to {@link Paintable} children, so they can paint themselves. 
 * @author Haakon Løtveit (email: haakon.lotveit@student.uib.no)
 *
 */
public interface TopLevelPaintable {
	
	/**
	 * Calling this method renders an entire frame.
	 * Implementing classes produce some form of {@link Graphics} object and sends it to its children so they paint themselves.
	 */
	public void paint();
}
