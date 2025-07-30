import React, { useEffect, useState } from 'react';
import API from '../api/axios';
import styles from './AdminUser.module.css';
import AdminBackButton from '../components/AdminBackButton';

function UserManagementPage() {
  const [users, setUsers] = useState([]);
  const [searchEmail, setSearchEmail] = useState('');
  const [searchResult, setSearchResult] = useState([]);

  const fetchUsers = async () => {
    const res = await API.get('/admin/user/all_users');
    setUsers(res.data);
  };

  const deleteUser = async (email) => {
    await API.delete(`/admin/user/delete`, { params: { email } });
    fetchUsers();
  };

  const searchUserByEmail = async () => {
    const res = await API.get(`/admin/user/find`, { params: { email: searchEmail } });
    setSearchResult(res.data);
  };

  useEffect(() => {
    fetchUsers();
  }, []);

  return (
    <div className={styles.page}>
      <div className={styles.AdminBackButton}>
        <AdminBackButton to="/admin" label="← Back to Admin Dashboard" />
      <div className={styles.container}>
        <h2 className={styles.heading}>User Management</h2>

        <div className={styles.section}>
          <h4>🔍 Search User by Email</h4>
          <div className={styles.inputGroup}>
            <input
              type="text"
              placeholder="Enter Email"
              value={searchEmail}
              onChange={(e) => setSearchEmail(e.target.value)}
              className={styles.input}
            />
            <button onClick={searchUserByEmail} className={styles.button}>
              Search
            </button>
          </div>

          {searchResult.length > 0 && (
            <div>
              <h5>Search Result:</h5>
              {searchResult.map((user, idx) => (
                <div key={idx} className={styles.resultCard}>
                  <span className={styles.userDetails}>
                    {user.name} - {user.email} ({user.role})
                  </span>
                </div>
              ))}
            </div>
          )}
        </div>

        <div className={styles.section}>
          <h4>👥 All Users</h4>
          {users.map(user => (
            <div key={user.userId} className={styles.userCard}>
              <span className={styles.userDetails}>
                {user.username} - {user.email} ({user.role})
              </span>
              <button
                className={styles.deleteButton}
                onClick={() => deleteUser(user.email)}
              >
                Delete
              </button>
              
            </div>
          ))}
        </div>

      </div>
    </div>
    </div>
  );
}

export default UserManagementPage;
