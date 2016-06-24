package yeb8jo.gameoflife.model;

/**
 * Created by yeb8jo on 2016/05/12.
 */
public class GameModel {
    public static final int CELL_SIZE = 10;

    private int rows;
    private int columns;

    private boolean map[][];

    public GameModel(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;

        map = new boolean[this.rows][this.columns];
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public boolean isAlive(int row, int column) {
        if(isOutOfMap(row, column))
            return false;

        return map[row][column];
    }

    public void makeAlive(int row, int column) {
        if(!isOutOfMap(row, column))
            map[row][column] = true;
    }

    public void makeDead(int row, int column) {
        if(!isOutOfMap(row, column))
            map[row][column] = false;
    }

    public boolean willLive(int row, int column) {
        int aliveNeighbours = 0;
        for (int i = row-1; i <= row +1 ; i++) {
            for (int j = column-1; j <= column+1; j++) {
                if(!(i==row && j==column) && isAlive(i, j))
                    aliveNeighbours++;
            }
        }

        if(isAlive(row, column))
            return aliveNeighbours == 2 || aliveNeighbours == 3;

        return aliveNeighbours == 3;
    }

    private boolean isOutOfMap(int row, int column) {
        return row < 0 || row > rows -1 || column < 0 || column > columns -1;
    }

    public void next() {
        boolean newMap[][] = new boolean[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                 newMap[i][j] = willLive(i, j);
            }
        }
        map = newMap;
    }

}
