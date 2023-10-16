import L from 'leaflet';
import { createControlComponent } from '@react-leaflet/core';
import 'leaflet-routing-machine';
import 'lrm-graphhopper';
import { ApiKey } from './ApiKey';


const CreateRoutingMachine = (
    from,
    to,
    vehicle,
) => {
    const instance = L.Routing.control({
        router: L.Routing.graphHopper(ApiKey, {
            urlParameters: {
                vehicle: vehicle || 'foot',
            },

        }),

        lineOptions: {
            styles: [{ color: '#6FA1EC', weight: 4 }],
            extendToWaypoints: true, // если true, то линия будет проходить через точки маршрута
            missingRouteTolerance: 100, // если маршрут не найден, то будет искать маршрут в радиусе 100 метров
        },

        plan: L.Routing.plan([L.latLng(from), L.latLng(to)], {
            draggableWaypoints: false,
            addWaypoints: false,
            createMarker: () => false,
        }),

        formatter: new L.Routing.Formatter({
            language: 'ru',
            units: 'metric',
            distanceTemplate: '{value} м',
        }),

        // иконочки для точек маршрута
        pointMarkerStyle: {
            radius: 0,
            opacity: 0,
        },

        show: false,
    });

    if (from === to) {
        instance.setWaypoints([]);
    }

    return {
        Router: createControlComponent(() => instance),
        instance,
    };
};

export default CreateRoutingMachine;