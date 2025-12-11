"use client";

import dynamic from "next/dynamic";
const Chart = dynamic(() => import("react-apexcharts"), { ssr: false });

export default function WeeklyStatsCard() {
  const series = [
    {
      name: "Sales",
      data: [10, 35, 22, 55, 40, 65, 52],
    },
  ];

  const options = {
    chart: { type: "line" as const, toolbar: { show: false } },
    stroke: { curve: "smooth" as const, width: 3, colors: ["#5d87ff"] },
    fill: {
      type: "gradient",
      gradient: {
        shadeIntensity: 1,
        opacityFrom: 0.4,
        opacityTo: 0,
      },
    },
    dataLabels: { enabled: false },
    xaxis: {
      categories: ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul"],
      labels: { style: { colors: "#94a3b8" } },
    },
    yaxis: { show: false },
    grid: { borderColor: "#f1f5f9" },
  };

  return (
    <div className="p-6 bg-white rounded-xl shadow-sm space-y-4">

      <h2 className="text-lg font-semibold">Weekly Stats</h2>
      <p className="text-gray-500 text-sm -mt-2">Average sales</p>

      {/* Chart */}
      <Chart options={options} series={series} type="line" height={220} />

      {/* Summary Items */}
      <div className="space-y-4">
        
        {/* Top Sales */}
        <div className="flex justify-between items-center p-3 rounded-xl bg-[#f8faff]">
          <div className="flex items-center gap-3">
            <div className="w-10 h-10 rounded-xl bg-[#eef2ff] grid place-items-center">
              <span className="text-blue-500 text-xl">▦</span>
            </div>
            <div>
              <p className="font-semibold">Top Sales</p>
              <p className="text-sm text-gray-500">Johnathan Doe</p>
            </div>
          </div>
          <span className="text-blue-500 font-semibold">+68</span>
        </div>

        {/* Best Seller */}
        <div className="flex justify-between items-center p-3 rounded-xl bg-[#f8fff9]">
          <div className="flex items-center gap-3">
            <div className="w-10 h-10 rounded-xl bg-[#e6ffed] grid place-items-center">
              <span className="text-green-500 text-xl">▦</span>
            </div>
            <div>
              <p className="font-semibold">Best Seller</p>
              <p className="text-sm text-gray-500">MaterialPro Admin</p>
            </div>
          </div>
          <span className="text-green-500 font-semibold">+68</span>
        </div>

        {/* Most Commented */}
        <div className="flex justify-between items-center p-3 rounded-xl bg-[#fff5f5]">
          <div className="flex items-center gap-3">
            <div className="w-10 h-10 rounded-xl bg-[#ffecec] grid place-items-center">
              <span className="text-red-500 text-xl">▦</span>
            </div>
            <div>
              <p className="font-semibold">Most Commented</p>
              <p className="text-sm text-gray-500">Ample Admin</p>
            </div>
          </div>
          <span className="text-red-500 font-semibold">+68</span>
        </div>

      </div>

    </div>
  );
}
