import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import AccountService from "../../services/AccountService";
import { message } from "antd";

export const fetchAccountById = createAsyncThunk("account/fetchById", async (accountId, { rejectWithValue }) => {
    try {
        console.log("fetching data...");
        const response = await AccountService.fetchById(accountId);
        message.success("Xem thông tin cá nhân thành công");
        return response.data;
    } catch (err) {
        message.error("Xem thông tin cá nhân thất bại");
        return rejectWithValue(err);
    }
});

const accountSlice = createSlice({
    name: "account",
    initialState: {
        account: null,
        loading: false,
        error: null
    },
    reducers: {},
    extraReducers: (builder) => {
        builder
            .addCase(fetchAccountById.pending, (state) => {
                state.loading = true;
                state.error = null;
            })
            .addCase(fetchAccountById.fulfilled, (state, action) => {
                state.loading = false;
                state.account = action.payload;
            })
            .addCase(fetchAccountById.rejected, (state, action) => {
                state.loading = false;
                state.error = action.payload;
            });
    }
});

export default accountSlice.reducer;
