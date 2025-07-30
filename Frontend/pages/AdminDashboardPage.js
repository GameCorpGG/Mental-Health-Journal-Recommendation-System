import React, {useEffect, useState} from 'react';
import { useNavigate } from 'react-router-dom';
import { jwtDecode } from 'jwt-decode'; 
import styles from './AdminDashboard.module.css';
import BackButton from '../components/BackButton';  

function AdminDashboardPage() {
  const navigate = useNavigate();
  const [loading, setLoading] = useState(true);

    useEffect(() => {
    const token = localStorage.getItem('token');
    if (!token) {
      navigate('/unauthorized');
      return;
    }

    try {
      const decoded = jwtDecode(token);
      if (decoded.role !== 'ADMIN') {
        navigate('/unauthorized');
      } 
    } catch (err) {
      navigate('/unauthorized');
    } finally{
      setLoading(false);
    }
  }, [navigate]);

  if (loading) return null;

  return (
    <div className={styles.page}>
          <div className={styles.backButton}>
      <BackButton to="/dashboard" label="← Back to Dashboard" />
      <div className={styles.container}>
        <h2 className={styles.heading}>Welcome, Admin</h2>
        <p className={styles.subtext}>Select what you want to manage:</p>
        <div className={styles.buttonGroup}>
          <button className={styles.button} onClick={() => navigate('/admin/users')}>
            👤 Manage Users
          </button>
          <button className={styles.button} onClick={() => navigate('/admin/content')}>
            📂 Manage Content
          </button>
        </div>
      </div>
    </div>
    </div>
  );
}

export default AdminDashboardPage;
