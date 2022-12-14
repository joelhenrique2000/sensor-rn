import sensors from './sensors';
export {
  setUpdateInterval as setUpdateIntervalForType,
  setLogLevelForType,
} from './rnsensors';

export const SensorTypes = {
  accelerometer: 'accelerometer',
  gyroscope: 'gyroscope',
  magnetometer: 'magnetometer',
  barometer: 'barometer',
  orientation: 'orientation',
  gravity: 'gravity',
};

export const {
  accelerometer,
  gyroscope,
  magnetometer,
  barometer,
  orientation,
  gravity,
} = sensors;
export default sensors;
