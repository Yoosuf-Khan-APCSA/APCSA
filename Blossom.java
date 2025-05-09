/* 
 * AP(r) Computer Science GridWorld Case Study:
 * Copyright(c) 2005-2006 Cay S. Horstmann (http://horstmann.com)
 *
 * This code is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * @author Cay Horstmann
 * @author Chris Nevison
 * @author Barbara Cloud Wells
 * @author Yoosuf Khan
 * @since March 13, 2025
 */

import info.gridworld.grid.Grid;
import info.gridworld.grid.BoundedGrid;
import info.gridworld.grid.Location;
import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Actor;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Rock;
import info.gridworld.actor.Flower;
import java.awt.Color;

/**
 * A <code>BoxBug</code> traces out a circle  of a given size. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class Blossom extends Flower
{
    
    private int life;//max steps spent alive
    private int step;//steps spent alive

    /**
     * Constructs a circle bug that traces a circle of a given side length
     * @param length the side length
     */
    public Blossom(int length)
    {
        setColor(Color.green);
        life = length;
    }

    /**
     * Constructs a circle bug that traces a circle of a given side length
     */
    public Blossom()
    {
        setColor(Color.green);
        life = 10;
    }

    
     /**
     * Causes the color of this flower to darken.
     */
    public void act()
    {
        super.act();
        step++;
        if(step==life) removeSelfFromGrid();
        
    }
}
