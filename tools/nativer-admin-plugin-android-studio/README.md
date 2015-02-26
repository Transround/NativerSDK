Enabling your users to use their languages within your app: It’s less than 20 minutes of your time to let users learn and practice languages while translating and using your app.
Android on-device resource localization
=======================================

Adds dynamic string resource management features enabling:

* in-context community translation,
* on-the-fly localized string resource download,
* gesture driven language change by shaking the phone

Supports 70+ languages including Chinese, Right-to-left languages

Enrolling your app
------------
In Android Studio choose File -> Settings -> Plugins -> Browse repositories -> Android On-Device Resource Localization and install it. 

![](./doc/images/1_install_plugin.png)

Restart Android studio.

After restart the Nativer Admin plugin will be activated in the right pane providing a registration and integration wizard for the Nativer service. Registering has a 90 days trial period – no commitment from your side. 

![](./doc/images/4_register_new_account_2.png)

You need to register yourself once, and each app you want to enroll. The wizard guide you through the registration and the SDK integration process of the current project. After successful registration and resource upload - the backend service generates 70+ language resource files for your app.

Testing the integration
------------
Generate a new APK and load it on your phone. (See note for Android Studio 1.1)

Checking the translatability: 

After you start your app - language manager screen appears: choose developer mode from the menu.

![](./doc/images/6_welcome_ui.png)
![](./doc/images/7_welcome_ui_2.png)
![](./doc/images/8_pseudo_translation.png)

Each translatable resource will have a star added to the beginning of the resource string. Shake your phone to switch between the original and the test versions. If the translation scope is ok then your app is ready for On-device resource localization. 

Check the user experience

Based on what translation strategy you choose  (machine, machine + community, pro) the language manager will offer different options to your users. Choose Translate on the language manager screen to see how easy to translate your app with our unique on-device translation – our translator app [Nativer from GooglePlay] (https://play.google.com/store/apps/details?id=com.transround.nativer&referrer=utm_source%3Dgithub%26utm_medium%3Dreferral%26utm_campaign%3Don-device-leiras) automatically downloaded.

Releasing your app
------------
Your app is ready for release. Set the localization strategy – which language, which option – on http://developer.nativer.com using your registration account. Release your app through your preferred distribution.
------------


**Note for Android Studio version 1.1 users**: 
In case you are using Android Studio version 1.1.x and run into error "Error:No such property: bootClasspath for class: com.android.build.gradle.AppPlugin" when building your project then please **follow this workaround** until we fix the issue.

**Modify the gradle version from 1.1.0 to 1.0.0** in your project's build.gradle file.

When the build.gradle file is modfied by the Android Studio plugin it contains the following section

```groovy
    ...
    dependencies {
        classpath 'com.android.tools.build:gradle:1.1.0'
    ...
```

that should be changed as follows

```groovy
    ...
    dependencies {
        classpath 'com.android.tools.build:gradle:1.0.0'
    ...
```

Please carry out a clean build of your project. This workaround should solve the above compilation error.

After you start your app - language manager screen appears: choose developer mode from the menu.




Then in your app all your translatable resources will have a star added to the beginning of the resource string. 
Shake your phone to switch between the original and the test versions. 

Your app is ready for On-device resource localization.
Download the translator app [Nativer from GooglePlay](https://play.google.com/store/apps/details?id=com.transround.nativer&referrer=utm_source%3Dgithub%26utm_medium%3Dreferral%26utm_campaign%3Don-device-leiras) to see the translation experience. 



