package com.transround.nativeradmin.swing;

import javax.swing.*;
import javax.swing.event.ListDataListener;
import java.io.File;
import java.util.List;

/**
 * Created by szeibert on 2014.12.01..
 */
public class FileListModel implements ListModel {
    private List<File> files;

    public FileListModel(List<File> files) {
        this.files = files;
    }
    @Override
    public int getSize() {
        return files.size();
    }

    @Override
    public Object getElementAt(int index) {
        return files.get(index).getAbsolutePath();
    }

    @Override
    public void addListDataListener(ListDataListener l) {

    }

    @Override
    public void removeListDataListener(ListDataListener l) {

    }

    public List<File> getFiles() {
        return files;
    }
}
