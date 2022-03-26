/*
 * :folding=explicit:collapseFolds=1:
 */

/**
 *	  ADB_Util is a utility for Android ADB
 *
 *	  Copyright (c) 2022 Joseph Siebenmann
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
    static volatile String sShowPackageNameInStatusBar;
    
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
    
    static volatile int iOS;
    static volatile int iListId;
    static volatile int iWirelessErrorCode;
    static volatile int iSelectMode;
    static volatile int iButtonCount;
    
    static volatile long lPrevTime;
    
	private StyledDocument doc;
	private Style normalStyle;
	private Style warningStyle;
	private Style errorStyle;
    
    static ArrayList FilesAr;
    static ArrayList DevicesAr = null;
    static ArrayList ConnectDevicesAr;
    static ArrayList fileToksAr;
    
    static List<String> selectionList = new ArrayList<String>();
    
    static CountDownLatch commandRequestLatch;
    static CountDownLatch completeLatch;
    
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
		
        SingletonClass sc = SingletonClass.getInstance();
        sc.bConnected = false;
		
		
		/**
         * Get number of running Emulators or devices..
         */

		completeLatch = new CountDownLatch(1);
		
		getDevicesBgThread = new GetDevicesBgThread();
		getDevicesBgThread.start();

		try
		{
			completeLatch.await();
		}
		catch (InterruptedException ie)
		{
		}

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
			
			sUsePidLogcat = processPath(prop.getProperty("use_pid_logcat"));
			sShowPackageNameInStatusBar = processPath(prop.getProperty("show_package_name_in_status_bar"));
			
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
		JMenuItem wirelessSubMenuItem = new JMenuItem("Re/Connect");
		wirelessSubMenuItem.setActionCommand("wireless_connect");
		wirelessSubMenuItem.addActionListener(actListener);
		wirelessSubMenu.add(wirelessSubMenuItem);
		homeMenu.add(wirelessSubMenu);
		
		wirelessSubMenuItem = new JMenuItem("Disconnect");
		wirelessSubMenuItem.setActionCommand("wireless_disconnect");
		wirelessSubMenuItem.addActionListener(actListener);
		wirelessSubMenu.add(wirelessSubMenuItem);
		
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

		Properties prop = new Properties();

		try
		{
			prop.load(new FileInputStream("config.properties"));
			sShowPackageNameInStatusBar = processPath(prop.getProperty("show_package_name_in_status_bar"));		
		}
		catch (IOException ioe)
		{
			System.out.println("RefreshProperties() Exception");
			ioe.printStackTrace();
		}
		
/*		
		if ( sShowPackageNameInStatusBar == null )
		    System.out.println("sShowPackageNameInStatusBar null");
		else
		    System.out.println("sShowPackageNameInStatusBar: '"+sShowPackageNameInStatusBar+"'");
/**/		
		
        if ( (sShowPackageNameInStatusBar != null) && (sShowPackageNameInStatusBar.equals("true")) )
        {
            
            packageLabel = new JLabel(" ");
            packageLabel.setBorder(loweredBevel);
    
            gbc = new GridBagConstraints();
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.anchor = GridBagConstraints.WEST;
            gbc.gridx = iGridX;
            gbc.weightx = 1.0;
            gbc.insets = new Insets(2, 2, 2, 2); // top left bottom right

            statusBar.add(packageLabel, gbc);
            
            iGridX++;
        }
		
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
			//System.out.println("\nIOBgThread run()");
			Process proc = null;			
			OutputStream os = null;
			
			InputStream error_is = null;
			InputStream out_is = null;
			BufferedInputStream error_bis = null;
			BufferedInputStream out_bis = null;
			
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			byte[] writeBuf;
			byte[] bZeroD = {(byte)0x0d};
			String sZeroD = new String(bZeroD);
			byte[] bZeroA = {(byte)0x0a};
			String sZeroA = new String(bZeroA);

			int iBytesRead = 0;
			int iCount;
			int iExitVal;
			int iTotalBytes;
			int iG;
			int iLoc;
			int iLoc2 = 0;
			int iLoc3 = 0;
			int iLoc4 = 0;
			int iLoc5 = 0;
			int iLoc6 = 0;
			int iLen;
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
			int iBlkCount = 0;
			int iLastZeroA = 0;
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
			boolean bFinishEnd = false;
			boolean bFirst2;
			
			String outLineS;
			String inLineS;
			String lineS = "";
			String outS;
			String sT = "";
			String sT2 = "";
			String sPrevEnd = "";
			StringBuffer sB = null;
			StringBuffer endSb = null;
			
			
			ArrayList locAr = new ArrayList(384);
			
            SingletonClass sc = SingletonClass.getInstance();
            sPackageName = sc.sPackageName;


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
/*			
			if ( commandS == null )
				System.out.println("commandS null");
			else
				System.out.println("commandS: '"+commandS+"'");
/**/

			try
			{
				Runtime rt = Runtime.getRuntime();
				
				if ( iOS == LINUX_MAC )
				{
					proc = rt.exec(new String[] {"/bin/bash", "-c", commandS});
				}
				else
				{
					proc = rt.exec("cmd.exe");
					
					writeBuf = commandS.getBytes();
					
					// Command..				
					os = proc.getOutputStream();
					os.write(writeBuf);
					os.flush();
				}
					
				error_is = proc.getErrorStream();
				out_is = proc.getInputStream();
				
				error_bis = new BufferedInputStream(error_is);
				out_bis = new BufferedInputStream(out_is);	
				
				if ( iOS == WINDOWS )
				{
					StringBuffer tSb = new StringBuffer();

					// Get current directory prompt..
					StringBuffer currDirSb = new StringBuffer();
					String currDirS = System.getProperty("user.dir");    // Like:  'C:\Dev\ADB_Util'
					//System.out.println("currDirS: '"+currDirS+"'");

					iLoc = currDirS.lastIndexOf('\\');
					if ( iLoc != -1 )
					{
					    // Like:  'ADB_Util>'
					    if ( (iLoc + 1) < currDirS.length() )
					    {
                            currDirSb.append(currDirS.substring(iLoc + 1));
                            currDirSb.append(">");
                        }
					}
					
					//System.out.println("currDirSb: '"+currDirSb.toString()+"'");
					
					// Set end to prompt..
					outputEndS = currDirSb.toString();
					
/*
					if ( actionCommandS == null )
						System.out.println("actionCommandS null");
					else
						System.out.println("actionCommandS: '"+actionCommandS+"'");
/**/

				}
				
				//System.out.println("\noutputEndS: '"+outputEndS+"'");

				iTotalBytes = 0;
				int iWordLength = 0;
				int iWordStart = 0;
				int iSIdx;
				int iBlockIdx;
				int iSplitBlockCount;
				int iChrLoc = 0;
				int iBreakLength = DISPLAY_WIDTH / 2;
				boolean bInWord;
				
				long lCTM1;
				long lCTM2;
				long lDif = 0;
/*				
                if ( sPid == null )
                    System.out.println("sPid null");
                else
                    System.out.println("sPid: '"+sPid+"'");
/**/                        

				sB = new StringBuffer();
				endSb = new StringBuffer();
				
				while ( ! isInterrupted() )
				{
					//System.out.println("--TOP--");
					// Kill for Logcat..					
					if ( bBreakOut )
						break;
					
					if ( bIOBgThreadBreak )
					    break;
					
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
						
						baos.reset();
						
						iTotalBytes += iBytesRead;
						lCTM2 = System.currentTimeMillis();
						lDif = lCTM2 - lCTM1;
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
						System.out.println("\n");
						System.out.println("\n");
					}
/**/

/*
                    if ( lineSb == null )
                        System.out.println("lineSb null");
                    else
                        System.out.println("lineSb.length(): "+lineSb.length());
/**/                            
 
                    if ( (sUsePidLogcat != null) && (sUsePidLogcat.equals("true")) )
                    {
                        // This seems to make the PID logcat a little more responsive..
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
                            if ( lDif > 0 )
                                Thread.sleep(40);
                                //Thread.sleep(45);
                            else
                                //Thread.sleep(30);
                                Thread.sleep(20);
                        }
                    }

					if ( (lineSb != null) && (iBytesRead > 0) )
					{
						// Start of new block..
						//System.out.println("=== NEW BLOCK ===");
						
						//System.out.println("bLogcatOn: "+bLogcatOn);
                        if ( (bLogcatOn) && (sUsePidLogcat != null) && (sUsePidLogcat.length() > 0) )
                        {
                            if ( sUsePidLogcat.equals("true") )
                            {
                                // Use PID Logcat..
                                if ( (sPid != null) && (sPid.length() > 0) )
                                    ;
                                else
                                {
                                    sPid = "";
                                    StringBuffer sb = new StringBuffer();
                                    
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
                                            Thread.sleep(20);
                                        }
                                        catch (InterruptedException ie)
                                        {
                                        }
                        
                                        if ( bCommandFinished )
                                            break;
                                    }

                                    if ( (sPackageName != null) && (! sPackageName.equals("null"))
                                        && (sPackageName.length() > 0) )
                                    {
                                        //System.out.println("sPackageName: '"+sPackageName+"'");
                                        iLoc3 = commandResultS.indexOf(sPackageName);
                                        if ( iLoc3 != -1 )
                                        {
                                            // Grab PID..
                                            for ( ; commandResultS.charAt(iLoc3) != (char)0x0a; iLoc3-- );
                            
                                            iLoc3++;
                                            for ( ; ! Character.isWhitespace(commandResultS.charAt(iLoc3)); iLoc3++ );
                                            for ( ; Character.isWhitespace(commandResultS.charAt(iLoc3)); iLoc3++ );
                                            iStart = iLoc3;
                                            for ( ; ! Character.isWhitespace(commandResultS.charAt(iLoc3)); iLoc3++ );
                                            sPid = commandResultS.substring(iStart, iLoc3);
                                            //System.out.println("sPid: '"+sPid+"'");
                                        }
                                    }
                                }
                                
                                sB = new StringBuffer();
                                
                                iLoc3 = 0;
                                bFirst2 = true;

                                if ( (sPid != null) && (sPid.length() > 0) )
                                {
                                    while ( true )
                                    {
                                        if ( iLoc3 < lineSb.length() )
                                            ;
                                        else
                                            break;
                                        
                                        iLoc2 = lineSb.indexOf(sZeroA, iLoc3);
                                        if ( iLoc2 != -1 )
                                        {
                                            iLastZeroA = iLoc2;
                                            
                                            if ( bFirst2 )
                                            {
                                                bFirst2 = false;
                                                endSb = new StringBuffer();
                                                
                                                endSb.append(sPrevEnd);
                                                
                                                if ( (iLoc2 + 1) > 0 )
                                                {
                                                    endSb.append(lineSb.substring(0, iLoc2 + 1));
                                                }
                                                
                                                //System.out.println("endSb: '"+endSb.toString()+"'");
                                                
                                                iLoc4 = endSb.indexOf(")");
                                                if ( iLoc4 != -1 )
                                                {
                                                    iLoc5 = endSb.indexOf("(");
                                                    if ( iLoc5 != -1 )
                                                    {
                                                        if ( (iLoc4 > 0) && (iLoc5 < iLoc4) )
                                                        {
                                                            sT = endSb.substring(iLoc5 + 1, iLoc4);
                                                            sT = sT.trim();
                                                            if ( sPid.equals(sT) )
                                                            {
                                                                //System.out.println("--Found PID--");
                                                                sB.append(endSb.toString());
                                                            }
                                                        }
                                                    }
                                                }
                                                
                                                iLoc3 = iLoc2 + 1;  // Next..
                                                continue;                                    
                                            }
                                            
                                            iLoc4 = lineSb.indexOf(")", iLoc2);
                                            if ( iLoc4 != -1 )
                                            {
                                                iLoc5 = lineSb.indexOf("(", iLoc2);
                                                if ( iLoc5 != -1 )
                                                {
                                                    // ' 1499): Scheduling ... ( ...
                                                    sT = "";
                                                    if ( (iLoc4 > 0) && (iLoc5 < iLoc4) )
                                                        sT = lineSb.substring(iLoc5 + 1, iLoc4);
                                                    
                                                    sT = sT.trim();
                                                    if ( sPid.equals(sT) )
                                                    {
                                                        iLoc6 = lineSb.indexOf(sZeroA, iLoc2 + 1);
                                                        if ( ((iLoc6 + 1) > (iLoc2 + 1)) && ((iLoc6 + 1) < lineSb.length()) )
                                                        {
                                                            sT2 = lineSb.substring(iLoc2 + 1, iLoc6 + 1);
                                                            //System.out.println("sT2: '"+sT2+"'");
                                                            sB.append(sT2);
                                                            
                                                        }
                                                    }
                                                }
                                                else
                                                    break;
                                            }
                                            else
                                                break;
                                        }
                                        else
                                            break;
                                        
                                        iLoc3 = iLoc2 + 1;  // Next..
                                        
                                    }   // End while..
                                }
                                
                               //System.out.println("Dropped out");
                               if ( ((iLastZeroA + 1) >= 0) && ((iLastZeroA + 1) < lineSb.length()) )
                                   sPrevEnd = lineSb.substring(iLastZeroA + 1);
                                //System.out.println("(End of block)sPrevEnd: '"+sPrevEnd+"'");
                                
                                bFinishEnd = true;
        
                                if ( sB.length() > 0 )
                                    ;
                                else
                                    sB.append("");
                                
                                lineSb = sB;
                            }
                        }

                        if ( lineSb.length() > 0 )
                        {
                        
                            bInWord = false;
                            iWordStart = 0;
                            
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
    
                            iIdx = 0;
                            bDoBreak = false;
                            bSplit = false;
                            iChunkLen = lineSb.length();
                            
                            for ( ; ; iIdx++ ) 
                            {
                                //System.out.println("--TOP--");
                                if ( bDoBreak )
                                {
                                    //System.out.println("--Breaking--");
                                    break;
                                }
                                
                                if ( iIdx >= iChunkLen )
                                {
                                    // Hit end of block..
                                    cChr = lineSb.charAt(lineSb.length() - 1);
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
                        }
					}
/**/					
                    //System.out.println("\nPast split block");
                    
					// Note:
					//
					// Sometimes it can catch the end prompt early,
					// so we need an end test..
					//System.out.println("iBytesRead: "+iBytesRead);
					if ( iBytesRead > 0 )
					{
						if ( ! bLogcatOn )
						{
							if ( iOS == WINDOWS )
							{
								if ( lineSb.charAt(lineSb.length() - 1) == '>' )
								{
									lineS = lineSb.substring(0, lineSb.length());

                                    if ( lineS.endsWith(outputEndS) )
                                    {
                                        //System.out.println("End matched, breaking out..");
                                        bBreakOut = true;	// Signal to break out..
                                    }
								}
							}
						}

						outLineS = "";	// Final line to be inserted..
						bContinued = false;
						iType = NORMAL;
						iLineLen = lineSb.length();

						try
						{
							doc = textPane.getStyledDocument();

							//System.out.println("\nbLogcatOn: "+bLogcatOn);
							if ( bLogcatOn )
							{
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
										outLineS = lineSb.substring(iStart, iIndex);
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
											outLineS = lineSb.substring(iStart);
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
											outLineS = lineSb.substring(iStart, iIndex);
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

                                    //System.out.println("\niType: "+iType);									
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
					else
					{
						// No output..
						if ( iOS == LINUX_MAC )
						{
							// If still getting data
							// gets Exception:
							// 'Exception: java.lang.IllegalThreadStateException: process hasn't exited'
							// When I/O is finished, does exitValue()..
							try
							{
								iExitVal = proc.exitValue();
								//System.out.println("iExitVal: "+iExitVal);
								break;
							}
							catch (IllegalThreadStateException itse)
							{
								//System.out.println(itse.toString());
							}
						}
					}
					
					iBytesRead = 0;
					
				}	// End while..
				
				bIOBgThreadBreak = false;    // Reset..
				
				//System.out.println("\nIOBgThread dropped out");
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
					System.out.println(ie.toString());
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

			if ( bLogcatOn )
			{
                // Turn off status message..                    
                statusPath.setText("    ");
			}
			    
			// Reset..
			bBreakOut = false;
			bLogcatOn = false;
			bFinished = true;

			//System.out.println("\nExiting IOBgThread run()");
		}
	}	//}}}

	//{{{	UpdateFileBrowserBgThread
	class UpdateFileBrowserBgThread extends Thread
	{
		public void run()
		{
			//System.out.println("\nUpdateFileBrowserBgThread run()");
			
			StringTokenizer st;
            StringBuffer sb = new StringBuffer();
            StringBuffer sB;
            StringBuffer tokSb;
            StringBuffer sNameSb = null;
            String sT = "";
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
                    Thread.sleep(150);
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
                System.out.println("\n============================================");
                System.out.println("commandResultS: '"+commandResultS+"'");
                //System.out.println("commandResultS.length(): "+commandResultS.length());
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
                    if ( iLoc != -1 )
                    {
                        sCurrentPath = sCurrentPath.substring(0, iLoc);
                    }
                    
                    if ( fileBrowserJList != null )
                        fileBrowserJList.clearSelection();
                    
                    //System.out.println("(Final)sCurrentPath: '"+sCurrentPath+"'");
                    
                    // Signal failed..
                    fileToksAr = new ArrayList();
                }
                else
                {
                    //System.out.println("OK");
                
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
                        tokSa[iTokIndex] = sT;
                        //System.out.println("["+iTokIndex+"]: '"+tokSa[iTokIndex]+"'");
                    }
                    
                    // Skip any leading..
                    for ( iTokIndex = 0; iTokIndex < iCount; )
                    {
                        if ( iTokIndex < tokSa.length )
                        {
                            if ( ((tokSa[iTokIndex].contains("-")) && (tokSa[iTokIndex].length() == 10)) ||
                                    ((tokSa[iTokIndex].startsWith("d")) && (tokSa[iTokIndex].length() == 10)) ||
                                    ((tokSa[iTokIndex].startsWith("l")) && (tokSa[iTokIndex].length() == 10)) ||
                                    ((tokSa[iTokIndex].startsWith("c")) && (tokSa[iTokIndex].length() == 10)) )
                                break;
                        }
                        else
                            break;
                            
                        iTokIndex++; 
                    }
                    
                    
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
                    
BREAK_OUT:                        
                    for ( ; iTokIndex < iCount; )
                    {
                        //System.out.println("--TOP-- iTokIndex: "+iTokIndex);
                        sNameSb = new StringBuffer();
                        
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
                                if ( tokSa[iTokIndex].contains(":") )
                                    break;
                            }
                            
                            iTokIndex++;
                            sNameSb.append(tokSa[iTokIndex]);
                            sNameSb.append(" ");
                            //System.out.println("(1)sNameSb: '"+sNameSb.toString()+"'");
                            
                            //      [java] drwxrwx--- root     sdcard_r          2020-02-27 16:39 Digital Editions

                           
                            // Get name..
                            //System.out.println("bIsDir: "+bIsDir);
                            if ( bIsDir )
                            {
                                tokSb = new StringBuffer();
                                tokSb.append("[");
                                tokSb.append(tokSa[iTokIndex]);
                                tokSb.append("]");
                                //System.out.println("(Add): '"+tokSb.toString()+"'");
                                fileToksAr.add((String)tokSb.toString());
                            }
                            
                            // 12:49 vendor -> /system/vendor   
                            // 12:49 storage
                            // 16:39 Digital Editions
                            //System.out.println("tokSa.length: "+tokSa.length);

INNER_BREAK:                                
                            while ( true )
                            {
                                //System.out.println("==TOP== iTokIndex: "+iTokIndex);
                                if ( (iTokIndex + 1) < tokSa.length )
                                {
                                    iTokIndex++;
                                    
                                    if ( tokSa[iTokIndex].startsWith("->") )
                                    {
                                        iTokIndex += 2;
                                        break;
                                    }
                                        
                                    if ( ((tokSa[iTokIndex].contains("-")) && (tokSa[iTokIndex].length() == 10)) ||
                                        ((tokSa[iTokIndex].startsWith("d")) && (tokSa[iTokIndex].length() == 10)) ||
                                        ((tokSa[iTokIndex].startsWith("l")) && (tokSa[iTokIndex].length() == 10)) ||
                                        ((tokSa[iTokIndex].startsWith("c")) && (tokSa[iTokIndex].length() == 10)) )
                                    {
                                        // At 'drwxrwx---'..
                                        break;
                                    }
                                    else
                                    {
                                        //System.out.println("tokSa[iTokIndex]: '"+tokSa[iTokIndex]+"'");
                                        if ( tokSa[iTokIndex].equals("lstat") )
                                        {
                                            //System.out.println("\n---HIT lstat");
                                            bHitlstat = true;
                                            
                                            while ( true )
                                            {
                                                if ( (iTokIndex + 1) < tokSa.length )
                                                {
                                                    iTokIndex++;
                                                    
                                                    if ( ((tokSa[iTokIndex].contains("-")) && (tokSa[iTokIndex].length() == 10)) ||
                                                        ((tokSa[iTokIndex].startsWith("d")) && (tokSa[iTokIndex].length() == 10)) ||
                                                        ((tokSa[iTokIndex].startsWith("l")) && (tokSa[iTokIndex].length() == 10)) ||
                                                        ((tokSa[iTokIndex].startsWith("c")) && (tokSa[iTokIndex].length() == 10)) )
                                                    {
                                                        // At 'drwxrwx---'..
                                                        //break;
                                                        break INNER_BREAK;
                                                    }
                                                }
                                                else
                                                    break;
                                            }
                                       }
                                       else
                                       {
                                            sNameSb.append(tokSa[iTokIndex]);
                                            sNameSb.append(" ");
                                            //System.out.println("(2)sNameSb: '"+sNameSb.toString()+"'");
                                       }
                                    }
                                }
                                else
                                    break BREAK_OUT;
                            }   // End while()..

                            if ( bIsDir )
                                ;
                            else
                            {
                                sT = sNameSb.toString();
                                sT = sT.trim();
                                //System.out.println("(Add)sT: '"+sT+"'");
                                fileToksAr.add((String)sT);
                            }
                        }
                        else
                        {
                            break;
                        }
                        
                    }   // End for..
                    
                    if ( bIsDir )
                        ;
                    else
                    {
                        sT = sNameSb.toString();
                        sT = sT.trim();
                        //System.out.println("(Add)sT: '"+sT+"'");
                        fileToksAr.add((String)sT);
                    }
                }
            
                //System.out.println("Dropped out");
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

            bUpdateFileBrowserFinished = true;
            //System.out.println("Exiting UpdateFileBrowserBgThread");
		}
	}    //}}}
	
	//{{{	GetDevicesBgThread
	class GetDevicesBgThread extends Thread
	{
		public void run()
		{
			//System.out.println("\nGetDevicesBgThread run()");
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
				
				sb.append(";adb kill-server");
				sb.append(";adb start-server");
				sb.append(";adb devices");
			}
			else
			{
				sb.append("SET PATH=");
				sb.append(androidSdkPathS);
				sb.append("/platform-tools");
				sb.append(";%PATH%");
				
				sb.append("&&adb kill-server");
				sb.append("&&adb start-server");
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
					Thread.sleep(250);
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
			for ( int iJ = 0; iJ < DevicesAr.size(); iJ++ )
				System.out.println("["+iJ+"]: '"+(String)DevicesAr.get(iJ)+"'");
/**/

			//bDevicesFinished = true;
			completeLatch.countDown();
			
		}
	}	//}}}

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
			
			// Check devices..
			bOK = false;
			internalSb = new StringBuffer();
			
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

			bCommandFinished = false;
			sInternalCommand = internalSb.toString();
			commandBgThread = new CommandBgThread();
			commandBgThread.start();
	
			// Wait for Thread to finish..
			while ( true )
			{
				try
				{
					Thread.sleep(200);
				}
				catch (InterruptedException ie)
				{
				}

				if ( bCommandFinished )
					break;
			}

			// Mix:
			// YT910A2GPY      device
			// emulator-5554   device
/*
			if ( commandResultS == null )
				System.out.println("commandResultS null");
			else
				System.out.println("commandResultS: '"+commandResultS+"'");
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
							sDevName = commandResultS.substring(iStart, iEnd);
							sDevName = sDevName.trim();
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
						selectDeviceDialog();
						
						// Wait for selection..
						while ( true )
						{
							try
							{
								Thread.sleep(200);
							}
							catch (InterruptedException ie)
							{
							}
			
							if ( bSelectFinished )
								break;
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
				if ( (sDeviceIPAddress != null) && (sDeviceIPAddress.length() > 0) )
				{
					// Construct Wireless Id..
					wIdSb = new StringBuffer();
					sDeviceIPAddress = sDeviceIPAddress.trim();
					wIdSb.append(sDeviceIPAddress);
					wIdSb.append(":5555");
					sWirelessID = wIdSb.toString();
					bOK = true;
				}
				else
				{
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
					
					bCommandFinished = false;		
					sInternalCommand = internalSb.toString();
					commandBgThread = new CommandBgThread();
					commandBgThread.start();
			
					// Wait for Thread to finish..
					while ( true )
					{
						try
						{
							Thread.sleep(200);
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
	
				bCommandFinished = false;		
				sInternalCommand = internalSb.toString();
				commandBgThread = new CommandBgThread();
				commandBgThread.start();
		
				// Wait for Thread to finish..
				while ( true )
				{
					try
					{
						Thread.sleep(200);
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
					System.out.println("commandResultS: '"+commandResultS+"'");
/**/

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
			//System.out.println("ConnectWirelessBgThread run()");
			StringBuffer internalSb = new StringBuffer();
			StringBuffer wIdSb;
			StringBuffer mSb;
			
			int iLoc1;
			int iLoc2;
			boolean bOK;
			iWirelessErrorCode = 0;
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
		
				//System.out.println("internalSb: '"+internalSb.toString()+"'");
				
				bCommandFinished = false;		
				sInternalCommand = internalSb.toString();
				commandBgThread = new CommandBgThread();
				commandBgThread.start();
		
				// Wait for Thread to finish..
				while ( true )
				{
					try
					{
						Thread.sleep(500);
					}
					catch (InterruptedException ie)
					{
					}
	
					//Thread.yield();
		
					if ( bCommandFinished )
						break;
				}
				
				bOK = false;
/*				
				if ( commandResultS == null )
					System.out.println("commandResultS null");
				else
					System.out.println("commandResultS: '"+commandResultS+"'");
/**/

				if ( (commandResultS != null) && (commandResultS.length() > 0) )
				{
					iLoc1 = commandResultS.indexOf("connected to");
					if ( iLoc1 != -1 )
					{
						// Success..
						// Set to use 'sWirelessID' as the new device name for -s..
						sDeviceName = sWirelessID;
						//System.out.println("sDeviceName: '"+sDeviceName+"'");
						
/*						
						if ( (DevicesAr != null) && (DevicesAr.size() > 1) )
						{
							// Update status bar..
							deviceLabel.setText(sDeviceName);
						}
/**/						
						bWirelessConnected = true;	// Signal connected..
						bOK = true;
					}
					
					if ( bOK )
					{
						// Success..
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
				System.out.println("sDeviceName null");
			else
				System.out.println("sDeviceName: '"+sDeviceName+"'");
/**/

			if ( (sDeviceName != null) && (sDeviceName.length() > 0) )
				deviceLabel.setText(sDeviceName);
			else
				deviceLabel.setText(" ");
				
            //System.out.println("Exiting ConnectWirelessBgThread");				
			//bConnectWirelessFinished = true;
			completeLatch.countDown();
		}
	}	//}}}
			
	//{{{	DisconnectWirelessBgThread
	class DisconnectWirelessBgThread extends Thread
	{
		public void run()
		{
			//System.out.println("DisconnectWirelessBgThread run()");
			StringBuffer internalSb = new StringBuffer();

			if ( (sWirelessID != null) && (sWirelessID.length() > 0) )
			{
				if ( iOS == LINUX_MAC )
				{
					internalSb.append("export PATH=${PATH}:");
					internalSb.append(androidSdkPathS);
					internalSb.append("/platform-tools");

					internalSb.append(";adb disconnect ");
					internalSb.append(sWirelessID);
					internalSb.append(";adb usb");	// No device/Id options for 'usb'..
				}
				else
				{
					internalSb.append("SET PATH=");
					internalSb.append(androidSdkPathS);
					internalSb.append("/platform-tools");
					internalSb.append(";%PATH%");

					internalSb.append("&&adb disconnect ");
					internalSb.append(sWirelessID);
					internalSb.append("&&adb usb");	// No device/Id options for 'usb'..
					internalSb.append("\n");
				}
		
				bCommandFinished = false;		
				sInternalCommand = internalSb.toString();
				commandBgThread = new CommandBgThread();
				commandBgThread.start();
		
				// Wait for Thread to finish..
				while ( true )
				{
					try
					{
						Thread.sleep(100);
					}
					catch (InterruptedException ie)
					{
					}
		
					if ( bCommandFinished )
						break;
				}
			}

			bWirelessConnected = false;	// Reset..
			bDisconnectWirelessFinished = true;
		}
	}	//}}}

	//{{{   FinishPath()   
	void FinishPath()
	{
	    //System.out.println("\nFinishPath()");
	    // Update Path Buttons and JList..
	    
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
                Thread.sleep(250);
            }
            catch (InterruptedException ie)
            {
            }

            if ( bUpdateFileBrowserFinished )
                break;
        }
        
        // Check for failed..
        if ( (fileToksAr != null) && (fileToksAr.size() > 0) )
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
                DefaultListModel dlModel = new DefaultListModel();
                fileBrowserJList.setModel(dlModel);
                dlModel.clear();
                for( int i = 0; i < fileToksAr.size(); i++ )
                {
                    sT = (String)fileToksAr.get(i);
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
			//System.out.println("\nCommandBgThread run()");
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
			
			//System.out.println("Exiting CommandBgThread run()");

			if (commandRequestLatch != null)
				commandRequestLatch.countDown();
			
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
                Thread.sleep(250);
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
            listSa = new String[fileToksAr.size()];
            
            for ( int g = 0; g < fileToksAr.size(); g++ )
            {
                listSa[g] = (String)fileToksAr.get(g);
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
            
            if ( (pathSa != null) && (pathSa.length > 0) )
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
				        if ( g < tSa.length )
				            tSa[g] = (String)ConnectDevicesAr.get(g);
				    }
				}
				else
				{
				    if ( g < DevicesAr.size() )
				    {
				        if ( g < tSa.length )
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
                        if ( iLoc2 != -1 )
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
                            Thread.sleep(100);
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

                if ( (sUseDirectScreenshotDownload != null) && (sUseDirectScreenshotDownload.length() > 0) )
                {
                    if ( sUseDirectScreenshotDownload.equals("true") )
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
                }
                
                if ( bUseDirect == false )
                {
                    sb.append("shell screencap ");
                    
                    if ( (sScreenshotDir != null) && (sScreenshotDir.length() > 0) )
                        sb.append(sScreenshotDir);
                    
                    sb.append("/");
                    sb.append(sBNm.toString());
                }
                
                if ( iOS == WINDOWS )
                    sb.append("\n");
                
                //System.out.println("sb: '"+sb.toString()+"'");
                // Set up for IOBgThread output..
                commandS = sb.toString();
                
                iOBgThread = new IOBgThread();
                iOBgThread.start();
                
/*                
                try
                {
                    commandRequestLatch.await();
                }
                catch (InterruptedException ie)
                {
                }
/**/                
			}
			else if ( PULL_SCREENSHOT.equals(sActionCommand) )
			{
			    //System.out.println("PULL_SCREENSHOT");
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
			    
                sb.append(" shell ls ");
                if ( (sScreenshotDir != null) && (sScreenshotDir.length() > 0) )
                    sb.append(sScreenshotDir);

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
                        Thread.sleep(250);
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
                tokSa = new String[iCount];
                
                for ( iTokIndex = 0; st.hasMoreTokens(); iTokIndex++ )
                {
                    sT = st.nextToken();
                    sT = sT.trim();
                    if ( sT.equals("*") )
                        break;
                    
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
                            Thread.sleep(100);
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
                        
                        sb.append("/");
                        if ( (sListSelection != null) && (sListSelection.length() > 0) )
                            sb.append(sListSelection);
                        
                        sb.append(" ");
                        if ( (sDownloadDir != null) && (sDownloadDir.length() > 0) )
                        {
                            sb.append((char)0x22);
                            sb.append(sDownloadDir);
                            sb.append((char)0x22);
                        }
        
                        if ( iOS == WINDOWS )
                            sb.append("\n");
        
                        // Set up for IOBgThread output..
                        commandS = sb.toString();
                        
                        bIOBgThreadFinished = false;
                        iOBgThread = new IOBgThread();
                        iOBgThread.start();
                        
                        while ( true )
                        {
                            try
                            {
                                Thread.sleep(150);
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
                            Thread.sleep(100);
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
                
                iOBgThread = new IOBgThread();
                iOBgThread.start();
                
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
                            Thread.sleep(100);
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
                        Thread.sleep(250);
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
                //System.out.println("SELECT_PACKAGE");
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
                        Thread.sleep(20);
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
			    
                sb.append("shell ls ");
                if ( (sCameraDir != null) && (sCameraDir.length() > 0) )
                    sb.append(sCameraDir);

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
                        Thread.sleep(250);
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
                tokSa = new String[iCount];
                
                for ( iTokIndex = 0; st.hasMoreTokens(); iTokIndex++ )
                {
                    sT = st.nextToken();
                    sT = sT.trim();
                    if ( sT.equals("*") )
                        break;
                    
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
                            Thread.sleep(100);
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
                        
                        sb.append("/");
                        if ( (sListSelection != null) && (sListSelection.length() > 0) )
                            sb.append(sListSelection);
                        
                        sb.append(" ");
                        if ( (sDownloadDir != null) && (sDownloadDir.length() > 0) )
                        {
                            sb.append((char)0x22);
                            sb.append(sDownloadDir);
                            sb.append((char)0x22);
                        }
        
                        if ( iOS == WINDOWS )
                            sb.append("\n");
        
                        // Set up for IOBgThread output..
                        commandS = sb.toString();

                        bIOBgThreadFinished = false;                        
                        iOBgThread = new IOBgThread();
                        iOBgThread.start();
                        
                        while ( true )
                        {
                            try
                            {
                                Thread.sleep(150);
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
                            Thread.sleep(100);
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
                        Thread.sleep(250);
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
                        if ( iLoc != -1 )
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
			    //System.out.println("PACKAGE_SUBMIT");
			    
			    if ( (sListSelection != null) && (sListSelection.length() > 0) )
			    {
			        //System.out.println("sListSelection: '"+sListSelection+"'");
			        if ( (sSelectedMenu != null) && (sSelectedMenu.equals("Select Package")) )
			        {
			            // Select Package..
			            sListSelection = sListSelection.trim();
                        SingletonClass sc = SingletonClass.getInstance();
                        sc.sPackageName = sListSelection;
			            //System.out.println("sc.sPackageName: '"+sc.sPackageName+"'");

                        if ( (sShowPackageNameInStatusBar != null) && (sShowPackageNameInStatusBar.equals("true")) )
                        {
                            if ( packageLabel != null )
                                packageLabel.setText(sListSelection);
                        }
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
				completeLatch = new CountDownLatch(1);
				
				getDevicesBgThread = new GetDevicesBgThread();
				getDevicesBgThread.start();

				try
				{
					completeLatch.await();
				}
				catch (InterruptedException ie)
				{
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
                            Thread.sleep(100);
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
				
				iOBgThread = new IOBgThread();
				iOBgThread.start();
			}
			else if ( LOGCAT.equals(sActionCommand) )
			{
			    //System.out.println("LOGCAT");
			    SingletonClass sc = SingletonClass.getInstance();
			    if ( sc.bConnected == false )
			        return;

                //System.out.println("bLogcatOn: "+bLogcatOn);			    
			    if ( bLogcatOn )
			    {
                    // Set to kill IOBgThread..
                    bBreakOut = true;
                    
                    // Wait for IOBgThread to end..
                    while ( true )
                    {
                        try
                        {
                            Thread.sleep(100);
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
			    else
			    {
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
                    
                    sb.append("shell ps");
                        
                    if ( iOS == WINDOWS )
                        sb.append("\n");
                    
                    //System.out.println("sb: '"+sb.toString()+"'");
                    //commandRequestLatch = new CountDownLatch(1);
                    bCommandFinished = false;
                    sInternalCommand = sb.toString();
                    commandBgThread = new CommandBgThread();
                    commandBgThread.start();
        
                    // Wait for Thread to finish..
                    while ( true )
                    {
                        try
                        {
                            Thread.sleep(250);
                        }
                        catch (InterruptedException ie)
                        {
                        }
        
                        if ( bCommandFinished )
                            break;
                    }
                    
                    sPackageName = sc.sPackageName;
/*                    
                    if ( sPackageName == null )
                        System.out.println("sPackageName null");
                    else
                        System.out.println("sPackageName: '"+sPackageName+"'");
/**/

                    //System.out.println("commandResultS: '"+commandResultS+"'");
                    
                    

                    if ( (sPackageName != null) && (! sPackageName.equals("null"))
                        && (sPackageName.length() > 0) )
                    {
                        iLoc = commandResultS.indexOf(sPackageName);
                        if ( iLoc != -1 )
                        {
                            // Grab PID..
                            int iStart = 0;
                            for ( ; commandResultS.charAt(iLoc) != (char)0x0a; iLoc-- );
            
                            iLoc++;
                            for ( ; ! Character.isWhitespace(commandResultS.charAt(iLoc)); iLoc++ );
                            for ( ; Character.isWhitespace(commandResultS.charAt(iLoc)); iLoc++ );
                            iStart = iLoc;
                            for ( ; ! Character.isWhitespace(commandResultS.charAt(iLoc)); iLoc++ );
                            sPid = commandResultS.substring(iStart, iLoc);
                            //System.out.println("sPid: '"+sPid+"'");
                        }
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
/**/			    
                    sb.append("logcat -v brief");
                    
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
                    bLogcatOn = true;

                    // Turn on status message..                    
                    statusPath.setText("  Logcat on");
                    
                    iOBgThread = new IOBgThread();
                    iOBgThread.start();
                }
			}
			else if ( WIRELESS_CONNECT.equals(sActionCommand) )
			{
				//System.out.println("WIRELESS_CONNECT");
			    SingletonClass sc = SingletonClass.getInstance();
			    if ( sc.bConnected == false )
			        return;
				
				bWirelessEnabled = true;

				//System.out.println("bWirelessConnected: "+bWirelessConnected);
				if ( bWirelessConnected )
					;
				else
				{
				
					bInitWirelessFinished = false;
					initWirelessBgThread = new InitWirelessBgThread();
					initWirelessBgThread.start();
	
					while ( true )
					{
						try
						{
							Thread.sleep(20);
						}
						catch (InterruptedException ie)
						{
						}
					
						if ( bInitWirelessFinished )
							break;
					}
				}
				
				// Check success..
				if ( (iWirelessErrorCode == 0) || bWirelessConnected )
				{
					if ( bWirelessConnected )
						;
					else
					{
						// Success, put up dialog for Connect..
						JOptionPane.showMessageDialog(
							null,				// parentComponent 
							"Hit OK when USB is disconnected.",	// message
							"Wireless Connect",			// title
							JOptionPane.INFORMATION_MESSAGE);	// messageType
					}
					
					//bConnectWirelessFinished = false;
					completeLatch = new CountDownLatch(1);
					
					connectWirelessBgThread = new ConnectWirelessBgThread();
					connectWirelessBgThread.start();

					try
					{
						completeLatch.await();
					}
					catch (InterruptedException ie)
					{
					}

/*
					while ( true )
					{
						try
						{
							Thread.sleep(200);
						}
						catch (InterruptedException ie)
						{
						}
					
						if ( bConnectWirelessFinished )
							break;
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
				
				//System.out.println("Exiting WIRELESS_CONNECT");
						
			}
			else if ( WIRELESS_DISCONNECT.equals(sActionCommand) )
			{
				//System.out.println("WIRELESS_DISCONNECT");
			    SingletonClass sc = SingletonClass.getInstance();
			    if ( sc.bConnected == false )
			        return;
				
				bWirelessEnabled = false;
				
				bDisconnectWirelessFinished = false;
				disconnectWirelessBgThread = new DisconnectWirelessBgThread();
				disconnectWirelessBgThread.start();

				while ( true )
				{
					try
					{
						Thread.sleep(20);
					}
					catch (InterruptedException ie)
					{
					}
				
					if ( bDisconnectWirelessFinished )
						break;
				}
				
				if ( (sDeviceName != null) && (sDeviceName.length() > 0) )
				{
					iLoc = sDeviceName.indexOf(":");
					if ( iLoc != -1 )
					{
						// Disconnected, so prevent it
						// from using wireless Id with '-s'..
						sDeviceName = "";
					}
				}

				//bDevicesFinished = false;
				completeLatch = new CountDownLatch(1);
				getDevicesBgThread = new GetDevicesBgThread();
				getDevicesBgThread.start();
				
				try
				{
					completeLatch.await();
				}
				catch (InterruptedException ie)
				{
				}


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
				
			}
			else if ( PULL_FILE.equals(sActionCommand) )
			{
			    //System.out.println("PULL_FILE");
			    SingletonClass sc = SingletonClass.getInstance();
			    //System.out.println("sc.bConnected: "+sc.bConnected);
			    if ( sc.bConnected == false )
			        return;
			    
			    sSelectedMenu = "Pull file";
			    
                // Open Dialog..
                FileBrowserDialog("Files to pull");
					
			}
			else if ( DELETE_FILE.equals(sActionCommand) )
			{
			    //System.out.println("DELETE_FILE");
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
                    System.out.println("sListSelection null");
                else
                    System.out.println("sListSelection: '"+sListSelection+"'");
/**/                        
                        
			    if ( (sListSelection != null) && (sListSelection.length() > 0) )
			    {
			        //System.out.println("sListSelection: '"+sListSelection+"'");
			        if ( sListSelection.startsWith("[") )
			        {
			            sT = sListSelection.substring(1, sListSelection.length() - 1);
			            //System.out.println("sT: '"+sT+"'");
			            
			            sB = new StringBuffer();
			            sB.append(sCurrentPath);
			            sB.append("/");
			            sB.append(sT);
			            sCurrentPath = sB.toString();

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
                                        sB.append(sCurrentPath);
                                        sB.append("/");
                                    }
        
                                    if ( (sListSelection != null) && (sListSelection.length() > 0) )
                                    {
                                        if ( sListSelection.startsWith("[") )
                                            sListSelection = sListSelection.substring(1, sListSelection.length() - 1);
                
                                        //System.out.println("(Final)sListSelection: '"+sListSelection+"'");
                                            
                                        sB.append(sListSelection);
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
                                    sB.append(sCurrentPath);    // Like: '/dev/block'
                                    sB.append("/");
                                    
                                    //System.out.println("(Final)sListSelection: '"+sListSelection+"'");
        
                                    // Like:  'mmcblk0p25'
                                    sB.append(sListSelection);
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
                                    sB.append(sCurrentPath);    // Like: '/dev/block'
                                    sB.append("/");
                                    
                                    //System.out.println("(Final)sListSelection: '"+sListSelection+"'");
        
                                    // Like:  'mmcblk0p25'
                                    sB.append(sListSelection);
                                    //System.out.println("sB: '"+sB.toString()+"'");
                                    
                                    sb.append(sB.toString());
                                }
                            }
                        }
                        
                        if ( iOS == WINDOWS )
                            sb.append("\n");
                        
                        //System.out.println("(Push/Pull command)sb: '"+sb.toString()+"'");
                        
                        // Set up for IOBgThread output..
                        commandS = sb.toString();
        
                        bIOBgThreadFinished = false;                
                        iOBgThread = new IOBgThread();
                        iOBgThread.start();
        
                        while ( true )
                        {
                            try
                            {
                                Thread.sleep(150);
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
                            Thread.sleep(100);
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
                                Thread.sleep(250);
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
		    StringBuffer sB;
		    
		    JList jList = (JList)e.getSource();
		    int iClickCount = e.getClickCount();
		    //System.out.println("iClickCount: "+iClickCount);
		    if ( iClickCount == 2 )
		    {
		        int iIndex = jList.locationToIndex(e.getPoint());
		        //System.out.println("iIndex: "+iIndex);
		        if ( iIndex >= 0 )
		        {
		            // Gets like:  '[sdcard]'
		            Object object = jList.getModel().getElementAt(iIndex);
		            //System.out.println("object.toString(): "+object.toString());
		            sListSelection = object.toString();

                    // From 'Go'..		            
			        if ( sListSelection.startsWith("[") )
			        {
			            sT = sListSelection.substring(1, sListSelection.length() - 1);
			            //System.out.println("sT: '"+sT+"'");
			            
			            sB = new StringBuffer();
			            sB.append(sCurrentPath);
			            sB.append("/");
			            sB.append(sT);
			            sCurrentPath = sB.toString();

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
			//("\nListSelectionListener valueChanged()");
			//System.out.println("e.toString(): "+e.toString());
			int iFirstIndex = e.getFirstIndex();
			int[] iSelAr;
			long lTime;
			boolean bDoGo = false;
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
			    if ( fileBrowserJList != null )
			    {
			        //sListSelection = (String)fileBrowserJList.getSelectedValue();
			        
			        selectionList = fileBrowserJList.getSelectedValuesList();
/*
                    if ( selectionList == null )
                        System.out.println("selectionList null");
                    else
                        System.out.println("selectionList.size(): "+selectionList.size());
/**/

/*			        
			        if ((selectionList != null) && (selectionList.size() > 0))
			        {
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



