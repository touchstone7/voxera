
import { API_BASE_URL } from './config'
import { ApiError, type ApiErrorResponse } from './apiError'

export interface ApiRequestOptions extends Omit<RequestInit, 'body'> {
    accessToken?: string
    body?: unknown
}

function isApiErrorResponse(
    value: unknown,
): value is ApiErrorResponse {
    return typeof value === 'object' && value !== null
}

async function parseResponseBody(
    response: Response,
): Promise<unknown> {
    const text = await response.text()

    if (!text) {
        return undefined
    }

    try {
        return JSON.parse(text) as unknown
    } catch {
        return text
    }
}

export async function apiRequest<T>(
    path: string,
    options: ApiRequestOptions = {},
): Promise<T> {
    const {
        accessToken,
        body,
        headers: customHeaders,
        ...requestOptions
    } = options

    const headers = new Headers(customHeaders)

    headers.set('Accept', 'application/json')

    if (accessToken) {
        headers.set('Authorization', `Bearer ${accessToken}`)
    }

    let requestBody: BodyInit | undefined

    if (body !== undefined) {
        requestBody = JSON.stringify(body)
        headers.set('Content-Type', 'application/json')
    }

    let response: Response

    try {
        response = await fetch(`${API_BASE_URL}${path}`, {
            ...requestOptions,
            headers,
            body: requestBody,
        })
    } catch (error) {
        if (error instanceof Error && error.name === 'AbortError') {
            throw error
        }

        throw new ApiError(
            'Unable to connect to the server. Please try again.',
            0,
            'NETWORK_ERROR',
        )
    }

    const responseBody = await parseResponseBody(response)

    if (!response.ok) {
        const errorResponse = isApiErrorResponse(responseBody)
            ? responseBody
            : undefined

        throw new ApiError(
            typeof errorResponse?.message === 'string'
                ? errorResponse.message
                : `Request failed with status ${response.status}`,
            response.status,
            typeof errorResponse?.error === 'string'
                ? errorResponse.error
                : undefined,
        )
    }

    return responseBody as T
}
