"use client"
import dynamic from "next/dynamic";

const Chart = dynamic(() => import("react-apexcharts"), { ssr: false });
export default function LineChart(){
    const series = [
        {
          name: "Earnings",
          data: [100, 250, 180, 300, 210, 320, 280],
        },
      ];
    
      const options = {
        chart: {
          toolbar: { show: false },
        },
        stroke: {
          curve: "smooth" as const,
          width: 4,
          colors: ["#5d87ff"],
        },
        fill: {
          type: "gradient",
          gradient: {
            shadeIntensity: 1,
            opacityFrom: 0.4,
            opacityTo: 0,
            stops: [0, 90, 100],
          },
        },
        dataLabels: { enabled: false },
        grid: { borderColor: "#f1f1f1" },
        xaxis: {
          categories: ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul"],
        },
        yaxis: { show: false },
      };

      return <Chart options={options} series={series} type="line" height={200}/>
}