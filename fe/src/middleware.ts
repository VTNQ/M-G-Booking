import { NextResponse } from 'next/server';
import type { NextRequest } from 'next/server';

export function middleware(request: NextRequest) {
    const token = request.cookies.get('auth-token')?.value;
    const { pathname } = request.nextUrl;

    // Protected routes
    const protectedPaths = ['/dashboard', '/bookings', '/profile'];
    const isProtectedPath = protectedPaths.some((path) =>
        pathname.startsWith(path)
    );

    // Redirect to login if accessing protected route without token
    if (isProtectedPath && !token) {
        const loginUrl = new URL('/login', request.url);
        loginUrl.searchParams.set('redirect', pathname);
        return NextResponse.redirect(loginUrl);
    }

    // Redirect to dashboard if accessing auth pages with token
    const authPaths = ['/login', '/register'];
    if (authPaths.includes(pathname) && token) {
        return NextResponse.redirect(new URL('/dashboard', request.url));
    }

    return NextResponse.next();
}

export const config = {
    matcher: [
        '/((?!api|_next/static|_next/image|favicon.ico).*)',
    ],
};