import React, { useEffect, useState, useRef } from 'react';
import styled from 'styled-components';

const AppShell = styled.div`
  width: 100vw;
  height: 100vh;
  background: #000;
  display: grid;
  grid-template-columns: ${({ open }) => (open ? '28% 72%' : '1fr')};
  transition: grid-template-columns 540ms cubic-bezier(0.4, 0, 0.2, 1);
  overflow: hidden;
`;

const LeftPane = styled.div`
  position: relative;
  height: 100%;
  display: grid;
  grid-template-rows: auto 1fr;
  overflow: hidden;
`;

const TitleBar = styled.div`
  position: sticky;
  top: 0;
  z-index: 2;
  padding: 18px 24px 14px;
  color: #fff;
  font-weight: 800;
  font-size: 1.9rem;
  background: linear-gradient(180deg, rgba(0,0,0,1) 0%, rgba(0,0,0,0.85) 70%, rgba(0,0,0,0) 100%);
`;

const GridScroll = styled.div`
  overflow: auto;
  padding: 0 24px 28px;
  scroll-behavior: smooth;
`;

const GridContainer = styled.div`
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(320px, 1fr));
  gap: 22px;
  padding-top: 6px;
  ${({ open }) => !open && 'max-width: 1400px; margin: 0 auto;'}
`;

const BubbleCard = styled.button`
  background: rgba(255, 255, 255, 0.15);
  border-radius: 22px;
  padding: 16px;
  box-shadow: 0 4px 24px 0 rgba(40, 60, 120, 0.07);
  backdrop-filter: blur(18px);
  -webkit-backdrop-filter: blur(18px);
  border: 1.2px solid rgba(255,255,255,0.32);
  color: #fff;
  font-size: 1rem;
  position: relative;
  transition: 
    box-shadow 180ms ease,
    transform 160ms ease,
    background 200ms ease,
    color 200ms ease;
  cursor: pointer;
  text-align: left;
  display: flex;
  flex-direction: column;
  outline: none;

  &::before {
    content: '';
    position: absolute;
    top: 0;
    left: 7%;
    width: 86%;
    height: 28%;
    border-radius: 22px 22px 90px 90px;
    pointer-events: none;
  }

  &:hover {
    box-shadow: 0 6px 28px 0 rgba(40, 60, 120, 0.17);
    transform: translateY(-1px) scale(1.02);
    background: rgba(192, 225, 244, 0); /* brighter on hover */
    color: #ffffffff; /* change text color on hover */
  }
`;


const Thumb = styled.img`
  width: 100%;
  height: 180px;
  object-fit: cover;
  border-radius: 16px;
  margin-bottom: 12px;
  background: #23272f;
`;

const Title = styled.div`
  font-size: 1.05rem;
  font-weight: 600;
  line-height: 1.35;
`;

/* Right side details */
const DetailsPaneShell = styled.div`
  height: 100%;
  position: relative;
  overflow: hidden;
`;

const DetailsScroll = styled.div`
  position: absolute;
  inset: 0;
  overflow: auto;
  padding: 72px 28px 28px; /* ensures content starts below Close button */
  scroll-behavior: smooth;
  transform: translateX(${({ open }) => (open ? '0' : '100%')});
  transition: transform 540ms cubic-bezier(0.4, 0, 0.2, 1);
  background: #111;
  color: #f5f5f7;
`;

const CloseBtn = styled.button`
  position: absolute;
  right: 20px;
  top: 18px;
  background: rgba(255,255,255,0.12);
  border: 1px solid rgba(255,255,255,0.22);
  color: #fff;
  border-radius: 10px;
  padding: 8px 12px;
  cursor: pointer;
  font-weight: 700;
  backdrop-filter: blur(10px);
  transition: background 160ms ease, transform 160ms ease, opacity 200ms ease;
  z-index: 3;

  &:hover { background: rgba(255,255,255,0.22); transform: translateY(-1px); }
`;

const Hero = styled.img`
  width: 100%;
  max-height: 340px;
  object-fit: cover;
  border-radius: 18px;
  margin-bottom: 18px;
  background: #222;
`;

const H1 = styled.h1`
  font-size: 1.6rem;
  margin: 0 0 10px 0;
`;

const Meta = styled.div`
  color: #b8c0cc;
  font-size: 0.95rem;
  margin-bottom: 18px;
`;

const Body = styled.div`
  font-size: 1.08rem;
  line-height: 1.6;
  white-space: pre-wrap;
`;

export default function App() {
  const [articles, setArticles] = useState([]);
  const [loading, setLoading]   = useState(true);
  const [error, setError]       = useState(null);
  const [selected, setSelected] = useState(null);

  const listScrollRef = useRef(null);
  const detailsScrollRef = useRef(null);

  useEffect(() => {
    fetch('http://localhost:8081/api/articles')
      .then(r => { if (!r.ok) throw new Error('Network error'); return r.json(); })
      .then(data => { setArticles(data); setLoading(false); })
      .catch(() => { setError('Failed to fetch articles.'); setLoading(false); });
  }, []);

  // When opening details, scroll its container to top smoothly.
  useEffect(() => {
    if (selected && detailsScrollRef.current) {
      detailsScrollRef.current.scrollTo({ top: 0, behavior: 'smooth' });
    }
  }, [selected]);

  return (
    <AppShell open={!!selected}>
      {/* Left: scrollable grid with bubble effect */}
      <LeftPane>
        <TitleBar>Tech Updates</TitleBar>
        <GridScroll ref={listScrollRef}>
          {loading && <div style={{ color:'#fff', fontSize:20 }}>Loading…</div>}
          {error && <div style={{ color:'#fff', fontSize:20 }}>{error}</div>}
          {!loading && !error && (
            <GridContainer open={!!selected}>
              {articles.map(a => (
                <BubbleCard key={a.id} onClick={() => setSelected(a)}>
                  {a.imgURL && <Thumb src={a.imgURL} alt={a.title} loading="lazy" />}
                  <Title>{a.title}</Title>
                </BubbleCard>
              ))}
            </GridContainer>
          )}
        </GridScroll>
      </LeftPane>

      {/* Right: scrollable details, starts below Close */}
      <DetailsPaneShell>
        <CloseBtn
          style={{ opacity: selected ? 1 : 0, pointerEvents: selected ? 'auto' : 'none' }}
          onClick={() => setSelected(null)}
        >
          Close
        </CloseBtn>
        <DetailsScroll ref={detailsScrollRef} open={!!selected} aria-hidden={!selected}>
          {selected && (
            <>
              {selected.imgURL && <Hero src={selected.imgURL} alt={selected.title} />}
              <H1>{selected.title}</H1>
              <Meta>
                {selected.source || 'Unknown source'}
                {selected.publishedAt ? ` · ${new Date(selected.publishedAt).toLocaleString()}` : ''}
              </Meta>
              <Body>
                {selected.content && !/^\s*$/.test(selected.content)
                  ? selected.content
                  : 'No content available for this article.'}
              </Body>
            </>
          )}
        </DetailsScroll>
      </DetailsPaneShell>
    </AppShell>
  );
}
