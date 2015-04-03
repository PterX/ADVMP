package buwai.android.shell.base;

import java.io.*;

/**
 * Created by buwai on 25/4/1.
 */
public class Utils {

    /**
     * 是否是Windows操作系统。
     * @return true：是Windows操作系统。false：不是Windows操作系统。
     */
    public static boolean isWindowsOS() {
        String os = System.getProperty("os.name");
        if(os.toLowerCase().startsWith("win")){
            return true;
        } else {
            return false;
        }
    }

    /**
     * 复制整个文件夹内容
     *
     * @param oldPath String 原文件路径 如：c:/fqf
     * @param newPath String 复制后路径 如：f:/fqf/ff
     * @return boolean
     */
    public static void copyFolder(String oldPath, String newPath) throws IOException {

        (new File(newPath)).mkdirs(); // 如果文件夹不存在 则建立新文件夹
        File a = new File(oldPath);
        String[] file = a.list();
        File temp = null;

        for (int i = 0; i < file.length; i++) {
            if (oldPath.endsWith(File.separator)) {
                temp = new File(oldPath + file[i]);
            } else {
                temp = new File(oldPath + File.separator + file[i]);
            }

            if (temp.isFile()) {
                try (FileInputStream input = new FileInputStream(temp);
                     FileOutputStream output = new FileOutputStream(newPath
                        + "/" + (temp.getName()).toString())) {

                    byte[] b = new byte[1024 * 5];
                    int len;
                    while ((len = input.read(b)) != -1) {
                        output.write(b, 0, len);
                    }
                    output.flush();
                    output.close();
                    input.close();
                }
            }
            if (temp.isDirectory()) {// 如果是子文件夹
                copyFolder(oldPath + "/" + file[i], newPath + "/" + file[i]);
            }
        }


    }

}
