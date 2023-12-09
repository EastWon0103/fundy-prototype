import React, { useEffect, useRef } from 'react'
import styled from 'styled-components';
import PdfContent from '../projectDetail/PdfContent';
import DevNoteComment from './DevNoteComment';

export default function DevNotePopUp({pdfUrl, onClose, devNoteId}) {
    const popupRef = useRef();

    useEffect(() => {
        function handleClickOutside(event) {
          if (popupRef.current && !popupRef.current.contains(event.target)) {
            onClose();
          }
        }
    
        document.addEventListener('mousedown', handleClickOutside);
        return () => {
          document.removeEventListener('mousedown', handleClickOutside);
        };
      }, [onClose]);

  return (
    <PopupContainer ref={popupRef}>
      <CloseButton onClick={onClose}>X</CloseButton>
      <PdfContent pdf={pdfUrl} scale={2} width={400} />
      <DevNoteComment devNoteId={devNoteId} />
    </PopupContainer>
  )
}

const PopupContainer = styled.div`
  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  width: 60%;
  max-width: 1680px;
  height: 80%;
  z-index: 1000;
  background-color: white;
  box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.5);
  overflow: auto;
`;

const CloseButton = styled.button`
  position: fixed; 
  top: 10px; 
  right: 10px; 
  background-color: #ff0000;
  color: white;
  border: none;
  cursor: pointer;
  padding: 10px; 
  z-index: 1001; 
`;