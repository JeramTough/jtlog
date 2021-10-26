package com.jeramtough.jtlog.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 11718
 * on 2017  October 13 Friday 23:34.
 */
public class MyStringUtil {

    public static String splitTextByCounterOfRow(String originalText, int limitNumber) {

        if (limitNumber <= 0) {
            return originalText;
        }

        String[] texts = originalText.split("\n");
        List<String> newTexts = new ArrayList<>();
        StringBuilder newText = new StringBuilder();
        int position;
        int howManySection;
        for (String text : texts) {
            if (text.length() > limitNumber) {
                position = 0;
                howManySection = text.length() / limitNumber + 1;
                for (int i = 0; i < howManySection; i++) {
                    if (i != howManySection - 1) {
                        //if this per isn't last section
                        newTexts.add(text.substring(position, position + limitNumber));
                        position = position + limitNumber;
                    } else {
                        newTexts.add(text.substring(position));
                    }
                }
            } else {
                newTexts.add(text);
            }
        }

        for (int i = 0; i < newTexts.size(); i++) {
            newText.append(newTexts.get(i));
            if (i != newTexts.size() - 1) {
                newText.append("\n");
            }
        }

        return newText.toString();
    }


    public static String getLogo() {
        //        text.append("\nWELCOME TO USE JtLog\n");

        String text = "\n" +
                "    []   [][][]   []        [][]     [][][]  \n" +
                "    []     []     []       []  []   []    [] \n" +
                "    []     []     []       []  []   []       \n" +
                "    []     []     []       []  []   []  [][] \n" +
                "[]  []     []     []       []  []   []    [] \n" +
                " [][]      []     [][][]    [][]    [][][][] \n";
        return text;
    }
}
