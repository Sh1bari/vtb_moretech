import React, { useState, useEffect } from 'react';
import { YMaps, Map, Placemark, Clusterer, GeolocationControl } from '@pbe/react-yandex-maps';
import '../styles.css';
import { api } from '../core/api.js';

function MyMap() {
  const [data, setData] = useState(null);
  const [clusterPoints, setClusterPoints] = useState([]);
  const [userLocation, setUserLocation] = useState(null);

  useEffect(() => {
    async function fetchData() {
      try {
        const res = await api.get('/api/getAll');
        setData(res.data);
        const points = res.data.map((item) => [item.latitude, item.longitude]);
        setClusterPoints(points);
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

  const [activePlacemark, setActivePlacemark] = useState(null);

  const handlePlacemarkClick = (index) => {
    setActivePlacemark(index);
  };

  return (
    <div className="">
      <YMaps
        apikey="666fdd6a-a191-4151-acd9-68476e330f7d" // Ваш API-ключ
        query={{
          ns: "use-load-option",
          load: "Map,control.ZoomControl,control.FullscreenControl,geoObject.addon.balloon",
        }}
      >
        <Map
          style={{ width: '100%', height: '70vh' }}
          modules={["geolocation", "geocode"]}  
          defaultState={{
            center: userLocation || [55.75, 37.57], // Используем местоположение пользователя или координаты по умолчанию
            zoom: 10,
            controls: ["zoomControl", "fullscreenControl"],
          }}
        >
          <GeolocationControl options={{ float: "left" }} />
          <Clusterer
            options={{
              preset: "islands#.ightClusterIcons",
              groupByCoordinates: false,
            }}
          >
            {clusterPoints.map((coordinates, index) => (
              <Placemark
                key={index}
                geometry={coordinates}
                options={{
                  iconLayout: "default#image",
                  iconImageHref: activePlacemark === index ? "/placemark-active.png" : "/placemark.png",
                  iconImageSize: [30, 41],
                }}
                onClick={() => handlePlacemarkClick(index)}
                onMouseEnter={() => handlePlacemarkClick(index)}
                onMouseLeave={() => handlePlacemarkClick(null)}
              />
            ))}
          </Clusterer>
          {userLocation && (
            <Placemark
              geometry={userLocation}
              options={{
                iconLayout: "default#image",
                iconImageHref: "/geolocation.png", // Путь к иконке местоположения пользователя
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
