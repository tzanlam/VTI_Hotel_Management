import { createBrowserRouter } from "react-router-dom";
import RoomLayout from "../pages/BookedManage/RoomLayout";
import LoginPage from "../pages/LoginPage"
import PageError from "../pages/configPage/PageError";
import MainLayout from "../components/MainLayout";
import Information from "../pages/Account/Information";
import MyHotelPage from "../pages/MyHotelPage";
export const router = createBrowserRouter([
    {
        path: "/",
        element: <LoginPage />
    },
    {
        path: "/myHotel",
        element: <MyHotelPage /> 
    },
    {
        path: "/home",
        element: <MainLayout />,
        children: [{
            path: "room",
            element: <RoomLayout />
        },
        {
            path: "infor/:accountId",
            element: <Information />
        }]
    },
    {
        path: "/error",
        element: <PageError />
    },
    {
        path: "/infor/:accountId",
        element: <Information />
    }
])