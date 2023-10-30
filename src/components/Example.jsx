import { React, useRef, useState } from "react";
import { YMaps, Map } from "@pbe/react-yandex-maps";
import ChooseRoute from "./ChooseRoute";

export default function Example() {
    const map = useRef(null);
    const mapState = {
        center: [55.739625, 37.5412],
        zoom: 12
    };

    const multiRoute = useRef(null);

    const addRoute = (ymaps) => {
        const pointA = [55.749, 37.524]; // Москва
        const pointB = [59.918072, 30.304908]; // Санкт-Петербург

        multiRoute.current = new ymaps.multiRouter.MultiRoute(
            {
                referencePoints: [pointA, pointB],
                params: {
                    routingMode: 'auto'
                }
            },
            {
                boundsAutoApply: true
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
        <div className="App">
            <YMaps
                query={{
                    apikey: '666fdd6a-a191-4151-acd9-68476e330f7d',
                }}
            >
                <Map
                    modules={["multiRouter.MultiRoute"]}
                    state={mapState}
                    instanceRef={map}
                    onLoad={addRoute}
                ></Map>
            </YMaps>
            <ChooseRoute onRouteTypeChange={handleRouteTypeChange} />
        </div>
    );
}
