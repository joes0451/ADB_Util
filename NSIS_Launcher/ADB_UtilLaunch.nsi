
; Java Launcher
;--------------
 
Name "ADB_Util"
Icon "java48.ico"
OutFile "ADB_Util.exe"
 
SilentInstall silent
AutoCloseWindow true
ShowInstDetails nevershow
 
!define CLASSPATH ".;ADB_Util-1.3.0.jar"
!define CLASS "com.dominionmobile.adbutil.ADB_Util"

Section ""
	Call GetJDK
	Pop $R0

	StrCpy $0 '"$R0" -classpath "${CLASSPATH}" ${CLASS}'
	
	SetOutPath $EXEDIR
	ExecWait $0
SectionEnd
 
Function GetJDK
;
;  Find javaw.exe
;  1 - in the registry
;  2 - in JAVA_HOME environment variable
;  3 - assume javaw.exe in current dir or PATH
 
	Push $R0
	Push $R1
	
	ClearErrors
	ReadRegStr $R1 HKLM "SOFTWARE\JavaSoft\Java Development Kit" "CurrentVersion"
	ReadRegStr $R0 HKLM "SOFTWARE\JavaSoft\Java Development Kit\$R1" "JavaHome"

;	Like: C:\Program Files\Java\jdk1.8.0_31\bin\javaw.exe	
	StrCpy $R0 "$R0\bin\javaw.exe"
	IfErrors 0 JDKFound
	
	ClearErrors
	ReadEnvStr $R0 "JAVA_HOME"
	StrCpy $R0 "$R0\bin\javaw.exe"
	IfErrors 0 JDKFound
	
	StrCpy $R0 "javaw.exe"
	
JDKFound:
	Pop $R1
	Exch $R0
	
FunctionEnd


