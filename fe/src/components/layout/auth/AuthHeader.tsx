import Link from 'next/link';
import Image from 'next/image';

export default function AuthHeader() {
    return (
        <header className="p-6">
            <div className="max-w-7xl mx-auto flex items-center justify-between">
                <Link href="/" className="flex items-center space-x-2">
                    <Image
                        src="/logo.png"
                        alt="M&G Booking"
                        width={40}
                        height={40}
                    />
                    <span className="text-2xl font-bold text-indigo-600">
            M&G Booking
          </span>
                </Link>

                <nav className="flex items-center space-x-4">
                    <Link
                        href="/about"
                        className="text-sm text-gray-600 hover:text-gray-900"
                    >
                        About
                    </Link>
                    <Link
                        href="/help"
                        className="text-sm text-gray-600 hover:text-gray-900"
                    >
                        Help
                    </Link>
                </nav>
            </div>
        </header>
    );
}