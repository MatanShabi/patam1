package test;

import java.util.Objects;
import java.util.Random;

public class Tile {
    public final char letter;
    public final int score;

    public Tile(char letter, int score) {
        this.letter = letter;
        this.score = score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tile tile = (Tile) o;
        return letter == tile.letter && score == tile.score;
    }

    @Override
    public int hashCode() {
        return Objects.hash(letter, score);
    }

    public static class Bag {

        private int[] letters;
        private int[] initialLetters;
        private int amountOfTiles;
        private Tile[] tiles;
        private static Bag instance;

        public Bag() {
            this.amountOfTiles = 98;
            this.letters = new int[]{9, 2, 2, 4, 12, 2, 3, 2, 9, 1, 1, 4, 2, 6, 8, 2, 1, 6, 4, 6, 4, 2, 2, 1, 2, 1};
            this.initialLetters = this.letters.clone();
            this.tiles = new Tile[26];
            int[] scores = new int[]{1, 3, 3, 2, 1, 4, 2, 4, 1, 8, 5, 1, 3, 1, 1, 3, 10, 1, 1, 1, 1, 4, 4, 8, 4, 10};
            for (int i = 0; i < 26; i++) {
                this.tiles[i] = new Tile((char)('A' + i), scores[i]);
            }
        }

        public Tile getRand() {
            if (amountOfTiles == 0) {
                return null; // indicates that all cells are 0
            }

            Random rand = new Random();
            int index = rand.nextInt(26); // Generates a random integer between 0 and 26

            while (letters[index] == 0) {
                index = rand.nextInt(26); // Generates a random integer between 0 and 26
            }

            letters[index]--;

            if (letters[index] == 0) {
                amountOfTiles--;
            }

            return tiles[index];
        }

        public Tile getTile(char letter) {
            int indexOfChar = (int) letter - (int) 'A';
            if(indexOfChar > 26 || indexOfChar < 0 || letters[indexOfChar] == 0) {
                return null;
            }

            letters[indexOfChar]--;
            return tiles[indexOfChar];
        }

        public void put(Tile tile) {
            int indexOfChar = (int) tile.letter - (int) 'A';
            if (this.letters[indexOfChar] < this.initialLetters[indexOfChar]) {
                amountOfTiles++;
                letters[indexOfChar]++;
            }
        }

        public int size() {
            return amountOfTiles;
        }

        public int[] getQuantities() {
            return this.letters.clone();
        }

        public static Bag getBag(){
            if (instance == null){
                instance = new Bag();
            }
            return instance;
        }
    }
}
