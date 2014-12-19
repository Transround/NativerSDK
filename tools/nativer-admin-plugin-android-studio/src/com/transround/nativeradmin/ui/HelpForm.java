package com.transround.nativeradmin.ui;

import com.transround.nativeradmin.network.JSONService;
import com.transround.nativeradmin.swing.AsyncTask;
import com.transround.nativeradmin.util.Constants;

import javax.swing.*;
import java.awt.*;

/**
 * Created by szeibert on 2014.12.04..
 */
public class HelpForm extends JPanel {
    private JPanel contentPane;
    private JEditorPane editorPane;
    private JButton closeButton;
    private NativerAdmin application;

    public HelpForm(final NativerAdmin application) {
        this.application = application;
        add(contentPane, BorderLayout.WEST);
        application.setProgress(true);
        AsyncTask<String> getHelpTextTask = new AsyncTask<String>() {
            @Override
            protected String performOperation() throws Exception {
                JSONService<String> getHelpTextService = new JSONService<String>(){};
                return getHelpTextService.getRawHTML(Constants.helpUrl);
            }

            @Override
            protected void onComplete() {
                application.setProgress(false);
            }

            @Override
            protected void onSuccess(String result) {
                editorPane.setContentType("text/html");
                editorPane.setText(result);
            }

            @Override
            protected void onFailure(Throwable e) {

            }
        };
        getHelpTextTask.run();
    }


    public JButton getCloseButton() {
        return closeButton;
    }
}
