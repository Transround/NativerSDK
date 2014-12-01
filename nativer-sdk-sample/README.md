App Localization Demo
===========
The main purpose for this app to show the power and fun translating an Android app on the phone.

In order to support this kind of translation of an app you need to build in a plugin (Nativer SDK) into the app.
The plugin together with detailed implementation instructions is also available on Github (https://github.com/Transround/NativerSDK).

1. Download the source code of [Nativer SDK] (https://github.com/Transround/NativerSDK).  Note: this will download the source code of the demo app and the SDK library, too.
2. You can find the compilation instruction for different development environments below.
3. If you start the app (extended with the plugin) an intro screen will guide you through the basic usage scenarios.

If first you would like to try the demo app itself, you can download it from [Google Play] (https://play.google.com/store/apps/details?id=com.transround.localizationdemo&utm_source=github&utm_medium=referral&utm_campaign=developer).

Compilation instructions (Android Studio)
-----------------------------------------
You simply have to import the downloaded source code into Android Studio, this will import two projects
* nativer-sdk: for the library
* nativer-sdk-sample: for the demo app

You can simply compile and start the demo app.

Compilation instructions (Eclipse)
-----------------------------------------
If you do not have AspectJ installed then first you have to install it please follow the instuctions on
For details regarding AspectJ installation please check the following [wiki page](https://github.com/Transround/NativerSDK/wiki/How-to-integrate-Nativer-SDK#3installing-aspectj);

If you have AspectJ then you have to import the downloaded source code into Eclipse, this will import two projects
* nativer-sdk: for the library
* nativer-sdk-sample: for the demo app

Then you have to set Android Support Library v7 for the SDK and the demo app project, too

In case you need instructions on how to set library support please see the following link: https://developer.android.com/tools/support-library/setup.html#libs-with-res

After these steps, you can compile and start the demo app.

Let us know if you have any questions, suggestions either by using the feedback button of the demo app or by sending an email to info@transround.com.

Thank you,
Transround team
