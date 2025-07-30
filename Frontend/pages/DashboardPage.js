import React from 'react';
import { useNavigate } from 'react-router-dom';
import styles from './DashboardPage.module.css';

function DashboardPage() {
  const navigate = useNavigate();

  const handleLogout = () => {
    localStorage.removeItem('token');
    navigate('/');
  };

  return (
    <div className={styles.page}>
      <div className={styles.container}>
        <h2 className={styles.heading}>Welcome to Your Mental Health Dashboard</h2>
        <p className={styles.subtext}>Select a feature below:</p>

        <div className={styles.buttonGroup}>
          <button className={styles.button} onClick={() => navigate('/journal')}>📝 Journal Entry</button>
          <button className={styles.button} onClick={() => navigate('/mood')}>😊 Mood Log</button>
          <button className={styles.button} onClick={() => navigate('/recommendations')}>🎯 Get Recommendations</button>
          <button className={styles.button} onClick={() => navigate('/admin')}>🛠 Admin Management</button>
          <button className={`${styles.button} ${styles.logout}`} onClick={handleLogout}>🚪 Logout</button>
        </div>
      </div>
    </div>
  );
}

export default DashboardPage;
