import mainUrl from "./axiosConfigs"

const AccountService = {
    fetchById(accountId){
        return mainUrl.get(`getAccountById?accountId=${accountId}`)
    }
}

export default AccountService;