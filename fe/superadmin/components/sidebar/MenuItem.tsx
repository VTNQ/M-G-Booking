import { ChevronDownIcon } from "../icons";

export default function MenuItem({ icon: Icon, label, active, dropdown }: any) {
    return (
        <div
            className={`flex items-center justify-between px-2 py-2 rounded-md cursor-pointer
            text-sm mb-1 ${active ? "bg-[#5d87ff] text-white" : "text-gray-700 hover:bg-gray-50"
                }`}
        >
            <div className="flex items-center gap-3">
                <Icon className="w-5 h-5" />
                <span>{label}</span>
            </div>
            {dropdown && <ChevronDownIcon className="w-4 h-4" />}
        </div>
    )
}