package com.suntoon.test;

import java.io.File;

/**
 * @ProjectionName desktop
 * @ClassName Tile2LL
 * @Description TODO
 * @Author YueLifeng
 * @Date 2019/5/16 0016下午 5:29
 * @Version 1.0
 */
public class Tile2LL {

    static double tile2lon(int x, int z) {
        return x / Math.pow(2.0, z) * 360.0 - 180;
    }

    static double tile2lat(int y, int z) {
        double n = Math.PI - (2.0 * Math.PI * y) / Math.pow(2.0, z);
        return Math.toDegrees(Math.atan(Math.sinh(n)));
    }

    public static void main(String[] args) {

        File file = new File("E://18");   //同一zoom 18级

        if (file.isDirectory()) {

            File[] files = file.listFiles();    //所有的x

            File xfile = files[files.length - 1];     //最大的x

            File[] yfiles = xfile.listFiles();    //所有的y

            File yminfile = yfiles[0];    //小的y
            File ymaxfile = yfiles[1];     //大的y

            // 两个y的名称
            Integer ymin = Integer.valueOf(yminfile.getName().substring(0, yminfile.getName().lastIndexOf(".")));
            Integer ymax = Integer.valueOf(ymaxfile.getName().substring(0, ymaxfile.getName().lastIndexOf(".")));

            // 算纬度
            Double ydmin = tile2lat(ymin, 18);
            Double ydmax = tile2lat(ymax, 18);

            if (ymin - ymax < 0 && ydmin.compareTo(ydmax) < 0) {
                System.out.println("tms");
            } else {
                System.out.println("google");
            }
        }
    }



}
