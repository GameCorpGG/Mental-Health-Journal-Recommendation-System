import React, { useEffect, useState } from 'react';
import API from '../api/axios';
import styles from './AdminContent.module.css';
import AdminBackButton from '../components/AdminBackButton';

function ContentManagementPage() {
  const [contents, setContents] = useState([]);
  const [newUrl, setNewUrl] = useState('');
  const [newCategory, setNewCategory] = useState('');

  const fetchContents = async () => {
    const res = await API.get('/admin/content');
    setContents(res.data);
  };

  const deleteContent = async (url) => {
    await API.delete(`/admin/content/delete`, { params: { url } });
    fetchContents();
  };

  const approveContent = async (url) => {
    await API.post(`/admin/content/approve`, null, { params: { url } });
    fetchContents();
  };

  const rejectContent = async (url) => {
    await API.post(`/admin/content/reject`, null, { params: { url } });
    fetchContents();
  };

  const addContent = async (e) => {
    e.preventDefault();
    if (!newUrl || !newCategory) return alert("Please enter both URL and category.");
    try {
      await API.post('/admin/content/add', {
        url: newUrl,
        category: newCategory
      });
      setNewUrl('');
      setNewCategory('');
      fetchContents();
    } catch (err) {
      alert('Failed to add content: ' + (err.response?.data?.message || 'Unknown error'));
    }
  };

  useEffect(() => {
    fetchContents();
  }, []);

  return (
    <div className={styles.page}>
      <div className={styles.AdminBackButton}>
        <AdminBackButton to="/admin" label="← Back to Admin Dashboard" />
      </div>

      <div className={styles.container}>
        <h2 className={styles.heading}>Content Management</h2>

        {/* ADD CONTENT FORM */}
        <form onSubmit={addContent} className={styles.addForm}>
          <h4>Add YouTube Video</h4>
          <input
            type="text"
            placeholder="YouTube Video URL"
            value={newUrl}
            onChange={(e) => setNewUrl(e.target.value)}
            className={styles.input}
            required
          />
          <select
            value={newCategory}
            onChange={(e) => setNewCategory(e.target.value)}
            className={styles.input}
            required
          >
            <option value="">Select Mood Tag</option>
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
          <button type="submit" className={styles.submitButton}>Add Video</button>
        </form>

        {/* EXISTING CONTENT */}
        {contents.length === 0 ? (
          <p>No content available.</p>
        ) : (
          contents.map((content) => (
            <div key={content.contentId} className={styles.contentCard}>
              <div className={styles.title}>{content.title}</div>
              <div className={styles.detail}>Category: {content.category}</div>
              <div className={styles.detail}>
                URL:{' '}
                <a href={content.url} target="_blank" rel="noopener noreferrer">
                  {content.url}
                </a>
              </div>
              <div className={styles.status}>Status: {content.status || 'Pending'}</div>

              <div className={styles.buttonGroup}>
                <button className={`${styles.button} ${styles.approve}`} onClick={() => approveContent(content.url)}>
                  Approve
                </button>
                <button className={`${styles.button} ${styles.reject}`} onClick={() => rejectContent(content.url)}>
                  Reject
                </button>
                <button className={`${styles.button} ${styles.delete}`} onClick={() => deleteContent(content.url)}>
                  Delete
                </button>
              </div>
            </div>
          ))
        )}
      </div>
    </div>
  );
}

export default ContentManagementPage;
