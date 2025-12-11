"use client"

import Image from "next/image"
import { useState } from "react";
import { login } from "./api/auth/route";
import { saveTokens } from "@/utils/auth";

export default function LoginPage() {

  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [errorMsg, setErrorMsg] = useState("");
  const handleLogin =async()=>{
    try{
      setErrorMsg("");

      const res = await login(email,password);
      if(res.data.role){
        setErrorMsg("You do not have permission to access this system.");
        return;
      }
      saveTokens({
        accessToken: res.accessToken,
      });
    }catch(error:any){
      setErrorMsg("Email or password is incorrect.");
      console.error("Login error:", error);
    }
  }
  return (
    <div className="flex min-h-screen bg-[#f6f9fc]">
      <div className="hidden lg:flex w-2/3 bg-[#eef2ff] justify-center items-center relative">
        <div className="max-w-xl">
          <Image
            src="https://modernize-tailwind-nextjs-main.vercel.app/images/backgrounds/authBG.webp"
            width={500}
            height={500}
            alt="Auth Illustration"
            className="object-contain"
            priority
          />
        </div>
        <div className="absolute top-6 left-8 flex items-center gap-2">
          <Image
            src="https://modernize-tailwind-nextjs-main.vercel.app/images/logos/dark-logo.svg"
            width={200}
            height={200}
            alt="Logo"
          />

        </div>
      </div>
      <div className="w-full lg:w-1/3 bg-white flex justify-center items-center px-10">
        <div className="w-full max-w-sm">
          <h2 className="text-2xl font-bold text-gray-900">Welcome to Modernize</h2>
          <p className="text-sm text-[#7c8fac] mb-6 font-medium">Your Admin Dashboard</p>
          {errorMsg && (
            <p className="text-red-600 text-sm mb-3">{errorMsg}</p>
          )}

          <div className="space-y-5">
            <div>
              <label className="text-sm font-semibold text-[#1c2536]">
                Username
              </label>
              <input
                type="text"
                value={email}
                onChange={e => setEmail(e.target.value)}
                placeholder="Enter your username"
                className="w-full mt-1 border border-[#e5e5e5] rounded-lg px-3 py-2 outline-none focus:right-2 focus:border-[#5d87ff]"
              />
            </div>

            <div>
              <label className="text-sm font-semibold text-[#1c2536]">
                Password
              </label>
              <input
                type="password"
                value={password}
                onChange={e => setPassword(e.target.value)}
                placeholder="Enter your password"
                className="w-full mt-1 border border-[#e5e5e5] rounded-lg px-3 py-2 outline-none focus:right-2 focus:border-[#5d87ff]"
              />

            </div>
            <div className="flex justify-between items-center text-sm">
              <label className="flex items-center gap-2">
                <input type="checkbox" />
                Remember this Device
              </label>
              <a href="#" className="text-blue-600 hover:underline">
                Forgot Password?
              </a>
            </div>
            <button className="w-full bg-[#5d87ff] text-white py-2 rounded-lg 
            hover:bg-[color-mix(in_oklab,#5d87ff_80%,black)]
            cursor-pointer
            transition"
            onClick={handleLogin}
            >
              Sign in
            </button>
          </div>
          <p className="text-center text-sm mt-6 text-gray-600">
            New to Modernize?{" "}
            <a href="#" className="text-blue-600 ">
              Create an account
            </a>
          </p>
        </div>
      </div>
    </div>
  )
}