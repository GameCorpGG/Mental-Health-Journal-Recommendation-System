import React, { useEffect, useState } from 'react';
import API from '../api/axios';
import styles from './MoodHistory.module.css';
import MoodBackButton from '../components/MoodBackButton';

function MoodHistoryPage() {
  const [entries, setEntries] = useState([]);

  const fetchEntries = async () => {
    try {
      const res = await API.get(`/mood/get`);
      setEntries(res.data);
    } catch (err) {
      alert('Failed to fetch previous mood entries: ' + (err.response?.data?.message || 'Unknown error'));
    }
  };

  useEffect(() => {
    fetchEntries();
  }, []);

  return (
    <div className={styles.page}>
      <div className={styles.MoodBackButton}>
        <MoodBackButton to="/mood" label="← Back to Mood Dashboard" />
      <div className={styles.container}>
        <h2 className={styles.heading}>Your Previous Mood Entries</h2>
        {entries.length === 0 ? (
          <p>No mood entries yet.</p>
        ) : (
          entries.map((entry, index) => (
            <div key={index} className={styles.entryCard}>
              <div className={styles.moodType}>{entry.moodType}</div>
              <div className={styles.note}>{entry.note}</div>
              <div className={styles.date}>
                {new Date(entry.date).toLocaleString()}
              </div>
            </div>
          ))
        )}
      </div>
    </div>
    </div>
  );
}

export default MoodHistoryPage;
