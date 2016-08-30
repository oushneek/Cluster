/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cluster;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Oushneek
 */
public class Starts_at {

    public void stringMatch(ArrayList<String> keywords) {
        int count =1;
        int flag=0;
        ArrayList<Integer> cluster_list = new ArrayList<Integer>();
        for(int i=0;i<keywords.size();i++)
            cluster_list.add(0);
        File afile = new File("F:/infancy/Cluster/Cluster_List.txt");

        try {
            FileWriter fw = new FileWriter(afile, true);
            BufferedWriter bw = new BufferedWriter(fw);

            for (int i = 0; i < keywords.size(); i++) {
                String word = keywords.get(i);
                flag=0;
                for (int j = 0; j < keywords.size(); j++) {
                    if (keywords.get(j).startsWith(word) && cluster_list.get(j)==0) {
                      //  bw.write("\nCluster "+count+" : "+keywords.get(j)+"\n");
                        cluster_list.set(j, count);
                        flag=1;
                    }
                }
                if(flag==1){
                    count++;
                }
            }
//            for(int i=0;i<cluster_list.size();i++)
//                System.out.println(cluster_list.get(i));
//            
            for(int i=1;i<count;i++){
                bw.write("\n\tCLUSTER "+i+" : \n");
                for(int j=0;j<keywords.size();j++){
                    if(cluster_list.get(j)==i){
                        bw.write(keywords.get(j)+"\n");
                    }
                }
            }
            
            bw.flush();
            bw.close();
            fw.close();
        } catch (IOException Ex) {
            System.out.println("Exception");
        }
    }
}
