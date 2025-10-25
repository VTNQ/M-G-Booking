import { Metadata } from 'next';
import Link from 'next/link';
import ForgotPasswordForm from '../../../components/features/auth/ForgotPasswordForm';

export const metadata: Metadata = {
    title: 'Forgot Password | M&G Booking',
    description: 'Reset your password',
};

export default function ForgotPasswordPage() {
    return (
        <div>
            <div className="mb-6">
                <h1 className="text-3xl font-bold text-gray-900">Forgot password?</h1>
                <p className="mt-2 text-sm text-gray-600">
                    No worries, we&#39;ll send you reset instructions
                </p>
            </div>

            <ForgotPasswordForm />

            <div className="mt-6 text-center">
                <Link
                    href="/login"
                    className="text-sm font-medium text-indigo-600 hover:text-indigo-500 flex items-center justify-center"
                >
                    <svg
                        className="w-4 h-4 mr-2"
                        fill="none"
                        stroke="currentColor"
                        viewBox="0 0 24 24"
                    >
                        <path
                            strokeLinecap="round"
                            strokeLinejoin="round"
                            strokeWidth={2}
                            d="M10 19l-7-7m0 0l7-7m-7 7h18"
                        />
                    </svg>
                    Back to login
                </Link>
            </div>
        </div>
    );
}