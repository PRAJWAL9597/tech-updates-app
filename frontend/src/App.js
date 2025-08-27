import React, { useEffect, useState } from 'react';
import styled from 'styled-components';

// iOS-style glassmorphic bubble component
const IOSGlassBubble = styled.div`
  background: rgba(255, 255, 255, 0.15);
  border-radius: 22px;
  padding: 18px 28px;
  box-shadow: 0 4px 24px 0 rgba(40, 60, 120, 0.07);
  backdrop-filter: blur(18px);
  -webkit-backdrop-filter: blur(18px);
  border: 1.2px solid rgba(255,255,255,0.32);
  color: #fff;
  font-size: 1.1rem;
  margin: 18px 0;
  position: relative;
  transition: box-shadow 0.2s, transform 0.2s;

  &::before {
    content: '';
    position: absolute;
    top: 0;
    left: 7%;
    width: 86%;
    height: 30%;
    border-radius: 22px 22px 90px 90px;
    // background: linear-gradient(180deg, rgba(255, 255, 255, 1) 60%, transparent 100%);
    pointer-events: none;
  }

  &:hover {
    box-shadow: 0 6px 28px 0 rgba(40, 60, 120, 0.17);
    transform: scale(1.02);
  }
`;

// Container for app content with a subtle gradient background
const Container = styled.div`
  max-width: 600px;
  margin: 3rem auto;
  padding: 1rem 1.5rem;
  background: linear-gradient(135deg, #4a90e2 0%, #50e3c2 100%);
  min-height: 100vh;
  font-family: 'Inter', sans-serif;
`;

function App() {
  const [articles, setArticles] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    fetch('http://localhost:8081/api/articles')
      .then(response => {
        if (!response.ok) throw new Error('Network response was not ok');
        return response.json();
      })
      .then(data => {
        setArticles(data);
        setLoading(false);
      })
      .catch(err => {
        setError('Failed to fetch articles. Please try again later.');
        setLoading(false);
      });
  }, []);

  return (
    <Container>
      <h1 style={{ color: '#fff', textAlign: 'center', marginBottom: '2rem' }}>
        Tech Updates
      </h1>
      {loading && <p style={{ color: '#fff' }}>Loading articles...</p>}
      {error && <p style={{ color: '#fff' }}>{error}</p>}
      {!loading && !error && articles.map((article, index) => (
        <IOSGlassBubble key={index}>{article.title}</IOSGlassBubble>
      ))}
    </Container>
  );
}

export default App;
