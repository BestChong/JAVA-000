package com.geet.time.week01;


import java.lang.reflect.Method;


public class HelloClassLoader extends ClassLoader {

    private static final String FILE_PATH = "C:/Users/liuchong/Desktop/Hello.xlass";
    private static final String CLASS_NAME = "Hello";
    private static final String METHOD_NAME = "hello";

    /**
     * 重写父类方法，返回Class对象
     *
     * @param name
     * @return
     * @throws ClassNotFoundException
     */
    @Override
    public Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] bytes = FileUtil.getBytes(FILE_PATH);
        return defineClass(name, bytes, 0, bytes.length);
    }

    public static void main(String[] args) {
        HelloClassLoader classLoader = new HelloClassLoader();
        try {
            Class<?> clazz = classLoader.findClass(CLASS_NAME);
            Method method = clazz.getMethod(METHOD_NAME);
            method.invoke(clazz.newInstance());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
