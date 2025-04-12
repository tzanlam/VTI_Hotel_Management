import { configureStore } from "@reduxjs/toolkit"
import authReducer from "./slice/authSlice"
import accountReducer from "./slice/accountSlice"
import hotelReducer from "./slice/hotelSlice"

const store = configureStore({
    reducer: {
        auth: authReducer,
        account: accountReducer,
        hotel: hotelReducer
    }
})

export default store;