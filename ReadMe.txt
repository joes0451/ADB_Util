
ADB_Util 
    Allows you to easily use Android's adb for your development
    or other uses.

    Go into config.properties and set 'android_sdk_path', 'download_dir',
    'screenshot_dir' and 'camera_dir' to what works for your system or device.
    You can make changes to config.properties without needing to restart ADB_Util.
    
    For 'Apk->Install apk' you must have 'Unknown sources' turned on in Security.

    ** Note **
    After you do "Reboot" or "Reboot Recovery" it will lose any Device Id,
    so you have to wait till it re-connects and do "Home->Select Device".
    
Logcat:
    For Logcat can choose to just view log output from the running Process Id,
	there can be a delay before it starts sending output.
    Normally with logcat you can let it run until it slows down, and
    then you can start your Process.

    You can edit the "logcat_filter" property in config.properties which will
    help to block the output of the selected tags.
    
    All warning and error messages are color highlighted in the output.
    
    You can choose 'Logcat->Select Package' to select a running process
    for using Logcat with the Process Id.
    
File Browser:
    When selecting a directory, other than for Submit, you can either
    double click the selection or select the directory and hit the "Go" Button.

Select Device:
    Allows you to select the target device, or Emulator, for Logcat output,
    Uninstall and Install Apk, if you are running more than one.
    The currently selected device is shown in the lower status bar.

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

    ADB_Util 1.1.2:
        Fixed "File->Push file".
        Improved "Home->Select Device".
        
    ADB_Util 1.1.3:
        Tried to improve the Process Id logcat.
        
