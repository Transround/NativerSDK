package com.transround.nativeradmin.ui;

import com.transround.nativeradmin.network.JSONService;
import com.transround.nativeradmin.swing.AsyncTask;
import com.transround.nativeradmin.util.Constants;

import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

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

        editorPane.setEditorKit(JEditorPane.createEditorKitForContentType("text/html"));
        editorPane.setEditable(false);

        editorPane.addHyperlinkListener(new HyperlinkListener() {
            public void hyperlinkUpdate(HyperlinkEvent e) {
                if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
                    if (Desktop.isDesktopSupported()) {
                        try {
                            URI uri = e.getURL().toURI();
                            Desktop.getDesktop().browse(uri);
                        } catch (URISyntaxException e1) {
                            e1.printStackTrace();
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    }
                }
            }
        });

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
