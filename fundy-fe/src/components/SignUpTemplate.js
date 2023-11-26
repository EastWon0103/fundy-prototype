import React, { useState } from 'react'
import useStore from '../store/store'
import styled from 'styled-components'
import isValidEmail from '../utils/isValidateEmail';
import isValidPassword from '../utils/isValidatePassword';
import isValidateNickname from '../utils/isValidateNickname';
import {useNavigate} from 'react-router-dom';

export default function SignUpTemplate() {
    const navigate = useNavigate();

    const [isEmailValid, setIsEmailValid] = useState(false);
    const [isPasswordValid, setIsPasswordValid] = useState(false);
    const [isNicknameValid, setIsNicknameValid] = useState(false);

    const {
        email,
        password, 
        nickname, 
        setEmail, 
        setPassword, 
        setNickname, 
        checkValidNickname, 
        isValidNickname, 
        performSignUp } = useStore();

    const handleEmailChange = (e) => {
        const emailValue = e.target.value;
        setEmail(emailValue);
        setIsEmailValid(isValidEmail(emailValue));
    }

    const handlePasswordChange = (e) => {
        const passwordValue = e.target.value;
        setPassword(passwordValue);
        setIsPasswordValid(isValidPassword(passwordValue));
    }

    const handleNicknameChange = (e) => {
        const nicknameValue = e.target.value;
        setNickname(e.target.value);
        setIsNicknameValid(isValidateNickname(nicknameValue));
    }

    const handleCheckNickname = async (e) => {
        e.preventDefault();
        if (!isNicknameValid) {
            alert('유효하지 않은 닉네임입니다. 닉네임을 확인해주세요.')
            return;
        } else {
            const isValid = await checkValidNickname();
            if (isValid) {
                alert('사용 가능한 닉네임입니다.');
            } else {
                alert('중복된 닉네임입니다.');
            }
        }
    };


    const handleSignUp = async (e) => {
        e.preventDefault();
    
        // 이메일과 비밀번호, 닉네임 유효성 검사
        if (!isEmailValid) {
            alert('유효하지 않은 이메일 주소입니다. 이메일을 확인해주세요.');
            return;
        }
    
        if (!isPasswordValid) {
            alert('유효하지 않은 비밀번호입니다. 비밀번호를 확인해주세요.');
            return;
        }

        if (!isNicknameValid) {
            alert('유효하지 않은 닉네임입니다. 닉네임을 확인해주세요.');
            return;
        }
    
        // 닉네임 중복 검사 확인
        if (isValidNickname === undefined) {
            alert('닉네임 중복체크를 수행해주세요.');
            return;
        } else if (!isValidNickname) {
            alert('닉네임이 중복입니다.');
            return;
        }

        // 모든 조건이 충족되면 회원가입 수행
        const signUpSuccess = await performSignUp();
        if (signUpSuccess) {
            navigate('/');
        }

    };

    return (
        <Container>
          <Title>회원가입을 위해<br />정보를 입력해주세요.</Title>
          <form onSubmit={handleSignUp}>
            <Label>
                * 이메일
                <Input 
                    type='email' 
                    placeholder='ex) fundy@fundy.com' 
                    value={email} 
                    onChange={handleEmailChange} 
                    isValid={isEmailValid}/>
            </Label>

                <Label>
                    * 닉네임
                    <NicknameContainer>
                        <NicknameInput
                            type='nickname'
                            placeholder='2자 이상'  
                            value={nickname} 
                            onChange={handleNicknameChange} 
                            isValid={isNicknameValid} />
                        <CheckButton onClick={handleCheckNickname}>중복확인</CheckButton>
                    </NicknameContainer>
                </Label>

            <Label>
                * 비밀번호
                <Input
                    type='password' 
                    placeholder='10자 이상, 대소문자, 숫자, 특수문자 하나 이상 포함'
                    value={password} 
                    onChange={handlePasswordChange} 
                    isValid={isPasswordValid} />
            </Label>
                <InputCheckbox type="checkbox" className="agree" /> 이용약관 개인정보 수집 및 정보이용에 동의합니다.
            <SignUpButton type='submit'>가입하기</SignUpButton>
          </form>
        </Container>
      );
    };

const Container = styled.div`
    position: absolute;
    left: 50%;
    top: 50%;
    transform: translate(-50%, -50%);
    width: 670px;
    height: 760px;
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
    // font-family: 'Noto Sans CJK KR';
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
        box-shadow: ${(props) => props.isValid ? '0 0 0 2px #8b00ff' : '0 0 0 2px red'};
    }
`;

const NicknameInput = styled(Input)`
    width: 330px;
    height: 30px;
`;

const InputCheckbox = styled.input`
    font-size: 20pt;
    width: 15px;
    height: 15px;
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

const NicknameContainer = styled.div`
    display: flex;
    align-items: center;
    margin-bottom: 10px; // 필요에 따라 조정
`;

const CheckButton = styled.button`
    margin-left: 50px;
    background-color: #FFFFFF;
    border-radius: 8px;
    border: #d09aff solid 1px;
    cursor: pointer;
    width: 100px;
    height: 40px;
    color: #8b00ff;
`;
