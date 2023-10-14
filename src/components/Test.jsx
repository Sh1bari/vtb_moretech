import React, { useEffect, useState } from 'react';
import { YMaps, Map, Placemark, Clusterer } from 'react-yandex-maps';
import axios from 'axios';
import { api } from '../core/api';

function MapComponent() {
    const [placemarks, setPlacemarks] = useState([]);
    const [mapKey, setMapKey] = useState(1); // Add a unique key to the Map component.

    useEffect(() => {
        api.get('/api/getAllOffices')
            .then((response) => {
                const offices = response.data;
                const newPlacemarks = offices.map((office) => (
                    <Placemark
                        key={office.id}
                        geometry={[office.latitude, office.longitude]}
                        properties={{
                            hintContent: office.departmentType,
                        }}
                    />
                ));
                setPlacemarks(newPlacemarks);

                // Increment the map key to force a re-render of the Map component.
                setMapKey((prevKey) => prevKey + 1);
            })
            .catch((error) => {
                console.error('Ошибка при загрузке данных:', error);
            });
    }, []);

    useEffect(() => {
        api.get('/api/getAllAtms')
            .then((response) => {
                const atm = response.data;
                const newPlacemarks = atm.map((office) => (
                    <Placemark
                        key={office.id}
                        geometry={[office.latitude, office.longitude]}
                        properties={{
                            hintContent: office.departmentType,
                        }}
                    />
                ));
                setPlacemarks(newPlacemarks);

                // Increment the map key to force a re-render of the Map component.
                setMapKey((prevKey) => prevKey + 1);
            })
            .catch((error) => {
                console.error('Ошибка при загрузке данных:', error);
            });
    }, []);

    return (
        <YMaps>
            <Map
                key={mapKey} // Ensure a different key triggers a re-render.
                width="100%"
                height="400px"
                defaultState={{ center: [55.755814, 37.617635], zoom: 10 }}
            >
                <Clusterer
                    options={{
                        preset: "islands#nightClusterIcons",
                        groupByCoordinates: false,

                    }}
                >
                    {placemarks}
                </Clusterer>
            </Map>
        </YMaps>
    );
}

export default MapComponent;