@echo off

SETLOCAL EnableDelayedExpansion
SET LF=^


if [%1]==[] goto help
if [%2]==[] goto help


set CLIENT_PATH=com.google.lecture_manager.client.components
set UTIL_PATH=com.google.lecture_manager.client.utils
set CTRL=Controller
set V=View
set CONTROLLER=%1!CTRL!
set VIEW=%1!V!
set I_VIEW=I%1!V!
SET SOURCE=.\src\com\google\lecture_manager\client\components

set CTRL_IMPORTS=package !CLIENT_PATH!.%2;!LF!!LF!import !UTIL_PATH!.!CTRL!;!LF!import !UTIL_PATH!.!V!;!LF!!LF!
set CTRL_CLASS=public class !CONTROLLER! extends !CTRL!^<!CONTROLLER!.!I_VIEW!^> {!LF!!LF!
set INTERFACE=  public interface !I_VIEW! extends !V! {!LF!  }!LF!!LF!
set INSTANCE=  private static !CONTROLLER! INSTANCE ^= null;!LF!!LF!
set VIEW_ATTR=  private !I_VIEW! view;!LF!!LF!
set BIND=  @Override!LF!  public void bind^(!I_VIEW! view^) {!LF!    this.view ^= view;!LF!    setIsBound^(true^);!LF!  }!LF!!LF!
set GET_VIEW=  @Override!LF!  public !V! getView^(^) {!LF!    return view;!LF!  }!LF!!LF!
set GET_INSTANCE=  public static !CTRL! getInstance^(^) {!LF!    if ^(INSTANCE ^=^= null^)!LF!      INSTANCE ^= new !CONTROLLER!^(^);!LF!    return INSTANCE;!LF!  }!LF!
set CTRL_RESULT=!CTRL_IMPORTS!!CTRL_CLASS!!INTERFACE!!INSTANCE!!VIEW_ATTR!!BIND!!GET_VIEW!!GET_INSTANCE!}

set VIEW_IMPORTS=package !CLIENT_PATH!.%2;!LF!!LF!import com.google.gwt.user.client.ui.Widget;!LF!!LF!
SET VIEW_CLASS=public class !VIEW! implements !CONTROLLER!.!I_VIEW! {!LF!!LF!
SET CONSTRUCTOR=  public !VIEW!^(^) {!LF!    initGUI^(^);!LF!  }!LF!!LF!
SET INIT_GUI=  @Override!LF!  public void initGUI^(^) {!LF!  }!LF!!LF!
SET AS_WIDGET=  @Override!LF!  public Widget asWidget^(^) {!LF!    return null;!LF!  }!LF!
set VIEW_RESULT=!VIEW_IMPORTS!!VIEW_CLASS!!CONSTRUCTOR!!INIT_GUI!!AS_WIDGET!}

mkdir !SOURCE!\%2\
echo !CTRL_RESULT! > !SOURCE!\%2\!CONTROLLER!.java
echo !VIEW_RESULT! > !SOURCE!\%2\!VIEW!.java

goto :eof

:help
echo [gen comp] Info:
echo [gen comp] to run the script -^> %0 viewName dirName
echo [gen comp] e.g. %0 Login login