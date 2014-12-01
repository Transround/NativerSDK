Nativer SDK
===========

About
-----

Nativer SDK adds dynamic resource management functionality to any Android app so it can be translated to any language with Nativer service. 

Please note that you only have to change the following configuration files in order integrate and utilize the localization services provided by the SDK, i.e. there is no need to change any single code line in your app.

For detailed step-by-step instructions how to integrate Nativer SDK into your app please check our wiki about [How to integrate Nativer SDK](https://github.com/Transround/NativerSDK/wiki/How-to-integrate-Nativer-SDK).
Then visit our [Nativer Developer Self Service site] (http://developer.nativer.com/) for further instructions.

Getting started
===============

Installation
------------

#### Common steps

Depending on the development environment or compiler you prefer to use there are some specific installation steps (described later in this readme), however independently of the IDE/compiler there are some common installation steps, too.

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
	<activity android:name="com.transround.plugin.activity.RefreshScreen" />
	...
</application>
```

#### Installation (using Gradle)

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

#### Installation (using Eclipse)

The following is a brief overview of how to integrate the Nativer SDK into your app using the Eclipse development environment.

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
	- Declare the Nativer SDK's service in the manifest file in your application. 

* In order to utilize the Nativer localization service you should go through a simple registration process at our [Developer Self Service Site] (http://nativer01.nativer.com/prod/admin/index.php?route=common/information&information_id=28&menu=devinfo). The main steps of the process are as follows:
    * Register your app in our database
    * Upload the resource files of your app and define the scope of translation

Note: In order to try the translation feature you will also need the Nativer app. The compiled app will warn you that Nativer is needed and will redirect you to the Nativer download page on Google Play.

Sample project
--------------
You can check how easy to build in the SDK and try the main features of the SDK using our sample App Localization project (https://github.com/Transround/NativerSDK/tree/master/nativer-sdk-sample).

Glossary
--------

*Host Application*: Is the original Application of a developer. The App Localization Demo (https://github.com/Transround/LocalizationDemoDev) is an example of a host application. By adding the Nativer SDK to it, becomes NLE (Nativer Localization Enabled) Application

*NLE Application*: Stands for Nativer Localization Enabled Application. This is an original Application of a developer, in which the Nativer SDK has been embedded.

*Nativer SDK*: A set of functions and libraries, which enable the host Application to become easily translatable in the Nativer Ecosystem

*Nativer Application*: This is Transround&rsquo;s own application, called Nativer, which implements majority of the functionality of the Nativer Ecosystem, like requesting localizations, downloading ready localizations, translate, proof reading, support, etc.
