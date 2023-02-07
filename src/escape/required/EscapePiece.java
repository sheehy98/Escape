/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Copyright ©2016-2023 Gary F. Pollice
 *******************************************************************************/

package escape.required;

/**
 * Interface for the piece implementations. It also contains static
 * enumerations for the piece names, movement patterns, and attributes since 
 * these are properties of pieces. How you implement the actual piece implementations
 * is up to you and the design decisions you make.
 * 
 * MODIFIABLE: NO
 * MOVEABLE: YES
 * REQUIRED: YES
 * 
 * You may extend this interface for your internal use, but this is the public interface
 * that all clients will use.
 */
public interface EscapePiece
{
	public static enum PieceName {BIRD, DOG, FROG, HORSE, SNAIL};
	
	public static enum MovementPattern {DIAGONAL, LINEAR, OMNI, ORTHOGONAL};
	
	public static enum PieceAttributeID {FLY, DISTANCE, JUMP, UNBLOCK, VALUE};
	
	/**
	 * @return the name
	 */
	default PieceName getName()
	{
		throw new EscapeException("Not implemented");
	}
	
	/**
	 * @return the owning player
	 */
	default String getPlayer()
	{
		throw new EscapeException("Not implemented");
	}
}
