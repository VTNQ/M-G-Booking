import Image from "next/image";
import { BellIcon, ChevronDownIcon, MagnifyingGlassIcon } from "../icons";

export default function NavBar() {
    return (
        <nav className="h-16 bg-white  px-6 flex 
        items-center justify-between ">
            <div className="flex items-center gap-5">
                <MagnifyingGlassIcon className="w-5 text-gray-600" />
                <div className="flex items-center gap-1 cursor-pointer text-[#2a3547]">
                    Apps <ChevronDownIcon className="w-4" />
                </div>
                <span className="cursor-pointer text-[#2a3547]">Chat</span>
                <span className="cursor-pointer text-[#2a3547]">Calendar</span>
                <span className="cursor-pointer text-[#2a3547]">Email</span>
            </div>
            <div className="flex items-center gap-6">
                GB
                <BellIcon className="w-5 text-gray-700 cursor-pointer" />
                <Image
                    src="https://modernize-tailwind-nextjs-main.vercel.app/images/profile/user-1.jpg"
                    width={35}
                    height={35}
                    className="rounded-full cursor-pointer"
                    alt="user"
                />
            </div>
        </nav>
    )
}