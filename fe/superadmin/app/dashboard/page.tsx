import ChartCard from "@/components/dashboard/ChartCard";
import RightStats from "@/components/dashboard/RightStats";
import StatsCard from "@/components/dashboard/StatsCard";

import EmployeeSalaryCard from "@/components/dashboard/EmployeeSalaryCard";
import MiniCard from "@/components/dashboard/MiniCard";
import UserCard from "@/components/dashboard/UserCard";
import BestSellingProducts from "@/components/dashboard/BestSellingProducts";
import LineChart from "@/components/dashboard/charts/LineChart";
import BarChart from "@/components/dashboard/charts/BarChart";
import WeeklyStatsCard from "@/components/dashboard/WeeklyStatsCard";
import TopPerformers from "@/components/dashboard/TopPerformers";

export default function DashboardHome() {
    return (
        <div className="space-y-8">

            {/* Top Stats */}
            <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-5 gap-4">
                <StatsCard title="Products" value="$96k" color="#fff6e5" />
                <StatsCard title="Followers" value="96" color="#eef2ff" />
                <StatsCard title="Blogs" value="696" color="#ffecec" />
                <StatsCard title="Invoices" value="59" color="#eef2ff" />
                <StatsCard title="Chats" value="3,560" color="#e7ffe8" />
            </div>

            {/* Chart + Right */}
            <div className="grid grid-cols-1 lg:grid-cols-3 gap-6">
                <div className="lg:col-span-2">
                    <ChartCard  />
                </div>
                <RightStats />
            </div>

            {/* SECOND ROW — đúng chuẩn */}
            <div className="grid grid-cols-1 lg:grid-cols-4 gap-6">

                {/* LEFT COLUMN (2 ROWS) */}
                <div className="lg:col-span-3 space-y-6">

                    {/* Row 1 */}
                    <div className="grid grid-cols-1 lg:grid-cols-3 gap-6">
                        <EmployeeSalaryCard />
                        <MiniCard title="Customers" value="36,358" percent={9} chartType="line" />
                        <MiniCard title="Projects" value="78,298" percent={9} chartType="bar" />
                    </div>

                    {/* Row 2 */}
                    <UserCard />

                </div>

                {/* RIGHT COLUMN (GIỮA 2 HÀNG) */}
                <BestSellingProducts className="lg:row-span-2" />
              

            </div>
            <div className="grid grid-cols-2 lg:grid-cols-2 gap-6">
                    <WeeklyStatsCard />
                    <TopPerformers />
                </div>
        </div>

    );
}
