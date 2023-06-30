package project2;

public interface CacheReplacementPolicy{

	// Add query for word
	void add(String word);

	// Return the word that should be removed from the cache
	String remove(); 
}
