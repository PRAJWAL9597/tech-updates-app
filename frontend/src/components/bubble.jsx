import styled from 'styled-components';

const CleanIOSBubble = styled.div`
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

  /* Remove the highlight: No ::before pseudo-element */
  /* &:before { ... } */
  
  &:hover {
    box-shadow: 0 6px 28px 0 rgba(40, 60, 120, 0.17);
    transform: scale(1.02);
  }
`;

export default function IOSBubble({ children }) {
  return <CleanIOSBubble>{children}</CleanIOSBubble>;
}
