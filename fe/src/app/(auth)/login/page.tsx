import { Metadata } from 'next';
import Link from 'next/link';
import LoginForm from '../../../components/features/auth/LoginForm';

export const metadata: Metadata = {
    title: 'Login - M&G Booking',
    description: 'Login to your account',
};

export default function LoginPage() {
    return (
        <div className="space-y-6">
            {/* Title */}
            <div className="text-center">
                <h1 className="text-3xl font-bold text-gray-900">Welcome Back</h1>
                <p className="mt-2 text-sm text-gray-600">
                    Sign in to your account to continue
                </p>
            </div>

            {/* Login Form */}
            <LoginForm />

            {/* Divider */}
            <div className="relative">
                <div className="absolute inset-0 flex items-center">
                    <div className="w-full border-t border-gray-300" />
                </div>
                <div className="relative flex justify-center text-sm">
                    <span className="px-2 bg-white text-gray-500">Or</span>
                </div>
            </div>

            {/* Link to Register */}
            <div className="text-center">
                <p className="text-sm text-gray-600">
                    Don't have an account?{' '}
                    <Link
                        href="/register"
                        className="font-medium text-indigo-600 hover:text-indigo-500"
                    >
                        Sign up now
                    </Link>
                </p>
            </div>
        </div>
    );
}