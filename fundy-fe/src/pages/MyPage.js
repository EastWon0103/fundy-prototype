import React from 'react'
import Profile from '../components/MyPage/Profile';
import styled from 'styled-components';

export default function MyPage() {

  return (
    <Body>
      <Profile />
    </Body>

    
  )
}

const Body = styled.body`

  background-color: #ebe9ea;
`