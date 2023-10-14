import React from 'react';
import { YMaps, Map, Placemark, Clusterer } from '@pbe/react-yandex-maps';
import './input.css';

const mapStyles = {
  width: '90%',
  height: '90vh',
};

function MyMap() {
  const clusterPoints = [
    [55.76, 37.64],
    [55.78, 37.61],
    [55.78, 37.60],
    [55.78, 37.65],
    [55.78, 37.70],
  ];

  return (
    <YMaps
      query={{
        ns: "use-load-option",
        load: "Map,Placemark,control.ZoomControl,control.FullscreenControl,geoObject.addon.balloon",
      }}
    >
      <Map style={mapStyles}
        defaultState={{
          center: [55.75, 37.57],
          zoom: 10,
          controls: ["zoomControl", "fullscreenControl"],
        }}
      >
        <Clusterer
          options={{
            preset: "islands#invertedVioletClusterIcons",
            groupByCoordinates: false,
          }}
        >
          {clusterPoints.map((coordinates, index) => (
            <Placemark key={index} geometry={coordinates} />
          ))}
        </Clusterer>
      </Map>
    </YMaps>
  );
}

export default MyMap;
