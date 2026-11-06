export interface ApiErrorResponse {
    timestamp?: string
    status?: number
    error?: string
    message?: string
}

export class ApiError extends Error {
    readonly status: number
    readonly errorCode?: string

    constructor(
        message: string,
        status: number,
        errorCode?: string,
    ) {
        super(message)

        this.name = 'ApiError'
        this.status = status
        this.errorCode = errorCode
    }
}