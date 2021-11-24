
package com.dominionmobile.adbutil;

public class SingletonClass
{
    private static SingletonClass single_instance = null;
    
    public String sSelectedFile;
    public String sPackageName;
    public boolean bConnected;
    
    private SingletonClass()
    {
    }
    
    public static SingletonClass getInstance()
    {
        if ( single_instance == null )
            single_instance = new SingletonClass();
        
        return single_instance;
    }
}


