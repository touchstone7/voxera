
import { Navigate, Outlet, useLocation } from 'react-router-dom'
import { useAuth } from './useAuth'
import type { UserRole } from './authTypes'

interface ProtectedRouteProps {
    allowedRoles: UserRole[]
}

export function ProtectedRoute({ allowedRoles }: ProtectedRouteProps) {
    const { isAuthenticated, user } = useAuth()
    const location = useLocation()

    if (!isAuthenticated || !user) {
        return (
            <Navigate
                to="/login"
                replace
                state={{ from: location }}
            />
        )
    }

    if (!allowedRoles.includes(user.role)) {
        return <Navigate to="/access-denied" replace />
    }

    return <Outlet />
}
