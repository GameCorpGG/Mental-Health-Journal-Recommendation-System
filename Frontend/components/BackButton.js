import React from 'react';
import { useNavigate } from 'react-router-dom';

function BackButton({ to = '/dashboard', label = '← Back to Dashboard' }) {
  const navigate = useNavigate();

  return (
    <button
      onClick={() => navigate(to)}
      style={{
        marginBottom: '20px',
        padding: '8px 16px',
        fontSize: '14px',
        backgroundColor: '#CC5500',
        color: 'white',
        border: 'none',
        borderRadius: '6px',
        cursor: 'pointer',
        transition: 'background-color 0.3s',
      }}
      onMouseOver={(e) => (e.target.style.backgroundColor = '#FF0000')}
      onMouseOut={(e) => (e.target.style.backgroundColor = '#CC5500')}
    >
      {label}
    </button>
  );
}

export default BackButton;
