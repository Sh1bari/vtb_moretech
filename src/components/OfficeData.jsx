import React, { useState, useEffect } from 'react';
import { YMaps, Map, Placemark, Clusterer, GeolocationControl } from 'react-yandex-maps';
import { api } from '../core/api';
import FilterComponent from './Filter'; // Обратите внимание на новое имя компонента

function YourComponent() {
    const [officesData, setOfficesData] = useState([]);
    const [officePlacemarks, setOfficePlacemarks] = useState([]);
    const [currentLocation, setCurrentLocation] = useState(null);
    const [params, setParams] = useState({
        radius: 1000,
        longitude: 37.617698,
        latitude: 55.755864,
        hasRamp: 'Y',
        isIndividual: 'Y',
        hour: '12',
    });

    useEffect(() => {
        fetchOffices(params); // Используйте функцию для запроса с текущими параметрами
    }, [params]); // Следите за изменениями параметров

    const fetchOffices = (params) => {
        api.get('/api/offices', { params })
            .then((response) => {
                const data = response.data;
                setOfficesData(data);
                console.log(data);
                const newPlacemarks = data.map((office) => ({
                    key: office.id,
                    geometry: [office.latitude, office.longitude],
                    options: {
                        iconLayout: 'default#image',
                        iconImageHref: '/bank.png',
                        iconImageSize: [40, 40],
                    },
                    properties: {
                        hintContent: office.departmentType,
                    },
                }));
                setOfficePlacemarks(newPlacemarks);
            })
            .catch((error) => {
                console.error(error);
            });
    };

    const handleFilterChange = (filterData) => {
        setParams((prevParams) => {
            return {
                ...prevParams,
                longitude: filterData.longitude,
                latitude: filterData.latitude,
                hasRamp: filterData.hasRamp,
                isIndividual: filterData.isIndividual,
                hour: filterData.hour,
            };
        });
    };

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
        <div>
            <YMaps
                query={{
                    apikey: '666fdd6a-a191-4151-acd9-68476e330f7d',
                }}
            >
                <Map
                    defaultState={{
                        center: [55.755864, 37.617698],
                        zoom: 10,
                    }}
                    width="100%"
                    height="400px"
                    modules={["geolocation", "geocode"]}
                >
                    <Clusterer
                        options={{
                            preset: "islands#nightClusterIcons",
                            groupByCoordinates: false,
                        }}
                    >
                        {officePlacemarks.map((office, index) => (
                            <Placemark
                                key={index}
                                geometry={office.geometry}
                                properties={office.properties}
                                options={office.options}
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
            <FilterComponent
                onFilterChange={(filterData) => {
                    handleFilterChange({
                        ...filterData,
                        // Передача координат
                        latitude: currentLocation[0],
                        longitude: currentLocation[1],
                    });
                }}
            />
        </div>
    );
}

export default YourComponent;
