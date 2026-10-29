import { useState, type FormEvent } from 'react';
import {
  BrowserRouter,
  Link,
  Navigate,
  Route,
  Routes,
  useLocation,
  useNavigate,
} from 'react-router-dom';
import './App.css';

type Provider = 'Google' | 'Apple';

function BrandPanel() {
  return (
    <aside className="brand-panel">
      <Link className="brand" to="/login" aria-label="Voxera home">
        <span className="brand-mark" aria-hidden="true">
          <span />
          <span />
          <span />
          <span />
          <span />
        </span>
        <span>voxera<span className="brand-dot">.</span></span>
      </Link>

      <div className="brand-content">
        <div className="eyebrow"><span className="eyebrow-dot" /> THE FUTURE OF IT SUPPORT</div>
        <h1>Support that <span>speaks your</span> language.</h1>
        <p>Meet a smarter way to solve problems. Human-centered support, powered by AI and built around real conversations.</p>
        <div className="waveform" aria-label="Decorative audio waveform">
          {Array.from({ length: 57 }, (_, i) => (
            <span key={i} style={{ height: `${18 + Math.abs(Math.sin(i * 1.7) * Math.cos(i * 0.31)) * 62}px` }} />
          ))}
        </div>
        <div className="brand-caption"><span>✳</span> Intelligent support. Real conversations.</div>
      </div>

      <footer className="brand-footer">
        <span>© 2026 Voxera</span>
        <span>Support, reimagined.</span>
      </footer>
    </aside>
  );
}

function GoogleIcon() {
  return <svg className="provider-icon" viewBox="0 0 48 48" aria-hidden="true">
    <path fill="#4285F4" d="M43.6 24.5c0-1.4-.1-2.8-.4-4.1H24v7.8h11a9.4 9.4 0 0 1-4.1 6.2v5.1h6.6c3.9-3.6 6.1-8.8 6.1-15Z" />
    <path fill="#34A853" d="M24 44c5.5 0 10.1-1.8 13.5-4.8l-6.6-5.1c-1.8 1.2-4 1.9-6.9 1.9-5.3 0-9.8-3.6-11.4-8.4H5.8v5.3A20 20 0 0 0 24 44Z" />
    <path fill="#FBBC05" d="M12.6 27.6a12 12 0 0 1 0-7.2v-5.3H5.8a20 20 0 0 0 0 17.8l6.8-5.3Z" />
    <path fill="#EA4335" d="M24 12c3 0 5.7 1 7.8 3.1l5.8-5.8C34.1 6 29.5 4 24 4A20 20 0 0 0 5.8 15.1l6.8 5.3C14.2 15.6 18.7 12 24 12Z" />
  </svg>;
}

function AppleIcon() {
  return <svg className="provider-icon apple-icon" viewBox="0 0 24 24" aria-hidden="true">
    <path fill="currentColor" d="M17.1 12.8c0-2.3 1.9-3.4 2-3.5a4.4 4.4 0 0 0-3.5-1.9c-1.5-.2-3 .9-3.8.9-.8 0-2- .9-3.3-.9a4.9 4.9 0 0 0-4.1 2.5c-1.8 3.1-.5 7.7 1.3 10.2.9 1.2 1.9 2.5 3.2 2.4 1.3 0 1.8-.8 3.4-.8s2.1.8 3.4.8c1.4 0 2.2-1.2 3.1-2.5a11 11 0 0 0 1.4-2.9 4.1 4.1 0 0 1-3.1-4.3ZM14.6 5.8a4.2 4.2 0 0 0 1-3.1 4.4 4.4 0 0 0-2.9 1.5 4 4 0 0 0-1 3 3.7 3.7 0 0 0 2.9-1.4Z" />
  </svg>;
}

function SocialButtons() {
  const navigate = useNavigate();

  function continueWith(provider: Provider) {
    navigate('/auth/callback', { state: { provider } });
  }

  return (
    <>
      <div className="divider-label"><span /> OR CONTINUE WITH <span /></div>
      <div className="social-buttons">
        <button className="social-button" type="button" onClick={() => continueWith('Google')}>
          <GoogleIcon /> Google
        </button>
        <button className="social-button" type="button" onClick={() => continueWith('Apple')}>
          <AppleIcon /> Apple
        </button>
      </div>
    </>
  );
}

function AuthLayout({ children }: { children: React.ReactNode }) {
  return (
    <main className="auth-layout">
      <BrandPanel />
      <section className="form-panel">
        <div className="mobile-brand">
          <span className="brand-mark"><span /><span /><span /><span /><span /></span>
          voxera<span className="brand-dot">.</span>
        </div>
        <div className="auth-card">{children}</div>
        <footer className="form-footer">
          <a href="https://example.com/privacy" onClick={(e) => { e.preventDefault(); alert('Privacy information will be added later.'); }}>Privacy</a>
          <span>·</span>
          <a href="https://example.com/terms" onClick={(e) => { e.preventDefault(); alert('Terms will be added later.'); }}>Terms</a>
          <span>·</span>
          <a href="mailto:support@example.com" onClick={(e) => { e.preventDefault(); alert('Support contact will be configured later.'); }}>Need help?</a>
        </footer>
      </section>
    </main>
  );
}

function LoginPage() {
  const [showPassword, setShowPassword] = useState(false);
  const [message, setMessage] = useState('');
  const [remember, setRemember] = useState(true);

  function submit(event: FormEvent<HTMLFormElement>) {
    event.preventDefault();
    setMessage('Demo mode: your details were not sent anywhere. Backend authentication is not connected.');
  }

  return (
    <AuthLayout>
      <div className="eyebrow">WELCOME BACK</div>
      <h2>Sign in to Voxera</h2>
      <p className="subtitle">Pick up where you left off.</p>

      <form onSubmit={submit} className="auth-form">
        <label htmlFor="login-email">Work email</label>
        <div className="input-wrap">
          <span className="input-icon">✉</span>
          <input id="login-email" name="email" type="email" placeholder="you@company.com" autoComplete="email" required />
        </div>

        <div className="label-row">
          <label htmlFor="login-password">Password</label>
          <Link to="/forgot-password">Forgot password?</Link>
        </div>
        <div className="input-wrap">
          <span className="input-icon">▣</span>
          <input id="login-password" name="password" type={showPassword ? 'text' : 'password'} placeholder="Enter your password" autoComplete="current-password" required minLength={1} />
          <button className="show-password" type="button" onClick={() => setShowPassword(!showPassword)}>{showPassword ? 'Hide' : 'Show'}</button>
        </div>

        <label className="checkbox-label">
          <input type="checkbox" checked={remember} onChange={(e) => setRemember(e.target.checked)} />
          Keep me signed in
        </label>
        <button className="primary-button" type="submit">Sign in <span>→</span></button>
        {message && <p className="notice" role="status">{message}</p>}
      </form>

      <SocialButtons />
      <p className="switch-auth">New to Voxera? <Link to="/signup">Create an account <span>→</span></Link></p>
    </AuthLayout>
  );
}

function SignupPage() {
  const [message, setMessage] = useState('');
  const [showPassword, setShowPassword] = useState(false);

  function submit(event: FormEvent<HTMLFormElement>) {
    event.preventDefault();
    const data = new FormData(event.currentTarget);
    if (data.get('password') !== data.get('confirmPassword')) {
      setMessage('Your passwords do not match. Please try again.');
      return;
    }
    setMessage('Demo mode: your form is valid, but no account has been created.');
  }

  return (
    <AuthLayout>
      <div className="eyebrow">GET STARTED</div>
      <h2>Create your account</h2>
      <p className="subtitle">Bring smarter IT support to your workday.</p>
      <form className="auth-form" onSubmit={submit}>
        <label htmlFor="signup-name">Full name</label>
        <div className="input-wrap"><span className="input-icon">♙</span><input id="signup-name" name="name" placeholder="Your full name" autoComplete="name" required /></div>
        <label htmlFor="signup-email">Work email</label>
        <div className="input-wrap"><span className="input-icon">✉</span><input id="signup-email" name="email" type="email" placeholder="you@company.com" autoComplete="email" required /></div>
        <label htmlFor="signup-password">Password</label>
        <div className="input-wrap"><span className="input-icon">▣</span><input id="signup-password" name="password" type={showPassword ? 'text' : 'password'} placeholder="Create a password (8+ characters)" minLength={8} autoComplete="new-password" required /><button className="show-password" type="button" onClick={() => setShowPassword(!showPassword)}>{showPassword ? 'Hide' : 'Show'}</button></div>
        <label htmlFor="signup-confirm">Confirm password</label>
        <div className="input-wrap"><span className="input-icon">▣</span><input id="signup-confirm" name="confirmPassword" type="password" placeholder="Enter your password again" minLength={8} autoComplete="new-password" required /></div>
        <button className="primary-button" type="submit">Create account <span>→</span></button>
        {message && <p className="notice" role="status">{message}</p>}
      </form>
      <SocialButtons />
      <p className="switch-auth">Already have an account? <Link to="/login">Sign in <span>→</span></Link></p>
    </AuthLayout>
  );
}

function ForgotPasswordPage() {
  const [submitted, setSubmitted] = useState(false);

  function submit(event: FormEvent<HTMLFormElement>) {
    event.preventDefault();
    setSubmitted(true);
  }

  return (
    <AuthLayout>
      <Link to="/login" className="back-link">← Back to sign in</Link>
      <div className="eyebrow">ACCOUNT RECOVERY</div>
      <h2>Forgot your password?</h2>
      <p className="subtitle">No worries. Enter the email associated with your account and we'll guide you through the next step.</p>
      {submitted ? (
        <div className="success-panel" role="status">
          <span className="success-icon">✓</span>
          <h3>Check your email</h3>
          <p>This is a frontend demo. No email was sent. In the real flow, password-reset instructions would arrive here.</p>
          <Link className="primary-button button-link" to="/reset-password">Continue to reset password <span>→</span></Link>
          <button className="text-button" onClick={() => setSubmitted(false)}>Try another email</button>
        </div>
      ) : (
        <form className="auth-form" onSubmit={submit}>
          <label htmlFor="forgot-email">Work email</label>
          <div className="input-wrap"><span className="input-icon">✉</span><input id="forgot-email" name="email" type="email" placeholder="you@company.com" autoComplete="email" required /></div>
          <button className="primary-button" type="submit">Send reset instructions <span>→</span></button>
        </form>
      )}
      <p className="switch-auth">Remembered your password? <Link to="/login">Sign in</Link></p>
    </AuthLayout>
  );
}

function ResetPasswordPage() {
  const [message, setMessage] = useState('');
  const [reset, setReset] = useState(false);

  function submit(event: FormEvent<HTMLFormElement>) {
    event.preventDefault();
    const data = new FormData(event.currentTarget);
    if (data.get('newPassword') !== data.get('confirmPassword')) {
      setMessage('Your passwords do not match. Please try again.');
      return;
    }
    setReset(true);
    setMessage('');
  }

  return (
    <AuthLayout>
      <Link to="/forgot-password" className="back-link">← Back to password recovery</Link>
      <div className="eyebrow">SECURE YOUR ACCOUNT</div>
      <h2>Set a new password</h2>
      <p className="subtitle">Choose a new password for your Voxera account.</p>
      {reset ? (
        <div className="success-panel" role="status">
          <span className="success-icon">✓</span>
          <h3>Password updated in demo</h3>
          <p>No password was stored or changed. This confirms only that the frontend flow works.</p>
          <Link className="primary-button button-link" to="/login">Return to sign in <span>→</span></Link>
        </div>
      ) : (
        <form className="auth-form" onSubmit={submit}>
          <label htmlFor="new-password">New password</label>
          <div className="input-wrap"><span className="input-icon">▣</span><input id="new-password" name="newPassword" type="password" placeholder="At least 8 characters" minLength={8} autoComplete="new-password" required /></div>
          <label htmlFor="confirm-password">Confirm new password</label>
          <div className="input-wrap"><span className="input-icon">▣</span><input id="confirm-password" name="confirmPassword" type="password" placeholder="Enter it again" minLength={8} autoComplete="new-password" required /></div>
          <button className="primary-button" type="submit">Reset password <span>→</span></button>
          {message && <p className="notice" role="alert">{message}</p>}
        </form>
      )}
    </AuthLayout>
  );
}

function CallbackPage() {
  const location = useLocation();
  const provider = (location.state as { provider?: Provider } | null)?.provider;

  return (
    <AuthLayout>
      <div className="eyebrow">SIGN-IN DEMO</div>
      <h2>{provider ? `Continue with ${provider}` : 'Authentication callback'}</h2>
      <p className="subtitle">This page represents the return point after an external sign-in provider.</p>
      <div className="info-panel">
        <span className="info-icon">i</span>
        <div>
          <strong>{provider ? `${provider} sign-in isn't configured yet` : 'No provider information received'}</strong>
          <p>This is a frontend-only flow. No external authentication was started and no account was accessed.</p>
        </div>
      </div>
      <Link className="primary-button button-link" to="/login">Return to sign in <span>→</span></Link>
      <p className="switch-auth">New to Voxera? <Link to="/signup">Create an account</Link></p>
    </AuthLayout>
  );
}

function NotFoundPage() {
  return (
    <AuthLayout>
      <div className="eyebrow">404 · PAGE NOT FOUND</div>
      <h2>We couldn't find that page.</h2>
      <p className="subtitle">The address may be incorrect, or the page may have moved.</p>
      <Link className="primary-button button-link" to="/login">Back to sign in <span>→</span></Link>
    </AuthLayout>
  );
}

export default function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Navigate to="/login" replace />} />
        <Route path="/login" element={<LoginPage />} />
        <Route path="/signup" element={<SignupPage />} />
        <Route path="/forgot-password" element={<ForgotPasswordPage />} />
        <Route path="/reset-password" element={<ResetPasswordPage />} />
        <Route path="/auth/callback" element={<CallbackPage />} />
        <Route path="*" element={<NotFoundPage />} />
      </Routes>
    </BrowserRouter>
  );
}
