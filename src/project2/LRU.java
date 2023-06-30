package project2;


import java.util.HashSet;
import java.util.LinkedList;

public class LRU implements CacheReplacementPolicy {

    private final HashSet<String> cache = new HashSet<String>();
    private final LinkedList<String> queue = new LinkedList<String>();

    // Add query for word
    public void add(String word){
        if(cache.contains(word)){
            queue.remove(word);
        } else {
            cache.add(word);
        }
        queue.addLast(word);
    }

    // Return the word that should be removed from the cache
    public String remove() {
        if (queue.isEmpty()) {
            return null;
        }
        String removeWord = queue.getFirst();
        queue.removeFirst();
        cache.remove(removeWord);

        return removeWord;
    }
}
