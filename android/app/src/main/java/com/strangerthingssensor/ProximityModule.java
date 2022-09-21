package com.strangerthingssensor;
import androidx.annotation.NonNull;

import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Promise;

import java.util.Date;
import java.util.Map;
import java.util.HashMap;
import android.util.Log;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.Arguments;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import android.hardware.SensorEventListener;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public class ProximityModule  extends ReactContextBaseJavaModule  implements SensorEventListener {

    private int eventCount = 0;
    private Boolean isSensorActivated = false;
    SensorManager sensorManager;
    Sensor proximitySensor;


    private static final String TAG = "RNProximityModule";
    private static final String KEY_PROXIMITY = "proximity";
    private static final String KEY_DISTANCE = "distance";
    private static final String KEY_EVENT_ON_SENSOR_CHANGE = "EVENT_ON_SENSOR_CHANGE";
    private static final String EVENT_ON_SENSOR_CHANGE = "onSensorChanged";
    private final ReactApplicationContext reactContext;


    private SensorManager mSensorManager;
    private Sensor mProximity;


    ProximityModule(ReactApplicationContext context) {
        super(context);


        this.reactContext = context;
        mSensorManager = (SensorManager) reactContext.getSystemService(Context.SENSOR_SERVICE);
        mProximity = mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
    }



    @Override
    public String getName() {
        return "ProximityModule";
    }

    @ReactMethod
    public void createCalendarEvent(String name, String location) {
        Log.d("CalendarModule", "Create event called with name: " + name
                + " and location: " + location);
    }

    @ReactMethod
    public void createCalendarPromise(Promise promise) {
        try {
            int i = (int) new Date().getTime();

            WritableMap params = Arguments.createMap();
           /* params.putBoolean("isSensorActivated", isSensorActivated);

            promise.resolve("Data returned from promise");
            //sendEvent("AproximityEvent", params);*/
        } catch (Exception e) {
            promise.reject("Err");
        }
    }

    private void sendEvent(ReactContext reactContext,
                           String eventName,
                           int params) {
        reactContext
                .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                .emit(eventName, params);
    }
/*
    private void sendEvent(String eventName, @Nullable WritableMap params) {
        reactContext
                .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                .emit(eventName, params);
    }*/
/*
    @ReactMethod
    public void addListener() {
        mSensorManager.registerListener(this, mProximity, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @ReactMethod
    public void removeListener() {
        mSensorManager.unregisterListener(this);
    }

    @Override
    public Map<String, Object> getConstants() {
        final Map<String, Object> constants = new HashMap<>();
        constants.put(KEY_EVENT_ON_SENSOR_CHANGE, EVENT_ON_SENSOR_CHANGE);
        return constants;
    }
*/

 

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_PROXIMITY) {

            int i = (int) new Date().getTime();
            sendEvent(getReactApplicationContext(),"AproximityEvent", i);
        }
    }


    //@Override
    public void onSeansorChanged(SensorEvent sensorEvent) {
        /*WritableMap params = Arguments.createMap();

        double distance = sensorEvent.values[0];
        double maximumRange = mProximity.getMaximumRange();
        boolean isNearDevice = distance < maximumRange;

        params.putBoolean(KEY_PROXIMITY, isNearDevice);
        params.putDouble(KEY_DISTANCE, distance);

        sendEvent(EVENT_ON_SENSOR_CHANGE, params);*/
        int i = (int) new Date().getTime();
        sendEvent(getReactApplicationContext(),"AproximityEvent", i);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}