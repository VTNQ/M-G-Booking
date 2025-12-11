import Image from "next/image";

export default function BestSellingProducts({ className = "" }) {
  return (
    <div className={`p-6 bg-[#eef2ff] rounded-xl shadow-sm ${className}`}>
      <h2 className="text-lg font-semibold">Best Selling Products</h2>
      <p className="text-gray-600 text-sm mb-4">Overview 2024</p>

      <Image
        src="/images/best-product.png"
        width={260}
        height={140}
        alt="Product"
        className="mx-auto"
      />

      <div className="mt-6 p-4 bg-white rounded-xl shadow-sm">
        <div className="mb-5">
          <div className="flex justify-between text-sm font-medium">
            <span>MaterialPro</span>
            <span className="text-[#5d87ff]">55%</span>
          </div>
          <div className="w-full h-2 bg-gray-200 rounded-full mt-2">
            <div className="h-full bg-[#5d87ff] rounded-full" style={{ width: "55%" }} />
          </div>
          <p className="text-gray-500 text-sm mt-1">$23,568</p>
        </div>

        <div>
          <div className="flex justify-between text-sm font-medium">
            <span>Modernize</span>
            <span className="text-[#5d87ff]">65%</span>
          </div>
          <div className="w-full h-2 bg-gray-200 rounded-full mt-2">
            <div className="h-full bg-cyan-400 rounded-full" style={{ width: "65%" }} />
          </div>
          <p className="text-gray-500 text-sm mt-1">$24,468</p>
        </div>
      </div>
    </div>
  );
}
