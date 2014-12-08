package com.transround.nativeradmin.ui;

import com.intellij.openapi.ui.Messages;
import com.transround.nativeradmin.model.LoginResponse;
import com.transround.nativeradmin.network.JSONService;
import com.transround.nativeradmin.swing.AsyncTask;
import com.transround.nativeradmin.util.Constants;
import com.transround.nativeradmin.util.Session;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

/**
 * Created by szeibert on 2014.11.27..
 */
public class LoginForm extends JPanel {
    private JButton loginButton;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton registerButton;
    private JPanel contentPane;
    private NativerAdmin application;

    public LoginForm(final NativerAdmin application) {
        this.application = application;
        add(contentPane, BorderLayout.WEST);
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                login();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    public void loginUserInSession() {
        usernameField.setText(Session.getInstance().getUser().getUsername());
        passwordField.setText(Session.getInstance().getUser().getPassword());
        login();
    }

    private void login() {
        application.setProgress(true);
        AsyncTask<LoginResponse> loginTask = new AsyncTask<LoginResponse>() {
            @Override
            protected LoginResponse performOperation() throws Exception {
                JSONService<LoginResponse> loginService = new JSONService<LoginResponse>(){};
                return loginService.get(Constants.accountServiceUrl + String.format("login&username=%1$s&password=%2$s", usernameField.getText(), passwordField.getText()));
            }

            @Override
            protected void onComplete() {
               application.setProgress(false);
            }

            @Override
            protected void onSuccess(LoginResponse result) {
                if (result.isSuccess()) {
                    Session.getInstance().setToken(result.getToken());
                    Session.getInstance().setSessid(result.getSessid());
                    application.userLoggedIn();
                } else {
                    Messages.showErrorDialog("Invalid username or password. Please try again", "Error during login");
                }
            }

            @Override
            protected void onFailure(Throwable e) {

            }
        };
        loginTask.run();
    }

    public JButton getRegisterButton() {
        return registerButton;
    }
}
