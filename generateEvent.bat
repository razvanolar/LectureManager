@echo off

SETLOCAL EnableDelayedExpansion
SET LF=^


if [%1]==[] goto help
if [%2]==[] goto help

SET CLIENT_PATH=com.google.lecture_manager.client
SET AT=@Override
SET SOURCE=.\src\com\google\lecture_manager\client

echo [gen event] Generating event files
echo [gen event]   %1.java
SET EVENT_IMPORTS=package !CLIENT_PATH!.events;!LF!!LF!import com.google.gwt.event.shared.GwtEvent;!LF!import !CLIENT_PATH!.handlers.%1Handler;!LF!!LF!
SET EVENT_CLASS=public class %1 extends GwtEvent^<%1Handler^> {!LF!!LF!
SET EVENT_TYPE=  public static Type^<%1Handler^> TYPE ^= new Type^<^>^(^);!LF!!LF!
SET EVENT_GET_TYPE=  !AT!!LF!  public Type^<%1Handler^> getAssociatedType^(^) {!LF!    return TYPE;!LF!  }!LF!!LF!
SET EVENT_DISPATCH=  !AT!!LF!  protected void dispatch^(%1Handler handler) {!LF!    handler.%2^(this^);!LF!  }!LF!
SET EVENT_RESULT=!EVENT_IMPORTS!!EVENT_CLASS!!EVENT_TYPE!!EVENT_GET_TYPE!!EVENT_DISPATCH!}
echo [gen event] Finished

echo [gen event]
echo [gen event] Generating handler files
echo [gen event]   %1Handler.java
SET HANDLER_IMPORTS=package !CLIENT_PATH!.handlers;!LF!!LF!import com.google.gwt.event.shared.EventHandler;!LF!import !CLIENT_PATH!.events.%1;!LF!!LF!
SET HANDLER_CLASS=public interface %1Handler extends EventHandler {!LF!
SET HANDLER_METHOD=  void %2^(%1 event^);!LF!
SET HANDLER_RESULT=!HANDLER_IMPORTS!!HANDLER_CLASS!!HANDLER_METHOD!}
echo [gen event] Finished

rem write results
echo [gen event]
echo [gen event] Writing objects
echo !EVENT_RESULT! > !SOURCE!\events\%1.java
echo !HANDLER_RESULT! > !SOURCE!\handlers\%1Handler.java
echo [gen event] Done

goto :eof

:help
echo [gen event] Info:
echo [gen event] To run the script -^> %0 eventName handlerMethodName
echo [gen event] e.g. %0 LoginEvent onLoginEvent
