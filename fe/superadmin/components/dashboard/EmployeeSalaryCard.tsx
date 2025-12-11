"use client";
import dynamic from "next/dynamic";
import { ApexOptions } from "apexcharts";
const Chart = dynamic(() => import("react-apexcharts"), { ssr: false });

export default function EmployeeSalaryCard() {
  const options: ApexOptions = {
    chart: { type: "bar", toolbar: { show: false } },
    plotOptions: { bar: { borderRadius: 6, columnWidth: "40%" } },
    colors: ["#5d87ff"],
    dataLabels: { enabled: false },
    grid: { borderColor: "#eef2f6" },
    xaxis: { categories: ["Apr", "May", "Jun", "Jul", "Aug", "Sept", "Oct"] },
    yaxis: { show: false }
  };

  return (
    <div className="p-6 bg-white rounded-xl shadow-sm">
      <h2 className="font-semibold text-lg">Employee Salary</h2>
      <p className="text-gray-500 text-sm mb-4">Every month</p>

      <Chart series={[{ name: "Salary", data: [30, 22, 45, 18, 12, 38, 26] }]}
             options={options}
             type="bar"
             height={240}
      />

      <div className="flex justify-between mt-4 text-sm">
        <div>
          <p className="flex gap-2 items-center">
            <span className="w-3 h-3 bg-[#eef2ff] rounded-sm"></span> Salary
          </p>
          <p className="font-semibold">$36,358</p>
        </div>

        <div>
          <p className="flex gap-2 items-center">
            <span className="w-3 h-3 bg-[#eef2ff] rounded-sm"></span> Profit
          </p>
          <p className="font-semibold">$5,296</p>
        </div>
      </div>
    </div>
  );
}
