package io.binghe.concurrent.chapter07.jol;

import org.openjdk.jol.info.ClassLayout;

/**
 * @author binghe
 * @version 1.0.0
 * @description 分析对象的大小
 */
public class ObjectSizeAnalysis {

    public static void main(String[] args) {
        MyObject obj = new MyObject();
        System.out.println(ClassLayout.parseInstance(obj).toPrintable());
    }
}
