package ru.ezhov.changetheword;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author ezhov_da
 */
public class WordChange {

    protected String patternForSpli = "\\s|-|,|\\)|\\(|\\.|«";

    public String change(String text) {
        Map<String, String> mapWord = new HashMap<String, String>();
        StringBuilder fullText = new StringBuilder(5000);
        String[] strings = text.split("\\s|-|,|\\)|\\(|\\.|«");
        for (String str : strings) {
            String strFromReverse = reverse(str);
            if (strFromReverse != null) {
                mapWord.put(str, strFromReverse);
            }
        }

        String textForReplace = text;
        Set<String> setString = mapWord.keySet();
        for (String strReplace : setString) {
            textForReplace = textForReplace.replaceAll(strReplace, mapWord.get(strReplace));
        }
        return textForReplace;
    }

    private String reverse(String src) {
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
            for (char c : characters) {
                stringBuilder.append(c);
            }
            stringBuilder.append(e);
            return stringBuilder.toString();
        }
        return null;
    }
}
