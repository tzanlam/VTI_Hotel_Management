import mainUrl from "./axiosConfigs"

const AuthService = {
    login(email, password){
        return mainUrl.post(`login?email=${email}&password=${password}`)
    },
    
    register(request){
        return mainUrl.post("register", request)
    },
    
    forgotPassword(email){
        return mainUrl.put("forgotPassword", {
            params: {email}
        })
    },

    confirm(accountId, verticalCode){
        return mainUrl.post("confirm", {
            params: {accountId, verticalCode}
        })
    }
}

export default AuthService;