package com.liuyu.common.file;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * ClassName: FileUtil <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 18-8-9 上午9:21 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
public class FileUtil {

    public static boolean dirNotExistAndCreate(File file) {
        if (file.isDirectory() && file.exists()) {
            file.mkdirs();
        }
        return true;
    }

    public static String fileSuffix(String fileName) {
        Path path = Paths.get(fileName);
        return fileSuffix(path);
    }

    public static String fileSuffix(Path filepath) {
        String fileName = filepath.getFileName().toString();
        int idx = fileName.lastIndexOf('.');
        return fileName.substring(idx);
    }


    public static String getPath(String path) {
        if (path.endsWith("/") || path.endsWith("\\")) {
            path = path.substring(0, path.length() - 1);
        }
        return path;
    }
}
