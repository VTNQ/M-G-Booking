import Image from "next/image";

export default function UserCard() {
    return (
        <div className="p-6 bg-white rounded-xl shadow-sm flex items-center gap-4">
        <Image src="/images/avatar.png" width={70} height={70} alt="User" />
  
        <div className="flex-1">
          <h3 className="font-semibold text-lg">Mathew Anderson</h3>
          <p className="text-gray-500 text-sm">22 March, 2025</p>
  
          <div className="flex mt-3 gap-2">
            <Image src="/images/user1.png" width={32} height={32} alt="u1" />
            <Image src="/images/user2.png" width={32} height={32} alt="u2" />
            <Image src="/images/user3.png" width={32} height={32} alt="u3" />
            <Image src="/images/user4.png" width={32} height={32} alt="u4" />
          </div>
        </div>
  
        <button className="w-10 h-10 bg-[#eef2ff] rounded-lg flex items-center justify-center">
          💬
        </button>
      </div>
    )
}