package test;

import java.util.ArrayList;

enum CellColor {
    GREEN, // regular word score
    AZURE, // double letter score
    BLUE, // triple latter score
    YELLOW, // double word score
    RED // triple word score
}

public class Board {
    static final int SIZE = 15;
    private static Cell[][] board = new Cell[SIZE][SIZE];
    private static Board instance;

    private Board(){
        initBoard();
        initRedCells();
        initYellowCells();
        initBlueCells();
        initAzureCells();
    }

    private void initBoard() {
        for(int i = 0; i<SIZE; i++){
            for(int j = 0; j<SIZE; j++){
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
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.printf("| %s ", board[i][j].color);
            }
            System.out.println("|");
        }
    }

    public boolean boardLegal(Word word) {

        if(word.getCol() < 0 || word.getRow() < 0)
            return false;

        if(word.isVertical()){
            return checkVerticalWordIsLegal(word);
        }
        else {
            return checkHorizontalWordIsLegal(word);
        }
    }

    private boolean checkVerticalWordIsLegal(Word word) {
        int wordPlaceTaken = word.getRow() + word.getTiles().length;

        if(board[7][7].getTile() == null && word.getCol() == 7 && wordPlaceTaken >7 && wordPlaceTaken <= SIZE) {
            return true;
        }

        //check length is valid
        if(wordPlaceTaken >= SIZE) {
            return false;
        }

        // last step if cell has neighbors
        for(int i = 0; i < word.getTiles().length; i++) {
            if(hasNonNullNeighbors(word.getRow()+i, word.getCol())){
                return true;
            }
        }

        return false;
    }

    private boolean checkHorizontalWordIsLegal(Word word){

        int wordPlaceTaken = word.getCol() + word.getTiles().length;

        if(board[7][7].getTile() == null && word.getRow() == 7 && wordPlaceTaken >7 && wordPlaceTaken <= SIZE) {
            return true;
        }

        //check length is valid
        if(wordPlaceTaken >= SIZE) {
            return false;
        }

        // last step if cell has neighbors
        for(int i = 0; i < word.getTiles().length; i++) {
            if(hasNonNullNeighbors(word.getRow(), word.getCol()+i)){
                return true;
            }
        }

        return false;
    }

    public static boolean hasNonNullNeighbors(int row, int col) {

        // Check left neighbor
        if (col > 0 && board[row][col - 1].getTile() != null) {
            return true;
        }

        // Check right neighbor
        if (col < SIZE - 1 && board[row][col + 1].getTile() != null) {
            return true;
        }

        // Check top neighbor
        if (row > 0 && board[row - 1][col].getTile() != null) {
            return true;
        }

        // Check bottom neighbor
        if (row < SIZE - 1 && board[row + 1][col].getTile() != null) {
            return true;
        }

        return false;
    }

    private Word getWordFromVerticalIndex(int row, int col) {
        // Check top and bottom neighbors
        if (!hasNonNullNeighbors(row, col)) {
            return null;
        }
        int startRowIndex = row + 1;

        // find start index
        while (startRowIndex > 0 && board[startRowIndex][col] != null) {
            startRowIndex--;
        }

        Word word = new Word(null, startRowIndex, col, true);
        ArrayList<Tile> tiles = new ArrayList<Tile>();
        for(int i=startRowIndex; i < SIZE; i++ ){
            Tile tile = board[i][col].getTile();
            if(tile != null) {
                tiles.add(tile);
            }
            else {
                break;
            }
        }

        word.setTiles(tiles.toArray(new Tile[tiles.size()]));

        return word;
    }

    private Word getWordFromHorizontalIndex(int row, int col) {
        // Check left and right neighbors
        if (col > 0 && board[row][col - 1].getTile() == null &&
                col < SIZE - 1 && board[row][col + 1].getTile() == null) {
            return null;
        }
        int startColIndex = col;

        // find start index
        while (startColIndex > 0 && board[row][startColIndex] != null) {
            startColIndex--;
        }

        Word word = new Word(null, row, startColIndex, true);
        ArrayList<Tile> tiles = new ArrayList<Tile>();
        for(int i=startColIndex; i < SIZE; i++ ){
            Tile tile = board[row][i].getTile();
            if(tile != null) {
                tiles.add(tile);
            }
            else {
                break;
            }
        }
        word.setTiles(tiles.toArray(new Tile[tiles.size()]));

        return word;
    }

    public boolean dictionaryLegal(Word word){
        return true;
    }

    public ArrayList<Word> getWords(Word word) {
        ArrayList<Word> words = new ArrayList<Word>();
        words.add(word);

        if(word.isVertical()){
            for(int i = 0; i < word.getTiles().length; i++) {
                int currentRow = word.getRow()+i;
                Tile currentTile = board[currentRow][word.getCol()].getTile();
                // check if the char not exists in board to search about new words in the board
                if(currentTile == null){
                    Word newWord = getWordFromVerticalIndex(currentRow, word.getCol());
                    if(newWord != null) {
                        words.add(newWord);
                    }
                }
            }
        }
        else {
            for(int i = 0; i < word.getTiles().length; i++) {
                int currentCol = word.getCol()+i;
                Tile currentTile = board[word.getRow()][currentCol].getTile();
                // check if the char not exists in board to search about new words in the board
                if(currentTile == null){
                    Word newWord = getWordFromHorizontalIndex(word.getRow(), currentCol);
                    if(newWord != null) {
                        words.add(newWord);
                    }
                }
            }
        }
        return words;
    }
    public int getScore(Word word) {
        if(word.isVertical()) {
            return calcVerticalWordScore(word);
        }
        else {
            return calcHorizontalWordScore(word);
        }

    }

    private static int calcVerticalWordScore(Word word) {
        int totalScore = 0;
        int multipler = 1;
        Tile[] tiles = word.getTiles();
        for (int i = 0; i < tiles.length; i++) {
            CellColor currentColor = CellColor.GREEN;
            int currentScore = 0;
            Cell currentCell = board[word.getRow()+i][word.getCol()];

            if(currentCell.getTile() != null){
                currentScore = currentCell.getTile().score;
            }
            else {
                currentScore = tiles[i].score;
                currentColor = currentCell.getColor();
            }

            switch (currentColor){
                case RED -> {
                    totalScore += currentScore;
                    multipler *= 3;
                    break;
                }
                case GREEN -> {
                    totalScore += currentScore;
                    break;
                }
                case BLUE -> {
                    totalScore += currentScore * 3;
                    break;
                }
                case AZURE -> {
                    totalScore += currentScore * 2;
                    break;
                }
                case YELLOW -> {
                    totalScore += currentScore;
                    multipler *= 2;
                }
            }
        }
        return totalScore * multipler;
    }

    private static int calcHorizontalWordScore(Word word) {
        int totalScore = 0;
        int multipler = 1 ;
        Tile[] tiles = word.getTiles();
        for (int i = 0; i < tiles.length; i++) {
            CellColor currentColor = CellColor.GREEN;
            int currentScore = 0;
            Cell currentCell =  board[word.getRow()][word.getCol() + i];

            if(currentCell.getTile() != null){
                currentScore = currentCell.getTile().score;
            }
            else {
                currentScore = tiles[i].score;
                currentColor = currentCell.getColor();
            }

            switch (currentColor){
                case RED -> {
                    totalScore += currentScore;
                    multipler *= 3;
                    break;
                }
                case GREEN -> {
                    totalScore += currentScore;
                    break;
                }
                case BLUE -> {
                    totalScore += currentScore * 3;
                    break;
                }
                case AZURE -> {
                    totalScore += currentScore * 2;
                    break;
                }
                case YELLOW -> {
                    totalScore += currentScore;
                    multipler *= 2;
                }
            }
        }
        return totalScore * multipler;
    }

    public int tryPlaceWord(Word word){
        int totalWordsScore = 0;

        if(!boardLegal(word)) {
            return 0;
        }

        ArrayList<Word> words = getWords(word);
        for(Word w: words) {
            if(dictionaryLegal(w)){
                totalWordsScore += getScore(w);
            }
            else {
                return 0;
            }
        }

        Tile[] tiles = word.getTiles();
        if(word.isVertical()){
            for(int i = 0; i < tiles.length; i++) {
                int currentRow = word.getRow() + i;
                board[currentRow][word.getCol()].setTile(tiles[i]);
            }
        }
        else {
            for (int i = 0; i < tiles.length; i++) {
                int currentCol = word.getCol() + i;
                board[word.getRow()][currentCol].setTile(tiles[i]);
            }
        }

        return totalWordsScore;
    }

    public Tile[][] getTiles() {
        Tile[][] tiles = new Tile[SIZE][SIZE];
        for(int i=0; i< SIZE; i++){
            for(int j=0; j< SIZE; j++){
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
