import React, { useState, useRef } from 'react';
import { YMaps, Map, Placemark, ListBox, ListBoxItem } from '@pbe/react-yandex-maps';
import './MyMap.css';
import Filter from '../../Filter/Filter';
import useCurrentLocation from '../Location';

const MyMap = () => {
    const currentLocation = useCurrentLocation();
    const map = useRef(null);

    const mapState = {
        center: [55.753600, 37.621184],
        zoom: 12
    };

    const multiRoute = useRef(null);

    const addRoute = (ymaps) => {
        const pointA = currentLocation;
        const pointB = [55.753600, 37.621184];

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
        <div className='map'>
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
                    height="100vh"
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
            <div className='btns'>
                <button onClick={() => handleRouteTypeChange('auto')}>Автомобиль</button>
                <button onClick={() => handleRouteTypeChange('masstransit')}>Автобусы</button>
                <button onClick={() => handleRouteTypeChange('pedestrian')}>Пешеход</button>
                <Filter />
            </div>
        </div>
    )
}

export default MyMap;