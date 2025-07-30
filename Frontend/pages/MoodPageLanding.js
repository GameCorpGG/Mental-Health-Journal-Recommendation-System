import React from 'react';
import { useNavigate } from 'react-router-dom';
import styles from './MoodLanding.module.css';
import BackButton from '../components/BackButton';

function MoodPageLanding() {
  const navigate = useNavigate();

  return (
    <div className={styles.page}>
      <div className={styles.BackButton}>
        <BackButton to="/dashboard" label="← Back to Dashboard" />
      <div className={styles.container}>
        <h2 className={styles.heading}>Mood Dashboard</h2>
        <p className={styles.subtext}>What would you like to do?</p>

        <button
          onClick={() => navigate('/mood/log')}
          className={styles.button}
        >
          ➕ Create New Mood Entry
        </button>

        <button
          onClick={() => navigate('/mood/get')}
          className={styles.button}
        >
          📖 View Past Mood Entries
        </button>
      </div>
    </div>
    </div>
  );
}

export default MoodPageLanding;
