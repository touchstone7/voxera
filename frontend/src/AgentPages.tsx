import { useMemo, useState } from 'react';
import { Link, NavLink } from 'react-router-dom';
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
              {item.to === '/agent/queue' && (
                <span className="agent-nav-count">3</span>
              )}
            </NavLink>
          ))}

          <div className="agent-nav-section agent-nav-section-spaced">
            MANAGEMENT
          </div>
          <div className="agent-nav-static">
            <span className="agent-nav-icon">♙</span>
            <span>User Management</span>
            <span className="agent-coming-soon">ADMIN</span>
          </div>
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
              <p>Ticket detail actions and assignment will be available in the next workflow pages.</p>
            </div>

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