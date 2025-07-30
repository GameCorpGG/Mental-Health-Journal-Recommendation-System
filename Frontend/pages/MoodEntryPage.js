import React, { useState } from 'react';
import API from '../api/axios';
import styles from './MoodEntry.module.css';
import MoodBackButton from '../components/MoodBackButton';

function MoodEntryPage() {
  const [moodType, setMoodType] = useState('');
  const [note, setNote] = useState('');
  const [success, setSuccess] = useState(false);

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await API.post('/mood/log', { moodType, note });
      setSuccess(true);
      setMoodType('');
      setNote('');
    } catch (err) {
      alert('Failed to submit mood: ' + (err.response?.data?.message || 'Unknown error'));
    }
  };

  return (
    <div className={styles.page}>
      <div className={styles.MoodBackButton}>
        <MoodBackButton to="/mood" label="← Back to Mood Dashboard" />
      <div className={styles.container}>
        <h2 className={styles.heading}>Log Your Mood</h2>
        <form onSubmit={handleSubmit}>
          <select
            value={moodType}
            onChange={(e) => setMoodType(e.target.value)}
            required
            className={styles.select}
          >
            <option value="">Select Mood</option>
            <option value="happy">Happy</option>
            <option value="sad">Sad</option>
            <option value="anxious">Anxious</option>
            <option value="depressed">Depressed</option>
            <option value="romantic">Romantic</option>
            <option value="motivated">Motivated</option>
            <option value="energetic">Energetic</option>
            <option value="excited">Excited</option>
            <option value="nostalgic">Nostalgic</option>
            <option value="relaxed">Relaxed</option>
            <option value="angry">Angry</option>
            <option value="calm">Calm</option>
            <option value="playful">Playful</option>
            <option value="creative">Creative</option>
          </select>

          <textarea
            className={styles.textarea}
            placeholder="Write a note about your mood (optional)"
            value={note}
            onChange={(e) => setNote(e.target.value)}
            rows="4"
          />

          <button type="submit" className={styles.button}>Submit Mood</button>
        </form>

        {success && <p className={styles.successMessage}>Mood submitted successfully!</p>}
      </div>
    </div>
    </div>
  );
}

export default MoodEntryPage;
