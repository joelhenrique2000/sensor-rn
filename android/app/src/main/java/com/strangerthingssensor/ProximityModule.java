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
import java.util.Timer;
import java.util.TimerTask;

import android.os.SystemClock;
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
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.modules.core.DeviceEventManagerModule;


public class ProximityModule  extends ReactContextBaseJavaModule  implements SensorEventListener {

    public static final String NAME = "JesusSensor";
    private final SensorManager mSensorManager;
    private final Sensor mSensorLight;
    private final ReactApplicationContext mReactContext;

    ProximityModule(ReactApplicationContext context) {
        super(context);

        mReactContext = context;
        mSensorManager = (SensorManager) mReactContext.getSystemService(mReactContext.SENSOR_SERVICE);
        mSensorLight = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
    }


    @Override
    public String getName() {
        return NAME;
    }

    private void sendEvent(@NonNull WritableMap params) {
        try {
            if (mReactContext != null) {
                mReactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                        .emit("LightSensor", params);
            }
        } catch (RuntimeException e) {
            Log.d("ERROR", "error in sending event");
        }
    }

    @Override
    public final void onSensorChanged(SensorEvent sensorEvent) {
        WritableMap sensorMap = Arguments.createMap();
        float lightSensorValue = sensorEvent.values[0];
        sensorMap.putDouble("lightValue", lightSensorValue);
        sendEvent(sensorMap);
    }

    @Override
    public final void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    @ReactMethod
    public void startLightSensor() {
        if (mSensorLight == null) {
            return;
        }
        mSensorManager.registerListener(this, mSensorLight, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @ReactMethod
    public void stopLightSensor() {
        if (mSensorLight == null) {
            return;
        }
        mSensorManager.unregisterListener(this);
    }


    /*@Override
    public String getName() {
        return "ProximityModule";
    }*/

/*
    private void sendEvent(ReactContext reactContext,
                           String eventName,
                           int params) {
        reactContext
                .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                .emit(eventName, params);
    }

    */

    /*
    private void sendEvent(String eventName, @Nullable WritableMap params) {
        reactContext
                .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                .emit(eventName, params);
    }*/


/*


    @Override
    public Map<String, Object> getConstants() {
        final Map<String, Object> constants = new HashMap<>();
        constants.put(KEY_EVENT_ON_SENSOR_CHANGE, EVENT_ON_SENSOR_CHANGE);
        return constants;
    }
*/


/*
    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_PROXIMITY) {

            int i = (int) new Date().getTime();
            sendEvent(getReactApplicationContext(),"AproximityEvent", i);
        }
    }
*/

    //@Override
    public void onSeansorChanged(SensorEvent sensorEvent) {
        /*WritableMap params = Arguments.createMap();

        double distance = sensorEvent.values[0];
        double maximumRange = mProximity.getMaximumRange();
        boolean isNearDevice = distance < maximumRange;

        params.putBoolean(KEY_PROXIMITY, isNearDevice);
        params.putDouble(KEY_DISTANCE, distance);

        sendEvent(EVENT_ON_SENSOR_CHANGE, params);*/
       /* int i = (int) new Date().getTime();
        sendEvent(getReactApplicationContext(),"AproximityEvent", i);*/
    }
/*
    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }*/


}