import dynamic from "next/dynamic";

const Chart = dynamic(() => import("react-apexcharts"), { ssr: false });
export default function DonutChart(){
    const series = [44,55,41];

    const options = {
        colors: ["#5d87ff", "#49beff", "#e8efff"],
        labels: ["2023", "2024", "2025"],
        legend: { show: false },
        stroke: { width: 0 },
        dataLabels: { enabled: false },
    }
    return <Chart options={options} series={series} type="donut" height={260}/>
}