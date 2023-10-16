import './button.css';
import { api } from '../core/api';
import { useSelector, useDispatch } from 'react-redux';
import { setBranches, clearBranches } from '../store/branchesSlice';
import { selectCurrentLocation } from '../store/locationSlice';

export const Button = () => {
    const branches = useSelector((state) => state.branches);
    const dispatch = useDispatch();
    const currentLocation = useSelector(selectCurrentLocation);
    const radius = 1000;
    const longitude = currentLocation.longitude;
    const latitude = currentLocation.latitude;
    const hasRamp = '';
    const hour = 12;
    const isIndividual = '';

    const takeData = async () => {

        try {
            const response = await api.get('/offices', {
                params: {
                    radius,
                    longitude,
                    latitude,
                    hasRamp,
                    hour,
                    isIndividual,
                },
            });
            dispatch(setBranches(response.data));
            console.log(response.data);

        } catch (error) {
            console.log(error);
        }
    }
    const clearData = () => {
        dispatch(clearBranches());
    }


    return (<>
        <button
            className='btn1'
            onClick={() => {
                takeData();
            }}
        >Get Data</button>
        <button
            className='btn2'
            onClick={() => {
                clearData();
            }}
        >Clear Data</button>
    </>)
}