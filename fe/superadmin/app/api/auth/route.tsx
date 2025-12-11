import Http from "@/libs/http"

export const login = async(email:string,password:string)=>{
    const response = await Http.post(
        "/v1/auth/login",
        {email,password},
        { skipAuth: true, withCredentials: true }
    )
    return response.data;
}