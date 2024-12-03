/*
 * :folding=explicit:collapseFolds=1:
 */

/**
 *	  ADB_Util is a utility for Android ADB
 *
 *	  Copyright (c) 2024 Joseph Siebenmann
 *
 *    This program is free software: you can redistribute it and/or modify
 *    it under the terms of the GNU General  Public License as published by
 *    the Free Software Foundation; version 2.
 *
 *    This program is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *    GNU General Public License for more details.
 *
 *    You should have received a copy of the GNU General Public License
 *    along with this program.  If not, see<http://www.gnu.org/licenses/>.
 */

package com.dominionmobile.adbutil;

import java.awt.event.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.JCheckBox;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileSystemView;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.*;
import java.awt.Color;
//import java.awt.List;
import java.awt.Font;
import java.awt.FlowLayout;
import java.awt.CardLayout;
import java.util.Iterator;
import java.util.List;
import java.io.File;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.BorderFactory;
import javax.swing.UIDefaults;
import javax.swing.WindowConstants;
import javax.swing.SwingUtilities;
import javax.swing.ListModel;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Component;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.Insets;
import java.awt.Window;
import java.awt.FontMetrics;
import java.awt.Rectangle;
import java.awt.TextArea;

import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.border.Border;
import javax.swing.UIManager;
import javax.swing.JSplitPane;
import javax.swing.JList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.io.InputStreamReader;
import java.io.DataOutputStream;
import java.io.DataInputStream;
import java.io.PrintWriter;
import java.io.OutputStreamWriter;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import javax.swing.plaf.ColorUIResource;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

import java.nio.file.Files;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.text.*;
import java.util.Properties;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.Arrays;


public class ADB_Util
{
    //{{{   Data

    private static JPanel mainPanel;
    private static JPanel textAreaPanel;
    private static JPanel cardPanel;
    private static JPanel pathButtonPanel;
    private static JFrame screenshotFrame;
    private static JFrame cameraFrame;
    private static JFrame adbCommandFrame;
    private static JFrame packageFrame;
    private static JFrame mainJFrame;
    private static JFrame selectDeviceFrame;
    private static JFrame fileBrowserFrame;
    private static JFrame selectPackageFrame;
    private static JTextPane textPane;
    private static JTextField currentPathField;
    private static JTextField adbCommandField;
    private static JList screenshotFilesJList;
    private static JList cameraImagesJList;
    private static JList uninstallJList;
    private static JList fileBrowserJList;
    private static JSpinner deviceSpinner;
    private static JSpinner fileSpinner;
    private static JMenuItem logcatMenuItem;
    
	private JLabel statusLabel;
	private JLabel deviceLabel;
	private JLabel packageLabel;
	private JLabel statusPath;
    
    private static ADB_Util aDB_Util;
    
    static volatile String sInternalCommand;
    static volatile String sDeviceName;
    static volatile String sScreenshotDir;
    static volatile String commandResultS;
    static volatile String androidSdkPathS;
    static volatile String sDownloadDir;
    static volatile String sCameraDir;
    static volatile String sListSelection;
    static volatile String commandS;
    static volatile String outputEndS;
    static volatile String sPackageName;
    static volatile String sPid;
    static volatile String sUsePidLogcat;
    static volatile String logcatFilterS;
    static volatile String sDeviceIPAddress;
    static volatile String sWirelessMessage;
    static volatile String sWirelessID;
    static volatile String sCurrentPath;
    static volatile String sUseDirectScreenshotDownload;
    static volatile String sSelectedFile;
    static volatile String sSelectedMenu;
    static volatile String sScreenRecordTimeLimit;
    static volatile String sScreenRecordBitRate;
    static volatile String sScreenRecordVideoSize;
    static volatile String sScreenRecordShowVerbose;
    static volatile String sWakefulness;
    static volatile String sSDcardPath;
    static volatile String sScreenshotPath;
    static volatile String sCameraPath;
    static volatile String sUseAdbPullToDownload;
    private String internalCommandS;
    
    static volatile String[] tokSa;
    
    static volatile boolean bCommandFinished;
    static volatile boolean bDevicesFinished;
    static volatile boolean bBreakOut;
    static volatile boolean bIOBgThreadBreak;
    static volatile boolean bLogcatOn;
    static volatile boolean bFinished;
    static volatile boolean bKillLogcat;
    static volatile boolean bIOBgThreadFinished;
    static volatile boolean bSelectFinished;
    static volatile boolean bInitWirelessFinished;
    static volatile boolean bWirelessConnected;
    static volatile boolean bDisconnectWirelessFinished;
    static volatile boolean bWirelessEnabled;
    static volatile boolean bUpdateFileBrowserFinished;
    static volatile boolean bShellPsFinished;
    static volatile boolean bWakeDeviceFinished;
    static volatile boolean bCheckDeviceFinished;
    static volatile boolean bSelectionTimerStarted;
    static volatile boolean bFileBrowserUpdateOkay;
    static volatile boolean bInternalFinished;
    static volatile boolean bConnectWirelessFinished;
    
    static volatile int iOS;
    static volatile int iListId;
    static volatile int iWirelessErrorCode;
    static volatile int iSelectMode;
    static volatile int iButtonCount;
    static volatile int iFileBrowserSelectedIndex;
    static volatile int iLoopCount;
    
    static volatile long lPrevTime;
    static volatile long lTimerEnd;
    
	private StyledDocument doc;
	private Style normalStyle;
	private Style warningStyle;
	private Style errorStyle;
    
    static ArrayList FilesAr;
    static ArrayList DevicesAr = null;
    static ArrayList ConnectDevicesAr;
    static ArrayList fileToksAr;
    
    static List<String> selectionList = new ArrayList<String>();
    
    //static CountDownLatch commandRequestLatch;
    //static CountDownLatch completeLatch;
    
    CardLayout cardLayout;
    
    private static Semaphore commandControl = new Semaphore(1);
    
    private byte[] tempBuf = new byte[4096];
    private StringBuffer lineSb = new StringBuffer(4096);
    
    CommandBgThread commandBgThread;
    GetDevicesBgThread getDevicesBgThread;
    IOBgThread iOBgThread;
    InitWirelessBgThread initWirelessBgThread;
    ConnectWirelessBgThread connectWirelessBgThread;
    DisconnectWirelessBgThread disconnectWirelessBgThread;
    UpdateFileBrowserBgThread updateFileBrowserBgThread;
    CheckDeviceBgThread checkDeviceBgThread;
    WakeDeviceBgThread wakeDeviceBgThread;
    FindSDcardBgThread findSDcardBgThread;
    
	static final int WINDOWS = 0;
	static final int LINUX_MAC = 1;
	
	static final int NORMAL = 0;
	static final int WARNING = 1;
	static final int ERROR = 2;
	
	static final int SELECT_NORMAL = 0;
	static final int SELECT_CONNECT = 1;
	
	//static final int FONT_SIZE = 12;
	static final int FONT_SIZE = 13;

	// List Ids	
	static final int SCREENSHOT_LIST = 0;
	static final int CAMERA_IMAGE_LIST = 1;
	static final int PACKAGE_LIST = 2;
	static final int FILE_BROWSER_LIST = 3;
	
	static final int DISPLAY_WIDTH = 105;
	static final int DISPLAY_BREAK_WIDTH = DISPLAY_WIDTH - 5;
	
	static final String TAKE_SCREENSHOT = "Take screenshot";
	static final String PULL_SCREENSHOT = "Pull screenshots";
	static final String SCREEN_RECORD = "Screen Record";
	static final String LIST_PIDS = "List PIDs";
	static final String LIST_PACKAGES = "List Packages";
	static final String GETPROP = "getprop";
	static final String ADB_COMMAND = "Adb Command";
	static final String REBOOT = "Reboot";
	static final String REBOOT_RECOVERY = "Reboot to Recovery";
	static final String SELECT_DEVICE = "Select Device";
	static final String DEVICES = "Devices";
	static final String PULL_FILE = "Pull files";
	static final String PUSH_FILE = "Push file";
	static final String DELETE_FILE = "Delete files";
	static final String WIRELESS_CONNECT = "wireless_connect";
	static final String WIRELESS_DISCONNECT = "wireless_disconnect";
	static final String REFRESH = "Refresh Properties";
	static final String LOGCAT = "Start/Stop Logcat";
	static final String PULL_CAMERA_IMAGE = "Pull camera images";
	static final String SELECT_PACKAGE = "Select Package";
	
	static final String PULL_CAMERA_IMAGE_SUBMIT = "pull_camera_image_submit";
	static final String PULL_CAMERA_IMAGE_CANCEL = "pull_camera_image_cancel";
	static final String PULL_SCREENSHOT_SUBMIT = "pull_screenshot_submit";
	static final String PULL_SCREENSHOT_CANCEL = "pull_screenshot_cancel";
	static final String SELECT_DEVICE_CANCEL = "select_device_cancel";
	static final String SELECT_DEVICE_SUBMIT = "select_device_submit";
	static final String ADB_COMMAND_SUBMIT = "adb_command_submit";
	static final String ADB_COMMAND_CANCEL = "adb_command_cancel";
	
	static final String SELECT_FILE_GO = "select_file_go";
	static final String SELECT_FILE_SUBMIT = "select_file_submit";
	static final String SELECT_FILE_CANCEL = "select_file_cancel";
	static final String INSTALL_APK = "Install apk";
	static final String UNINSTALL_APK = "Uninstall apk";
	static final String PACKAGE_SUBMIT = "package_submit";
	static final String PACKAGE_CANCEL = "package_cancel";
	
    //}}}

    //{{{   ADB_Util() constructor    
    public ADB_Util()
    {
        //System.out.println("ADB_Util()");
		// Determine OS..
		String sOs = System.getProperty("os.name").toLowerCase();
		if (sOs.contains("win"))
			iOS = WINDOWS;
		else if ((sOs.contains("nix")) ||
                (sOs.contains("nux")) ||
                (sOs.contains("mac")))
			iOS = LINUX_MAC;
		
		CreateGui();
		RefreshProperties();
		
		bLogcatOn = false;
		bWirelessConnected = false;
		sCurrentPath = "";
		iButtonCount = 0;
		lPrevTime = 0;
		bSelectionTimerStarted = false;
		
		
        SingletonClass sc = SingletonClass.getInstance();
        sc.bConnected = false;
        sc.s_WirelessConnected = false;
		
		
		/**
         * Get number of running Emulators or devices..
         */

		//completeLatch = new CountDownLatch(1);
		
		bDevicesFinished = false;
		getDevicesBgThread = new GetDevicesBgThread();
		getDevicesBgThread.start();

		while ( true )
		{
            try
            {
                //completeLatch.await();
                Thread.sleep(333);
            }
            catch (InterruptedException ie)
            {
            }
            
            if ( bDevicesFinished )
                break;
        }

/*
        if ( DevicesAr == null )
            System.out.println("DevicesAr null");
        else
            System.out.println("DevicesAr.size(): "+DevicesAr.size());
/**/        
        
		if ( (DevicesAr	!= null) && (DevicesAr.size() > 0) )
		{
			if ( DevicesAr.size() > 1 )
			{
				// Open Dialog..
				selectDeviceDialog();
			}
			else
			{
			    sc.bConnected = true;
			    
				// Single device, show it..
				deviceLabel.setText((String)DevicesAr.get(0));
				sDeviceName = (String)DevicesAr.get(0);
			}
		}
		
		findSDcardBgThread = new FindSDcardBgThread();
		findSDcardBgThread.start();
		
    }   //}}}

	//{{{	RefreshProperties()
	private void RefreshProperties()
	{
	    //System.out.println("RefreshProperties()");
		// Read Properties..
		String sT = "";
		Properties prop = new Properties();

		try
		{
			prop.load(new FileInputStream("config.properties"));

			// Get Property Values..
			androidSdkPathS = processPath(prop.getProperty("android_sdk_path"));
			sDownloadDir = processPath(prop.getProperty("download_dir"));
			sScreenshotDir = processPath(prop.getProperty("screenshot_dir"));
			sScreenRecordTimeLimit = processPath(prop.getProperty("screen_record_time_limit"));
			sScreenRecordBitRate = processPath(prop.getProperty("screen_record_bit_rate"));
			sScreenRecordVideoSize = processPath(prop.getProperty("screen_record_video_size"));
			sScreenRecordShowVerbose = processPath(prop.getProperty("screen_record_use_verbose"));
			
			sCameraDir = processPath(prop.getProperty("camera_dir"));
			sT = processPath(prop.getProperty("package_name"));
			if ( (sT != null) && (! sT.equals("null")) && (sT.length() > 0) )
			{
                SingletonClass sc = SingletonClass.getInstance();
                sc.sPackageName = sT;
			}
			
			sUseAdbPullToDownload = processPath(prop.getProperty("use_adb_pull_to_download"));
			
			sUsePidLogcat = processPath(prop.getProperty("use_pid_logcat"));
			
			logcatFilterS = processPath(prop.getProperty("logcat_filter"));
			sDeviceIPAddress = processPath(prop.getProperty("device_ip_address"));
			sUseDirectScreenshotDownload = processPath(prop.getProperty("use_direct_screenshot_download"));
			
		}
		catch (IOException ioe)
		{
			System.out.println("RefreshProperties() Exception");
			ioe.printStackTrace();
		}

		StringBuffer sB = new StringBuffer();
		sB.append("  ");
		sB.append(androidSdkPathS);
		statusLabel.setText(sB.toString());
		
		//System.out.println("Exiting RefreshProperties()");

	} //}}}
    
	//{{{	CreateGui()
	public void CreateGui()
	{
	    //System.out.println("CreateGui()");
	    int iFontSize = FONT_SIZE;
	    
		// Use BorderLayout..
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());

		GridBagLayout gridbag = new GridBagLayout();

		JPanel topPanel = new JPanel();
		topPanel.setLayout(gridbag);

		/**
		 *		Menus
		 */

		GridBagConstraints menuBarGbc = new GridBagConstraints();

		JMenuBar menuBar = new JMenuBar();
		
		JMenu homeMenu = new JMenu("Home");
		JMenuItem selectDeviceMenuItem = new JMenuItem("Select Device");
		selectDeviceMenuItem.addActionListener(actListener);
		JMenuItem devicesMenuItem = new JMenuItem("Devices");
		devicesMenuItem.addActionListener(actListener);

		JMenu wirelessSubMenu = new JMenu("Wireless");
		//JMenuItem wirelessSubMenuItem = new JMenuItem("Re/Connect");
		JMenuItem wirelessSubMenuItem = new JMenuItem("Connect");
		wirelessSubMenuItem.setActionCommand("wireless_connect");
		wirelessSubMenuItem.addActionListener(actListener);
		wirelessSubMenu.add(wirelessSubMenuItem);
		homeMenu.add(wirelessSubMenu);
		
		wirelessSubMenuItem = new JMenuItem("Disconnect");
		wirelessSubMenuItem.setActionCommand("wireless_disconnect");
		wirelessSubMenuItem.addActionListener(actListener);
		wirelessSubMenu.add(wirelessSubMenuItem);
		
		JMenuItem refreshMenuItem = new JMenuItem("Refresh Properties");
		refreshMenuItem.addActionListener(actListener);
		
		JMenu screenshotMenu = new JMenu("Screen Capture/Camera");
		JMenuItem takeScreenshotMenuItem = new JMenuItem("Take screenshot");
		takeScreenshotMenuItem.addActionListener(actListener);
		JMenuItem pullScreenshotMenuItem = new JMenuItem("Pull screenshots");
		pullScreenshotMenuItem.addActionListener(actListener);
		JMenuItem screenRecordMenuItem = new JMenuItem("Screen Record");
		screenRecordMenuItem.addActionListener(actListener);
		JMenuItem pullCameraImageMenuItem = new JMenuItem("Pull camera images");
		pullCameraImageMenuItem.addActionListener(actListener);

		JMenu commandMenu = new JMenu("Command");
		JMenuItem listPidsMenuItem = new JMenuItem("List PIDs");
		listPidsMenuItem.addActionListener(actListener);
		JMenuItem listPackagesMenuItem = new JMenuItem("List Packages");
		listPackagesMenuItem.addActionListener(actListener);
		JMenuItem getpropMenuItem = new JMenuItem("getprop");
		getpropMenuItem.addActionListener(actListener);
		JMenuItem adbCommandMenuItem = new JMenuItem("Adb Command");
		adbCommandMenuItem.addActionListener(actListener);

		JMenu rebootMenu = new JMenu("Reboot");
		JMenuItem rebootMenuItem = new JMenuItem("Reboot");
		rebootMenuItem.addActionListener(actListener);
		JMenuItem rebootRecoveryMenuItem = new JMenuItem("Reboot to Recovery");
		rebootRecoveryMenuItem.addActionListener(actListener);
		
		JMenu logcatMenu = new JMenu("Logcat");
		logcatMenuItem = new JMenuItem("Start/Stop Logcat");
		logcatMenuItem.addActionListener(actListener);
		JMenuItem selectPackageMenuItem = new JMenuItem("Select Package");
		selectPackageMenuItem.addActionListener(actListener);

		JMenu apkMenu = new JMenu("Apk");
		JMenuItem uninstallAppMenuItem = new JMenuItem("Uninstall apk");
		uninstallAppMenuItem.addActionListener(actListener);
		JMenuItem installAppMenuItem = new JMenuItem("Install apk");
		installAppMenuItem.addActionListener(actListener);

		JMenu fileMenu = new JMenu("File");
		JMenuItem pushFileMenuItem = new JMenuItem("Push file");
		pushFileMenuItem.addActionListener(actListener);
		JMenuItem pullFileMenuItem = new JMenuItem("Pull files");
		pullFileMenuItem.addActionListener(actListener);
		JMenuItem deleteFileMenuItem = new JMenuItem("Delete files");
		deleteFileMenuItem.addActionListener(actListener);
		
		homeMenu.add(selectDeviceMenuItem);
		homeMenu.add(devicesMenuItem);
		homeMenu.add(wirelessSubMenu);
		homeMenu.add(refreshMenuItem);
		menuBar.add(homeMenu);
		
		screenshotMenu.add(takeScreenshotMenuItem);
		screenshotMenu.add(pullScreenshotMenuItem);
		screenshotMenu.add(screenRecordMenuItem);
		screenshotMenu.add(pullCameraImageMenuItem);
		menuBar.add(screenshotMenu);
		
		commandMenu.add(listPidsMenuItem);
		commandMenu.add(listPackagesMenuItem);
		commandMenu.add(getpropMenuItem);
		commandMenu.add(adbCommandMenuItem);
		menuBar.add(commandMenu);

		rebootMenu.add(rebootMenuItem);
		rebootMenu.add(rebootRecoveryMenuItem);
		menuBar.add(rebootMenu);
		
		logcatMenu.add(logcatMenuItem);
		logcatMenu.add(selectPackageMenuItem);
		menuBar.add(logcatMenu);

		apkMenu.add(uninstallAppMenuItem);
		apkMenu.add(installAppMenuItem);
		menuBar.add(apkMenu);

		fileMenu.add(pushFileMenuItem);
		fileMenu.add(pullFileMenuItem);
		fileMenu.add(deleteFileMenuItem);
		menuBar.add(fileMenu);

		menuBarGbc.gridy = 0;
		menuBarGbc.gridheight = 1;
		menuBarGbc.weightx = 1.0;
		menuBarGbc.gridwidth = GridBagConstraints.REMAINDER;
		menuBarGbc.fill = GridBagConstraints.HORIZONTAL;
		menuBarGbc.anchor = GridBagConstraints.WEST;

		topPanel.add(menuBar, menuBarGbc);

		/**
		 *		Console Pane
		 */

		 
		cardLayout = new CardLayout(); 
		cardPanel = new JPanel();
		cardPanel.setLayout(cardLayout);


		Dimension dim = new Dimension();
		dim = topPanel.getPreferredSize();

		Font defaultFont = new Font("Monospaced", Font.BOLD, FONT_SIZE);
		FontMetrics fontMetrics = mainPanel.getFontMetrics(defaultFont);
		
		int iChrW = fontMetrics.charWidth('X');
		int iHgt = fontMetrics.getHeight();
		
		topPanel.setPreferredSize(new Dimension(iChrW * DISPLAY_WIDTH, (int)dim.getHeight()));	// Original

		UIDefaults defs = UIManager.getDefaults();
		defs.put("TextPane.background", new ColorUIResource(Color.BLACK));
		defs.put("TextPane.inactiveBackground", new ColorUIResource(Color.BLACK));

		StyleContext sc = new StyleContext();
		doc = new DefaultStyledDocument(sc);
		
		textPane = new JTextPane();
		textPane.setEditable(false);
		textPane.setPreferredSize(new Dimension(iChrW * DISPLAY_WIDTH, iHgt * 20));
		textPane.setBorder(BorderFactory.createEmptyBorder());	// Remove default (white) margin..
		
		normalStyle = sc.addStyle("Normal", null);
		normalStyle.addAttribute(StyleConstants.Foreground, new Color(224, 224, 224));
		normalStyle.addAttribute(StyleConstants.FontSize, new Integer(iFontSize));
		normalStyle.addAttribute(StyleConstants.FontFamily, "Monospaced");
		normalStyle.addAttribute(StyleConstants.Bold, new Boolean(true));

		warningStyle = sc.addStyle("Warning", null);
		warningStyle.addAttribute(StyleConstants.Foreground, new Color(255, 158, 13));
		warningStyle.addAttribute(StyleConstants.FontSize, new Integer(iFontSize));
		warningStyle.addAttribute(StyleConstants.FontFamily, "Monospaced");
		warningStyle.addAttribute(StyleConstants.Bold, new Boolean(true));

		errorStyle = sc.addStyle("Error", null);
		errorStyle.addAttribute(StyleConstants.Foreground, new Color(255, 25, 25));
		errorStyle.addAttribute(StyleConstants.FontSize, new Integer(iFontSize));
		errorStyle.addAttribute(StyleConstants.FontFamily, "Monospaced");
		errorStyle.addAttribute(StyleConstants.Bold, new Boolean(true));
		
		JScrollPane scrollPane = new JScrollPane(textPane,
			JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
			JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		scrollPane.setPreferredSize(new Dimension(iChrW * DISPLAY_WIDTH, iHgt * 20));
		
		cardPanel.add(scrollPane);
		

		/**
		 *		Status Bar..
		 */

		Border loweredBevel = BorderFactory.createLoweredBevelBorder();
		int iGridX = 0;

		JPanel statusBar = new JPanel();
		statusBar.setLayout(gridbag);
		GridBagConstraints gbc = new GridBagConstraints();

		statusLabel = new JLabel(" ");
		statusLabel.setBorder(loweredBevel);

		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.gridx = iGridX;
		gbc.weightx = 1.0;
		gbc.insets = new Insets(2, 2, 2, 2); // top left bottom right

		statusBar.add(statusLabel, gbc);
		
		iGridX++;
		
		deviceLabel = new JLabel(" ");			// <-- (Set)
		deviceLabel.setBorder(loweredBevel);

		if ( (sDeviceName != null) && (sDeviceName.length() > 0) )
			statusLabel.setText(sDeviceName);
		
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.gridx = iGridX;
		gbc.weightx = 0.1;
		gbc.insets = new Insets(2, 2, 2, 2);	// top left bottom right
		
		statusBar.add(deviceLabel, gbc);
		
		iGridX++;
		

		statusPath = new JLabel(" ");			// <-- (Set)
		statusPath.setBorder(loweredBevel);
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.EAST;
		gbc.gridx = iGridX;
		gbc.weightx = 1.0;
		gbc.insets = new Insets(2, 2, 2, 2);	// top left bottom right
		
		statusBar.add(statusPath, gbc);
		
		dim = statusBar.getPreferredSize();
		statusBar.setPreferredSize(new Dimension(iChrW, (int)dim.getHeight()));
		statusBar.setMaximumSize(new Dimension(iChrW, (int)dim.getHeight()));

        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(cardPanel, BorderLayout.CENTER);
		mainPanel.add(statusBar, BorderLayout.SOUTH);

	} //}}}

	//{{{	IOBgThread
	/**
     * Handle commands and console output 
     */
	class IOBgThread extends Thread
	{
		public void run()
		{
		    //StringBuffer commandSb = null;
		    
			//System.out.println("IOBgThread run()");
			
            Process proc = null;
			OutputStream os = null;
			
			InputStream error_is = null;
			InputStream out_is = null;
			BufferedInputStream error_bis = null;
			BufferedInputStream out_bis = null;
			
			//char[] cAs = {'"', 'a', 'p', 'p', '.', 's', 't', 'a', 'r', 't', 'e', 'd', '"'};
			//String sAppStarted = new String(cAs);
			
			byte[] bZeroA = {(byte)0x0a};
			String sZeroA = new String(bZeroA);
			
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			byte[] writeBuf;

			int iBytesRead = 0;
			int iCount;
			int iExitVal;
			int iTotalBytes;
			int iG;
			int iLoc;
			int iLoc3 = 0;
			int iGotZeroCount = 0;
			
			
			int iLen;
			int iExitCount = 0;
			int iLEnd = -1;
			int iLenSave = 0;
			int iCurrentType = NORMAL;
			int iTmpLength = tempBuf.length;
			int iIndex;
			int iType;
			int iLineLen;
			int iLnLength;
			int iStart;
			int iLen2;
			
			int iBlockIndex = 0;
			int iBlockCharCount = 0;
			int iIdx;
			int iG3;
			int iT;
			int iZ;
			int iY;
			int iBIndex;
			int iClosest;
			int iInsertLoc;
			int iHalf;
			int iChunkLen;
			int iArrayIndex = 0;
			int iPartialLength = 0;
			int iBlockCount = 0;	
			int[] iBlock = new int[128];

			char cChr;
			
			Integer IVal;
			
			boolean bCompletePartialLine = false;
			boolean bLineBreakOut;
			boolean bSaveLen = false;
			boolean bContinued;
			boolean bOver;
			boolean bHitTrailing;
			boolean bBytesAvailable;
			boolean bFoundPhrase = false;
			boolean bCheckBlock;
			boolean bFirst = true;
			boolean bSplit = false;
			boolean bDoBreak = false;
			boolean bBlockSplit = false;
			boolean bStart;
			boolean bEndBreakOut = false;
			boolean bBreakOut = false;
			
			String outLineS;
			String sEndOne = "";
			String sEndTwo = "";
			String sEndThree = "tools>";
			String inLineS;
			String lineS = "";
			String outS;
			String sTest = "";
			StringBuffer sb;
			StringBuffer commandSb;
			
			long lCurrentTime;
			long lDifCount = 0;
			
			
			ArrayList locAr = new ArrayList(384);

			// Note:
			//
			// If we use: exec(new String[] {})
			// we can detect when the I/O is finished
			// using exitValue(), but we don't get any
			// command lines from the system.
			//
			// if we use exec(), exitValue() doesn't
			// work but we get the command lines from the system
			// which show useful information

			//System.out.println("\n\n(IOBgThread)commandS: '"+commandS+"'");
			//System.out.println("\nIOBgThread run()");

			
			SingletonClass sc = SingletonClass.getInstance();
			
/*			
			if ( sc.s_sCommand == null )
				System.out.println("sc.s_sCommand null");
			else
				System.out.println("sc.s_sCommand: '"+sc.s_sCommand+"'");
/**/			
			
/*			
			if ( commandS == null )
				System.out.println("commandS null");
			else
				System.out.println("commandS: '"+commandS+"'");
/**/


            //System.out.println("sUsePidLogcat: '"+sUsePidLogcat+"'");
/*            
            if ( packageNameS == null )
                System.out.println("packageNameS null");
            else
                System.out.println("packageNameS: '"+packageNameS+"'");
/**/

            // Moved out of try..            
			Runtime rt = Runtime.getRuntime();
				

			try
			{
			    
                //SingletonClass sc = SingletonClass.getInstance();
				//Runtime rt = Runtime.getRuntime();
				
                //if ( rt == null )
                    //System.out.println("rt null");
				
				
				if ( iOS == LINUX_MAC )
				{
					//proc = rt.exec(new String[] {"/bin/bash", "-c", commandS});
					proc = rt.exec(new String[] {"/bin/bash", "-c", sc.s_sCommand});
				}
				else
				{
					proc = rt.exec("cmd.exe");
                    
					//writeBuf = commandS.getBytes();
					writeBuf = sc.s_sCommand.getBytes();
					
					// Command..				
					os = proc.getOutputStream();
					os.write(writeBuf);
					os.flush();
				}

                // Give time to execute..				
				try
				{
				    Thread.sleep(500);
				    //Thread.sleep(750);
				    //Thread.sleep(1000);
				}
				catch (InterruptedException ie)
				{
				}

				error_is = proc.getErrorStream();
				out_is = proc.getInputStream();
				
				error_bis = new BufferedInputStream(error_is);
				out_bis = new BufferedInputStream(out_is);
				
/*
                if ( actionCommandS == null )
                    System.out.println("\nactionCommandS null");
                else
                    System.out.println("\nactionCommandS: '"+actionCommandS+"'");
/**/

				iTotalBytes = 0;
				
				int iWordLength = 0;
				int iWordStart = 0;
				int iSIdx;
				int iBlockIdx;
				int iSplitBlockCount;
				int iChrLoc = 0;
				int iBreakLength = DISPLAY_WIDTH / 2;
				
				//int iLoopCount = 0;
				iLoopCount = 0;
				
				boolean bInWord;
				String sPrevEnd = "";
				
				//String sT = "";
				long lCTM1;
				long lCTM2;
				long lDif = 0;
				
				lCurrentTime = System.currentTimeMillis();
				
                        while ( ! isInterrupted() )
                        {
                            //System.out.println("--TOP--");
                            iLoopCount++;
                            
/*                            
                            if ( sc.s_KillIOBgThread )
                                break;
/**/                            
                            // Kill for Logcat..					
                            //if ( bBreakOut )
                            if ( bEndBreakOut )
                            {
                                // End of line..
                                //System.out.println("bEndBreakOut breaking");
                                break;
                            }
                            
                            if ( bIOBgThreadBreak )
                            {
                                //System.out.println("bIOBgThreadBreak breaking");
                                break;
                            }
                            
                            if ( bBreakOut )
                            {
                                break;
                            }
                            
                            lineSb = new StringBuffer();
                            
                            if ( out_bis.available() > 0 )	// Check Output Stream..
                            {
                                lCTM1 = System.currentTimeMillis();
                                iBytesRead = out_bis.read(tempBuf, 0, iTmpLength);
                                //System.out.println("(Output Stream)iBytesRead: "+iBytesRead);
                                if ( iBytesRead == -1 )
                                {
                                    // Never gets this..
                                    break;
                                }
        
                                baos.write(tempBuf, 0, iBytesRead);
        
                                lineSb.insert(0, baos.toString());
                                lineSb.setLength(iBytesRead);
                                
                                //System.out.println("\nlineSb: '"+lineSb.toString()+"'");
                                
                                baos.reset();
                                
                                iTotalBytes += iBytesRead;
                                lCTM2 = System.currentTimeMillis();
                                lDif = lCTM2 - lCTM1;       // Output stream, time to read()
                                //System.out.println("Diff: "+lDif);
                            }
                            else if ( error_bis.available() > 0 )	// Check Error Stream..
                            {
                                iBytesRead = error_bis.read(tempBuf, 0, iTmpLength);
                                //System.out.println("(Error Stream)iBytesRead: "+iBytesRead);
                                if ( iBytesRead == -1 )
                                {
                                    // Never gets this..
                                    break;
                                }
                                
                                baos.write(tempBuf, 0, iBytesRead);
        
                                lineSb.insert(0, baos.toString());
                                lineSb.setLength(iBytesRead);
        
                                baos.reset();						
                                
                                iTotalBytes += iBytesRead;
                            }

/*                            
                            // Debbuging..					
                            if ( (lineSb != null) && (iBytesRead > 0) )
                            {
                                System.out.println();
                                char cTChr;
        
                                //if ( lineSb.length() > 500 )
                                //{
                                    for ( int g = 0; g < lineSb.length(); g++ )
                                    //for ( int g = 0; g < 500; g++ )
                                    {
                                        cTChr = (char)lineSb.charAt(g);
                                        if ( (cTChr < 0x20) || (cTChr > 0x7e) )
                                            System.out.print("["+Integer.toHexString((int)cTChr)+"]");
                                        else
                                            System.out.print(cTChr);
                                    }
                                //}
                                System.out.println();
                            }
/**/
        
/*
                            if ( lineSb == null )
                                System.out.println("(Before)lineSb null");
                            else
                                System.out.println("(Before)lineSb.length(): "+lineSb.length());
/**/
        
/*
                            if ( sUsePidLogcat == null )
                                System.out.println("sUsePidLogcat null");
                            else    
                                System.out.println("sUsePidLogcat: "+sUsePidLogcat);
/**/

                            if ( (sUsePidLogcat != null) && (sUsePidLogcat.equals("true")) )
                            {
                                Thread.sleep(5);
                            }
                            else
                            {
                                // Without this, console output
                                // can get really laggy and unresponsive..
                                if ( lineSb.length() < 4096 )
                                    Thread.sleep(10);
                                else
                                {
                                    
                                    //System.out.println("lDif: "+lDif);
                                    
                                    if ( lDif > 0 )     // Output stream, time to read()
                                    {
                                        lDifCount = 0;
                                        Thread.sleep(40);   // Was using
                                    }
                                    else
                                    {
                                        lDifCount++;
                                        Thread.sleep(20);     // Was using
                                    }
                                }
                            }
                            
                            //System.out.println("lDifCount: "+lDifCount);

                            if ( (lineSb != null) && (iBytesRead > 0) )
                            {
                                // Got data, so reset..                                
                                iLoopCount = 0;
                                iGotZeroCount = 0;


/*                                
                                // Check for '"app.started"'..
                                if ( lineSb.toString().contains(sAppStarted) )
                                {
                                    System.out.println("\nFound app.started");
                                    bFoundAppStarted = true;
                                    
                                    if ( resumeButton == null )
                                        System.out.println("resumeButton null");
                                    
                                    if ( resumeButton != null )
                                    {
                                        // Ghost Resume button..
                                        resumeButton.setEnabled(false);
                                    }
                                }
/**/                                
                                // Start of new block..
                                //System.out.println("=== NEW BLOCK ===");
                                //System.out.println("lineSb.length(): "+lineSb.length());
                                
                                bInWord = false;
                                iWordStart = 0;
                                boolean bFirst2;
                                int iLastZeroA = 0;
                                int iLoc2 = 0;
                                //int iLoc3 = 0;
                                int iLoc4 = 0;
                                int iLoc5 = 0;
                                int iLoc6 = 0;
                                StringBuffer endSb;
                                StringBuffer sB;
                                //StringBuffer sb;
                                //String sPrevEnd = "";
                                String sT = "";
                                String sT2 = "";
                                String sBlockEnd = "";
                                
                                //System.out.println("bBlockSplit: "+bBlockSplit);
                                if ( bBlockSplit )
                                {
                                    // Continue processing from previous block..
                                }
                                else
                                {
                                    // New block..
                                    iWordLength = 0;
                                }
        
                                // In Flutter there
                                // were some bad lead characters
                                // so kill any of those..
                                if ( lineSb.length() > 0 )
                                {
                                    while ( true )
                                    {
                                        if ( lineSb.charAt(0) > 0x7f )
                                        {
                                            lineSb.deleteCharAt(0);
                                            continue;
                                        }
                                        else
                                            break;
                                    }
                                }
/**/

                                iIdx = 0;
                                bDoBreak = false;
                                bSplit = false;
                                iChunkLen = lineSb.length();
                                //System.out.println("bBlockSplit: "+bBlockSplit);
                                //System.out.println("iWordLength: "+iWordLength);
                                //System.out.println("iWordStart: "+iWordStart);
                                
                                for ( ; ; iIdx++ ) 
                                {
                                    //System.out.println("--TOP--  iIdx: "+iIdx);
                                    if ( bDoBreak )
                                    {
                                        //System.out.println("--Breaking--");
                                        break;
                                    }
                                    
                                    //System.out.println("iChunkLen: "+iChunkLen);
                                    if ( iIdx >= iChunkLen )
                                    {
                                        // Hit end of block..
                                        cChr = ' ';
                                        if ( ((lineSb.length() - 1) >= 0) && ((lineSb.length() - 1) < lineSb.length()) )
                                        {
                                            cChr = lineSb.charAt(lineSb.length() - 1);
                                        }
                                        
                                        if ( (cChr == (char)0x0a) || (cChr == (char)0x0d) )
                                            ;
                                        else
                                        {
                                            bBlockSplit = true;
                                        }
                                        
                                        bDoBreak = true;
                                    }
                                    else
                                    {
                                        cChr = lineSb.charAt(iIdx);
                                        if ( Character.isWhitespace(cChr) )
                                        {
                                            // Whitespace..
                                            iWordLength = 0;	// Reset..
                                            bInWord = false;	// Reset..
                                        }
                                        else
                                        {
                                            // Character..
                                            if ( bInWord == false )
                                            {
                                                bInWord = true;
                                                iWordStart = iIdx;
                                            }
                                                
                                            iWordLength++;
                                            if ( iWordLength >= DISPLAY_BREAK_WIDTH )
                                            {
                                                bSplit = true;
                                            }
                                        }
                                    }
                                    
                                    //System.out.println("bSplit: "+bSplit);
                                    if ( bSplit )
                                    {
                                        // Split..
                                        //System.out.println("\n== SPLIT ==");
                                        bSplit = false;		// Reset..
                                        iSIdx = iWordStart;
                                        
                                        iSplitBlockCount = iWordLength;
                                        iWordLength = 0;	// Reset..
                                        
                                        //System.out.println("iSplitBlockCount: "+iSplitBlockCount);
                                        iChrLoc = 0;
                                        
                                        for ( ; ; iSIdx++, iSplitBlockCount++ )
                                        {
                                            if ( iSIdx >= iChunkLen )
                                            {
                                                // Hit end..
                                                cChr = lineSb.charAt(lineSb.length() - 1);
                                                if ( (cChr == (char)0x0a) || (cChr == (char)0x0d) )
                                                    ;
                                                else
                                                {
                                                    iWordLength = iSplitBlockCount;
                                                }
        
                                                bDoBreak = true;
                                                break;
                                            }
                                            
                                            cChr = lineSb.charAt(iSIdx);
                                            if ( Character.isWhitespace(cChr) )
                                            {
                                                // Done..
                                                break;
                                            }
                                            
                                            if ( ! Character.isLetterOrDigit(cChr) )
                                            {
                                                // Special character..
                                                iChrLoc = iSIdx;
                                                //System.out.println("(Special)iChrLoc: "+iChrLoc);
                                            }
                                            
                                            if ( iSplitBlockCount > iBreakLength )
                                            {
                                                //System.out.println("iSplitBlockCount > iBreakLength");
                                                if ( iChrLoc > (iSIdx - (iBreakLength / 2) ) )
                                                {
                                                    lineSb.insert((iChrLoc + 1), ' ');
                                                }
                                                else
                                                {
                                                    // No special characters found..
                                                    lineSb.insert((iSIdx + 1), ' ');
                                                }
                                                
                                                iChunkLen++;	// Adjust for added space..
                                                iSIdx += 1;		// Adjust past insert, plus loop increment..
                                                iChrLoc = 0;	// Reset..
                                                iSplitBlockCount = 0;
                                                
                                            }
                                        }	// End for..
                                        
                                        //System.out.println("Dropped out");
                                    }
                                }	// End for..
                                
                                //System.out.println("(A)Dropped out");
                                
                            }   // End if ( (lineSb != null) && (iBytesRead > 0) )
                            else
                            {
                                // Didn't get anything..
                                //System.out.println("Didn't get anything");
                                
                                iGotZeroCount++;
                                //System.out.println("(Nothing)iGotZeroCount: "+iGotZeroCount);
                                //System.out.println("iLoopCount: "+iLoopCount);
                                
                                //if ( iLoopCount > 3000 )
                                //{
                                    //bBreakOut = true;
                                //}
                            }
/**/	

//--------------------------------------------------------------------------------
// End test
//--------------------------------------------------------------------------------

/*
                            if ( commandPhrase == null )
                                System.out.println("commandPhrase null");
                            else
                                System.out.println("commandPhrase: '"+commandPhrase+"'");
/**/                                    
                                    
                            // Note:
                            //
                            // Sometimes it can catch the end prompt early,
                            // so we need an end test..
                            if ( iBytesRead > 0 )
                            {
                                //iGotZeroCount = 0;  // Reset..
                                
                                if ( bLogcatOn == false )
                                {
/*                                    
                                    if ( (commandPhrase != null) && (commandPhrase.length() > 0) )
                                    {
                                        //System.out.println("commandPhrase: '"+commandPhrase+"'");
                                        if ( lineSb.indexOf(commandPhrase) != -1 )
                                        {
                                            //System.out.println("--Found Phrase--");
                                            bFoundPhrase = true;
                                        }
                                    }
                                    else
                                    {
                                        // Prevent early end..
                                        if ( (lCurrentTime + 2000) < (long)System.currentTimeMillis() )
                                            ;   // Too early..
                                        else
                                            bFoundPhrase = true;
                                    }
/**/

                                    //System.out.println("lineSb.length(): "+lineSb.length());
                                    
                                    //if ( ((lineSb.length() - 1) >= 0) && (lineSb.charAt(lineSb.length() - 1) == '>') )
                                    //{
                                        lineS = lineSb.substring(0, lineSb.length());
    
                                        if ( lineS.length() > 5 )
                                        {
                                            if ( (lineS.length() - 5) < lineS.length() )
                                                sTest = lineS.substring(lineS.length() - 5);
                                            
                                            //System.out.println("bFoundPhrase: "+bFoundPhrase);
                                            //System.out.println("sTest: '"+sTest+"'");
                                            //if ( bFoundPhrase && (sTest.endsWith(">") || sTest.endsWith("$")) )
                                            if ( (sTest.endsWith(">") || sTest.endsWith("$")) )
                                            {
                                                //System.out.println("\nHit end");
                                                bEndBreakOut = true;
                                            }
                                        }
                                    //}
                                    
/*
                                    if ( sGradlewCommand == null )
                                        System.out.println("sGradlewCommand null");
                                    else
                                        System.out.println("sGradlewCommand: '"+sGradlewCommand+"'");
/**/                                
                                    //System.out.println("(Check)lineSb: '"+lineSb.toString()+"'");

/*                                    
                                    if ( (sGradlewCommand != null) && (lineSb.indexOf(sGradlewCommand) != -1) )
                                    {
                                        // Finish Progress Bar early..
                                        //System.out.println("--FOUND--");
                                        bFinished = true;
                                    }
/**/    

/*                                    
                                    if ( bGradleSelected && (! bFinished) )
                                    {
                                        if ( (lineSb.indexOf("preBuild") != -1) ||
                                            (lineSb.indexOf(":clean") != -1) )
                                        {
                                            // Finish Progress Bar early..
                                            bFinished = true;
                                        }
                                    }
/**/                                    
                                }
        
                                outLineS = "";	// Final line to be inserted..
                                bContinued = false;
                                iType = NORMAL;
                                iLineLen = lineSb.length();
                                
                                try
                                {
                                    doc = textPane.getStyledDocument();
        
                                    if ( bLogcatOn )
                                    {
                                        // Logcat output..
                                        bLineBreakOut = false;
                                        iIndex = 0;
                                        
                                        while ( true )
                                        {
                                            if ( bCompletePartialLine )
                                            {
                                                // Reset..
                                                bCompletePartialLine = false;
                                                
                                                // Capture rest of line..
                                                iStart = iIndex;
                                                bHitTrailing = false;
                                                
                                                for ( ;; iIndex++ )
                                                {
                                                    if ( iIndex >= iLineLen )
                                                        break;
                                                    
                                                    if ( bHitTrailing )
                                                    {
                                                        if ( Character.isWhitespace(lineSb.charAt(iIndex)) )
                                                            ;
                                                        else
                                                            break;
                                                    }
                        
                                                    if ( (lineSb.charAt(iIndex) == 0x0d) ||
                                                            (lineSb.charAt(iIndex) == 0x0a) )
                                                        bHitTrailing = true;
                                                }
        
                                                // Set up for output..
                                                if ( bHitTrailing )
                                                    bSaveLen = false;	// Reset..
                                                    
                                                iType = iCurrentType;
                                                if ( (iStart >= 0) && (iIndex >= 0) && (iIndex < lineSb.length()) )
                                                {
                                                    outLineS = lineSb.substring(iStart, iIndex);
                                                }
                                            }
                                            else
                                            {
                                                // Process line from Start..
                                                iLenSave = 0;	// Reset..
                                                
                                                // Set Type..
                                                if ( ((iIndex + 1) < iLineLen) &&
                                                    (lineSb.charAt(iIndex + 1) == '/') )
                                                {
                                                    // Start Characters, X/..
                                                    if ( lineSb.charAt(iIndex) == 'E' )
                                                        iType = ERROR;												
                                                    else if ( lineSb.charAt(iIndex) == 'W' )
                                                        iType = WARNING;
                                                    else
                                                        iType = NORMAL;
                                                }
                                                else
                                                {
                                                    // Not a "Start"..
                                                    iType = NORMAL;
                                                }
                                                
                                                iStart = iIndex;
                                                bOver = false;
                                                bHitTrailing = false;
                                                
                                                for ( ;; iIndex++ )
                                                {
                                                    if ( iIndex >= iLineLen )
                                                    {
                                                        bOver = true;
                                                        break;
                                                    }
                                                    
                                                    if ( bHitTrailing )
                                                    {
                                                        if ( Character.isWhitespace(lineSb.charAt(iIndex)) )
                                                            ;
                                                        else
                                                            break;	// Start of next line..
                                                    }
        
                                                    if ( (lineSb.charAt(iIndex) == 0x0d) ||
                                                            (lineSb.charAt(iIndex) == 0x0a) )
                                                        bHitTrailing = true;
                                                }
                                                
                                                if ( bOver )
                                                {
                                                    // Set up to draw what we have..
                                                    if ( (iStart >= 0) && (iStart < lineSb.length()) )
                                                    {
                                                        outLineS = lineSb.substring(iStart);
                                                    }
                                                    
                                                    iCurrentType = iType;	// Save for next draw..
                                                    
                                                    if ( ! bHitTrailing )
                                                    {
                                                        bCompletePartialLine = true;
                                                        bSaveLen = true;
                                                    }
                                                    
                                                    // Signal to break..
                                                    bLineBreakOut = true;
                                                }
                                                else
                                                {
                                                    if ( (iStart >= 0) && (iIndex >= 0) && (iIndex < lineSb.length()) )
                                                    {
                                                        outLineS = lineSb.substring(iStart, iIndex);
                                                    }
                                                }
                                            }
                                            
                                            // We should now have the full line
                                            // including any trailing 0x0d 0x0a
                                            iLen = doc.getLength();
        
                                            // Try to back up over any trailing 0x0d 0x0a
                                            // to append the partial string..									
                                            if ( (iLenSave > 0) && (iLen > 0) )
                                            {
                                                int iJ;
                                                String xS = "";
                                                for ( iJ = iLenSave; ; iJ-- )
                                                {
                                                    xS = doc.getText(iJ, 1);
                                                    if ( xS.equals(0x0d) || xS.equals(0x0a) )
                                                        ;
                                                    else
                                                        break;
                                                }
                                                
                                                iLen = iJ;
                                            }
                                            
                                            if ( iType == NORMAL )
                                                doc.insertString(iLen, outLineS, normalStyle);
                                            else if ( iType == WARNING )
                                                doc.insertString(iLen, outLineS, warningStyle);
                                            else if ( iType == ERROR )
                                                doc.insertString(iLen, outLineS, errorStyle);
                                            
                                            if ( bSaveLen )
                                                iLenSave = doc.getLength();
                                                
                                            if ( iLen > 0 )
                                            {
                                                // Keep all output in view..
                                                textPane.setCaretPosition(doc.getLength() - 1);
                                            }
                                            
                                            if ( bLineBreakOut )
                                                break;
                                            
                                        }	// End while..
                                        
                                        //System.out.println("Dropped out");
                                    }
                                    else
                                    {
                                        //System.out.println("Normal Output, iBytesRead: "+iBytesRead);
                                        //System.out.println("lineSb.length(): "+lineSb.length());
                                        // Normal output..
                                        if ( iBytesRead > 0 ) 
                                        {
                                            outS = lineSb.substring(0, lineSb.length());
                                            
                                            iLen = doc.getLength();
                                            
                                            // This can have issues.
                                            // It's most noticable when you do a Gradle 'Release'.
                                            // It gets the output strings, but doesn't do anything with them
                                            // until the end, when it dumps everything.
                                            // With the new SwingWorker it seems to be better.
        
                                            doc.insertString(iLen, outS, normalStyle);
                                            
                                            if ( iLen > 0 )
                                            {
                                                textPane.setCaretPosition(doc.getLength() - 1);
                                                textPane.repaint();
                                            }
                                        }
                                    }
                                }
                                catch (IllegalArgumentException iae)
                                {
                                    iae.printStackTrace();
                                }
                                catch (BadLocationException ble)
                                {
                                    ble.printStackTrace();
                                }
                            }
                            else    // if ( iBytesRead > 0 )
                            {
                                // No output..
                                
                                //System.out.println("(No output)iLoopCount: "+iLoopCount);
                                
                                
                                
                                if ( iOS == LINUX_MAC )
                                //if ( true )
                                {
                                    // If still getting data
                                    // gets Exception:
                                    // 'Exception: java.lang.IllegalThreadStateException: process hasn't exited'
                                    // When I/O is finished, does exitValue()..
                                    try
                                    {
                                        iExitVal = proc.exitValue();
                                        //System.out.println("iExitVal: "+iExitVal);

                                        // In cases like Install, it can quit before
                                        // the result gets output, so this give it more
                                        // time to output the results..                                        
                                        iExitCount++;
                                        if ( iExitCount > 10 )
                                        {
                                            break;
                                        }
                                    }
                                    catch (IllegalThreadStateException itse)
                                    {
                                        // Still getting data..
                                        //System.out.println(itse.toString());
                                    }
                                }
                            }
                            
                            iBytesRead = 0;
                            
                        }	// End while, while ( ! isInterrupted() )
                        
/*                        
                        if ( sc.s_ShowProgressBar )
                        {
                            if ( tProgressJFrame != null )
                            {
                                tProgressJFrame.dispose();
                                tProgressJFrame = null;
                            }
                        }
/**/ 

				bIOBgThreadBreak = false;    // Reset..
				
				//System.out.println("====IOBgThread dropped out====");
			}
			catch (Exception e)
			{
				System.out.println("Exception: "+e.toString());
				e.printStackTrace();
			}
			finally
			{
				try
				{
					if ( error_bis != null )
						error_bis.close();
					
					if ( out_bis != null )
						out_bis.close();
					
					if ( error_is != null )
						error_is.close();
					
					if ( out_is != null )
						out_is.close();
 				}
				catch (IOException ioe)
				{
				}
			}

			//System.out.println("Calling destroy()");
			proc.destroy();

			while ( true )
			{
				try
				{
					iExitVal = proc.waitFor();
					//System.out.println("iExitVal: "+iExitVal);
					break;
				}
				catch (InterruptedException ie)
				{
					System.out.println("Exception: "+ie.toString());
				}
			}

/*			
			if ( bKillLogcat )
			{
				if ( bKillLogcat )
					bKillLogcat = false;	// Reset..
				
				killAdbBgThread = new KillAdbBgThread();
				killAdbBgThread.start();
			}
/**/
			//System.gc();

			bIOBgThreadFinished = true;
			
			// Reset..
			bBreakOut = false;
			bLogcatOn = false;
			bFinished = true;

			//System.out.println("\nExiting IOBgThread run()");
			
		}
	}	//}}}

	//{{{   checkAttr()
	public boolean checkAttr(String sIn)
	{
	    char cChr;
	    
	    if ( sIn.length() != 10 )
	        return false;
	    
	    // Leading 'l' is a symlink but we treat it as a directory..
	    if ( sIn.startsWith("d") || sIn.startsWith("l") ||
	        sIn.startsWith("c") || sIn.startsWith("-") )
	    {
	        for ( int iJ = 1; iJ < sIn.length(); iJ++ )
	        {
	            cChr = sIn.charAt(iJ);
	            if ( cChr == 'r' || cChr == 'w' ||
                        cChr == 'x' || cChr == '?' ||
                        cChr == '-' )
                {
	                ;
	            }
	            else
	                return false;
	        }
	        
	        return true;
	    }
	    else
	        return false;
	}    //}}}
	
	//{{{	UpdateFileBrowserBgThread
	class UpdateFileBrowserBgThread extends Thread
	{
		public void run()
		{
			//System.out.println("UpdateFileBrowserBgThread run()");
			
			StringTokenizer st;
            StringBuffer sb = new StringBuffer();
            StringBuffer sB;
            StringBuffer tokSb;
            StringBuffer sNameSb = null;
            String sT = "";
            String sSymLinkPath = "";
            int iTokIndex;
            int iLoc = 0;
            int iLoc2 = 0;
            boolean bIsDir = false;
            boolean bHitlstat = false;
/*            
            if ( sCurrentPath == null )
                System.out.println("sCurrentPath null");
            else
                System.out.println("sCurrentPath: '"+sCurrentPath+"'");
/**/

            if ( iOS == LINUX_MAC )
            {
                sb.append("export PATH=${PATH}:");
                sb.append(androidSdkPathS);
                sb.append("/platform-tools");
                sb.append(";adb ");
            }
            else
            {
                sb.append("SET PATH=");
                sb.append(androidSdkPathS);
                sb.append("/platform-tools");
                sb.append(";%PATH%");
                sb.append("&&adb ");
            }

            if ( (sDeviceName != null) && (sDeviceName.length() > 0) )
            {
                sb.append("-s ");
                sb.append(sDeviceName);
                sb.append(" ");
            }
/**/
            
            sb.append("shell ls -l");

/*            
            if ( sSelectedMenu == null )
                System.out.println("sSelectedMenu null");
            else
                System.out.println("sSelectedMenu: '"+sSelectedMenu+"'");
/**/

            commandResultS = "";
            if ( (sCurrentPath != null) && (sCurrentPath.length() > 0) )
            {
                sb.append(" ");
                if ( sCurrentPath.contains(" ") )
                {
                    sb.append("'");
                    sb.append(sCurrentPath);
                    sb.append("'");
                }
                else
                    sb.append(sCurrentPath);
                
                if ( (sSelectedMenu != null) && ((sSelectedMenu.equals("Pull file")) ||
                        (sSelectedMenu.equals("Delete file")) ||
                        (sSelectedMenu.equals("Push file"))) )
                    sb.append("/");
            }
                
            if ( iOS == WINDOWS )
                sb.append("\n");
            
            //System.out.println("(Command)sb: '"+sb.toString()+"'");
            bCommandFinished = false;
            sInternalCommand = sb.toString();
            commandBgThread = new CommandBgThread();
            commandBgThread.start();

            // Wait for Thread to finish..
            while ( true )
            {
                try
                {
                    //Thread.sleep(333);
                    Thread.sleep(500);
                }
                catch (InterruptedException ie)
                {
                }

                if ( bCommandFinished )
                    break;
            }

            
/*
            if ( commandResultS == null )
                System.out.println("commandResultS null");
            else
            {
                //System.out.println("============================================");
                //System.out.println("commandResultS: '"+commandResultS+"'");
                System.out.println("commandResultS.length(): "+commandResultS.length());
            }
/**/ 

            // ls: /cache/: Permission denied
            // lstat '/dev//mem' failed: Permission denied
            
            if ( (commandResultS != null) && (commandResultS.length() > 0) )
            {
                // Check for failed..
                iLoc = commandResultS.indexOf("total ");
                iLoc2 = commandResultS.lastIndexOf("Permission denied");
                
                if ( (commandResultS.contains("opendir failed")) ||
                    ((commandResultS.contains("total 0") && (commandResultS.length() < 20))) ||
                    (commandResultS.contains("No such file")) ||
                    ((iLoc2 != -1) && (commandResultS.length() < 100)) )
                {
                    //System.out.println("Failed");
                    
                    // Signal fail..
                    bFileBrowserUpdateOkay = false;

                    if ( fileBrowserJList != null )
                        fileBrowserJList.clearSelection();
/*                    
                    if ( sCurrentPath == null )
                        System.out.println("sCurrentPath null");
                    else
                        System.out.println("sCurrentPath: '"+sCurrentPath+"'");
/**/
                        
                    //System.out.println("(Failed)sCurrentPath: '"+sCurrentPath+"'");
                    // Go back one level..
                    // Like:  '/dev/rpipe'
                    iLoc = sCurrentPath.lastIndexOf("/");
                    if ( (iLoc != -1) && (iLoc < sCurrentPath.length()) )
                    {
                        sCurrentPath = sCurrentPath.substring(0, iLoc);
                    }
                    
                    if ( fileBrowserJList != null )
                        fileBrowserJList.clearSelection();
                    
                    //System.out.println("(Final)sCurrentPath: '"+sCurrentPath+"'");

                    // Leave intact if it fails..                    
                    // Signal failed..
                    //fileToksAr = new ArrayList();
                }
                else
                {
                    //System.out.println("OK");
                    
                    // Signal success..
                    bFileBrowserUpdateOkay = true;
                
                    fileToksAr = new ArrayList();
                    st = new StringTokenizer(commandResultS);
                    int iCount = st.countTokens();
                    //System.out.println("iCount: "+iCount);
                    
                    tokSa = new String[iCount];
                    
                    //      [java] drwxrwx--- root     sdcard_r          2020-02-27 16:39 Digital Editions
                    
                    for ( iTokIndex = 0; st.hasMoreTokens(); iTokIndex++ )
                    {
                        sT = st.nextToken();
                        sT = sT.trim();
                        if ( iTokIndex < tokSa.length )
                            tokSa[iTokIndex] = sT;
                        //System.out.println("["+iTokIndex+"]: '"+tokSa[iTokIndex]+"'");
                    }
                    
                    // Skip any leading, like:  'total 18198'..
                    for ( iTokIndex = 0; iTokIndex < iCount; )
                    {
                        if ( iTokIndex < tokSa.length )
                        {
                            if ( ((tokSa[iTokIndex].contains("-")) && (tokSa[iTokIndex].length() == 10)) ||
                                    ((tokSa[iTokIndex].startsWith("d")) && (tokSa[iTokIndex].length() == 10)) ||
                                    ((tokSa[iTokIndex].startsWith("l")) && (tokSa[iTokIndex].length() == 10)) ||
                                    ((tokSa[iTokIndex].startsWith("c")) && (tokSa[iTokIndex].length() == 10)) ||
                                    ((tokSa[iTokIndex].startsWith("-")) && (tokSa[iTokIndex].length() == 10)) )
                                break;
                        }
                        else
                            break;
                            
                        iTokIndex++; 
                    }

                    // Note:
                    // In this new case, it didn't get it right
                    // It only showed 'Screen' and wouldn't let you see
                    // the contents..
                    
                    // drwxrwx--- 2 root everybody 3452 2023-05-10 11:52 Screen recordings
                    
                    
                     // crw-rw-rw- system   graphics  10,  42 2021-07-27 15:53 ion
                     //
                     // lstat '/dev//kmem' failed: Permission denied
                     //
                     // crw------- root     root       1,  11 2021-07-27 15:53 kmsg                        
                    
                    // lrwxrwxrwx root     root              2021-07-17 10:48 bin -> /system/bin
                    // -rwxr-x--- root     root          160 1969-12-31 19:00 init.recovery.board.rc
                    // drwxrwx--x system   system            2014-12-31 20:51 productinfo
                    
                    //System.out.println("\ntokSa.length: "+tokSa.length);
                    bHitlstat = false;
                    boolean bDidFirst = false;
                    boolean bStartedNm = false;
                    boolean bSkipToNext = false;
                    boolean bHitQuestion = false;
                    
//BREAK_OUT:                        
                    for ( ; iTokIndex < iCount; )
                    {
                        //System.out.println("--TOP-- iTokIndex: "+iTokIndex);
                        //sNameSb = new StringBuffer();
                        bHitQuestion = false;
                        
                        // At first token..
                        if ( iTokIndex < tokSa.length )
                        {
                            bIsDir = false;
                            //System.out.println("["+iTokIndex+"]: '"+tokSa[iTokIndex]+"'");
                            if ( ((tokSa[iTokIndex].startsWith("d")) && (tokSa[iTokIndex].length() == 10)) ||
                                ((tokSa[iTokIndex].startsWith("l")) && (tokSa[iTokIndex].length() == 10)) )
                            {
                                // "Directory"..
                                bIsDir = true;
                            }
    
                            // Skip to token before name..                        
                            while ( true )
                            {
                                iTokIndex++;
                                //System.out.println("Found :");
                                if ( iTokIndex < tokSa.length )
                                {
                                    if ( tokSa[iTokIndex].equals("?") )
                                    {
                                        bHitQuestion = true;
                                    }
                                    else if ( tokSa[iTokIndex].contains(":") )
                                    {
                                        break;
                                    }
                                    else
                                    {
                                        // Hit name..
                                        if ( bHitQuestion )
                                        {
                                            iTokIndex--;    // Adjust for next increment..
                                            break;
                                        }
                                    }
                                }
                                else
                                    break;
                            }

                            // [8]: '11:52'
                            // [9]: 'Screen'
                            // [10]: 'recordings'
                            // [11]: 'drwxrwx---'
                            
                            iTokIndex++;    // Get past '19:00'..
                            //System.out.println("(At name)iTokIndex: "+iTokIndex);

                            // At name..                            
                            bStartedNm = false;
                            bSkipToNext = false;
                            sSymLinkPath = "";
                            sNameSb = new StringBuffer();
                            
                            for ( int iZ = iTokIndex; iZ < tokSa.length; )
                            {
                                // Look for end leading token 'drwxrwx---'..
                                // Leading 'l' is a symlink but we treat it as a directory..
                                if ( checkAttr(tokSa[iZ]) )
                                {
                                    // Hit token after name..
                                    iTokIndex = iZ;
                                    //System.out.println("(After lead)iTokIndex: "+iTokIndex);
                                    break;
                                }
                                else if ( tokSa[iZ].equals("lstat") )
                                {
                                    // Skip everything till we hit new start..
                                    //System.out.println("\n---HIT lstat");
                                    //bHitlstat = true;
                                    bSkipToNext = true;
                                }
                                else if ( iZ == (tokSa.length - 1) )
                                {
                                    // Last token in block..
                                    if ( bSkipToNext )
                                    {
                                        break;
                                    }
                                    else
                                    {
                                        if ( bStartedNm )
                                            sNameSb.append(" ");
                                        
                                        if ( iZ < tokSa.length )
                                            sNameSb.append(tokSa[iZ]);
                                        break;
                                    }
                                }
                                else
                                {
                                    if ( bSkipToNext )
                                        ;
                                    else
                                    {
                                        if ( bStartedNm )
                                        {
                                            // Second or more..
                                            // 12:49 vendor -> /system/vendor
                                            // 11:52 Screen recordings
                                            
                                            //if ( tokSa[iZ].startsWith("->") )
                                            if ( (iZ < tokSa.length) && (tokSa[iZ].startsWith("->")) )
                                            {
                                                // Symlink, so treat next token as the new path
                                                if ( (iZ + 1) < tokSa.length )
                                                    sSymLinkPath = tokSa[iZ + 1];
                                                bSkipToNext = true;
                                                //iZ += 2;
                                                //break;
                                            }
                                            else
                                            {
                                                sNameSb.append(" ");
                                                if ( iZ < tokSa.length )
                                                    sNameSb.append(tokSa[iZ]);
                                            }
                                        }
                                        else
                                        {
                                            // First name token..
                                            if ( iZ < tokSa.length )
                                                sNameSb.append(tokSa[iZ]);
                                            bStartedNm = true;
                                        }
                                    }
                                }
                                
                                iZ++;   // Next..
                            }   // End for..

                            if ( (sNameSb != null) && (sNameSb.length() > 0) )
                            {
                                if ( bIsDir )
                                {
                                    tokSb = new StringBuffer();
                                    tokSb.append("[");
                                    tokSb.append(sNameSb.toString());
                                    //tokSb.append("]");
                                    tokSb.append("]~");
                                    tokSb.append(sSymLinkPath);
                                    
                                    //System.out.println("(Add): '"+tokSb.toString()+"'");
                                    fileToksAr.add((String)tokSb.toString());
                                }
                                else
                                {
                                    tokSb = new StringBuffer();
                                    sT = sNameSb.toString();
                                    sT = sT.trim();
                                    tokSb.append(sT);
                                    tokSb.append("~");
                                    tokSb.append(sSymLinkPath);
                                    
                                    //System.out.println("(Add): '"+sT+"'");
                                    fileToksAr.add((String)tokSb.toString());
                                    //fileToksAr.add((String)sT);
                                }
                            }
                        }
                    }   // End for..
                }
            }
                            
                            
                            
                            
            //System.out.println("\nsNameSb: '"+sNameSb.toString()+"'");
            //System.out.println("tokSa["+iTokIndex+"]: '"+tokSa[iTokIndex]+"'");

            //System.out.println("(1)sNameSb: '"+sNameSb.toString()+"'");
            
            //      [java] drwxrwx--- root     sdcard_r          2020-02-27 16:39 Digital Editions

            // 12:49 vendor -> /system/vendor   
            // 12:49 storage
            // 16:39 Digital Editions
            //System.out.println("tokSa.length: "+tokSa.length);
 
/*
            if ( fileToksAr == null )
                System.out.println("fileToksAr null");
            else
            {
                System.out.println("fileToksAr.size(): "+fileToksAr.size());
                for ( int iX = 0; iX < fileToksAr.size(); iX++ )
                    System.out.println("fileToksAr["+iX+"]: '"+(String)fileToksAr.get(iX)+"'");
            }
/**/

            bUpdateFileBrowserFinished = true;
            //System.out.println("Exiting UpdateFileBrowserBgThread");
		}
	}    //}}}
	
	//{{{	GetDevicesBgThread
	class GetDevicesBgThread extends Thread
	{
		public void run()
		{
			//System.out.println("GetDevicesBgThread run()");
			int iLoc;
			int iLoc2;
			int iLoc3;
			int iStart;
			int iEnd;
			int iIndex = 0;
			int iLongest = 0;
			String sDevice = "";
			
			DevicesAr = new ArrayList();
	
			//System.out.println("androidSdkPathS: '"+androidSdkPathS+"'");
			
			StringBuffer sb = new StringBuffer();
			
			if ( iOS == LINUX_MAC )
			{
				sb.append("export PATH=${PATH}:");
				sb.append(androidSdkPathS);
				sb.append("/platform-tools");
				
				//sb.append(";adb kill-server");
				//sb.append(";adb start-server");
				sb.append(";adb devices");
			}
			else
			{
				sb.append("SET PATH=");
				sb.append(androidSdkPathS);
				sb.append("/platform-tools");
				sb.append(";%PATH%");
				
				//sb.append("&&adb kill-server");
				//sb.append("&&adb start-server");
				sb.append("&&adb devices");
				sb.append("\n");
			}
	

            //System.out.println("sb: '"+sb.toString()+"'");
            bCommandFinished = false;
            sInternalCommand = sb.toString();
            commandBgThread = new CommandBgThread();
            commandBgThread.start();

			// Wait for Thread to finish..
			while ( true )
			{
				try
				{
					Thread.sleep(333);
				}
				catch (InterruptedException ie)
				{
				}

				if ( bCommandFinished )
					break;
			}

/*			
            System.out.println();
            char cTChr;
            
            for ( int g = 0; g < commandResultS.length(); g++ )
            {
                cTChr = (char)commandResultS.charAt(g);
                if ( (cTChr < 0x20) || (cTChr > 0x7e) )
                    System.out.print("["+Integer.toHexString((int)cTChr)+"]");
                else
                    System.out.print(cTChr);
            }
            System.out.println();
/**/

/*
			if ( commandResultS == null )
				System.out.println("(devices)commandResultS null");
			else
				System.out.println("(devices)commandResultS: '"+commandResultS+"'");
/**/
				
			int iG;
			if ( (commandResultS != null) && (commandResultS.length() > 0) )
			{
				iLoc = commandResultS.indexOf("attached");
				if ( iLoc != -1 )
				{
					iLoc2 = iLoc;
					while ( true )
					{
						iLoc2 = commandResultS.indexOf("device", iLoc2);
						if ( iLoc2 != -1 )
						{
							for ( iG = (iLoc2 - 1); Character.isWhitespace(commandResultS.charAt(iG)); iG--);
							iEnd = iG + 1;
							
							for ( ; ! Character.isWhitespace(commandResultS.charAt(iG)); iG-- );
							iStart = iG + 1;
							
							if ( (iStart >= 0) && (iEnd < commandResultS.length()) )
							    sDevice = commandResultS.substring(iStart, iEnd);
							sDevice = sDevice.trim();
							
							//System.out.println("(ADD)sDevice: '"+sDevice+"'");
							// Longest first..
							if ( sDevice.length() > iLongest )
							{
								DevicesAr.add(0, (String)sDevice);
								iLongest = sDevice.length();
							}
							else
							{
								DevicesAr.add(iIndex, (String)sDevice);
							}
							
							iIndex++;
						}
						else
							break;
						
						iLoc2++;
					}
				}
			}

/*			
            if ( DevicesAr == null )
                System.out.println("DevicesAr null");
            else
            {
                for ( int iJ = 0; iJ < DevicesAr.size(); iJ++ )
                    System.out.println("["+iJ+"]: '"+(String)DevicesAr.get(iJ)+"'");
            }
/**/

			bDevicesFinished = true;
			//completeLatch.countDown();
			
		}
	}	//}}}

	//{{{   FindSDcardBgThread
	class FindSDcardBgThread extends Thread
	{
		public void run()
		{
			//System.out.println("FindSDcardBgThread run()");
	    
            StringBuffer sb = new StringBuffer();
            StringBuffer sB;
            StringBuffer pathSb;
            StringTokenizer st;
            
            String sT = "";
            String sLead = "";
            String sRootPath = "";
            
            String[] tSa;
            int iCount = 0;
            int iTokIndex = 0;
            int iLoc2 = 0;
            int iLoc3 = 0;
            char cTChr;
            
            if ( iOS == LINUX_MAC )
            {
                sb.append("export PATH=${PATH}:");
                sb.append(androidSdkPathS);
                sb.append("/platform-tools");
                sb.append(";adb ");
            }
            else
            {
                sb.append("SET PATH=");
                sb.append(androidSdkPathS);
                sb.append("/platform-tools");
                sb.append(";%PATH%");
                sb.append("&&adb ");
            }
            
            if ( (sDeviceName != null) && (sDeviceName.length() > 0) )
            {
                sb.append("-s ");
                sb.append(sDeviceName);
                sb.append(" ");
            }

            sb.append("shell ls ");
            sLead = sb.toString();      // Save..
            
            sb.append("-l /sdcard");

            if ( iOS == WINDOWS )
                sb.append("\n");

            //System.out.println("(Command)sb: '"+sb.toString()+"'");
            
            bCommandFinished = false;
            sInternalCommand = sb.toString();
            commandBgThread = new CommandBgThread();
            commandBgThread.start();

            // Wait for Thread to finish..
            while ( true )
            {
                try
                {
                    Thread.sleep(333);
                }
                catch (InterruptedException ie)
                {
                }

                if ( bCommandFinished )
                    break;
            }

            //System.out.println("commandResultS: "+commandResultS);
 
            // lrw-r--r-- 1 root root 21 2008-12-31 10:00 /sdcard -> /storage/self/primary
            iLoc2 = commandResultS.indexOf("->");
            if ( iLoc2 != -1 )
            {
                iLoc3 = commandResultS.indexOf("/", (iLoc2 + 4));
                if ( iLoc3 != -1 )
                {
                    if ( ((iLoc2 + 2) >= 0) && (iLoc3 < commandResultS.length()) )
                        sRootPath = commandResultS.substring((iLoc2 + 2), iLoc3);
                    sRootPath = sRootPath.trim();
                }
            }

            sb = new StringBuffer();
            sb.append(sLead);
            sb.append(sRootPath);
            
            if ( iOS == WINDOWS )
                sb.append("\n");
            
            //System.out.println("(Command)sb: '"+sb.toString()+"'");
            
            bCommandFinished = false;
            sInternalCommand = sb.toString();
            commandBgThread = new CommandBgThread();
            commandBgThread.start();

            // Wait for Thread to finish..
            while ( true )
            {
                try
                {
                    Thread.sleep(333);
                }
                catch (InterruptedException ie)
                {
                }

                if ( bCommandFinished )
                    break;
            }

            st = new StringTokenizer(commandResultS);
            iCount = st.countTokens();
            //System.out.println("iCount: "+iCount);
            tSa = new String[iCount];
            
            for ( iTokIndex = 0; st.hasMoreTokens(); iTokIndex++ )
            {
                sT = st.nextToken();
                sT = sT.trim();
                //System.out.println("sT: '"+sT+"'");    
                if ( (tSa != null) && (iTokIndex < tSa.length) )
                    tSa[iTokIndex] = sT;    // Like:  '004E-A507'
            }

            for ( int iM = 0; iM < tSa.length; iM++ )
            {
                //System.out.println("--TOP-- iM: "+iM);
                sb = new StringBuffer();
                
                pathSb = new StringBuffer();
                pathSb.append(sRootPath);
                
                sb.append(sLead);  
                sb.append(sRootPath);
                sb.append('/');
                sb.append(tSa[iM]);

                // Prevent permission error..                
                if ( tSa[iM].equals("emulated") )
                {
                    sb.append("/0");
                    pathSb.append("/emulated/0");
                }
                else
                {
                    pathSb.append("/");
                    pathSb.append(tSa[iM]);
                }
                
                sInternalCommand = sb.toString();
                
                if ( iOS == WINDOWS )
                    sInternalCommand = sInternalCommand + "\n";
                
                //System.out.println("(Command)sb: '"+sb.toString()+"'");
                
                bCommandFinished = false;
                commandBgThread = new CommandBgThread();
                commandBgThread.start();
    
                // Wait for Thread to finish..
                while ( true )
                {
                    try
                    {
                        Thread.sleep(333);
                    }
                    catch (InterruptedException ie)
                    {
                    }
    
                    if ( bCommandFinished )
                        break;
                }

                if ( commandResultS.contains("DCIM") )
                {
                    sb.append("/DCIM");
                    pathSb.append("/DCIM");

                    //System.out.println("\n(Command)sb: '"+sb.toString()+"'");
                    
                    bCommandFinished = false;
                    sInternalCommand = sb.toString();
                    commandBgThread = new CommandBgThread();
                    commandBgThread.start();
        
                    // Wait for Thread to finish..
                    while ( true )
                    {
                        try
                        {
                            Thread.sleep(333);
                        }
                        catch (InterruptedException ie)
                        {
                        }
        
                        if ( bCommandFinished )
                            break;
                    }
                    
                    if ( commandResultS.contains("Screenshots") ) 
                    {
                        pathSb.append("/Screenshots");
                        sScreenshotPath = pathSb.toString();
                        //System.out.println("\nsScreenshotPath: '"+sScreenshotPath+"'");
                    }
                    else if ( commandResultS.contains("Camera") )
                    {
                        pathSb.append("/Camera");
                        sCameraPath = pathSb.toString();
                        //System.out.println("\nsCameraPath: '"+sCameraPath+"'");
                    }
                    
                    if (  tSa[iM].equals("emulated") || tSa[iM].equals("self") )
                        ;
                    else
                    {
                        // 'sSDcardPath'..
                        sB = new StringBuffer();
                        sB.append(sRootPath);
                        sB.append('/');
                        sB.append(tSa[iM]);
                        
                        sSDcardPath = sB.toString();
                        //System.out.println("sSDcardPath: '"+sSDcardPath+"'");
                    }
                }
                
            }   // End for..
		}
	}    //}}}

	//{{{   WirelessDisconnect()    
	private void WirelessDisconnect()
	{
	    //System.out.println("");
	    //System.out.println("WirelessDisconnect()");
	    
        bWirelessEnabled = false;
        
        bDisconnectWirelessFinished = false;
        disconnectWirelessBgThread = new DisconnectWirelessBgThread();
        disconnectWirelessBgThread.start();

        while ( true )
        {
            try
            {
                Thread.sleep(333);
            }
            catch (InterruptedException ie)
            {
            }
        
            if ( bDisconnectWirelessFinished )
                break;
        }
        
        if ( (sDeviceName != null) && (sDeviceName.length() > 0) )
        {
            int iLoc = sDeviceName.indexOf(":");
            if ( iLoc != -1 )
            {
                // Disconnected, so prevent it
                // from using wireless Id with '-s'..
                sDeviceName = "";
            }
        }

        //completeLatch = new CountDownLatch(1);
        
        bDevicesFinished = false;
        getDevicesBgThread = new GetDevicesBgThread();
        getDevicesBgThread.start();
        
        while ( true )
        {
            try
            {
                Thread.sleep(333);   
            }
            catch (InterruptedException ie)
            {
            }
            
            if ( bDevicesFinished )
                break;
        }
        
/*        
        try
        {
            completeLatch.await();
        }
        catch (InterruptedException ie)
        {
        }
/**/

        if ( (DevicesAr != null) && (DevicesAr.size() > 1) )
        {
            // Open Dialog..
            selectDeviceDialog();
        }
        else
        {
            // Clear status bar..
            deviceLabel.setText(" ");
        }

        // Reset..        
        SingletonClass sc = SingletonClass.getInstance();
        sc.s_WirelessConnected = false;

	    
	    //System.out.println("Exiting WirelessDisconnect()");
	}    //}}}
	
	//{{{	InitWirelessBgThread
	class InitWirelessBgThread extends Thread
	{
		public void run()
		{
			//System.out.println("InitWirelessBgThread run()");

			StringBuffer internalSb = new StringBuffer();
			StringBuffer wIdSb = new StringBuffer();
			StringBuffer mSb;
			String sT = "";
			String sDevName = "";
			int iLoc1;
			int iLoc2;
			int iLoc3;
			int iEnd;
			int iStart;
			int iIndex;
			int iLongest = 0;
			
			ConnectDevicesAr = new ArrayList();

			boolean bOK = false;
			
			
			bOK = false;
			internalSb = new StringBuffer();
			
/*			
			if ( androidSdkPathS == null )
			    System.out.println("androidSdkPathS null");
			else
			    System.out.println("androidSdkPathS: "+androidSdkPathS);
/**/

			if ( iOS == LINUX_MAC )
			{
				internalSb.append("export PATH=${PATH}:");
				internalSb.append(androidSdkPathS);
				internalSb.append("/platform-tools");

				internalSb.append(";adb devices");
			}
			else
			{
				internalSb.append("SET PATH=");
				internalSb.append(androidSdkPathS);
				internalSb.append("/platform-tools");
				internalSb.append(";%PATH%");
				
				internalSb.append("&&adb devices");
				internalSb.append("\n");
			}
			
			//System.out.println("internalSb: "+internalSb.toString());

			bInternalFinished = false;		
			sInternalCommand = internalSb.toString();
			commandBgThread = new CommandBgThread();
			commandBgThread.start();
	
			// Wait for Thread to finish..
			while ( true )
			{
				try
				{
					Thread.sleep(333);
				}
				catch (InterruptedException ie)
				{
				}

				if ( bInternalFinished )
					break;
			}
			
			// Mix:
			// YT910A2GPY      device
			// emulator-5554   device
/*			
			if ( commandResultS == null )
				System.out.println("(Devices)commandResultS null");
			else
				System.out.println("(Devices)commandResultS: '"+commandResultS+"'");
/**/

			if ( (commandResultS != null) && (commandResultS.length() > 0) )
			{
				iIndex = 0;
				DevicesAr = new ArrayList();
				iLoc2 = commandResultS.indexOf("attached");
				if ( iLoc2 != -1 )
				{
					while ( true )
					{
						iLoc1 = commandResultS.indexOf("device", iLoc2);
						if ( iLoc1 != -1 )
						{
							// Scan back and get device name..
							for ( iLoc3 = iLoc1 - 1; Character.isWhitespace(commandResultS.charAt(iLoc3)); iLoc3-- );
							iEnd = iLoc3 + 1;
							
							for ( ; ! Character.isWhitespace(commandResultS.charAt(iLoc3)); iLoc3-- );
							iStart = iLoc3 + 1;
							if ( (iStart >= 0) && (iEnd < commandResultS.length()) )
							{
							    if ( (iStart >= 0) && (iEnd < commandResultS.length()) )
							        sDevName = commandResultS.substring(iStart, iEnd);
                                sDevName = sDevName.trim();
                            }
                            
							//System.out.println("sDevName: '"+sDevName+"'");
							
							if ( sDevName.startsWith("emulator") )
								;
							else
							{
								// Device..
								bOK = true;
								ConnectDevicesAr.add((String)sDevName);
								//System.out.println("(Add): '"+sDevName+"'");
							}

							// All, longest first..
							if ( sDevName.length() > iLongest )
							{
								DevicesAr.add(0, (String)sDevName);
								iLongest = sDevName.length();
							}
							else
							{
								DevicesAr.add(iIndex, (String)sDevName);
							}
							
							iIndex++;
						}
						else
							break;
						
						iLoc1++;
						iLoc2 = iLoc1;
					}
				}
			}

			//System.out.println("bOK: "+bOK);
			if ( bOK )
			{
				bOK = false;
/*				
				if ( DevicesAr != null )
				{
				    for ( int iX = 0; iX < DevicesAr.size(); iX++ )
				        System.out.println("(DevicesAr)["+iX+"]: "+(String)DevicesAr.get(iX));
				}
/**/				
				
				if ( (ConnectDevicesAr != null) && (ConnectDevicesAr.size() > 0) )
				{
					if ( ConnectDevicesAr.size() == 1 )
					{
						// Single device..
						iWirelessErrorCode = 0;
						sDeviceName = (String)ConnectDevicesAr.get(0);
						sDeviceName = sDeviceName.trim();
						//System.out.println("(Single device)sDeviceName: '"+sDeviceName+"'");
					}
					else
					{
						// More than one device,
						// put up Select Dialog..
						iSelectMode = SELECT_CONNECT;
						bSelectFinished = false;
						
                        if ( (DevicesAr	!= null) && (DevicesAr.size() > 0) )
                        {
                            if ( DevicesAr.size() > 1 )
                            {
                                // Open Dialog..
                                selectDeviceDialog();
                                
                                // Wait for selection..
                                while ( true )
                                {
                                    try
                                    {
                                        Thread.sleep(333);
                                    }
                                    catch (InterruptedException ie)
                                    {
                                    }
                    
                                    if ( bSelectFinished )
                                        break;
                                }
                            }
                            else
                            {
                                // Single device, show it..
                                deviceLabel.setText((String)DevicesAr.get(0));
                            }
                        }
					}
/*					
					if ( sDeviceName == null )
						System.out.println("(Selected device)sDeviceName null");
					else
						System.out.println("(Selected device)sDeviceName: '"+sDeviceName+"'");
/**/

				}
				
				// Get IP address..
				// Check if we have it..
/*				
				if ( sDeviceIPAddress == null )
				    System.out.println("sDeviceIPAddress null");
				else
				    System.out.println("sDeviceIPAddress: '"+sDeviceIPAddress+"'");
/**/				    
				
				if ( (sDeviceIPAddress != null) && (sDeviceIPAddress.length() > 0) )
				{
					// Construct Wireless Id..
					wIdSb = new StringBuffer();
					sDeviceIPAddress = sDeviceIPAddress.trim();
					wIdSb.append(sDeviceIPAddress);
					wIdSb.append(":5555");
					sWirelessID = wIdSb.toString();
					//System.out.println("sWirelessID: '"+sWirelessID+"'");
					bOK = true;
				}
				else
				{
				    //System.out.println("Getting IP address");
					internalSb = new StringBuffer();
					if ( iOS == LINUX_MAC )
					{
						internalSb.append("export PATH=${PATH}:");
						internalSb.append(androidSdkPathS);
						internalSb.append("/platform-tools");
						
						internalSb.append(";adb -s ");
						internalSb.append(sDeviceName);
						internalSb.append(" shell ip addr");
						
					}
					else
					{
						internalSb.append("SET PATH=");
						internalSb.append(androidSdkPathS);
						internalSb.append("/platform-tools");
						internalSb.append(";%PATH%");
						
						internalSb.append("&&adb -s ");
						internalSb.append(sDeviceName);
						internalSb.append(" shell ip addr");
						internalSb.append("\n");
					}
			
					//System.out.println("internalSb: '"+internalSb.toString()+"'");
					
					bInternalFinished = false;		
					sInternalCommand = internalSb.toString();
					commandBgThread = new CommandBgThread();
					commandBgThread.start();
			
					// Wait for Thread to finish..
					while ( true )
					{
						try
						{
							Thread.sleep(333);
						}
						catch (InterruptedException ie)
						{
						}
		
						if ( bInternalFinished )
							break;
					}

/*					
					if ( commandResultS == null )
						System.out.println("commandResultS null");
					else
						System.out.println("commandResultS: '"+commandResultS+"'");
/**/
	
					if ( (commandResultS != null) && (commandResultS.length() > 0) )
					{
						iLoc1 = commandResultS.indexOf("error");
						if ( iLoc1 != -1 )
						{
							// Error..
							iWirelessErrorCode = 1;
							sWirelessMessage = "Error: Could not get IP address.";
						}
						else
						{
							// Get inet address not depending on 'wlan0'..
							
							// Note:
							// It will also match with:
							// 'inet6 fe80::e263:e5ff:fe66:a576/64 scope link'
							
							// It can also not get 'error',
							// but still not find the inet IP address..
							
							iLoc1 = 0;
							while ( true )
							{
								iLoc1 = commandResultS.indexOf("inet", iLoc1);
								if ( iLoc1 != -1 )
								{
									// Check for 'inetn'..
									if ( Character.isWhitespace(commandResultS.charAt(iLoc1 + 4)) )
									{
										iLoc2 = commandResultS.indexOf(".", iLoc1);
										if ( iLoc2 != -1 )
										{
											iEnd = iLoc2;
											iStart = iLoc2 - 3;
											if ( (iStart >= 0) && (iEnd < commandResultS.length()) )
											    sT = commandResultS.substring(iStart, iEnd);
											//System.out.println("sT: '"+sT+"'");
											if ( sT.equals("127") )
												;
											else
											{
												iLoc2 = commandResultS.indexOf((int)0x2f, iLoc1);	// '/'
												iEnd = iLoc2;
												
												// Construct Wireless Id..
												wIdSb = new StringBuffer();
												if ( (iStart >= 0) && (iEnd < commandResultS.length()) )
												    wIdSb.append(commandResultS.substring(iStart, iEnd));
												wIdSb.append(":5555");
												sWirelessID = wIdSb.toString();
												//System.out.println("sWirelessID: '"+sWirelessID+"'");
												bOK = true;
												break;
											}
										}
									}
								}
								else
									break;
								
								iLoc1++;
							}	// End while..
							
							if ( bOK == false )
							{
								// Did not find IP address..
								// Error..
								iWirelessErrorCode = 1;
								sWirelessMessage = "Error: Could not get IP address.";
							}
						}
					}
				}

				internalSb = new StringBuffer();
				mSb = new StringBuffer();
				
				if ( iOS == LINUX_MAC )
				{
					internalSb.append("export PATH=${PATH}:");
					internalSb.append(androidSdkPathS);
					internalSb.append("/platform-tools");

					mSb = new StringBuffer();
					if ( (ConnectDevicesAr != null) && (ConnectDevicesAr.size() > 1) )
					{
						mSb.append(";adb -s ");
						mSb.append(sDeviceName);
						mSb.append(" tcpip 5555");
					}
					else
					{
						mSb.append(";adb tcpip 5555");
					}
	
					internalSb.append(mSb.toString());
				}
				else
				{
					internalSb.append("SET PATH=");
					internalSb.append(androidSdkPathS);
					internalSb.append("/platform-tools");
					internalSb.append(";%PATH%");

					mSb = new StringBuffer();
					if ( (ConnectDevicesAr != null) && (ConnectDevicesAr.size() > 1) )
					{
						mSb.append("&&adb -s ");
						mSb.append(sDeviceName);
						mSb.append(" tcpip 5555");
					}
					else
					{
						mSb.append("&&adb tcpip 5555");
					}
					
					mSb.append("\n");
					
					internalSb.append(mSb.toString());
				}
				
				//System.out.println("internalSb: '"+internalSb.toString()+"'");
	
				bInternalFinished = false;		
				sInternalCommand = internalSb.toString();
				commandBgThread = new CommandBgThread();
				commandBgThread.start();
		
				// Wait for Thread to finish..
				while ( true )
				{
					try
					{
						Thread.sleep(333);
					}
					catch (InterruptedException ie)
					{
					}
	
					if ( bInternalFinished )
						break;
				}
				
/*
				if ( commandResultS != null )
				{
					System.out.println();
					char cTChr;
					
					for ( int g = 0; g < commandResultS.length(); g++ )
					{
						cTChr = (char)commandResultS.charAt(g);
						if ( (cTChr < 0x20) || (cTChr > 0x7e) )
							System.out.print("["+Integer.toHexString((int)cTChr)+"]");
						else
							System.out.print(cTChr);
					}
					System.out.println();
				}
/**/

				if ( (commandResultS != null) && (commandResultS.length() > 0) )
				{
					iLoc2 = commandResultS.indexOf("restarting in TCP mode");
					if ( iLoc2 != -1 )
					{
						bOK = true;
					}
				}
			}
			else
			{
				// Error..
				iWirelessErrorCode = 1;
				sWirelessMessage = "Error: No device found.";
			}

			bInitWirelessFinished = true;
			
			//System.out.println("Exiting InitWirelessBgThread run()");
		}
	}	//}}}
	
	//{{{	ConnectWirelessBgThread
	class ConnectWirelessBgThread extends Thread
	{
		public void run()
		{
		    //System.out.println("");
			//System.out.println("ConnectWirelessBgThread run()");
			
			StringBuffer internalSb = new StringBuffer();
			StringBuffer wIdSb;
			StringBuffer mSb;
			
			int iLoc1;
			int iLoc2;
			boolean bOK;
			iWirelessErrorCode = 0;

			// Check if it was connected..
			SingletonClass sc = SingletonClass.getInstance();
			
            //System.out.println("sc.s_WirelessConnected: "+sc.s_WirelessConnected);			
			if ( sc.s_WirelessConnected )
            {
                //System.out.println("\nTrying to Disconnect");
                // Try to disconnect..
				bDisconnectWirelessFinished = false;
				disconnectWirelessBgThread = new DisconnectWirelessBgThread();
				disconnectWirelessBgThread.start();

				while ( true )
				{
					try
					{
						Thread.sleep(333);
					}
					catch (InterruptedException ie)
					{
					}
				
					if ( bDisconnectWirelessFinished )
						break;
				}
				
				sWirelessID = "";
				
				// After the Disconnect the 'sWirelessID' should
				// be invalid..
				
            }

            // Note:
            // After Disconnect the sWirelessID shouldn't be valid..

/*            
			if ( sWirelessID == null )
				System.out.println("sWirelessID null");
			else
				System.out.println("sWirelessID: '"+sWirelessID+"'");
/**/

			if ( (sWirelessID != null) && (sWirelessID.length() > 0) )
			{
				if ( iOS == LINUX_MAC )
				{
					internalSb.append("export PATH=${PATH}:");
					internalSb.append(androidSdkPathS);
					internalSb.append("/platform-tools");

					mSb = new StringBuffer();
					if ( (ConnectDevicesAr != null) && (ConnectDevicesAr.size() > 1) )
					{
						mSb.append(";adb -s ");
						
						// For initial Connect, original device name
						// for ReConnect, wirelessId..
						mSb.append(sDeviceName);
						mSb.append(" connect ");
						mSb.append(sWirelessID);
					}
					else
					{
						mSb.append(";adb connect ");
						mSb.append(sWirelessID);
					}

					internalSb.append(mSb.toString());
				}
				else
				{
					internalSb.append("SET PATH=");
					internalSb.append(androidSdkPathS);
					internalSb.append("/platform-tools");
					internalSb.append(";%PATH%");

					mSb = new StringBuffer();
					if ( (ConnectDevicesAr != null) && (ConnectDevicesAr.size() > 1) )
					{
						mSb.append("&&adb -s ");
						
						// For initial Connect, original device name
						// for ReConnect, wirelessId..
						mSb.append(sDeviceName);
						mSb.append(" connect ");
						mSb.append(sWirelessID);
					}
					else
					{
						mSb.append("&&adb connect ");
						mSb.append(sWirelessID);
					}

					mSb.append("\n");
					internalSb.append(mSb.toString());
				}
		
				//System.out.println("(Connect command)internalSb: '"+internalSb.toString()+"'");
				
				bInternalFinished = false;		
				sInternalCommand = internalSb.toString();
				commandBgThread = new CommandBgThread();
				commandBgThread.start();
		
				// Wait for Thread to finish..
				while ( true )
				{
					try
					{
						Thread.sleep(333);
					}
					catch (InterruptedException ie)
					{
					}
		
					if ( bInternalFinished )
						break;
				}
				
				bOK = false;

/*				
				if ( commandResultS == null )
					System.out.println("(Connect)commandResultS null");
				else
					System.out.println("(Connect)commandResultS: '"+commandResultS+"'");
/**/

				if ( (commandResultS != null) && (commandResultS.length() > 0) )
				{
				    //System.out.println("Found connected to");
					iLoc1 = commandResultS.indexOf("connected to");
					if ( iLoc1 != -1 )
					{
						// Success..
						// Set to use 'sWirelessID' as the new device name for -s..
						sDeviceName = sWirelessID;
						//.println("sDeviceName: '"+sDeviceName+"'");
						
						bOK = true;
					}
					
					//System.out.println("bOK: "+bOK);
					if ( bOK )
					{
						// Success..
                        sc.s_WirelessConnected = true;
					}
					else
					{
						// Error..
						//System.out.println("Connect failed");
						iWirelessErrorCode = 1;
						sWirelessMessage = "Error: Connect failed.";
					}
				}
			}
			
/*			
			if ( sDeviceName == null )
				System.out.println("(A)sDeviceName null");
			else
				System.out.println("(A)sDeviceName: '"+sDeviceName+"'");
/**/

			if ( (sDeviceName != null) && (sDeviceName.length() > 0) )
				deviceLabel.setText(sDeviceName);
			else
				deviceLabel.setText(" ");
				
				
			bConnectWirelessFinished = true;
			//completeLatch.countDown();
			
			//System.out.println("Exiting ConnectWirelessBgThread");
			
		}
	}	//}}}
			
	//{{{	DisconnectWirelessBgThread
	class DisconnectWirelessBgThread extends Thread
	{
		public void run()
		{
		    //System.out.println("\n");
			//System.out.println("DisconnectWirelessBgThread run()");
			StringBuffer internalSb = new StringBuffer();

			//
			// Do 'adb disconnect'..
			if ( (sWirelessID != null) && (sWirelessID.length() > 0) )
			{
				if ( iOS == LINUX_MAC )
				{
					internalSb.append("export PATH=${PATH}:");
					internalSb.append(androidSdkPathS);
					internalSb.append("/platform-tools");

					internalSb.append(";adb disconnect ");
					internalSb.append(sWirelessID);
				}
				else
				{
					internalSb.append("SET PATH=");
					internalSb.append(androidSdkPathS);
					internalSb.append("/platform-tools");
					internalSb.append(";%PATH%");

					// Note:
					// We should wait some before
					// we call 'adb usb'..
					internalSb.append("&&adb disconnect ");
					internalSb.append(sWirelessID);
					internalSb.append("\n");
				}
				
				//System.out.println("(adb disconnect)internalSb: "+internalSb.toString());    // Very long output
		
				bInternalFinished = false;		
				sInternalCommand = internalSb.toString();
				commandBgThread = new CommandBgThread();
				commandBgThread.start();
		
				// Wait for Thread to finish..
				while ( true )
				{
					try
					{
						//Thread.sleep(150);
						Thread.sleep(333);
					}
					catch (InterruptedException ie)
					{
					}
		
					if ( bInternalFinished )
						break;
				}
				
				//System.out.println("(commandBgThread): "+g_sT);

				// Note:
				// Since this is just to Disconnect,
				// once it does we shouldn't need to do an 'adb usb'..
				
			}

			SingletonClass sc = SingletonClass.getInstance();
			
			// Disconnect reset..
			sc.s_WirelessConnected = false;    // Reset..
			bDisconnectWirelessFinished = true;
			
			//System.out.println("Exiting DisconnectWirelessBgThread");
			
		}
	}	//}}}

	//{{{	CheckDeviceBgThread
	class CheckDeviceBgThread extends Thread
	{
		public void run()
		{
			//System.out.println("\nCheckDeviceBgThread run()");
			StringBuffer sb = new StringBuffer();
			int iStart = 0;
			int iLoc2 = 0;
			String sT = "";
			sWakefulness = "";
			
			sb = new StringBuffer();		
			if ( iOS == LINUX_MAC )
			{
				sb.append("export PATH=${PATH}:");
				sb.append(androidSdkPathS);
				sb.append("/platform-tools");
				sb.append(";adb ");
			}
			else
			{
				sb.append("SET PATH=");
				sb.append(androidSdkPathS);
				sb.append("/platform-tools");
				sb.append(";%PATH%");

				sb.append("&&adb ");
			}

            if ( (sDeviceName != null) && (sDeviceName.length() > 0) )
            {
                sb.append("-s ");
                sb.append(sDeviceName);
                sb.append(" ");
            }

            sb.append("shell dumpsys power");
            
            if ( iOS == WINDOWS )
                sb.append("\n");
            
			//System.out.println("sb: '"+sb.toString()+"'");
			
			bCommandFinished = false;		
			sInternalCommand = sb.toString();
			commandBgThread = new CommandBgThread();
			commandBgThread.start();
	
			// Wait for Thread to finish..
			while ( true )
			{
				try
				{
					Thread.sleep(333);
				}
				catch (InterruptedException ie)
				{
				}

				if ( bCommandFinished )
					break;
			}
			
            // mWakefulness=Asleep
			// mWakefulness=Awake

            if ( (commandResultS != null) && (commandResultS.length() > 0) )
            {
                iLoc2 = commandResultS.indexOf("mWakefulness=");
                iStart = iLoc2;
                if ( iLoc2 != -1 )
                {
                    char cCh;
                    while ( true )
                    {
                        cCh = commandResultS.charAt(iLoc2);
                        if ( cCh > 0x20 )
                        {
                            iLoc2++;
                            continue;
                        }
                        else
                            break;
                    }
                    
                    if ( ((iStart + 13) >= 0) && (iLoc2 < commandResultS.length()) )
                        sT = commandResultS.substring((iStart + 13), iLoc2);
                    sT = sT.trim();
                    sWakefulness = sT;
                }
            }
            
            bCheckDeviceFinished = true;
		}
	}    //}}}

	//{{{	WakeDeviceBgThread
	class WakeDeviceBgThread extends Thread
	{
		public void run()
		{
			//System.out.println("WakeDeviceBgThread run()");
			StringBuffer internalSb = new StringBuffer();
			
			if ( iOS == LINUX_MAC )
			{
				internalSb.append("export PATH=${PATH}:");
				internalSb.append(androidSdkPathS);
				internalSb.append("/platform-tools");
				
				internalSb.append(";adb ");
			}
			else
			{
				internalSb.append("SET PATH=");
				internalSb.append(androidSdkPathS);
				internalSb.append("/platform-tools");
				internalSb.append(";%PATH%");
				
				internalSb.append("&&adb ");
			}
			
			if ( (sDeviceName != null) && (sDeviceName.length() > 0) )
			{
				internalSb.append("-s ");
				internalSb.append(sDeviceName);
				internalSb.append(" ");
			}

			//internalSb.append("shell input keyevent 26");    // KEYCODE_POWER
			internalSb.append("shell input keyevent 224");    // KEYCODE_WAKEUP
			
            if ( iOS == WINDOWS )
                internalSb.append("\n");
			
			bCommandFinished = false;		
			sInternalCommand = internalSb.toString();
			commandBgThread = new CommandBgThread();
			commandBgThread.start();
	
			// Wait for Thread to finish..
			while ( true )
			{
				try
				{
					Thread.sleep(333);
				}
				catch (InterruptedException ie)
				{
				}
	
				if ( bCommandFinished )
					break;
			}
			
			bWakeDeviceFinished = true;
		}
	}    //}}}

	//{{{   FinishPath()   
	void FinishPath()
	{
	    //System.out.println("\nFinishPath()");
	    // Update Path Buttons and JList..
	    
	    // Takes 'sCurrentPath' and breaks it into tokens
	    // to help create the buttons..
	    
        //System.out.println("(In)sCurrentPath: '"+sCurrentPath+"'");
        StringTokenizer st;
        StringBuffer sB;
        String[] tkSa;
        String sT = "";
        int iCount = 0;
        int iTokIndex = 0;
        int iChrW;
        int iHgt;
        JButton pathButton;
        
		Font defaultFont = new Font("Monospaced", Font.BOLD, FONT_SIZE);
		FontMetrics fontMetrics = mainPanel.getFontMetrics(defaultFont);
		
		iChrW = fontMetrics.charWidth('X');
		iHgt = fontMetrics.getHeight();
        
        
        bUpdateFileBrowserFinished = false;
        updateFileBrowserBgThread = new UpdateFileBrowserBgThread();
        updateFileBrowserBgThread.start();
        
        // Wait for Thread to finish..
        while ( true )
        {
            try
            {
                Thread.sleep(333);
            }
            catch (InterruptedException ie)
            {
            }

            if ( bUpdateFileBrowserFinished )
                break;
        }
        
        // Check for failed..
        if ( bFileBrowserUpdateOkay )
        {
            // Update path buttons..
            if ( pathButtonPanel != null )
            {
                //System.out.println("\nUpdating buttons");
                pathButtonPanel.removeAll();   // Remove all the components

                iButtonCount = 0;   // Reset..
                
                // Tokenize..
                //System.out.println("sCurrentPath: '"+sCurrentPath+"'");
                st = new StringTokenizer(sCurrentPath, "/");
                iCount = st.countTokens();
                //System.out.println("(Org)iCount: "+iCount);
                
                tkSa = new String[iCount + 1];
                tkSa[0] = "/ ";
                
                for ( iTokIndex = 1; st.hasMoreTokens(); iTokIndex++ )
                {
                    tkSa[iTokIndex] = st.nextToken();
                    tkSa[iTokIndex] = tkSa[iTokIndex].trim();
                    //System.out.println("["+iTokIndex+"]: '"+tkSa[iTokIndex]+"'");
                }
        
                for ( iTokIndex = 0; iTokIndex < iCount + 1; iTokIndex++ )
                {
                    // Construct name..
                    sT = "";
                    if ( (tkSa != null) && (tkSa.length > 0) )
                        sT = tkSa[iTokIndex];
                    
                    //System.out.println("sT: '"+sT+"'");
                    sB = new StringBuffer();
                    if ( sT.startsWith("/") )
                        ;
                    else
                        sB.append("/");
                    
                    sB.append(sT);
                    
                    // Construct button..
                    //System.out.println("sB.toString(): '"+sB.toString()+"'");
                    pathButton = new JButton(sB.toString());
                    if ( iTokIndex == ((iCount + 1) - 1) )
                        pathButton.setEnabled(false);
                    
                    pathButton.addActionListener(actListener);
                    
                    pathButtonPanel.add(pathButton);
                    
                    iButtonCount++;
                }
                
                pathButtonPanel.validate();
                pathButtonPanel.repaint();
            }
            
            //System.out.println("iButtonCount: "+iButtonCount);
            
            if ( iButtonCount > 4 )
            {
                // Resize..
                pathButtonPanel.setPreferredSize(new Dimension(iChrW * 10, (int)iHgt * 4));
                pathButtonPanel.revalidate();
            }
            else
            {
                pathButtonPanel.setPreferredSize(new Dimension(iChrW * 10, (int)iHgt * 2));
                pathButtonPanel.revalidate();
            }

/*            
            if ( fileToksAr == null )
                System.out.println("fileToksAr null");
            else
                System.out.println("fileToksAr.size(): "+fileToksAr.size());
/**/            
            
            // Update JList..
            if ( (fileToksAr != null) && (fileToksAr.size() > 0) )
            {
                int iLoc = 0;
                DefaultListModel dlModel = new DefaultListModel();
                fileBrowserJList.setModel(dlModel);
                dlModel.clear();
                for( int i = 0; i < fileToksAr.size(); i++ )
                {
                    sT = (String)fileToksAr.get(i);
                    if ( sT != null )
                    {
                        iLoc = sT.indexOf("~");
                        if ( (iLoc != -1) && (iLoc < sT.length()) )
                        {
                            sT = sT.substring(0, iLoc);
                        }
                    }
                    //System.out.println("["+i+"]: '"+sT+"'");
                    dlModel.addElement((String)sT);
                }
            }
        }
        
        //System.out.println("Exiting, (Out)sCurrentPath: '"+sCurrentPath+"'");
    }    //}}}
	
	//{{{	processPath()
	/**
	 * Remove double quotes
	 * Flip '\' -> '/'
	 */
	//String ProcessPath(String sIn)
	String processPath(String sIn)
	{
		//System.out.println("\nProcessPath()");
		StringBuffer sb = null;

		if ((sIn != null) && (!sIn.equals("")))
			sb = new StringBuffer(sIn);
		else
			return sIn;

		for ( int g = 0; g < sb.length(); )
		{
			if (sb.charAt(g) == '\\')
				sb.setCharAt(g, '/');
			else if (sb.charAt(g) == (char)'"')
			{
				sb.deleteCharAt(g);
				continue;
			}

			g++;
		}

		//System.out.println("(Returning)sb: "+sb.toString());		
		return sb.toString();
	} //}}}
    
	//{{{   CommandBgThread    readLine()
	class CommandBgThread extends Thread
	{
		public void run()
		{
			//System.out.println("CommandBgThread run()");
			//System.out.println("sInternalCommand: '"+sInternalCommand+"'");
			
			// ==================
			// THREAD MUTEX
			// ==================
			try
			{
				commandControl.acquire();			
			}
			catch (InterruptedException ie)
			{
			}

			ProcessBuilder processBuilder;
			Process process = null;
			InputStream inputStream;
			InputStream errorStream;
			BufferedReader inputBufferedReader = null;
			BufferedReader errorBufferedReader = null;
			String sLine = "";
			StringBuffer sB;
			StringBuffer sBOut;
			int iExitCode = 0;
			int iExitVal = 0;
			long lTime = 0;
			long lTime2 = 0;

			byte[] bReply = {(byte) 0x79, (byte) 0x0d, (byte) 0x0a};
			String sReply = new String(bReply);
			byte[] b0d0a = {(byte) 0x0d, (byte) 0x0a};
			String sEnd = new String(b0d0a);

			try
			{
				processBuilder = new ProcessBuilder();

				if ( iOS == LINUX_MAC )
					processBuilder.command("/bin/bash", "-c", sInternalCommand);
				else
					processBuilder.command("cmd.exe", "/c", sInternalCommand);

				process = processBuilder.start();
			}
			catch (IOException ioe)
			{}

			inputStream = process.getInputStream();
			errorStream = process.getErrorStream();
			sBOut = new StringBuffer();

			try
			{
				inputBufferedReader = new BufferedReader(new InputStreamReader(inputStream));
				errorBufferedReader = new BufferedReader(new InputStreamReader(errorStream));

				//System.out.println("Above top");
				while ( true )
				{
					if ( inputBufferedReader.ready() )
					{
					    lTime = System.currentTimeMillis();
						sLine = inputBufferedReader.readLine();
						if ( sLine != null )
						{
							//System.out.println("(input): '"+sLine+"'");

							sB = new StringBuffer(sLine);
							sB.append(sEnd);

							sBOut.append(sB.toString());

/*							
                            System.out.println("\n\n");
                            char cTChr;

                            for ( int g = 0; g<sB.length(); g++ )
                            {
                                cTChr = (char)sB.charAt(g);
                                if ( (cTChr<0x21) || (cTChr > 0x7e) )
                                    System.out.print("["+Integer.toHexString((int)cTChr)+"]");
                                else
                                    System.out.print(cTChr);
                            }
                            System.out.println("\n\n length(): "+sB.length());
/**/

						}
					}
					else if (errorBufferedReader.ready())
					{
					    lTime = System.currentTimeMillis();
						sLine = errorBufferedReader.readLine();
						if ( sLine != null )
						{
							//System.out.println("(error): '"+sLine+"'");
							sB = new StringBuffer(sLine);
							sB.append(sEnd);
							
							sBOut.append(sB.toString());
						}
					}
					else
					{
						// Didn't get anything..
						lTime2 = System.currentTimeMillis();
						if ( (lTime > 0) && (lTime2 - lTime > 3000) )
						{
						    //System.out.println("Too long, breaking");
						    break;
						}
						
						try
						{
							iExitVal = process.exitValue();
							//System.out.println("iExitVal: "+iExitVal);
							break;
						}
						catch (IllegalThreadStateException itse)
						{}
					}
				} // End while..
			}
			catch (IOException ioe)
			{
				System.out.println("CommandBgThread Exception:");
				ioe.printStackTrace();
			}
			finally
			{
				try
				{
					if (inputBufferedReader != null)
						inputBufferedReader.close();

					if (inputStream != null)
						inputStream.close();

					if (errorBufferedReader != null)
						errorBufferedReader.close();

					if (errorStream != null)
						errorStream.close();
				}
				catch (IOException ioe)
				{}
			}

			if ( process != null )
			{
				process.destroy();
				process = null;
			}

			commandResultS = sBOut.toString();
			
			//System.out.println("commandResultS: '"+commandResultS+"'");
			
/*			
			//String sT4 = commandResultS.substring(5000, commandResultS.length() - 1000);
			//String sT4 = commandResultS.substring(5000);
			String sT4 = commandResultS.substring(0, 6500);
			
            System.out.println("\n\n");
            char cTChr;

            for ( int g = 0; g<sT4.length(); g++ )
            {
                cTChr = (char)sT4.charAt(g);
                if ( (cTChr<0x21) || (cTChr > 0x7e) )
                    System.out.print("["+Integer.toHexString((int)cTChr)+"]");
                else
                    System.out.print(cTChr);
            }
            System.out.println("\n\n");
/**/			

			bCommandFinished = true;
			bInternalFinished = true;
			
			//System.out.println("Exiting CommandBgThread run()");

			//if (commandRequestLatch != null)
				//commandRequestLatch.countDown();
			
			// ==================
			// THREAD MUTEX
			// ==================
			commandControl.release();

		}
	} //}}}

	//{{{   PullScreenshotDialog()
	public void PullScreenshotDialog()
	{
		//System.out.println("\nPullScreenshotDialog()");
		screenshotFrame = new JFrame();
		screenshotFrame.setLayout(new BorderLayout());
		screenshotFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		screenshotFrame.setTitle("Pull Screenshots");

		int iGridY;
		int iSz = 0;
		//String[] tSa;

		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(4, 4, 4, 4); // External padding of the component

		iGridY = 0;
		JLabel targetSdkLbl = new JLabel("Screenshots: ");
		gbc.gridx = 0;
		gbc.gridy = iGridY;
		gbc.gridwidth = 1;
		panel.add(targetSdkLbl, gbc);

		JScrollPane scrollPane = new JScrollPane();
		

		screenshotFilesJList = new JList(tokSa);
		
		screenshotFilesJList.setFont(new Font("Monospaced", Font.BOLD, FONT_SIZE));
		screenshotFilesJList.setVisibleRowCount(8);
		//screenshotFilesJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		screenshotFilesJList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		screenshotFilesJList.addListSelectionListener(selectionListener);
		
		scrollPane.getViewport().setView(screenshotFilesJList);
		
		gbc.gridx = 1;
		gbc.gridy = iGridY;
		gbc.gridwidth = 3;
		panel.add(scrollPane, gbc);

		iGridY++;

		panel.setBorder(new LineBorder(Color.GRAY));
		JButton submitButton = new JButton("Submit");
		submitButton.setActionCommand("pull_screenshot_submit");
		submitButton.addActionListener(actListener);

		JButton cancelButton = new JButton("Cancel");
		cancelButton.setActionCommand("pull_screenshot_cancel");
		cancelButton.addActionListener(actListener);
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(submitButton);
		buttonPanel.add(cancelButton);

		screenshotFrame.getContentPane().add(panel, BorderLayout.CENTER);
		screenshotFrame.getContentPane().add(buttonPanel, BorderLayout.PAGE_END);
		screenshotFrame.pack();
		screenshotFrame.setVisible(true);
		screenshotFrame.setResizable(false);
		
		// Set List Id..
		iListId = SCREENSHOT_LIST;

	} //}}}

	//{{{   PullCameraImageDialog()
	public void PullCameraImageDialog()
	{
		//System.out.println("\nPullCameraImageDialog()");
		cameraFrame = new JFrame();
		cameraFrame.setLayout(new BorderLayout());
		cameraFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		cameraFrame.setTitle("Pull camera images");

		int iGridY;
		int iSz = 0;

		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(4, 4, 4, 4); // External padding of the component

		iGridY = 0;
		JLabel targetSdkLbl = new JLabel("Camera images: ");
		gbc.gridx = 0;
		gbc.gridy = iGridY;
		gbc.gridwidth = 1;
		panel.add(targetSdkLbl, gbc);

		JScrollPane scrollPane = new JScrollPane();
		
		cameraImagesJList = new JList(tokSa);
		cameraImagesJList.setFont(new Font("Monospaced", Font.BOLD, FONT_SIZE));
		cameraImagesJList.setVisibleRowCount(8);
		//cameraImagesJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		cameraImagesJList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		cameraImagesJList.addListSelectionListener(selectionListener);
		
		scrollPane.getViewport().setView(cameraImagesJList);
		
		gbc.gridx = 1;
		gbc.gridy = iGridY;
		gbc.gridwidth = 3;
		panel.add(scrollPane, gbc);

		iGridY++;

		panel.setBorder(new LineBorder(Color.GRAY));
		JButton submitButton = new JButton("Submit");
		submitButton.setActionCommand("pull_camera_image_submit");
		submitButton.addActionListener(actListener);

		JButton cancelButton = new JButton("Cancel");
		cancelButton.setActionCommand("pull_camera_image_cancel");
		cancelButton.addActionListener(actListener);
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(submitButton);
		buttonPanel.add(cancelButton);

		cameraFrame.getContentPane().add(panel, BorderLayout.CENTER);
		cameraFrame.getContentPane().add(buttonPanel, BorderLayout.PAGE_END);
		cameraFrame.pack();
		cameraFrame.setVisible(true);
		cameraFrame.setResizable(false);
		
		// Set List Id..
		iListId = CAMERA_IMAGE_LIST;

	} //}}}

	//{{{   PackageDialog()
	public void PackageDialog(String sTitle)
	{
		//System.out.println("\nPackageDialog()");
		
		packageFrame = new JFrame();
		packageFrame.setLayout(new BorderLayout());
		packageFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		packageFrame.setTitle(sTitle);

		int iGridY;
		int iSz = 0;

		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(4, 4, 4, 4); // External padding of the component

		iGridY = 0;
		JLabel targetSdkLbl = new JLabel("Packages: ");
		gbc.gridx = 0;
		gbc.gridy = iGridY;
		gbc.gridwidth = 1;
		panel.add(targetSdkLbl, gbc);

		JScrollPane scrollPane = new JScrollPane();
		
		uninstallJList = new JList(tokSa);
		uninstallJList.setFont(new Font("Monospaced", Font.BOLD, FONT_SIZE));
		uninstallJList.setVisibleRowCount(10);
		uninstallJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		uninstallJList.addListSelectionListener(selectionListener);
		
		scrollPane.getViewport().setView(uninstallJList);
		
		gbc.gridx = 1;
		gbc.gridy = iGridY;
		gbc.gridwidth = 3;
		panel.add(scrollPane, gbc);

		iGridY++;

		panel.setBorder(new LineBorder(Color.GRAY));
		JButton submitButton = new JButton("Submit");
		submitButton.setActionCommand("package_submit");
		submitButton.addActionListener(actListener);

		JButton cancelButton = new JButton("Cancel");
		cancelButton.setActionCommand("package_cancel");
		cancelButton.addActionListener(actListener);
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(submitButton);
		buttonPanel.add(cancelButton);

		packageFrame.getContentPane().add(panel, BorderLayout.CENTER);
		packageFrame.getContentPane().add(buttonPanel, BorderLayout.PAGE_END);
		packageFrame.pack();
		packageFrame.setVisible(true);
		packageFrame.setResizable(false);
		
		// Set List Id..
		iListId = PACKAGE_LIST;

	} //}}}

	//{{{	AdbCommandDialog()
	public void AdbCommandDialog()
	{
		adbCommandFrame = new JFrame();
		adbCommandFrame.setLayout(new BorderLayout());		
		adbCommandFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		adbCommandFrame.setTitle("Adb Command");
		
		JPanel panel = new JPanel(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		JLabel commandLbl = new JLabel("Command: ");

		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(4, 4, 4, 4);	// bottom, left, right, top		
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 1;
		panel.add(commandLbl, gbc);

		adbCommandField = new JTextField(30);

		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(4, 4, 4, 4);	// bottom, left, right, top
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.gridwidth = 2;
		panel.add(adbCommandField, gbc);

		panel.setBorder(new LineBorder(Color.GRAY));

		JButton submitButton = new JButton("Submit");
		submitButton.setActionCommand("adb_command_submit");
		submitButton.addActionListener(actListener);
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.setActionCommand("adb_command_cancel");
		cancelButton.addActionListener(actListener);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(submitButton);
		buttonPanel.add(cancelButton);

		adbCommandFrame.getContentPane().add(panel, BorderLayout.CENTER);
		adbCommandFrame.getContentPane().add(buttonPanel, BorderLayout.PAGE_END);
		
		adbCommandFrame.pack();
		adbCommandFrame.setVisible(true);
		
	}	//}}}

	//{{{	FileBrowserDialog()
	public void FileBrowserDialog(String sTitle)
	{
	    //System.out.println("\n-------------------------------");
	    //System.out.println("FileBrowserDialog()");
	    
	    String[] tSa = null;
	    String[] pathSa = null;
	    String[] listSa = null;
	    String sT = "";
	    StringBuffer sB = null;
	    StringTokenizer st;
	    JButton pathButton;
	    int iGridY = 0;
	    int iTokIndex = 0;
	    int iCount = 0;
	    int iPathCount = 0;
	    int iListCount = 0;
	    
		fileBrowserFrame = new JFrame();
		fileBrowserFrame.setLayout(new BorderLayout());	
		fileBrowserFrame.addWindowListener(windowListener);
		fileBrowserFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		//System.out.println("sTitle: '"+sTitle+"'");
		fileBrowserFrame.setTitle(sTitle);
		fileBrowserFrame.setAlwaysOnTop(true);
		
		Font defaultFont = new Font("Monospaced", Font.BOLD, FONT_SIZE);
		FontMetrics fontMetrics = fileBrowserFrame.getFontMetrics(defaultFont);
		
		int iChrW = fontMetrics.charWidth('X');
		int iHgt = fontMetrics.getHeight();
		fileBrowserFrame.setPreferredSize(new Dimension(iChrW * 50, (int)iHgt * 25));
		
		pathButtonPanel = new JPanel();
		pathButtonPanel.setLayout(new FlowLayout());
		
		
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		iGridY = 0;
/*		
        if ( fileToksAr == null )
            System.out.println("fileToksAr null");
        else
            System.out.println("fileToksAr.size(): "+fileToksAr.size());
/**/

        //sCurrentPath = "";
        
        // Populate 'fileToksAr'..
        bUpdateFileBrowserFinished = false;
        updateFileBrowserBgThread = new UpdateFileBrowserBgThread();
        updateFileBrowserBgThread.start();
        
        // Wait for Thread to finish..
        while ( true )
        {
            try
            {
                //Thread.sleep(250);
                //Thread.sleep(333);
                Thread.sleep(500);
            }
            catch (InterruptedException ie)
            {
            }

            if ( bUpdateFileBrowserFinished )
                break;
        }

/*        
        if ( fileToksAr == null )
            System.out.println("fileToksAr null");
        else
        {
            System.out.println("fileToksAr.size(): "+fileToksAr.size());
            for ( int iZ = 0; iZ < fileToksAr.size(); iZ++ )
                System.out.println("fileToksAr["+iZ+"]: '"+(String)fileToksAr.get(iZ)+"'");
        }
/**/

        if ( (fileToksAr != null) && (fileToksAr.size() > 0) )
        {
            sT = "";
            int iLoc = 0;
            listSa = new String[fileToksAr.size()];
            
            for ( int g = 0; g < fileToksAr.size(); g++ )
            {
                sT = (String)fileToksAr.get(g);
                if ( sT != null )
                {
                    iLoc = sT.indexOf("~");
                    if ( (iLoc != -1) && (iLoc < sT.length()) )
                    {
                        sT = sT.substring(0, iLoc);
                    }
                }
                
                //listSa[g] = (String)fileToksAr.get(g);
                if ( (listSa != null) && (g < listSa.length) )
                    listSa[g] = (String)sT;
                //System.out.println("listSa["+g+"]: '"+listSa[g]+"'");
            }

            iListCount = fileToksAr.size();
        }
        
        //System.out.println("sCurrentPath: '"+sCurrentPath+"'");

        // Reset sCurrentPath to root..
        sCurrentPath = "";
        
        // Tokenize..
        //System.out.println("sCurrentPath: '"+sCurrentPath+"'");
        st = new StringTokenizer(sCurrentPath, "/");
        iPathCount = st.countTokens();
        //System.out.println("iPathCount: "+iPathCount);
        
        pathSa = new String[iPathCount + 1];

        pathSa[0] = "/ ";
        
        for ( iTokIndex = 1; st.hasMoreTokens(); iTokIndex++ )
        {
            pathSa[iTokIndex] = st.nextToken();
            pathSa[iTokIndex] = pathSa[iTokIndex].trim();
            //System.out.println("["+iTokIndex+"]: '"+pathSa[iTokIndex]+"'");
        }
        
/*
        if ( pathSa == null )
            System.out.println("pathSa null");
        else
            System.out.println("pathSa.length: "+pathSa.length);
/**/

        //System.out.println("\n=================================================="); 
        //System.out.println("buttons");
        
        // Path buttons..
        for ( iTokIndex = 0; iTokIndex < iPathCount + 1; iTokIndex++ )
        {
            // Construct name..
            sT = "";
            
            if ( (pathSa != null) && (pathSa.length > 0) && (iTokIndex < pathSa.length) )
                sT = pathSa[iTokIndex];
            
            if ( (sT != null) && (! sT.equals("null")) && (sT.length() > 0) )
            {
                //System.out.println("(Button name)sT: '"+sT+"'");
                sB = new StringBuffer();
                if ( sT.startsWith("/") )
                    ;
                else
                    sB.append("/");
                
                sB.append(sT);
            }
            
            // Construct button..
            //System.out.println("sB.toString(): '"+sB.toString()+"'");
            pathButton = new JButton(sB.toString());
            if ( iTokIndex == ((iCount + 1) - 1) )
                pathButton.setEnabled(false);
            
            pathButton.addActionListener(actListener);
            
            pathButtonPanel.add(pathButton);
            
            iButtonCount++;
            
        }
 
        //System.out.println("iButtonCount: "+iButtonCount);
        
        if ( iButtonCount > 4 )
        {
            // Resize..
            //System.out.println("(B)Doing resize");
            pathButtonPanel.setPreferredSize(new Dimension(iChrW * 10, (int)iHgt * 4));
            pathButtonPanel.revalidate();
            
        }
        else
        {
            pathButtonPanel.setPreferredSize(new Dimension(iChrW * 10, (int)iHgt * 2));
            pathButtonPanel.revalidate();
        }

        JScrollPane scrollPane = new JScrollPane();
/*        
        if ( listSa == null )
            System.out.println("listSa null");
        else
            System.out.println("listSa.length: "+listSa.length);
/**/ 

        if ( (listSa != null) && (listSa.length > 0) )
            fileBrowserJList = new JList(listSa);
        
        if ( fileBrowserJList != null )
        {
            fileBrowserJList.setFont(new Font("Monospaced", Font.BOLD, FONT_SIZE));
            fileBrowserJList.setVisibleRowCount(15);
            //fileBrowserJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            fileBrowserJList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
            fileBrowserJList.addListSelectionListener(selectionListener);
            fileBrowserJList.addMouseListener(jListMouseListener);

            scrollPane.getViewport().setView(fileBrowserJList);
            scrollPane.setPreferredSize(new Dimension(300, 300)); 
        }

        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(4, 4, 4, 4);	// bottom, left, right, top
        
        gbc.gridx = 1;
        gbc.gridy = iGridY;
        gbc.gridwidth = 3;
        panel.add(scrollPane, gbc);
			
		panel.setBorder(new LineBorder(Color.GRAY));

		JButton goButton = new JButton("Go");
		goButton.setActionCommand("select_file_go");
		goButton.addActionListener(actListener);
		
		JButton submitButton = new JButton("Submit");
		submitButton.setActionCommand("select_file_submit");
		submitButton.addActionListener(actListener);
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.setActionCommand("select_file_cancel");
		cancelButton.addActionListener(actListener);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(goButton);
		buttonPanel.add(submitButton);
		buttonPanel.add(cancelButton);

		fileBrowserFrame.getContentPane().add(pathButtonPanel, BorderLayout.NORTH);
		fileBrowserFrame.getContentPane().add(panel, BorderLayout.CENTER);
		fileBrowserFrame.getContentPane().add(buttonPanel, BorderLayout.PAGE_END);
		
		fileBrowserFrame.pack();
		fileBrowserFrame.setVisible(true);
		fileBrowserFrame.setResizable(false);
		
		// Set List Id..
		iListId = FILE_BROWSER_LIST;
			
	}    //}}}

	//{{{	selectDeviceDialog()
	public void selectDeviceDialog()
	{
	    //System.out.println("\n-- selectDeviceDialog() --");
		selectDeviceFrame = new JFrame();
		selectDeviceFrame.setLayout(new BorderLayout());		
		selectDeviceFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		selectDeviceFrame.setTitle("Select Device");
		selectDeviceFrame.setAlwaysOnTop(true);
		
		
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		JLabel deviceLbl = new JLabel("Device: ");

		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(4, 25, 4, 4);	// bottom, left, right, top
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		panel.add(deviceLbl, gbc);

/*
		if ( DevicesAr == null )
			System.out.println("DevicesAr null");
		else
			System.out.println("DevicesAr.size(): "+DevicesAr.size());
/**/		
				
		// Construct choices..
		String[] tSa;
		boolean bOk = false;
		boolean bConnect = false;
		int iSz = 0;
		
        if ( (DevicesAr != null) && (DevicesAr.size() > 0) )
        {
            iSz = DevicesAr.size();
            bOk = true;
        }
		
		if ( bOk )
		{
			tSa = new String[iSz];
			for ( int g = 0; g < tSa.length; g++ )
			{
				if ( bConnect )
				{
				    if ( g < ConnectDevicesAr.size() )
				    {
				        if ( (tSa != null) && (g < tSa.length) )
				            tSa[g] = (String)ConnectDevicesAr.get(g);
				    }
				}
				else
				{
				    if ( g < DevicesAr.size() )
				    {
				        if ( (tSa != null) && (g < tSa.length) )
				            tSa[g] = (String)DevicesAr.get(g);
				    }
				}
			}

			SpinnerListModel deviceModel = new SpinnerListModel(tSa);		
			deviceSpinner = new JSpinner(deviceModel);
			
			deviceSpinner.setEditor(new JSpinner.DefaultEditor(deviceSpinner));	// Set as non-editable..
			deviceSpinner.setAlignmentX(Component.LEFT_ALIGNMENT);
			
			gbc = new GridBagConstraints();
			gbc.fill = GridBagConstraints.HORIZONTAL;
			gbc.insets = new Insets(4, 4, 4, 4);	// bottom, left, right, top
			
			gbc.gridx = 1;
			gbc.gridy = 0;
			gbc.gridwidth = 3;
			panel.add(deviceSpinner, gbc);
		}

		panel.setBorder(new LineBorder(Color.GRAY));
		
		JButton submitButton = new JButton("Submit");
		submitButton.setActionCommand("select_device_submit");
		submitButton.addActionListener(actListener);
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.setActionCommand("select_device_cancel");
		cancelButton.addActionListener(actListener);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(submitButton);
		buttonPanel.add(cancelButton);

		selectDeviceFrame.getContentPane().add(panel, BorderLayout.CENTER);
		selectDeviceFrame.getContentPane().add(buttonPanel, BorderLayout.PAGE_END);
		
		selectDeviceFrame.pack();
		selectDeviceFrame.setVisible(true);
		selectDeviceFrame.setResizable(false);
		
	}	//}}}

	//{{{	ActionListener
	private ActionListener actListener = new ActionListener()
	{
		public void actionPerformed(ActionEvent e)
		{
			//System.out.println("\n== ActionListener ==");
			String sActionCommand = e.getActionCommand();
			String sT = "";
			StringBuffer sb;
			StringBuffer sB;
			StringBuffer sBNm = null;
			StringTokenizer st;
			int iLoc = 0;
			int iLoc2 = 0;
			int iCount = 0;
			int iTokIndex = 0;
			//System.out.println("sActionCommand: '"+sActionCommand+"'");

            if ( sActionCommand.startsWith("/") )
            {
                // Path button..
                // '/dev/cpuctl/bg_non_interactive'
                
                // sActionCommand: '/  '  '/dev'
                //System.out.println("PATH");
                JButton pathButton;
                //System.out.println("sCurrentPath: '"+sCurrentPath+"'");
                
                if ( sActionCommand.equals("/ ") )
                {
                    sCurrentPath = "";
                }
                else
                {
                    iLoc = sCurrentPath.indexOf(sActionCommand);
                    if ( iLoc != -1 )
                    {
                        iLoc2 = sCurrentPath.indexOf("/", iLoc + 1);
                        if ( (iLoc2 != -1) && (iLoc2 < sCurrentPath.length()) )
                        {
                            sCurrentPath = sCurrentPath.substring(0, iLoc2);
                        }
                    }
                }

                FinishPath();
            }
			else if ( TAKE_SCREENSHOT.equals(sActionCommand) )
			{
			    //System.out.println("TAKE_SCREENSHOT");
			    SingletonClass sc = SingletonClass.getInstance();
			    if ( sc.bConnected == false )
			        return;
			    
                if ( bLogcatOn )
                {
                    // Set to kill IOBgThread..
                    bBreakOut = true;
                    
                    // Wait for IOBgThread to end..
                    while ( true )
                    {
                        try
                        {
                            Thread.sleep(333);
                        }
                        catch (InterruptedException ie)
                        {
                        }
                        
                        if ( ! bLogcatOn )
                            break;
                    }
                }
			    
			    RefreshProperties();
			    boolean bUseDirect = false;
                sb = new StringBuffer();
                sBNm = new StringBuffer();
                sBNm.append("Screenshot_");
                
                Date date = new Date();
                SimpleDateFormat format = new SimpleDateFormat();
                String DateToStr = "";
                
                format = new SimpleDateFormat("yyyy-MM-dd-HH-mm");
                DateToStr = format.format(date);
                sBNm.append(DateToStr);
                
                sBNm.append(".png");
                //System.out.println("sBNm: '"+sBNm.toString()+"'");
                
                if ( iOS == LINUX_MAC )
                {
                    sb.append("export PATH=${PATH}:");
                    sb.append(androidSdkPathS);
                    sb.append("/platform-tools");
                    sb.append(";adb ");
                }
                else
                {
                    sb.append("SET PATH=");
                    sb.append(androidSdkPathS);
                    sb.append("/platform-tools");
                    sb.append(";%PATH%");
                    sb.append("&&adb ");
                }

                if ( (sDeviceName != null) && (sDeviceName.length() > 0) )
                {
                    sb.append("-s ");
                    sb.append(sDeviceName);
                    sb.append(" ");
                }
/**/

                // Note:
                // As an alternative, use this to transfer
                // to Download directory:
                
                // adb shell screencap -p /sdcard/screencap.png
                // adb pull /sdcard/screencap.png C:\\Users\\<username>\\Pictures\\screencap.png
                
                if ( (sUseDirectScreenshotDownload != null) && (sUseDirectScreenshotDownload.equals("true")) )
                {
                    // Direct..
                    bUseDirect = true;
                    sb.append("exec-out screencap -p > ");
                    
                    if ( (sDownloadDir != null) && (sDownloadDir.length() > 0) )
                    {
                        sb.append('"');
                        sb.append(sDownloadDir);
                        sb.append("/");
                        sb.append(sBNm.toString());
                        sb.append('"');
                    }
                }
                
                if ( bUseDirect == false )
                {
                    sb.append("shell screencap ");
                    
                    sb.append("-p /sdcard");
                    
/*                    
                    if ( (sScreenshotDir != null) && (sScreenshotDir.length() > 0) )
                        sb.append(sScreenshotDir);
                    else
                    {
                        sb.append("-p /sdcard");
                    }
/**/                    
                    sb.append("/");
                    sb.append(sBNm.toString());
                }
                
                if ( iOS == WINDOWS )
                    sb.append("\n");
                
                //System.out.println("sb: '"+sb.toString()+"'");
                // Set up for IOBgThread output..
                commandS = sb.toString();
                
                sc = SingletonClass.getInstance();
                sc.s_sCommand = commandS;
                
                bIOBgThreadFinished = false;
                iOBgThread = new IOBgThread();
                iOBgThread.start();
                
                while ( true )
                {
                    try
                    {
                        Thread.sleep(333);
                    }
                    catch (InterruptedException ie)
                    {
                    }
                    
                    if ( bIOBgThreadFinished )
                        break;
                }
                
                //System.out.println("Dropped out");
                
                if ( ((sUseAdbPullToDownload != null) && (sUseAdbPullToDownload.equals("true"))) &&
                     ((sDownloadDir != null) && (! sDownloadDir.equals("null")) && (sDownloadDir.length() > 0)) &&
                     (bUseDirect == false) )    
                {
                    // Delay a little..
                    try
                    {
                        Thread.sleep(1500);
                    }
                    catch (InterruptedException ie)
                    {
                    }
                    
                    sb = new StringBuffer();
                    
                    if ( iOS == LINUX_MAC )
                    {
                        sb.append("export PATH=${PATH}:");
                        sb.append(androidSdkPathS);
                        sb.append("/platform-tools");
                        sb.append(";adb ");
                    }
                    else
                    {
                        sb.append("SET PATH=");
                        sb.append(androidSdkPathS);
                        sb.append("/platform-tools");
                        sb.append(";%PATH%");
                        sb.append("&&adb ");
                    }
    
                    if ( (sDeviceName != null) && (sDeviceName.length() > 0) )
                    {
                        sb.append("-s ");
                        sb.append(sDeviceName);
                        sb.append(" ");
                    }
                    
                    sb.append("pull ");
                    
                    sb.append(" /sdcard/");
                    sb.append(sBNm.toString());
                    
                    sb.append(" ");
                    sb.append('"');
                    sb.append(sDownloadDir);    // Like:  "C:/Users/Joe Siebenmann/Downloads"
                    sb.append('"');
                    
                    //System.out.println("sb: '"+sb.toString()+"'");
                    
                    if ( iOS == WINDOWS )
                        sb.append("\n");
                    
                    // Set up for IOBgThread output..
                    commandS = sb.toString();
    
                    sc = SingletonClass.getInstance();
                    sc.s_sCommand = commandS;
                    
                    iOBgThread = new IOBgThread();
                    iOBgThread.start();
                }
			}
			else if ( REFRESH.equals(sActionCommand) )
			{
				RefreshProperties();
			}
			else if ( PULL_SCREENSHOT.equals(sActionCommand) )
			{
			    //System.out.println("PULL_SCREENSHOT");
			    if ( (DevicesAr != null) && (DevicesAr.size() > 0) )
			        ;
			    else
			        return;
			    
			    SingletonClass sc = SingletonClass.getInstance();
			    if ( sc.bConnected == false )
			        return;
			    
			    byte[] bZd = {(byte)0x0d};
			    String sbZd = new String(bZd);
			    
			    RefreshProperties();
			    sb = new StringBuffer();
			    
                if ( iOS == LINUX_MAC )
                {
                    sb.append("export PATH=${PATH}:");
                    sb.append(androidSdkPathS);
                    sb.append("/platform-tools");
                    sb.append(";adb ");
                }
                else
                {
                    sb.append("SET PATH=");
                    sb.append(androidSdkPathS);
                    sb.append("/platform-tools");
                    sb.append(";%PATH%");
                    sb.append("&&adb ");
                }
                
                if ( (sDeviceName != null) && (sDeviceName.length() > 0) )
                {
                    sb.append("-s ");
                    sb.append(sDeviceName);
                    sb.append(" ");
                }
/**/
			    
                sb.append(" shell ls ");
                
                if ( (sScreenshotDir != null) && (sScreenshotDir.length() > 0) )
                {
                    sb.append(sScreenshotDir);
                }
                else
                {
                    if ( sScreenshotPath != null )
                        sb.append(sScreenshotPath);
                }

                if ( iOS == WINDOWS )
                    sb.append("\n");
    
                //System.out.println("sb: '"+sb.toString()+"'");
                bCommandFinished = false;
                sInternalCommand = sb.toString();
                commandBgThread = new CommandBgThread();
                commandBgThread.start();
    
                // Wait for Thread to finish..
                while ( true )
                {
                    try
                    {
                        Thread.sleep(333);
                    }
                    catch (InterruptedException ie)
                    {
                    }
    
                    if ( bCommandFinished )
                        break;
                }

                //System.out.println("commandResultS: "+commandResultS); 
/*                
                System.out.println();
                char cTChr;
                
                for ( int g = 0; g < commandResultS.length(); g++ )
                {
                    cTChr = (char)commandResultS.charAt(g);
                    if ( (cTChr < 0x20) || (cTChr > 0x7e) )
                        System.out.print("["+Integer.toHexString((int)cTChr)+"]");
                    else
                        System.out.print(cTChr);
                }
                System.out.println();
/**/
                
                
                //      [java] Screenshot_20230131_092811_One UI Home.jpg[d][a]Screenshot_20230201_104133_One UI Home.jpg[d][a]                
                st = new StringTokenizer(commandResultS, sbZd);
                iCount = st.countTokens();
                //System.out.println("iCount: "+iCount);
                tokSa = new String[iCount];
                
                for ( iTokIndex = 0; st.hasMoreTokens(); iTokIndex++ )
                {
                    sT = st.nextToken();
                    sT = sT.trim();
                    if ( sT.equals("*") )
                        break;

                    //System.out.println("["+iTokIndex+"]: '"+tokSa[iTokIndex]+"'");
                    if ( (tokSa != null) && (iTokIndex < tokSa.length) )                   
                        tokSa[iTokIndex] = sT;
                }

                if ( (tokSa != null) && (tokSa.length > 0) )
                {
                    iListId = SCREENSHOT_LIST;
                    PullScreenshotDialog();
                }
			}
			else if ( PULL_SCREENSHOT_SUBMIT.equals(sActionCommand) )
			{
			    //System.out.println("PULL_SCREENSHOT_SUBMIT");
			    
                if ( bLogcatOn )
                {
                    // Set to kill IOBgThread..
                    bBreakOut = true;
                    
                    // Wait for IOBgThread to end..
                    while ( true )
                    {
                        try
                        {
                            Thread.sleep(333);
                        }
                        catch (InterruptedException ie)
                        {
                        }
                        
                        if ( ! bLogcatOn )
                            break;
                    }
                }
			    
			    RefreshProperties();
			    
			    
                if ( (selectionList != null) && (selectionList.size() > 0) )
                {
                    for ( int iSelIndex = 0; iSelIndex < selectionList.size(); iSelIndex++ )
                    {
                        //System.out.println("--TOP--  iSelIndex: "+iSelIndex);
                        sListSelection = selectionList.get(iSelIndex);
                        //System.out.println("sListSelection: '"+sListSelection+"'");
			    
                        sb = new StringBuffer();
                        
                        if ( iOS == LINUX_MAC )
                        {
                            sb.append("export PATH=${PATH}:");
                            sb.append(androidSdkPathS);
                            sb.append("/platform-tools");
                            sb.append(";adb ");
                        }
                        else
                        {
                            sb.append("SET PATH=");
                            sb.append(androidSdkPathS);
                            sb.append("/platform-tools");
                            sb.append(";%PATH%");
                            sb.append("&&adb ");
                        }
        
                        if ( (sDeviceName != null) && (sDeviceName.length() > 0) )
                        {
                            sb.append("-s ");
                            sb.append(sDeviceName);
                            sb.append(" ");
                        }
                        
                        sb.append("pull ");
                        if ( (sScreenshotDir != null) && (sScreenshotDir.length() > 0) )
                            sb.append(sScreenshotDir);
                        else
                        {
                            if ( sScreenshotPath != null )
                                sb.append(sScreenshotPath);
                        }
                        
                        sb.append("/");
                        if ( (sListSelection != null) && (sListSelection.length() > 0) )
                        {
                            sb.append('"');
                            sb.append(sListSelection);
                            sb.append('"');
                        }
                        
                        sb.append(" ");
                        if ( (sDownloadDir != null) && (sDownloadDir.length() > 0) )
                        {
                            sb.append('"');
                            sb.append(sDownloadDir);
                            sb.append('"');
                        }
        
                        if ( iOS == WINDOWS )
                            sb.append("\n");
        
                        // Set up for IOBgThread output..
                        commandS = sb.toString();
                        
                        SingletonClass sc = SingletonClass.getInstance();
                        sc.s_sCommand = commandS;
                        
                        bIOBgThreadFinished = false;
                        iOBgThread = new IOBgThread();
                        iOBgThread.start();
                        
                        while ( true )
                        {
                            try
                            {
                                Thread.sleep(333);
                            }
                            catch (InterruptedException ie)
                            {
                            }
                            
                            if ( bIOBgThreadFinished )
                                break;
                        }
                    }   // End for..
                }
                
				screenshotFrame.setVisible(false);
				screenshotFrame.dispose();

            }
			else if ( SCREEN_RECORD.equals(sActionCommand) )
			{
			    //System.out.println("SCREEN_RECORD");
			    SingletonClass sc = SingletonClass.getInstance();
			    if ( sc.bConnected == false )
			        return;
			    
                if ( bLogcatOn )
                {
                    // Set to kill IOBgThread..
                    bBreakOut = true;
                    
                    // Wait for IOBgThread to end..
                    while ( true )
                    {
                        try
                        {
                            Thread.sleep(333);
                        }
                        catch (InterruptedException ie)
                        {
                        }
                        
                        if ( ! bLogcatOn )
                            break;
                    }
                }
			    
			    RefreshProperties();
                sb = new StringBuffer();
                
                // Like:  'Screenrecord_2024-12-02-11-50.mp4'
                sBNm = new StringBuffer();
                sBNm.append("Screenrecord_");
                
                Date date = new Date();
                SimpleDateFormat format = new SimpleDateFormat();
                String DateToStr = "";
                
                format = new SimpleDateFormat("yyyy-MM-dd-HH-mm");
                DateToStr = format.format(date);
                sBNm.append(DateToStr);
                sBNm.append(".mp4");
                //System.out.println("sBNm: '"+sBNm.toString()+"'");
                
                if ( iOS == LINUX_MAC )
                {
                    sb.append("export PATH=${PATH}:");
                    sb.append(androidSdkPathS);
                    sb.append("/platform-tools");
                    sb.append(";adb ");
                }
                else
                {
                    sb.append("SET PATH=");
                    sb.append(androidSdkPathS);
                    sb.append("/platform-tools");
                    sb.append(";%PATH%");
                    sb.append("&&adb ");
                }

                if ( (sDeviceName != null) && (sDeviceName.length() > 0) )
                {
                    sb.append("-s ");
                    sb.append(sDeviceName);
                    sb.append(" ");
                }
/**/

                sb.append("shell screenrecord");

                if ( (sScreenRecordShowVerbose != null) && (sScreenRecordShowVerbose.length() > 0) &&
                    (sScreenRecordShowVerbose.equals("true")) )
                {
                    sb.append(" --verbose");
                }
                
                if ( (sScreenRecordTimeLimit != null) && (sScreenRecordTimeLimit.length() > 0) &&
                    (! sScreenRecordTimeLimit.equals("null")) )
                {
                    sb.append(" --time-limit ");
                    sb.append(sScreenRecordTimeLimit);
                }
                
                if ( (sScreenRecordVideoSize != null) && (sScreenRecordVideoSize.length() > 0) &&
                    (! sScreenRecordVideoSize.equals("null")) )
                {
                    sb.append(" --size ");
                    sb.append(sScreenRecordVideoSize);
                }
                
                if ( (sScreenRecordBitRate != null) && (sScreenRecordBitRate.length() > 0) &&
                    (! sScreenRecordBitRate.equals("null")) )
                {
                    sb.append(" --bit-rate ");
                    sb.append(sScreenRecordBitRate);
                }

                sb.append(" /sdcard/");
                sb.append(sBNm.toString());
                
                if ( iOS == WINDOWS )
                    sb.append("\n");
                
                //System.out.println("sb: '"+sb.toString()+"'");
                // Set up for IOBgThread output..
                commandS = sb.toString();

                sc = SingletonClass.getInstance();
                sc.s_sCommand = commandS;
                
                bIOBgThreadFinished = false;
                iOBgThread = new IOBgThread();
                iOBgThread.start();
                
                while ( true )
                {
                    try
                    {
                        Thread.sleep(333);
                    }
                    catch (InterruptedException ie)
                    {
                    }
                    
                    if ( bIOBgThreadFinished )
                        break;
                }
                
                //System.out.println("Dropped out");
                
                if ( ((sUseAdbPullToDownload != null) && (sUseAdbPullToDownload.equals("true"))) &&
                     ((sDownloadDir != null) && (! sDownloadDir.equals("null")) && (sDownloadDir.length() > 0)) )    
                {
                    // Delay a little..
                    try
                    {
                        Thread.sleep(1500);
                    }
                    catch (InterruptedException ie)
                    {
                    }
                    
                    sb = new StringBuffer();
                    
                    if ( iOS == LINUX_MAC )
                    {
                        sb.append("export PATH=${PATH}:");
                        sb.append(androidSdkPathS);
                        sb.append("/platform-tools");
                        sb.append(";adb ");
                    }
                    else
                    {
                        sb.append("SET PATH=");
                        sb.append(androidSdkPathS);
                        sb.append("/platform-tools");
                        sb.append(";%PATH%");
                        sb.append("&&adb ");
                    }
    
                    if ( (sDeviceName != null) && (sDeviceName.length() > 0) )
                    {
                        sb.append("-s ");
                        sb.append(sDeviceName);
                        sb.append(" ");
                    }
                    
                    sb.append("pull ");
                    
                    sb.append(" /sdcard/");
                    sb.append(sBNm.toString());
                    
                    sb.append(" ");
                    sb.append('"');
                    sb.append(sDownloadDir);    // Like:  "C:/Users/Joe Siebenmann/Downloads"
                    sb.append('"');
                    
                    //System.out.println("sb: '"+sb.toString()+"'");
                    
                    if ( iOS == WINDOWS )
                        sb.append("\n");
                    
                    // Set up for IOBgThread output..
                    commandS = sb.toString();
    
                    sc = SingletonClass.getInstance();
                    sc.s_sCommand = commandS;
                    
                    iOBgThread = new IOBgThread();
                    iOBgThread.start();
                }
			}
			else if ( LIST_PIDS.equals(sActionCommand) ||
			    LIST_PACKAGES.equals(sActionCommand) ||
			    GETPROP.equals(sActionCommand) ||
			    ADB_COMMAND_SUBMIT.equals(sActionCommand) )
			{
			    //System.out.println("ADB Command");
			    SingletonClass sc = SingletonClass.getInstance();
			    if ( sc.bConnected == false )
			        return;
			    
                if ( bLogcatOn )
                {
                    // Set to kill IOBgThread..
                    bBreakOut = true;
                    
                    // Wait for IOBgThread to end..
                    while ( true )
                    {
                        try
                        {
                            Thread.sleep(333);
                        }
                        catch (InterruptedException ie)
                        {
                        }
                        
                        if ( ! bLogcatOn )
                            break;
                    }
                }
                
                RefreshProperties();
                sb = new StringBuffer();
			    
                if ( iOS == LINUX_MAC )
                {
                    sb.append("export PATH=${PATH}:");
                    sb.append(androidSdkPathS);
                    sb.append("/platform-tools");
                    sb.append(";adb ");
                }
                else
                {
                    sb.append("SET PATH=");
                    sb.append(androidSdkPathS);
                    sb.append("/platform-tools");
                    sb.append(";%PATH%");
                    sb.append("&&adb ");
                }

                if ( (sDeviceName != null) && (sDeviceName.length() > 0) )
                {
                    sb.append("-s ");
                    sb.append(sDeviceName);
                    sb.append(" ");
                }
/**/

                if ( LIST_PIDS.equals(sActionCommand) )
                    sb.append("shell ps");
                else if ( LIST_PACKAGES.equals(sActionCommand) )
                    sb.append("shell pm list packages");
                else if ( GETPROP.equals(sActionCommand) )
                    sb.append("shell getprop");
                else
                {
/*                    
                    if ( adbCommandField == null )
                        System.out.println("adbCommandField null");
                    else
                        System.out.println("adbCommandField not null");
/**/                        
                    if ( adbCommandField != null )
                    {
                        sT = adbCommandField.getText();
                        if ( (sT != null) && (sT.length() > 4) )
                        {
                            //System.out.println("sT: '"+sT+"'");
                            sT = sT.trim();
                            if ( sT.startsWith("adb") )
                            {
                                if ( 4 < sT.length() )
                                    sb.append(sT.substring(4));
                            }
                        }
                    }
                }
                
                if ( iOS == WINDOWS )
                    sb.append("\n");
                
                //System.out.println("sb: '"+sb.toString()+"'");
                // Set up for IOBgThread output..
                commandS = sb.toString();

                sc = SingletonClass.getInstance();
                sc.s_sCommand = commandS;
                
                iOBgThread = new IOBgThread();
                iOBgThread.start();
                
			}
			else if ( REBOOT.equals(sActionCommand) ||
			    REBOOT_RECOVERY.equals(sActionCommand) )
			{
			    //System.out.println("REBOOT");
			    SingletonClass sc = SingletonClass.getInstance();
			    if ( sc.bConnected == false )
			        return;
			    
			    RefreshProperties();
			    sb = new StringBuffer();
			    
                if ( iOS == LINUX_MAC )
                {
                    sb.append("export PATH=${PATH}:");
                    sb.append(androidSdkPathS);
                    sb.append("/platform-tools");
                    sb.append(";adb ");
                }
                else
                {
                    sb.append("SET PATH=");
                    sb.append(androidSdkPathS);
                    sb.append("/platform-tools");
                    sb.append(";%PATH%");
                    sb.append("&&adb ");
                }
                
                if ( (sDeviceName != null) && (sDeviceName.length() > 0) )
                {
                    sb.append("-s ");
                    sb.append(sDeviceName);
                    sb.append(" ");
                }
/**/

                if ( REBOOT.equals(sActionCommand) )
                    sb.append("reboot");
                else
                    sb.append("reboot recovery");

                if ( iOS == WINDOWS )
                    sb.append("\n");
    
                //System.out.println("sb: '"+sb.toString()+"'");
                bCommandFinished = false;
                sInternalCommand = sb.toString();
                commandBgThread = new CommandBgThread();
                commandBgThread.start();
    
                // Wait for Thread to finish..
                while ( true )
                {
                    try
                    {
                        Thread.sleep(333);
                    }
                    catch (InterruptedException ie)
                    {
                    }
    
                    if ( bCommandFinished )
                        break;
                }

                // Reset..                
                deviceLabel.setText((String)"   ");
                sDeviceName = "";
                
            }
            else if ( SELECT_PACKAGE.equals(sActionCommand) )
            {
                //System.out.println("\nSELECT_PACKAGE");
			    SingletonClass sc = SingletonClass.getInstance();
			    if ( sc.bConnected == false )
			        return;
                
                ArrayList tAr = null;
                String[] tSa;
                int iLoc3 = 0;
                boolean bOk = false;
                
                sSelectedMenu = "Select Package";
                
                sb = new StringBuffer();
                
                if ( iOS == LINUX_MAC )
                {
                    sb.append("export PATH=${PATH}:");
                    sb.append(androidSdkPathS);
                    sb.append("/platform-tools");
                    sb.append(";adb ");
                }
                else
                {
                    sb.append("SET PATH=");
                    sb.append(androidSdkPathS);
                    sb.append("/platform-tools");
                    sb.append(";%PATH%");
                    sb.append("&&adb ");
                }
                
                sb.append("shell ps");
                    
                if ( iOS == WINDOWS )
                    sb.append("\n");
                
                bCommandFinished = false;		
                sInternalCommand = sb.toString();
                commandBgThread = new CommandBgThread();
                commandBgThread.start();
        
                // Wait for Thread to finish..
                while ( true )
                {
                    try
                    {
                        Thread.sleep(333);
                    }
                    catch (InterruptedException ie)
                    {
                    }
        
                    if ( bCommandFinished )
                        break;
                }
                
                //System.out.println("commandResultS: '"+commandResultS+"'");
                if ( (commandResultS != null) && (commandResultS.length() > 0) )
                {
                    iLoc = 0;
                    iLoc2 = 0;
                    tAr = new ArrayList();
                    
                    iLoc = commandResultS.indexOf("root");      // Skip column titles..
                    //System.out.println("iLoc: "+iLoc);
                    
                    while ( true )
                    {
                        iLoc2 = commandResultS.indexOf(0x0a, iLoc);
                        if ( iLoc2 != -1 )
                        {
                            bOk = false;
                            
                            iLoc3 = commandResultS.indexOf(" S ", iLoc);
                            if ( (iLoc3 != -1) && (iLoc3 < iLoc2) )
                            {
                                bOk = true;
                            }
                            else
                            {
                                iLoc3 = commandResultS.indexOf(" R ", iLoc);
                                if ( (iLoc3 != -1) && (iLoc3 < iLoc2) )
                                {
                                    bOk = true;
                                }
                            }
                            
                            if ( bOk )
                            {
                                iLoc3 += 3;
                                
                                if ( (iLoc3 >= 0) && (iLoc2 < commandResultS.length()) )
                                    sT = commandResultS.substring(iLoc3, iLoc2);
                                //System.out.println("sT: '"+sT+"'");
                                if ( (sT.startsWith("/")) ||
                                        (sT.contains(" ")) ||
                                        (Character.isDigit(sT.charAt(0))) ||
                                        (sT.contains(":")) ||
                                        (sT.contains("-")) )
                                    ;
                                else
                                {
                                    if ( sT.contains(".") )
                                        tAr.add((String)sT);
                                }
                            }
                        }
                        else
                            break;
                        
                        iLoc = iLoc2 + 1;
                        
                    }   // End while..
        
                    if ( tAr.size() > 0 )
                    {
                        tokSa = new String[tAr.size()];
                        for ( int iX = 0; iX < tAr.size(); iX++ )
                            tokSa[iX] = (String)tAr.get(iX);
                        
                        Arrays.sort(tokSa);
                        iListId = PACKAGE_LIST;
                        PackageDialog("Select Package");
                    }
                }
            }
			else if ( PULL_CAMERA_IMAGE.equals(sActionCommand) )
			{
			    //System.out.println("PULL_CAMERA_IMAGE");
			    if ( (DevicesAr != null) && (DevicesAr.size() > 0) )
			        ;
			    else
			        return;
			    
			    SingletonClass sc = SingletonClass.getInstance();
			    if ( sc.bConnected == false )
			        return;
			    
			    byte[] bZd = {(byte)0x0d};
			    String sbZd = new String(bZd);
			    
			    RefreshProperties();
			    
			    sb = new StringBuffer();
			    
                if ( iOS == LINUX_MAC )
                {
                    sb.append("export PATH=${PATH}:");
                    sb.append(androidSdkPathS);
                    sb.append("/platform-tools");
                    sb.append(";adb ");
                }
                else
                {
                    sb.append("SET PATH=");
                    sb.append(androidSdkPathS);
                    sb.append("/platform-tools");
                    sb.append(";%PATH%");
                    sb.append("&&adb ");
                }

                if ( (sDeviceName != null) && (sDeviceName.length() > 0) )
                {
                    sb.append("-s ");
                    sb.append(sDeviceName);
                    sb.append(" ");
                }
/**/
			    
                sb.append("shell ls ");
                if ( (sCameraDir != null) && (sCameraDir.length() > 0) )
                    sb.append(sCameraDir);
                else
                {
                    if ( sCameraPath != null )
                        sb.append(sCameraPath);
                }

                if ( iOS == WINDOWS )
                    sb.append("\n");

         
                //System.out.println("sb: '"+sb.toString()+"'");
                bCommandFinished = false;
                sInternalCommand = sb.toString();
                commandBgThread = new CommandBgThread();
                commandBgThread.start();
    
                // Wait for Thread to finish..
                while ( true )
                {
                    try
                    {
                        Thread.sleep(333);
                    }
                    catch (InterruptedException ie)
                    {
                    }
    
                    if ( bCommandFinished )
                        break;
                }
                
                st = new StringTokenizer(commandResultS, sbZd);
                iCount = st.countTokens();
                //System.out.println("iCount: "+iCount);
                tokSa = new String[iCount];
                
                for ( iTokIndex = 0; st.hasMoreTokens(); iTokIndex++ )
                {
                    sT = st.nextToken();
                    sT = sT.trim();
                    if ( sT.equals("*") )
                        break;
                    
                    if ( (tokSa != null) && (iTokIndex < tokSa.length) )
                        tokSa[iTokIndex] = sT;
                }

                if ( (tokSa != null) && (tokSa.length > 0) )
                {
                    iListId = CAMERA_IMAGE_LIST;
                    PullCameraImageDialog();
                }
			}
			else if ( PULL_CAMERA_IMAGE_SUBMIT.equals(sActionCommand) )
			{
			    //System.out.println("PULL_CAMERA_IMAGE_SUBMIT");

                if ( bLogcatOn )
                {
                    // Set to kill IOBgThread..
                    bBreakOut = true;
                    
                    // Wait for IOBgThread to end..
                    while ( true )
                    {
                        try
                        {
                            Thread.sleep(333);
                        }
                        catch (InterruptedException ie)
                        {
                        }
                        
                        if ( ! bLogcatOn )
                            break;
                    }
                }
			    
			    RefreshProperties();
/*			    
			    if ( selectionList == null )
			        System.out.println("selectionList null");
			    else
			        System.out.println("selectionList.size(): "+selectionList.size());
/**/

                if ( (selectionList != null) && (selectionList.size() > 0) )
                {
                    for ( int iSelIndex = 0; iSelIndex < selectionList.size(); iSelIndex++ )
                    {
                        //System.out.println("--TOP--  iSelIndex: "+iSelIndex);
                        sListSelection = selectionList.get(iSelIndex);
                        //System.out.println("sListSelection: '"+sListSelection+"'");
			    
                        sb = new StringBuffer();
                        
                        if ( iOS == LINUX_MAC )
                        {
                            sb.append("export PATH=${PATH}:");
                            sb.append(androidSdkPathS);
                            sb.append("/platform-tools");
                            sb.append(";adb ");
                        }
                        else
                        {
                            sb.append("SET PATH=");
                            sb.append(androidSdkPathS);
                            sb.append("/platform-tools");
                            sb.append(";%PATH%");
                            sb.append("&&adb ");
                        }
        
                        if ( (sDeviceName != null) && (sDeviceName.length() > 0) )
                        {
                            sb.append("-s ");
                            sb.append(sDeviceName);
                            sb.append(" ");
                        }
                        
                        sb.append("pull ");
                        if ( (sCameraDir != null) && (sCameraDir.length() > 0) )
                            sb.append(sCameraDir);
                        else
                        {
                            if ( sCameraPath != null )
                                sb.append(sCameraPath);
                        }
                        
                        sb.append("/");
                        if ( (sListSelection != null) && (sListSelection.length() > 0) )
                        {
                            sb.append('"');
                            sb.append(sListSelection);
                            sb.append('"');
                        }
                        
                        sb.append(" ");
                        if ( (sDownloadDir != null) && (sDownloadDir.length() > 0) )
                        {
                            sb.append('"');
                            sb.append(sDownloadDir);
                            sb.append('"');
                        }
        
                        if ( iOS == WINDOWS )
                            sb.append("\n");
        
                        // Set up for IOBgThread output..
                        commandS = sb.toString();

                        SingletonClass sc = SingletonClass.getInstance();
                        sc.s_sCommand = commandS;
                        
                        bIOBgThreadFinished = false;                        
                        iOBgThread = new IOBgThread();
                        iOBgThread.start();
                        
                        while ( true )
                        {
                            try
                            {
                                Thread.sleep(333);
                            }
                            catch (InterruptedException ie)
                            {
                            }
                            
                            if ( bIOBgThreadFinished )
                                break;
                        }
                    }   // End for..
                }
                

				cameraFrame.setVisible(false);
				cameraFrame.dispose();

            }
			else if ( INSTALL_APK.equals(sActionCommand) )
			{
			    //System.out.println("INSTALL_APK");
			    SingletonClass sc = SingletonClass.getInstance();
			    if ( sc.bConnected == false )
			        return;

                if ( bLogcatOn )
                {
                    // Set to kill IOBgThread..
                    bBreakOut = true;
                    
                    // Wait for IOBgThread to end..
                    while ( true )
                    {
                        //Thread.yield();
                        try
                        {
                            Thread.sleep(333);
                        }
                        catch (InterruptedException ie)
                        {
                        }
                        
                        if ( ! bLogcatOn )
                            break;
                    }
                }
			    
			    RefreshProperties();
			    
			    JFileChooser fChooser;

                fChooser = new JFileChooser();
                //fChooser.setCurrentDirectory(new java.io.File("."));
                fChooser.setCurrentDirectory(FileSystemView.getFileSystemView().getHomeDirectory());

			    
				fChooser.setDialogTitle("Apk file");
				fChooser.setAcceptAllFileFilterUsed(false);
				FileNameExtensionFilter filter = new FileNameExtensionFilter("APK", "apk");
				fChooser.addChoosableFileFilter(filter);
				
				fChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);    // What we can select..
				
				if ( fChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION )
				{
					// Selected..
					//System.out.println("File selected");
					sSelectedFile = fChooser.getSelectedFile().getPath();
					//System.out.println("sSelectedFile: '"+sSelectedFile+"'");
					
					sb = new StringBuffer();
					
                    if ( iOS == LINUX_MAC )
                    {
                        sb.append("export PATH=${PATH}:");
                        sb.append(androidSdkPathS);
                        sb.append("/platform-tools");
                        sb.append(";adb ");
                    }
                    else
                    {
                        sb.append("SET PATH=");
                        sb.append(androidSdkPathS);
                        sb.append("/platform-tools");
                        sb.append(";%PATH%");
                        sb.append("&&adb ");
                    }
                    
                    if ( (sDeviceName != null) && (sDeviceName.length() > 0) )
                    {
                        sb.append("-s ");
                        sb.append(sDeviceName);
                        sb.append(" ");
                    }
/**/
    
                    sb.append("install ");
                    sb.append(sSelectedFile);
                    
                    if ( iOS == WINDOWS )
                        sb.append("\n");
                    
                    // Set up for IOBgThread output..
                    commandS = sb.toString();
                    
                    sc = SingletonClass.getInstance();
                    sc.s_sCommand = commandS;
                    
                    iOBgThread = new IOBgThread();
                    iOBgThread.start();
                    
				}
			    
			}
			else if ( UNINSTALL_APK.equals(sActionCommand) )
			{
			    //System.out.println("UNINSTALL_APK");
			    SingletonClass sc = SingletonClass.getInstance();
			    if ( sc.bConnected == false )
			        return;
			    
			    RefreshProperties();
			    sb = new StringBuffer();
			    String[] tSa;
			    ArrayList tAr;
			    
                if ( iOS == LINUX_MAC )
                {
                    sb.append("export PATH=${PATH}:");
                    sb.append(androidSdkPathS);
                    sb.append("/platform-tools");
                    sb.append(";adb ");
                }
                else
                {
                    sb.append("SET PATH=");
                    sb.append(androidSdkPathS);
                    sb.append("/platform-tools");
                    sb.append(";%PATH%");
                    sb.append("&&adb ");
                }

                if ( (sDeviceName != null) && (sDeviceName.length() > 0) )
                {
                    sb.append("-s ");
                    sb.append(sDeviceName);
                    sb.append(" ");
                }
/**/
			    
                sb.append("shell pm list packages");

                if ( iOS == WINDOWS )
                    sb.append("\n");

                //System.out.println("sb: '"+sb.toString()+"'");
                bCommandFinished = false;
                sInternalCommand = sb.toString();
                commandBgThread = new CommandBgThread();
                commandBgThread.start();
    
                // Wait for Thread to finish..
                while ( true )
                {
                    try
                    {
                        Thread.sleep(333);
                    }
                    catch (InterruptedException ie)
                    {
                    }
    
                    if ( bCommandFinished )
                        break;
                }
                
                st = new StringTokenizer(commandResultS);
                iCount = st.countTokens();
                //System.out.println("iCount: "+iCount);

                // Originally like:  'package:com.dominionmobile.android.hurricane'                
                iLoc = 0;
                tAr = new ArrayList();
                for ( iTokIndex = 0; st.hasMoreTokens(); iTokIndex++ )
                {
                    sT = st.nextToken();
                    sT = sT.trim();
                    if ( sT.equals("package:android") )
                        ;   // Skip..
                    else
                    {
                        // Remove 'package:'
                        iLoc = sT.indexOf(":");
                        if ( (iLoc != -1) && ((iLoc + 1) < sT.length()) )
                            tAr.add((String)sT.substring(iLoc + 1));
                    }
                }
                
                tokSa = new String[tAr.size()];
                for ( int iX = 0; iX < tAr.size(); iX++ )
                    tokSa[iX] = (String)tAr.get(iX);

                if ( (tokSa != null) && (tokSa.length > 0) )
                {
                    Arrays.sort(tokSa);
                    iListId = PACKAGE_LIST;
                    PackageDialog("Uninstall package");
                    
                }
            }
			else if ( PACKAGE_SUBMIT.equals(sActionCommand) )
			{
			    //System.out.println("\nPACKAGE_SUBMIT");

                bCheckDeviceFinished = false;
                checkDeviceBgThread = new CheckDeviceBgThread();
                checkDeviceBgThread.start();
                
                while ( true )
                {
                    try
                    {
                        Thread.sleep(333);
                    }
                    catch (InterruptedException ie)
                    {
                    }
    
                    if ( bCheckDeviceFinished )
                        break;
                }

                if ( (sWakefulness != null) && (sWakefulness.equals("Asleep")) )
                {
                    // Wake up device..
                    bWakeDeviceFinished = false;
                    wakeDeviceBgThread = new WakeDeviceBgThread();
                    wakeDeviceBgThread.start();
                    
                    while ( true )
                    {
                        try
                        {
                            Thread.sleep(333);
                        }
                        catch (InterruptedException ie)
                        {
                        }
        
                        if ( bWakeDeviceFinished )
                            break;
                    }
                }
			    
			    if ( (sListSelection != null) && (sListSelection.length() > 0) )
			    {
			        //System.out.println("sListSelection: '"+sListSelection+"'");
			        if ( (sSelectedMenu != null) && (sSelectedMenu.equals("Select Package")) )
			        {
			            // Select Package..
			            sListSelection = sListSelection.trim();
                        SingletonClass sc = SingletonClass.getInstance();
                        sc.sPackageName = sListSelection;
			            //System.out.println("\nsc.sPackageName: '"+sc.sPackageName+"'");

			        }
			        else
			        {
			            // Uninstall..
                        sb = new StringBuffer();
                        
                        if ( iOS == LINUX_MAC )
                        {
                            sb.append("export PATH=${PATH}:");
                            sb.append(androidSdkPathS);
                            sb.append("/platform-tools");
                            
                            sb.append(";adb ");
        
                            if ( (sDeviceName != null) && (sDeviceName.length() > 0) )
                            {
                                sb.append("-s ");
                                sb.append(sDeviceName);
                                sb.append(" ");
                            }
                            
                            sb.append("uninstall ");
                            sb.append(sListSelection);
                        }
                        else
                        {
                            sb.append("SET PATH=");
                            sb.append(androidSdkPathS);
                            sb.append("/platform-tools");
                            sb.append(";%PATH%");
                            
                            sb.append("&&adb ");
                            
                            if ( (sDeviceName != null) && (sDeviceName.length() > 0) )
                            {
                                sb.append("-s ");
                                sb.append(sDeviceName);
                                sb.append(" ");
                            }
                            
                            sb.append("uninstall ");
                            sb.append(sListSelection);
                            sb.append("\n");
                            
                        }
                        
                        // Set up for IOBgThread output..
                        commandS = sb.toString();

                        SingletonClass sc = SingletonClass.getInstance();
                        sc.s_sCommand = commandS;
                        
                        iOBgThread = new IOBgThread();
                        iOBgThread.start();
                    }
                }
                    
                packageFrame.setVisible(false);
                packageFrame.dispose();
            
			}
			else if ( SELECT_DEVICE.equals(sActionCommand) )
			{
			    //System.out.println("SELECT_DEVICE");
			    
			    RefreshProperties();
			    
				DevicesAr = new ArrayList();				
				//completeLatch = new CountDownLatch(1);
				
				bDevicesFinished = false;
				getDevicesBgThread = new GetDevicesBgThread();
				getDevicesBgThread.start();

				while ( true )
				{
                    try
                    {
                        //completeLatch.await();
                        Thread.sleep(350);
                    }
                    catch (InterruptedException ie)
                    {
                    }
                    
                    if ( bDevicesFinished )
                        break;
                }

/*				
				if ( DevicesAr == null )
					System.out.println("(Before selectDeviceDialog())DevicesAr null");
				else
					System.out.println("(Before selectDeviceDialog())DevicesAr.size(): "+DevicesAr.size());
/**/

                if ( (DevicesAr != null) && (DevicesAr.size() == 1) )
                {
                    // Set Device..
                    SingletonClass sc = SingletonClass.getInstance();
                    sc.bConnected = true;
                    
                    sDeviceName = (String)DevicesAr.get(0);
                    //System.out.println("sDeviceName: '"+sDeviceName+"'");

                    // Load Status bar..
                    if ( (sDeviceName != null) && (sDeviceName.length() > 0) )
                        deviceLabel.setText(sDeviceName);
                }
				else if ( (DevicesAr != null) && (DevicesAr.size() > 1) )
					selectDeviceDialog();
			    
            }
            else if ( SELECT_DEVICE_SUBMIT.equals(sActionCommand) )
            {
                //System.out.println("SELECT_DEVICE_SUBMIT");
				// Set Device..
				RefreshProperties();
				
                SingletonClass sc = SingletonClass.getInstance();
                sc.bConnected = true;
				
				sDeviceName = (String)deviceSpinner.getValue();
				//System.out.println("sDeviceName: '"+sDeviceName+"'");
				
				// Load Status bar..
				if ( (sDeviceName != null) && (sDeviceName.length() > 0) )
					deviceLabel.setText(sDeviceName);
				
				bSelectFinished = true;
				selectDeviceFrame.dispose();
                
            }
			else if ( DEVICES.equals(sActionCommand) )
			{
			    //System.out.println("DEVICES");
				// Devices..
				if ( bLogcatOn )
				{
                    // Set to kill IOBgThread..
                    bBreakOut = true;
                    
                    // Wait for IOBgThread to end..
                    while ( true )
                    {
                        try
                        {
                            Thread.sleep(333);
                        }
                        catch (InterruptedException ie)
                        {
                        }
                        
                        if ( ! bLogcatOn )
                            break;
                    }
                    
                    bLogcatOn = false;

                    // Turn off status message..                    
                    statusPath.setText("    ");
				}
				
				sb = new StringBuffer();
				
				if ( iOS == LINUX_MAC )
				{
					sb.append("export PATH=${PATH}:");
					sb.append(androidSdkPathS);
					sb.append("/platform-tools");
					
					sb.append(";adb devices -l");
				}
				else
				{
					sb.append("SET PATH=");
					sb.append(androidSdkPathS);
					sb.append("/platform-tools");
					sb.append(";%PATH%");
				
					sb.append("&&adb devices -l");
					sb.append("\n");
				}
				
				commandS = sb.toString();

                SingletonClass sc = SingletonClass.getInstance();
                sc.s_sCommand = commandS;
				
				iOBgThread = new IOBgThread();
				iOBgThread.start();
				
				
			}
			else if ( LOGCAT.equals(sActionCommand) )
			{
			    //System.out.println("\nLOGCAT");
			    SingletonClass sc = SingletonClass.getInstance();
			    if ( sc.bConnected == false )
			        return;
			    
                //System.out.println("bLogcatOn: "+bLogcatOn);			    
			    if ( bLogcatOn )
			    {
			        
			        if ( iOBgThread != null )
			        {
			            iOBgThread.interrupt();
			        }
			        
                    // Set to kill IOBgThread..
                    bIOBgThreadFinished = false;
                    bBreakOut = true;
                    //System.out.println("Set bBreakOut");
                    
                    // Wait for IOBgThread to end..
                    while ( true )
                    {
                        try
                        {
                            Thread.sleep(333);
                        }
                        catch (InterruptedException ie)
                        {
                        }
                        
                        if ( bIOBgThreadFinished ) 
                            break;
                    }
                    
                    //System.out.println("Dropped out of IOBgThread");
                    
                    bLogcatOn = false;

                    // Turn off status message..                    
                    statusPath.setText("    ");
			    }
			    else
			    {
			        // Logcat off..
			        //System.out.println("\nStarting logcat");
                    RefreshProperties();
                    
                    // Clear current PID..
                    sPid = "";
                    
                    sb = new StringBuffer();

                    // Clear buffer..                    
                    if ( iOS == LINUX_MAC )
                    {
                        sb.append("export PATH=${PATH}:");
                        sb.append(androidSdkPathS);
                        sb.append("/platform-tools");
                        
                        sb.append(";adb ");
                        
                        if ( (sDeviceName != null) && (sDeviceName.length() > 0) )
                        {
                            sb.append("-s ");
                            sb.append(sDeviceName);
                            sb.append(" ");
                        }
                        
                        //sb.append(";adb logcat -c");
                        sb.append("logcat -c");
                        sb.append(";");
                        
                    }
                    else
                    {
                        sb.append("SET PATH=");
                        sb.append(androidSdkPathS);
                        sb.append("/platform-tools");
                        sb.append(";%PATH%");
                        
                        sb.append("&&adb ");
                        
                        if ( (sDeviceName != null) && (sDeviceName.length() > 0) )
                        {
                            sb.append("-s ");
                            sb.append(sDeviceName);
                            sb.append(" ");
                        }
                        
                        //sb.append("&&adb logcat -c");
                        sb.append("logcat -c");
                        sb.append("&&");
                        
                    }
                    
                    sb.append("adb ");

                    if ( (sDeviceName != null) && (sDeviceName.length() > 0) )
                    {
                        sb.append("-s ");
                        sb.append(sDeviceName);
                        sb.append(" ");
                    }
/**/

					// Try to default to a more compact version..
					// Also ensures that highlighting will work..
                    sb.append("logcat -v brief");
                    
                    //sb.append("logcat -c -v brief");
                    //sb.append("logcat -c");
                    //sb.append("logcat");
                    
					if ( (logcatFilterS != null) && (! logcatFilterS.equals("")) )
					{
						// Construct Logcat command..
						st = new StringTokenizer(logcatFilterS, ",");
					    
						while ( st.hasMoreTokens() )
						{
							sT = st.nextToken();
							sb.append(" ");
							sb.append(sT);
							sb.append(":F");
						}
					}
    
                    if ( iOS == WINDOWS )
                        sb.append("\n");
    
                    textPane.setText("");
                
                    commandS = sb.toString();
                    //System.out.println("commandS: '"+commandS+"'");
                    
                    sc = SingletonClass.getInstance();
                    sc.s_sCommand = commandS;
                    
                    bLogcatOn = true;

                    // Turn on status message..                    
                    statusPath.setText("  Logcat on");

                    bIOBgThreadFinished = false;                    
                    iOBgThread = new IOBgThread();
                    iOBgThread.start();
                }
			}
			else if ( WIRELESS_CONNECT.equals(sActionCommand) )
			{
			    //System.out.println("");
				//System.out.println("WIRELESS_CONNECT");
				
				bWirelessEnabled = true;

				//System.out.println("bWirelessConnected: "+bWirelessConnected);
				SingletonClass sc = SingletonClass.getInstance();
				
				//System.out.println("sc.s_WirelessConnected: "+sc.s_WirelessConnected);
				//if ( sc.s_WirelessConnected )
				if ( true )
				{
				    // There was a previous connection, disconnect..
				    WirelessDisconnect();
				    
				    try
				    {
				         //Thread.sleep(2500);  
				         Thread.sleep(1000);
				    }
				    catch (InterruptedException ie)
				    {
				    }
				}

				
                bInitWirelessFinished = false;
                initWirelessBgThread = new InitWirelessBgThread();
                initWirelessBgThread.start();

                while ( true )
                {
                    try
                    {
                        Thread.sleep(333);
                    }
                    catch (InterruptedException ie)
                    {
                    }
                
                    if ( bInitWirelessFinished )
                        break;
                }
				
				// Check success..
				
				//System.out.println("iWirelessErrorCode: "+iWirelessErrorCode);
				//System.out.println("sc.s_WirelessConnected: "+sc.s_WirelessConnected);
				
				if ( (iWirelessErrorCode == 0) || sc.s_WirelessConnected )
				{
					if ( sc.s_WirelessConnected )
					{
					    //System.out.println("sc.s_WirelessConnected true so don't need Dialog");
						;
					}
					else
					{
					    // When the selection is changed, setValue is invoked, which generates a PropertyChangeEvent.
					    
						// Success, put up dialog for Connect..
						JOptionPane.showMessageDialog(
							null,				// parentComponent 
							"Hit OK when USB is disconnected.",	// message
							"Wireless Connect",			// title
							JOptionPane.INFORMATION_MESSAGE);	// messageType
					}
					
					// We should use a PropertyChangeListener,
					// but for now we'll let it wait..
					
					//completeLatch = new CountDownLatch(1);
					bConnectWirelessFinished = false;
					connectWirelessBgThread = new ConnectWirelessBgThread();
					connectWirelessBgThread.start();
					
					while ( true )
					{
					    try
					    {
					        Thread.sleep(333);
					    }
					    catch (InterruptedException ie)
					    {
					    }
					    
					    if ( bConnectWirelessFinished )
					        break;
					}
					
/*
					try
					{
						completeLatch.await();
					}
					catch (InterruptedException ie)
					{
					}
/**/					
				}

				int iMessageType;
				if ( iWirelessErrorCode != 0 )
				{
					iMessageType = JOptionPane.ERROR_MESSAGE;

					JOptionPane.showMessageDialog(
						null,				// parentComponent 
						sWirelessMessage,	// message
						"Wireless",			// title
						iMessageType);	// messageType
				}
/**/

				//System.out.println("Exiting WIRELESS_CONNECT");
						
			}
			else if ( WIRELESS_DISCONNECT.equals(sActionCommand) )
			{
				//System.out.println("WIRELESS_DISCONNECT");
				
				WirelessDisconnect();
				
			}
			else if ( PULL_FILE.equals(sActionCommand) )
			{
			    //System.out.println("PULL_FILE");
			    SingletonClass sc = SingletonClass.getInstance();
			    //System.out.println("sc.bConnected: "+sc.bConnected);
			    if ( sc.bConnected == false )
			        return;
			    //else
			        //System.out.println("OK");
			    
			    sSelectedMenu = "Pull file";
			    
                // Open Dialog..
                FileBrowserDialog("Files to pull");
					
			}
			else if ( DELETE_FILE.equals(sActionCommand) )
			{
			    //System.out.println("DELETE_FILE");
			    if ( (DevicesAr != null) && (DevicesAr.size() > 0) )
			        ;
			    else
			        return;
			    
			    SingletonClass sc = SingletonClass.getInstance();
			    if ( sc.bConnected == false )
			        return;
			    
			    sSelectedMenu = "Delete file";
			    
                // Open Dialog..
                FileBrowserDialog("Files to delete");
					
			}
			else if ( SELECT_FILE_GO.equals(sActionCommand) )
			{
			    //System.out.println("SELECT_FILE_GO");
			    
			    //System.out.println("sCurrentPath: '"+sCurrentPath+"'");
			    JButton pathButton;
			    String sSymLinkPath = "";
			    iLoc = 0;
			    //int iSelIndex = 0;
/*
                if ( selectionList == null )
                    System.out.println("selectionList null");
                else
                    System.out.println("selectionList.size(): "+selectionList.size());
/**/
			    
                if ( (selectionList != null) && (selectionList.size() > 0) )
                {
                    sListSelection = selectionList.get(0);
                }
/*                
                if ( sListSelection == null )
                    System.out.println("(GO)sListSelection null");
                else
                    System.out.println("(GO)sListSelection: '"+sListSelection+"'");
/**/                        


                //System.out.println("iFileBrowserSelectedIndex: "+iFileBrowserSelectedIndex);

                // Note:
                // If the name contains any spaces we need to surround
                // the whole path with single quotes or it won't process it right..
			    if ( (sListSelection != null) && (sListSelection.length() > 0) )
			    {
/*			        
			        if ( fileToksAr == null )
			            System.out.println("fileToksAr null");
			        else
			            System.out.println("fileToksAr.size(): "+fileToksAr.size());
/**/			        
			        
		            if ( (fileToksAr != null) && (fileToksAr.size() > 0) )
		            {
		                sT = (String)fileToksAr.get(iFileBrowserSelectedIndex);
		                //System.out.println("sT: '"+sT+"'");
		                iLoc = sT.indexOf("~");
		                if ( (iLoc != -1) && ((iLoc + 1) < sT.length()) )
		                {
		                    sSymLinkPath = sT.substring(iLoc + 1);
		                    if ( sSymLinkPath.startsWith("?") )
		                        sSymLinkPath = "";
		                }
		            }
			        
			        
			        //System.out.println("sListSelection: '"+sListSelection+"'");
			        if ( sListSelection.startsWith("[") )
			        {
			            // Strip off brackets..
			            sT = sListSelection.substring(1, sListSelection.length() - 1);
			            //System.out.println("sT: '"+sT+"'");
			            
			            sB = new StringBuffer();
			            sB.append(sCurrentPath);
			            
                        if ( (sSymLinkPath != null) && (sSymLinkPath.length() > 0) &&
                            (! sSymLinkPath.equals("null")) )
                        {
                            // Use symlink, has leading '/' so don't add it..
                            sB.append(sSymLinkPath);
                        }
                        else
                        {
                            // Use regular selection..
                            sB.append("/");
                            sB.append(sT);
                        }
			            
			            sCurrentPath = sB.toString();
			            
			            // Detect 'emulated' so we can add '/0'..
			            if ( sCurrentPath.endsWith("emulated") )
			            {
			                sB.append("/0");
			                sCurrentPath = sB.toString();
			            }
			            
			            //System.out.println("(New)sCurrentPath: '"+sCurrentPath+"'");

			            FinishPath();
			        }
			    }
			    
			    //System.out.println("Exiting SELECT_FILE_GO");
			}
			else if ( SELECT_FILE_SUBMIT.equals(sActionCommand) )
			{
			    //System.out.println("SELECT_FILE_SUBMIT");
			    
			    if ( fileBrowserFrame != null )
			    {
                    fileBrowserFrame.setVisible(false);
                    fileBrowserFrame.dispose();
                }
                
/*                
                if ( selectionList == null )
                    System.out.println("selectionList null");
                else
                    System.out.println("selectionList.size(): "+selectionList.size());
/**/                    
                
                if ( (selectionList != null) && (selectionList.size() > 0) )
                {
                    for ( int iSelIndex = 0; iSelIndex < selectionList.size(); iSelIndex++ )
                    {
                        //System.out.println("--TOP--  iSelIndex: "+iSelIndex);
                        sListSelection = selectionList.get(iSelIndex);
                        //System.out.println("sListSelection: '"+sListSelection+"'");

                        sb = new StringBuffer();
                        
                        if ( iOS == LINUX_MAC )
                        {
                            sb.append("export PATH=${PATH}:");
                            sb.append(androidSdkPathS);
                            sb.append("/platform-tools");
                            sb.append(";adb ");
                        }
                        else
                        {
                            sb.append("SET PATH=");
                            sb.append(androidSdkPathS);
                            sb.append("/platform-tools");
                            sb.append(";%PATH%");
                            sb.append("&&adb ");
                        }
        
                        if ( (sDeviceName != null) && (sDeviceName.length() > 0) )
                        {
                            sb.append("-s ");
                            sb.append(sDeviceName);
                            sb.append(" ");
                        }

/*                        
                        if ( sSelectedMenu == null )
                            System.out.println("sSelectedMenu null");
                        else
                            System.out.println("sSelectedMenu: '"+sSelectedMenu+"'");
/**/                
                        
                        if ( (sSelectedMenu != null) && (sSelectedMenu.length() > 0) )
                        {
                            if ( sSelectedMenu.equals("Push file") )
                            {
                                System.out.println("Push file");
                                
                                //   adb push c:/users/administrator/downloads/ep042019_5day_005.zip /sdcard/ep042019_5day_005.zip
                                sb.append("push ");
                                
                                SingletonClass sc = SingletonClass.getInstance();
                                sSelectedFile = sc.sSelectedFile;

/*                                
                                if ( sSelectedFile == null )
                                    System.out.println("(sc)sSelectedFile null");
                                else
                                    System.out.println("(sc)sSelectedFile: '"+sSelectedFile+"'");
/**/                        
                                
/*                        
                                if ( sCurrentPath == null )
                                    System.out.println("sCurrentPath null");
                                else
                                    System.out.println("sCurrentPath: '"+sCurrentPath+"'");
/**/                        
/*                        
                                if ( sListSelection == null )
                                    System.out.println("sListSelection null");
                                else
                                    System.out.println("sListSelection: '"+sListSelection+"'");
/**/
                                if ( (sSelectedFile != null) && (sSelectedFile.length() > 0) )
                                {
                                    // Source..
                                    sB = new StringBuffer();
                                    sB.append('"');
                                    sSelectedFile = processPath(sSelectedFile);     // Flip slashes..
                                    sB.append(sSelectedFile);
                                    
                                    sB.append('"');
                                    
                                    sb.append(sB.toString());
                                    sb.append(" ");
        
                                    // Destination.. 
                                    sB = new StringBuffer();
                                    if ( (sCurrentPath != null) && (sCurrentPath.length() > 0) )
                                    {
                                        sB.append('"');
                                        sB.append(sCurrentPath);
                                        sB.append("/");
                                    }
        
                                    if ( (sListSelection != null) && (sListSelection.length() > 0) )
                                    {
                                        if ( sListSelection.startsWith("[") )
                                            sListSelection = sListSelection.substring(1, sListSelection.length() - 1);
                
                                        //System.out.println("(Final)sListSelection: '"+sListSelection+"'");
                                            
                                        sB.append(sListSelection);
                                        sB.append('"');
                                    }
                                    
                                    //System.out.println("sB: '"+sB.toString()+"'");
                                    
                                    sb.append(sB.toString());
                                }
                            }
                            else if ( sSelectedMenu.equals("Pull file") )
                            {
                                //System.out.println("Pull file");
                                // adb pull /sdcard/ep042019_5day_005.zip "C:/Users/Joe Siebenmann/Downloads/ep042019_5day_005.zip"
                                sb.append("pull ");
                                
                                // Source dir..
/*                                
                                if ( sListSelection == null )
                                    System.out.println("sListSelection null");
                                else
                                    System.out.println("sListSelection: '"+sListSelection+"'");
/**/ 
/*        
                                if ( sCurrentPath == null )
                                    System.out.println("sCurrentPath null");
                                else
                                    System.out.println("sCurrentPath: '"+sCurrentPath+"'");
/**/
                                if ( (sCurrentPath != null) && (sCurrentPath.length() > 0)
                                    && (sListSelection != null) && (sListSelection.length() > 0) )
                                {
        
                                    sB = new StringBuffer();
                                    sB.append('"');
                                    sB.append(sCurrentPath);    // Like: '/dev/block'
                                    sB.append("/");
                                    
                                    //System.out.println("(Final)sListSelection: '"+sListSelection+"'");
        
                                    // Like:  'mmcblk0p25'
                                    sB.append(sListSelection);
                                    sB.append('"');
                                    //System.out.println("sB: '"+sB.toString()+"'");
                                    
                                    sb.append(sB.toString());
                                }
/*                        
                                if ( sListSelection == null )
                                    System.out.println("sListSelection null");
                                else
                                    System.out.println("sListSelection: '"+sListSelection+"'");
/**/ 
        
                                // Destination path..
                                if ( (sDownloadDir != null) && (sDownloadDir.length() > 0) )
                                {
                                    sb.append(" ");
                                    sb.append('"');
                                    sb.append(sDownloadDir);
                                    sb.append('"');
                                    sb.append(" ");
                                }
                            }
                            else if ( sSelectedMenu.equals("Delete file") )
                            {
                                sb.append("shell rm ");
                                
                                if ( (sCurrentPath != null) && (sCurrentPath.length() > 0)
                                    && (sListSelection != null) && (sListSelection.length() > 0) )
                                {
        
                                    sB = new StringBuffer();
                                    sB.append('"');
                                    sB.append(sCurrentPath);    // Like: '/dev/block'
                                    sB.append("/");
                                    
                                    //System.out.println("(Final)sListSelection: '"+sListSelection+"'");
        
                                    // Like:  'mmcblk0p25'
                                    sB.append(sListSelection);
                                    //System.out.println("sB: '"+sB.toString()+"'");
                                    sB.append('"');
                                    
                                    sb.append(sB.toString());
                                }
                            }
                        }
                        
                        if ( iOS == WINDOWS )
                            sb.append("\n");
                        
                        //System.out.println("(Push/Pull command)sb: '"+sb.toString()+"'");
                        
                        // Set up for IOBgThread output..
                        commandS = sb.toString();
                        
                        SingletonClass sc = SingletonClass.getInstance();
                        sc.s_sCommand = commandS;
                
                        bIOBgThreadFinished = false;                
                        iOBgThread = new IOBgThread();
                        iOBgThread.start();
        
                        while ( true )
                        {
                            try
                            {
                                //Thread.sleep(333);
                                Thread.sleep(500);
                            }
                            catch (InterruptedException ie)
                            {
                            }
                            
                            if ( bIOBgThreadFinished )
                                break;
                        }
                        
                        if ( sSelectedMenu.equals("Push file") )
                            break;
                        
                    }   // End for..
                }
			}
			else if ( PUSH_FILE.equals(sActionCommand) )
			{
			    //System.out.println("PUSH_FILE");
			    if ( (DevicesAr != null) && (DevicesAr.size() > 0) )
			        ;
			    else
			        return;
			    
			    SingletonClass sc = SingletonClass.getInstance();
			    if ( sc.bConnected == false )
			        return;
			    
			    sSelectedMenu = "Push file";
			    
                if ( bLogcatOn )
                {
                    // Set to kill IOBgThread..
                    bBreakOut = true;
                    
                    // Wait for IOBgThread to end..
                    while ( true )
                    {
                        //Thread.yield();
                        try
                        {
                            Thread.sleep(333);
                        }
                        catch (InterruptedException ie)
                        {
                        }
                        
                        if ( ! bLogcatOn )
                            break;
                    }
                }
                
			    RefreshProperties();

			    JFileChooser fChooser;
			    String sSelectedFile = "";

                fChooser = new JFileChooser();
                boolean bDoUpdate;
                
                fChooser.setCurrentDirectory(FileSystemView.getFileSystemView().getHomeDirectory());

				fChooser.setDialogTitle("File to Push");
				fChooser.setAcceptAllFileFilterUsed(false);
				fChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);    // What we can select..
				if ( fChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION )
				{
					// Selected..
					sSelectedFile = fChooser.getSelectedFile().getPath();
					
					sc.sSelectedFile = sSelectedFile;
/*					
					if ( sSelectedFile == null )
					    System.out.println("sSelectedFile null");
					else
					    System.out.println("sSelectedFile: '"+sSelectedFile+"'");
/**/

					sCurrentPath = "";
					
					//System.out.println("sCurrentPath: '"+sCurrentPath+"'");
					//System.out.println("sSelectedMenu: '"+sSelectedMenu+"'");
					
					if ( (sSelectedMenu != null) && (sSelectedMenu.equals("Push file")) )
					    ;
                    else
                    {
                        bUpdateFileBrowserFinished = false;
                        updateFileBrowserBgThread = new UpdateFileBrowserBgThread();
                        updateFileBrowserBgThread.start();
                        
                        // Wait for Thread to finish..
                        while ( true )
                        {
                            try
                            {
                                Thread.sleep(333);
                            }
                            catch (InterruptedException ie)
                            {
                            }
            
                            if ( bUpdateFileBrowserFinished )
                                break;
                        }
                    }
/*                    
                    if ( fileToksAr == null )
                        System.out.println("fileToksAr null");
                    else
                    {
                        System.out.println("fileToksAr.size(): "+fileToksAr.size());
                        for ( int iX = 0; iX < fileToksAr.size(); iX++ )
                            System.out.println("fileToksAr["+iX+"]: '"+(String)fileToksAr.get(iX)+"'");
                        
                    }
/**/					
					
					// Open Dialog..
					FileBrowserDialog("Destination directory");
					
				}
			}
			else if ( ADB_COMMAND.equals(sActionCommand) )
			{
			    SingletonClass sc = SingletonClass.getInstance();
			    if ( sc.bConnected == false )
			        return;
			    
			    AdbCommandDialog();
			}
			else if ( SELECT_FILE_CANCEL.equals(sActionCommand) )
			{
			    //System.out.println("SELECT_FILE_CANCEL");
			    sCurrentPath = "";    // Reset..
			    
			    if ( fileBrowserFrame != null )
			    {
                    fileBrowserFrame.setVisible(false);
                    fileBrowserFrame.dispose();
                }
			}
			else if (PULL_SCREENSHOT_CANCEL.equals(sActionCommand))
			{
			    if ( screenshotFrame != null )
			    {
                    screenshotFrame.setVisible(false);
                    screenshotFrame.dispose();
                }
			}
			else if (PULL_CAMERA_IMAGE_CANCEL.equals(sActionCommand))
			{
			    if ( cameraFrame != null )
			    {
                    cameraFrame.setVisible(false);
                    cameraFrame.dispose();
                }
			}
			else if ( ADB_COMMAND_CANCEL.equals(sActionCommand) )
			{
			    if ( adbCommandFrame != null )
			    {
                    adbCommandFrame.setVisible(false);
                    adbCommandFrame.dispose();
                }
			}
			else if (PACKAGE_CANCEL.equals(sActionCommand))
			{
			    if ( packageFrame != null )
			    {
                    packageFrame.setVisible(false);
                    packageFrame.dispose();
                }
			}
			else if (SELECT_DEVICE_CANCEL.equals(sActionCommand))
			{
			    if ( selectDeviceFrame != null )
			    {
                    selectDeviceFrame.setVisible(false);
                    selectDeviceFrame.dispose();
                }
			}
		}
	};    //}}}

	//{{{	WindowListener	windowClosed()
	static WindowListener windowListener = new WindowAdapter()
	{
		public void windowClosed(WindowEvent e)
		{
			// Invoked when a window has been closed as the result of calling dispose on the window.
			sCurrentPath = "";    // Reset..
			Window window = e.getWindow();
			//System.out.println("window.toString(): "+window.toString());
			if ( window.toString().contains("title=,") )    // Main Window will have this..
			    System.exit(0);
		}

		public void windowClosing(WindowEvent e)
		{
			// Invoked when the user attempts to close the window from the window's system menu.
			Window window = e.getWindow();
			window.dispose();
		}
	}; //}}}

	//{{{   MouseListener  jListMouseListener
	MouseListener jListMouseListener = new MouseAdapter()
	{
		public void mouseClicked(MouseEvent e)
		{
		    //System.out.println("\nMouseListener mouseClicked()");
		    String sT = "";
		    String sPath = "";
		    String sSymLinkPath = "";
		    StringBuffer sB;
		    int iLoc = 0;
		    
		    JList jList = (JList)e.getSource();
		    int iClickCount = e.getClickCount();
		    //System.out.println("iClickCount: "+iClickCount);
		    if ( iClickCount == 2 )
		    {
		        // Get tokens from selection..
		        int iIndex = jList.locationToIndex(e.getPoint());
		        //System.out.println("iIndex: "+iIndex);
		        if ( iIndex >= 0 )
		        {
/*		  
		            if ( fileToksAr == null )
		                System.out.println("fileToksAr null");
		            else
		                System.out.println("fileToksAr.size(): "+fileToksAr.size());
/**/

		            if ( (fileToksAr != null) && (fileToksAr.size() > 0) )
		            {
		                sT = (String)fileToksAr.get(iIndex);
		                //System.out.println("sT: '"+sT+"'");
		                iLoc = sT.indexOf("~");
		                if ( (iLoc != -1) && ((iLoc + 1) < sT.length()) )
		                {
		                    sSymLinkPath = sT.substring(iLoc + 1);
		                    if ( sSymLinkPath.startsWith("?") )
		                        sSymLinkPath = "";
		                }
		            }
		            
		            //System.out.println("sCurrentPath: '"+sCurrentPath+"'");
		            
/*                    
                    if ( sT == null )
                        System.out.println("sT null");
                    else
                        System.out.println("sT: '"+sT+"'");
/**/                    
                    
                    // From 'Go'..		            
			        if ( sT.startsWith("[") )
			        {
			            // Strip off brackets..
			            // '[sdcard]~/storage/self/primary'
			            //    
			            if ( (iLoc - 1) < sT.length() )
			                sT = sT.substring(1, iLoc - 1);
			            //System.out.println("sT: '"+sT+"'");
			            
			            sB = new StringBuffer();
			            sB.append(sCurrentPath);
			            
                        if ( (sSymLinkPath != null) && (sSymLinkPath.length() > 0) &&
                            (! sSymLinkPath.equals("null")) )
                        {
                            // Use symlink, has leading '/' so don't add it..
                            sB.append(sSymLinkPath);
                        }
                        else
                        {
                            // Use regular selection..
                            sB.append("/");
                            sB.append(sT);
                        }
			            
			            sCurrentPath = sB.toString();
			            
			            // Detect 'emulated' so we can add '/0'..
			            if ( sCurrentPath.endsWith("emulated") )
			            {
			                sB.append("/0");
			                sCurrentPath = sB.toString();
			            }

			            FinishPath();
			        }
		        }
		    }
		}
	};    //}}}
	
	//{{{   ListSelectionListener selectionListener
	ListSelectionListener selectionListener = new ListSelectionListener()
	{
		public void valueChanged(ListSelectionEvent e)
		{
			//System.out.println("\nListSelectionListener valueChanged()");
			//System.out.println("e.toString(): "+e.toString());
			int iFirstIndex = e.getFirstIndex();
			int[] iSelAr;
			long lTime;
			//long lTimerEnd = 0;
			boolean bDoGo = false;
			//boolean bSelectionTimerStarted = false;
			boolean bProcess = false;
			StringBuffer sB;
			String sT = "";
			//List<String> selList = new ArrayList<String>();
			selectionList = new ArrayList<String>();
			//System.out.println("iFirstIndex: "+iFirstIndex);
			
			if ( iListId == SCREENSHOT_LIST )
			{
			    if ( screenshotFilesJList != null )
			    {
			        //sListSelection = (String)screenshotFilesJList.getSelectedValue();
			        selectionList = screenshotFilesJList.getSelectedValuesList();
			    }
			}
			else if ( iListId == CAMERA_IMAGE_LIST )
			{
			    if ( cameraImagesJList != null )
			    {
			        //sListSelection = (String)cameraImagesJList.getSelectedValue();
			        selectionList = cameraImagesJList.getSelectedValuesList();
			    }
			}
			else if ( iListId == PACKAGE_LIST )
			{
			    if ( uninstallJList != null )
			        sListSelection = (String)uninstallJList.getSelectedValue();
			}
			else if ( iListId == FILE_BROWSER_LIST )
			{
			    //System.out.println("FILE_BROWSER_LIST");
			    
			    // Note:
			    // It looks like it goes in again here so rapidly
			    // that the index keeps getting wiped out..
			    long lCurrTime = System.currentTimeMillis();
			    //System.out.println("lCurrTime: "+lCurrTime);
			    bProcess = false;
			    
			    //System.out.println("bSelectionTimerStarted: "+bSelectionTimerStarted);
			    if ( bSelectionTimerStarted )
			    {
			        //System.out.println("lTimerEnd: "+lTimerEnd);
			        if ( lCurrTime > lTimerEnd )
			        {
			            // Time expired, okay to process..
			            bSelectionTimerStarted = false;    // Reset..
			            bProcess = true;
			        }
			        else
			        {
			            // Within time period, ignore..
			            ;
			        }
			    }
			    else
			    {
			        // Timer off, start it..
			        lTimerEnd = lCurrTime + 1500;
			        //System.out.println("lTimerEnd: "+lTimerEnd);
			        bSelectionTimerStarted = true;
			        bProcess = true;
			    }

/*			    
			    if ( fileBrowserJList == null )
			        System.out.println("fileBrowserJList null");
			    else
			        System.out.println("fileBrowserJList not null");
/**/			        
			    
                if ( fileBrowserJList != null )
                {
                    //sListSelection = (String)fileBrowserJList.getSelectedValue();

                    // Just prevent the index from being reset..
                    if ( bProcess )
                    {
                        iFileBrowserSelectedIndex = fileBrowserJList.getSelectedIndex();
                        //System.out.println("(getSelectedIndex())iFileBrowserSelectedIndex: "+iFileBrowserSelectedIndex);
                    }
                    
                    
                    selectionList = fileBrowserJList.getSelectedValuesList();

/*                    
                    if ( selectionList == null )
                        System.out.println("selectionList null");
                    else
                    {
                        System.out.println("selectionList.size(): "+selectionList.size());
                        for ( int iJ = 0; iJ < selectionList.size(); iJ++ )
                            System.out.println("selectionList ["+iJ+"]: '"+selectionList.get(iJ)+"'");
                        
                    }
/**/

                    
/*			        
                    iSelAr = fileBrowserJList.getSelectedIndices();
                    if ((iSelAr != null) && (iSelAr.length > 0))
                    {
                        for ( int iJ = 0; iJ < iSelAr.length; iJ++ )
                            System.out.println("iSelAr["+iJ+"]: "+iSelAr[iJ]);
                        
                    }
/**/			        
                }
			}
		}
	}; //}}}

	//{{{	main()	
	public static void main(String[] args)
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			public void run()
			{
			    //System.out.println("\nmain run()");
				aDB_Util = new ADB_Util();

				mainJFrame = new JFrame();
				mainJFrame.setContentPane(mainPanel);
				mainJFrame.revalidate();
				mainJFrame.pack();
				mainJFrame.repaint();
				mainJFrame.addWindowListener(windowListener);
				mainJFrame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
				mainJFrame.setVisible(true);
			}
		});
	} //}}}

}



