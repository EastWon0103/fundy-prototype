import React from 'react'
import styled from 'styled-components'

export default function GoToDevNote() {
  return (
    <Container>
        <Section>{'>'} 개발노트 확인 하기 {'<'}</Section>
    </Container>
  )
}

const Container = styled.div`
    display: flex;
    justify-content: center;
    align-items: center;
    margin: 10px auto;
    display: flex; 
    width: 96%;
    height: 98px;
    background-color: #fff;
`
const Section = styled.button`
    font-family: 'Orbit-Regular';
    border: none;
    background: none;
    text-align: center;
    font-size: 24px;
    cursor: pointer;
`
