import React, { useState } from 'react'
import styled from 'styled-components';
import useStore from '../../store/store';
import { useNavigate } from 'react-router';
import { login } from '../../apis/API';
import useModal from '../../hooks/useModal';
import { Modal } from '../common';

export default function LoginTemplate() {

  const {       
    modalOpen,
    modalDescription,
    setModalDescription,
    modalAction,
    openModal,
    } = useModal();

  const { setToken, setIsLoggedIn, getUserInfo } = useStore();
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const navigate = useNavigate();

  const handleEmailChange = (e) => {
    const emailValue = e.target.value;
    setEmail(emailValue);
  }

  const handlePasswordChange = (e) => {
    const passwordValue = e.target.value;
    setPassword(passwordValue);
  }

  const performLogin = async () => {
    try {
      const response = await login(email, password);
      setIsLoggedIn(true)
      setToken(response.result.token);
      return true;

    } catch (error) {
      console.log('로그인 실패', error)
      return false;
      
    }
  }

  const handleLogin = async (e) => {
    e.preventDefault();
    const loginSuccess = await performLogin();
    if (loginSuccess) {
      await getUserInfo();
      navigate('/');
    } else {
      setModalDescription(`로그인에 실패하였습니다.\n아이디와 비밀번호를 확인해주세요.`)
      openModal();
      return;
    }
  }

  return (
    <div>
      <Container>
        <Title>로그인</Title>
        <form onSubmit={handleLogin}>
          <Label>
              * 이메일
              <Input 
                  type='email' 
                  placeholder='이메일 입력' 
                  value={email}
                  onChange={handleEmailChange} />
          </Label>

          <Label>
              * 비밀번호
              <Input
                  type='password' 
                  placeholder='비밀번호 입력'
                  value={password}
                  onChange={handlePasswordChange} />
          </Label>
          <SignUpButton type='submit'>로그인</SignUpButton>
        </form>
      </Container>
      <Modal
        isOpen={modalOpen}
        action={modalAction}
        description={modalDescription} />
    </div>
  );
};

const Container = styled.div`
  position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
  width: 670px;
  height: 580px;
  background: #FFFFFF;
  border: 1px solid #d09aff;
  box-shadow: 7px 7px 39px rgba(208, 154, 255, 0.25);
  border-radius: 20px;
  padding: 100px;
  box-sizing: border-box;
  display: flex;
  flex-direction: column;
`;

const Title = styled.h2`
  font-weight: 700;
  font-size: 32px;
  line-height: 47px;
  color: #8b00ff;
`;

const Label = styled.label`
  text-align: left;
  display: block;
  color: grey;
  margin-bottom: 10px;
  height: 69px;
`;

const Input = styled.input`
  padding: 0px;
  border: none;
  border-bottom: 1px solid #CFCFCF;
  width: 466px;
  height: 30px;

  &:focus {
      outline: none;
      box-shadow: 0 0 0 2px #8b00ff;
  }
`;

const SignUpButton = styled.button`
  margin-top: 50px;
  width: 400px;
  height: 50px;
  background-color: #FFFFFF;
  color: #8b00ff;
  border-radius: 8px;
  border: #d09aff solid 1px;
  cursor: pointer;
`;


