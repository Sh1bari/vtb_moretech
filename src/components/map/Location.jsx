import React from 'react';
import { useSelector, useDispatch } from 'react-redux';
import { Marker, useMapEvents } from 'react-leaflet';
import { setCurrentLocation, selectCurrentLocation } from '../store/locationSlice';


export const LocationMarker = ({
  location,
  setLocation,
}) => {

  const currentLocation = useSelector(selectCurrentLocation);
  const dispatch = useDispatch();

  const map = useMapEvents({
    locationfound: (e) => {
      setLocation(e.latlng);
      map.flyTo(e.latlng, map.getZoom());
    },
  });

  React.useEffect(() => {
    if (!location) {
      map.locate();
    }
    if (location) {
      const latitude = location.lat;
      const longitude = location.lng;
      dispatch(setCurrentLocation({latitude, longitude}));
    }
  }, [location, map]);


  return location === null ? null : <Marker position={location} />;
};