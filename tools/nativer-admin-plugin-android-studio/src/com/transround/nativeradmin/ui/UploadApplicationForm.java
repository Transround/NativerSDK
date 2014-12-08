package com.transround.nativeradmin.ui;

import com.intellij.openapi.ui.Messages;
import com.transround.nativeradmin.model.CategoryDescription;
import com.transround.nativeradmin.model.Language;
import com.transround.nativeradmin.model.ServiceResult;
import com.transround.nativeradmin.model.TrApplication;
import com.transround.nativeradmin.network.JSONService;
import com.transround.nativeradmin.swing.AsyncTask;
import com.transround.nativeradmin.swing.FileListModel;
import com.transround.nativeradmin.util.AndroidXMLUtils;
import com.transround.nativeradmin.util.Constants;
import com.transround.nativeradmin.util.GradleUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by szeibert on 2014.11.27..
 */
public class UploadApplicationForm extends JPanel {
    private JTextField packageField;
    private JList resourcesList;
    private JTextField applicationNameField;
    private JButton registerApplicationButton;
    private JPanel contentPane;
    private JLabel titleText;
    private JLabel requiredPermissionsLabel;
    private JTextField manifestField;
    private JButton updateProjectFilesButton;
    private JList gradleBuildFilesList;
    private JComboBox defaultLanguageComboBox;
    private NativerAdmin application;
    private FileListModel resourceFilesListModel;
    private FileListModel gradleBuildFilesListModel;

    public UploadApplicationForm(final NativerAdmin application) {
        this.application = application;
        add(contentPane, BorderLayout.WEST);
        application.setProgress(true);
        Font font = new Font(titleText.getFont().getFontName(), Font.PLAIN, 20);
        titleText.setFont(font);
        packageField.setText(AndroidXMLUtils.getAndroidPackageName());
        applicationNameField.setText(AndroidXMLUtils.getProjectName());
        try {
            resourceFilesListModel = new FileListModel(AndroidXMLUtils.lookupStringResourceFilesInAndroidProject());
        } catch (IOException e) {
            e.printStackTrace();
        }
        resourcesList.setModel(resourceFilesListModel);

        gradleBuildFilesListModel = new FileListModel(GradleUtils.getGradleBuildFiles());
        gradleBuildFilesList.setModel(gradleBuildFilesListModel);

        StringBuilder requiredPermissionsText = new StringBuilder();
        java.util.List<String> requiredPermissionsList = AndroidXMLUtils.getRequiredPermissions();
        if (requiredPermissionsList.size() > 0) {
            requiredPermissionsText.append("<html>");
            for (int i = 0; i < requiredPermissionsList.size(); i++) {
                if (i != 0) requiredPermissionsText.append("<br/>");
                requiredPermissionsText.append(requiredPermissionsList.get(i));
            }
            requiredPermissionsText.append("</html>");
        } else {
            requiredPermissionsText.append("No additional permissions required.");
        }
        requiredPermissionsLabel.setText(requiredPermissionsText.toString());

        manifestField.setText(AndroidXMLUtils.getAndroidManifest().getAbsolutePath());

        registerApplicationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerApplication();
            }
        });

        updateProjectFilesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateProjectFiles();
            }
        });

        for (Language language : Language.values()) {
            defaultLanguageComboBox.addItem(language);
        }
        defaultLanguageComboBox.setSelectedIndex(Language.getByCode("en").ordinal());
    }

    private void registerApplication() {
        application.setProgress(true);
        AsyncTask<ServiceResult> registerApplicationTask = new AsyncTask<ServiceResult>() {
            @Override
            protected ServiceResult performOperation() throws Exception {
                JSONService<ServiceResult> insertApplicationService = new JSONService<ServiceResult>(){};
                TrApplication application = new TrApplication();
                application.setTrJavaPackage(packageField.getText());
                CategoryDescription cd = new CategoryDescription();
                cd.setName(applicationNameField.getText());
                java.util.List<CategoryDescription> cdList = new ArrayList<CategoryDescription>();
                cdList.add(cd);
                application.setCategoryDescription(cdList);
                ServiceResult result = insertApplicationService.post(Constants.accountServiceUrl + "insertApplication",application);
                if (result.isSuccess()) {
                    JSONService<ServiceResult> uploadFilesService = new JSONService<ServiceResult>(){};
                    ServiceResult uploadResult = uploadFilesService.upload(Constants.uploadUrl, resourceFilesListModel.getFiles());
                    System.out.println(uploadResult.getResult());
                    if (uploadResult.isSuccess()) {
                        JSONService<ServiceResult> processUploadedResourcesService = new JSONService<ServiceResult>(){};
                        ServiceResult processResult = processUploadedResourcesService.get(Constants.accountServiceUrl + "processPreUploadedResources&default_lang_code=" + ((Language)defaultLanguageComboBox.getSelectedItem()).getCode());
                        if (!processResult.isSuccess()) {
                            result = processResult;
                        }
                    } else {
                        result = uploadResult;
                    }
                }
                return result;
            }

            @Override
            protected void onComplete() {
                application.setProgress(false);
            }

            @Override
            protected void onSuccess(ServiceResult result) {
                AndroidXMLUtils.makeChangesToManifest();
                GradleUtils.makeChangesToGradleBuildFiles();
                if (result.isSuccess()) {
                    Messages.showInfoMessage("Your application was successfully registered in Nativer.", "Application Registered");
                    application.applicationRegistered();
                } else {
                    Messages.showErrorDialog("The following error occurred during registration: " + result.getErrorMessage(), "Registration Failed");
                }
            }

            @Override
            protected void onFailure(Throwable e) {
                Messages.showErrorDialog("Exception occurred: " + e.getMessage(), "Error");
            }
        };
        registerApplicationTask.run();
    }

    private void updateProjectFiles() {
        AndroidXMLUtils.makeChangesToManifest();
        GradleUtils.makeChangesToGradleBuildFiles();
        StringBuilder messageBuilder = new StringBuilder();
        messageBuilder.append("The following files were updated:\n");
        messageBuilder.append(AndroidXMLUtils.getAndroidManifest().getAbsolutePath()).append("\n");
        for (File gradleBuildFile : GradleUtils.getGradleBuildFiles()) {
            messageBuilder.append(gradleBuildFile.getAbsolutePath()).append("\n");
        }
        Messages.showInfoMessage(messageBuilder.toString(), "Project Files Updated");
    }
}
