/* eslint-disable @typescript-eslint/no-unused-vars */
/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * Generated with the TypeScript template
 * https://github.com/react-native-community/react-native-template-typescript
 *
 * @format
 */

import React, {useEffect, useState} from 'react';
import {
  SafeAreaView,
  Text,
  useColorScheme,
  NativeModules,
  NativeEventEmitter,
  DeviceEventEmitter,
} from 'react-native';

import {Observable} from 'rxjs';
import {filter, map, publish, refCount} from 'rxjs/operators';
import {Colors} from 'react-native/Libraries/NewAppScreen';
import {
  accelerometer,
  SensorTypes,
  setUpdateIntervalForType,
} from './sensorTest';

const {JesusSensor} = NativeModules;
// const eventEmitter = new NativeEventEmitter(JesusSensor);

// function isAvailable() {
//   const promise = ProximityModule.isAvailable();
//   return promise;
// }

// function start() {
//   ProximityModule.startUpdates();
// }

// function stop() {
//   ProximityModule.stopUpdates();
// }

// const proximitySensor = Observable.create(function subscribe(observer: {
//   next: (arg0: any) => void;
//   error: (arg0: string) => void;
// }) {
//   let isSensorAvailable = false;

//   let unsubscribeCallback = () => {
//     if (!isSensorAvailable) return;
//     if (ProximityModule) ProximityModule.remove();
//     // stop the sensor
//     stop();
//   };

//   isAvailable().then(
//     () => {
//       isSensorAvailable = true;

//       const emitter = new NativeEventEmitter(ProximityModule);

//       emitter.addListener(ProximityModule, data => {
//         observer.next(data);
//       });

//       // Start the sensor manager
//       start();
//     },
//     () => {
//       observer.error('Sensor não está disponivel');
//     },
//   );

//   return unsubscribeCallback;
// });

export function startLightSensor(): void {
  return JesusSensor.startLightSensor();
}

export function stopLightSensor(): void {
  return JesusSensor.stopLightSensor();
}

const App = () => {
  const LIGHT_SENSOR = 'LightSensor';

  const isDarkMode = useColorScheme() === 'dark';
  const [test, setTest] = useState(0);
  const [result, setResult] = React.useState<number | undefined>();

  useEffect(() => {
    startLightSensor();
    const subscription = DeviceEventEmitter.addListener(
      LIGHT_SENSOR,
      (data: {lightValue: number}) => {
        setResult(data.lightValue);
      },
    );

    return () => {
      stopLightSensor();
      subscription?.remove();
    };
  }, []);

  const backgroundStyle = {
    backgroundColor: isDarkMode ? Colors.darker : Colors.lighter,
  };

  return (
    <SafeAreaView style={backgroundStyle}>
      <Text>{result}</Text>
    </SafeAreaView>
  );
};

export default App;
