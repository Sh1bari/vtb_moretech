import React from 'react';
import './styles.css'; // Подключите стили здесь
import MapComponent from './components/MapComponent'
import Filter from './components/Filter';
import Variants from './components/Variants';
function App() {
  return (
    <div className="relative">
      <div className="w-full bg-custom-blue absolute top-0 left-0 z-10">
        <header className=" text-white flex items-center p-0">
          <img src="/VTB_logo-add_ru_rgb.png" alt="logo vtb" className="w-48" />
          <p className=" text-xl">WEB-service для подбора отделения банка, учитывая потребности клиента.</p>
        </header>
        <hr className=" bg-custom-cian h-2 my-0 border-0" />
      </div>
      <div className="flex justify-between">
        <div className="flex-1 bg-white h-screen m-0 p-0"><Filter /></div>
        <div className="flex-1 bg-white h-screen m-0 p-0"><MapComponent /></div>
        <div className="flex-1 bg-white h-screen m-0 p-0 hidden"><Variants /></div>
      </div>
    </div>
  );
}

export default App;