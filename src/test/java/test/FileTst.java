package test;

import org.junit.jupiter.api.Test;

import java.io.*;

/**
 * Created on 2018-09-07 16:02
 * by @author JeramTough
 */
public class FileTst {

    @Test
    public void test() {

        File file = new File("E:\\Codes\\IdeaCodes\\JtlogForMaven\\src\\main\\java\\com\\jeramtough\\jtlog\\recorder\\RecorderHandler.java");
        System.out.println(file.exists());
        if (file.exists()){
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                FileOutputStream fileOutputStream=new FileOutputStream(new File("C:\\Users\\11718\\Desktop" +
                        "\\New folder (2)\\test1.txt"));
                byte[] readBytes = new byte[255];
                while (fileInputStream.read(readBytes) > 0) {
                    fileOutputStream.write(readBytes);
                }
                fileOutputStream.write("fdsfsdfsdfsdfdsafsdfsdafsdfsdfsdfsdfa".getBytes());

                fileInputStream.close();
                fileOutputStream.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
