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
} from 'react-native';

import {Colors} from 'react-native/Libraries/NewAppScreen';

const {ProximityModule} = NativeModules;

const App = () => {
  // eslint-disable-next-line react-hooks/exhaustive-deps

  useEffect(() => {
    const eventEmitter = new NativeEventEmitter();

    eventEmitter.addListener('AproximityEvent', aproximityEvent => {
      console.log(aproximityEvent);
    });
    return () => {
      eventEmitter.removeAllListeners('AproximityEvent');
    };
  }, []);

  const isDarkMode = useColorScheme() === 'dark';
  const [test, setTest] = useState();

  const backgroundStyle = {
    backgroundColor: isDarkMode ? Colors.darker : Colors.lighter,
  };

  async function getData() {
    var result = await ProximityModule.createCalendarPromise();

    setTest(result);
    return Promise.resolve(result);
  }

  getData();

  return (
    <SafeAreaView style={backgroundStyle}>
      <Text>{test}</Text>
    </SafeAreaView>
  );
};

export default App;
