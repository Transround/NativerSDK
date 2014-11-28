Nativer SDK
===========


About
-----

Nativer SDK adds dynamic resource management functionality to any Android app so it can be translated to any language with Nativer service. 

Please note that you only have to change the following configuration files in order integrate and utilize the localization services provided by the SDK, i.e. there is no need to change any single code line in your app.

For detailed step-by-step instructions how to integrate Nativer SDK into your app please check our wiki about [How to integrate Nativer SDK](https://github.com/Transround/NativerSDK/wiki/How-to-integrate-Nativer-SDK).
Then visit our [Nativer Developer Self Service site] (http://developer.nativer.com/) for further instructions.

Installation (common steps) - AndroidManifest.xml
-------------------------------------

Depending on the development environment or compiler you prefer to use there are some specific installation steps (described later in this readme), however independently of the IDE/compiler there are some common installation steps, too.

Declare the Nativer SDK's service in the manifest file in your application:

Copy the following text sequence, and paste it into the **AndroidManifest.xml** file of your project.

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

Installation (using Gradle)
---------------------

Nativer-sdk uses Aspectj so you have to use [Aspectdroid](https://github.com/Transround/aspectdroid) plugin to compile your android project with AspectJ's compiler.
If you would like to learn more about why AspectJ is used please check our wiki about [How to integrate Nativer SDK](https://github.com/Transround/NativerSDK/wiki/How-to-integrate-Nativer-SDK).

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

Installation (using Eclipse)
----------------------

The following is a brief overview of how to integrate the Nativer SDK into your app using the Eclipse development environment.

For detailed step-by-step instructions how to integrate Nativer SDK into your app please check our wiki about [How to integrate Nativer SDK](https://github.com/Transround/NativerSDK/wiki/How-to-integrate-Nativer-SDK).
Then visit our [Nativer Developer Self Service site] (http://developer.nativer.com/) for further instructions.

### Requirements

- Eclipse with ADT bundle
- AspectJ plugin
- Android v7 app compat library
- Google API (minimum API level 15)

### How does it work

To make your life easier, Nativer SDK uses AspectJ to make the necessary changes in your code instead of you.
In order to compile code with aspects in Eclipse you need to install the *AspectJ Development Tools* plugin.

### General work considerations

Check the build target of ALL projects and make sure that you are building with Google APIs, and for at least API level 15.
Make sure that a Java JDK of version 6 is installed.

### Installing AspectJ

The AspectJ Development Tools is a standard Eclipse plugin which can be downloaded and installed by Eclipse the same way you installed the Android Development Tools.

### Converting your Android project to an AspectJ project

In order to tell Eclipse to use the AJDT features in your Android project you must convert it to an AspectJ project. 

### Setting up Nativer SDK in your project

Setting up Nativer SDK requires just a little bit more effort than adding an ordinary Android library to your project.
Check out Nativer SDK from Github (https://github.com/Transround/NativerSDK).

#### Import NativerSDK to Eclipse

Import the NativerSDK project into your workspace.
- File/Import... and then choose "Existing Android Code Into Workspace". Please do not forget to check the "Copy project into workspace" option.
- Please note that in case you checked out Nativer SDK into your workspace directly then you have to use File/Import... and then "General/Existing Projects into Workspace" 

#### Set Android Support Library v7 for the SDK and your project

Please add Android Support Library v7 to the Android library dependency to  Nativer SDK project. (Right click on Nativer SDK project > Properties > Android > Add...)  
Do the same procedure for your project.

#### There are some further project configuration steps you have to carry out
* Add NativerSDK to your Project
* Add NativerSDK project and the nativersdkplugin.jar to your Aspect Path
* After adding the SDK and the jar to the AspectJ's build path also add them to the Java Build Path
* Declare the Nativer SDK's service in the manifest file in your application

#### Declare the Nativer SDK's service in the manifest file in your application. 
Please check *Installation (common steps)* above for further details.

#### Protecting parts of your application from the Nativer SDK

If there are some parts of your application which you don't want to be translated for some reason then you can tell Nativer to avoid them.

You can use the ``` @DontTouchThis ``` annotation to tell the SDK which part of your code should be left intact. You can use this annotation on a whole class or on a method. 

#### Build and test your app project.

It should now support all Nativer capabilities.

Install the app to a test device (or emulator) and start it.
The app will display an intro page that will help you to try Nativer localization features.

Note: In order to try the translation feature you will also need the Nativer app. The compiled app will warn you that Nativer is needed and will redirect you to the Nativer download page on Google Play.


Glossary
--------

*Host Application*: Is the original Application of a developer. The App Localization Demo (https://github.com/Transround/LocalizationDemoDev) is an example of a host application. By adding the Nativer SDK to it, becomes NLE (Nativer Localization Enabled) Application

*NLE Application*: Stands for Nativer Localization Enabled Application. This is an original Application of a developer, in which the Nativer SDK has been embedded.

*Nativer SDK*: A set of functions and libraries, which enable the host Application to become easily translatable in the Nativer Ecosystem

*Nativer Application*: This is Transround&rsquo;s own application, called Nativer, which implements majority of the functionality of the Nativer Ecosystem, like requesting localizations, downloading ready localizations, translate, proof reading, support, etc.
