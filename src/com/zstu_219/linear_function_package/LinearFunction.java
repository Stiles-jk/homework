package com.zstu_219.linear_function_package;

import java.awt.geom.Point2D;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 该类代表一个 linear function，提供两个接口
 * 1.生成一个已知linear function 的10个随机点
 * 2.对已知点，使用最小二乘拟合为一条 linear function
 * @auther Stiles-JKY
 * @date 2020/5/24-19:25
 */
public class LinearFunction {

    private double a;
    private double b;

    private List<Point2D> pointList = new ArrayList<>();//用来存放所有生成的linearFunction的点

    public LinearFunction() {}

    public LinearFunction(String x, String y) {
        this.a = (double) s2Int(x);
        this.b = (double) s2Int(y);
        generateRandomPoint();
    }

    public void setA(double a) {
        this.a = a;
    }

    public void setB(double b) {
        this.b = b;
    }

    public void setPointList(List<Point2D> pointList) {
        this.pointList = pointList;
    }

    /**
     * 将String转为int
     *
     * @param str
     * @return
     */
    private int s2Int(String str) {

        if (str == null) {
            throw new IllegalArgumentException("请输入正确的数字");
        }
        return Integer.parseInt(str);
    }

    //默认生成10个点
    private void generateRandomPoint() {
        Random randomNums = new Random();

        for (int i = 0; i < 30; i++) {
            double seed = randomNums.nextInt(100);
            DecimalFormat df = new DecimalFormat("0.00");
            double x = Double.valueOf(df.format(seed));
            double y = this.a * x + this.b;

            this.pointList.add(new Point2D.Double(x, y));
        }
    }

    //获得最小二乘拟合直线的点
    private List<Point2D> generateRandomPoint(double a,double b) {

        List<Point2D> newPoints = new ArrayList<>();
        Random randomNums = new Random();
        for (int i = 0; i < this.pointList.size(); i++) {
            //随机数待完善
            double seed = randomNums.nextInt(100);
            DecimalFormat df = new DecimalFormat("0.00");
            double x = Double.valueOf(df.format(seed));
            double y = a * x + b;
            newPoints.add(new Point2D.Double(x,y));
        }

        return newPoints;
    }

    public List<Point2D> getPoints() {
        return this.pointList;
    }

    /**
     * 利用从jList中读取到的点数据，使用最小二乘法重新拟合直线，并求得相关系数r
     *
     * @param points
     */
    public List<Point2D> leastSquareFit(List<Point2D> points) {

        double a = 0;
        double b = 0;
        
        int size = points.size();
        System.out.println(size);
        double A = 0;
        double B = 0;
        double C = 0;
        double D = 0;

        for (int i = 0; i < points.size(); i++) {
            Point2D cur = points.get(i);
            double x = cur.getX();
            double y = cur.getY();
            A += x * x;
            B += x;
            C += x * y;
            D += y;
        }

        //计算a和b
        double denominator = size * A - B * B;
        if (denominator != 0) {//防止分母为0
            a = (size * C - B * D) / denominator;
            b = (A * D - C * B) / denominator;

        } else { //分母为0，表示 i = xi
            a = 1;
            b = 0;
        }

        //计算相关系数r
        double r = 0;
        double xMean = B / size; // x的平均值
        double yMean = D / size; // y的平均值

        double rMolecule = 0;
        double sumXX = 0;
        double sumYY = 0;
        for (int i = 0; i < size; i++) {
            Point2D cur = points.get(i);
            double x = cur.getX();
            double y = cur.getY();
            //相关系数分子
            rMolecule += (x - xMean) * (y - yMean);

            sumXX += (x - xMean) * (x - xMean);
            sumYY += (y - yMean) * (y - yMean);
        }
        double rDenominator = Math.sqrt(sumXX) * Math.sqrt(sumYY);

        r = rMolecule / rDenominator;//求出相关系数，可以用于判断数据是否相关

        //使用求出的拟合直线的值，生成新的点
        List<Point2D> newPoints = this.generateRandomPoint(a, b);
        return newPoints;
    }
}
