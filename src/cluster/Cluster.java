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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Oushneek
 */
public class Cluster {

    public void cluster_data(ArrayList<String> keyword, double[][] similarity) {
        ArrayList<Integer> cluster_list = new ArrayList<Integer>();
        for (int i = 0; i < keyword.size(); i++) {
            cluster_list.add(0);
        }
        Connection conn = null;
        Statement stmt = null;
        int cluster_count = 0;
        double max = 0;
        int max_i = 0, max_j = 0;
        int flag = 0;

        do {
            flag = 1;
            max = 0;
            max_i = 0;
            max_j = 0;
            for (int i = 0; i < keyword.size(); i++) {
                for (int j = i + 1; j < keyword.size(); j++) {
                    if (similarity[i][j] > max) {
                        max = similarity[i][j];
                        max_i = i;
                        max_j = j;
                    }
                }
            }
            if (max == 0) {
                break;
            }

            if (cluster_list.get(max_i) == 0 && cluster_list.get(max_j) == 0) {
                cluster_count++;
                cluster_list.set(max_i, cluster_count);
                cluster_list.set(max_j, cluster_count);
            } else if (cluster_list.get(max_i) != 0 && cluster_list.get(max_j) == 0) {
                cluster_list.set(max_j, cluster_list.get(max_i));
            } else if (cluster_list.get(max_j) != 0 && cluster_list.get(max_i) == 0) {
                cluster_list.set(max_i, cluster_list.get(max_j));
            }
            similarity[max_i][max_j] = 0;

        } while (max > 0);

        File afile = new File("F:/infancy/Cluster/Cluster_List.txt");
        try {
            FileWriter fw = new FileWriter(afile, true);
            BufferedWriter bw = new BufferedWriter(fw);

            for (int i = 1; i <= cluster_count; i++) {
                bw.write("\n\tCluster " + i + " : \n");
                for (int j = 0; j < keyword.size(); j++) {
                    if (cluster_list.get(j) == i) {
                        bw.write(keyword.get(j) + "\n");
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
