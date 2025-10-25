import { ReactNode } from 'react';
import AuthHeader from '../../components/layout/auth/AuthHeader';
import AuthFooter from '../../components/layout/auth/AuthFooter';

interface AuthLayoutProps {
    children: ReactNode;
}

export default function AuthLayout({ children }: AuthLayoutProps) {
    return (
        <div className="min-h-screen flex flex-col bg-gradient-to-br from-blue-50 to-indigo-100">
            {/* Header chung */}
            <AuthHeader />

            {/* Main content */}
            <main className="flex-1 flex items-center justify-center p-4">
                <div className="w-full max-w-md bg-white rounded-lg shadow-xl p-8">
                    {children}
                </div>
            </main>

            {/* Footer chung */}
            <AuthFooter />
        </div>
    );
}