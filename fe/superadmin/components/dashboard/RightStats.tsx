"use client";
import dynamic from "next/dynamic";
import { ApexOptions } from "apexcharts";
import { CurrencyDollarIcon } from "../icons";

const Chart = dynamic(() => import("react-apexcharts"), { ssr: false });

export default function RightStats() {
  const donutOptions: ApexOptions = {
    chart: { type: "donut" },
    labels: ["2023", "2024", "2025"],
    colors: ["#5d87ff", "#e2e8ff", "#b4c3ff"],
    legend: { show: false },
    stroke: { width: 0 },
    dataLabels: { enabled: false },
    plotOptions: { pie: { donut: { size: "70%" } } },
  };

  const lineOptions: ApexOptions = {
    chart: { type: "line", toolbar: { show: false } },
    stroke: { curve: "smooth", width: 3, colors: ["#5d87ff"] },
    grid: { borderColor: "#eef2f6" },
    dataLabels: { enabled: false },
    xaxis: { categories: ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul"] },
    yaxis: { show: false }
  };

  return (
    <div className="space-y-6">

      {/* Donut */}
      <div className="p-6 bg-white rounded-xl shadow-sm">
        <h2 className="text-lg font-semibold">Yearly Breakup</h2>
        <p className="text-gray-600">$36,358</p>

        <div className="mt-4 w-40 mx-auto">
          <Chart series={[44, 32, 24]} options={donutOptions} type="donut" height={220} />
        </div>
      </div>

      {/* Monthly Earnings */}
      <div className="p-6 bg-white rounded-xl shadow-sm">
        <div className="flex justify-between items-center">
          <h2 className="text-lg font-semibold">Monthly Earnings</h2>
          <div className="w-10 h-10 bg-[#5d87ff] rounded-full flex items-center justify-center">
            <CurrencyDollarIcon className="w-6 h-6 text-white" />
          </div>
        </div>

        <p className="text-2xl font-semibold mt-2">$6,820</p>
        <p className="text-sm text-red-500">↓ 9% last year</p>

        <Chart
          series={[{ name: "Earnings", data: [10, 35, 22, 55, 40, 65, 52] }]}
          options={lineOptions}
          type="line"
          height={180}
        />
      </div>
    </div>
  );
}
