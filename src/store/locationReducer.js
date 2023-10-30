const initialState = {
    currentLocation: null,
};

const locationReducer = (state = initialState, action) => {
    switch (action.type) {
        case 'SET_LOCATION':
            return {
                ...state,
                currentLocation: action.payload,
            };
        default:
            return state;
    }
};

export const setLocation = (location) => ({
    type: 'SET_LOCATION',
    payload: location,
});

export default locationReducer;
