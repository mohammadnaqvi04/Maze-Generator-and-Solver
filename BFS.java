package Naqvi.cs146.project3;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BFS {

    private MazeNode[][] matrix;
    private Queue<MazeNode> cellQueue;
    private int visitedCells;
    private int size;

    public BFS(MazeNode[][] matrix) {
        this.matrix = matrix;
        //LinkedList implementation of a cellQueue
        cellQueue = new LinkedList<MazeNode>();
        visitedCells = 0;
        size = matrix.length;
    }

    public MazeNode[][] executeBFS() {
        MazeNode currentCell = matrix[0][0];
        cellQueue.add(currentCell);
        currentCell.setTraversalOrder(0);

        //while you have not reached the maze exit, keep iterating
        while(currentCell != matrix[size-1][size-1]) {
            // remove the head of the queue, which is the top
            // left corner during the first iteration and one of the neighbors
            // of the previous current cell afterwards
            currentCell = cellQueue.poll();
            //if the current cell has not been visited during BFS
            if(!currentCell.getVisitedBFS()) {
                //set its traversal order to be the digit in the ones place
                //as we reset to 0 when more than 9 nodes need to have their
                //traversal order set
                currentCell.setTraversalOrder((visitedCells++) % 10);
                //update the current cell to indicate it has been visited
                currentCell.setVisitedBFS(true);
                //retrieve the neighbors of the current cell
                ArrayList<MazeNode> currentCellsNeighbors = getNeighbors(currentCell);
                //iterating through the neighbors of a cell
                for(MazeNode neighbor : currentCellsNeighbors) {
                    if(!neighbor.getVisitedBFS()) {
                        //add the neighbor to the cellQueue
                        cellQueue.add(neighbor);
                        //set the current cell to be the parent cell of the neighbor
                        neighbor.setParent(currentCell);
                    }
                }
            }
        }
        return matrix;
    }

    //helper method for executeDFS that returns an arraylist of a Cell's neighbors
    public ArrayList<MazeNode> getNeighbors(MazeNode cell){
        ArrayList<MazeNode> neighbors = new ArrayList<MazeNode>();
        int row = cell.getRow();
        int col = cell.getCol();

        // Northern cell
        if(row - 1 >= 0) {
            //If the northern cell has not been visited in DFS and it does not have a northern wall
            //add it to the neighbors of the current cell
            if(!matrix[row - 1][col].getVisitedBFS() && !cell.getCheckNorthWall()) {
                neighbors.add(matrix[row-1][col]);
            }
        }

        // Southern cell
        if(row + 1 < size) {
            //If the southern cell has not been visited in DFS and it does not have a southern wall
            //add it to the neighbors of the current cell
            if(!matrix[row + 1][col].getVisitedBFS() && !cell.getCheckSouthWall()) {
                neighbors.add(matrix[row + 1][col]);
            }
        }

        // Western cell
        if(col - 1 >= 0) {
            //If the western cell has not been visited in DFS and it does not have a western wall
            //add it to the neighbors of the current cell
            if(!matrix[row][col - 1].getVisitedBFS() && !cell.getCheckWestWall()) {
                neighbors.add(matrix[row][col - 1]);
            }
        }

        // Eastern cell
        if(col + 1 < size) {
            //If the eastern cell has not been visited in DFS and it does not have an eastern wall
            //add it to the neighbors of the current cell
            if(!matrix[row][col + 1].getVisitedBFS() && !cell.getCheckEastWall()) {
                neighbors.add(matrix[row][col + 1]);
            }
        }
        return neighbors;
    }

}
