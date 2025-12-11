import { BASE_API } from "@/constants/app";
import { getTokens } from "@/utils/auth";
import axios, { InternalAxiosRequestConfig } from "axios";

declare module "axios"{
    export interface AxiosRequestConfig {
        skipAuth?: boolean;
    
    }
}

const Http = axios.create({
    baseURL:BASE_API,
    withCredentials:true,
    timeout:15000
})
Http.interceptors.request.use(
    (config: InternalAxiosRequestConfig) => {
      const token = getTokens()?.accessToken;
  
      if (!config.skipAuth && token && token !== "undefined") {
        config.headers = config.headers || {};
        config.headers.Authorization = `Bearer ${token}`;
      }
  
      return config;
    },
    (error) => Promise.reject(error)
  );
  Http.interceptors.response.use(
    (response) => response,
    (error) => {
      const status = error?.response?.status;
      const message = error?.response?.data;
  
      if (
        status === 401 &&
        (message === "Token has expired" ||
          message === "Invalid JWT token")
      ) {
        if (typeof window !== "undefined") {
          localStorage.removeItem("token");
          window.location.href = "/login";
        }
      }
  
      return Promise.reject(error);
    }
  );
  export default Http;