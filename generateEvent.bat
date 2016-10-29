@echo off

SETLOCAL EnableDelayedExpansion
SET LF=^


if [%1]==[] goto noEvent
if [%2]==[] goto noMethod

SET CLIENT_PATH=com.google.lecture_manager.client
SET AT=@Override
SET SOURCE=.\src\com\google\lecture_manager\client

echo [gen] Generating event files
echo [gen]   %1.java
SET EVENT_IMPORTS=package !CLIENT_PATH!.events;!LF!!LF!import com.google.gwt.event.shared.GwtEvent;!LF!import !CLIENT_PATH!.handlers.%1Handler;!LF!!LF!
SET EVENT_CLASS=public class %1 extends GwtEvent^<%1Handler^> {!LF!!LF!
SET EVENT_TYPE=  public static Type^<%1Handler^> TYPE ^= new Type^<^>^(^);!LF!!LF!
SET EVENT_GET_TYPE=  !AT!!LF!  public Type^<%1Handler^> getAssociatedType^(^) {!LF!    return TYPE;!LF!  }!LF!!LF!
SET EVENT_DISPATCH=  !AT!!LF!  protected void dispatch^(%1Handler handler) {!LF!    handler.%2^(this^);!LF!  }!LF!
SET EVENT_RESULT=!EVENT_IMPORTS!!EVENT_CLASS!!EVENT_TYPE!!EVENT_GET_TYPE!!EVENT_DISPATCH!}
echo [gen] Finished

echo [gen]
echo [gen] Generating handler files
echo [gen]   %1Handler.java
SET HANDLER_IMPORTS=package !CLIENT_PATH!.handlers;!LF!!LF!import com.google.gwt.event.shared.EventHandler;!LF!import !CLIENT_PATH!.events.%1;!LF!!LF!
SET HANDLER_CLASS=public interface %1Handler extends EventHandler {!LF!
SET HANDLER_METHOD=  void %2^(%1 event^);!LF!
SET HANDLER_RESULT=!HANDLER_IMPORTS!!HANDLER_CLASS!!HANDLER_METHOD!}
echo [gen] Finished

rem write results
echo [gen]
echo [gen] Writing objects
echo !EVENT_RESULT! > !SOURCE!\events\%1.java
echo !HANDLER_RESULT! > !SOURCE!\handlers\%1Handler.java
echo [gen] Done

goto :eof

:noEvent
echo Please provide an event
goto :help

:noMethod
echo Please provide the method name
goto :help

:help
echo Informations:
echo to run the script -^> %0 eventName handlerMethodName
