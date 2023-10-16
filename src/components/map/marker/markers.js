import L from 'leaflet';
import pngMarker from '../../../../public/marker_icon.png';
import pngBank from '../../../../public/marker_bank.png';
import pngAtm from '../../../../public/marker_atm.png';


export const iconMarker = new L.Icon({
    iconUrl: pngMarker,
    iconRetinaUrl: pngMarker,
    iconSize: new L.Point(30, 38),
});
export const bankMarker = new L.Icon({
    iconUrl: pngBank,
    iconRetinaUrl: pngBank,
    iconSize: new L.Point(30, 38),
});
export const atmMarker = new L.Icon({
    iconUrl: pngAtm,
    iconRetinaUrl: pngAtm,
    iconSize: new L.Point(30, 38),
});