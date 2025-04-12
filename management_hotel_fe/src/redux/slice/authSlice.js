    import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
    import AuthService from "../../services/AuthService";
    import { message } from "antd";

    export const login = createAsyncThunk("auth/login", async ({ email, password }, { rejectWithValue }) => {
        try {
            const response = await AuthService.login(email, password);
            return response.data;
        } catch (err) {
            message.error("Đăng nhập thất bại");
            return rejectWithValue(err.response.data);
        }
    });

    export const register = createAsyncThunk("auth/register", async (request, { rejectWithValue }) => {
        try {
            const response = await AuthService.register(request);
            return response.data;
        } catch (error) {
            message.error("Đăng kí thất bại");
            return rejectWithValue(error);
        }
    });

    export const forgotPassword = createAsyncThunk("auth/forgotPassword", async ({ email }, { rejectWithValue }) => {
        try {
            const response = await AuthService.forgotPassword(email);
            return response;
        } catch (error) {
            message.error("Xảy ra lỗi");
            return rejectWithValue(error);
        }
    });

    export const confirm = createAsyncThunk("auth/confirm", async ({ email, verticalCode }, { rejectWithValue }) => {
        try {
            const response = await AuthService.confirm(email, verticalCode);
            return response.data;
        } catch (err) {
            message.error("Xảy ra lỗi");
            return rejectWithValue(err);
        }
    });

    const authSlice = createSlice({
        name: "auth",
        initialState: {
            account: null,
            isAuthenticated: false,
            loading: false,
            error: null
        },
        reducers: {
            logout: (state) => {
                state.account = null;
                state.isAuthenticated = false;
                localStorage.removeItem("accountId");
                localStorage.removeItem("fullName");
                localStorage.removeItem("token");
                localStorage.removeItem("hotelId");
                message.success("Đăng xuất thành công");
            }
        },
        extraReducers: (builder) => {
            builder
                .addCase(login.pending, (state) => {
                    state.loading = true;
                })
                .addCase(login.fulfilled, (state, action) => {
                    state.account = action.payload;
                    state.isAuthenticated = true;
                    state.loading = false;
                    localStorage.setItem("accountId", action.payload.accountId);
                    localStorage.setItem("fullName", action.payload.fullName);
                    localStorage.setItem("token", action.payload.token);
                })
                .addCase(login.rejected, (state, action) => {
                    state.loading = false;
                    state.error = action.payload;
                })
                .addCase(register.pending, (state) => {
                    state.loading = true;
                })
                .addCase(register.fulfilled, (state) => {
                    state.loading = false;
                })
                .addCase(register.rejected, (state, action) => {
                    state.loading = false;
                    state.error = action.payload;
                })
                .addCase(forgotPassword.pending, (state) => {
                    state.loading = true;
                })
                .addCase(forgotPassword.fulfilled, (state) => {
                    state.loading = false;
                })
                .addCase(forgotPassword.rejected, (state, action) => {
                    state.loading = false;
                    state.error = action.payload;
                })
                .addCase(confirm.pending, (state) => {
                    state.loading = true;
                })
                .addCase(confirm.fulfilled, (state) => {
                    state.loading = false;
                })
                .addCase(confirm.rejected, (state, action) => {
                    state.loading = false;
                    state.error = action.payload;
                });
        }
    });

    export const { logout } = authSlice.actions;
    export default authSlice.reducer;
