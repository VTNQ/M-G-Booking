import { Squares2X2Icon } from "../icons";
import BarChart from "./charts/BarChart";

export default function ChartCard() {
  return (
    <div className="p-6 bg-white rounded-xl shadow-sm flex flex-col lg:grid lg:grid-cols-3 gap-6">

      {/* LEFT */}
      <div className="lg:col-span-2">
        <h2 className="text-xl font-semibold">Revenue Updates</h2>
        <p className="text-gray-500 text-sm mb-4">Overview of profit</p>

        <BarChart height={520} />
      </div>

      {/* RIGHT */}
      <div className="flex flex-col justify-between">
        <div className="flex justify-end">
          <select className="border rounded-lg px-3 py-2 text-sm border-[#e5e5e5]">
            <option>Year 2025</option>
            <option>Year 2024</option>
            <option>Year 2023</option>
          </select>
        </div>

        <div className="flex gap-3 items-start">
          <div className="p-3 bg-[#eef2ff] rounded-lg">
            <Squares2X2Icon className="w-6 h-6 text-[#5d87ff]" />
          </div>

          <div>
            <p className="text-3xl font-bold">$63,489</p>
            <p className="text-gray-500 text-sm">Total Earnings</p>
          </div>
        </div>

        <div className="space-y-4">
          <div>
            <p className="flex gap-2 items-center">
              <span className="w-3 h-3 bg-blue-600 rounded-full"></span>
              <span className="text-gray-600 text-sm">Earnings this month</span>
            </p>
            <p className="font-semibold">$48,820</p>
          </div>

          <div>
            <p className="flex gap-2 items-center">
              <span className="w-3 h-3 bg-cyan-400 rounded-full"></span>
              <span className="text-gray-600 text-sm">Expense this month</span>
            </p>
            <p className="font-semibold">$26,498</p>
          </div>
        </div>

        <button className="mt-5 bg-[#5d87ff] text-white py-3 rounded-lg">
          View Full Report
        </button>
      </div>
    </div>
  );
}
