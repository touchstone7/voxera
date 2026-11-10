export type UserRole = 'EMPLOYEE' | 'AGENT' | 'ADMIN'

export interface AuthUser {
    userId: string
    employeeId: string | null
    name: string
    email: string
    role: UserRole
}

export interface LoginResponse extends AuthUser {
    accessToken: string
}

export interface AuthSession {
    user: AuthUser
    accessToken: string
}