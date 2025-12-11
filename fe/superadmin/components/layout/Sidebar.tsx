import Image from "next/image";
import { MENU_APPS, MENU_HOME } from "../sidebar/menu.config";
import MenuItem from "../sidebar/MenuItem";

export default function Siderbar() {
    return (
        <aside className="w-64 h-screen 
        bg-white border-r border-gray-200 flex flex-col p-4">
            <div className="flex items-center gap-2 px-2 mb-8">
                <Image
                    src="https://modernize-tailwind-nextjs-main.vercel.app/images/logos/dark-logo.svg"
                    width={140}
                    height={60}
                    alt="Modernize"
                />
            </div>
            <h3 className="text-xs font-semibold text-gray-500 px-2 mb-2">HOME</h3>
            {MENU_HOME.map((item, i) => (
                <MenuItem key={i} {...item} />
            ))}
            <h3 className="text-xs font-semibold text-gray-500 mt-6 px-2 mb-2">APPS</h3>
            {MENU_APPS.map((item, i) => (
                <MenuItem key={i} {...item} />
            ))}
            <div className="mt-auto">
                <div className="bg-[#e8f1ff] p-3 rounded-lg flex items-center gap-3">
                    <Image
                        src="https://modernize-tailwind-nextjs-main.vercel.app/images/profile/user-1.jpg"
                        width={45}
                        height={45}
                        className="rounded-full"
                        alt="User"
                    />
                    <div>
                        <p className="font-semibold text-sm">Mathew</p>
                        <p className="text-xs text-gray-500">Designer</p>
                    </div>
                    <div className="ml-auto text-blue-600 cursor-pointer">⏻</div>
                </div>
            </div>
        </aside>
    )
}