package com.geet.time.week01;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FileUtil {
    private FileUtil(){};

    public static byte[] getBytes(String filePath) {
        FileInputStream fileInputStream = null;
        byte[] bytesArray = null;
        try {
            File file = new File(filePath);
            bytesArray = new byte[(int) file.length()];
            //read file into bytes[]
            fileInputStream = new FileInputStream(file);
            fileInputStream.read(bytesArray);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        for (int i = 0; i < bytesArray.length; i++) {
            bytesArray[i] = (byte) (255 - bytesArray[i]);
        }
        return bytesArray;
    }
}
