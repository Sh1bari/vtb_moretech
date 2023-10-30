import { React, useEffect, useState, useRef } from 'react'
import { YMaps, Map, Placemark, ListBox, ListBoxItem } from '@pbe/react-yandex-maps';
import ChooseRoute from "./ChooseRoute";

function MyMap() {

    const [currentLocation, setCurrentLocation] = useState(null);

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

    const map = useRef(null);
    const mapState = {
        center: [55.753600, 37.621184],
        zoom: 12
    };

    const multiRoute = useRef(null);

    const addRoute = (ymaps) => {
        const pointA = currentLocation;
        const pointB = [];

        multiRoute.current = new ymaps.multiRouter.MultiRoute(
            {
                referencePoints: [pointA, pointB],
                params: {
                    routingMode: 'auto'
                }
            },
            {
                wayPointStartIconLayout: 'none',
                wayPointFinishIconLayout: 'none',
                boundsAutoApply: false
            }
        );

        map.current.geoObjects.add(multiRoute.current);
    };

    const [routeType, setRouteType] = useState();

    const handleRouteTypeChange = (newRouteType) => {
        setRouteType(newRouteType);
        if (multiRoute.current) {
            multiRoute.current.model.setParams({
                routingMode: newRouteType
            });
        }
    };


    return (
        <div>
            <YMaps
                query={{ apikey: '666fdd6a-a191-4151-acd9-68476e330f7d', }}
            >
                <Map
                    defaultState={{
                        center: [55.753600, 37.621184],
                        zoom: 12,
                        controls: ["zoomControl", "fullscreenControl"],
                    }}
                    width="100%"
                    height="90vh"
                    state={mapState}
                    instanceRef={map}
                    onLoad={addRoute}
                    modules={["control.ZoomControl", "control.FullscreenControl", "multiRouter.MultiRoute"]}
                >
                    <ListBox data={{ content: "Выберите тип" }}>
                        <ListBoxItem data={{ content: "Банкоматы" }} />
                        <ListBoxItem data={{ content: "Оффисы" }} />
                    </ListBox>
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
                </Map>
            </YMaps>
            <ChooseRoute onRouteTypeChange={handleRouteTypeChange} />
        </div>
    )
}

export default MyMap;