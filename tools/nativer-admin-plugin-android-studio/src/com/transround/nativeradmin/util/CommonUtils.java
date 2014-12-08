package com.transround.nativeradmin.util;

import com.intellij.openapi.project.Project;
import com.intellij.xml.actions.xmlbeans.FileUtils;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by szeibert on 2014.12.03..
 */
public class CommonUtils {

    protected static Project project;

    public static Project getProject() {
        return project;
    }

    public static void setProject(Project project) {
        AndroidXMLUtils.project = project;
    }

    protected static String getFileExtension(File file) {
        if (file.isDirectory()) {
            return null;
        } else {
            String fileName = file.getName();
            String[] nameParts = fileName.split("\\.");
            return nameParts[nameParts.length - 1];
        }
    }

    protected static boolean isNonBuildDirectory(File folder) {
        if (!folder.isDirectory()) {
            return false;
        }
        for (String buildDirectory : Constants.buildDirectories) {
            if (buildDirectory.equals(folder.getName())) {
                return false;
            }
        }
        return true;
    }

    protected static String readContentFromFile(File file) {
        String content = null;
        try {
            FileReader reader = new FileReader(file);
            char[] chars = new char[(int) file.length()];
            reader.read(chars);
            content = new String(chars);
            reader.close();
        } catch (IOException e) {

        }
        return content;
    }

    protected static void writeContentToFile(String content, File file) throws IOException {
        FileWriter writer = new FileWriter(file);
        writer.write(content);
        writer.close();
    }

    protected static void backupFile(File file) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd_HHmmss");
        FileUtils.copyFile(file, new File(String.format("%1$s.%2$s.bak", file.getAbsolutePath(), simpleDateFormat.format(new Date()))));
    }
}
