package Naqvi.cs146.project3;

import Naqvi.cs146.project3.BFS;
import Naqvi.cs146.project3.DFS;
import Naqvi.cs146.project3.Maze;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


import static org.junit.Assert.assertTrue;

public class MazeTests {

    //method to compare two files line by line
    public boolean compareFiles(String path1, String path2) throws IOException {

        BufferedReader actual = new BufferedReader(new FileReader(path1));
        BufferedReader expected = new BufferedReader(new FileReader(path2));

        String actualLine = actual.readLine();
        String expectedLine = expected.readLine();

        boolean flag = true;

        while (actualLine != null || expectedLine != null) {

            if(actualLine == null || expectedLine == null) {
                flag = false;
                break;
            }
            else if(!actualLine.equalsIgnoreCase(expectedLine)) {
                flag = false;
                break;
            }
            actualLine = actual.readLine();
            expectedLine = expected.readLine();

        }
        return flag;
    }

    @Test
    public void FourByFourTest() throws IOException {
            //Use the same seed for every generation, only change size between runs
            //Compare expected and resulting mazes via file parsers
            //New file for each size
        Maze maze = new Maze(4);

        DFS dfs = new DFS(maze.generateMaze());
        BFS bfs = new BFS(maze.returnMatrix());

        PrintMaze output = new PrintMaze(bfs.executeBFS(), dfs.executeDFS());

        output.writeMazesToFile("4x4");
        boolean result = compareFiles("4x4", "Expected Files/Expected4x4.txt");
        assertTrue(result);
    }

    @Test
    public void EightByEightTest() throws IOException {
        //Use the same seed for every generation, only change size between runs
        //Compare expected and resulting mazes via file parsers
        //New file for each size
        Maze maze = new Maze(8);

        DFS dfs = new DFS(maze.generateMaze());
        BFS bfs = new BFS(maze.returnMatrix());

        PrintMaze output = new PrintMaze(bfs.executeBFS(), dfs.executeDFS());

        output.writeMazesToFile("8x8");
        boolean result = compareFiles("8x8", "Expected Files/Expected8x8.txt");
        assertTrue(result);
    }

    @Test
    public void ThirteenByThirteenTest() throws IOException {
        //Use the same seed for every generation, only change size between runs
        //Compare expected and resulting mazes via file parsers
        //New file for each size
        Maze maze = new Maze(13);

        DFS dfs = new DFS(maze.generateMaze());
        BFS bfs = new BFS(maze.returnMatrix());

        PrintMaze output = new PrintMaze(bfs.executeBFS(), dfs.executeDFS());

        output.writeMazesToFile("13x13");
        boolean result = compareFiles("13x13", "Expected Files/Expected13x13.txt");
        assertTrue(result);
    }

    @Test
    public void TwentySixByTwentySixTest() throws IOException {
        //Use the same seed for every generation, only change size between runs
        //Compare expected and resulting mazes via file parsers
        //New file for each size
        Maze maze = new Maze(26);

        DFS dfs = new DFS(maze.generateMaze());
        BFS bfs = new BFS(maze.returnMatrix());

        PrintMaze output = new PrintMaze(bfs.executeBFS(), dfs.executeDFS());

        output.writeMazesToFile("26x26");
        boolean result = compareFiles("26x26", "Expected Files/Expected26x26.txt");
        assertTrue(result);
    }

    @Test
    public void ThirtySevenByThirtySevenTest() throws IOException {
        //Use the same seed for every generation, only change size between runs
        //Compare expected and resulting mazes via file parsers
        //New file for each size
        Maze maze = new Maze(37);

        DFS dfs = new DFS(maze.generateMaze());
        BFS bfs = new BFS(maze.returnMatrix());

        PrintMaze output = new PrintMaze(bfs.executeBFS(), dfs.executeDFS());

        output.writeMazesToFile("37x37");
        boolean result = compareFiles("37x37", "Expected Files/Expected37x37.txt");
        assertTrue(result);
    }

}
