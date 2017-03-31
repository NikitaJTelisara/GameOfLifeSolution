public class gameOfLife {
    public static void main(String[] args) {
        int[][] inputGameGrid = {{0, 0, 0, 0, 0}, {0, 0, 1, 0, 0}, {0, 0, 1, 0, 0}, {0, 0, 1, 0, 0}, {0, 0, 0, 0, 0}};

        int num_of_iterations = 7;

        int[][] expected_output = {{0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 1, 1, 1, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}};

        boolean result = testGame(inputGameGrid, num_of_iterations, expected_output);
        if (result) {
            System.out.println("Game Successful!!");
        } else {
            System.out.println("Game Failed!!");
        }

        int[][] inputGameGrid1 = {{1,0,1,1},{0,0,1,1}};

        int num_of_iterations1 = 3;

        int[][] expected_output1 = {{0,0,0,0},{0,0,0,0}};

        result = testGame(inputGameGrid1, num_of_iterations1, expected_output1);
        if (result) {
            System.out.println("Game Successful!!");
        } else {
            System.out.println("Game Failed!!");
        }
    }

    public static boolean testGame(int[][] seed, int num_of_iterations, int[][] expected_state) {
        int[][] output = new int[seed.length][seed[0].length];
        System.out.println("Initial Status of Grid");
        printGrid(seed);
        for (int i = 1; i <= num_of_iterations; i++) {
            System.out.println("Generation Number :" + i);
            output = gameOfLife(seed);
            printGrid(output);
            seed = output;
        }
        return checkMatch(output,expected_state);
    }

    public static void printGrid(int[][] gameGrid) {
        for (int i = 0; i < gameGrid.length; i++) {
            for (int j = 0; j < gameGrid[0].length; j++) {
                System.out.print(gameGrid[i][j]);
            }
            System.out.print("\n");
        }
        System.out.print("\n");
    }

    public static boolean checkMatch(int[][] grid1, int[][] grid2) {
        for (int i = 0; i < grid1.length; i++) {
            for (int j = 0; j < grid1[0].length; j++) {
                if (grid1[i][j] != grid2[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    public static int[][] gameOfLife(int[][] gameGrid) {
        int[][] nextStateGrid = new int[gameGrid.length][gameGrid[0].length];
        for (int i = 0; i < gameGrid.length; i++) {
            for (int j = 0; j < gameGrid[0].length; j++) {
                nextStateGrid[i][j] = gameGrid[i][j];
            }
        }
        for (int i = 0; i < nextStateGrid.length; i++) {
            for (int j = 0; j < nextStateGrid[0].length; j++) {
                int k = getLiveNeighbours(nextStateGrid, i, j);
                /*  Any live cell */
                if (nextStateGrid[i][j] == 1) {
                    /* with two or three live neighbors lives on to the next generation */
                    if (k == 2 || k == 3) {
                        gameGrid[i][j] = 1;
                        /*with fewer than two live neighbors dies */
                    } else if (k < 2) {
                        gameGrid[i][j] = 0;
                        /* with more than three live neighbors dies */
                    } else if (k > 2) {
                        gameGrid[i][j] = 0;
                    }
                    /* Any dead cell */
                } else {
                    /* with exactly three live neighbors becomes a live cell */
                    if (k == 3) {
                        gameGrid[i][j] = 1;

                    }
                }
            }
        }
        return gameGrid;
    }

    /**
     * Get Live Neighbours, get all live neighbours for a cell
     *
     * @return count, the number of live neighbours
     */
    public static int getLiveNeighbours(int[][] gameGrid, int row, int col) {
        int count = 0;

        /* neighbour 1 is at int[row-1][col-1] */
        int j = row - 1;
        int k = col - 1;
        if (incrementCount(j, k, gameGrid)) {
            count++;
        }

        /* neighbour 2 is at int[row-1][col]  */
        j = row - 1;
        k = col;
        if (incrementCount(j, k, gameGrid)) {
            count++;
        }

        /* neighbour 3 is at int[row-1][col+1] */
        j = row - 1;
        k = col + 1;
        if (incrementCount(j, k, gameGrid)) {
            count++;
        }

        /* neighbour 4 is at int[row][col-1]   */
        j = row;
        k = col - 1;
        if (incrementCount(j, k, gameGrid)) {
            count++;
        }

        /* neighbour 5 is at int[row][col+1]  */
        j = row;
        k = col + 1;
        if (incrementCount(j, k, gameGrid)) {
            count++;
        }

        /* neighbour 6 is at int[row+1][col-1] */
        j = row + 1;
        k = col - 1;
        if (incrementCount(j, k, gameGrid)) {
            count++;
        }

        /* neighbour 7 is at int[row+1][col]   */
        j = row + 1;
        k = col;
        if (incrementCount(j, k, gameGrid)) {
            count++;
        }

        /* neighbour 8 is at int[row+1][col+1]  */
        j = row + 1;
        k = col + 1;
        if (incrementCount(j, k, gameGrid)) {
            count++;
        }
        return count;
    }

    /**
     * Increment Count, check if neighbour is live
     *
     * @return true, if count should be incremented, otherwise false;
     */
    public static boolean incrementCount(int row, int col, int[][] gameGrid) {
        if ((row >= 0 && row < gameGrid.length) && (col >= 0 && col < gameGrid[0].length)) {
            if (gameGrid[row][col] == 1) {
                return true;
            }
        }
        return false;
    }
}
