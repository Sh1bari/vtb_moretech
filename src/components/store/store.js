import { configureStore } from '@reduxjs/toolkit';
import branchesReducer from './branchesSlice';
import locationReducer from './locationSlice';

const store = configureStore({
  reducer: {
    branches: branchesReducer,
    location: locationReducer,
  },
});

export default store;