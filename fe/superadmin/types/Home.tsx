export type StatsCardProps={
    title:string;
    value: string | number;
    icon?: string;
    color: string;
}
export type BarChartProps={
    height?:number;
    colors?:string[];
    series?: { name: string; data: number[] }[];
}