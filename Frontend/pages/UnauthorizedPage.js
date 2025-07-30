// pages/UnauthorizedPage.js
import React from 'react';
import { useNavigate } from 'react-router-dom';

function UnauthorizedPage() {
  const navigate = useNavigate();

  return (
    <div style={{ textAlign: 'center', paddingTop: '100px' }}>
      <h1 style={{ fontSize: '3rem', color: '#e74c3c' }}>403 - Unauthorized</h1>
      <p style={{ fontSize: '1.2rem', marginBottom: '30px' }}>
        You do not have permission to access this page.
      </p>
      <button
        onClick={() => navigate('/dashboard')}
        style={{
          padding: '10px 20px',
          backgroundColor: '#3498db',
          color: 'white',
          border: 'none',
          borderRadius: '6px',
          fontSize: '16px',
          cursor: 'pointer',
        }}
      >
        ← Back to Dashboard
      </button>
    </div>
  );
}

export default UnauthorizedPage;
