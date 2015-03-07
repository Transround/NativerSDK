Android Studio Plugin for Nativer SDK integration
=================================================

Android Studio Plugin is for automating Nativer SDK integration to your app. Step-by-step it will 
- install Nativer SDK into your app,
- setup the management consol,
- guide you through the testing process.

This document describes how to:
- install the plugin into your Android project
- test the dynamic resource management integration
- connect to the management console

Integrating the technology takes about 15 minutes. Your app will be ready for dynamic localization, your APK can be published without additonal built in language resources.

How to try it?
--------------
In Android Studio choose File -> Settings -> Plugins -> Browse repositories -> Android On-Device Resource Localization and install it. 

![](./doc/images/1_install_plugin.png)

Restart Android studio.

After restart the Nativer Admin plugin will be activated in the right pane providing a step by step instruction. (There is no commitment from your side until you launch your app with the Nativer SDK in a public appstore.) 

![](./doc/images/4_register_new_account_2.png)

How to test it?
---------------
Generate a new APK and load it on your phone. (See note for Android Studio 1.1)

After you start your app - Nativer SDK automatically launching the language manager popup screen. From the top right corner menu choose the developer mode.

![](./doc/images/6_welcome_ui.png)
![](./doc/images/7_welcome_ui_2.png)
![](./doc/images/8_pseudo_translation.png)

Each translatable resource is marked with a star at the beginning of the resource string. Shake your phone to switch between the original and the test versions. If the translation scope is ok then your app is ready for On-device resource localization. 

How to configure it?
--------------------

Before publishing your app on the management console you can configure how language resources are generated and used, and how to set up integrated analytics to get insights the language impact of user behaviour.

For Setting languages pls. go to http://developer.nativer.com/prod/admin/index.php?route=localisation/language_setup  using your registration account.

For Setting Analytics pls. go to http://developer.nativer.com/prod/admin/index.php?route=catalog/tracking using your registration account.
