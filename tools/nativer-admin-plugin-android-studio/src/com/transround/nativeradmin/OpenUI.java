package com.transround.nativeradmin;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.ui.Messages;
import com.transround.nativeradmin.ui.NativerAdmin;
import com.transround.nativeradmin.util.AndroidXMLUtils;
import com.transround.nativeradmin.util.CommonUtils;
import com.transround.nativeradmin.util.GradleUtils;

import java.awt.*;

/**
 * Created by szeibert on 2014.11.27..
 */
public class OpenUI extends AnAction {
    public void actionPerformed(AnActionEvent e) {
        CommonUtils.setProject(e.getProject());
        AndroidXMLUtils.reset();
        GradleUtils.reset();

        if (!AndroidXMLUtils.isAndroidProjectFolder()) {
            Messages.showErrorDialog(String.format("Nativer works only with Android projects.\n%1$s is not an Android project folder.", e.getProject().getBaseDir().getPath()), "Error: Not Android Project");
        } else {
            NativerAdmin dialog = new NativerAdmin();
            Dimension screenSize = dialog.getToolkit().getScreenSize();
            Dimension dialogSize = new Dimension(900, 500);
            dialog.setBounds((screenSize.width - dialogSize.width) / 2, (screenSize.height - dialogSize.height) / 2, dialogSize.width, dialogSize.height);
            dialog.setTitle("Nativer Admin");
            dialog.setResizable(false);
            dialog.setVisible(true);
        }
    }
}
