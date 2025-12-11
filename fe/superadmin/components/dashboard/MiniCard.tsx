export default function MiniCard({ title, value, percent, chart }: any) {
    return (
      <div className="p-6 bg-white rounded-xl shadow-sm">
        <p className="text-sm text-gray-500">{title}</p>
        <p className="text-2xl font-bold">{value}</p>
        <p className={`text-sm font-medium ${percent >= 0 ? "text-green-500" : "text-red-500"}`}>
          {percent >= 0 ? `↑ ${percent}%` : `↓ ${percent}%`}
        </p>
  
        <div className="mt-3">{chart}</div>
      </div>
    );
  }
  