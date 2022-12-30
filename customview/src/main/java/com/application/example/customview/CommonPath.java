package com.application.example.customview;

import android.graphics.Path;

/**
 * @author: est7
 * @date: 2022/12/29
 */
public class CommonPath {
    /**
     * n角星路径
     *
     * @param num 几角星
     * @param R   外接圆半径
     * @param r   内接圆半径
     * @return n角星路径
     */
    public static Path nStarPath(int num, float R, float r) {
        Path path = new Path();
        float perDeg = 360 / num;
        float degA = perDeg / 2 / 2;
        float degB = 360 / (num - 1) / 2 - degA / 2 + degA;

        path.moveTo((float) (Math.cos(rad(degA + perDeg * 0)) * R + R * Math.cos(rad(degA))), (float) (-Math.sin(rad(degA + perDeg * 0)) * R + R));
        for (int i = 0; i < num; i++) {
            path.lineTo((float) (Math.cos(rad(degA + perDeg * i)) * R + R * Math.cos(rad(degA))), (float) (-Math.sin(rad(degA + perDeg * i)) * R + R));
            path.lineTo((float) (Math.cos(rad(degB + perDeg * i)) * r + R * Math.cos(rad(degA))), (float) (-Math.sin(rad(degB + perDeg * i)) * r + R));
        }
        path.close();
        return path;
    }

    /**
     * 角度制化为弧度制
     *
     * @param deg 角度
     * @return 弧度
     */
    public static float rad(float deg) {
        return (float) (deg * Math.PI / 180);
    }


    public static Path commonPath() {
        Path path = new Path();
        path.moveTo(10, 10);
        path.lineTo(20,10);
        path.lineTo(20,20);
        path.lineTo(10,20);
//        path.lineTo(50,50);
        path.close();
        return path;
    }
}
