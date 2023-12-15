import React, { useEffect } from 'react'
import Profile from '../components/MyPage/Profile';
import useStore from '../store/store';
import styled from 'styled-components';

export default function MyPage() {
  const { getUserInfo } = useStore();

  useEffect(() => {
    const fetchData = async() => {
      await getUserInfo();

    }
    fetchData();

  }, [getUserInfo])
    
  return (
    <Body>
      <Profile />
    </Body>

    
  )
}

const Body = styled.body`

  background-color: #ebe9ea;
`