@echo off

SETLOCAL EnableDelayedExpansion
SET LF=^


if [%1]==[] goto help
if [%2]==[] goto help

SET CLIENT_PATH=com.google.lecture_manager.client.components
SET UTIL_PATH=com.google.lecture_manager.client.utils
SET CTRL=Controller
SET V=View
SET CONTROLLER=%1!CTRL!
SET VIEW=%1!V!
SET I_VIEW=I%1!V!
SET SOURCE=.\src\com\google\lecture_manager\client\components

echo [gen comp] Target component files
echo [gen comp]   !CONTROLLER!
echo [gen comp]   !VIEW!
echo [gen comp]
echo [gen comp] Start generating !CONTROLLER! class
SET CTRL_IMPORTS=package !CLIENT_PATH!.%2;!LF!!LF!import !UTIL_PATH!.!CTRL!;!LF!import !UTIL_PATH!.!V!;!LF!!LF!
SET CTRL_CLASS=public class !CONTROLLER! extends !CTRL!^<!CONTROLLER!.!I_VIEW!^> {!LF!!LF!
SET INTERFACE=  public interface !I_VIEW! extends !V! {!LF!  }!LF!!LF!
SET INSTANCE=  private static !CONTROLLER! INSTANCE ^= null;!LF!!LF!
SET VIEW_ATTR=  private !I_VIEW! view;!LF!!LF!
SET BIND=  @Override!LF!  public void bind^(!I_VIEW! view^) {!LF!    this.view ^= view;!LF!    setIsBound^(true^);!LF!  }!LF!!LF!
SET GET_VIEW=  @Override!LF!  public !V! getView^(^) {!LF!    return view;!LF!  }!LF!!LF!
SET GET_INSTANCE=  public static !CTRL! getInstance^(^) {!LF!    if ^(INSTANCE ^=^= null^)!LF!      INSTANCE ^= new !CONTROLLER!^(^);!LF!    return INSTANCE;!LF!  }!LF!
SET CTRL_RESULT=!CTRL_IMPORTS!!CTRL_CLASS!!INTERFACE!!INSTANCE!!VIEW_ATTR!!BIND!!GET_VIEW!!GET_INSTANCE!}
echo [gen comp] Finished

echo [gen comp]
echo [gen comp] Start generating !VIEW! class
SET VIEW_IMPORTS=package !CLIENT_PATH!.%2;!LF!!LF!import com.google.gwt.user.client.ui.Widget;!LF!!LF!
SET VIEW_CLASS=public class !VIEW! implements !CONTROLLER!.!I_VIEW! {!LF!!LF!
SET CONSTRUCTOR=  public !VIEW!^(^) {!LF!    initGUI^(^);!LF!  }!LF!!LF!
SET INIT_GUI=  @Override!LF!  public void initGUI^(^) {!LF!  }!LF!!LF!
SET AS_WIDGET=  @Override!LF!  public Widget asWidget^(^) {!LF!    return null;!LF!  }!LF!
set VIEW_RESULT=!VIEW_IMPORTS!!VIEW_CLASS!!CONSTRUCTOR!!INIT_GUI!!AS_WIDGET!}
echo [gen comp] Finished

echo [gen comp]
echo [gen comp] Create directory !SOURCE!\%2\
mkdir !SOURCE!\%2\
echo [gen comp] Writing objects
echo !CTRL_RESULT! > !SOURCE!\%2\!CONTROLLER!.java
echo !VIEW_RESULT! > !SOURCE!\%2\!VIEW!.java
echo [gen comp] Done

goto :eof

:help
echo [gen comp] Info:
echo [gen comp] To run the script -^> %0 viewName dirName
echo [gen comp] e.g. %0 Login login