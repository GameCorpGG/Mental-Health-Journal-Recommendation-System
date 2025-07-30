import React, { useState } from 'react';
import API from '../api/axios';
import { useNavigate } from 'react-router-dom';
import styles from './RegisterPage.module.css'; // Import the CSS module

function RegisterPage() {
  const [email, setEmail] = useState('');
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [role, setRole] = useState('');
  const navigate = useNavigate();

  const handleRegister = async (e) => {
    e.preventDefault();
    try {
      await API.post('/auth/register', { email, username, password, role });
      alert('Registration successful! You can now log in.');
      navigate('/');
    } catch (err) {
      alert('Registration failed: ' + (err.response?.data?.message || 'Unknown error'));
    }
  };

  return (
    <div className={styles.page}>
      <form onSubmit={handleRegister} className={styles.container}>
        <h2 className={styles.heading}>Register</h2>

        <input
          type="text"
          placeholder="Username"
          value={username}
          onChange={e => setUsername(e.target.value)}
          required
          className={styles.input}
        />

        <input
          type="email"
          placeholder="Email"
          value={email}
          onChange={e => setEmail(e.target.value)}
          required
          className={styles.input}
        />

        <input
          type="password"
          placeholder="Password"
          value={password}
          onChange={e => setPassword(e.target.value)}
          required
          className={styles.input}
        />

        <select
          value={role}
          onChange={e => setRole(e.target.value)}
          required
          className={styles.input}
        >
          <option value="" disabled>Select Role</option>
          <option value="USER">USER</option>
          <option value="ADMIN">ADMIN</option>
        </select>

        <button type="submit" className={styles.button}>Register</button>
        <p className={styles.loginText}>
          Already have an account? <a href="/">Login here</a>
        </p>
      </form>
    </div>
  );
}

export default RegisterPage;
