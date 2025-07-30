import React, { useEffect, useState } from 'react';
import API from '../api/axios';
import styles from './JournalHistory.module.css';
import JournalBackButton from '../components/JournalBackButton';

function JournalHistoryPage() {
  const [entries, setEntries] = useState([]);

  const fetchEntries = async () => {
    try {
      const res = await API.get(`/journal/history`);
      setEntries(res.data);
    } catch (err) {
      alert('Failed to fetch journal entries: ' + (err.response?.data?.message || 'Unknown error'));
    }
  };

  useEffect(() => {
    fetchEntries();
  }, []);

  return (
    <div className={styles.page}>
      <div className={styles.JournalBackButton}>
        <JournalBackButton to="/journal" label="← Back to Journal Dashboard" />
      <div className={styles.container}>
        <h2 className={styles.heading}>Your Previous Journal Entries</h2>
        {entries.length === 0 ? (
          <p>No journal entries yet.</p>
        ) : (
          entries.map((entry, index) => (
            <div key={index} className={styles.entryCard}>
              <div className={styles.entryTitle}>{entry.title}</div>
              <div className={styles.entryContent}>{entry.content}</div>
              <div className={styles.entryDate}>
                {new Date(entry.entryDate).toLocaleString()}
              </div>
            </div>
          ))
        )}
      </div>
    </div>
    </div>
  );
}

export default JournalHistoryPage;
