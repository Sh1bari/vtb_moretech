import { createSlice } from '@reduxjs/toolkit';

const locationSlice = createSlice({
  name: 'location',
  initialState: {
    latitude: 0,
    longitude: 0,
  }, // Инициализируйте с null или начальным значением
  reducers: {
    setCurrentLocation: (state, action) => {
      state.latitude = action.payload.latitude;
      state.longitude = action.payload.longitude;
    },
  },
});

export const { setCurrentLocation } = locationSlice.actions;
export const selectCurrentLocation = (state) => state.location; // Селектор для получения текущего местоположения

export default locationSlice.reducer;