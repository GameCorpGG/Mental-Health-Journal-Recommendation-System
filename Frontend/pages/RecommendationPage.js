import React, { useEffect, useState } from 'react';
import API from '../api/axios';
import styles from './Recommendation.module.css';
import BackButton from '../components/BackButton';

function RecommendationPage() {
  const [recommendations, setRecommendations] = useState([]);
  const [error, setError] = useState('');

  const fetchRecommendations = async () => {
    try {
      const token = localStorage.getItem('token');
      if (!token) {
        setError("User not logged in.");
        return;
      }

      const res = await API.get('/recommendations/recommend');
      setRecommendations(res.data);
    } catch (err) {
      setError(err.response?.data?.message || 'Failed to fetch recommendations.');
    }
  };

  useEffect(() => {
    fetchRecommendations();
  }, []);

  const extractYouTubeId = (url) => {
    const regExp = /(?:v=|\/)([0-9A-Za-z_-]{11})(?:\?|&|$)/;
    const match = url.match(regExp);
    return match ? match[1] : null;
  };

  return (
    <div className={styles.page}>
      <div className={styles.backButton}>
        <BackButton to="/dashboard" label="← Back to Dashboard" />
      <div className={styles.container}>
        <h2 className={styles.heading}>Personalized Recommendations</h2>
        {error && <p style={{ color: 'red' }}>{error}</p>}

        <div className={styles.grid}>
          {recommendations.map((rec, index) => {
            const content = rec.content;
            const videoId = extractYouTubeId(content.url);

            return (
              <div key={index} className={styles.card}>
                {videoId ? (
                  <iframe
                    className={styles.iframe}
                    src={`https://www.youtube.com/embed/${videoId}`}
                    title={`video-${index}`}
                    allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"
                    allowFullScreen
                  />
                ) : (
                  <div className={styles.iframe}>
                    <a href={content.url} target="_blank" rel="noopener noreferrer">Watch Video</a>
                  </div>
                )}
                <div className={styles.content}>
                  <div className={styles.title}>{content.title}</div>
                  <div className={styles.moodTag}>{content.category}</div>
                </div>
              </div>
            );
          })}
        </div>
      </div>
    </div>
    </div>
  );
}

export default RecommendationPage;
