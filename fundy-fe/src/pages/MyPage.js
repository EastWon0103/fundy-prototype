import React, { useEffect } from 'react'
import Profile from '../components/Profile';
import useStore from '../store/store';

export default function MyPage() {
  const getUserInfo = useStore(state => state.getUserInfo);

  useEffect(() => {
    getUserInfo();
  }, [getUserInfo])
    
  return (
    <Profile />
    
  )
}