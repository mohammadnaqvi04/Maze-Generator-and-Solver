package Naqvi.cs146.project3;

import java.util.ArrayList;
import java.util.Stack;

public class DFS {

    private MazeNode[][] cellMatrix;
    private Stack<MazeNode> cellStack;
    private MazeNode currentCell;
    private int visitedCells;
    private int size;

    /*
    * 
    * @param theCellMatrix the actual maze to be modified by DFS algorithm
    * 
    */
    public DFS(MazeNode[][] theCellMatrix) {
        cellMatrix = theCellMatrix;
        //stack that keeps track of cell locations
        cellStack = new Stack<MazeNode>();
        size = cellMatrix.length;
        //Variable to start algorithm at entering cell
        currentCell = cellMatrix[0][0];
        visitedCells = 0;
    }

    //An iterative implementation of DFS based on CLRS implementation
    public MazeNode[][] executeDFS() {
        //add the current cell in the top left corner of the maze to the cell stack
        cellStack.push(currentCell);
        currentCell.setTraversalOrder(0);

        //while the exit has not been reached
        while(currentCell != cellMatrix[size-1][size-1]) {
            // pop off the first element in the stack, which is the top
            // left corner during the first iteration and one of the neighbors
            // of the previous current cell afterwards
            currentCell = cellStack.pop();
            //if the current cell has not been visited during DFS
            if(!currentCell.getVisitedDFS()) {
                //set its traversal order to be the digit in the ones place
                //as we reset to 0 when more than 9 nodes need to have their
                //traversal order set
                currentCell.setTraversalOrder((visitedCells++) % 10);
                //update the current cell to indicate it has been visited
                currentCell.setVisitedDFS(true);
                ArrayList<MazeNode> currentCellsNeighbors = getNeighbors(currentCell);

                //iterating through the neighbors of a cell
                for(MazeNode neighbor : currentCellsNeighbors) {
                    if(!neighbor.getVisitedDFS()) {
                        //add the neighbor to the stack
                        cellStack.push(neighbor);
                        //set the current cell to be the parent cell of the neighbor
                        neighbor.setParent(currentCell);
                    }
                }
            }
        }
        return cellMatrix;
    }

    //helper method for executeDFS that returns an arraylist of a Cell's neighbors
    public ArrayList<MazeNode> getNeighbors(MazeNode cell) {
        ArrayList<MazeNode> neighbors = new ArrayList<MazeNode>();
        int row = cell.getRow();
        int col = cell.getCol();

        // Northern cell
        if(row - 1 >= 0) {
            //If the northern cell has not been visited in DFS and it does not have a northern wall
            //add it to the neighbors of the current cell
            if(!cellMatrix[row - 1][col].getVisitedDFS() && !cell.getCheckNorthWall()) {
                neighbors.add(cellMatrix[row-1][col]);
            }
        }

        // Southern cell
        if(row + 1 < size) {
            //If the southern cell has not been visited in DFS and it does not have a southern wall
            //add it to the neighbors of the current cell
            if(!cellMatrix[row + 1][col].getVisitedDFS() && !cell.getCheckSouthWall()) {
                neighbors.add(cellMatrix[row + 1][col]);
            }
        }

        // Western cell
        if(col - 1 >= 0) {
            //If the western cell has not been visited in DFS and it does not have a western wall
            //add it to the neighbors of the current cell
            if(!cellMatrix[row][col - 1].getVisitedDFS() && !cell.getCheckWestWall()) {
                neighbors.add(cellMatrix[row][col - 1]);
            }
        }

        // Eastern cell
        if(col + 1 < size) {
            //If the eastern cell has not been visited in DFS and it does not have an eastern wall
            //add it to the neighbors of the current cell
            if(!cellMatrix[row][col + 1].getVisitedDFS() && !cell.getCheckEastWall()) {
                neighbors.add(cellMatrix[row][col + 1]);
            }
        }
        return neighbors;
    }

}
