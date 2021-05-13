import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String url = in.nextLine();
        String pathToHTML = "./Download.html";
        String pathToText = "./Text.txt";
        DownloadingPage page = new DownloadingPage();
        page.download(url, pathToHTML);
        ConvertingPage convertingPage = new ConvertingPage();
        convertingPage.convertToText(pathToHTML, pathToText);
        convertingPage.convertToWords(pathToText);
        clearText(pathToText);
    }

    public static void clearText(String pathToWords) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(new File(pathToWords)))) {
            writer.write("");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
