package Naqvi.cs146.project3;

import Naqvi.cs146.project3.MazeNode;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Stack;

public class PrintMaze {

    private MazeNode[][] BFSMatrix;
    private MazeNode[][] DFSMatrix;
    private int size;
    private int BFSvisitedCells;
    private int DFSvisitedCells;


    public PrintMaze(MazeNode[][] BFSMatrix, MazeNode[][] DFSMatrix) {
        this.BFSMatrix = BFSMatrix;
        this.DFSMatrix = DFSMatrix;

        //Could be either Maze since both are same size
        size = BFSMatrix.length;

        BFSvisitedCells = 0;
        DFSvisitedCells = 0;
    }

    public void writeMazesToFile(String fileName) {

        try {
            File mazeFile = new File(fileName);
            FileWriter fw = new FileWriter(mazeFile);
            BufferedWriter bw = new BufferedWriter(fw);


/****************************Prints Maze without before traversal by either algorithm****************************/
            bw.write("MAZE:");
            bw.newLine();
            bw.newLine();

            //iterates through matrix row by row printing each cell's surroundings based
            //on its properties
            for(int r = 0; r < size; r++) {
                MazeNode[] row = BFSMatrix[r];

                // prints the necessary characters to appear
                // north of the current cell
                for(MazeNode cell : row) {
                    if(cell.getCheckNorthWall()) {
                        bw.write("+-");
                    }
                    else {
                        bw.write("+ ");
                    }
                }
                bw.write("+");
                bw.newLine();

                // prints the necessary characters to appear
                //on the left

                //NOTE: It is not necessary to print the characters
                //on the right of the cell as it will eventually be iterated through
                //and can then perform this same check
                for(int c = 0; c < size; c++) {
                    MazeNode cell = row[c];
                    if(cell.getCheckWestWall()) {
                        bw.write("| ");
                    }
                    else {
                        bw.write("  ");
                    }
                    if(c == size - 1) {
                        bw.write("|");
                        bw.newLine();
                    }
                }

                // prints the necessary characters to appear
                // south of the current cell
                if(r == size - 1) {
                    for(MazeNode cell : row) {
                        if(cell.getCheckSouthWall()) {
                            bw.write("+-");
                        }
                        else {
                            bw.write("+ ");
                        }
                    }
                    bw.write("+");
                    bw.newLine();
                }
            }


/****************************Prints Maze for BFS traversal****************************/
            bw.newLine();
            bw.write("BFS:");
            bw.newLine();

            for(int r = 0; r < size; r++) {
                MazeNode[] row = BFSMatrix[r];

                // prints the necessary characters to appear
                // north of the current cell
                for(MazeNode cell : row) {
                    if(cell.getCheckNorthWall()) {
                        bw.write("+-");
                    }
                    else {
                        bw.write("+ ");
                    }
                }
                bw.write("+");
                bw.newLine();

                // prints the necessary characters to appear
                //on the left

                //NOTE: It is not necessary to print the characters
                //on the right of the cell as it will eventually be iterated through
                //and can then perform this same check
                for(int c = 0; c < size; c++) {
                    MazeNode cell = row[c];
                    if(cell.getCheckWestWall()) {
                        if(cell.getTraversalOrder() != -1) {
                            bw.write("|" + cell.getTraversalOrder());
                            BFSvisitedCells++;
                        }
                        else {
                            bw.write("| ");
                        }
                    }
                    else {
                        if(cell.getTraversalOrder() != -1) {
                            bw.write(" " + cell.getTraversalOrder());
                            BFSvisitedCells++;
                        }
                        else {
                            bw.write("  ");
                        }
                    }
                    if(c == size - 1) {
                        bw.write("|");
                        bw.newLine();
                    }
                }

                // prints the necessary characters to appear
                // south of the current cell
                if(r == size - 1) {
                    for(MazeNode cell : row) {
                        if(cell.getCheckSouthWall()) {
                            bw.write("+-");
                        }
                        else {
                            bw.write("+ ");
                        }
                    }
                    bw.write("+");
                    bw.newLine();

                }
            }

            bw.newLine();

/****************************Prints Maze for BFS Shortest Path****************************/

            //Stack to keep track of the path for BFS
            Stack<MazeNode> BFSPath = new Stack<MazeNode>();
            //Starting path-tracking from exit node
            MazeNode BFSCell = BFSMatrix[size-1][size-1];
            //setting the start and end positions in the maze to be part of the shortest path
            BFSMatrix[0][0].setShortest(true);
            BFSMatrix[size-1][size-1].setShortest(true);

            //iterating backwards to construct the path traversal for the algorithm
            while(BFSCell != BFSMatrix[0][0]) {
                //Adding node to path
                BFSPath.add(BFSCell);
                // Updating to indicate it is part of the shortest path
                BFSMatrix[BFSCell.getRow()][BFSCell.getCol()].setShortest(true);
                //Moving to the parent of the cell and repeating the process
                BFSCell = BFSCell.getParent();
            }
            //Adding the entry cell
            BFSPath.add(BFSMatrix[0][0]);

            int pathLengthBFS = BFSPath.size();

            for(int r = 0; r < size; r++) {
                MazeNode[] row = BFSMatrix[r];

                // prints the necessary characters to appear
                // north of the current cell
                for(MazeNode cell : row) {
                    if(cell.getCheckNorthWall()) {
                        bw.write("+-");
                    }
                    else {
                        bw.write("+ ");
                    }
                }
                bw.write("+");
                bw.newLine();

                // prints the necessary characters to appear
                //on the left

                //NOTE: It is not necessary to print the characters
                //on the right of the cell as it will eventually be iterated through
                //and can then perform this same check
                for(int c = 0; c < size; c++) {
                    MazeNode cell = row[c];
                    if(cell.getCheckWestWall()) {
                        if(cell.getShortest()) {
                            bw.write("|#");
                        }
                        else {
                            bw.write("| ");
                        }
                    }
                    else {
                        if(cell.getShortest()) {
                            bw.write(" #");
                        }
                        else {
                            bw.write("  ");
                        }
                    }
                    if(c == size - 1) {
                        bw.write("|");
                        bw.newLine();
                    }
                }

                // prints the necessary characters to appear
                // south of the current cell
                if(r == size - 1) {
                    for(MazeNode cell : row) {
                        if(cell.getCheckSouthWall()) {
                            bw.write("+-");
                        }
                        else {
                            bw.write("+ ");
                        }
                    }
                    bw.write("+");
                    bw.newLine();

                }
            }

            bw.newLine();
            bw.write("Path: ");
            while(BFSPath.size() != 0) {
                MazeNode pathCell = BFSPath.pop();
                bw.write("(" + pathCell.getRow() + "," + pathCell.getCol() + ") ");
            }


            bw.newLine();
            bw.write("Length of path: " + pathLengthBFS);
            bw.newLine();
            bw.write("Visited cells: " + BFSvisitedCells);



            bw.newLine();
            bw.newLine();


/****************************Prints Maze for DFS traversal****************************/

            bw.newLine();
            bw.write("DFS:");
            bw.newLine();

            for(int r = 0; r < size; r++) {
                MazeNode[] row = DFSMatrix[r];

                // prints the necessary characters to appear
                // north of the current cell
                for(MazeNode cell : row) {
                    if(cell.getCheckNorthWall()) {
                        bw.write("+-");
                    }
                    else {
                        bw.write("+ ");
                    }
                }
                bw.write("+");
                bw.newLine();

                // prints the necessary characters to appear
                //on the left

                //NOTE: It is not necessary to print the characters
                //on the right of the cell as it will eventually be iterated through
                //and can then perform this same check
                for(int c = 0; c < size; c++) {
                    MazeNode cell = row[c];
                    if(cell.getCheckWestWall()) {
                        if(cell.getTraversalOrder() != -1) {
                            bw.write("|" + cell.getTraversalOrder());
                            DFSvisitedCells++;
                        }
                        else {
                            bw.write("| ");
                        }
                    }
                    else {
                        if(cell.getTraversalOrder() != -1) {
                            bw.write(" " + cell.getTraversalOrder());
                            DFSvisitedCells++;
                        }
                        else {
                            bw.write("  ");
                        }
                    }
                    if(c == size - 1) {
                        bw.write("|");
                        bw.newLine();
                    }
                }

                // prints the necessary characters to appear
                // south of the current cell
                if(r == size - 1) {
                    for(MazeNode cell : row) {
                        if(cell.getCheckSouthWall()) {
                            bw.write("+-");
                        }
                        else {
                            bw.write("+ ");
                        }
                    }
                    bw.write("+");
                    bw.newLine();

                }
            }

            bw.newLine();

/****************************Prints Maze for DFS Shortest Path****************************/

            //Stack to keep track of the path for DFS
            Stack<MazeNode> DFSPath = new Stack<MazeNode>();
            //Starting path-tracking from exit node
            MazeNode DFSCell = DFSMatrix[size-1][size-1];
            //setting the start and end positions in the maze to be part of the shortest path
            DFSMatrix[0][0].setShortest(true);
            DFSMatrix[size-1][size-1].setShortest(true);

            //iterating backwards to construct the path traversal for the algorithm
            while(DFSCell != DFSMatrix[0][0]) {
                //Adding node to path
                DFSPath.add(DFSCell);
                //Updating to indicate it is part of the shortest path
                DFSMatrix[DFSCell.getRow()][DFSCell.getCol()].setShortest(true);
                //Moving to the parent of the cell and repeating the process
                DFSCell = DFSCell.getParent();
            }
            //Adding the entry cell
            DFSPath.add(DFSMatrix[0][0]);

            int pathLengthDFS = DFSPath.size();

            for(int r = 0; r < size; r++) {
                MazeNode[] row = DFSMatrix[r];

                // prints the necessary characters to appear
                // north of the current cell
                for(MazeNode cell : row) {
                    if(cell.getCheckNorthWall()) {
                        bw.write("+-");
                    }
                    else {
                        bw.write("+ ");
                    }
                }
                bw.write("+");
                bw.newLine();

                // prints the necessary characters to appear
                //on the left

                //NOTE: It is not necessary to print the characters
                //on the right of the cell as it will eventually be iterated through
                //and can then perform this same check
                for(int c = 0; c < size; c++) {
                    MazeNode cell = row[c];
                    if(cell.getCheckWestWall()) {
                        if(cell.getShortest()) {
                            bw.write("|#");
                        }
                        else {
                            bw.write("| ");
                        }
                    }
                    else {
                        if(cell.getShortest()) {
                            bw.write(" #");
                        }
                        else {
                            bw.write("  ");
                        }
                    }
                    if(c == size - 1) {
                        bw.write("|");
                        bw.newLine();
                    }
                }

                // prints the necessary characters to appear
                // south of the current cell
                if(r == size - 1) {
                    for(MazeNode cell : row) {
                        if(cell.getCheckSouthWall()) {
                            bw.write("+-");
                        }
                        else {
                            bw.write("+ ");
                        }
                    }
                    bw.write("+");
                    bw.newLine();

                }
            }

            bw.newLine();
            /*
            Iterates through DFSPath variable to print out the traversal of an algorithm
             */
            bw.write("Path: ");
            while(DFSPath.size() != 0) {
                MazeNode pathCell = DFSPath.pop();
                bw.write("(" + pathCell.getRow() + "," + pathCell.getCol() + ") ");
            }


            bw.newLine();
            bw.write("Length of path: " + pathLengthDFS);
            bw.newLine();
            bw.write("Visited cells: " + DFSvisitedCells);

            bw.newLine();
            bw.newLine();
            bw.write("======================");
            bw.newLine();
            bw.write("  Program Completed!");
            bw.newLine();
            bw.write("======================");

            bw.close();





        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }



}