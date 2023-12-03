import React, { useEffect } from 'react'
import Profile from '../components/Profile';
import useStore from '../store/store';
import styled from 'styled-components';

export default function MyPage() {
  const { getUserInfo, getFundings } = useStore();

  useEffect(() => {
    const fetchData = async() => {
      await getUserInfo();
      await getFundings();

    }
    fetchData();

  }, [getUserInfo, getFundings])
    
  return (
    <Body>
      <Profile />
    </Body>

    
  )
}

const Body = styled.body`

  background-color: #ebe9ea;
`