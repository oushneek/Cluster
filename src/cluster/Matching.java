/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cluster;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Oushneek
 */
public class Matching {

    public void match(ArrayList<String> keyword) {
//        for(int i=0;i<keyword.size();i++){
//            System.out.println(keyword.get(i));
//        }

        HashMap<String, Integer> percentage = new HashMap<String, Integer>();
        HashMap<String, HashMap<String, Integer>> matchings = new HashMap<String, HashMap<String, Integer>>();
        for (int i = 0; i < keyword.size(); i++) {
            String test_word = keyword.get(i);
            System.out.println("        "+keyword.get(i));
            percentage.clear();
            int percent = 0;
            for (int j = i + 1; j < keyword.size(); j++) {
                System.out.println("                        "+keyword.get(j));
                String test_word_2=keyword.get(j);
                int large=0,small=0;
                String large_word,small_word;
                if(test_word.length()>test_word_2.length()){
                    large=test_word.length();
                    small=test_word_2.length();
                    large_word=keyword.get(i);
                    small_word=test_word_2;
                }
                else{
                    large=test_word_2.length();
                    small=test_word.length();
                    large_word=test_word_2;
                    small_word=keyword.get(i);
                }
                int count=0;
                for(int x=0;x<small;x++){
                    for(int y=0;y<large;y++){
                        if(small_word.charAt(x)==large_word.charAt(y))
                            count++;
                    }
                }
                System.out.println("count="+count);
                
                if (test_word.contains(keyword.get(j)) || keyword.get(j).contains(test_word) || count >2) {
                    percent = calculate_percentage(test_word, keyword.get(j));
                    percentage.put(keyword.get(j), percent);
                }
            }
            Set set = percentage.entrySet();
            Iterator iterator = set.iterator();
            while (iterator.hasNext()) {
                Map.Entry mentry = (Map.Entry) iterator.next();
                System.out.println(mentry.getKey() + "  =   " + mentry.getValue());
            }

            matchings.put(test_word, percentage);
            
        }

    }

    private int calculate_percentage(String test_word, String test_word_2) {

        int len1 = test_word.length();
        int len2 = test_word_2.length();
        int larger, smaller;
        if (len1 > len2) {
            larger = len1;
            smaller = len2;
        } else {
            larger = len2;
            smaller = len1;
        }
        int percent = (smaller * 100) / larger;

        return percent;
    }

}
