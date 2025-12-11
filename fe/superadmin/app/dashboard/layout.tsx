import NavBar from "@/components/layout/NavBar";
import Siderbar from "@/components/layout/Sidebar";

export default function DashboardLayout({ children }: any) {
    return (
        <div className="flex bg-[#f6f9fc] h-screen overflow-hidden">
            <Siderbar />
            <div className="flex-1 flex flex-col">
                <NavBar />
                <main className="p-6 overflow-auto">{children}</main>
            </div>
        </div>
    )
}