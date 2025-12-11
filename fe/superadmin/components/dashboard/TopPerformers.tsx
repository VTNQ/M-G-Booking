import Image from "next/image";

const users = [
  { name: "Sunil Joshi", role: "Web Designer", project: "Elite Admin", priority: "Low", budget: "3.9k", avatar: "https://modernize-tailwind-nextjs-main.vercel.app/images/profile/user-1.jpg" },
  { name: "John Deo", role: "Web Developer", project: "Flexy Admin", priority: "Medium", budget: "24.5k", avatar: "https://modernize-tailwind-nextjs-main.vercel.app/images/profile/user-2.jpg" },
  { name: "Nirav Joshi", role: "Web Manager", project: "Material Pro", priority: "High", budget: "12.8k", avatar: "https://modernize-tailwind-nextjs-main.vercel.app/images/profile/user-3.jpg" },
  { name: "Yuvraj Sheth", role: "Project Manager", project: "Xtreme Admin", priority: "Low", budget: "4.8k", avatar: "https://modernize-tailwind-nextjs-main.vercel.app/images/profile/user-4.jpg" },
  { name: "Micheal Doe", role: "Content Writer", project: "Helping Hands WP Theme", priority: "High", budget: "9.3k", avatar: "https://modernize-tailwind-nextjs-main.vercel.app/images/profile/user-5.jpg" },
];

export default function TopPerformers() {
  return (
    <div className="p-6 bg-white rounded-xl shadow-sm overflow-x-auto">

      <div className="flex justify-between items-center mb-4">
        <div>
          <h2 className="text-lg font-semibold">Top Performers</h2>
          <p className="text-sm text-gray-500">Best employees</p>
        </div>

        <input
          placeholder="Search User..."
          className="border px-4 py-2 rounded-lg text-sm w-60 border-[#e5e5e5] focus:border-[#5d87ff] outline-none focus:right-2"
        />
      </div>

      <table className="w-full text-left">
        <thead>
          <tr className="text-gray-500 text-sm border-b border-[#e5e5e5]">
            <th className="py-3">Assigned</th>
            <th>Project</th>
            <th>Priority</th>
            <th>Budget</th>
          </tr>
        </thead>

        <tbody className="text-sm">
          {users.map((u, index) => (
            <tr key={index} className="border-b last:border-none border-[#e5e5e5]">
              <td className="py-4 flex items-center gap-3">
                <Image src={u.avatar} width={40} height={40} alt={u.name} className="rounded-full" />
                <div>
                  <p className="font-semibold">{u.name}</p>
                  <p className="text-gray-500 text-sm">{u.role}</p>
                </div>
              </td>

              <td>{u.project}</td>

              <td>
                <span className={`px-3 py-1 rounded-full text-xs font-medium
                  ${u.priority === "Low" ? "bg-blue-100 text-blue-600" : ""}
                  ${u.priority === "Medium" ? "bg-amber-100 text-amber-600" : ""}
                  ${u.priority === "High" ? "bg-red-100 text-red-600" : ""}
                `}>
                  {u.priority}
                </span>
              </td>

              <td>{u.budget}</td>
            </tr>
          ))}
        </tbody>
      </table>

    </div>
  );
}
