import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { login, register } from '../../services/AuthService';
import './Login.css';

function Login() {
  const [isRegister, setIsRegister] = useState(false);
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState('');
  const navigate = useNavigate();

  const handleLogin = async (e) => {
    e.preventDefault();
    setError('');

    try {
      const response = await login(username,password);

      const token = response.data.token;
      localStorage.setItem('token', token);
      navigate('/home');
    } catch (err) {
      setError('User/password are invalid!');
    }
  };

  const handleRegister = async (e) => {
  e.preventDefault();
  setError('');
    try {
      await register({ username, password });
      setIsRegister(false);
    } catch (err) {
      setError('Error during register. Try again later.');
    }
  };

  return (
    <div className="login-container">
      <div className="login-card">
        <h2 className="login-title">
          {isRegister ? 'Create Account' : 'Miscellaneous Store'}
        </h2>

        <form onSubmit={isRegister ? handleRegister : handleLogin} className="login-form">
          <input
            className="login-input"
            type="text"
            placeholder="Username"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
          />
          <input
            className="login-input"
            type="password"
            placeholder="Password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
          />
          
          <button className="login-button" type="submit">
          {isRegister ? 'Register' : 'Login'}
        </button>

        <button
          className="register-button"
          type="button"
          onClick={() => setIsRegister(!isRegister)}
        >
          {isRegister ? 'Back to Login' : 'Create Account'}
        </button>

        {error && <p className="login-error">{error}</p>}
        </form>
      </div>
    </div>
  );
}

export default Login;
