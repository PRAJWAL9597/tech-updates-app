import React, { useEffect, useState } from 'react';
import styled from 'styled-components';

const GridContainer = styled.div`
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(320px, 1fr));
  gap: 28px;
  padding: 48px 36px;
  background: #000000ff;
  min-height: 100vh;
  width: 100vw;
  box-sizing: border-box;
`;

const ArticleCard = styled.div`
  background: rgba(255, 255, 255, 0.15);
  border-radius: 24px;
  box-shadow: 0 4px 24px 0 rgba(40, 60, 120, 0.07);
  backdrop-filter: blur(18px);
  -webkit-backdrop-filter: blur(18px);
  border: 1.2px solid rgba(255,255,255,0.32);
  color: #fff;
  font-size: 1.1rem;
  padding: 18px 28px;
  cursor: pointer;
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
    pointer-events: none;
  }

  &:hover {
    box-shadow: 0 6px 28px 0 rgba(40, 60, 120, 0.17);
    transform: scale(1.02);
  }

  display: flex;
  flex-direction: column;
`;

const ArticleImage = styled.img`
  width: 100%;
  border-radius: 18px 18px 0 0;
  object-fit: cover;
  max-height: 220px;
  margin-bottom: 12px;
`;

const ArticleTitle = styled.div`
  font-weight: 600;
  margin-top: 8px;
`;

export default function App() {
  const [articles, setArticles] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    fetch('http://localhost:8081/api/articles')
      .then((response) => {
        if (!response.ok) throw new Error('Network error');
        return response.json();
      })
      .then(data => {
        setArticles(data);
        setLoading(false);
      })
      .catch(() => {
        setError('Failed to fetch articles.');
        setLoading(false);
      });
  }, []);

  return (
    <GridContainer>
      {loading && <div style={{ color: '#fff', fontSize: 22 }}>Loading articles...</div>}
      {error && <div style={{ color: '#fff', fontSize: 22 }}>{error}</div>}
      {!loading && !error && articles.map((article, index) => (
        <ArticleCard key={index}>
          {article.imgURL && <ArticleImage src={article.imgURL} alt={article.title} />}
          <ArticleTitle>{article.title}</ArticleTitle>
        </ArticleCard>
      ))}
    </GridContainer>
  );
}
