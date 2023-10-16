import React, { useState, useEffect } from 'react';
import { LatLng } from 'leaflet';
import { MapContainer, TileLayer, Marker } from 'react-leaflet';
import CreateRoutingMachine from './Routing';
import { LocationMarker } from './Location';
import 'leaflet/dist/leaflet.css';
import { MapMarker } from './marker';
import './main.css';
import { useSelector } from 'react-redux';
import { selectBranches } from '../store/branchesSlice';

const mark1 = [55.755864, 37.617698];

const MapWidget = () => {
    const [location, setLocation] = useState(null);

    const branches = useSelector(selectBranches);

    const minBranch = branches.reduce((min, branch) => {
        return branch.k < min.k ? branch : min;
    }, branches[0]);

    const { instance, Router } = CreateRoutingMachine(
        location ? [location.lat, location.lng] : mark1,
        minBranch ? [minBranch.latitude, minBranch.longitude] : mark1,
        'car'
    );

    instance.on('routesfound', (e) => {
        const routes = e.routes;
        if (routes.length > 0) {
            const route = routes[0];
            const totalTime = route.summary.totalTime;
            console.log(`Время маршрута: ${totalTime} секунд`);
        }
    });

    useEffect(() => {
        const label = document.querySelector(
            '.leaflet-control-attribution.leaflet-control',
        );
        if (label) {
            label.innerHTML = 'Powered by MD&HH';
        }
    }, [location]);

    return (
        <div className='map-window'>
            <MapContainer
                center={location ?? [55.75399399999374, 37.62209300000001]}
                zoom={16}
                scrollWheelZoom={true}
                className='map-window-style'
            >
                <TileLayer
                    attribution='&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
                    url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
                />
                {
                    branches.length > 0 && minBranch &&
                    branches.map((data, key) => (
                        (minBranch.k == data.k)? (<MapMarker key={key + 1} type='atm' position={[data?.latitude, data?.longitude]} />) : (<MapMarker key={key + 1} type='bank' position={[data?.latitude, data?.longitude]} />)
                    ))
                }


                <LocationMarker location={location} setLocation={setLocation} />
                {/* <MapMarker type='default' position={mark1} /> */}

                {minBranch && <Router />}
            </MapContainer>
        </div>
    );
};

export default MapWidget;
