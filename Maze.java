package Naqvi.cs146.project3;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class Maze {

    private int[][] adjMatrix;
    private MazeNode[][] matrix;
    private int size;
    Stack<MazeNode> stack;
    private Random random;

    public Maze(int size) {
        this.size = size;
        adjMatrix = new int[size][size]; //initializes a 2D array as an adjacency matrix to keep track of Node relationships
        matrix = new MazeNode[size][size]; //Initializes a 2D array containing MazeNodes in each position
        random = new Random(4);

        //Populates every index of the Maze with a MazeNode
        for(int row = 0; row < size; row++) {
            for(int col = 0; col < size; col++) {
                matrix[row][col] = new MazeNode(row, col);
            }
        }
    }


    //generates a Random maze that either search algorithm will function on
    public MazeNode[][] generateMaze() {

        //stack to provide a structure to store potential NextNodes
        stack = new Stack<MazeNode>();
        //defines the total number of cells in the maze
        int totalCells = size*size;
        //keeps track of current node during creation
        MazeNode currentCell = matrix[0][0];
        //counterVariable for number of visited nodes
        int visitedCells = 1;

        //while loop to ensure all Nodes have been visited
        while(visitedCells < totalCells) {

            //keeps track of all neighbors of the current MazeNode
            ArrayList<MazeNode> neighbors = getNeighbors(currentCell);

            //if a cell has at least one neighbor
            if(neighbors.isEmpty() == false) {

                //generates a random number based on the neighbors of the current cell
                int index = random.nextInt(neighbors.size());
                //selects a random neighbor of that cell to be the next cell
                MazeNode nextCell = neighbors.get(index);

                //establishing connection between two neighboring cells in adjacency matrix
                adjMatrix[currentCell.getRow()][currentCell.getCol()] = 1;
                adjMatrix[nextCell.getRow()][nextCell.getCol()] = 1;


                // if the nextCell is North of currentCell
                if(currentCell.getRow() - 1 == nextCell.getRow() && currentCell.getCol() == nextCell.getCol()) {
                    currentCell.setCheckNorthWall(false);
                    nextCell.setCheckSouthWall(false);
                }

                // if the nextCell is South of currentCell
                else if(currentCell.getRow() + 1 == nextCell.getRow() && currentCell.getCol() == nextCell.getCol()) {
                    //eliminate the wall between the two Nodes
                    currentCell.setCheckSouthWall(false);
                    //eliminate the wall between the two Nodes
                    nextCell.setCheckNorthWall(false);
                }

                // if the nextCell is East of currentCell
                else if(currentCell.getRow() == nextCell.getRow() && currentCell.getCol() + 1 == nextCell.getCol()) {
                    currentCell.setCheckEastWall(false);
                    nextCell.setCheckWestWall(false);
                }

                // if the nextCell is West of currentCell
                else if(currentCell.getRow() == nextCell.getRow() && currentCell.getCol() - 1 == nextCell.getCol()) {
                    currentCell.setCheckWestWall(false);
                    nextCell.setCheckEastWall(false);
                }

                //set the current and next nodes as having been visited
                currentCell.setVisited(true);
                nextCell.setVisited(true);

                //update the status of the current and next nodes to not have all their walls present
                currentCell.setIsEnclosed(false);
                nextCell.setIsEnclosed(false);

                //If a cell has at least one neighbor, push it into the stack
                stack.push(currentCell);
                //the currentCell is a random neighbor of the previous current cell
                currentCell = nextCell;
                visitedCells++;
            }
            else {
                //If a cell does not have at least one neighbor, the current cell is the most
                //recently added Node to the stack
                currentCell = stack.pop();
            }
        }

        //setting entrance to be top left of maze
        matrix[0][0].setCheckNorthWall(false);
        //setting exit to be bottom right of maze
        matrix[size-1][size-1].setCheckSouthWall(false);
        return matrix;

    }

    //returns an arraylist containing the neighbors of a cell
    public ArrayList<MazeNode> getNeighbors(MazeNode cell) {

        ArrayList<MazeNode> neighbors = new ArrayList<MazeNode>();
        int row = cell.getRow();
        int col = cell.getCol();

        //checking to see if Node North to MazeNode exists, whether it's boundaries are present, and whether it has been visited before
        if(row - 1 >= 0 && matrix[row - 1][col].getIsEnclosed() && !matrix[row - 1][col].getVisited()) {
            neighbors.add(matrix[row - 1][col]);
        }
        //checking to see if Node South to MazeNode exists, whether it's boundaries are present, and whether it has been visited before
        if(row + 1 < size && matrix[row + 1][col].getIsEnclosed() && !matrix[row + 1][col].getVisited()) {
            neighbors.add(matrix[row + 1][col]);
        }

        //checking to see if Node West to MazeNode exists, whether it's boundaries are present, and whether it has been visited before
        if(col - 1 >= 0 && matrix[row][col - 1].getIsEnclosed() && !matrix[row][col - 1].getVisited()) {
            neighbors.add(matrix[row][col - 1]);
        }
        //checking to see if Node East to MazeNode exists, whether it's boundaries are present, and whether it has been visited before
        if(col + 1 < size && matrix[row][col + 1].getIsEnclosed() && !matrix[row][col + 1].getVisited()) {
            neighbors.add(matrix[row][col + 1]);
        }

        return neighbors;
    }


    public MazeNode[][] returnMatrix() {
        return matrix;
    }


}
