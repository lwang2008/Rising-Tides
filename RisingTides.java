import java.util.LinkedList;
import java.util.Queue;

public class RisingTides {
    public static boolean[][] floodedRegionsIn(double[][] terrain,
                                               GridLocation[] sources,
                                               double height) {
        
        int rows = terrain.length;
        int cols = terrain[0].length;
        boolean[][] flooded = new boolean[rows][cols];
        
        //Create queue for Breadtb First Search
        Queue<GridLocation> queue = new LinkedList<>();
        
        //Initialize with water sources that are at or below water level
        for (GridLocation source : sources) {
            int row = source.row;
            int col = source.col;
            
            //Check whether or not to flood source
            if (terrain[row][col] <= height) {
                flooded[row][col] = true;
                queue.add(source);
            }
        }
        
        //Defining 4 cardinal directions for each square
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};
        
        //BFS 
        while (!queue.isEmpty()) {
            GridLocation current = queue.poll();
            
            //Check four adjacent cells 
            for (int i = 0; i < 4; i++) {

                //Using 2 lists from above, check 4 adjacent cells
                int newRow = current.row + dr[i];
                int newCol = current.col + dc[i];
                
                //Check if the new position is on grid
                if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols) {
                    //Check if at or below water level and is it flooded yet
                    if (terrain[newRow][newCol] <= height && !flooded[newRow][newCol]) {
                        flooded[newRow][newCol] = true;
                        queue.add(new GridLocation(newRow, newCol));
                    }
                }
            }
        }
        
        return flooded;
    }
}