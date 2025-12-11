import type { NextConfig } from "next";

const nextConfig: NextConfig = {
  /* config options here */
  images: {
    remotePatterns: [
      {
        protocol: "https",
        hostname: "modernize-tailwind-nextjs-main.vercel.app",
      },
    ],
  },
};

export default nextConfig;
