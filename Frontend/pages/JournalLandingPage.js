import React from 'react';
import { useNavigate } from 'react-router-dom';
import styles from './JournalLanding.module.css';
import BackButton from '../components/BackButton';

function JournalLandingPage() {
  const navigate = useNavigate();

  return (
    <div className={styles.page}>
      <div className={styles.backButton}>
         <BackButton to="/dashboard" label="← Back to Dashboard" />
      <div className={styles.container}>
        <h2 className={styles.heading}>Journal Dashboard</h2>
        <p className={styles.subtext}>What would you like to do?</p>

        <button
          onClick={() => navigate('/journal/create')}
          className={styles.button}
        >
          ➕ Create New Entry
        </button>

        <button
          onClick={() => navigate('/journal/history')}
          className={styles.button}
        >
          📖 View Past Entries
        </button>
      </div>
    </div>
    </div>
  );
}

export default JournalLandingPage;
