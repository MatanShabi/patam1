package test;

import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;

public class DictionaryManager {
    private static DictionaryManager dictionaryManager = null;
    private Map<String, Dictionary> dictionariesMap;

    private DictionaryManager() {
        dictionariesMap = new HashMap<>();
    }

    public static DictionaryManager get() {
        if (dictionaryManager == null) {
            dictionaryManager = new DictionaryManager();
        }
        return dictionaryManager;
    }

    public boolean query(String... args) {
        String[] booksName = new String[args.length - 1];
        booksName = Arrays.copyOfRange(args, 0, args.length - 1);

        String query = args[args.length - 1];

        boolean result = false;

        for (String bookName : booksName) {
            addBook(bookName);
            if (dictionariesMap.get(bookName).query(query)) {
                result = true;
            }
        }
        return result;
    }

    private void addBook(String bookName) {
        if (!dictionariesMap.containsKey(bookName)) {
            dictionariesMap.put(bookName, new Dictionary(bookName));
        }
    }

    public int getSize() {
        return dictionariesMap.size();
    }

    public boolean challenge(String... args) {
        String query = args[args.length - 1];
        String[] booksName = new String[args.length - 1];
        booksName = Arrays.copyOfRange(args, 0, args.length - 1);

        boolean result = false;

        for (String bookName : booksName) {
            addBook(bookName);
            if (dictionariesMap.get(bookName).challenge(query)) {
                result = true;
            }
        }

        return result;
    }
}
