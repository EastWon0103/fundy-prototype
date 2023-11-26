import React from 'react'
import {Link, useLocation} from 'react-router-dom'
import styled from 'styled-components'
import logo from '../images/logo.png'
import logoText from '../images/logoText.png'
import useScroll from '../hooks/useScroll'

import useStore from '../store/store'

export default function Navigator() {

    const { isLoggedIn, user } = useStore();

    const show = useScroll(50);
    const location = useLocation();

  return (
    <Container show={show}>
        <StyledLink to='/'>
            <NavLogo
                alt='nav logo'
                src={logo}
                onClick={() => location.pathname === '/' ? window.location.reload() : null}
                />
        </StyledLink>

        <StyledLink to='/'>
            <NavLogoText
                alt='nav logo text'
                src={logoText}
                onClick={() => location.pathname === '/' ? window.location.reload() : null}  
                />
        </StyledLink>

        <NavButtons>

            {isLoggedIn ? (
                <UserGreeting show={show}>{'>'} {user.nickname} {'<'}</UserGreeting>
            ) : (
                <>
                    <Link to='/login'>
                        <LoginButton>로그인</LoginButton>
                    </Link>
                    <span>{'|'}</span>
                    <Link to='/signup'>
                        <SignUpButton>회원가입</SignUpButton>
                    </Link>
                </>

            )}

            <ProjectButton show={show}>프로젝트 만들기</ProjectButton>
        </NavButtons>
        
    </Container>
    
  )
}

const Container = styled.nav`
    position: fixed;
    top: 0;
    width: 100%;
    height: 30px;
    z-index: 1;
    padding: 20px;
    display: flex;
    justify-content: space-between;
    align-items: center;
    transition-timing-function: ease-in;
    transition: all 0.5s;
    background-color: ${(props) => props.show ? '#d09aff' : 'white'};
`

const NavLogo = styled.img`
    position: fixed;
    left: 30px;
    object-fit: contain;
    cursor: pointer;
`

const NavLogoText = styled.img`
    position: fixed;
    left: 88px;
    object-fit: contain;
    cursor: pointer;
`

const NavButtons = styled.button`
    background-color: transparent;
    margin-left: auto;
    margin-right: 40px;
    display: flex;
    align-items: center;
    border: none;
`

const ButtonStyle = styled.button`
    font-family: Orbit-Regular;
    background-color: transparent;
    border: none;
    cursor: pointer;
    &:hover {
        background-color: rgba(255, 255, 255, 0.5);
    }
`;

const SignUpButton = styled(ButtonStyle)`
    margin-right: 4px;
`;

const LoginButton = styled(ButtonStyle)``;

const ProjectButton = styled(ButtonStyle)`
    font-weight: bold;
    background-color: ${(props) => props.show ? '#946cee' : 'white'};
    border: ${(props) => props.show ? 'solid white' : 'solid #946cee'};
    font-size: 18px;
    padding: 4px 10px;
    border-radius: 15px;
    transition-timing-function: ease-in;
    transition: all 0.5s;
`;

const StyledLink = styled(Link)`
    display: flex; // 혹은 display: block;
    align-items: center; // 수직 중앙 정렬을 위해
`;

const UserGreeting = styled.span`
    font-family: Orbit-Regular;
    font-weight: bold;
    font-size: 20px;
    margin-right: 10px;
`;

