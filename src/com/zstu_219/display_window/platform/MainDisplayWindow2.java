package com.zstu_219.display_window.platform;

import com.zstu_219.display_window.draw_component.DrawCSYS;
import com.zstu_219.linear_function_package.LinearFunction;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


/**
 * @auther Stiles-JKY
 * @date 2020/5/23-19:12
 */
public class MainDisplayWindow2 extends JFrame {

    // Variables declaration - do not modify
    private JButton generate;
    private JButton paint;
    private JButton repaint;
    private JLabel label_y;
    private JLabel label_x;
    private JTable pointTable;
    private JPanel left_panel;
    private JPanel graph_panel;
    private JScrollPane jScrollPane1;//滚动面板，用于jList
    private JTextField aValue;
    private JTextField bValue;
    private int index = 1;
    private LinearFunction linearFunction;
    private DefaultTableModel pointModel = new DefaultTableModel();

    private List<Point2D> points = new ArrayList<>();//存放当前直线上的点的集合

    /**
     * Creates new form MainDisplayWindow
     */
    public MainDisplayWindow2() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    private void initComponents() {

        left_panel = new JPanel();
        label_y = new JLabel();
        aValue = new JTextField();
        label_x = new JLabel();
        bValue = new JTextField();
        generate = new JButton();
        paint = new JButton();
        jScrollPane1 = new JScrollPane();
        this.pointModel.addColumn("Point");
        this.pointModel.addColumn("X");
        this.pointModel.addColumn("Y");

        pointTable = new JTable(pointModel);
        repaint = new JButton();

        graph_panel = new DrawCSYS();//坐标轴背景


        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        left_panel.setBorder(BorderFactory.createTitledBorder(null,
                "Function", 0, 0,
                new Font("宋体", 1, 12))); // NOI18N
        left_panel.setRequestFocusEnabled(false);

        label_y.setFont(new Font("宋体", 1, 36)); // NOI18N
        label_y.setText("Y = ");

        aValue.setFont(new Font("宋体", 1, 24)); // NOI18N

        //text listener1
        aValue.addActionListener(evt -> aValueActionPerformed(evt));

        label_x.setFont(new Font("宋体", 1, 24)); // NOI18N
        label_x.setText("X +");

        bValue.setFont(new Font("宋体", 1, 24)); // NOI18N
        //text listener2
        bValue.addActionListener(evt -> bValueActionPerformed(evt));

        generate.setText("Generate");

        //generate listener
        generate.addActionListener(evt -> generateActionPerformed(evt));

        paint.setText("paint");

        //paint Listener
        paint.addActionListener(evt -> paintActionPerformed(evt));

        jScrollPane1.setViewportView(pointTable);

        //repaint button
        repaint.setText("repaint");
        //repaint listener
        repaint.addActionListener(evt -> repaintActionPerformed(evt));

        //left_panel layout manager
        GroupLayout left_panelLayout = new GroupLayout(left_panel);
        left_panel.setLayout(left_panelLayout);

        left_panelLayout.setHorizontalGroup(
                left_panelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(left_panelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(left_panelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jScrollPane1)
                                        .addGroup(GroupLayout.Alignment.LEADING, left_panelLayout.createSequentialGroup()
                                                .addGroup(left_panelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(label_y, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(generate, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(aValue, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(left_panelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                        .addGroup(left_panelLayout.createSequentialGroup()
                                                                .addComponent(label_x)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(bValue, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
                                                        .addComponent(paint, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                        .addComponent(repaint, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap(53, Short.MAX_VALUE))
        );

        //left_panel layout
        left_panelLayout.setVerticalGroup(
                left_panelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(left_panelLayout.createSequentialGroup()
                                .addGap(52, 52, 52)
                                .addGroup(left_panelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(label_y, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                                        .addGroup(left_panelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                .addComponent(aValue, GroupLayout.PREFERRED_SIZE, 27, Short.MAX_VALUE)
                                                .addComponent(label_x, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                                                .addComponent(bValue, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                                .addGap(54, 54, 54)
                                .addGroup(left_panelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(paint, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(generate, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                                .addComponent(repaint, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26))
        );


        //paint functional graph panel
        graph_panel.setBorder(BorderFactory.createTitledBorder(null, "Functional_Graph", 0, 0,
                new Font("宋体", 1, 12))); // NOI18N

        //graph panel layout
        GroupLayout graph_panelLayout = new GroupLayout(graph_panel);
        graph_panel.setLayout(graph_panelLayout);

        graph_panelLayout.setHorizontalGroup(
                graph_panelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 473, Short.MAX_VALUE)
        );

        graph_panelLayout.setVerticalGroup(
                graph_panelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 0, Short.MAX_VALUE)
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);

        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(left_panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(graph_panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(graph_panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(left_panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
        );

        pack();//最佳大小
    }

    /**
     * 读取aValue中的值
     *
     * @param evt
     */
    private void aValueActionPerformed(ActionEvent evt) {

        Object source = evt.getSource();
        JTextField aValue = (JTextField) source;
        System.out.println(aValue.getText());
    }


    /**
     * 读取bValue中的值
     *
     * @param evt
     */
    private void bValueActionPerformed(ActionEvent evt) {

        Object source = evt.getSource();
        JTextField aValue = (JTextField) source;
        System.out.println(aValue.getText());
    }

    /**
     * button: generate
     * 生成随机点,每次调用生成30个点，无大小顺序
     *
     * @param evt
     */
    private void generateActionPerformed(ActionEvent evt) {

        String aValue = this.aValue.getText();
        String bValue = this.bValue.getText();
        //for test
        System.out.println(aValue + " & " + bValue);
        this.linearFunction = new LinearFunction(aValue, bValue);//得到当前的linear function

        //需要方法,给model设置值
        setModel();
        this.pointTable.setModel(this.pointModel);
        //使用当前点坐标绘制点
        ((DrawCSYS) graph_panel).display(this.points);//绘制点

    }


    private void setModel() {
        this.points = linearFunction.getPoints();

        for (int i = 0; i < points.size(); i++) {
            double x = points.get(i).getX();
            double y = points.get(i).getY();

            this.pointModel.addRow(new Object[]{index++, x, y});
        }
    }

    /**
     * button: paint
     * 按照table中得到的随机点绘制函数图像
     *
     * @param evt
     */
    private void paintActionPerformed(ActionEvent evt) {

        ((DrawCSYS) graph_panel).display(this.points);
        //测试是否调用方法
        System.out.println("paint event");

    }


    /**
     * button: repaint
     * 按照修改后的list中的值绘制函数图像
     *
     * @param evt
     */
    private void repaintActionPerformed(ActionEvent evt) {
        List<Point2D> newPoints = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            Object strX = this.pointModel.getValueAt(i, 1);
            System.out.println(strX);
            Object strY = this.pointModel.getValueAt(i, 2);

            newPoints.add(new Point2D.Double((double)strX, (double)strY));
        }

        List<Point2D> leastSquareFitPoints = this.linearFunction.leastSquareFit(newPoints);//使用修改后的点做最小二乘直线拟合
        ((DrawCSYS) graph_panel).drawNewLine(leastSquareFitPoints);//传入新点,在原点的基础上画线

        System.out.println("repaint event");
    }


}
