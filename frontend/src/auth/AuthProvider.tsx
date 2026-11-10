import { useMemo, useState, type ReactNode } from 'react'
import type { AuthSession, LoginResponse } from './authTypes'
import { AuthContext, type AuthContextValue } from './authContext'

const SESSION_STORAGE_KEY = 'voxera_auth_session'


function readStoredSession(): AuthSession | null {
    try {
        const stored =
            localStorage.getItem(SESSION_STORAGE_KEY) ??
            sessionStorage.getItem(SESSION_STORAGE_KEY)

        if (!stored) {
            return null
        }

        const session = JSON.parse(stored) as AuthSession

        if (
            !session.accessToken ||
            !session.user?.userId ||
            !session.user?.email ||
            !['EMPLOYEE', 'AGENT', 'ADMIN'].includes(session.user.role)
        ) {
            localStorage.removeItem(SESSION_STORAGE_KEY)
            sessionStorage.removeItem(SESSION_STORAGE_KEY)
            return null
        }

        return session
    } catch {
        localStorage.removeItem(SESSION_STORAGE_KEY)
        sessionStorage.removeItem(SESSION_STORAGE_KEY)
        return null
    }
}

export function AuthProvider({ children }: { children: ReactNode }) {
    const [session, setSession] = useState<AuthSession | null>(
        readStoredSession,
    )

    function login(response: LoginResponse, remember: boolean) {
        const newSession: AuthSession = {
            user: {
                userId: response.userId,
                employeeId: response.employeeId,
                name: response.name,
                email: response.email,
                role: response.role,
            },
            accessToken: response.accessToken,
        }

        const targetStorage = remember ? localStorage : sessionStorage
        const otherStorage = remember ? sessionStorage : localStorage

        targetStorage.setItem(
            SESSION_STORAGE_KEY,
            JSON.stringify(newSession),
        )

        otherStorage.removeItem(SESSION_STORAGE_KEY)

        setSession(newSession)
    }

    function logout() {
        localStorage.removeItem(SESSION_STORAGE_KEY)
        sessionStorage.removeItem(SESSION_STORAGE_KEY)
        setSession(null)
    }

    const value = useMemo<AuthContextValue>(
        () => ({
            user: session?.user ?? null,
            accessToken: session?.accessToken ?? null,
            isAuthenticated: Boolean(session?.accessToken),
            login,
            logout,
        }),
        [session],
    )

    return (
        <AuthContext.Provider value={value}>
            {children}
        </AuthContext.Provider>
    )
}