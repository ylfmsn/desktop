package com.suntoon.test;

import java.io.*;

/**
 * @ProjectionName desktop
 * @ClassName readTxt
 * @Description TODO
 * @Author YueLifeng
 * @Date 2019/5/8 0008上午 11:22
 * @Version 1.0
 */
public class readTxt {

    public static void main(String[] args) {

        File file = new File("E:\\Untitled-1.txt");
        File file1 = new File("E:\\Untitled-2.txt");

        if(file.isFile() && file.exists()){
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                FileWriter fileWriter = new FileWriter(file1);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

                StringBuffer sb = new StringBuffer();
                String text = null;
                while((text = bufferedReader.readLine()) != null){
                    sb.append("libs\\" + text + "\n");
                }

                System.out.println(sb.toString());
            } catch (Exception e) {
                // TODO: handle exception
            }
        }




    }





}
