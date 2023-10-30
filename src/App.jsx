import React from 'react';
import './styles.css';
import MyMap from './components/MyMap';
import Filter from './components/Filter';

const data = [];
function App() {
  return (
    <div className="relative h-screen">
      <MyMap/>
      <Filter/>
    </div>
  );
}

export default App;