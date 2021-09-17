package com.geek.course;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class HelloClassLoader extends ClassLoader {


    private static final String FILE_NAME = "Hello.xlass";

    public static void main(String[] args) throws Exception {
        Class<?> helloClass = new HelloClassLoader().loadClass(FILE_NAME);
        for (Method method : helloClass.getMethods()) {
            System.out.println(method.getName());
        }
        for (Field field : helloClass.getFields()) {
            System.out.println(field.getName());
        }
        Method helloMethod = helloClass.getMethod("hello");
        Object helloObject = helloClass.getDeclaredConstructor().newInstance();
        helloMethod.invoke(helloObject);
    }

    /**
     * find class
     * 1. read file to bytes
     * 2. decode bytes
     * 3. convert bytes to class
     *
     * @param name
     * @return class
     * @throws ClassNotFoundException
     */
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(name);
        try {
            // 读取数据
            int length = inputStream.available();
            byte[] byteArray = new byte[length];
            inputStream.read(byteArray);
            // 转换数据
            decode(byteArray);
            // 转换类
            return defineClass("Hello", byteArray, 0, byteArray.length);


        } catch (IOException e) {
            throw new ClassNotFoundException(name, e);
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * decode bytes (y = 255 - x)
     *
     * @param byteArray
     */
    private static void decode(byte[] byteArray) {
        for (int i = 0; i < byteArray.length; i++) {
            byteArray[i] = (byte) (255 - byteArray[i]);
        }
    }

}