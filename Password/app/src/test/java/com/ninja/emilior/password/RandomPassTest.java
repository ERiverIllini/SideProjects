package com.ninja.emilior.password;

import org.junit.Test;
import java.util.Arrays;
import java.util.HashSet;

import static org.junit.Assert.*;

/**
 * Created by emilio on 3/13/17.
 */
public class RandomPassTest {

    @Test
    public void generatePass() throws Exception {
        final int TESTING_LENGTH = 10;
        final int TESTING_SYM_LEN = 3;
        final int TESTING_NUM_LEN = 5;
        HashSet<Character> SYMBOL_LIST = new HashSet<>(Arrays.asList(',','?','~','!','-','_','@','$','*','.','#'));
        HashSet<Character> NUM_LIST = new HashSet<>(Arrays.asList('0','1','2','3','4','5','6','7','8','9'));
        RandomPass rand = new RandomPass(TESTING_LENGTH, TESTING_SYM_LEN,TESTING_NUM_LEN);
        String res = rand.generatePass();
        assertEquals(10, res.length());

        int symbolCount = 0;
        int charCount = 0;
        int numCount = 0;
        for (int i = 0; i < 10; i ++){
            if (SYMBOL_LIST.contains(res.charAt(i)) ){
                symbolCount++;
            }
            if (NUM_LIST.contains(res.charAt(i))){
                numCount++;
            }
            if ((res.charAt(i) >= 97 && res.charAt(i) <= 122) || (res.charAt(i) >= 65 && res.charAt(i) <= 90)){
                System.out.println(res.charAt(i));
                charCount++;
            }
        }
        assertEquals(TESTING_LENGTH, (numCount+symbolCount+charCount));
        assertEquals(TESTING_NUM_LEN, numCount);
        assertEquals(TESTING_SYM_LEN, symbolCount);
        assertEquals(TESTING_LENGTH-TESTING_NUM_LEN-TESTING_SYM_LEN, charCount);
    }

    @Test
    public void randChar() throws Exception {
        RandomPass rand = new RandomPass(10,10,10);
        for (int i = 0; i < 1000; i++){
            char curr = rand.randChar();
            assertTrue((curr >= 97 && curr <= 122) || (curr >= 65 || curr <= 90));
        }
    }

    @Test
    public void randSym() throws Exception {
        RandomPass rand = new RandomPass(10,10,10);
        HashSet<Character> SYMBOL_LIST = new HashSet<>(Arrays.asList(',','?','~','!','-','_','@','$','*','.','#'));
        for (int i = 0; i < 1000; i++) {
            char curr = rand.randSym();
            assertTrue(SYMBOL_LIST.contains(curr));
        }

    }

    @Test
    public void randNum() throws Exception {
        RandomPass rand = new RandomPass(10,10,10);
        HashSet<Character> NUM_LIST = new HashSet<>(Arrays.asList('0','1','2','3','4','5','6','7','8','9'));
        for (int i = 0; i < 1000; i++) {
            char curr = rand.randNum();
            assertTrue(NUM_LIST.contains(curr));
        }
    }

}