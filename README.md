Android Dynamic Resource Management: the Nativer SDK
==========================================

Nativer SDK adds dynamic resource management functionality to any Android app:
- Translating apps into any language using smartphones with immediate preview
- Distributing language resources on-the-fly to end users

Nativer SDK integration within 15 minutes
--------------------------------------------------
Only few configuration files (detailed below) have to be changed in order to integrate and utilize the localization services provided by the SDK, i.e. there is no need to change any single code line in your app.

However, if you are using Android Studio, the integration can even be completed easier by using our [Android Studio plugin](https://github.com/Transround/NativerSDK/tree/master/tools/nativer-admin-plugin-android-studio#instructions).
The plugin will guide you through the integration and registration steps and will carry out the necessary configuration file changes. So, if you prefer to use this plugin please follow these [instructions](https://github.com/Transround/NativerSDK/tree/master/tools/nativer-admin-plugin-android-studio#instructions).

In case you prefer to manually complete the necessary configuration changes please follow the description below.

If you would like to get even more details please check our wiki for step-by-step instructions about [How to integrate Nativer SDK](https://github.com/Transround/NativerSDK/wiki/How-to-integrate-Nativer-SDK).
Then visit our [Nativer Developer Self Service site] (http://developer.nativer.com/) for further instructions.

Getting started
===============

Automated installation using Android Studio plugin
--------------------------------------------------
The plugin will guide you through the integration and registration steps and will carry out the necessary configuration file changes, please follow these [instructions](https://github.com/Transround/NativerSDK/tree/master/tools/nativer-admin-plugin-android-studio#instructions).

Manual installation
-------------------
In case you are not using Android Studio or prefer to manually complete the necessary configuration changes please follow the description below.

#### Common steps for Android Studio|Gradle and Eclipse

Declare the Nativer SDK's service in the manifest file in your application:

Copy the following text sequence, and paste it into the **AndroidManifest.xml** file of your project.

```xml
...
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
...
<application
	<!-- Add the following lines to the application section -->
	...
	<service android:name="com.transround.plugin.service.PluginInterfaceService" >
	    <intent-filter>
	        <action android:name="com.transround.tools.PING" />
	
	        <category android:name="com.transround.tools.TRANSLATOR" />
	    </intent-filter>
	</service>
	...
	<activity android:name="com.transround.plugin.activity.RefreshScreen" android:exported="true"/>
	...
</application>
```
You should go through a [simple registration process] (https://github.com/Transround/NativerSDK/blob/master/README.md#android-app-localization---common-steps) after completing installation steps for your development environment in order to utilize multilingual Adnroid app localization service.

#### Installation using Android Studio|Gradle

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

#### Installation using Eclipse

For detailed step-by-step instructions how to integrate Nativer SDK into your app please check our wiki about [How to integrate Nativer SDK](https://github.com/Transround/NativerSDK/wiki/How-to-integrate-Nativer-SDK).
Then visit our [Nativer Developer Self Service site] (http://developer.nativer.com/) for further instructions.

* Requirements
	- Eclipse with ADT bundle
	- AspectJ plugin
	- Android v7 app compat library
* How does it work

	To make your life easier, Nativer SDK uses AspectJ to make the necessary changes in your code instead of you.
	In order to compile code with aspects in Eclipse you need to install the *AspectJ Development Tools* plugin.
	In order to tell Eclipse to use the AJDT features in your Android project you must convert it to an AspectJ project. 	For more details please check our related [wiki page](https://github.com/Transround/NativerSDK/wiki/How-to-integrate-Nativer-SDK#3installing-aspectj).
* Setting up Nativer SDK in your project

	- Set Android Support Library v7 for the SDK and your project
	- Add NativerSDK project and the nativersdkplugin.jar to your Aspect Path
	- After adding the SDK and the jar to the AspectJ's build path also add them to the Java Build Path
	- Declare the Nativer SDK's service in the manifest file in your application

Android app localization - common steps
----------------------------------------
* In order to utilize the Nativer localization service you should go through a simple registration process at our [Developer Self Service Site] (http://nativer01.nativer.com/prod/admin/index.php?route=common/information&information_id=28&menu=devinfo). The main steps of the process are as follows:
    * Register your app in our database
    * Upload the resource files of your app and define the scope of translation

Note: In order to try the translation feature you will also need the Nativer app. The compiled app will warn you that Nativer is needed and will redirect you to the Nativer download page on Google Play.

Sample Multilanguage Android app project
----------------------------------------
You can check how easy to build in the SDK and try the main features of the SDK using our sample [App Localization project] (https://github.com/Transround/NativerSDK/tree/master/nativer-sdk-sample).

Glossary
--------

*Host Application*: Is the original Application of a developer. The App Localization Demo (https://github.com/Transround/NativerSDK/tree/master/nativer-sdk-sample) is an example of a host application. By adding the Nativer SDK to it, becomes NLE (Nativer Localization Enabled) Application.

*NLE Application*: Stands for Nativer Localization Enabled Application. This is an original Application of a developer, in which the Nativer SDK has been embedded.

*Nativer SDK*: A set of functions and libraries, which enable the host Application to become easily translatable in the Nativer Ecosystem.

*Nativer Application*: This is Transround&rsquo;s own application, called Nativer, which implements majority of the functionality of the Nativer Ecosystem, like requesting localizations, downloading ready localizations, translate, proof reading, support, etc.
