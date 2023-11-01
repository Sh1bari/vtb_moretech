import React from 'react';
import ReactDOM from 'react-dom';
import { Provider } from 'react-redux';
import './styles.css';
import App from './App';
import reportWebVitals from './reportWebVitals';
import './fonts/VTBGroupUI-Medium.ttf';
import './fonts/VTBGroupUI-Regular.ttf';
import './fonts/VTBGroupUI-SemiBold.ttf';
import './fonts/VTBGroupUI-Bold.ttf';
import './fonts/VTBGroupUI-DemiBold.ttf';
import './fonts/VTBGroupUI-Light.ttf';

ReactDOM.render(
    <App />,
    document.getElementById('root')
);

reportWebVitals();