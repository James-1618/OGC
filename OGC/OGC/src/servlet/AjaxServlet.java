package servlet;

import njupt.domain.BoundingBox;
import util.CalSim;
import util.MyCmp;
import util.MysqlDB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.TreeMap;

@WebServlet("/ajaxServlet")
public class AjaxServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String result = request.getParameter("value");
        System.out.println(result);
        String arr[] = result.split(" ");
        String ver = arr[2].split(",")[0];
        String hor = arr[2].split(",")[1];
        String minx_str = hor.split("~")[0];
        double iminx = Double.parseDouble(minx_str.substring(0, minx_str.length() - 1));
        String maxx_str = hor.split("~")[1];
        double imaxx = Double.parseDouble(maxx_str.substring(0, maxx_str.length() - 1));
        String miny_str = ver.split("~")[0];
        double iminy = Double.parseDouble(miny_str.substring(0, miny_str.length() - 1));
        String maxy_str = ver.split("~")[1];
        double imaxy = Double.parseDouble(maxy_str.substring(0, maxy_str.length() - 1));
        BoundingBox iBoundingBox = new BoundingBox(iminx, iminy, imaxx, imaxy);
        MysqlDB db = new MysqlDB();
        ResultSet rs = db.executeQuery("select * from layersinfo_mineral");
        TreeMap<Double, String> map = new TreeMap<Double, String>(new MyCmp());
        try {
            while (rs.next()) {
                double max = 0;
                String L_spatialEx = rs.getString("L_spatialEx");
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
                     //   e.printStackTrace();
                    }
                }
                map.put(max, url);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.close();
        }

        String retString = "";
        for (Double key : map.keySet()) {
            if (key != 0) {
                retString = retString.concat(map.get(key) + ",");
                retString = retString.concat( String.valueOf(key) + ",");
            }
        }
      //  System.out.println(retString);
        response.getWriter().write(retString);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
