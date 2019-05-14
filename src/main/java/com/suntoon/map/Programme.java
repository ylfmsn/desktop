package com.suntoon.map;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import javax.swing.*;

/**
 * @ProjectionName desktop
 * @ClassName Programme
 * @Description 程序主入口
 * @Author YueLifeng
 * @Date 2019/4/25 0025下午 3:58
 * @Version 1.0
 */
public class Programme {

    public static void main(String[] args) {

        try{
            //设置本属性将改变窗口边框样式定义
            //BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.osLookAndFeelDecorated;
            BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.translucencyAppleLike;
            org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
            UIManager.put("RootPane.setupButtonVisible", false);
        } catch (Exception e) {
            e.printStackTrace();
        }

        JMapFrame frame = new JMapFrame();
        frame.setVisible(true);
    }
}
