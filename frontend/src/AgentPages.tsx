import { useMemo, useState } from 'react';
import { Link, NavLink, useParams } from 'react-router-dom';
import './agent.css';

type TicketStatus = 'Open' | 'In Progress' | 'Pending' | 'Resolved';
type TicketPriority = 'Low' | 'Medium' | 'High' | 'Urgent';

type AgentTicket = {
  id: string;
  title: string;
  requester: string;
  initials: string;
  category: string;
  status: TicketStatus;
  priority: TicketPriority;
  assignedTo: string | null;
  created: string;
  description: string;
};

const initialTickets: AgentTicket[] = [
  {
    id: 'VXR-1052',
    title: 'Production database connection failing',
    requester: 'Olivia Martinez',
    initials: 'OM',
    category: 'Database',
    status: 'Open',
    priority: 'Urgent',
    assignedTo: null,
    created: 'Oct 30, 2026',
    description: 'The production application is intermittently failing to connect to the primary database. Multiple users are affected and the issue requires investigation.',
  },
  {
    id: 'VXR-1051',
    title: 'Unable to access shared drive',
    requester: 'Ethan Wilson',
    initials: 'EW',
    category: 'Access',
    status: 'In Progress',
    priority: 'High',
    assignedTo: 'Alex Morgan',
    created: 'Oct 30, 2026',
    description: 'The shared department drive returns an access-denied error despite the user being part of the department.',
  },
  {
    id: 'VXR-1050',
    title: 'Laptop not detecting external monitor',
    requester: 'Sophia Chen',
    initials: 'SC',
    category: 'Hardware',
    status: 'Open',
    priority: 'Medium',
    assignedTo: null,
    created: 'Oct 29, 2026',
    description: 'The laptop does not detect the external monitor connected through the docking station.',
  },
  {
    id: 'VXR-1049',
    title: 'Email delivery delays',
    requester: 'Noah Williams',
    initials: 'NW',
    category: 'Software',
    status: 'Pending',
    priority: 'High',
    assignedTo: 'Priya Sharma',
    created: 'Oct 29, 2026',
    description: 'Outgoing emails are taking several minutes to reach external recipients.',
  },
  {
    id: 'VXR-1048',
    title: 'Unable to connect to office VPN',
    requester: 'Jordan Davis',
    initials: 'JD',
    category: 'Network',
    status: 'In Progress',
    priority: 'High',
    assignedTo: 'Alex Morgan',
    created: 'Oct 28, 2026',
    description: 'The office VPN disconnects shortly after connecting. The employee has restarted the laptop and retried the connection.',
  },
  {
    id: 'VXR-1047',
    title: 'Request for software installation',
    requester: 'Isabella Brown',
    initials: 'IB',
    category: 'Software',
    status: 'Resolved',
    priority: 'Low',
    assignedTo: 'Daniel Lee',
    created: 'Oct 28, 2026',
    description: 'Request to install an approved development tool on the employee workstation.',
  },
  {
    id: 'VXR-1042',
    title: 'Access required for project repository',
    requester: 'Jordan Davis',
    initials: 'JD',
    category: 'Access',
    status: 'Open',
    priority: 'Medium',
    assignedTo: null,
    created: 'Oct 27, 2026',
    description: 'Read access is required for the project repository to continue assigned work.',
  },
  {
    id: 'VXR-1039',
    title: 'Wi-Fi connectivity dropping frequently',
    requester: 'Mia Thompson',
    initials: 'MT',
    category: 'Network',
    status: 'Pending',
    priority: 'Medium',
    assignedTo: 'Priya Sharma',
    created: 'Oct 26, 2026',
    description: 'Wireless connectivity drops several times throughout the workday.',
  },
  {
    id: 'VXR-1031',
    title: 'Laptop performance issue',
    requester: 'Jordan Davis',
    initials: 'JD',
    category: 'Hardware',
    status: 'Pending',
    priority: 'Low',
    assignedTo: 'Daniel Lee',
    created: 'Oct 24, 2026',
    description: 'The laptop becomes slow when multiple applications are running.',
  },
  {
    id: 'VXR-1019',
    title: 'Email configuration fixed',
    requester: 'Jordan Davis',
    initials: 'JD',
    category: 'Software',
    status: 'Resolved',
    priority: 'Low',
    assignedTo: 'Alex Morgan',
    created: 'Oct 20, 2026',
    description: 'The email client configuration issue has been resolved.',
  },
];

const navigation = [
  { to: '/agent/tickets', label: 'All Tickets', icon: '▤', end: true },
  { to: '/agent/queue', label: 'Team Queue', icon: '◫', end: true },
  { to: '/agent/voice', label: 'Voice Support', icon: '⌁', end: false },
];

function AgentLayout({
  children,
  title,
  subtitle,
}: {
  children: React.ReactNode;
  title: string;
  subtitle: string;
}) {
  return (
    <div className="agent-app">
      <aside className="agent-sidebar">
        <Link to="/agent/tickets" className="agent-brand">
          <span className="agent-brand-mark">v.</span>
          <span>voxera<span className="agent-brand-dot">.</span></span>
        </Link>

        <div className="agent-workspace-label">SUPPORT WORKSPACE</div>

        <nav className="agent-nav" aria-label="Agent navigation">
          <div className="agent-nav-section">WORKSPACE</div>
          {navigation.map((item) => (
            <NavLink
              key={item.to}
              to={item.to}
              end={item.end}
              className={({ isActive }) =>
                `agent-nav-link${isActive ? ' active' : ''}`
              }
            >
              <span className="agent-nav-icon">{item.icon}</span>
              <span>{item.label}</span>
            </NavLink>
          ))}

                  <div className="agent-nav-section agent-nav-section-spaced">
                      MANAGEMENT
                  </div>

                  <NavLink
                      to="/admin/users"
                      className={({ isActive }) =>
                          `agent-nav-link${isActive ? ' active' : ''}`
                      }
                  >
                      <span className="agent-nav-icon">♙</span>
                      <span>User Management</span>
                      <span className="agent-coming-soon">ADMIN</span>
                  </NavLink>

                  <NavLink
                      to="/admin/settings"
                      className={({ isActive }) =>
                          `agent-nav-link${isActive ? ' active' : ''}`
                      }
                  >
                      <span className="agent-nav-icon">⚙</span>
                      <span>Administration</span>
                      <span className="agent-coming-soon">ADMIN</span>
                  </NavLink>
              </nav>

        <div className="agent-sidebar-bottom">
          <div className="agent-status-card">
            <span className="agent-status-indicator" />
            <div>
              <strong>You're online</strong>
              <span>Available for tickets</span>
            </div>
            <span className="agent-status-menu">···</span>
          </div>

          <div className="agent-user">
            <div className="agent-avatar">AM</div>
            <div className="agent-user-info">
              <strong>Alex Morgan</strong>
              <span>Support Agent</span>
            </div>
            <span className="agent-user-menu">···</span>
          </div>
        </div>
      </aside>

      <main className="agent-main">
        <header className="agent-topbar">
          <div className="agent-breadcrumb">
            <span>Support Workspace</span>
            <span>/</span>
            <strong>{title}</strong>
          </div>
          <div className="agent-top-actions">
            <span className="agent-demo-label"><span /> DEMO MODE</span>
            <button className="agent-top-icon" type="button" aria-label="Notifications">♧</button>
            <div className="agent-avatar small">AM</div>
          </div>
        </header>

        <section className="agent-content">
          <div className="agent-page-heading">
            <div>
              <div className="agent-eyebrow">AGENT WORKSPACE</div>
              <h1>{title}</h1>
              <p>{subtitle}</p>
            </div>
          </div>

          {children}

          <footer className="agent-footer">
            <span>© 2026 Voxera</span>
            <span>Support workspace · Demo data · No backend connected</span>
          </footer>
        </section>
      </main>
    </div>
  );
}

function StatusBadge({ status }: { status: TicketStatus }) {
  return (
    <span className={`agent-ticket-status status-${status.toLowerCase().replace(' ', '-')}`}>
      <span />
      {status}
    </span>
  );
}

function PriorityBadge({ priority }: { priority: TicketPriority }) {
  return (
    <span className={`agent-ticket-priority priority-${priority.toLowerCase()}`}>
      <span />
      {priority}
    </span>
  );
}

export function AllTicketsPage() {
  const [tickets] = useState(initialTickets);
  const [search, setSearch] = useState('');
  const [statusFilter, setStatusFilter] = useState('All statuses');
  const [priorityFilter, setPriorityFilter] = useState('All priorities');
  const [categoryFilter, setCategoryFilter] = useState('All categories');
  const [selectedTicket, setSelectedTicket] = useState<AgentTicket | null>(null);

  const filteredTickets = useMemo(() => {
    const query = search.trim().toLowerCase();

    return tickets.filter((ticket) => {
      const matchesSearch =
        !query ||
        ticket.id.toLowerCase().includes(query) ||
        ticket.title.toLowerCase().includes(query) ||
        ticket.requester.toLowerCase().includes(query) ||
        (ticket.assignedTo ?? '').toLowerCase().includes(query);

      const matchesStatus =
        statusFilter === 'All statuses' || ticket.status === statusFilter;
      const matchesPriority =
        priorityFilter === 'All priorities' || ticket.priority === priorityFilter;
      const matchesCategory =
        categoryFilter === 'All categories' || ticket.category === categoryFilter;

      return matchesSearch && matchesStatus && matchesPriority && matchesCategory;
    });
  }, [tickets, search, statusFilter, priorityFilter, categoryFilter]);

  const unassigned = tickets.filter((ticket) => !ticket.assignedTo && ticket.status !== 'Resolved').length;
  const inProgress = tickets.filter((ticket) => ticket.status === 'In Progress').length;
  const urgent = tickets.filter((ticket) => ticket.priority === 'Urgent' && ticket.status !== 'Resolved').length;

  function clearFilters() {
    setSearch('');
    setStatusFilter('All statuses');
    setPriorityFilter('All priorities');
    setCategoryFilter('All categories');
  }

  return (
    <AgentLayout
      title="All Tickets"
      subtitle="Monitor, prioritize and manage support requests across your organization."
    >
      <div className="agent-stats-grid">
        <div className="agent-stat-card">
          <div className="agent-stat-top">
            <span className="agent-stat-icon purple">▤</span>
            <span className="agent-stat-caption">Across all statuses</span>
          </div>
          <span className="agent-stat-number">{tickets.length}</span>
          <span className="agent-stat-label">Total tickets</span>
        </div>

        <div className="agent-stat-card">
          <div className="agent-stat-top">
            <span className="agent-stat-icon amber">◷</span>
            <span className="agent-stat-caption">Needs an owner</span>
          </div>
          <span className="agent-stat-number">{unassigned}</span>
          <span className="agent-stat-label">Unassigned tickets</span>
        </div>

        <div className="agent-stat-card">
          <div className="agent-stat-top">
            <span className="agent-stat-icon blue">↻</span>
            <span className="agent-stat-caption">Currently being handled</span>
          </div>
          <span className="agent-stat-number">{inProgress}</span>
          <span className="agent-stat-label">In progress</span>
        </div>

        <div className="agent-stat-card">
          <div className="agent-stat-top">
            <span className="agent-stat-icon red">!</span>
            <span className="agent-stat-caption">Requires attention</span>
          </div>
          <span className="agent-stat-number">{urgent}</span>
          <span className="agent-stat-label">Urgent tickets</span>
        </div>
      </div>

      <div className="agent-section-heading">
        <div>
          <h2>Ticket overview</h2>
          <p>Review and manage requests submitted by employees.</p>
        </div>
        <span className="agent-record-count">{filteredTickets.length} records</span>
      </div>

      <div className="agent-ticket-panel">
        <div className="agent-filter-toolbar">
          <div className="agent-search">
            <span>⌕</span>
            <input
              type="search"
              placeholder="Search tickets, requesters, agents..."
              aria-label="Search tickets"
              value={search}
              onChange={(event) => setSearch(event.target.value)}
            />
            <kbd>⌘ K</kbd>
          </div>

          <div className="agent-filter-group">
            <select
              aria-label="Filter by status"
              value={statusFilter}
              onChange={(event) => setStatusFilter(event.target.value)}
            >
              <option>All statuses</option>
              <option>Open</option>
              <option>In Progress</option>
              <option>Pending</option>
              <option>Resolved</option>
            </select>

            <select
              aria-label="Filter by priority"
              value={priorityFilter}
              onChange={(event) => setPriorityFilter(event.target.value)}
            >
              <option>All priorities</option>
              <option>Urgent</option>
              <option>High</option>
              <option>Medium</option>
              <option>Low</option>
            </select>

            <select
              aria-label="Filter by category"
              value={categoryFilter}
              onChange={(event) => setCategoryFilter(event.target.value)}
            >
              <option>All categories</option>
              <option>Network</option>
              <option>Hardware</option>
              <option>Software</option>
              <option>Access</option>
              <option>Database</option>
            </select>
          </div>
        </div>

        <div className="agent-table-wrap">
          <table className="agent-table">
            <thead>
              <tr>
                <th>Ticket</th>
                <th>Requester</th>
                <th>Priority</th>
                <th>Status</th>
                <th>Assigned to</th>
                <th>Created</th>
                <th />
              </tr>
            </thead>
            <tbody>
              {filteredTickets.map((ticket) => (
                <tr
                  key={ticket.id}
                  className={selectedTicket?.id === ticket.id ? 'selected' : ''}
                >
                  <td>
                    <button
                      className="agent-ticket-title"
                      type="button"
                      onClick={() => setSelectedTicket(ticket)}
                    >
                      <strong>{ticket.title}</strong>
                      <span>{ticket.id} · {ticket.category}</span>
                    </button>
                  </td>
                  <td>
                    <div className="agent-requester">
                      <span className="agent-requester-avatar">{ticket.initials}</span>
                      <span>{ticket.requester}</span>
                    </div>
                  </td>
                  <td><PriorityBadge priority={ticket.priority} /></td>
                  <td><StatusBadge status={ticket.status} /></td>
                  <td>
                    {ticket.assignedTo ? (
                      <span className="agent-assignee">{ticket.assignedTo}</span>
                    ) : (
                      <span className="agent-unassigned">Unassigned</span>
                    )}
                  </td>
                  <td className="agent-date">{ticket.created}</td>
                  <td>
                    <button
                      className="agent-row-action"
                      type="button"
                      aria-label={`View ${ticket.id}`}
                      onClick={() => setSelectedTicket(ticket)}
                    >
                      ↗
                    </button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>

          {filteredTickets.length === 0 && (
            <div className="agent-empty-state">
              <span>⌕</span>
              <h3>No matching tickets</h3>
              <p>Try adjusting your search or filters.</p>
              <button type="button" onClick={clearFilters}>Clear filters</button>
            </div>
          )}
        </div>

        <div className="agent-table-footer">
          <span>Showing <strong>{filteredTickets.length}</strong> of <strong>{tickets.length}</strong> tickets</span>
          <span>Sample data · Updated just now</span>
        </div>
      </div>

      {selectedTicket && (
        <div className="agent-detail-backdrop" onClick={() => setSelectedTicket(null)}>
          <section
            className="agent-detail-panel"
            role="dialog"
            aria-modal="true"
            aria-labelledby="agent-detail-title"
            onClick={(event) => event.stopPropagation()}
          >
            <div className="agent-detail-header">
              <div>
                <span className="agent-detail-id">{selectedTicket.id}</span>
                <h2 id="agent-detail-title">{selectedTicket.title}</h2>
              </div>
              <button
                type="button"
                className="agent-detail-close"
                aria-label="Close ticket details"
                onClick={() => setSelectedTicket(null)}
              >
                ×
              </button>
            </div>

            <div className="agent-detail-badges">
              <StatusBadge status={selectedTicket.status} />
              <PriorityBadge priority={selectedTicket.priority} />
            </div>

            <div className="agent-detail-section">
              <h3>Description</h3>
              <p>{selectedTicket.description}</p>
            </div>

            <div className="agent-detail-grid">
              <div><span>Requester</span><strong>{selectedTicket.requester}</strong></div>
              <div><span>Category</span><strong>{selectedTicket.category}</strong></div>
              <div><span>Assigned to</span><strong>{selectedTicket.assignedTo ?? 'Unassigned'}</strong></div>
              <div><span>Created</span><strong>{selectedTicket.created}</strong></div>
            </div>

            <div className="agent-detail-note">
                <span>✳</span>
                <p>
                    Assign this request to an agent or change its current owner.
                </p>
            </div>

            <Link
                to={`/agent/tickets/${selectedTicket.id}/assign`}
                className="agent-detail-done"
                style={{
                    display: 'flex',
                    alignItems: 'center',
                    justifyContent: 'center',
                    textDecoration: 'none',
                    marginTop: '12px',
                }}
            >
                Assign / Reassign ticket →
            </Link>

            <button
              className="agent-detail-done"
              type="button"
              onClick={() => setSelectedTicket(null)}
            >
              Close details
            </button>
          </section>
        </div>
      )}
    </AgentLayout>
  );
}

export function TeamQueuePage() {
  const [tickets, setTickets] = useState(initialTickets);
  const [priorityFilter, setPriorityFilter] = useState('All priorities');
  const [categoryFilter, setCategoryFilter] = useState('All categories');
  const [confirmation, setConfirmation] = useState('');

  const queueTickets = tickets.filter(
    (ticket) => !ticket.assignedTo && ticket.status !== 'Resolved'
  );

  const categories = Array.from(
    new Set(queueTickets.map((ticket) => ticket.category))
  );

  const filteredTickets = useMemo(
    () =>
      queueTickets.filter((ticket) => {
        const matchesPriority =
          priorityFilter === 'All priorities' ||
          ticket.priority === priorityFilter;

        const matchesCategory =
          categoryFilter === 'All categories' ||
          ticket.category === categoryFilter;

        return matchesPriority && matchesCategory;
      }),
    [queueTickets, priorityFilter, categoryFilter]
  );

  const urgentCount = queueTickets.filter(
    (ticket) => ticket.priority === 'Urgent'
  ).length;

  const highPriorityCount = queueTickets.filter(
    (ticket) => ticket.priority === 'High'
  ).length;

  function takeOwnership(ticket: AgentTicket) {
    setTickets((currentTickets) =>
      currentTickets.map((currentTicket) =>
        currentTicket.id === ticket.id
          ? { ...currentTicket, assignedTo: 'Alex Morgan' }
          : currentTicket
      )
    );

    setConfirmation(`${ticket.id} has been assigned to you.`);
  }

  function clearFilters() {
    setPriorityFilter('All priorities');
    setCategoryFilter('All categories');
  }

  return (
    <AgentLayout
      title="Team Queue"
      subtitle="Review unassigned requests and take ownership of tickets that need attention."
    >
      <div className="agent-stats-grid">
        <div className="agent-stat-card">
          <div className="agent-stat-top">
            <span className="agent-stat-icon purple">◫</span>
            <span className="agent-stat-caption">Awaiting an owner</span>
          </div>
          <span className="agent-stat-number">{queueTickets.length}</span>
          <span className="agent-stat-label">Unassigned tickets</span>
        </div>

        <div className="agent-stat-card">
          <div className="agent-stat-top">
            <span className="agent-stat-icon red">!</span>
            <span className="agent-stat-caption">Immediate attention</span>
          </div>
          <span className="agent-stat-number">{urgentCount}</span>
          <span className="agent-stat-label">Urgent tickets</span>
        </div>

        <div className="agent-stat-card">
          <div className="agent-stat-top">
            <span className="agent-stat-icon amber">↑</span>
            <span className="agent-stat-caption">High priority</span>
          </div>
          <span className="agent-stat-number">{highPriorityCount}</span>
          <span className="agent-stat-label">High priority tickets</span>
        </div>

        <div className="agent-stat-card">
          <div className="agent-stat-top">
            <span className="agent-stat-icon blue">✓</span>
            <span className="agent-stat-caption">Your ownership</span>
          </div>
          <span className="agent-stat-number">
            {tickets.filter((ticket) => ticket.assignedTo === 'Alex Morgan').length}
          </span>
          <span className="agent-stat-label">Assigned to you</span>
        </div>
      </div>

      {confirmation && (
        <div className="agent-queue-confirmation">
          <span>✓</span>
          {confirmation}
          <button
            type="button"
            onClick={() => setConfirmation('')}
            aria-label="Dismiss confirmation"
          >
            ×
          </button>
        </div>
      )}

      <div className="agent-section-heading">
        <div>
          <h2>Unassigned tickets</h2>
          <p>
            Requests that are waiting for an agent to take ownership.
          </p>
        </div>
        <span className="agent-record-count">
          {filteredTickets.length} of {queueTickets.length} tickets
        </span>
      </div>

      <div className="agent-ticket-panel">
        <div className="agent-queue-toolbar">
          <div className="agent-queue-toolbar-label">
            <span>⌕</span>
            Filter queue
          </div>

          <div className="agent-queue-filters">
            <select
              value={priorityFilter}
              onChange={(event) => setPriorityFilter(event.target.value)}
              aria-label="Filter by priority"
            >
              <option>All priorities</option>
              <option>Urgent</option>
              <option>High</option>
              <option>Medium</option>
              <option>Low</option>
            </select>

            <select
              value={categoryFilter}
              onChange={(event) => setCategoryFilter(event.target.value)}
              aria-label="Filter by category"
            >
              <option>All categories</option>
              {categories.map((category) => (
                <option key={category}>{category}</option>
              ))}
            </select>

            {(priorityFilter !== 'All priorities' ||
              categoryFilter !== 'All categories') && (
              <button
                type="button"
                className="agent-queue-clear"
                onClick={clearFilters}
              >
                Clear filters
              </button>
            )}
          </div>
        </div>

        {filteredTickets.length > 0 ? (
          <div className="agent-table-wrap">
            <table className="agent-table agent-queue-table">
              <thead>
                <tr>
                  <th>Ticket</th>
                  <th>Requester</th>
                  <th>Priority</th>
                  <th>Category</th>
                  <th>Created</th>
                  <th>Action</th>
                </tr>
              </thead>

              <tbody>
                {filteredTickets.map((ticket) => (
                  <tr key={ticket.id}>
                    <td>
                      <div className="agent-queue-ticket">
                        <strong>{ticket.title}</strong>
                        <span>{ticket.id}</span>
                      </div>
                    </td>

                    <td>
                      <div className="agent-requester">
                        <span className="agent-requester-avatar">
                          {ticket.initials}
                        </span>
                        {ticket.requester}
                      </div>
                    </td>

                    <td>
                      <PriorityBadge priority={ticket.priority} />
                    </td>

                    <td>{ticket.category}</td>

                    <td className="agent-date">{ticket.created}</td>

                    <td>
                      <button
                        type="button"
                        className="agent-queue-take-button"
                        onClick={() => takeOwnership(ticket)}
                      >
                        Take ticket <span>→</span>
                      </button>
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        ) : (
          <div className="agent-empty-state">
            <span>✓</span>
            <h3>
              {queueTickets.length === 0
                ? 'The queue is clear'
                : 'No matching tickets'}
            </h3>
            <p>
              {queueTickets.length === 0
                ? 'There are currently no unassigned tickets waiting for an agent.'
                : 'Try changing your filters to see more tickets.'}
            </p>
            {(priorityFilter !== 'All priorities' ||
              categoryFilter !== 'All categories') && (
              <button type="button" onClick={clearFilters}>
                Clear filters
              </button>
            )}
          </div>
        )}

        <div className="agent-table-footer">
          <span>
            Showing <strong>{filteredTickets.length}</strong> unassigned tickets
          </span>
          <span>Assignment is manual</span>
        </div>
      </div>
    </AgentLayout>
  );
}

const availableAgents = [
  {
    name: 'Alex Morgan',
    initials: 'AM',
    role: 'Senior Support Agent',
    availability: 'Available',
  },
  {
    name: 'Priya Shah',
    initials: 'PS',
    role: 'Technical Support Agent',
    availability: 'Available',
  },
  {
    name: 'Daniel Kim',
    initials: 'DK',
    role: 'Infrastructure Support Agent',
    availability: 'Busy',
  },
];

export function TicketAssignmentPage() {
  const { ticketId } = useParams<{ ticketId: string }>();

  const ticket = initialTickets.find(
    (item) => item.id === ticketId
  );

  const [selectedAgent, setSelectedAgent] = useState(
    ticket?.assignedTo ?? ''
  );
  const [assigned, setAssigned] = useState(false);

  if (!ticket) {
    return (
      <AgentLayout
        title="Ticket not found"
        subtitle="The requested ticket could not be found."
      >
        <div className="agent-ticket-panel">
          <div className="agent-empty-state">
            <span>⌕</span>
            <h3>Ticket not found</h3>
            <p>Check the ticket reference and try again.</p>
            <Link className="agent-assignment-back" to="/agent/tickets">
              Back to all tickets
            </Link>
          </div>
        </div>
      </AgentLayout>
    );
  }

  const currentAgent = availableAgents.find(
    (agent) => agent.name === ticket.assignedTo
  );

  function handleAssignment(event: React.FormEvent<HTMLFormElement>) {
    event.preventDefault();

    if (!selectedAgent) return;

    setAssigned(true);
  }

  return (
    <AgentLayout
      title="Ticket Assignment"
      subtitle="Choose the right support agent to take ownership of this request."
    >
      <div className="agent-assignment-back-row">
        <Link to="/agent/tickets" className="agent-assignment-back">
          ← Back to all tickets
        </Link>
        <span>Ticket assignment</span>
      </div>

      {assigned && (
        <div className="agent-queue-confirmation">
          <span>✓</span>
          {ticket.id} has been assigned to {selectedAgent}.
        </div>
      )}

      <div className="agent-assignment-layout">
        <section className="agent-assignment-main">
          <div className="agent-assignment-card">
            <div className="agent-assignment-card-heading">
              <div>
                <span className="agent-detail-id">{ticket.id}</span>
                <h2>{ticket.title}</h2>
                <p>
                  Review the request before selecting an owner.
                </p>
              </div>

              <StatusBadge status={ticket.status} />
            </div>

            <div className="agent-detail-badges">
              <PriorityBadge priority={ticket.priority} />
              <span className="agent-assignment-category">
                {ticket.category}
              </span>
            </div>

            <div className="agent-assignment-description">
              <span>REQUEST DESCRIPTION</span>
              <p>{ticket.description}</p>
            </div>

            <div className="agent-assignment-ticket-meta">
              <div>
                <span>Requester</span>
                <strong>{ticket.requester}</strong>
              </div>
              <div>
                <span>Created</span>
                <strong>{ticket.created}</strong>
              </div>
              <div>
                <span>Current assignee</span>
                <strong>{ticket.assignedTo ?? 'Unassigned'}</strong>
              </div>
            </div>
          </div>

          <form
            className="agent-assignment-card agent-assignment-form"
            onSubmit={handleAssignment}
          >
            <div className="agent-assignment-card-heading">
              <div>
                <h2>Choose an agent</h2>
                <p>
                  Select an agent to assign responsibility for this ticket.
                </p>
              </div>
            </div>

            <div className="agent-assignment-agent-list">
              {availableAgents.map((agent) => {
                const workload = initialTickets.filter(
                  (item) => item.assignedTo === agent.name
                ).length;

                const isSelected = selectedAgent === agent.name;

                return (
                  <label
                    key={agent.name}
                    className={`agent-assignment-agent${isSelected ? ' selected' : ''}`}
                  >
                    <input
                      type="radio"
                      name="assignedAgent"
                      value={agent.name}
                      checked={isSelected}
                      onChange={() => {
                        setSelectedAgent(agent.name);
                        setAssigned(false);
                      }}
                    />

                    <span className="agent-assignment-avatar">
                      {agent.initials}
                    </span>

                    <span className="agent-assignment-agent-info">
                      <strong>{agent.name}</strong>
                      <span>{agent.role}</span>
                    </span>

                    <span className="agent-assignment-agent-meta">
                      <span className={`agent-agent-status ${agent.availability === 'Available' ? 'available' : 'busy'}`}>
                        {agent.availability}
                      </span>
                      <span>{workload} active tickets</span>
                    </span>
                  </label>
                );
              })}
            </div>

            <div className="agent-assignment-form-footer">
              <span>
                {currentAgent && !assigned
                  ? `Currently assigned to ${currentAgent.name}`
                  : 'Assignment is a manual action'}
              </span>

              <button
                type="submit"
                className="agent-assignment-submit"
                disabled={!selectedAgent || (assigned && selectedAgent === ticket.assignedTo)}
              >
                {assigned
                  ? 'Assignment confirmed'
                  : ticket.assignedTo
                    ? 'Reassign ticket'
                    : 'Assign ticket'}
                <span>→</span>
              </button>
            </div>
          </form>
        </section>

        <aside className="agent-assignment-sidebar">
          <div className="agent-assignment-summary">
            <span className="agent-assignment-summary-icon">♧</span>
            <h3>Assignment guidance</h3>
            <p>
              Consider the ticket category, priority and agent workload when
              choosing an owner.
            </p>
            <div>
              <span>Ticket priority</span>
              <PriorityBadge priority={ticket.priority} />
            </div>
            <div>
              <span>Ticket category</span>
              <strong>{ticket.category}</strong>
            </div>
          </div>

          <div className="agent-assignment-summary agent-assignment-note">
            <span className="agent-assignment-summary-icon">✳</span>
            <h3>Manual assignment</h3>
            <p>
              The selected agent becomes the ticket owner. This demo does not
              send notifications or update the backend.
            </p>
          </div>
        </aside>
      </div>
    </AgentLayout>
  );
}
type ManagedUser = {
    id: string;
    name: string;
    email: string;
    initials: string;
    department: string;
    role: 'EMPLOYEE' | 'AGENT' | 'ADMIN';
    status: 'ACTIVE' | 'INACTIVE';
    joined: string;
    lastActive: string;
};

const initialUsers: ManagedUser[] = [
    {
        id: 'USR-1001',
        name: 'Alex Morgan',
        email: 'alex.morgan@voxera.demo',
        initials: 'AM',
        department: 'IT Support',
        role: 'AGENT',
        status: 'ACTIVE',
        joined: 'Jan 12, 2026',
        lastActive: 'Online now',
    },
    {
        id: 'USR-1002',
        name: 'Jordan Davis',
        email: 'jordan.davis@voxera.demo',
        initials: 'JD',
        department: 'Operations',
        role: 'EMPLOYEE',
        status: 'ACTIVE',
        joined: 'Feb 04, 2026',
        lastActive: '12 minutes ago',
    },
    {
        id: 'USR-1003',
        name: 'Priya Shah',
        email: 'priya.shah@voxera.demo',
        initials: 'PS',
        department: 'IT Support',
        role: 'AGENT',
        status: 'ACTIVE',
        joined: 'Feb 18, 2026',
        lastActive: 'Online now',
    },
    {
        id: 'USR-1004',
        name: 'Daniel Kim',
        email: 'daniel.kim@voxera.demo',
        initials: 'DK',
        department: 'IT Support',
        role: 'AGENT',
        status: 'ACTIVE',
        joined: 'Mar 02, 2026',
        lastActive: '1 hour ago',
    },
    {
        id: 'USR-1005',
        name: 'Olivia Martinez',
        email: 'olivia.martinez@voxera.demo',
        initials: 'OM',
        department: 'Finance',
        role: 'EMPLOYEE',
        status: 'ACTIVE',
        joined: 'Mar 14, 2026',
        lastActive: 'Yesterday',
    },
    {
        id: 'USR-1006',
        name: 'Ethan Wilson',
        email: 'ethan.wilson@voxera.demo',
        initials: 'EW',
        department: 'Human Resources',
        role: 'EMPLOYEE',
        status: 'INACTIVE',
        joined: 'Apr 09, 2026',
        lastActive: 'Oct 12, 2026',
    },
    {
        id: 'USR-1007',
        name: 'Sophia Chen',
        email: 'sophia.chen@voxera.demo',
        initials: 'SC',
        department: 'Administration',
        role: 'ADMIN',
        status: 'ACTIVE',
        joined: 'Jan 08, 2026',
        lastActive: 'Online now',
    },
    {
        id: 'USR-1008',
        name: 'Noah Patel',
        email: 'noah.patel@voxera.demo',
        initials: 'NP',
        department: 'Sales',
        role: 'EMPLOYEE',
        status: 'INACTIVE',
        joined: 'May 21, 2026',
        lastActive: 'Sep 28, 2026',
    },
];

export function UserManagementPage() {
    const [users, setUsers] = useState(initialUsers);
    const [search, setSearch] = useState('');
    const [roleFilter, setRoleFilter] = useState('ALL');
    const [statusFilter, setStatusFilter] = useState('ALL');
    const [selectedUserId, setSelectedUserId] = useState<string | null>(
        initialUsers[0].id,
    );

    const filteredUsers = useMemo(() => {
        const query = search.trim().toLowerCase();

        return users.filter((user) => {
            const matchesSearch =
                !query ||
                user.name.toLowerCase().includes(query) ||
                user.email.toLowerCase().includes(query) ||
                user.id.toLowerCase().includes(query) ||
                user.department.toLowerCase().includes(query);

            const matchesRole =
                roleFilter === 'ALL' || user.role === roleFilter;

            const matchesStatus =
                statusFilter === 'ALL' || user.status === statusFilter;

            return matchesSearch && matchesRole && matchesStatus;
        });
    }, [users, search, roleFilter, statusFilter]);

    const selectedUser =
        users.find((user) => user.id === selectedUserId) ?? null;

    const activeCount = users.filter((user) => user.status === 'ACTIVE').length;
    const agentCount = users.filter((user) => user.role === 'AGENT').length;
    const adminCount = users.filter((user) => user.role === 'ADMIN').length;

    function updateUserRole(userId: string, role: ManagedUser['role']) {
        setUsers((current) =>
            current.map((user) =>
                user.id === userId ? { ...user, role } : user,
            ),
        );
    }

    function toggleUserStatus(userId: string) {
        setUsers((current) =>
            current.map((user) =>
                user.id === userId
                    ? {
                        ...user,
                        status: user.status === 'ACTIVE' ? 'INACTIVE' : 'ACTIVE',
                    }
                    : user,
            ),
        );
    }

    return (
        <AgentLayout
            title="User Management"
            subtitle="Manage workspace users, access roles, and account status."
        >
            <div className="um-page">
                <div className="um-stats">
                    <div className="um-stat-card">
                        <span className="um-stat-label">TOTAL USERS</span>
                        <strong>{users.length}</strong>
                        <span className="um-stat-note">Registered accounts</span>
                    </div>
                    <div className="um-stat-card">
                        <span className="um-stat-label">ACTIVE USERS</span>
                        <strong>{activeCount}</strong>
                        <span className="um-stat-note">Currently enabled</span>
                    </div>
                    <div className="um-stat-card">
                        <span className="um-stat-label">SUPPORT AGENTS</span>
                        <strong>{agentCount}</strong>
                        <span className="um-stat-note">Agent accounts</span>
                    </div>
                    <div className="um-stat-card">
                        <span className="um-stat-label">ADMINISTRATORS</span>
                        <strong>{adminCount}</strong>
                        <span className="um-stat-note">Admin accounts</span>
                    </div>
                </div>

                <div className="um-toolbar">
                    <div className="um-search">
                        <span>⌕</span>
                        <input
                            type="search"
                            placeholder="Search users by name, email, or ID..."
                            value={search}
                            onChange={(event) => setSearch(event.target.value)}
                        />
                    </div>

                    <div className="um-filters">
                        <select
                            aria-label="Filter by role"
                            value={roleFilter}
                            onChange={(event) => setRoleFilter(event.target.value)}
                        >
                            <option value="ALL">All roles</option>
                            <option value="EMPLOYEE">Employee</option>
                            <option value="AGENT">Agent</option>
                            <option value="ADMIN">Admin</option>
                        </select>

                        <select
                            aria-label="Filter by status"
                            value={statusFilter}
                            onChange={(event) => setStatusFilter(event.target.value)}
                        >
                            <option value="ALL">All statuses</option>
                            <option value="ACTIVE">Active</option>
                            <option value="INACTIVE">Inactive</option>
                        </select>
                    </div>
                </div>

                <div className="um-content">
                    <div className="um-table-card">
                        <div className="um-table-heading">
                            <div>
                                <h2>Workspace users</h2>
                                <p>
                                    {filteredUsers.length} of {users.length} users
                                </p>
                            </div>
                            <span className="um-demo-tag">DEMO DATA</span>
                        </div>

                        <div className="um-table-scroll">
                            <table className="um-table">
                                <thead>
                                    <tr>
                                        <th>User</th>
                                        <th>Role</th>
                                        <th>Status</th>
                                        <th>Department</th>
                                        <th />
                                    </tr>
                                </thead>
                                <tbody>
                                    {filteredUsers.map((user) => (
                                        <tr
                                            key={user.id}
                                            className={
                                                selectedUserId === user.id ? 'selected' : ''
                                            }
                                            onClick={() => setSelectedUserId(user.id)}
                                        >
                                            <td>
                                                <div className="um-user-cell">
                                                    <div className="um-avatar">{user.initials}</div>
                                                    <div>
                                                        <strong>{user.name}</strong>
                                                        <span>{user.email}</span>
                                                    </div>
                                                </div>
                                            </td>
                                            <td>
                                                <span className={`um-role ${user.role.toLowerCase()}`}>
                                                    {user.role}
                                                </span>
                                            </td>
                                            <td>
                                                <span
                                                    className={`um-status ${user.status.toLowerCase()}`}
                                                >
                                                    <span />
                                                    {user.status === 'ACTIVE' ? 'Active' : 'Inactive'}
                                                </span>
                                            </td>
                                            <td className="um-department">{user.department}</td>
                                            <td>
                                                <button
                                                    type="button"
                                                    className="um-view-button"
                                                    onClick={(event) => {
                                                        event.stopPropagation();
                                                        setSelectedUserId(user.id);
                                                    }}
                                                >
                                                    View
                                                </button>
                                            </td>
                                        </tr>
                                    ))}

                                    {filteredUsers.length === 0 && (
                                        <tr>
                                            <td colSpan={5} className="um-empty">
                                                <strong>No users found</strong>
                                                <span>Try changing your search or filters.</span>
                                            </td>
                                        </tr>
                                    )}
                                </tbody>
                            </table>
                        </div>
                    </div>

                    <aside className="um-details">
                        {selectedUser ? (
                            <>
                                <div className="um-details-heading">
                                    <span>USER DETAILS</span>
                                    <span className="um-details-id">{selectedUser.id}</span>
                                </div>

                                <div className="um-profile">
                                    <div className="um-profile-avatar">
                                        {selectedUser.initials}
                                    </div>
                                    <h2>{selectedUser.name}</h2>
                                    <p>{selectedUser.email}</p>
                                    <span
                                        className={`um-status ${selectedUser.status.toLowerCase()}`}
                                    >
                                        <span />
                                        {selectedUser.status === 'ACTIVE' ? 'Active' : 'Inactive'}
                                    </span>
                                </div>

                                <div className="um-detail-divider" />

                                <div className="um-detail-fields">
                                    <div>
                                        <span>Department</span>
                                        <strong>{selectedUser.department}</strong>
                                    </div>
                                    <div>
                                        <span>Account created</span>
                                        <strong>{selectedUser.joined}</strong>
                                    </div>
                                    <div>
                                        <span>Last active</span>
                                        <strong>{selectedUser.lastActive}</strong>
                                    </div>
                                </div>

                                <div className="um-detail-divider" />

                                <div className="um-control">
                                    <label htmlFor="um-role-select">Access role</label>
                                    <select
                                        id="um-role-select"
                                        value={selectedUser.role}
                                        onChange={(event) =>
                                            updateUserRole(
                                                selectedUser.id,
                                                event.target.value as ManagedUser['role'],
                                            )
                                        }
                                    >
                                        <option value="EMPLOYEE">Employee</option>
                                        <option value="AGENT">Agent</option>
                                        <option value="ADMIN">Admin</option>
                                    </select>
                                    <p>Controls the user's workspace permissions.</p>
                                </div>

                                <div className="um-account-action">
                                    <div>
                                        <strong>
                                            {selectedUser.status === 'ACTIVE'
                                                ? 'Deactivate account'
                                                : 'Activate account'}
                                        </strong>
                                        <span>
                                            {selectedUser.status === 'ACTIVE'
                                                ? 'Prevent this user from accessing the workspace.'
                                                : 'Restore this user’s workspace access.'}
                                        </span>
                                    </div>
                                    <button
                                        type="button"
                                        className={
                                            selectedUser.status === 'ACTIVE'
                                                ? 'um-deactivate-button'
                                                : 'um-activate-button'
                                        }
                                        onClick={() => toggleUserStatus(selectedUser.id)}
                                    >
                                        {selectedUser.status === 'ACTIVE' ? 'Deactivate' : 'Activate'}
                                    </button>
                                </div>

                                <div className="um-demo-notice">
                                    <span>ⓘ</span>
                                    Changes are simulated and will reset when the page reloads.
                                </div>
                            </>
                        ) : (
                            <div className="um-no-selection">
                                <strong>Select a user</strong>
                                <span>Choose a user from the list to view their details.</span>
                            </div>
                        )}
                    </aside>
                </div>
            </div>
        </AgentLayout>
    );
}

type SettingsSection =
    | 'General'
    | 'Tickets'
    | 'Workflow'
    | 'Notifications'
    | 'Voice Agent';

type VoxeraSettings = {
    workspaceName: string;
    organization: string;
    timezone: string;
    ticketPrefix: string;
    defaultPriority: 'Low' | 'Medium' | 'High' | 'Urgent';
    categories: string;
    allowReopening: boolean;
    requireAssignment: boolean;
    notifyNewTicket: boolean;
    notifyAssignment: boolean;
    notifyStatusChange: boolean;
    notifyByEmail: boolean;
    notifyInApp: boolean;
    voiceEnabled: boolean;
    voiceGreeting: string;
    voiceLanguage: string;
};

const defaultVoxeraSettings: VoxeraSettings = {
    workspaceName: 'Voxera Support',
    organization: 'Voxera Technologies',
    timezone: 'Asia/Kolkata',
    ticketPrefix: 'VXR',
    defaultPriority: 'Medium',
    categories: 'Hardware, Software, Network, Database, Access, Other',
    allowReopening: true,
    requireAssignment: false,
    notifyNewTicket: true,
    notifyAssignment: true,
    notifyStatusChange: true,
    notifyByEmail: true,
    notifyInApp: true,
    voiceEnabled: true,
    voiceGreeting: 'Hello! Welcome to Voxera Support. How can I help you today?',
    voiceLanguage: 'English',
};

const settingsNavigation: {
    name: SettingsSection;
    icon: string;
    description: string;
}[] = [
        {
            name: 'General',
            icon: '⚙',
            description: 'Workspace identity and regional preferences',
        },
        {
            name: 'Tickets',
            icon: '▤',
            description: 'Ticket defaults and categories',
        },
        {
            name: 'Workflow',
            icon: '⇄',
            description: 'Ticket lifecycle and assignment rules',
        },
        {
            name: 'Notifications',
            icon: '♧',
            description: 'Updates and notification preferences',
        },
        {
            name: 'Voice Agent',
            icon: '⌁',
            description: 'Voice support configuration',
        },
    ];

export function AdministrationPage() {
    const [activeSection, setActiveSection] =
        useState<SettingsSection>('General');
    const [settings, setSettings] = useState(defaultVoxeraSettings);
    const [saveMessage, setSaveMessage] = useState('');

    function updateSetting<K extends keyof VoxeraSettings>(
        key: K,
        value: VoxeraSettings[K],
    ) {
        setSettings((current) => ({ ...current, [key]: value }));
        setSaveMessage('');
    }

    function toggleSetting(key: keyof VoxeraSettings) {
        const currentValue = settings[key];

        if (typeof currentValue === 'boolean') {
            updateSetting(key, !currentValue);
        }
    }

    function saveSettings() {
        setSaveMessage('Your changes have been saved in this demo session.');
    }

    const currentNavigation = settingsNavigation.find(
        (item) => item.name === activeSection,
    );

    return (
        <AgentLayout
            title="Administration"
            subtitle="Configure your workspace and manage support operations."
        >
            <div className="as-page">
                <div className="as-heading-row">
                    <div>
                        <span className="as-section-label">WORKSPACE CONFIGURATION</span>
                        <h2>Settings</h2>
                        <p>Manage how your Voxera support workspace operates.</p>
                    </div>
                    <span className="as-demo-indicator">
                        <span />
                        DEMO CONFIGURATION
                    </span>
                </div>

                <div className="as-layout">
                    <aside className="as-navigation">
                        <div className="as-navigation-title">SETTINGS</div>

                        {settingsNavigation.map((item) => (
                            <button
                                type="button"
                                key={item.name}
                                className={`as-nav-item${activeSection === item.name ? ' active' : ''
                                    }`}
                                onClick={() => {
                                    setActiveSection(item.name);
                                    setSaveMessage('');
                                }}
                            >
                                <span className="as-nav-icon">{item.icon}</span>
                                <span className="as-nav-copy">
                                    <strong>{item.name}</strong>
                                    <small>{item.description}</small>
                                </span>
                                <span className="as-nav-arrow">›</span>
                            </button>
                        ))}

                        <div className="as-navigation-note">
                            <span>ⓘ</span>
                            Settings are currently managed in demo mode.
                        </div>
                    </aside>

                    <section className="as-panel">
                        <div className="as-panel-heading">
                            <div>
                                <h2>{activeSection}</h2>
                                <p>{currentNavigation?.description}</p>
                            </div>
                            <span className="as-panel-icon">
                                {currentNavigation?.icon}
                            </span>
                        </div>

                        {activeSection === 'General' && (
                            <div className="as-section-content">
                                <div className="as-group-heading">
                                    <h3>Workspace information</h3>
                                    <p>Basic details used to identify your support workspace.</p>
                                </div>

                                <div className="as-form-grid">
                                    <div className="as-field">
                                        <label htmlFor="as-workspace">Workspace name</label>
                                        <input
                                            id="as-workspace"
                                            value={settings.workspaceName}
                                            onChange={(event) =>
                                                updateSetting('workspaceName', event.target.value)
                                            }
                                        />
                                    </div>

                                    <div className="as-field">
                                        <label htmlFor="as-organization">Organization</label>
                                        <input
                                            id="as-organization"
                                            value={settings.organization}
                                            onChange={(event) =>
                                                updateSetting('organization', event.target.value)
                                            }
                                        />
                                    </div>

                                    <div className="as-field as-field-full">
                                        <label htmlFor="as-timezone">Workspace timezone</label>
                                        <select
                                            id="as-timezone"
                                            value={settings.timezone}
                                            onChange={(event) =>
                                                updateSetting('timezone', event.target.value)
                                            }
                                        >
                                            <option value="Asia/Kolkata">
                                                India Standard Time (UTC+05:30)
                                            </option>
                                            <option value="Europe/London">
                                                London (UTC+00:00 / UTC+01:00)
                                            </option>
                                            <option value="Asia/Singapore">
                                                Singapore (UTC+08:00)
                                            </option>
                                            <option value="America/New_York">
                                                Eastern Time (UTC-05:00 / UTC-04:00)
                                            </option>
                                            <option value="UTC">Coordinated Universal Time (UTC)</option>
                                        </select>
                                        <small>
                                            Used for displaying ticket timestamps and activity.
                                        </small>
                                    </div>
                                </div>
                            </div>
                        )}

                        {activeSection === 'Tickets' && (
                            <div className="as-section-content">
                                <div className="as-group-heading">
                                    <h3>Ticket defaults</h3>
                                    <p>Set the default values used when new tickets are created.</p>
                                </div>

                                <div className="as-form-grid">
                                    <div className="as-field">
                                        <label htmlFor="as-prefix">Ticket number prefix</label>
                                        <input
                                            id="as-prefix"
                                            maxLength={6}
                                            value={settings.ticketPrefix}
                                            onChange={(event) =>
                                                updateSetting(
                                                    'ticketPrefix',
                                                    event.target.value.toUpperCase(),
                                                )
                                            }
                                        />
                                        <small>Example: {settings.ticketPrefix || 'VXR'}-1053</small>
                                    </div>

                                    <div className="as-field">
                                        <label htmlFor="as-priority">Default priority</label>
                                        <select
                                            id="as-priority"
                                            value={settings.defaultPriority}
                                            onChange={(event) =>
                                                updateSetting(
                                                    'defaultPriority',
                                                    event.target.value as VoxeraSettings['defaultPriority'],
                                                )
                                            }
                                        >
                                            <option value="Low">Low</option>
                                            <option value="Medium">Medium</option>
                                            <option value="High">High</option>
                                            <option value="Urgent">Urgent</option>
                                        </select>
                                    </div>

                                    <div className="as-field as-field-full">
                                        <label htmlFor="as-categories">Ticket categories</label>
                                        <textarea
                                            id="as-categories"
                                            rows={3}
                                            value={settings.categories}
                                            onChange={(event) =>
                                                updateSetting('categories', event.target.value)
                                            }
                                        />
                                        <small>
                                            Separate categories with commas. This is demo
                                            configuration and does not change the ticket forms.
                                        </small>
                                    </div>
                                </div>
                            </div>
                        )}

                        {activeSection === 'Workflow' && (
                            <div className="as-section-content">
                                <div className="as-group-heading">
                                    <h3>Ticket lifecycle</h3>
                                    <p>Configure basic behavior for ticket handling.</p>
                                </div>

                                <div className="as-static-workflow">
                                    <span>AVAILABLE STATUSES</span>
                                    <div className="as-status-list">
                                        {['Open', 'In Progress', 'Pending', 'Resolved', 'Closed', 'Cancelled'].map(
                                            (status) => (
                                                <span key={status} className="as-status-chip">
                                                    <span />
                                                    {status}
                                                </span>
                                            ),
                                        )}
                                    </div>
                                    <small>
                                        These are the current Voxera ticket statuses. Their
                                        lifecycle definitions are not editable here.
                                    </small>
                                </div>

                                <div className="as-setting-list">
                                    <div className="as-setting-row">
                                        <div>
                                            <strong>Allow ticket reopening</strong>
                                            <p>
                                                Allow resolved tickets to be reopened when further
                                                attention is needed.
                                            </p>
                                        </div>
                                        <button
                                            type="button"
                                            role="switch"
                                            aria-checked={settings.allowReopening}
                                            className={`as-switch${settings.allowReopening ? ' enabled' : ''
                                                }`}
                                            onClick={() => toggleSetting('allowReopening')}
                                        >
                                            <span />
                                        </button>
                                    </div>

                                    <div className="as-setting-row">
                                        <div>
                                            <strong>Require assignment</strong>
                                            <p>
                                                Indicate that tickets should be assigned to an agent
                                                before work begins.
                                            </p>
                                        </div>
                                        <button
                                            type="button"
                                            role="switch"
                                            aria-checked={settings.requireAssignment}
                                            className={`as-switch${settings.requireAssignment ? ' enabled' : ''
                                                }`}
                                            onClick={() => toggleSetting('requireAssignment')}
                                        >
                                            <span />
                                        </button>
                                    </div>
                                </div>
                            </div>
                        )}

                        {activeSection === 'Notifications' && (
                            <div className="as-section-content">
                                <div className="as-group-heading">
                                    <h3>Notification preferences</h3>
                                    <p>Choose which workspace events should generate notifications.</p>
                                </div>

                                <div className="as-setting-list">
                                    <div className="as-setting-row">
                                        <div>
                                            <strong>New ticket created</strong>
                                            <p>Notify when a new support ticket is submitted.</p>
                                        </div>
                                        <button
                                            type="button"
                                            role="switch"
                                            aria-checked={settings.notifyNewTicket}
                                            className={`as-switch${settings.notifyNewTicket ? ' enabled' : ''}`}
                                            onClick={() => toggleSetting('notifyNewTicket')}
                                        >
                                            <span />
                                        </button>
                                    </div>

                                    <div className="as-setting-row">
                                        <div>
                                            <strong>Ticket assigned</strong>
                                            <p>Notify the relevant user when a ticket is assigned.</p>
                                        </div>
                                        <button
                                            type="button"
                                            role="switch"
                                            aria-checked={settings.notifyAssignment}
                                            className={`as-switch${settings.notifyAssignment ? ' enabled' : ''}`}
                                            onClick={() => toggleSetting('notifyAssignment')}
                                        >
                                            <span />
                                        </button>
                                    </div>

                                    <div className="as-setting-row">
                                        <div>
                                            <strong>Ticket status changed</strong>
                                            <p>Notify when a ticket moves to another status.</p>
                                        </div>
                                        <button
                                            type="button"
                                            role="switch"
                                            aria-checked={settings.notifyStatusChange}
                                            className={`as-switch${settings.notifyStatusChange ? ' enabled' : ''}`}
                                            onClick={() => toggleSetting('notifyStatusChange')}
                                        >
                                            <span />
                                        </button>
                                    </div>
                                </div>

                                <div className="as-group-heading as-group-secondary">
                                    <h3>Delivery channels</h3>
                                    <p>Choose the channels enabled for workspace notifications.</p>
                                </div>

                                <div className="as-setting-list">
                                    <div className="as-setting-row">
                                        <div>
                                            <strong>Email notifications</strong>
                                            <p>Notification delivery through email.</p>
                                        </div>
                                        <button
                                            type="button"
                                            role="switch"
                                            aria-checked={settings.notifyByEmail}
                                            className={`as-switch${settings.notifyByEmail ? ' enabled' : ''}`}
                                            onClick={() => toggleSetting('notifyByEmail')}
                                        >
                                            <span />
                                        </button>
                                    </div>

                                    <div className="as-setting-row">
                                        <div>
                                            <strong>In-app notifications</strong>
                                            <p>Notification delivery inside the Voxera workspace.</p>
                                        </div>
                                        <button
                                            type="button"
                                            role="switch"
                                            aria-checked={settings.notifyInApp}
                                            className={`as-switch${settings.notifyInApp ? ' enabled' : ''}`}
                                            onClick={() => toggleSetting('notifyInApp')}
                                        >
                                            <span />
                                        </button>
                                    </div>
                                </div>
                            </div>
                        )}

                        {activeSection === 'Voice Agent' && (
                            <div className="as-section-content">
                                <div className="as-group-heading">
                                    <h3>Voice support</h3>
                                    <p>Configure the basic voice support experience.</p>
                                </div>

                                <div className="as-setting-row as-voice-enable">
                                    <div>
                                        <strong>Enable voice support</strong>
                                        <p>
                                            Show voice support as an available workspace capability.
                                        </p>
                                    </div>
                                    <button
                                        type="button"
                                        role="switch"
                                        aria-checked={settings.voiceEnabled}
                                        className={`as-switch${settings.voiceEnabled ? ' enabled' : ''}`}
                                        onClick={() => toggleSetting('voiceEnabled')}
                                    >
                                        <span />
                                    </button>
                                </div>

                                <div className="as-form-grid as-voice-fields">
                                    <div className="as-field as-field-full">
                                        <label htmlFor="as-greeting">Welcome greeting</label>
                                        <textarea
                                            id="as-greeting"
                                            rows={3}
                                            value={settings.voiceGreeting}
                                            disabled={!settings.voiceEnabled}
                                            onChange={(event) =>
                                                updateSetting('voiceGreeting', event.target.value)
                                            }
                                        />
                                    </div>

                                    <div className="as-field">
                                        <label htmlFor="as-language">Language</label>
                                        <select
                                            id="as-language"
                                            value={settings.voiceLanguage}
                                            disabled={!settings.voiceEnabled}
                                            onChange={(event) =>
                                                updateSetting('voiceLanguage', event.target.value)
                                            }
                                        >
                                            <option value="English">English</option>
                                            <option value="Hindi">Hindi</option>
                                        </select>
                                    </div>
                                </div>

                                <div className="as-voice-note">
                                    <span>ⓘ</span>
                                    These preferences are illustrative. No voice provider,
                                    speech service, or live agent connection is configured here.
                                </div>
                            </div>
                        )}

                        <div className="as-save-footer">
                            <div>
                                {saveMessage && (
                                    <span className="as-save-message">
                                        <span>✓</span>
                                        {saveMessage}
                                    </span>
                                )}
                            </div>
                            <button
                                type="button"
                                className="as-save-button"
                                onClick={saveSettings}
                            >
                                Save Changes <span>→</span>
                            </button>
                        </div>
                    </section>
                </div>
            </div>
        </AgentLayout>
    );
}