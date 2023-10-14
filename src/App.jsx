import React from 'react';
import './styles.css'; // Подключите стили здесь
import MapComponent from './components/MapComponent';

function App() {
  return (
    <div className="relative">
      <MapComponent />
      <div className="w-full bg-custom-blue absolute top-0 left-0 z-10">
        <header className=" text-white flex items-center p-0">
          <img src="/VTB_logo-add_ru_rgb.png" alt="logo vtb" className="w-48" />
          <p className=" text-xl">Web-service подбора отделения банка, учитывая потребности клиента.</p>
        </header>
        <hr className="bg-blue-500 h-2 my-0" />
      </div>
      {/* <div className="container">
        <div className="block"></div>
        <div className="block"></div>
        <div className="block"></div>
      </div> */}
    </div>
  );
}

export default App;
