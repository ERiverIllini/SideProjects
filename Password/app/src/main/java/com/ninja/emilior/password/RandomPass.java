package com.ninja.emilior.password;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Created by emilio on 3/13/17.
 */

public class RandomPass {


    private final char[] SYMBOL_LIST = {',','?','~','!','-','_','@','$','*','.','#'};
    private int charAmount;
    private int passLength;
    private int numAmnt;
    private int symbolAmnt;


    RandomPass (int length, int symbols, int numbers){
        passLength = length;
        numAmnt = numbers;
        symbolAmnt = symbols;
        charAmount = passLength - symbols - numbers;
    }

    /**
     * Generates a random string based on the accepted symbol amount, number amount, and character
     * amount, whose positions are also randomly dictated.
     *
     * @return randomly generated password based on given parameters.
     */
    public String generatePass(){
        ArrayList <Character> output = new ArrayList<>();
        while (numAmnt > 0){
            output.add(randNum());
            numAmnt--;
        }
        while (symbolAmnt > 0){
            output.add(randSym());
            symbolAmnt--;
        }
        while (charAmount > 0){
            output.add(randChar());
            charAmount--;
        }

        Collections.shuffle(output);
        StringBuilder solution = new StringBuilder();
        for (char character : output){
            solution.append(character);
        }
        return solution.toString();
    }

    /**
     * Generates a random character whose numeric value is between 0 and 9.
     *
     * @return random character from 0-9.
     */
    public char randNum(){
        Random rand = new Random();
        char output = (char)(rand.nextInt(10)+'0');
        return output;
    }

    /**
     * Generates a random character from within SYMBOLS_LIST.
     * SYMBOLS_LIST supports (',','?','~','!','-','_','@','$','*','.','#')
     *
     * @return random character in SYMBOLS_LIST
     */
    public char randSym(){
        Random rand = new Random();
        return SYMBOL_LIST[rand.nextInt(SYMBOL_LIST.length)];
    }

    /**
     * Generates a random character between a-z or A-Z;
     *
     * @return random character in the alphabet.
     */
    public char randChar(){
        Random rand = new Random();
        if (Math.random() >= 0.5){
            return (char)(rand.nextInt(26) + 65);
        }
        else{
            return (char)(rand.nextInt(26) + 97);
        }
    }
}
