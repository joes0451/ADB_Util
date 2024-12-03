
ADB_Util 
    Allows you to easily use Android's adb for your development
    or other uses.

    Go into config.properties and set 'android_sdk_path', 'download_dir',
    'screenshot_dir' and 'camera_dir' to what works for your device.
    You can make changes to config.properties without needing to restart ADB_Util.
    
    For 'Apk->Install apk' you must have 'Unknown sources' enabled in Security.

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
    
Refresh Properties:
    Refreshes any changes made to config.properties.
    
File Browser:
    When selecting a directory, other than for Submit, you can either
    double click the selection or select the directory and hit the "Go" Button.
    It will automatically use its symlink for given directories.

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
    selecting Wireless->Connect.
    
    - Attach your Android device to your computer with USB cable.
    
    - Wait for the indication that the OS has detected and connected with
    your device.
    
    - Select Wireless->Connect.
    
    - After you see the Wireless Connect dialog, unplug your USB cable
    and hit 'OK'.
    
    - When you are finished with your session, select Wireless->Disconnect.


Release notes:
        
    ADB_Util 1.2.7:
        Improved timing.
        
    ADB_Util 1.2.8: 
        Improved Exception prevention.

    ADB_Util 1.3.0:
        Improved Logcat and Process Id Logcat.
        New option, 'use_adb_pull_to_download', to use 'adb pull' to download to system
        download directory, for 'Take screenshot' and 'Screen Record'.
        Other improvements.
    
   joes0451@outlook.com
        
