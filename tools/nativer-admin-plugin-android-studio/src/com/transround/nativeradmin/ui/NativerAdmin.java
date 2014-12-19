package com.transround.nativeradmin.ui;

import com.intellij.openapi.projectRoots.Sdk;
import com.intellij.openapi.roots.ProjectRootManager;
import com.intellij.openapi.ui.Messages;
import com.transround.nativeradmin.OpenUI;
import com.transround.nativeradmin.model.Eula;
import com.transround.nativeradmin.network.JSONService;
import com.transround.nativeradmin.swing.AsyncTask;
import com.transround.nativeradmin.swing.ImagePanel;
import com.transround.nativeradmin.util.CommonUtils;
import com.transround.nativeradmin.util.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class NativerAdmin extends JDialog {
    private JPanel contentPane;
    private JPanel leftPanel;
    private JPanel rightPanel;
    private JTextPane textPane;
    private JProgressBar progressBar;
    private ImagePanel imagePanel;
    private JScrollPane scrollPane;
    private JPanel rootPanel;
    private LoginForm loginForm;
    private RegistrationForm registrationForm;
    private UploadApplicationForm uploadApplicationForm;
    private HelpForm helpForm;

    public NativerAdmin() {
        setContentPane(contentPane);
        setModal(true);
        loginForm = new LoginForm(this);
        registrationForm = new RegistrationForm(this);
        uploadApplicationForm = new UploadApplicationForm(this);
        helpForm = new HelpForm(this);

        loginForm.getRegisterButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setLeftComponent(registrationForm);
            }
        });
        registrationForm.getCancelButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setLeftComponent(loginForm);
            }
        });

        setLeftComponent(loginForm);

        progressBar.setIndeterminate(true);
        progressBar.setVisible(false);
        AsyncTask<Eula> getEulaTask = new AsyncTask<Eula>() {
            @Override
            protected Eula performOperation() throws Exception {
                JSONService<Eula> eulaService = new JSONService<Eula>(){};
                return eulaService.get(Constants.eulaUrl);
            }

            @Override
            protected void onComplete() {
                progressBar.setVisible(false);
            }

            @Override
            protected void onSuccess(Eula result) {
                textPane.setContentType("text/html");
                textPane.setText(result.getText());
                textPane.setCaretPosition(0);
                leftPanel.updateUI();
            }

            @Override
            protected void onFailure(Throwable e) {
                Messages.showErrorDialog("Couldn't get EULA text." + e.getMessage(), "Error");
                e.printStackTrace();
            }
        };
        getEulaTask.run();
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    public void setProgress(boolean progress) {
        progressBar.setVisible(progress);
    }

    public void setLeftComponent(Component component) {
        leftPanel.removeAll();
        leftPanel.add(component, BorderLayout.WEST);
        leftPanel.updateUI();
    }

    public void setRightComponent(Component component) {
        rightPanel.removeAll();
        rightPanel.add(component, BorderLayout.WEST);
        rightPanel.updateUI();
    }

    private void createUIComponents() {
        imagePanel = new ImagePanel(OpenUI.class.getResourceAsStream("/images/nativerLogo.png"));
    }

    public void userLoggedIn() {
        rootPanel.removeAll();
        rootPanel.setLayout(new BorderLayout());
        rootPanel.add(uploadApplicationForm, BorderLayout.WEST);
        uploadApplicationForm.invalidate();
        uploadApplicationForm.updateUI();
        rootPanel.invalidate();
        rootPanel.updateUI();
    }

    public void userSignedUp() {
        setLeftComponent(loginForm);
        loginForm.loginUserInSession();
    }

    public void applicationRegistered() {
        CommonUtils.rebuildProject();
        rootPanel.removeAll();
        rootPanel.setLayout(new BorderLayout());
        //helpForm.setPreferredSize(new Dimension(870, 360));
        helpForm.getCloseButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        rootPanel.add(helpForm, BorderLayout.WEST);
        rootPanel.updateUI();
    }
}
