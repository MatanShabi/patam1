package project2;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IOSearcher {

    public static boolean search(String word, String ...fileNames) {
        for (String fileName : fileNames) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(fileName));
                Pattern pattern = Pattern.compile("\\b" + word + "\\b");

                String line;
                while ((line = reader.readLine()) != null) {
                    Matcher matcher = pattern.matcher(line);
                    if (matcher.find()) {
                        return true;  // Word found in the file
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        return false;
    }

}
