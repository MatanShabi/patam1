package test;

enum CellColor {
    RED,
    BLUE,
    AZURE,
    YELLOW
}

public class Board {

    private static Cell[][] board = new Cell[15][15];
    private static Board instance;

    private Board(){
        //TODO:: init board cells with the specific colors
    }

    // TODO: implement this
    public boolean boardLegal(Word word) {
        if(word.isVertical()){
            if(word.getRow() + word.getTiles().length >= 15) {
                return false;
            }

            boolean isValid = false;

            for(int i = 0; i < word.getTiles().length; i++ ){
                if(board[word.getRow()+i][word.getCol()].tile != null &&  board[word.getRow()+i][word.getCol()].tile != word.getTiles()[i]) {

                }
            }

            return isValid;
        }
        else {
            if(word.getCol() + word.getTiles().length >= 15) {
                return false;
            }
        }
        return true;
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
        public int color;

        public Cell(Tile tile, int color) {
            this.tile = tile;
            this.color = color;
        }

        public Tile getTile() {
            return tile;
        }

        public void setTile(Tile tail) {
            this.tile = tail;
        }

        public int getColor() {
            return color;
        }

        public void setColor(int color) {
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
