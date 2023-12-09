import React, { useState } from 'react';
import styled, { css } from 'styled-components';
import formatDate from '../../utils/formatDate';
import { getDevNote } from '../../apis/API';
import DevNotePopUp from './DevNotePopUp';

export default function DevNoteCarrousel({devNotes, projectId}) {
  const [currentIndex, setCurrentIndex] = useState(0);
  const [pdfUrl, setPdfUrl] = useState('');
  const [showPopUp, setShowPopUp] = useState(false);
  const [currentDevNoteId, setCurrentDevNoteId] = useState(null);

  const goToNext = () => {
    setCurrentIndex((currentIndex + 1) % devNotes.length);
  };

  const goToPrev = () => {
    setCurrentIndex((currentIndex - 1 + devNotes.length) % devNotes.length);
  };

  const handleItemClick = async (devNoteId) => {

    console.log('Clicked devNoteId:', devNoteId); // 로그 추가

    try {
      const data = await getDevNote(projectId, devNoteId);
      setPdfUrl(data.result.content);
      setShowPopUp(true);
      setCurrentDevNoteId(devNoteId)
    } catch (error) {
      console.log('PDF요청 실패', error)
      
    }
  }

  return (
    <CarouselContainer>
      <Button onClick={goToPrev}>{"<"}</Button>
      {devNotes.map((note, index) => (
        <CarouselItem 
          key={note.id} 
          active={index === currentIndex}
          onClick={() => handleItemClick(note.id)}>
          <h3>{note.title}</h3>
          <DevNoteImage src={note.thumbnail} alt={note.title} />
          <p>{formatDate(note.createdAt)}</p>
        </CarouselItem>
      ))}
      <Button onClick={goToNext}>{">"}</Button>
      {showPopUp && (
        <DevNotePopUp
          pdfUrl={pdfUrl}
          onClose={() => setShowPopUp(false)}
          devNoteId={currentDevNoteId}
          />
      )}
    </CarouselContainer>
  );
}

const CarouselContainer = styled.div`
  margin: 15% auto;
  display: flex;
  align-items: center;
  justify-content: center;
  overflow: visible;

`;

const Button = styled.button`
  background-color: transparent;
  border: none;
  width: 40px;
  height: 40px;
  cursor: pointer;
  font-weight: 600;
  font-size: 40px;

`;

const CarouselItem = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: space-around;
  align-items: center;
  width: 400px;
  height: 600px;
  margin: 40px;
  padding: 20px;
  border: 1px solid #ddd;
  border-radius: 4px;
  transition: transform 0.5s ease, opacity 0.5s ease;
  z-index: 0;

  ${({ active }) => active
    ? css`
        transform: scale(1.1);
        opacity: 1;
      `
    : css`
        transform: scale(0.9);
        opacity: 0.5;
      `
  }
`;

const DevNoteImage = styled.img`
  width: 300px;
  height: 400px;
`