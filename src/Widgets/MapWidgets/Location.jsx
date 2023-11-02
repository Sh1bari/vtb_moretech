import { useState, useEffect } from 'react';

const useCurrentLocation = () => {
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

    return currentLocation;
};

export default useCurrentLocation;
