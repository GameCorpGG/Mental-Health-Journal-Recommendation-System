import React, { useState } from 'react';
import API from '../api/axios';
import styles from './JournalCreate.module.css';
import JournalBackButton from '../components/JournalBackButton';

function JournalCreatePage() {
  const [title, setTitle] = useState('');
  const [content, setContent] = useState('');
  const [success, setSuccess] = useState(false);

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await API.post(`/journal/create`, { title, content });
      setSuccess(true);
      setTitle('');
      setContent('');
    } catch (err) {
      alert('Failed to submit journal: ' + (err.response?.data?.message || 'Unknown error'));
    }
  };

  return (
    <div className={styles.page}>
      <div className={styles.JournalBackButton}>
        <JournalBackButton to="/journal" label="← Back to Journal Dashboard" />
      <div className={styles.container}>
        <h2 className={styles.heading}>Create Journal Entry</h2>
        <form onSubmit={handleSubmit}>
          <input
            type="text"
            placeholder="Title"
            value={title}
            onChange={(e) => setTitle(e.target.value)}
            required
            className={styles.input}
          />
          <textarea
            rows="6"
            placeholder="Write your thoughts..."
            value={content}
            onChange={(e) => setContent(e.target.value)}
            required
            className={styles.textarea}
          />
          <button type="submit" className={styles.button}>
            Submit Entry
          </button>
        </form>
        {success && <p className={styles.successMessage}>Journal submitted successfully!</p>}
      </div>
    </div>
    </div>
  );
}

export default JournalCreatePage;
