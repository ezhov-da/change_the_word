package ru.ezhov.changetheword;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ezhov_da
 */
public class WordChangeTest {

    @Test
    public void testChange() {
        System.out.println("change");
        String text = "text";
        WordChange instance = new WordChange();
        String result = instance.change(text);
        System.out.println("result: " + result);
        assertTrue(!text.equals(result));
    }

}
