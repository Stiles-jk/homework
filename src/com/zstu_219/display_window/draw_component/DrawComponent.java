package com.zstu_219.display_window.draw_component;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

/**
 * 用来绘制直角坐标系
 * @auther Stiles-JKY
 * @date 2020/5/23-20:25
 */
public class DrawComponent extends JComponent {

    private static final int DEFAULT_WIDTH = 400;
    private static final int DEFAULT_HEIGHT = 400;


    @Override
    protected void paintComponent(Graphics g) {

        Graphics2D g2D = (Graphics2D)g;//转为Graphics2D

        //draw a rectangle
        double leftX = 100;
        double leftY = 100;
        double width = 200;
        double height = 150;
        Rectangle2D rect = new Rectangle2D.Double(leftX,leftY,width,height);
        g2D.draw(rect);//draw this rectangle

        //draw the enclosed ellipse
        Ellipse2D ellipse = new Ellipse2D.Double();//绘制空椭圆
        ellipse.setFrame(rect);//设定外接矩形
        g2D.draw(ellipse);

        //draw a diagonal line      start point              end point
        g2D.draw(new Line2D.Double(leftX,leftY,leftX + width,leftY + height));

        //draw a circle with same center
        double centerX = rect.getCenterX();
        double centerY = rect.getCenterY();
        double radius = 150;

        Ellipse2D circle = new Ellipse2D.Double();
        circle.setFrameFromCenter(centerX,centerY,centerX + radius,centerY + radius);
        g2D.setPaint(Color.BLUE);//调整绘图颜色
        g2D.draw(circle);

        //绘制一个填充的矩形
        Rectangle2D rectFill = new Rectangle2D.Double();
        rectFill.setFrameFromDiagonal(new Point2D.Double(200,200),new Point2D.Double(300,300));
        g2D.setPaint(Color.YELLOW);
        g2D.fill(rectFill);

    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(DEFAULT_WIDTH,DEFAULT_HEIGHT);
    }
}
