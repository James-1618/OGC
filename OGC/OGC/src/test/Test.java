package test;

import njupt.domain.BoundingBox;
import util.CalSim;
import util.MysqlDB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.TreeMap;

public class Test {
    // minx maxx miny maxy
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("璇疯緭鍏ユ偍闇�瑕佸尮閰嶇殑minx");
        double iminx = sc.nextDouble();
        System.out.println("璇疯緭鍏ユ偍闇�瑕佸尮閰嶇殑miny");
        double iminy = sc.nextDouble();
        System.out.println("璇疯緭鍏ユ偍闇�瑕佸尮閰嶇殑maxx");
        double imaxx = sc.nextDouble();
        System.out.println("璇疯緭鍏ユ偍闇�瑕佸尮閰嶇殑maxy");
        double imaxy = sc.nextDouble();
        BoundingBox iBoundingBox = new BoundingBox(iminx, iminy, imaxx, imaxy);
        MysqlDB db = new MysqlDB();
        ResultSet rs = db.executeQuery("select * from layersinfo");
        TreeMap<Double, String> map = new TreeMap<Double, String>();
        try {
            while (rs.next()) {
                double max = 0;
                String L_spatialEx = rs.getString("L_spatialEx");
//                String dates = rs.getString("L_temporal");
//                String[] date = dates.split(";");
                String url = rs.getString("url");
                String[] a = L_spatialEx.split(";");
                for (String i : a) {
                    String[] coor = i.split(",");
                    try {
                        double minx = Double.parseDouble(coor[0]);
                        double maxx = Double.parseDouble(coor[1]);
                        double miny = Double.parseDouble(coor[2]);
                        double maxy = Double.parseDouble(coor[3]);
                        BoundingBox boundingBox = new BoundingBox(minx, miny, maxx, maxy);
                        Double sim = CalSim.calSim(iBoundingBox, boundingBox);
                        if (sim > max) {
                            max = sim;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                map.put(max, url);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.close();
        }

        for (Double key : map.keySet()) {
            if (key != 0) {
                System.out.println(map.get(key));
                System.out.println(key);
                System.out.println();
            }
        }

    }
}
