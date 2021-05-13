import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ConvertingPage {
    public void convertToText(String pathToHTML, String pathToWords) {
        Document document;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(new File(pathToWords), true))) {
            document = Jsoup.parse(new File(pathToHTML), "UTF-8");
            writer.write(document.body().text());
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void convertToWords(String path) {

        String text = null;
        Map<String, Integer> words;
        List<String> q;
        String w;

        try {
            text = Files.readString(Paths.get(path));
        } catch (IOException e) {
            e.printStackTrace();
        }

        q = Stream.of(text.split("[,;:.!?\\n\\r\\t\\s'\"\\[\\]()]+"))
                .map(String::toLowerCase)
                .collect(Collectors.toList());

        for (int i = 0; i < q.size(); i++) {
            w = q.get(i);
            if (!w.matches("^[a-zа-я]+$")) {
                q.remove(q.get(i));
            }
        }

        words = q.stream().collect(Collectors.toMap(Function.identity(), v -> 1, Integer::sum));
        System.out.println(words);
    }
}
