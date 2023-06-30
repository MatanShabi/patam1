package project2;


import java.util.*;

public class LFU implements CacheReplacementPolicy {
    private int minFrequency = 0;
    private final Map<String, Entry> cache = new HashMap<String, Entry>();
    private final Map<Integer, LinkedHashMap<String, String>> wordFrequency = new HashMap<Integer, LinkedHashMap<String, String>>();

    private static class Entry {
        String word;
        int frequency;

        Entry(String word, int frequency) {
            this.word = word;
            this.frequency = frequency;
        }
    }

    public void add(String word) {
        if (cache.containsKey(word)) {
            updateFrequency(word);
        } else {
            addToCache(word);
        }
    }

    public String remove() {
        if (cache.isEmpty()) {
            return null;
        }

        LinkedHashMap<String, String> wordsWithMinFrequency = wordFrequency.get(minFrequency);
        String wordToRemove = wordsWithMinFrequency.keySet().iterator().next();

        wordsWithMinFrequency.remove(wordToRemove);
        if (wordsWithMinFrequency.isEmpty()) {
            wordFrequency.remove(minFrequency);
            updateMinFrequency();
        }

        cache.remove(wordToRemove);

        return wordToRemove;
    }

    private void updateFrequency(String word) {
        Entry entry = cache.get(word);
        int frequency = entry.frequency;
        entry.frequency++;

        LinkedHashMap<String, String> wordsWithPrevFrequency = wordFrequency.get(frequency);
        wordsWithPrevFrequency.remove(word);
        wordFrequency.putIfAbsent(frequency + 1, new LinkedHashMap<>());
        wordFrequency.get(frequency + 1).put(word, word);

        if (frequency == minFrequency && wordsWithPrevFrequency.isEmpty()) {
            updateMinFrequency();
        }
    }

    private void addToCache(String word) {
        cache.put(word, new Entry(word, 1));
        wordFrequency.putIfAbsent(1, new LinkedHashMap<>());
        wordFrequency.get(1).put(word, word);
        minFrequency = 1;
    }

    private void updateMinFrequency() {
        minFrequency = wordFrequency.isEmpty() ? 0 : wordFrequency.keySet().iterator().next();
    }
}
