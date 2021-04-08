import React, { useEffect } from 'react';
import { Text, View, Button, NativeModules, NativeEventEmitter, StyleSheet } from 'react-native';

export default function App() {
  const { Counter } = NativeModules;
  const CounterEvents = new NativeEventEmitter(Counter);

  useEffect(() => {
    CounterEvents.addListener("onIncrement", showCount)
    CounterEvents.addListener("onDecrement", showCount)
  }, [CounterEvents])

  const showCount = (value) => {
    if (typeof value === "number") {
      console.log('count is ' + value)
    } else {
      console.log('count is ' + value.count)
    }
  }

  return (
      <View style={styles.container}>
          <Text style={styles.title}>Native Module Test</Text>
          <Button title="Get Count" onPress={() => Counter.getCount(showCount)}/>
          <Button title="Increase Count" onPress={() => Counter.increment()} />
          <Button title="Decrease Count" onPress={() => Counter.decrement()} />
      </View>
  )
}

const styles = StyleSheet.create({
  container : {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    flexDirection: 'column'
  },
  title : {
    fontSize: 20,
    marginBottom: 30
  }
})