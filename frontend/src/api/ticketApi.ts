import { apiRequest } from './apiClient';
import type {
  CreateTicketCommentRequest,
  CreateTicketRequest,
  TicketCommentResponse,
  TicketResponse,
} from './ticketTypes';

export function getTickets(
  accessToken: string,
): Promise<TicketResponse[]> {
  return apiRequest<TicketResponse[]>('/api/v1/tickets', {
    accessToken,
  });
}

export function getTicket(
  ticketId: string,
  accessToken: string,
): Promise<TicketResponse> {
  return apiRequest<TicketResponse>(
    `/api/v1/tickets/${encodeURIComponent(ticketId)}`,
    { accessToken },
  );
}

export function createTicket(
  request: CreateTicketRequest,
  accessToken: string,
): Promise<TicketResponse> {
  return apiRequest<TicketResponse>('/api/v1/tickets', {
    method: 'POST',
    body: request,
    accessToken,
  });
}

export function cancelTicket(
  ticketId: string,
  accessToken: string,
): Promise<TicketResponse> {
  return apiRequest<TicketResponse>(
    `/api/v1/tickets/${encodeURIComponent(ticketId)}/cancel`,
    {
      method: 'POST',
      accessToken,
    },
  );
}

export function getTicketComments(
  ticketId: string,
  accessToken: string,
): Promise<TicketCommentResponse[]> {
  return apiRequest<TicketCommentResponse[]>(
    `/api/v1/tickets/${encodeURIComponent(ticketId)}/comments`,
    { accessToken },
  );
}

export function addTicketComment(
  ticketId: string,
  request: CreateTicketCommentRequest,
  accessToken: string,
): Promise<TicketCommentResponse> {
  return apiRequest<TicketCommentResponse>(
    `/api/v1/tickets/${encodeURIComponent(ticketId)}/comments`,
    {
      method: 'POST',
      body: request,
      accessToken,
    },
  );
}
