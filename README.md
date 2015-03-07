Android Dynamic Resource Management: Nativer SDK
==========================================

Nativer SDK adds dynamic resource management to any Android app, so language resources can be downloaded on a live app:
- Localization process can be decoupled from the app build cycle
- Translation work can be executed on-screen in-app having immediate feedback
- Management console added to provide control on user, machine and pro generated resource creation, distribution and usage. 

Nativer SDK integration in 15 minutes
--------------------------------------------------
No source code change needed in your app, only few configuration files have to be modified to integrate and utilize the SDK.

For Android Studio an integration plugin released to automate the process: [Android Studio plugin](https://github.com/Transround/NativerSDK/tree/master/tools/nativer-admin-plugin-android-studio#instructions).
The plugin will guide you through the integration and will carry out the necessary configuration file changes for your app. It also guide you through the testing of the integration and the setup of the resource management consol backend. 

For Eclipse IDE we provide a step-by-step integration instructions: 
In case you prefer to manually complete the necessary configuration changes please follow the description below.

For reference of the complete integration, testing and configuration please visit the [Nativer Developer Service Site] (http://developer.nativer.com/).

Sample Multilanguage Android app project
----------------------------------------
You can check how easy to build in the SDK and try the main features of the SDK using our sample [App Localization project] (https://github.com/Transround/NativerSDK/tree/master/nativer-sdk-sample).

Glossary
--------

*Host Application*: Is the original Application of a developer. The App Localization Demo (https://github.com/Transround/NativerSDK/tree/master/nativer-sdk-sample) is an example of a host application. By adding the Nativer SDK to it, becomes NLE (Nativer Localization Enabled) Application.

*NLE Application*: Stands for Nativer Localization Enabled Application. This is an original Application of a developer, in which the Nativer SDK has been embedded.

*Nativer SDK*: A set of functions and libraries, which enable the host Application to become easily translatable in the Nativer Ecosystem.

*Nativer Application*: This is Transround&rsquo;s own application, called Nativer, which implements majority of the functionality of the Nativer Ecosystem, like requesting localizations, downloading ready localizations, translate, proof reading, support, etc.
