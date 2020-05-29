package com.zstu_219.display_window.draw_component;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.List;


/**
 * 用来绘制一个固定的直角坐标系
 * @auther Stiles-JKY
 * @date 2020/5/24-18:24
 */
public class DrawCSYS extends JPanel {

    List<Point2D> points;
    boolean flag = false;//标志当前点是否为最小二乘拟合点
    public void display(List<Point2D> points) {
        this.points = points;
        repaint();
    }

    public void drawLine(List<Point2D> points) {
        this.points = points;
    }

    public void drawNewLine(List<Point2D> points) {
        this.points = points;
        this.flag = true;
        repaint();
    }

    private Graphics2D graphics2D;

    @Override
    public void paint(Graphics g) {
        super.paint(g);//绘制背景,防止被覆盖

        this.graphics2D= (Graphics2D)g;
        graphics2D.setColor(Color.BLACK);//设置画笔颜色
        //x
        graphics2D.drawLine(20,450,450,450);
        graphics2D.drawLine(450,450,445,445);
        graphics2D.drawLine(450,450,445,455);

        //y
        graphics2D.drawLine(20,450,20,20);
        graphics2D.drawLine(20,20,25,25);
        graphics2D.drawLine(20,20,15,25);

        if (!this.flag) {
            paintPoint(points);
        } else {
            paintLine(points);
            this.flag = false;
        }

        System.out.println("paint");
    }

    private void paintPoint(List<Point2D> points) {

        if (points == null) {
            return;
        }

        for (int i = 0; i < points.size(); i++) {
            System.out.println(points.size());
            Point2D point = coordinateConverter(points.get(i));
            double x = point.getX();
            double y = point.getY();
            Ellipse2D circle = new Ellipse2D.Double();
            circle.setFrameFromCenter(x,y,x + 2,y + 2);
            this.graphics2D.setPaint(Color.BLACK);
            this.graphics2D.draw(circle);
        }
    }

    //绘制拟合直线
    private void paintLine(List<Point2D> points) {

        if (points == null) {
            return;
        }


        for (int i = 0; i < 30; i++) {
            //转换坐标
            Point2D point1 = coordinateConverter(points.get(i));
            if (i+1 == points.size()) {
                break;
            }
            Point2D point2 = coordinateConverter(points.get(i + 1));
            Line2D.Double tempLine = new Line2D.Double();
            tempLine.setLine(point1,point2);
            this.graphics2D.draw(tempLine);

        }


    }

    private Point2D coordinateConverter(Point2D point) {

        double x = point.getX();
        double y = point.getY();
        point.setLocation(x + 20,450 - y);

        return point;
    }



}
