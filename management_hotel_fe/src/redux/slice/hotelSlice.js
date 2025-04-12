import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import { message } from "antd";
import HotelService from "../../services/HotelService";

export const fetchHotelById = createAsyncThunk("hotel/fetchHotelById", async (hotelId, { rejectWithValue }) => {
    try {
        const response = await HotelService.fetchById(hotelId);
        console.log(response.data);
        
        return response.data
    } catch (error) {
        return rejectWithValue(error.response?.data || error.message);
    }
});

export const fetchHotelByAccountId = createAsyncThunk("hotel/fetchHotelByAccountId", async (accountId, { rejectWithValue }) => {
    try {
        const response = await HotelService.fetchHotelByAccountId(accountId);
        console.log(response.data);
        
        return response.data;
    } catch (error) {
        console.log(error);
        
        return rejectWithValue(error.response?.data || error.message);
    }
});

const hotelSlice = createSlice({
    name: "hotel",
    initialState: {
        hotel: [],
        loading: false,
        error: null
    },
    reducers: {},
    extraReducers: (builder) => {
        builder
            .addCase(fetchHotelById.pending, (state) => {
                state.loading = true;
                state.error = null;
            })
            .addCase(fetchHotelById.fulfilled, (state, action) => {
                state.loading = false;
                state.hotel = action.payload;
                message.success("Xem thông tin khách sạn thành công!");
            })
            .addCase(fetchHotelById.rejected, (state, action) => {
                state.loading = false;
                state.error = action.payload;
                message.error("Xem thông tin khách sạn thất bại!");
            })

            // xem danh sách bằng accountID
            .addCase(fetchHotelByAccountId.pending, (state) => {
                state.loading = true;
                state.error = null;
            })
            .addCase(fetchHotelByAccountId.fulfilled, (state, action) => {
                state.loading = false;
                state.hotel = action.payload;
                message.success("Lấy danh sách khách sạn thành công!");
            })
            .addCase(fetchHotelByAccountId.rejected, (state, action) => {
                state.loading = false;
                state.error = action.payload;
            });
    }
});

export default hotelSlice.reducer;
