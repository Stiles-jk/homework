package com.zstu_219.pinpong;

import java.util.*;

/**
 * 甲乙丙，3人打乒乓球，谁打输谁下，已知甲打了9场，乙打了6场，问达成这个条件丙打的场次有多少种情况，丙最多打了几场。
 *
 * @auther Stiles-JKY
 * @date 2020/5/25-10:23
 */
public class PingPongGame {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //获取其中两人打的场次
//        int a = scanner.nextInt();
//        int b = scanner.nextInt();
        int a = 60;
        int b = 100;
        List<Integer> cMatchTimes = getCMatchTimes(a, b);
        System.out.println(cMatchTimes);
    }

    private static List<Integer> getCMatchTimes(int a, int b) {
        //a表示甲的次数，b表示乙的次数
        //存放比赛次数key = match times;value = c times
        List<Integer> counts = new ArrayList<>();
        int max = Math.max(a, b);
        int min = Math.min(a, b);
        //得到c有可能打的次数的范围
        int cTimesMax = min * 2 + 1;
        int cTimesMin = min / 2;
        int loseTime = 0;//丙输的次数,也代表甲乙打的场次
        int mid = 0;

        //这里表示丙为最大次数，则从丙一直赢开始计算
        for (int i = cTimesMax; i >= max; i--) {
            mid = max;//让位 max - i; mid = max; min = min;
            //从最多开始，如果输一场，则甲乙之间多打一场
            if (2 * (min - loseTime) == mid || 2 * (min - loseTime) + 1 == mid) {
                counts.add(i);
            }

            loseTime++;
            //循环一次，将max,min重置为a,b之间的大小关系
            max = Math.max(a, b);
            min = Math.min(a, b);
        }


        //已知甲 = max 打了9场，乙 = min打了 6 场，求丙打了几场，且丙在 max 和 min之间
        for (int i = max - 1; i >= min; i--) {
            //甲和乙最多打6场，
            for (int j = 0; j <= 6; j++) {
                //                j代表甲和乙打的场数
                int x = max - j;//甲和丙打的场数
                int y = min - j;//乙和丙打的场数
                if ((x + y == i) && (y * 2 == x || y * 2 + 1 == x)) {
                    counts.add(i);
                }
            }
        }

        //这是 甲 乙 丙 的场次，i代表丙打的场次
        for (int i = min - 1; i >= cTimesMin; i--) {
            //甲和乙打的次数
            for (int j = 0; j <= 6; j++) {
                int x = max -j;//甲和丙打的次数
                int y = min - i;//乙和丙打的次数
                if ((x + y == i)) {
                    counts.add(i);
                }
            }
        }


        return counts;
    }


    private static void getCMatchTimesByViolent(int a, int b) {

    }


}
