import { StatsCardProps } from "@/types/Home";
import Image from "next/image";

export default function StatsCard({ title, value, icon, color }: StatsCardProps) {
    return (
        <div
            className={`p-5 rounded-xl shadow-sm   flex flex-col gap-3`}
            style={{ backgroundColor: color }}
        >
            {icon && <Image src={icon} width={50} height={50} alt={title} />}
            <div className="text-gray-700 font-medium text-sm">{title}</div>
            <div className="text-2xl font-bold">{value}</div>
        </div>
    )
}