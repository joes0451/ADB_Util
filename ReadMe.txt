
ADB_Util 
    Allows you to easily use Android's adb for your development
    or other uses.

    Go into config.properties and set 'android_sdk_path', 'download_dir',
    'screenshot_dir' and 'camera_dir' to what works for your system or device.
    You can make changes to config.properties without needing to restart ADB_Util.
    
    For 'Apk->Install apk' you must have 'Unknown sources' turned on in Security.

    ** Note **
    After you do "Reboot" or "Reboot to Recovery" it will lose any Device Id,
    so you have to wait till it re-connects and do "Home->Select Device".
    
Logcat:
    This lets you see all the logcat output.

    You can edit the "logcat_filter" property in config.properties which will
    help to block the output of the selected tags.
    
    All warning and error messages are color highlighted in the output.
    
PID Logcat:
    This lets you see just the log output from the running Process Id.
    
    To enable, go into config.properties and set 'use_pid_logcat' to "true".
    
    It needs the package name of the application for the Process Id, so you first need to
    start the app and select 'Logcat->Select Package' and select the package name.
    This saves it so it can later be matched when you start
    logcat.
    
Refresh:
    Refreshes any changes made to config.properties.
    
File Browser:
    When selecting a directory, other than for Submit, you can either
    double click the selection or select the directory and hit the "Go" Button.

Select Device:
    Allows you to select the target device, or Emulator, for Logcat output,
    Uninstall and Install Apk, if you are running more than one.
    The currently selected device is shown in the lower status bar.
    
Devices:
    Shows current devices, useful for checking wireless connection.

Wireless:
    Allows you to use adb wireless over Wi-Fi, no root required.
    You can optionally add your device's IP address in config.properties.
    If you lose your wireless connection you can reconnect by
    selecting Wireless->Re/Connect.
    
    - Attach your Android device to your computer with USB cable.
    
    - Wait for the indication that the OS has detected and connected with
    your device.
    
    - Select Wireless->Re/Connect.
    
    - After you see the Wireless Connect dialog, unplug your USB cable
    and hit 'OK'.
    
    - When you are finished with your session, select Wireless->Disconnect.


Release notes:
        
    ADB_Util 1.1.7:
        For 'Apk->Uninstall..' it will now wake up the device, if it's asleep,
        so that the uninstall will work.

    ADB_Util 1.1.9:
        Improved logcat.  It now clears the logcat buffer each time it starts to 
        reduce any slowdowns caused by long previous outputs.
        Improved PID logcat.  The secound time you would run it,
        it wouldn't get any output, and fixed it.
    
    ADB_Util 1.2.0:
        Improved logcat.  Now uses 'brief' option for more compact output and
        allow highlighting to work on newer devices.
    
    
    
   joes0451@outlook.com
        
