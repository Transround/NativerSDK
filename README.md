Nativer SDK
===========

<p></p>

<h1>SDK integration</h1>

<p></p>

<h1>1.How does it work</h1>

<p>To make your life easier, Nativer SDK uses AspectJ to make the necessary changes in your code instead of you. AspectJ is the Java implementation of Aspect Oriented Programming (AOP). The idea of AOP is to inject code fragments to well-defined places in an application without modifying the source code. AspectJ uses code weaving technology to inject additional code at compile time which means your source code remains intact but your compiled application will contain everything required.</p>

<p>In order to compile code with aspects in Eclipse you need to install the <em>AspectJ Development Tools</em> plugin.</p>

<h1>2.General work considerations</h1>

<p>In order to avoid disturbing your other projects, or making irreversible damages, we suggest you to create a new, clean workspace, where you will have to import your Android project to.</p>

<p>Import your application project(s) into the newly created Workspace. (File / New / Android project from existing code.). Do not forget to select the &ldquo;Copy projects into workspace&rdquo; checkbox, in order to keep your original sources intact.</p>

<p>Check the build target of ALL projects and make sure that you are building with Google APIs, and for at least API level 17.</p>

<p>Make sure that a Java JDK of version 1.6 is installed.</p>

<p><strong><em>Note</em></strong><em>: though the solution was validated with Java 1.6, some preliminary tests show, that works also fine with version 1.7. We are working hard to validate the 1.7 version as well. As soon as the validation is ready, it will be communicated through our website.</em></p>

<p>&nbsp;</p>

<h1>3.Installing AspectJ</h1>

<p>The AspectJ Development Tools is a standard Eclipse plugin which can be downloaded and installed by Eclipse the same way you installed the Android Development Tools.</p>

<p>Select <strong><em>&bdquo;Help/Install New Software&hellip;&rdquo;</em></strong> in Eclipse and click on <strong><em>&bdquo;Add&hellip;&rdquo;</em></strong>. The Repository Location depends on which version of Eclipse you use:</p>

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

<p>&nbsp;</p>

<h1>4.Converting your Android project to an AspectJ project</h1>

<p>In order to tell Eclipse to use the AJDT features in your Android project you must convert it to an AspectJ project. To do so, right click on your project and select <strong><em>&bdquo;Configure/Convert to AspectJ Project&hellip;&rdquo;</em></strong> from the popup menu.</p>

<p><img alt="" src="http://nativer01.nativer.com/prod/image/data/IntegrationDoc/ConvertProjectToAspectJ.png" style="height:119px; width:605px" /></p>

<p><strong><em>Note:</em></strong><em> Don&rsquo;t worry, a project can have multiple natures in Eclipse, so your project keeps its Android nature and you can undo this change easily in the future should you change your mind.</em></p>

<p>&nbsp;</p>

<h1>5.Setting up Nativer SDK in your project</h1>

<p>Setting up Nativer SDK requires just a little bit more effort than adding an ordinary Android library to your project.</p>

<p>Download file Nativer SDKDist.zip from our Developer Website, and unpack it on a temporary folder of your computer.(e.g.:E:\NativerSDK).</p>

<p><strong>Import NativerSDK to Eclipse</strong></p>

<p>Import the NativerSDK project from the temporary folder into the Workspace. (File / Import / Existing Android code into Workspace.) Please do not forget to check the &ldquo;Copy project into workspace&rdquo; option!</p>

<p><strong>Set Android Support Library for the SDK and your project</strong></p>

<p>After importing, right-click to the NativerSDK project and use the Android tools / Add Support Library&hellip; feature. The Android SDK Manager will download the latest Android Support Library. Please accept the license then press Install.</p>

<p>Do the same procedure for your project.</p>

<p>This will guarantee that the support libraries will be the same versions (if there is any in your project).</p>

<h2>Add NativerSDK to your Project.</h2>

<p>For this, right-click your project, and go to Properties. Select Android on the left of the screen. On the lower part of the screen select Add, and choose NativerSDK from the list. Please be sure you DO NOT CHECK the &ldquo;Is Library&rdquo; option. If ready, press OK.</p>

<p><img alt="" src="http://nativer01.nativer.com/prod/image/data/IntegrationDoc/AddNativerSDKToProject.png" style="height:598px; width:598px" /></p>

<p><strong>Add NativerSDK project and the nativersdkplugin.jar to your Aspect Path.</strong></p>

<p>Right-click your project and go to Properties. Select AspectJ Build on the left of the screen. Than on the main part of the Properties screen select the Aspect Path tab.</p>

<p>Press the Add Project&hellip; button, then select the NativerSDK project and click OK.</p>

<p>Press the Add External JARs button and go to your temporary folder. There you will find the nativersdkplugin.jar. Select this file and press OK. Your Properties screen will look very similar to the picture below. After you added the SDK project and the jar file, press OK to go back to the projects list.</p>

<p><img alt="" src="http://nativer01.nativer.com/prod/image/data/IntegrationDoc/AddNativerSDKandNativerJARToAspectJ.png" style="height:598px; width:598px" /></p>

<p><strong>After adding the SDK and the jar to the AspectJ&rsquo;s build path also add them to the Java Build Path.</strong></p>

<p>Please check Order and Export tab of the Java Build Path. Right-click to your project, go to the Properties menu. On the left select Java Build Path, then switch to the Order and Export tab. Please make sure that NativerSDK and the nativersdkplugin.jar are checked, and their order is like in the picture below:</p>

<p><img alt="" src="http://nativer01.nativer.com/prod/image/data/IntegrationDoc/JavaBuildPathOrderandExport.png" style="height:598px; width:598px" /></p>

<p>Press OK, then clean the NativerSDK project and your project as well.</p>

<p>For this use the Project / Clean&hellip; in the upper menubar of Eclipse for cleaning the SDK and your project. Select Clean projects selected below, check your project and the NativerSDK then press OK.</p>

<h2>Declare the Nativer SDK&rsquo;s service in the manifest file in your application:</h2>

<p>Copy the following text sequence, and paste it into the AndroidManifest.xml file of your project.</p>

<p>&lt;service android:name=<em>&quot;com.transround.plugin.service.PluginInterfaceService&quot;</em> &gt;</p>

<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &lt;intent-filter&gt;</p>

<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &lt;action android:name=<em>&quot;com.transround.tools.PING&quot;</em> /&gt;</p>

<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &lt;category android:name=<em>&quot;com.transround.tools.TRANSLATOR&quot;</em> /&gt;</p>

<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &lt;/intent-filter&gt;</p>

<p>&nbsp;&lt;/service&gt;</p>

<p>&nbsp;&lt;activity android:name=<em>&quot;com.transround.plugin.activity.RefreshScreen&quot;</em> /&gt;</p>

<p>Please be careful, and make sure the above text is copied into your manifest file before the&nbsp; &lt;/application&gt; closing tag&nbsp; declaration.</p>

<p>Also make sure you do not paste the text inside an other application or service, or any other declaration.</p>

<p>Please also make sure you have the following text outside the section:</p>

<p>&nbsp;&lt;uses-permission android:name=<em>&quot;android.permission.INTERNET&quot;</em> /&gt;</p>

<p>&nbsp;&lt;uses-permission android:name=<em>&quot;android.permission.WRITE_EXTERNAL_STORAGE&quot;</em> /&gt;</p>

<p>&nbsp;</p>

<h1>6.Changing default SDK settings in your application</h1>

<p>The Nativer SDK can be configured using the Nativer application but you can also change the default settings for you application.</p>

<p>You can change the following in res/values/settings.xml:</p>

<h2>transround_plugin_enabled</h2>

<p>Sets wether the Nativer SDK&rsquo;s functionality is enabled by default.</p>

<p><strong>Valid values:</strong></p>

<ul>
	<li><strong>@string/transround_enabled</strong> &ndash; plugin is enabled</li>
	<li><strong>@string/transround_disabled</strong> &ndash; plugin is disabled</li>
</ul>

<p>&nbsp;</p>

<h2>transround_popup_enabled</h2>

<p>Sets whether a confirmation dialog shows before starting the translator app.</p>

<p><strong>Valid values:</strong></p>

<ul>
	<li><strong>@string/transround_enabled</strong> &ndash; confirmation dialog is enabled</li>
	<li><strong>@string/transround_disabled</strong> &ndash; confirmation dialog is disabled</li>
</ul>

<p>&nbsp;</p>

<h2>transround_splash_mode</h2>

<p>Sets the default behavior of the Nativer splash screen.</p>

<p><strong>Valid values:</strong></p>

<ul>
	<li><strong>@string/transround_splash_always</strong> &ndash; always show the splash screen</li>
	<li><strong>@string/transround_splash_second_time_only</strong> &ndash; the splash screen shows only the second time the app is started (first time is spared for the original splash screen)</li>
	<li><strong>@string/transround_splash_never</strong> &ndash; never show the splash screen</li>
</ul>

<p>&nbsp;</p>

<h2>transround_translation_mode</h2>

<p>Sets the default method used to start the translator app.</p>

<p><strong>Valid values:</strong></p>

<ul>
	<li><strong>@string/transround_translation_mode_notification</strong> &ndash; use notifications to start the translator app</li>
	<li><strong>@string/transround_translation_mode_volume_key</strong> &ndash; use volume key combination to start the translator app</li>
	<li><strong>@string/transround_translation_mode_both</strong> &ndash; use both methods</li>
</ul>

<p>&nbsp;</p>

<h1>7.Protecting parts of your application from the Nativer SDK</h1>

<p>If there are some parts of your application which you don&rsquo;t want to be translated for some reason then here&rsquo;s how you can tell Nativer to avoid them.</p>

<p>You can use the <strong>@DontTouchThis</strong> annotation to tell the SDK which part of your code should be left intact. You can use this annotation on a whole class or on a method. For example if you want a specific Activity not to be modified then you can annotate your Activity class like this:</p>

<p><strong>&hellip;</strong></p>

<p><strong>import</strong> com.transround.plugin.aspect.TransroundAspect.DontTouchThis;</p>

<p>&nbsp;</p>

<p>@DontTouchThis</p>

<p><strong>public</strong> <strong>class</strong> MyProtectedActivity <strong>extends</strong> Activity {</p>

<p>&nbsp;</p>

<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; @Override</p>

<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <strong>public</strong> <strong>void</strong> onCreate(Bundle savedInstanceState) {</p>

<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <strong>super</strong>.onCreate(savedInstanceState);</p>

<p>&hellip;</p>

<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; }</p>

<p>}</p>

<p>This way nothing will be affected by the SDK in this Activity.</p>

<p>You can also annotate a method to protect an Adapter for example when you don&rsquo;t want the data in your Spinner changed:</p>

<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; @DontTouchThis</p>

<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <strong>protected</strong> <strong>void</strong> createMyContents() {</p>

<p>&hellip;</p>

<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; mySpinner.setAdapter(myAdapter);</p>

<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; }</p>

<p>This way myAdapter won&rsquo;t be affected by the plugin but everything else will be.</p>

<p>&nbsp;</p>

<h1>8.Build your project.</h1>

<p></p>

<p>It should now have all Nativer capabilities, and should display the notification and the splash screen, provided as they are enabled in Nativer and on your master server.</p>

<p></p>

<h2>Define the scope of translation</h2>

<p>Upload the default language resource files of your application in <strong><em>Catalog -&gt; Applications and Categories;</em></strong> select the application and use the links in <strong>Action </strong>column</p>

<p>Data Cleaning. After uploading the resource files you get automatically into a data cleaning list of all your resources. Plsease deselect all items which should not be translated. (For example Application name, links, database details, etc.)</p>

<h2>Testing translatability</h2>

<p>Install your NLE app to a test device (or emulator)</p>

<p>Download and install Nativer app: If you did not download it already, please refer back to Step4, Nativer downloadables. &nbsp;</p>

<p>Start Nativer app and login with tester account (<em><strong>Catalog -&gt; Test customer accounts</strong></em>). This will set Nativer default language to Pseudo language for testing purposes.</p>

<p>Check your NLE app.&nbsp; Go to the For You tab (or Shop tab) of Nativer, find your NLE app on the list, and tap Download on the Pseudo Language translation of it. Nativer will automatically download the pseudo tranlation file, switch your app display language to Pseudo, and start your application. Do not worry! Pseudo is not a new language you have to learn! This simply means that all translatable strings will be the original ones you just uploaded to our server, except the first character, which is replaced with a star. This way you can check through your app, that all desired content became translatable. Each string, phrase, label, etc, of your application, which has the star as first character, will be later translatable by Nativer technology.</p>

<p>If you find issues, non-translatable elements which should be translatable, you need to review your application and its resources. and need to repeat the process from resource upload.</p>

<p>If you found no issues than your app technically became NLE. Please go to <em><strong>Catalog -&gt; Applications and Categories</strong></em> and select action <strong>EDIT </strong>for your application. On the new page go to <strong>Data Tab</strong>, and on the bottom change the status to &ldquo;<strong>Ready to Publish</strong>&rdquo;.&nbsp;</p>

<p>You are almost done. Your application became translatable to any supported language. Now please follow the steps in Go To Market.</p>

<p><a href="http://nativer01.nativer.com/prod/admin/index.php?route=common/information&amp;token=[__token__]&amp;information_id=42&amp;menu=devres">Go to Market</a></p>

<h1>Glossary:</h1>

<p><b>Host Application</b>: Is the original Application of a developer. The App Localization Demo (https://github.com/Transround/LocalizationDemoDev) is an example of a host application. By adding the Nativer SDK to it, becomes NLE (Nativer Localization Enabled) Application</p>

<p><b>NLE Application</b>: Stands for Nativer Localization Enabled Application. This is an original Application of a developer, in which the Nativer SDK has been embedded.</p>

<p><b>Nativer SDK</b>: A set of functions and libraries, which enable the host Application to become easily translatable in the Nativer Ecosystem</p>

<p><b>Nativer Application</b>: This is Transround&rsquo;s own application, called Nativer, which implements majority of the functionality of the Nativer Ecosystem, like requesting localizations, downloading ready localizations, translate, proof reading, support, etc.</p>
