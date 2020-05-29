package com.zstu_219.linear_function_package;

import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import java.awt.geom.Point2D;
import java.util.List;

/**
 * table的数据模型
 *
 * @auther Stiles-JKY
 * @date 2020/5/24-22:04
 */
public class LinearFunctionTableModel extends AbstractTableModel {

    private LinearFunction linearFunction;
    public static final int POINT = 0;
    public static final int X_COLUMN = 1;
    public static final int Y_COLUMN = 2;
    //表头                         点的编号 x坐标 y坐标
    private String[] columnNames = {"Point", "X", "Y"};

    //表中的初始数据源
    private Object[][] pointsTable = new Object[30][3];

    //获取当前的线性函数
    public LinearFunctionTableModel(LinearFunction linearFunction) {

        this.linearFunction = linearFunction;
        int index = 0;
        List<Point2D> points = linearFunction.getPoints();
        for (int i = 0; i < pointsTable.length; i++) {
            this.pointsTable[i][0] = index++;
            this.pointsTable[i][1] = (points.get(i)).getX();
            this.pointsTable[i][2] = (points.get(i)).getY();
        }
    }


    //用来更新当前的线性函数
    public LinearFunction getLinearFunction() {
        return linearFunction;
    }

    public void setLinearFunction(LinearFunction linearFunction) {
        this.linearFunction = linearFunction;
    }

    /**
     * 返回当前某一列的数据模型
     *
     * @param columnIndex
     * @return
     */
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return getValueAt(0, columnIndex).getClass();
    }

    /**
     * 修改表格中某个单元格的数据
     * @param aValue
     * @param rowIndex
     * @param columnIndex
     */
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        pointsTable[rowIndex][columnIndex] = aValue;
        fireTableCellUpdated(rowIndex,columnIndex);
    }

    /**
     * 获取当前table的行数
     *
     * @return
     */
    @Override
    public int getRowCount() {
        return this.pointsTable.length;
    }

    /**
     * 获取当前的列数
     *
     * @return
     */
    @Override
    public int getColumnCount() {
        return this.columnNames.length;
    }

    /**
     * 返回当前数据模型中的某行某列的数据
     *
     * @param rowIndex
     * @param columnIndex
     * @return
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return pointsTable[rowIndex][columnIndex];
    }

    //可修改列
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == X_COLUMN || columnIndex == Y_COLUMN;
    }

}
