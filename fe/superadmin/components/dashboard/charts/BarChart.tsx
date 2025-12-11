"use client"
import { BarChartProps } from "@/types/Home";
import { ApexOptions } from "apexcharts";
import dynamic from "next/dynamic";
import { useState } from "react";
const Chart = dynamic(() => import("react-apexcharts"), { ssr: false });
export default function BarChart({
    height = 280,
    colors = ["#5d87ff", "#49beff"],
    series = [
      {
        name: "Earnings",
        data: [1400, 2500, 2100, 3000, 1500, 1100, 1600],
      },
      {
        name: "Expense",
        data: [-1800, -1500, -2500, -1700, -900, -1400, -1200],
      },
    ],
  }: BarChartProps) {
    const options: ApexOptions = {
      chart: {
        type: "bar",
        toolbar: { show: false },
      },
  
      colors,
  
      plotOptions: {
        bar: {
          borderRadius: 6,
          columnWidth: "40%",
        },
      },
  
      dataLabels: { enabled: false },
  
      stroke: {
        width: 2,
        colors: ["transparent"],
      },
  
      xaxis: {
        categories: ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul"],
        labels: {
          style: { colors: "#94a3b8" }, // slate-400
        },
      },
  
      yaxis: { show: false },
  
      grid: {
        borderColor: "#e5e7eb", // gray-300
      },
  
      legend: { show: false },
    };
  
    return (
      <Chart options={options} series={series} type="bar" height={height} />
    );
  }