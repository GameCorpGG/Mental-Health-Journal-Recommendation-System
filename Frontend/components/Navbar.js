import React from 'react';
import { Link, useNavigate } from 'react-router-dom';

function NavBar() {
  const token = localStorage.getItem('token');
  const userRole = localStorage.getItem('role'); // Assume role is saved in localStorage at login
  const navigate = useNavigate();

  const handleLogout = () => {
    localStorage.removeItem('token');
    localStorage.removeItem('role');
    navigate('/login');
  };

  return (
    <nav style={{ padding: '10px', borderBottom: '1px solid #ccc', marginBottom: '20px' }}>
      <Link to="/dashboard" style={{ marginRight: '10px' }}>Dashboard</Link>

      {token && userRole === 'ADMIN' && (
        <Link to="/admin" style={{ marginRight: '10px' }}>Admin Panel</Link>
      )}

      {!token ? (
        <>
          <Link to="/login" style={{ marginRight: '10px' }}>Login</Link>
          <Link to="/register">Register</Link>
        </>
      ) : (
        <button onClick={handleLogout} style={{ marginLeft: '10px' }}>Logout</button>
      )}
    </nav>
  );
}

export default NavBar;
