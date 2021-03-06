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
SET CLIENT=.\src\com\google\lecture_manager\client
SET SOURCE=!CLIENT!\components

if exist !SOURCE!\%2 goto alreadyExist

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
mkdir !SOURCE!\%2
echo [gen comp] Writing objects
echo !CTRL_RESULT! > !SOURCE!\%2\!CONTROLLER!.java
echo !VIEW_RESULT! > !SOURCE!\%2\!VIEW!.java
echo [gen comp] Done

if [%3]==[] goto :success

SET FACTORIES_PATH=!CLIENT!\utils\factories
SET CONTROLLER_FACTORY_PATH=!FACTORIES_PATH!\ControllerFactory.java
SET CONTROLLER_PACKAGE=!CLIENT_PATH!.%2.!CONTROLLER!
SET VIEW_PACKAGE=!CLIENT_PATH!.%2.!VIEW!
SET VIEW_FACTORY_PATH=!FACTORIES_PATH!\ViewFactory.java
echo [gen comp]
echo [gen comp] Start adding %e enum into ElementTypes
java -cp .\utils UpdateElementTypes !CLIENT!\utils\ElementTypes.java !CONTROLLER_FACTORY_PATH! !VIEW_FACTORY_PATH! !CONTROLLER_PACKAGE! !VIEW_PACKAGE! %3 || goto javaFailed

goto :success

:help
echo [gen comp] Info:
echo [gen comp] To run the script -^> %0 viewName dirName [elementType]
echo [gen comp] If elementType parameter is provided the following classes will be updated in order to use the new created component:
echo [gen comp]   * ElementTypes
echo [gen comp]   * ControllerFactory
echo [gen comp]   * ViewFactory
echo [gen comp] e.g. %0 Login login
echo [gen comp] e.g. %0 Login login LOGIN_FORM
goto :eof

:javaFailed
echo [gen comp] UpdateElementTypes failed
echo [gen comp] Remove directory !SOURCE!\%2
rmdir /s /q !SOURCE!\%2
goto :error

:alreadyExist
echo [gen comp] The specified component already exists
goto error

:error
echo [gen comp] STATUS: FAILED
goto :eof

:success
echo [gen comp] STATUS: SUCCESS