package io.binghe.concurrent.chapter09;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @author binghe
 * @version 1.0.0
 * @description Unsafe案例
 */
public class UnsafeTest {

    private static final Unsafe unsafe = getUnsafe();

    private static long staticNameOffset = 0;
    private static long memberVariableOffset = 0;

    private static String staticName = "binghe_001";
    private String memberVariable = "binghe_001";

    static {
        try {
            staticNameOffset = unsafe.staticFieldOffset
                    (UnsafeTest.class.getDeclaredField("staticName"));
            memberVariableOffset = unsafe.objectFieldOffset
                    (UnsafeTest.class.getDeclaredField("memberVariable"));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        UnsafeTest unSaveTest = new UnsafeTest();
        System.out.println("修改前的值如下:");
        System.out.println("staticName=" + staticName + ", memberVariable=" + unSaveTest.memberVariable);

        unsafe.putObject(UnsafeTest.class, staticNameOffset, "binghe_static");
        unsafe.compareAndSwapObject(unSaveTest, memberVariableOffset, "binghe_001", "binghe_variable");

        System.out.println("修改后的值如下:");
        System.out.println("staticName=" + staticName + ", memberVariable=" + unSaveTest.memberVariable);
    }

    private static Unsafe getUnsafe() {
        Unsafe unsafe = null;
        try {
            Field singleoneInstanceField = Unsafe.class.getDeclaredField("theUnsafe");
            singleoneInstanceField.setAccessible(true);
            unsafe = (Unsafe) singleoneInstanceField.get(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return unsafe;
    }
}
