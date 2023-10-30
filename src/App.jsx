import React from 'react';
import './styles.css';
import MyMap from './components/MyMap';
import Filter from './components/Filter';
import './App.css';

function App() {
  return (
    <div className="app">
      <MyMap/>
      <Filter/>
    </div>
  );
}

export default App;