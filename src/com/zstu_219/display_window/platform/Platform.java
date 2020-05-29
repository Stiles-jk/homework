package com.zstu_219.display_window.platform;

import javax.swing.*;
import java.awt.*;

/**
 * @auther Stiles-JKY
 * @date 2020/5/23-19:51
 */
public class Platform {

    public static void main(String[] args) {

        //invokeLater方法接收一个Runnable接口
        EventQueue.invokeLater(()->new MainDisplayWindow2().setVisible(true));
    }
}
