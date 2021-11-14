package Naqvi.cs146.project3;

public class MazeNode {

    private MazeNode parent; //keeps track of parent of a MazeNode for backtracking
    private int col; //y position of a node in the graph
    private int row; //x position of a node in the graph

    private boolean isEnclosed;// used to make sure all walls surrounding a Node are not knocked down
    private boolean checkNorth;
    private boolean checkEast;
    private boolean checkSouth;
    private boolean checkWest;

    private boolean visitedBFS; // Keeps track of whether this is a visited Node in BFS
    private boolean visitedDFS; // Keeps track of whether this is a visited Node in DFS
    private boolean visited; // Indicates whether this node has been visited
    private boolean shortest; // indicates whether this Node is part of the shortest path
    private int traversalOrder; //represents the number of the MazeNode when printing traversal order

    public MazeNode(int row, int col) {
        this.row = row;
        this.col = col;

        isEnclosed = true;
        checkNorth = true;
        checkEast = true;
        checkSouth = true;
        checkWest = true;

        visitedDFS = false;
        visitedBFS = false;
        visited = false;

        //initializing variable for traversal
        traversalOrder = -1;
        parent = null;
        shortest = false;

    }

    public boolean getShortest() {
        return shortest;
    }
    public void setShortest(boolean theShortest) {
        shortest = theShortest;
    }

    public boolean getVisitedBFS() {
        return visitedBFS;
    }
    public void setVisitedBFS(boolean visited) {
        visitedBFS = visited;
    }

    public boolean getVisitedDFS() {
        return visitedDFS;
    }
    public void setVisitedDFS(boolean visited) {
        visitedDFS = visited;
    }

    public MazeNode getParent() {return parent;}
    public void setParent(MazeNode cell) {parent = cell;}

    public int getTraversalOrder() {
        return traversalOrder;
    }
    public void setTraversalOrder(int traversalOrder) {
        this.traversalOrder = traversalOrder;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }
    public boolean getVisited() {
        return visited;
    }

    //Sets whether a MazeNode is cornered from all sides
    public void setIsEnclosed(boolean isEnclosed) {
        this.isEnclosed = isEnclosed;
    }
    public boolean getIsEnclosed() {return isEnclosed;}

    public boolean getCheckNorthWall() {
        return checkNorth;
    }
    public boolean getCheckEastWall() {
        return checkEast;
    }
    public boolean getCheckSouthWall() {
        return checkSouth;
    }
    public boolean getCheckWestWall() {
        return checkWest;
    }

    public void setCheckNorthWall(boolean wall) {
        checkNorth = wall;
    }
    public void setCheckEastWall(boolean wall) {
        checkEast = wall;
    }
    public void setCheckSouthWall(boolean wall) {
        checkSouth = wall;
    }
    public void setCheckWestWall(boolean wall) {
        checkWest = wall;
    }

    public int getRow() {return row;}
    public void setRow(int row) {this.row = row;}

    public int getCol() { return col;}
    public void setCol(int col) {this.col = col;}
    
    public void setPos(int row, int col) {
        this.row = row;
        this.col = col;
    }



}
