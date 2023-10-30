import React, { useState, useEffect } from 'react';
import { YMaps, Map, Placemark, Clusterer, GeolocationControl } from 'react-yandex-maps';
import FilterComponent from './Filter2';
import ScrollingList from './ScrollingList';
import { api } from '../core/api';

function OfficeData() {
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

    const fetchOffices = (filterParams) => {
        // Вызов функции для загрузки данных с использованием фильтров
        api.get('/api/offices', { params: filterParams })
            .then((response) => {
                const data = response.data;
                // Сортировка данных по полю 'k' (от самого маленького к большему)
                data.sort((a, b) => a.k - b.k);
                setOfficesData(data);
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

    useEffect(() => {
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
    }, []);

    // Обработчик фильтрации
    const handleFilterChange = (filterData) => {
        // Обновите параметры фильтра и вызовите fetchOffices
        setParams({
            ...filterData,
            latitude: currentLocation[0],
            longitude: currentLocation[1],
        });
    };

    // Обработчик нажатия кнопки "Применить фильтр"
    const handleApplyFilter = () => {
        fetchOffices(params);
    };

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
            <FilterComponent onFilterChange={handleFilterChange} />
            <button
                className="p-2 border bg-custom-blue text-white cursor-pointer hover:bg-blue-900"
                onClick={handleApplyFilter}
            >
                Применить фильтр
            </button>
            <ScrollingList data={officesData} />
        </div>
    );
}

export default OfficeData;
