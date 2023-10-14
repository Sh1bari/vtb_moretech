import React, { useState, useEffect } from 'react';
import { YMaps, Map, Placemark, Clusterer, GeolocationControl } from '@pbe/react-yandex-maps';
import '../styles.css';
import { api } from '../core/api.js';

function MyMap() {
  const [data, setData] = useState([]);
  const [clusterPoints, setClusterPoints] = useState([]);
  const [userLocation, setUserLocation] = useState(null);
  const [activePlacemark, setActivePlacemark] = useState(null);
  const [dataLoaded, setDataLoaded] = useState(false); // Состояние для отслеживания загрузки данных

  useEffect(() => {
    async function fetchData() {
      try {
        const officesResponse = await api.get('/api/getAllOffices');
        const atmResponse = await api.get('/api/getAllAtms');
        const offices = officesResponse.data;
        const atm = atmResponse.data;

        // Обновляем данные и точки для кластера
        setData([...offices, ...atm]);
        const points = [...offices, ...atm].map((item) => [item.latitude, item.longitude]);
        setClusterPoints(points);

        // Устанавливаем, что данные загружены
        setDataLoaded(true);
      } catch (error) {
        console.error('Ошибка при загрузке данных', error);
      }
    }

    fetchData();

    if ("geolocation" in navigator) {
      navigator.geolocation.getCurrentPosition((position) => {
        const { latitude, longitude } = position.coords;
        setUserLocation([latitude, longitude]);
      });
    }
  }, []);

  return (
    <div className="">
      <YMaps
        
        query={{
          ns: "use-load-option",
          load: "Map,control.ZoomControl,control.FullscreenControl,geoObject.addon.balloon",
        }}
      >
        <Map
          style={{ width: '100%', height: '70vh' }}
          modules={["geolocation", "geocode"]}
          defaultState={{
            center: userLocation || [55.75, 37.57],
            zoom: 10,
            controls: ["zoomControl", "fullscreenControl"],
          }}
        >
          <GeolocationControl options={{ float: "left" }} />
          {dataLoaded && ( // Отображаем метки только после загрузки данных
            <Clusterer
              options={{
                preset: "islands#nightClusterIcons",
                groupByCoordinates: false,
              }}
            >
              {clusterPoints.map((coordinates, index) => (
                <Placemark
                  key={index}
                  geometry={coordinates}
                  options={{
                    iconLayout: "default#image",
                    iconImageHref: activePlacemark === index ? "/bank1.png" : "/ATM1.png",
                    iconImageSize: [40, 40],
                  }}
                />
              ))}
            </Clusterer>
          )}
          {userLocation && (
            <Placemark
              geometry={userLocation}
              options={{
                iconLayout: "default#image",
                iconImageHref: "/geolocation.png",
                iconImageSize: [30, 41],
              }}
            />
          )}
        </Map>
      </YMaps>
    </div>
  );
}

export default MyMap;
