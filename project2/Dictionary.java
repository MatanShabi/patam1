package project2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Dictionary {
    private final String[] fileNames;

    // cache for exist words
    private final CacheManager cacheManagerLRU;
    // cache for non-exist words
    private final CacheManager cacheManagerLFU;
    private final BloomFilter bloomFilter;

    public Dictionary(String ...fileNames) {
        this.fileNames = fileNames;
        this.cacheManagerLRU = new CacheManager(400, new LRU());
        this.cacheManagerLFU= new CacheManager(100, new LFU());
        this.bloomFilter = new BloomFilter(256,"MD5","SHA1");
        insertWordsToBloomFilter();
    }

    private void insertWordsToBloomFilter() {
        for (String fileName : fileNames) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(fileName));

                String line;
                while ((line = reader.readLine()) != null) {
                    String[] words = line.split(" ");
                    for(String w : words)
                        bloomFilter.add(w);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean query(String word) {
        if(cacheManagerLRU.query(word)){
            return true;
        }
        if(cacheManagerLFU.query(word)){
            return false;
        }
        if(bloomFilter.contains(word)) {
            cacheManagerLRU.add(word);
            return true;
        }
        else {
            cacheManagerLFU.add(word);
            return false;
        }
    }

    public boolean challenge(String word) {
        IOSearcher ioSearch = new IOSearcher();
        if(ioSearch.search(word, fileNames)){
            cacheManagerLRU.add(word);
            return true;
        }
        else {
            cacheManagerLFU.add(word);
            return false;
        }

    }
}
