package test;

import java.util.Objects;
import java.io.*;

public class BookScrabbleHandler implements ClientHandler {

    public BookScrabbleHandler() {
    }

    @Override
    public void handleClient(InputStream inFromclient, OutputStream outToClient) {

        DictionaryManager dictionaryManager = DictionaryManager.get();

        try {
            PrintWriter printWriter = new PrintWriter(outToClient, true);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inFromclient));

            String line = bufferedReader.readLine();
            String[] query = line.substring(2).split(",");

            String key = new StringBuilder().append(line.charAt(0)).toString();

            if (key.equals("Q")) {
                printWriter.println(dictionaryManager.query(query));
            } else if (key.equals("C")) {
                printWriter.println(dictionaryManager.challenge(query));
            }

            printWriter.close();
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() {
    }
}
