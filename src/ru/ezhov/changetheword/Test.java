package ru.ezhov.changetheword;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ezhov_da
 */
public class Test {

    private static final Logger LOG = Logger.getLogger(Test.class.getName());

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Map<String, String> mapWord = new HashMap<String, String>();
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File("src.txt"));
            scanner = new Scanner(new InputStreamReader(new FileInputStream(new File("src.txt")), "UTF-8"));
            //scanner = new Scanner(new File("manifest.mf"));
            //scanner = new Scanner(new File("build.xml"));
        } catch (Exception ex) {
            LOG.log(Level.WARNING, "Ну не удалось, видимо не нашелся файл )))", ex);
        }
        if (scanner == null) {
            System.exit(-1000);
        }

        StringBuilder fullText = new StringBuilder(5000);

        while (scanner.hasNextLine()) {
            String s = scanner.nextLine();
            String[] strings = s.split("\\s|-|,|\\)|\\(|\\.|«");
            fullText.append(s);
            fullText.append("\n");
            for (String str : strings) {
                String strFromReverse = reverse(str);
                if (strFromReverse != null) {
                    mapWord.put(str, strFromReverse);
                }
            }

        }

        String textForReplace = fullText.toString();
        Set<String> setString = mapWord.keySet();
        for (String strReplace : setString) {
            textForReplace = textForReplace.replaceAll(strReplace, mapWord.get(strReplace));
        }
        scanner.close();
        File file = new File("result.txt");

//        try (FileWriter writer = new FileWriter(file);) {
//            writer.write(textForReplace);
//        } catch (IOException ex) {
//            LOG.log(Level.WARNING, "Ну,.... не удалось )))", ex);
//        }
    }

    private static String reverse(String src) {
        String result = null;
        if (src.length() > 3) {
            List<Character> characters = new ArrayList<Character>(src.length());
            String f = src.substring(0, 1);
            String e = src.substring(src.length() - 1, src.length());
            String part = src.substring(1, src.length() - 1);
            char[] chars = part.toCharArray();
            for (char c : chars) {
                characters.add(c);
            }
            Collections.shuffle(characters);
            StringBuilder stringBuilder = new StringBuilder(src.length());
            stringBuilder.append(f);
//            characters.stream().forEach((c) -> {
//                stringBuilder.append(c);
//            });
            stringBuilder.append(e);
            return stringBuilder.toString();
        }
        return null;
    }

}
