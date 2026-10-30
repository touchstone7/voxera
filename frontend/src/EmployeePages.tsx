import { useState, type FormEvent } from 'react';
import { Link, NavLink, useNavigate, useParams } from 'react-router-dom';
import './employee.css';

type TicketStatus = 'Open' | 'In Progress' | 'Pending' | 'Resolved';
type TicketPriority = 'Low' | 'Medium' | 'High' | 'Urgent';

type Ticket = {
  id: string;
  title: string;
  category: string;
  status: TicketStatus;
  priority: TicketPriority;
  created: string;
  description: string;
};

const initialTickets: Ticket[] = [
  {
    id: 'VXR-1048',
    title: 'Unable to connect to office VPN',
    category: 'Network',
    status: 'In Progress',
    priority: 'High',
    created: 'Oct 28, 2026',
    description: 'The office VPN disconnects shortly after connecting. I have restarted my laptop and retried the connection.',
  },
  {
    id: 'VXR-1042',
    title: 'Access required for project repository',
    category: 'Access',
    status: 'Open',
    priority: 'Medium',
    created: 'Oct 27, 2026',
    description: 'I need read access to the project repository to continue my assigned work.',
  },
  {
    id: 'VXR-1031',
    title: 'Laptop performance issue',
    category: 'Hardware',
    status: 'Pending',
    priority: 'Low',
    created: 'Oct 24, 2026',
    description: 'My laptop has become slow when multiple applications are running.',
  },
  {
    id: 'VXR-1019',
    title: 'Email configuration fixed',
    category: 'Software',
    status: 'Resolved',
    priority: 'Low',
    created: 'Oct 20, 2026',
    description: 'The email client configuration issue has been resolved.',
  },
];

const navigation = [
  { to: '/employee/dashboard', label: 'Overview', icon: '◫', end: true },
  { to: '/employee/tickets', label: 'My Tickets', icon: '▤', end: false },
  { to: '/employee/tickets/new', label: 'Create Ticket', icon: '+', end: true },
  { to: '/employee/notifications', label: 'Notifications', icon: '♧', end: true },
  { to: '/employee/profile', label: 'Profile & Settings', icon: '◎', end: true },
];

function EmployeeLayout({
  children,
  title,
  subtitle,
}: {
  children: React.ReactNode;
  title: string;
  subtitle: string;
}) {
  return (
    <div className="employee-app">
      <aside className="employee-sidebar">
        <Link to="/employee/dashboard" className="employee-brand">
          <span className="employee-brand-mark">v.</span>
          <span>voxera<span className="employee-brand-dot">.</span></span>
        </Link>

        <div className="employee-workspace-label">WORKSPACE</div>
        <nav className="employee-nav" aria-label="Employee navigation">
          {navigation.map((item) => (
            <NavLink
              key={item.to}
              to={item.to}
              end={item.end}
              className={({ isActive }) =>
                `employee-nav-link${isActive ? ' active' : ''}`
              }
            >
              <span className="employee-nav-icon">{item.icon}</span>
              <span>{item.label}</span>
              {item.to === '/employee/notifications' && (
                <span className="employee-nav-count">2</span>
              )}
            </NavLink>
          ))}
        </nav>

        <div className="employee-sidebar-bottom">
          <div className="employee-help-card">
            <span className="employee-help-icon">✳</span>
            <strong>Need a hand?</strong>
            <p>Get help with your IT issue.</p>
            <Link to="/employee/tickets/new">Raise a ticket →</Link>
          </div>
          <div className="employee-user">
            <div className="employee-avatar">JD</div>
            <div className="employee-user-info">
              <strong>Jordan Davis</strong>
              <span>Employee account</span>
            </div>
            <Link to="/employee/profile" aria-label="Open profile">···</Link>
          </div>
        </div>
      </aside>

      <main className="employee-main">
        <header className="employee-topbar">
          <div className="employee-breadcrumb">
            <span>Workspace</span><span>/</span><strong>{title}</strong>
          </div>
          <div className="employee-top-actions">
            <span className="employee-demo-label"><span /> DEMO MODE</span>
            <Link
              to="/employee/notifications"
              className="employee-bell"
              aria-label="Notifications"
            >
              ♧<span />
            </Link>
            <div className="employee-avatar small">JD</div>
          </div>
        </header>

        <section className="employee-content">
          <div className="employee-page-heading">
            <div>
              <div className="employee-eyebrow">EMPLOYEE WORKSPACE</div>
              <h1>{title}</h1>
              <p>{subtitle}</p>
            </div>
            <Link className="employee-primary-button" to="/employee/tickets/new">
              <span>+</span> Create ticket
            </Link>
          </div>
          {children}
          <footer className="employee-footer">
            <span>© 2026 Voxera</span>
            <span>Demo workspace · No backend connected</span>
          </footer>
        </section>
      </main>
    </div>
  );
}

function StatusBadge({ status }: { status: TicketStatus }) {
  return <span className={`ticket-status status-${status.toLowerCase().replace(' ', '-')}`}>{status}</span>;
}

function PriorityBadge({ priority }: { priority: TicketPriority }) {
  return <span className={`ticket-priority priority-${priority.toLowerCase()}`}><span />{priority}</span>;
}

function TicketTable({ tickets }: { tickets: Ticket[] }) {
  if (!tickets.length) {
    return (
      <div className="employee-empty-state">
        <span>▤</span>
        <h3>No tickets found</h3>
        <p>Try changing your filters or create a new support ticket.</p>
        <Link className="employee-primary-button" to="/employee/tickets/new">Create ticket</Link>
      </div>
    );
  }

  return (
    <div className="employee-table-wrap">
      <table className="employee-table">
        <thead>
          <tr>
            <th>Ticket</th><th>Status</th><th>Priority</th><th>Created</th><th />
          </tr>
        </thead>
        <tbody>
          {tickets.map((ticket) => (
            <tr key={ticket.id}>
              <td>
                <Link className="ticket-title-link" to={`/employee/tickets/${ticket.id}`}>
                  <strong>{ticket.title}</strong>
                  <span>{ticket.id} · {ticket.category}</span>
                </Link>
              </td>
              <td><StatusBadge status={ticket.status} /></td>
              <td><PriorityBadge priority={ticket.priority} /></td>
              <td className="ticket-date">{ticket.created}</td>
              <td><Link className="ticket-open-link" to={`/employee/tickets/${ticket.id}`} aria-label={`Open ${ticket.id}`}>↗</Link></td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export function EmployeeDashboard() {
  const [tickets] = useState(initialTickets);
  const active = tickets.filter((ticket) => ticket.status !== 'Resolved').length;
  const inProgress = tickets.filter((ticket) => ticket.status === 'In Progress').length;
  const resolved = tickets.filter((ticket) => ticket.status === 'Resolved').length;

  return (
    <EmployeeLayout title="Overview" subtitle="Here's what's happening with your IT support today.">
      <div className="employee-welcome">
        <div>
          <div className="employee-welcome-label">THURSDAY, OCTOBER 29, 2026</div>
          <h2>Good to see you, Jordan <span>✳</span></h2>
          <p>Need something fixed? Your support workspace is ready.</p>
          <Link to="/employee/tickets/new" className="employee-welcome-button">Get support <span>→</span></Link>
        </div>
        <div className="employee-welcome-art" aria-hidden="true">
          <div className="welcome-orbit orbit-one" />
          <div className="welcome-orbit orbit-two" />
          <div className="welcome-orb">v<span>.</span></div>
          <div className="welcome-wave">{Array.from({ length: 21 }, (_, i) => <i key={i} style={{ height: `${12 + Math.abs(Math.sin(i * 0.8)) * 37}px` }} />)}</div>
        </div>
      </div>

      <div className="employee-section-heading">
        <div><h2>Your support at a glance</h2><p>A quick overview of your requests.</p></div>
        <Link to="/employee/tickets">View all tickets →</Link>
      </div>

      <div className="employee-stats-grid">
        <div className="employee-stat-card">
          <div className="employee-stat-top"><span className="stat-icon purple">▤</span><span className="stat-trend">All time</span></div>
          <span className="employee-stat-number">{tickets.length}</span>
          <span className="employee-stat-label">Total tickets</span>
        </div>
        <div className="employee-stat-card">
          <div className="employee-stat-top"><span className="stat-icon blue">◷</span><span className="stat-caption">Needs attention</span></div>
          <span className="employee-stat-number">{active}</span>
          <span className="employee-stat-label">Active tickets</span>
        </div>
        <div className="employee-stat-card">
          <div className="employee-stat-top"><span className="stat-icon amber">↻</span><span className="stat-caption">Being handled</span></div>
          <span className="employee-stat-number">{inProgress}</span>
          <span className="employee-stat-label">In progress</span>
        </div>
        <div className="employee-stat-card">
          <div className="employee-stat-top"><span className="stat-icon green">✓</span><span className="stat-caption">Completed</span></div>
          <span className="employee-stat-number">{resolved}</span>
          <span className="employee-stat-label">Resolved tickets</span>
        </div>
      </div>

      <div className="employee-section-heading recent-heading">
        <div><h2>Recent tickets</h2><p>Track your latest support requests.</p></div>
        <Link to="/employee/tickets">See all →</Link>
      </div>
      <TicketTable tickets={tickets.slice(0, 3)} />

      <div className="employee-bottom-grid">
        <div className="employee-panel">
          <div className="employee-panel-heading"><h3>Quick actions</h3><span>✳</span></div>
          <Link to="/employee/tickets/new" className="employee-quick-action"><span className="quick-icon">＋</span><span><strong>Raise a ticket</strong><small>Tell us what's not working</small></span><b>→</b></Link>
          <Link to="/employee/notifications" className="employee-quick-action"><span className="quick-icon">♧</span><span><strong>View notifications</strong><small>See updates on your requests</small></span><b>→</b></Link>
        </div>
        <div className="employee-panel employee-assist-panel">
          <div className="employee-assist-symbol">✳</div>
          <div className="employee-assist-eyebrow">VOXERA ASSIST</div>
          <h3>Support starts with a conversation.</h3>
          <p>Voice support will help you describe an issue naturally. This experience is planned for a future milestone.</p>
          <span className="employee-coming-soon">COMING SOON</span>
        </div>
      </div>
    </EmployeeLayout>
  );
}

export function MyTicketsPage() {
  const [tickets] = useState(initialTickets);
  const [status, setStatus] = useState('All');
  const [search, setSearch] = useState('');
  const filtered = tickets.filter((ticket) =>
    (status === 'All' || ticket.status === status) &&
    `${ticket.id} ${ticket.title} ${ticket.category}`.toLowerCase().includes(search.toLowerCase())
  );

  return (
    <EmployeeLayout title="My Tickets" subtitle="View and track all your IT support requests.">
      <div className="employee-filter-bar">
        <input value={search} onChange={(event) => setSearch(event.target.value)} placeholder="Search tickets..." aria-label="Search tickets" />
        <select value={status} onChange={(event) => setStatus(event.target.value)} aria-label="Filter by status">
          <option>All</option><option>Open</option><option>In Progress</option><option>Pending</option><option>Resolved</option>
        </select>
      </div>
      <TicketTable tickets={filtered} />
      <p className="employee-demo-note">Showing sample tickets for demonstration. Ticket data is not loaded from the backend.</p>
    </EmployeeLayout>
  );
}

export function CreateTicketPage() {
  const navigate = useNavigate();
  const [message, setMessage] = useState('');

  function submit(event: FormEvent<HTMLFormElement>) {
    event.preventDefault();
    setMessage('Demo ticket created locally. It has not been sent to the support system.');
  }

  return (
    <EmployeeLayout title="Create a Ticket" subtitle="Describe your issue and we'll help you find the right support.">
      <div className="employee-form-layout">
        <form className="employee-panel employee-ticket-form" onSubmit={submit}>
          <div className="employee-form-section-title"><span>01</span><div><h2>Tell us about the issue</h2><p>Fields marked with * are required.</p></div></div>
          <label htmlFor="ticket-title">Issue title *</label>
          <input id="ticket-title" name="title" placeholder="e.g. Unable to connect to VPN" required maxLength={120} />
          <label htmlFor="ticket-category">Category *</label>
          <select id="ticket-category" name="category" required defaultValue="">
            <option value="" disabled>Select a category</option><option>Hardware</option><option>Software</option><option>Network</option><option>Access</option><option>Email</option><option>Other</option>
          </select>
          <label htmlFor="ticket-priority">Priority *</label>
          <select id="ticket-priority" name="priority" defaultValue="Medium" required>
            <option>Low</option><option>Medium</option><option>High</option><option>Urgent</option>
          </select>
          <label htmlFor="ticket-description">Description *</label>
          <textarea id="ticket-description" name="description" rows={6} placeholder="Describe what happened, what you expected, and any steps you've already tried..." required maxLength={3000} />
          <div className="employee-form-hint">Tip: Include any error messages and what you were doing when the issue occurred.</div>
          <div className="employee-form-actions">
            <button type="button" className="employee-secondary-button" onClick={() => navigate('/employee/tickets')}>Cancel</button>
            <button type="submit" className="employee-primary-button">Submit ticket <span>→</span></button>
          </div>
          {message && <p className="employee-form-message" role="status">{message}</p>}
        </form>
        <aside className="employee-panel employee-ticket-aside">
          <span className="employee-aside-icon">✳</span><h3>What happens next?</h3>
          <ol><li><span>1</span><div><strong>We receive your request</strong><p>Your issue is recorded with the details you provide.</p></div></li><li><span>2</span><div><strong>Support reviews it</strong><p>The support team assesses priority and next steps.</p></div></li><li><span>3</span><div><strong>Track progress</strong><p>Follow updates from your My Tickets page.</p></div></li></ol>
          <p className="employee-demo-note">This is a preview of the intended workflow, not a live submission.</p>
        </aside>
      </div>
    </EmployeeLayout>
  );
}

export function TicketDetailsPage() {
  const { ticketId } = useParams();
  const ticket = initialTickets.find((item) => item.id.toLowerCase() === ticketId?.toLowerCase());

  return (
    <EmployeeLayout title="Ticket Details" subtitle="Review the details and current status of your request.">
      <Link className="employee-back-link" to="/employee/tickets">← Back to My Tickets</Link>
      {ticket ? (
        <div className="employee-detail-layout">
          <section className="employee-panel employee-ticket-detail">
            <div className="employee-detail-title-row"><div><span className="employee-detail-id">{ticket.id}</span><h2>{ticket.title}</h2></div><StatusBadge status={ticket.status} /></div>
            <div className="employee-detail-meta"><span>Category <strong>{ticket.category}</strong></span><span>Priority <PriorityBadge priority={ticket.priority} /></span><span>Created <strong>{ticket.created}</strong></span></div>
            <div className="employee-detail-section"><h3>Description</h3><p>{ticket.description}</p></div>
            <div className="employee-detail-section"><h3>Activity</h3><div className="employee-activity-item"><span className="activity-dot" /><div><strong>Ticket submitted</strong><p>Your request was recorded in this demo.</p><small>{ticket.created}</small></div></div>{ticket.status !== 'Open' && <div className="employee-activity-item"><span className="activity-dot violet" /><div><strong>Status: {ticket.status}</strong><p>This activity is illustrative sample data.</p><small>Latest update · Demo</small></div></div>}</div>
          </section>
          <aside className="employee-panel employee-ticket-aside"><h3>Request information</h3><div className="employee-info-line"><span>Ticket ID</span><strong>{ticket.id}</strong></div><div className="employee-info-line"><span>Requester</span><strong>Jordan Davis</strong></div><div className="employee-info-line"><span>Assigned team</span><strong>IT Support</strong></div><div className="employee-info-line"><span>Assigned agent</span><strong>Not assigned</strong></div><p className="employee-demo-note">Assignment details are placeholders in this demo.</p></aside>
        </div>
      ) : (
        <div className="employee-empty-state"><span>⌕</span><h3>Ticket not found</h3><p>This demo contains only a few sample tickets.</p><Link className="employee-primary-button" to="/employee/tickets">Browse tickets</Link></div>
      )}
    </EmployeeLayout>
  );
}

export function NotificationsPage() {
  const [read, setRead] = useState<string[]>([]);
  const notifications = [
    { id: 'n1', icon: '↻', title: 'Your VPN issue is in progress', detail: 'VXR-1048 · A support agent is reviewing your request.', time: '2 hours ago' },
    { id: 'n2', icon: '✓', title: 'Ticket VXR-1019 was resolved', detail: 'Email configuration fixed · Marked as resolved.', time: 'Yesterday' },
    { id: 'n3', icon: '✳', title: 'Welcome to Voxera', detail: 'Use your workspace to create and track support tickets.', time: '3 days ago' },
  ];

  return (
    <EmployeeLayout title="Notifications" subtitle="Stay up to date with your support requests.">
      <div className="employee-notification-toolbar"><span>{notifications.length - read.length} unread notifications</span><button className="employee-secondary-button" onClick={() => setRead(notifications.map((item) => item.id))}>Mark all as read</button></div>
      <div className="employee-notification-list">
        {notifications.map((item) => (
          <article key={item.id} className={`employee-notification${read.includes(item.id) ? ' is-read' : ''}`}>
            <span className="employee-notification-icon">{item.icon}</span>
            <div className="employee-notification-body"><h3>{item.title}</h3><p>{item.detail}</p><span>{item.time}</span></div>
            {!read.includes(item.id) && <button className="employee-mark-read" onClick={() => setRead((current) => [...current, item.id])}>Mark read</button>}
          </article>
        ))}
      </div>
      <p className="employee-demo-note">Notifications are sample content. No live alerts are being received.</p>
    </EmployeeLayout>
  );
}

export function ProfilePage() {
  const [saved, setSaved] = useState(false);

  function submit(event: FormEvent<HTMLFormElement>) {
    event.preventDefault();
    setSaved(true);
  }

  return (
    <EmployeeLayout title="Profile & Settings" subtitle="Manage your personal details and workspace preferences.">
      <div className="employee-profile-card employee-panel"><div className="employee-profile-avatar">JD</div><div><h2>Jordan Davis</h2><p>jordan.davis@example.com</p><span>EMPLOYEE ACCOUNT</span></div><span className="employee-profile-status"><i /> Active demo profile</span></div>
      <form className="employee-panel employee-profile-form" onSubmit={submit}>
        <div className="employee-form-section-title"><span>01</span><div><h2>Personal information</h2><p>Update the profile details shown in your workspace.</p></div></div>
        <div className="employee-profile-fields"><div><label htmlFor="profile-name">Full name</label><input id="profile-name" defaultValue="Jordan Davis" required /></div><div><label htmlFor="profile-email">Work email</label><input id="profile-email" type="email" defaultValue="jordan.davis@example.com" required /></div><div><label htmlFor="profile-id">Employee ID</label><input id="profile-id" value="EMP-2026-0142" readOnly /></div><div><label htmlFor="profile-team">Department</label><input id="profile-team" defaultValue="Engineering" /></div></div>
        <div className="employee-form-section-title profile-preferences-title"><span>02</span><div><h2>Preferences</h2><p>Choose how you prefer to receive updates.</p></div></div>
        <label className="employee-preference"><input type="checkbox" defaultChecked /><span><strong>Email notifications</strong><small>Receive updates about ticket status changes.</small></span></label>
        <label className="employee-preference"><input type="checkbox" defaultChecked /><span><strong>Product announcements</strong><small>Hear about new features and improvements.</small></span></label>
        <div className="employee-form-actions"><button className="employee-primary-button" type="submit">Save changes <span>→</span></button></div>
        {saved && <p className="employee-form-message" role="status">Demo only: changes have not been saved to an account.</p>}
      </form>
      <p className="employee-demo-note">Profile values and preferences are for demonstration. No account data is being read or updated.</p>
    </EmployeeLayout>
  );
}
