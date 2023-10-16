import { createSlice } from '@reduxjs/toolkit';

const branchesSlice = createSlice({
    name: 'branches',
    initialState: [],
    reducers: {
        setBranches: (state, action) => {
            return action.payload; // action.payload должен быть массивом с данными о филиалах
        },
        clearBranches: (state) => {
            return []; // Этот селектор очищает данные о филиалах
        },
    },
});

export const { setBranches, clearBranches  } = branchesSlice.actions;

export const selectBranches = (state) => state.branches; // Создайте селектор

export default branchesSlice.reducer;