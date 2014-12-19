package com.transround.nativeradmin.util;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by szeibert on 2014.11.27..
 */
public class AndroidXMLUtils extends CommonUtils {

    private static File androidManifest;
    private static List<File> androidResFolders;

    public static File getAndroidManifest() {
        if (androidManifest == null) {
            findAndroidManifest(new File(project.getBaseDir().getPath()));
        }
        return androidManifest;
    }

    public static List<File> getAndroidResFolders() {
        if (androidResFolders == null) {
            androidResFolders = new ArrayList<File>();
            findAndroidResFolders(new File(project.getBaseDir().getPath()));
        }
        return androidResFolders;
    }

    public static void reset() {
        androidResFolders = null;
        androidManifest = null;
    }

    private static void findAndroidResFolders(File folder) {
        for (File f : folder.listFiles()) {
            if (f.getName().equals("res") && f.isDirectory()) {
                androidResFolders.add(f);
                return;
            }
            if (isNonBuildDirectory(f)) {
                findAndroidResFolders(f);
            }
        }
    }

    private static void findAndroidManifest(File folder) {
        for (File f : folder.listFiles()) {
            if (androidManifest != null) {
                return;
            }
            if (isAndroidApplicationManifest(f)) {
                androidManifest = f;
                return;
            }
            if (isNonBuildDirectory(f)) {
                findAndroidManifest(f);
            }
        }
    }

    private static boolean isStringsXMLFile(File file) {
        if (false == file.isDirectory()) {
            String extension = getFileExtension(file);
            if (extension.equals("xml")) {
                try {
                    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                    DocumentBuilder db = dbf.newDocumentBuilder();
                    Document doc = db.parse(file);
                    Element rootElement = doc.getDocumentElement();

                    if (rootElement.getNodeName().equals("resources")) {

                        NodeList children = rootElement.getChildNodes();

                        for (int i = 0; i < children.getLength(); i++) {
                            Node currentChild = children.item(i);
                            if (currentChild instanceof Element && isStringResourceType((Element) currentChild)) {
                                return true;
                            }
                        }
                        return false;

                    } else {
                        return false;
                    }

                } catch (SAXException e) {
                    e.printStackTrace();
                    return false;
                } catch (ParserConfigurationException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    private static boolean isStringResourceType(Element element) {
        for (String stringElement : Constants.stringElementNames) {
            if (stringElement.equals(element.getNodeName())) {
                return true;
            }
        }
        return false;
    }

    private static boolean isAndroidApplicationManifest(File file) {
        if (!file.getName().equals("AndroidManifest.xml")) {
            return false;
        }
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);
            Element rootElement = doc.getDocumentElement();
            if (rootElement.getElementsByTagName("application") == null || rootElement.getElementsByTagName("application").item(0) == null) {
                return false;
            }
            Element applicationElement = (Element) rootElement.getElementsByTagName("application").item(0);
            return applicationElement.getChildNodes().getLength() > 0;
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static ArrayList<File> lookupStringResourceFilesInAndroidProject() throws IOException {
        ArrayList<File> returnValue = new ArrayList<File>();

        for (File resFolder : getAndroidResFolders()) {
            for (File currentResDir : resFolder.listFiles()) {
                // Looking for "values*" folders:
                if (currentResDir.isDirectory() && currentResDir.getName().startsWith("values")) {
                    if (currentResDir.canRead()) {
                        for (File file : currentResDir.listFiles()) {
                            if (isStringsXMLFile(file)) {
                                returnValue.add(file);
                            }
                        }
                    } else {
                        throw new IOException("Error: Cannot read resource files of project. Make sure that you have the appropriate file system permissions.");
                    }
                }
            }
        }

        return returnValue;
    }

    public static boolean isAndroidProjectFolder() {
        return getAndroidManifest() != null && getAndroidManifest().exists();
    }

    public static String getAndroidPackageName() {
        String packageName = null;
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(getAndroidManifest());
            Element rootElement = doc.getDocumentElement();
            packageName = rootElement.getAttribute("package");
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return packageName;
    }

    public static String getProjectName() {
        return project.getName();
    }


    public static void makeChangesToManifest() {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(getAndroidManifest());
            Element rootElement = doc.getDocumentElement();

            // generate required permissions if not already present
            for (String requiredPermission : getRequiredPermissions()) {
                Element permissionElement = doc.createElement("uses-permission");
                permissionElement.setAttribute("android:name", requiredPermission);
                rootElement.insertBefore(permissionElement, rootElement.getFirstChild());
                Comment commentElement = doc.createComment("Permission required by Nativer. Generated by Nativer Admin for Android Studio.");
                rootElement.insertBefore(commentElement, rootElement.getFirstChild());
            }

            Element applicationElement = (Element) rootElement.getElementsByTagName("application").item(0);

            // generate service node if not already present
            NodeList services = applicationElement.getElementsByTagName("service");
            if (!hasElementWithAttribute(services, "android:name", "com.transround.plugin.service.PluginInterfaceService")) {
                Element serviceElement = doc.createElement("service");
                serviceElement.setAttribute("android:name", "com.transround.plugin.service.PluginInterfaceService");
                Element intentFilterElement = doc.createElement("intent-filter");
                Element actionElement = doc.createElement("action");
                actionElement.setAttribute("android:name", "com.transround.tools.PING");
                intentFilterElement.appendChild(actionElement);
                Element categoryElement = doc.createElement("category");
                categoryElement.setAttribute("android:name", "com.transround.tools.TRANSLATOR");
                intentFilterElement.appendChild(categoryElement);
                serviceElement.appendChild(intentFilterElement);
                applicationElement.insertBefore(serviceElement, applicationElement.getFirstChild());
                Comment serviceCommentElement = doc.createComment("Translation Interface Service required by Nativer. Generated by Nativer Admin for Android Studio");
                applicationElement.insertBefore(serviceCommentElement, applicationElement.getFirstChild());
            }

            // generate required activity if not already present
            NodeList activities = applicationElement.getElementsByTagName("activity");
            if (!hasElementWithAttribute(activities, "android:name", "com.transround.plugin.activity.RefreshScreen")) {
                Element activityElement = doc.createElement("activity");
                activityElement.setAttribute("android:name", "com.transround.plugin.activity.RefreshScreen");
                activityElement.setAttribute("android:exported", "true");
                applicationElement.insertBefore(activityElement, applicationElement.getFirstChild());
                Comment activityCommentElement = doc.createComment("Activity required by Nativer. Generated by Nativer Admin for Android Studio");
                applicationElement.insertBefore(activityCommentElement, applicationElement.getFirstChild());
            }

            backupFile(getAndroidManifest());
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            Properties outputProperties = new Properties();
            outputProperties.put(javax.xml.transform.OutputKeys.INDENT, "yes");
            outputProperties.put("{http://xml.apache.org/xslt}indent-amount", "4");
            transformer.setOutputProperties(outputProperties);
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(getAndroidManifest());

            transformer.transform(source, result);

            project.getBaseDir().refresh(false, true);

        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    public static List<String> getRequiredPermissions() {
        return getRequiredPermissions(new File(project.getBaseDir().getPath()));
    }

    public static List<String> getRequiredPermissions(File folder) {
        List<String> requiredPermissionsResult = new ArrayList<String>();

        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(getAndroidManifest());
            Element rootElement = doc.getDocumentElement();

            NodeList permissions = rootElement.getElementsByTagName("uses-permission");
            for (String requiredPermission : Constants.requiredPermissions) {
                if (!hasElementWithAttribute(permissions, "android:name", requiredPermission)) {
                    requiredPermissionsResult.add(requiredPermission);
                }
            }

        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        return requiredPermissionsResult;
    }

    private static boolean hasElementWithAttribute(NodeList nodeList, String attributeName, String attributeValue) {
        for (int i = 0; i < nodeList.getLength(); i++) {
            if (attributeValue.equals(((Element) nodeList.item(i)).getAttribute(attributeName))) {
                return true;
            }
        }
        return false;
    }
}