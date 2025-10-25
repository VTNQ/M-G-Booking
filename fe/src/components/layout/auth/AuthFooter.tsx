export default function AuthFooter() {
    return (
        <footer className="p-6 border-t border-gray-200 bg-white">
            <div className="max-w-7xl mx-auto text-center text-sm text-gray-600">
                <p>&copy; 2024 M&G Booking. All rights reserved.</p>
                <div className="mt-2 space-x-4">
                    <a href="/privacy" className="hover:text-gray-900">Privacy Policy</a>
                    <a href="/terms" className="hover:text-gray-900">Terms of Service</a>
                    <a href="/contact" className="hover:text-gray-900">Contact</a>
                </div>
            </div>
        </footer>
    );
}