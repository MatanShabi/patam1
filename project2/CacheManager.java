package project2;


import java.util.HashSet;

public class CacheManager {

    private final int size;
    private final HashSet<String> cache ;
    private final CacheReplacementPolicy crp;

    public CacheManager(int size, CacheReplacementPolicy crp) {
        this.size = size;
        this.cache = new HashSet<String>();
        this.crp = crp;
    }

    public boolean query(String word){
        return cache.contains(word);
    }

    public void add(String word) {
        if(cache.size() >= size){
            String removeWord = crp.remove();
            cache.remove(removeWord);
        }
        crp.add(word);
        cache.add(word);

    }
}
