package test;

enum CellColor {
    GREEN, // regular word score
    AZURE, // double letter score
    BLUE, // triple latter score
    YELLOW, // double word score
    RED // triple word score
}

public class Board {

    private static Cell[][] board = new Cell[15][15];
    private static Board instance;

    private Board(){
        initBoard();
        initRedCells();
        initYellowCells();
        initBlueCells();
        initAzureCells();
    }

    private void initBoard() {
        for(int i = 0; i<15; i++){
            for(int j = 0; j<15; j++){
                board[i][j] = new Cell(null, CellColor.GREEN);
            }
        }
    }
    private void initRedCells(){
        board[0][0].setColor(CellColor.RED);
        board[0][7].setColor(CellColor.RED);
        board[0][14].setColor(CellColor.RED);
        board[7][0].setColor(CellColor.RED);
        board[14][0].setColor(CellColor.RED);
        board[14][7].setColor(CellColor.RED);
        board[7][14].setColor(CellColor.RED);
        board[14][14].setColor(CellColor.RED);
    }

    private void initYellowCells() {
        board[7][7].setColor(CellColor.YELLOW);
        board[1][1].setColor(CellColor.YELLOW);
        board[2][2].setColor(CellColor.YELLOW);
        board[3][3].setColor(CellColor.YELLOW);
        board[4][4].setColor(CellColor.YELLOW);
        board[1][13].setColor(CellColor.YELLOW);
        board[2][12].setColor(CellColor.YELLOW);
        board[3][11].setColor(CellColor.YELLOW);
        board[4][10].setColor(CellColor.YELLOW);
        board[13][1].setColor(CellColor.YELLOW);
        board[12][2].setColor(CellColor.YELLOW);
        board[11][3].setColor(CellColor.YELLOW);
        board[10][4].setColor(CellColor.YELLOW);
        board[13][13].setColor(CellColor.YELLOW);
        board[12][12].setColor(CellColor.YELLOW);
        board[11][11].setColor(CellColor.YELLOW);
        board[10][10].setColor(CellColor.YELLOW);
    }

    private void initBlueCells(){
        board[5][5].setColor(CellColor.BLUE);
        board[5][9].setColor(CellColor.BLUE);
        board[9][5].setColor(CellColor.BLUE);
        board[9][9].setColor(CellColor.BLUE);
        board[5][1].setColor(CellColor.BLUE);
        board[9][1].setColor(CellColor.BLUE);
        board[1][5].setColor(CellColor.BLUE);
        board[5][5].setColor(CellColor.BLUE);
        board[9][5].setColor(CellColor.BLUE);
        board[13][5].setColor(CellColor.BLUE);
        board[1][9].setColor(CellColor.BLUE);
        board[5][9].setColor(CellColor.BLUE);
        board[9][9].setColor(CellColor.BLUE);
        board[13][9].setColor(CellColor.BLUE);
        board[5][13].setColor(CellColor.BLUE);
        board[9][13].setColor(CellColor.BLUE);
    }
    private void initAzureCells(){
        board[3][0].setColor(CellColor.AZURE);
        board[11][0].setColor(CellColor.AZURE);
        board[6][2].setColor(CellColor.AZURE);
        board[8][2].setColor(CellColor.AZURE);
        board[0][3].setColor(CellColor.AZURE);
        board[7][3].setColor(CellColor.AZURE);
        board[14][3].setColor(CellColor.AZURE);
        board[2][6].setColor(CellColor.AZURE);
        board[6][6].setColor(CellColor.AZURE);
        board[8][6].setColor(CellColor.AZURE);
        board[12][6].setColor(CellColor.AZURE);
        board[3][7].setColor(CellColor.AZURE);
        board[11][7].setColor(CellColor.AZURE);
        board[2][8].setColor(CellColor.AZURE);
        board[6][8].setColor(CellColor.AZURE);
        board[8][8].setColor(CellColor.AZURE);
        board[12][8].setColor(CellColor.AZURE);
        board[0][11].setColor(CellColor.AZURE);
        board[7][11].setColor(CellColor.AZURE);
        board[14][11].setColor(CellColor.AZURE);
        board[6][12].setColor(CellColor.AZURE);
        board[8][12].setColor(CellColor.AZURE);
        board[3][14].setColor(CellColor.AZURE);
        board[11][14].setColor(CellColor.AZURE);
    }

    private void print(){
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                System.out.printf("| %s ", board[i][j].color);
            }
            System.out.println("|");
        }
    }

    // TODO: implement this
    public boolean boardLegal(Word word) {
        return false;
    }

    public boolean dictionaryLegal(){
        return true;
    }

    public Word[] getWords(Word word) {
        return null;
    }
    public int getScore(Word word) {
        return 0;
    }

    public int tryPlaceWord(Word word){
        return 0;
    }

    public Tile[][] getTiles() {
        Tile[][] tiles = new Tile[15][15];
        for(int i=0; i< 15; i++){
            for(int j=0; j< 15; j++){
                tiles[i][j] = board[i][j].getTile();
            }
        }
        return tiles;
    }

    private static class Cell {
        private Tile tile;
        public CellColor color;

        public Cell(Tile tile, CellColor color) {
            this.tile = tile;
            this.color = color;
        }

        public Tile getTile() {
            return tile;
        }

        public void setTile(Tile tail) {
            this.tile = tail;
        }

        public CellColor getColor() {
            return color;
        }

        public void setColor(CellColor color) {
            this.color = color;
        }
    }

    public static Board getBoard() {
        if(instance == null) {
            instance = new Board();
        }
        return instance;
    }
}
