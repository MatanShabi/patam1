package project2;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.BitSet;

public class BloomFilter {

    private final BitSet wordBitSet;
    private int wordBitSetLen;
    private String md5;
    private String sha1;

    public BloomFilter(int wordBitSetLen, String md5, String sha1) {
        this.md5 = md5;
        this.sha1 = sha1;
        this.wordBitSetLen = wordBitSetLen;
        this.wordBitSet = new BitSet(wordBitSetLen);
    }

    public void add(String word) {
        try {
            MessageDigest md5Hash = MessageDigest.getInstance(md5);
            MessageDigest sha1Hash = MessageDigest.getInstance(sha1);

            byte[] md5ResultBytes = md5Hash.digest(word.getBytes());
            byte[] sha1ResultBytes = sha1Hash.digest(word.getBytes());

            int md5ResultValue = Math.abs(new BigInteger(md5ResultBytes).intValue() % wordBitSetLen);
            int sha1ResultValue = Math.abs(new BigInteger(sha1ResultBytes).intValue() % wordBitSetLen);

            wordBitSet.set(md5ResultValue);
            wordBitSet.set(sha1ResultValue);

        } catch (NoSuchAlgorithmException e) {
            System.out.println(e.getMessage());
        }
    }


    public boolean contains(String word) {
        try {
            MessageDigest md5Hash = MessageDigest.getInstance(md5);
            MessageDigest sha1Hash = MessageDigest.getInstance(sha1);

            byte[] md5ResultBytes = md5Hash.digest(word.getBytes());
            byte[] sha1ResultBytes = sha1Hash.digest(word.getBytes());

            int md5ResultValue = Math.abs(new BigInteger(md5ResultBytes).intValue() % wordBitSetLen);
            int sha1ResultValue = Math.abs(new BigInteger(sha1ResultBytes).intValue() % wordBitSetLen);

            return wordBitSet.get(md5ResultValue) && wordBitSet.get(sha1ResultValue);

        } catch (NoSuchAlgorithmException e) {
            System.out.println(e.getMessage());
        }

        return false;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for( int i = 0; i < wordBitSet.length();  i++ )
        {
            s.append( wordBitSet.get(i) ? 1: 0 );
        }
        return s.toString();
    }
}