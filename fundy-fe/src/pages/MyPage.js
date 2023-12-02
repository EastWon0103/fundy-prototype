import React from 'react'
import useStore from '../store/store'
import styled from 'styled-components';
import Profile from '../components/Profile';

export default function MyPage() {
    
  return (
    <Profile />
    
  )
}



// {"id":4,
// "email":"ryanbae94@gmail.com",
// "nickname":"te",
// "profile":"https://fundy-bucket.s3.ap-northeast-2.amazonaws.com/default/profileImage.png",
// "accounts":[{"id":4,"number":"35330-525-753","balance":100000}]}

const Div = styled.div`
    margin-top: 100px;
`