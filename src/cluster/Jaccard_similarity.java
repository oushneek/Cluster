/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cluster;

import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *
 * @author Oushneek
 */
public class Jaccard_similarity {
    
    public void getsimilarity(ArrayList<String> keyword){
        
        
       double similarity_test[][] = new double [keyword.size()][keyword.size()];
       for(int i=0;i<keyword.size();i++){
           for(int j=0;j<keyword.size();j++){
               similarity_test[i][j]=0;
           }
       }
//        for(int i=0;i<keyword.size();i++){
//            for(int j=0;j<keyword.size();j++){
//                similarity.get(i).
//            }
//        }
        // save the similarity in a 2D arraylist
//        String a="dc app";
//        String b="dc metro app";
        int[] lens=new int[2];
//        lens=matchinglength(a,b);
//        System.out.println("matching length ="+lens[0]);
        
        
        for(int i=0;i<keyword.size();i++){
            String thisword=keyword.get(i);
            for(int j=i+1;j<keyword.size();j++){
                String comp=keyword.get(j);
                lens=matchinglength(thisword,comp);
                int totlen=lens[1];
                double sim=(double)(lens[0])/(double)(totlen);
                similarity_test[i][j]=sim;
            }
        }
        Cluster data=new Cluster();
        data.cluster_data(keyword, similarity_test);
       
//        for(int i=0;i<keyword.size();i++){
//            for(int j=0;j<keyword.size();j++){
//                System.out.print(similarity_test[i][j]+" -- ");
//            }
//            System.out.println("\n");
//        }
    }

    private int []matchinglength(String a, String b) {
        
        int result[]=new int[2];
        result[0]=0;
        result[1]=a.length()+b.length();
        
        if (a.contains(b)){
            result[0]=b.length();
            result[1]-=result[0];
        }
        else if(b.contains(a)){
            result[0]=a.length();
            result[1]-=result[0];
        }
        else{
            StringTokenizer c = new StringTokenizer(a, " ");
            String withoutspace_a="";
            String withoutspace_b="";
            ArrayList<String> tokens=new ArrayList<String>();
            while(c.hasMoreTokens()){
                String next=c.nextToken();
                tokens.add(next);
                withoutspace_a+=next;
            }
            int templen=0;
            for(int i=0;i<tokens.size();i++){
                if(tokens.get(i).contains(b))
                    templen=b.length();
                else if(b.contains(tokens.get(i))){
                    templen+=tokens.get(i).length();
                }
            }
            result[0]=templen;
            StringTokenizer d = new StringTokenizer(b, " ");
            while(d.hasMoreTokens()){
                withoutspace_b+=d.nextToken();
            }
            result[1]=withoutspace_a.length()+withoutspace_b.length()-result[0];
        
        }
        return result;
    }
    
    
    
}
