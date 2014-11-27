Nativer SDK
===========


About
-----

Nativer SDK adds dynamic resource management functionality to any Android app so it can be translated to any language with Nativer service. 

If you would ilke to integrate Nativer SDK into your app please visit the [Nativer Developer Self Service site] (http://developer.nativer.com/) for further instructions.


Usage (Gradle)
--------------

Nativer-sdk uses Aspectj so you have to use [Aspectdroid](https://github.com/Transround/aspectdroid) plugin to compile your android project with AspectJ's compiler.

* Add aspectdroid plugin

	Complete the **buildscript** section of your project's build file:
	```groovy
	buildscript {
	    ext {	
		aspectjVersion = '1.8.2'
	    }
	    repositories {
		mavenCentral()
		// repository for aspectdroid plugin
	        maven {
	            url "http://www.transround.com/repositories/public/"
	        }
		
	    }
	    dependencies {
	        classpath 'com.android.tools.build:gradle:0.13.+'	
	        classpath 'com.transround:aspectdroid:1.0.+'
	    }
	}
	```

	Apply the `aspectdroid` plugin:
	```groovy
	apply plugin: 'aspectdroid'
	```

* Add nativer-sdk library
	
	Use our maven repository:
	```groovy
	repositories {
	    mavenCentral()
	    maven {
	     url "http://www.transround.com/repositories/public/"
	    }
	}
	```

	Add nativer-sdk dependency:
	```groovy
	dependencies {
	   compile 'com.transround:nativer-sdk:1.0.+'    
	}
	```

<p></p>

<h1>Usage (Eclipse)</h1>

<p></p>

The aim of this readme is to describe how to integrate the App Localization Demo app with the Nativer SDK in order to try the localization capabilities of Nativer.

If you would ilke to integrate Nativer SDK into your app please visit the [Nativer Developer Self Service site] (http://developer.nativer.com/) for further instructions.

<p></p>

<h1>Requirements</h1>

<p></p>

- Eclipse with ADT bundle
- AspectJ plugin
- Android v7 app compat library
- Google API (minimum API level 15)

<p></p>
<p></p>

<h1>1.How does it work</h1>

<p>To make your life easier, Nativer SDK uses AspectJ to make the necessary changes in your code instead of you. AspectJ is the Java implementation of Aspect Oriented Programming (AOP). The idea of AOP is to inject code fragments to well-defined places in an application without modifying the source code. AspectJ uses code weaving technology to inject additional code at compile time which means your source code remains intact but your compiled application will contain everything required.</p>

<p>In order to compile code with aspects in Eclipse you need to install the <em>AspectJ Development Tools</em> plugin.</p>

<h1>2.General work considerations</h1>

<p>In order to avoid disturbing your other projects, or making irreversible damages, we suggest you to create a new, clean workspace, where you will have to import your Android project to.</p>

<p>Import your application project(s) into the newly created Workspace. (File / New / Android project from existing code.). Do not forget to select the &ldquo;Copy projects into workspace&rdquo; checkbox, in order to keep your original sources intact.</p>

<p>Check the build target of ALL projects and make sure that you are building with Google APIs, and for at least API level 15.</p>

<p>Make sure that a Java JDK of version 6 is installed.</p>

<p></p>

<h1>3.Installing AspectJ</h1>

<p>The AspectJ Development Tools is a standard Eclipse plugin which can be downloaded and installed by Eclipse the same way you installed the Android Development Tools.</p>

Select **"Help/Install New Software..."** in Eclipse and click on 
**Add...** The Repository Location depends on which version of Eclipse you use:</p>

<ul>
	<li>In case of Eclipse Helios: <a href="http://download.eclipse.org/tools/ajdt/36/update">http://download.eclipse.org/tools/ajdt/36/update</a></li>
	<li>In case of Eclipse Indigo: <a href="http://download.eclipse.org/tools/ajdt/37/update">http://download.eclipse.org/tools/ajdt/37/update</a></li>
	<li>In case of Eclipse Juno: <a href="http://download.eclipse.org/tools/ajdt/42/update">http://download.eclipse.org/tools/ajdt/42/update</a></li>
</ul>

<p>Select the newly created update site and check <strong><em>&bdquo;AspectJ Development Tools (Required)&rdquo;</em></strong> and &bdquo;<strong><em>Other AJDT Tools (Optional)&rdquo;</em></strong> then click on <strong><em>&bdquo;Next&rdquo;</em></strong> and follow the directions given by Eclipse.</p>

<p><img alt="" src="http://nativer01.nativer.com/prod/image/data/IntegrationDoc/Install_AspectJ.png" style="height:509px; width:539px" /></p>

<p><strong><em>Notes:</em></strong></p>

<p><strong><em>1.</em></strong><em> Alternatively you can go to </em><a href="http://www.eclipse.org/ajdt/downloads/"><em>http://www.eclipse.org/ajdt/downloads/</em></a><em> to download the AJDT plugin as a zip file and use that as a normal update site.</em></p>

<p><strong><em>2</em></strong><em>. Due to lack of official AspectJ version, the latest Kepler 4.3 release of Eclipse is actually not supported by Nativer. As soon as AspectJ for Kepler becomes available, it will be validated by us, and communicated via our website</em></p>

<p></p>

<h1>4.Converting your Android project to an AspectJ project</h1>

<p>In order to tell Eclipse to use the AJDT features in your Android project you must convert it to an AspectJ project. To do so, right click on your project and select <strong><em>&bdquo;Configure/Convert to AspectJ Project&hellip;&rdquo;</em></strong> from the popup menu.</p>

<p><img alt="" src="http://nativer01.nativer.com/prod/image/data/IntegrationDoc/ConvertProjectToAspectJ.png" style="height:119px; width:605px" /></p>

**Note:** Don't worry, a project can have multiple natures in Eclipse, so your project keeps its Android nature and you can undo this change easily in the future should you change your mind.

<p></p>

<h1>5.Setting up Nativer SDK in your project</h1>

<p></p>

<p>Setting up Nativer SDK requires just a little bit more effort than adding an ordinary Android library to your project.</p>

Check out Nativer SDK from Github (https://github.com/Transround/NativerSDK).

**Import NativerSDK to Eclipse**

Import the NativerSDK project into your workspace.
- File/Import... and then choose "Existing Android Code Into Workspace". Please do not forget to check the "Copy project into workspace" option.
- Please note that in case you checked out Nativer SDK into your workspace directly then you have to use File/Import... and then "General/Existing Projects into Workspace" 

<p><strong>Set Android Support Library v7 for the SDK and your project</strong></p>

In case you need instructions on how to set library support please see the following link: https://developer.android.com/tools/support-library/setup.html#libs-with-res

Please add Android Support Library v7 to the Android library dependency to  Nativer SDK project. (Right click on Nativer SDK project > Properties > Android > Add...)  

<p></p>

<p>Do the same procedure for your project.</p>

<h2>Add NativerSDK to your Project.</h2>

<p>For this, right-click your project, and go to Properties. Select Android on the left of the screen. On the lower part of the screen select Add, and choose NativerSDK from the list. Please be sure you DO NOT CHECK the &ldquo;Is Library&rdquo; option. If ready, press OK.</p>

<p><img alt="" src="http://nativer01.nativer.com/prod/image/data/IntegrationDoc/AddNativerSDKToProject.png" style="height:598px; width:598px" /></p>

<p></p>

<p><strong>Add NativerSDK project and the nativersdkplugin.jar to your Aspect Path.</strong></p>

<p>Right-click your project and go to Properties. Select AspectJ Build on the left of the screen. Than on the main part of the Properties screen select the Aspect Path tab.</p>

<p>Press the Add Project... button, then select the NativerSDK project and click OK.</p>

<p>Press the Add External JARs button, you will find the nativersdkplugin.jar in NativerSDK project folder. Select this file and press OK. Your Properties screen will look very similar to the picture below. After you added the SDK project and the jar file, press OK to go back to the projects list.</p>

<p><img alt="" src="http://nativer01.nativer.com/prod/image/data/IntegrationDoc/AddNativerSDKandNativerJARToAspectJ.png" style="height:598px; width:598px" /></p>

<p><strong>After adding the SDK and the jar to the AspectJ's build path also add them to the Java Build Path.</strong></p>

<p>Please check Order and Export tab of the Java Build Path. Right-click to your project, go to the Properties menu. On the left select Java Build Path, then switch to the Order and Export tab. Please make sure that NativerSDK and the nativersdkplugin.jar are checked, and their order is like in the picture below:</p>

<p><img alt="" src="http://nativer01.nativer.com/prod/image/data/IntegrationDoc/JavaBuildPathOrderandExport.png" style="height:598px; width:598px" /></p>

<p>Press OK, then clean the NativerSDK project and your project as well.</p>

<p>For this use the Project / Clean... in the upper menubar of Eclipse for cleaning the SDK and your project. Select Clean projects selected below, check your project and the NativerSDK then press OK.</p>

<h2>Declare the Nativer SDK's service in the manifest file in your application:</h2>

<p>Copy the following text sequence, and paste it into the AndroidManifest.xml file of your project.</p>

```xml
<service android:name="com.transround.plugin.service.PluginInterfaceService" >
    <intent-filter>
        <action android:name="com.transround.tools.PING" />

        <category android:name="com.transround.tools.TRANSLATOR" />
    </intent-filter>
</service>

<activity android:name="com.transround.plugin.activity.RefreshScreen" />
```

Please be careful, and make sure the above text is copied into your manifest file before the ``` </application>``` closing tag declaration.

Also make sure you do not paste the text inside another application or service, or any other declaration.

Please also make sure you have the following text outside the section:

```xml
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
```

<p></p>

<h1>6.Protecting parts of your application from the Nativer SDK</h1>

<p>If there are some parts of your application which you don't want to be translated for some reason then here is how you can tell Nativer to avoid them.</p>

<p>You can use the ``` @DontTouchThis ``` annotation to tell the SDK which part of your code should be left intact. You can use this annotation on a whole class or on a method. For example if you want a specific Activity not to be modified then you can annotate your Activity class like this:</p>

```java
…
import com.transround.plugin.aspect.TransroundAspect.DontTouchThis;

@DontTouchThis
public class MyProtectedActivity extends Activity {
       @Override
       public void onCreate(Bundle savedInstanceState) {
             super.onCreate(savedInstanceState);
…
       }
}
```

<p>This way nothing will be affected by the SDK in this Activity.</p>

<p>You can also annotate a method to protect an Adapter for example when you don&rsquo;t want the data in your Spinner changed:</p>

```java
@DontTouchThis

protected void createMyContents() {

…

     mySpinner.setAdapter(myAdapter);

}
```

<p>This way myAdapter won't be affected by the plugin but everything else will be.</p>

<p>&nbsp;</p>

<h1>7. Build and test the demo app project.</h1>

<p></p>

<p>It should now support all Nativer capabilities.</p>

<p>Install the app to a test device (or emulator) and start it.
The app will display an intro page that will help you to try Nativer localization features.

<p></p>

Note: In order to try the translation feature you will also need the Nativer app. The demo app will warn you that Nativer is needed and will redirect you to the Nativer download page on Google Play.

</p>

<h1>Glossary</h1>

<p><b>Host Application</b>: Is the original Application of a developer. The App Localization Demo (https://github.com/Transround/LocalizationDemoDev) is an example of a host application. By adding the Nativer SDK to it, becomes NLE (Nativer Localization Enabled) Application</p>

<p><b>NLE Application</b>: Stands for Nativer Localization Enabled Application. This is an original Application of a developer, in which the Nativer SDK has been embedded.</p>

<p><b>Nativer SDK</b>: A set of functions and libraries, which enable the host Application to become easily translatable in the Nativer Ecosystem</p>

<p><b>Nativer Application</b>: This is Transround&rsquo;s own application, called Nativer, which implements majority of the functionality of the Nativer Ecosystem, like requesting localizations, downloading ready localizations, translate, proof reading, support, etc.</p>
