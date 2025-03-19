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
import java.util.function.Supplier;

/**
 * A <code>BoxBug</code> Bug that can jump over other actors <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class Jumper extends Bug
{
    
    private int jump;
    private int maxJump;

    /**
     * Constructs a jumper bug that can jump over other actors
     * @param jumps maximum jumps till random turn
     */
    public Jumper(int jumps)
    {
        maxJump = jumps;
    }

    /**
     * Constructs a jumper bug that can jump over other actors
     */
    public Jumper()
    {
        maxJump = 100;
    }

    /**
     * Moves to the next location .
     */
    public void act()
    {
        if(jump>=maxJump){
            setDirection(45*(int)(Math.random()*8));
            jump=0;
        }
        if (hasClearJump())
        {
            jump();
            jump++;
        }
        else if( canJump())
        {
            turn();
            jump=0;
        }
        else if( canMove())
        {
            move();
        }
        else {
            turn();
            jump=0;
        }
    }
    /**
     * checks if bug can jump forward 
     * @return true if can jump false if can't
     */
     public boolean hasClearJump(){
        Grid<Actor> grid = getGrid();
        Location newJump = getLocation().getAdjacentLocation(getDirection());
        newJump = newJump.getAdjacentLocation(getDirection());
        if(grid.isValid(newJump)&&((grid.get(newJump)==null|| (grid.get(newJump) instanceof Flower)&&!(grid.get(newJump) instanceof Blossom)))) return true;
        return false;
     }
     /**
     * checks if bug can jump in any direction 
     * @return true if can jump false if can't
     */
    public boolean canJump(){
        int row =getLocation().getRow();
        int col =getLocation().getCol();
        Grid<Actor> grid = getGrid();
        Location check = new Location(row,col+jump);
        if(grid.isValid(check)&&(grid.get(check)==null|| (grid.get(check) instanceof Flower)&&!(grid.get(check) instanceof Blossom))) return true;
         check = new Location(row,col-jump);
        if(grid.isValid(check)&&(grid.get(check)==null|| (grid.get(check) instanceof Flower)&&!(grid.get(check) instanceof Blossom))) return true;
         check = new Location(row+jump,col);
        if(grid.isValid(check)&&(grid.get(check)==null|| (grid.get(check) instanceof Flower)&&!(grid.get(check) instanceof Blossom))) return true;
         check = new Location(row-jump,col);
        if(grid.isValid(check)&&(grid.get(check)==null|| (grid.get(check) instanceof Flower)&&!(grid.get(check) instanceof Blossom))) return true;
         check = new Location(row+jump,col+jump);
        if(grid.isValid(check)&&(grid.get(check)==null|| (grid.get(check) instanceof Flower)&&!(grid.get(check) instanceof Blossom))) return true;
         check = new Location(row-jump,col+jump);
        if(grid.isValid(check)&&(grid.get(check)==null|| (grid.get(check) instanceof Flower)&&!(grid.get(check) instanceof Blossom))) return true;
         check = new Location(row+jump,col-jump);
        if(grid.isValid(check)&&(grid.get(check)==null|| (grid.get(check) instanceof Flower)&&!(grid.get(check) instanceof Blossom))) return true;
          check = new Location(row-jump,col-jump);
        if(grid.isValid(check)&&(grid.get(check)==null|| (grid.get(check) instanceof Flower)&&!(grid.get(check) instanceof Blossom))) return true;
        return false;
    }
    /**
     * Moves the bug forward, putting a blossom into the location it previously
     * occupied.
     */
    public void move()
    {
        Grid<Actor> gr = getGrid();
        if (gr == null)
            return;
        Location loc = getLocation();
        Location next = loc.getAdjacentLocation(getDirection());
        if (gr.isValid(next))
            moveTo(next);
        else
            removeSelfFromGrid();
        Blossom blossom = new Blossom((int)(Math.random()*20));
        blossom.putSelfInGrid(gr, loc);
    }
    /**
     * Moves the bug forward 2 spaces
     */
    public void jump()
    {
        Grid<Actor> gr = getGrid();
        if (gr == null)
            return;
        Location loc = getLocation();
        Location next = loc.getAdjacentLocation(getDirection());
         next = next.getAdjacentLocation(getDirection());
        if (gr.isValid(next))
            moveTo(next);
        else
            removeSelfFromGrid();
        Blossom blossom = new Blossom((int)(Math.random()*20));
        blossom.putSelfInGrid(gr, loc);
    }
}
