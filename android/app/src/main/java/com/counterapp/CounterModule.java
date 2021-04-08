package com.counterapp;

import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.Arguments;

public class CounterModule extends ReactContextBaseJavaModule {
  private static ReactApplicationContext reactContext;

  CounterModule(ReactApplicationContext context) {
    super(context);
    reactContext = context;
  }

  private void sendEvent(ReactContext reactContext,
                         String eventName,
                         WritableMap params) {
    reactContext
      .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
      .emit(eventName, params);
  }

  private static Integer initialCount = 0;


  @Override
  public String getName() {
    return "Counter";
  }

  @ReactMethod
  public void getCount(Callback printCallback) {
      printCallback.invoke(initialCount);
  }

  @ReactMethod
  public void increment() {
    initialCount += 1;
    
    WritableMap params = Arguments.createMap();
    params.putInt("count", initialCount);
    sendEvent(reactContext, "onIncrement", params);
  }

  @ReactMethod
  public void decrement() {
    initialCount -= 1;

    WritableMap params = Arguments.createMap();
    params.putInt("count", initialCount);
    sendEvent(reactContext, "onDecrement", params);
  }
}