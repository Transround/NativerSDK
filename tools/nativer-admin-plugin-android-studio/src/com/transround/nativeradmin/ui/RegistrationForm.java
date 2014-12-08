package com.transround.nativeradmin.ui;

import com.intellij.openapi.ui.Messages;
import com.intellij.ui.JBColor;
import com.transround.nativeradmin.model.RegisterResponse;
import com.transround.nativeradmin.model.User;
import com.transround.nativeradmin.network.JSONService;
import com.transround.nativeradmin.swing.AsyncTask;
import com.transround.nativeradmin.util.Constants;
import com.transround.nativeradmin.util.Session;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by szeibert on 2014.11.27..
 */
public class RegistrationForm extends JPanel {
    private JButton signupButton;
    private JButton cancelButton;
    private JTextField companyField;
    private JTextField emailField;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JPasswordField passwordConfirmField;
    private JPanel contentPane;
    private JLabel titleText;
    private NativerAdmin application;

    public RegistrationForm(final NativerAdmin application) {
        this.application = application;
        add(contentPane, BorderLayout.WEST);
        application.setProgress(true);
        Font font = new Font(titleText.getFont().getFontName(), Font.PLAIN, 20);
        titleText.setFont(font);
        signupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                signUp();
            }
        });
    }

    public JButton getCancelButton() {
        return cancelButton;
    }

    private void signUp() {
        application.setProgress(true);
        if (validateUser()) {
            AsyncTask<RegisterResponse> registerTask = new AsyncTask<RegisterResponse>() {
                @Override
                protected RegisterResponse performOperation() throws Exception {
                    User user = new User();
                    user.setCompany(companyField.getText());
                    user.setEmail(emailField.getText());
                    user.setPassword(passwordField.getText());
                    user.setUsername(usernameField.getText());
                    Session.getInstance().setUser(user);
                    JSONService<RegisterResponse> registerService = new JSONService<RegisterResponse>(){};
                    return registerService.post(Constants.accountServiceUrl + "register", user);
                }

                @Override
                protected void onComplete() {
                    application.setProgress(false);
                }

                @Override
                protected void onSuccess(RegisterResponse result) {
                    if (result.isSuccess()) {
                        Session.getInstance().getUser().setId(result.getId());
                        Messages.showInfoMessage("Registration was successful.", "Information");
                        application.userSignedUp();
                    } else {
                        Session.getInstance().setUser(null);
                        Messages.showErrorDialog(result.getErrorMessage(), "Error During Registration");
                    }
                }

                @Override
                protected void onFailure(Throwable e) {
                    e.printStackTrace();
                    Messages.showErrorDialog("There was an error during registration. Please try again.", "Error During Registration");
                }
            };
            registerTask.run();
        } else {
            Messages.showErrorDialog("Please fill out the registration form correctly", "Validation Error");
        }
    }

    private boolean validateUser() {
        boolean isValid = true;
        if (companyField.getText().equals("")) {
            isValid = false;
            companyField.setBackground(JBColor.RED);
        }
        if (usernameField.getText().equals("")) {
            isValid = false;
            usernameField.setBackground(JBColor.RED);
        }
        if (emailField.getText().equals("")) {
            isValid = false;
            emailField.setBackground(JBColor.RED);
        }
        if (passwordField.getText().equals("")) {
            isValid = false;
            passwordField.setBackground(JBColor.RED);
        }
        if (passwordConfirmField.getText().equals("")) {
            isValid = false;
            passwordConfirmField.setBackground(JBColor.RED);
        }
        if (!passwordField.getText().equals(passwordConfirmField.getText())) {
            isValid = false;
            passwordField.setBackground(JBColor.RED);
            passwordConfirmField.setBackground(JBColor.RED);
        }
        return isValid;
    }
}
