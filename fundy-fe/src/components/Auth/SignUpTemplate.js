import React, { useState } from 'react'
import useStore from '../../store/store'
import styled from 'styled-components'
import checkEmailFormat from '../../utils/checkEmailFormat';
import checkPasswordFormat from '../../utils/checkPasswordFormat';
import checkNicknameFormat from '../../utils/checkNicknameFormat';
import {useNavigate} from 'react-router-dom';
import { checkNickname, getEmailAuthCode, signUp, verifyEmailAuthCode } from '../../apis/API';

export default function SignUpTemplate() {
    const navigate = useNavigate();

    // 입력 값 상태
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [nickname, setNickname] = useState('');
    const [code, setCode] = useState('');

    // 입력 값 유효성 체크
    const [isEmailValid, setIsEmailValid] = useState(false);
    const [isPasswordValid, setIsPasswordValid] = useState(false);
    const [isNicknameValid, setIsNicknameValid] = useState(null);

    // 닉네임 중복 체크
    const [isNicknameDuplicated, setIsNicknameDuplicated] = useState(false);

    // 이메일 인증 체크
    const [isVerifyEmail, setIsVerifyEmail] = useState(false);

    const { token, setToken } = useStore();

    const handleEmailChange = (e) => {
        const emailValue = e.target.value;
        setEmail(emailValue);
        setIsEmailValid(checkEmailFormat(emailValue));
    }

    const handlePasswordChange = (e) => {
        const passwordValue = e.target.value;
        setPassword(passwordValue);
        setIsPasswordValid(checkPasswordFormat(passwordValue));
    }

    const handleNicknameChange = (e) => {
        const nicknameValue = e.target.value;
        setNickname(nicknameValue);
        setIsNicknameValid(checkNicknameFormat(nicknameValue));
    }

    const handleCodeChange = (e) => {
        const codeValue = e.target.value;
        setCode(codeValue);
    }

    const checkNicknameDuplication = async () => {
        try {
            const response = await checkNickname(nickname);
            setIsNicknameDuplicated(response.result.duplicate);
        } catch (error) {
            console.log('닉네임 중복검사 중 오류 발생', error);
            setIsNicknameDuplicated(null);
        }
    }

    const handleCheckNickname = async (e) => {
        e.preventDefault();
        if (!isNicknameValid) {
            alert('유효하지 않은 닉네임입니다. 닉네임을 확인해주세요.')
            return;
        } else {
            await checkNicknameDuplication(nickname);
            if (!isNicknameDuplicated) {
                alert('사용 가능한 닉네임입니다.');
            } else {
                alert('중복된 닉네임입니다.');
            }
        }
    };

    const postEmailAuthCode = async () => {
        try {
            const response = await getEmailAuthCode(email);            
            setToken(response.result.token);
            return true;
        } catch (error) {
            console.log('인증코드 발송 실패', error)
            return false;
            
        }
    }

    const handleEmailAuthCode = async (e) => {
        e.preventDefault();
        const isSuccess = await postEmailAuthCode();
        if(isSuccess) {
            alert('인증코드 발송 완료')
        } else {
            alert('인증코드 발송 실패')
        }
    }

    const handleVerifyEmailAuthCode = async (e) => {
        e.preventDefault();
        try {
            const response = await verifyEmailAuthCode(email, token, code)
            if(response.result.verify) {
                setIsVerifyEmail(true);
                alert('인증완료')
            } else {
                setIsVerifyEmail(false);
                alert('인증 실패');
            }

        } catch (error) {
            console.log('인증 중 오류 발생', error)
            alert('인증 중 오류 발생')
            throw error;
        }

    }

    const performSignUp = async () => {
        try {
            await signUp(email, password, nickname);
            return true;
        } catch (error) {
            console.log('회원가입 실패', error)
            alert('회원가입 실패. 다시 시도 해주세요')
            return false;
        }
    }

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

        if (!isVerifyEmail) {
            alert('이메일 인증이 완료되지 않았습니다. 이메일 인증을 수행해주세요.');
            return;
        }
    
        // 닉네임 중복 검사 확인
        if (isNicknameDuplicated === null) {
            alert('닉네임 중복체크를 수행해주세요.');
            return;
        } else if (isNicknameDuplicated) {
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
                <EmailContainer>
                    <EmailInput 
                        type='email' 
                        placeholder='ex) fundy@fundy.com' 
                        value={email} 
                        onChange={handleEmailChange} 
                        isValid={isEmailValid}/>
                    <CheckButton onClick={handleEmailAuthCode}>인증코드발송</CheckButton>
                </EmailContainer>
            </Label>

            <Label>
                * 이메일 인증코드
                <CodeContainer>
                    <CodeInput 
                        type='code' 
                        placeholder='인증코드 작성' 
                        value={code} 
                        onChange={handleCodeChange}
                        isValid={true}/> 
                    <CheckButton onClick={handleVerifyEmailAuthCode}>인증하기</CheckButton>
                </CodeContainer>
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
    margin-top: 70px;

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

const EmailContainer = styled(NicknameContainer)``;
const EmailInput = styled(NicknameInput)``;
const CodeContainer = styled(NicknameContainer)``;
const CodeInput = styled(NicknameInput)``;