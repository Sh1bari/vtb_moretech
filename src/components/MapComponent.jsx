import React, { useEffect, useState } from 'react';
import { YMaps, Map, Placemark, Clusterer, GeolocationControl, ZoomControl, FullscreenControl } from 'react-yandex-maps';
import axios from 'axios';
import { api } from '../core/api';

function MapComponent() {
  const [officePlacemarks, setOfficePlacemarks] = useState([]);
  const [atmPlacemarks, setAtmPlacemarks] = useState([]);
  const [mapKey, setMapKey] = useState(1);
  const [currentLocation, setCurrentLocation] = useState(null);

  useEffect(() => {
    api.get('/api/getAllOffices')
      .then((response) => {
        const offices = response.data;
        const newPlacemarks = offices.map((office) => ({
          key: office.id,
          geometry: [office.latitude, office.longitude],
          options: {
            iconLayout: "default#image",
            iconImageHref: "/bank1.png",
            iconImageSize: [40, 40],
          },
          properties: {
            hintContent: office.departmentType,
          },
        }));
        setOfficePlacemarks(newPlacemarks);
      })
      .catch((error) => {
        console.error('Ошибка при загрузке данных:', error);
      });
  }, []);

  useEffect(() => {
    api.get('/api/getAllAtms')
      .then((response) => {
        const atms = response.data;
        const newPlacemarks = atms.map((atm) => ({
          key: atm.id,
          geometry: [atm.latitude, atm.longitude],
          properties: {
            hintContent: atm.departmentType,
          },
          options: {
            iconLayout: "default#image",
            iconImageHref: "/ATM1.png",
            iconImageSize: [40, 40],
          },
        }));
        setAtmPlacemarks(newPlacemarks);
      })
      .catch((error) => {
        console.error('Ошибка при загрузке данных:', error);
      });
  }, []);

  const mergedPlacemarks = [...officePlacemarks, ...atmPlacemarks];

  useEffect(() => {
    // Функция для получения текущего местоположения
    const getCurrentLocation = () => {
      if ("geolocation" in navigator) {
        navigator.geolocation.getCurrentPosition(
          (position) => {
            const { latitude, longitude } = position.coords;
            setCurrentLocation([latitude, longitude]);
          },
          (error) => {
            console.error('Ошибка получения текущего местоположения:', error);
          }
        );
      }
    };
    getCurrentLocation();
    return;
  }, []);

  return (
    <YMaps
      query={{
        apikey: '666fdd6a-a191-4151-acd9-68476e330f7d',
      }}>
      <Map
        key={mapKey}
        width="100%"
        height="100vh"
        defaultState={{
          center: [55.755814, 37.617635],
          zoom: 10,
          controls: [],
        }}
        modules={["geolocation", "geocode"]}
        options={{
          restrictMapArea: [
            [53.027644, 27.552111],
            [58.305095, 48.346313],
          ],
        }}
      >
        <Clusterer
          options={{
            preset: "islands#nightClusterIcons",
            groupByCoordinates: false,
            
          }}
        >
          {mergedPlacemarks.map((placemark, index) => (
            <Placemark
              key={`${placemark.key}-${placemark.type}-${index}`}
              geometry={placemark.geometry}
              properties={placemark.properties}
              options={placemark.options}
            />
          ))}
        </Clusterer>
        {currentLocation && (
          <Placemark
            geometry={currentLocation}
            options={{
              iconLayout: "default#image",
              iconImageHref: "/geolocation.png",
              iconImageSize: [30, 41],
            }}
          />
        )}
        <GeolocationControl options={{ float: 'right' }} />
      </Map>
    </YMaps>
  );
}

export default MapComponent;
