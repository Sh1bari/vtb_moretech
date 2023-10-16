import { Marker } from 'react-leaflet';
import { iconMarker, bankMarker, atmMarker } from './markers';

export const MapMarker = ({
    position,
    onClick,
    type,
}) => {
    return (
        <Marker
            position={position}
            icon={
                type === 'atm'
                  ? atmMarker
                  : type === 'bank'
                  ? bankMarker
                  : iconMarker
              }
            eventHandlers={{
                click: onClick,
            }}
        />
    );
};