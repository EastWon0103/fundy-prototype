import React from 'react'
import styled from 'styled-components';

export default function Modal({ isOpen, action, description }) {
    
    if (!isOpen) return null;
    const renderDescription = () => {
        return description.split('\n').map((line, index) => (
            <span key={index}>{line}</span>
        ))
    }

  return (
    <Container>
        <ModalBody>
            {renderDescription()}
            <ModalButton onClick={action}>확인</ModalButton>
        </ModalBody>
    </Container>
    
  )
}

const Container = styled.div`
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background-color: rgba(0, 0, 0, 0.4);
    display: flex;
    justify-content: center;
    align-items: center;
`

const ModalBody = styled.div`
    position: absolute;
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: space-evenly;
    width: 300px;
    height: 150px;
    padding: 40px;
    text-align: center;
    background-color: rgba(255, 255, 255);
    border-radius: 10px;
    box-shadow: 0 2px 3px 0 rgba(34, 36, 38, 0.15);

    span {
        margin: 3px auto;
    }

`

const ModalButton = styled.button`
    background-color: #FFFFFF;
    border-radius: 8px;
    border: #d09aff solid 1px;
    cursor: pointer;
    width: 60%;
    height: 40px;
    font-size: 16px;
    color: #8b00ff;
    margin: 30px auto 0 auto;
`

const Description = styled.div`
`
