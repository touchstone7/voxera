import { createContext } from 'react'
import type { AuthSession, LoginResponse } from './authTypes'

export interface AuthContextValue {
    user: AuthSession['user'] | null
    accessToken: string | null
    isAuthenticated: boolean
    login: (response: LoginResponse, remember: boolean) => void
    logout: () => void
}

export const AuthContext = createContext<AuthContextValue | undefined>(
    undefined,
)