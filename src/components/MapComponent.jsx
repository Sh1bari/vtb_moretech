import React, { useEffect, useState } from 'react';
import { YMaps, Map, Placemark, Clusterer, GeolocationControl } from 'react-yandex-maps';
import { api } from '../core/api';

function MapComponent() {
  const [officePlacemarks, setOfficePlacemarks] = useState([]);
  const [atmPlacemarks, setAtmPlacemarks] = useState([]);
  const [mapKey, setMapKey] = useState(1);
  const [currentLocation, setCurrentLocation] = useState(null);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    api.get('/api/getAllOffices')
      .then((response) => {
        const offices = response.data;
        const newPlacemarks = offices.map((office) => ({
          key: office.id,
          geometry: [office.latitude, office.longitude],
          options: {
            iconLayout: "default#image",
            iconImageHref: "/bank.png",
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
            iconImageHref: "/ATM3.png",
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
    // Задержка в 2 секунды перед скрытием гифки
    const timeout = setTimeout(() => {
      setLoading(false);
    }, 1000);

    return () => {
      clearTimeout(timeout);
    };
  }, []);

  const [selectedMarker, setSelectedMarker] = useState(null);

  // Добавьте обработчик события при клике на маркер
  const handleMarkerClick = (e) => {
    setSelectedMarker(e.get('target'));
  };

  return (
    <YMaps
      query={{
        apikey: '666fdd6a-a191-4151-acd9-68476e330f7d',
      }}
    >
      <div className="relative">
        {loading && (
          <div className="h-screen flex items-center justify-center">
            <img
              src='/loading.gif' // Путь к гифке
              alt="Loading"
              hidden={!loading} // Скрыть, если loading равен false
            />
          </div>
        )}
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
              [53.469556, 35.094762],
              [57.185870, 39.414526],
            ],
          }}
          hidden={loading} // Скрыть, если loading равен true
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
          <GeolocationControl options={{ float: 'right' }} />
        </Map>
      </div>
    </YMaps>
  );
}

export default MapComponent;
