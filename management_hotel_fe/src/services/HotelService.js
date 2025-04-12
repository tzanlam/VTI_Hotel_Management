import mainUrl from "./axiosConfigs"

const HotelService = {
    fetchById(hotelId){
        return mainUrl.get(`getHotelById?hotelId=${hotelId}`)
    },
    fetchHotelByAccountId(accountId){
        return mainUrl.get(`getHotelByAccountId?accountId=${accountId}`)
    }
}

export default HotelService;