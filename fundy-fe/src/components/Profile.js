import React from 'react'
import styled from 'styled-components'
import useStore from '../store/store'
import formatCurrency from '../utils/formatCurrency'

export default function Profile() {

    const { user } = useStore()

    const formattedBalance = formatCurrency(user.accounts[0].balance);

    const games = [{
        id: 1,
        image: 'https://s3-alpha-sig.figma.com/img/9945/33a5/614890d3c056cec00b07ae3b1a6a96ba?Expires=1702252800&Signature=Xrtdb0xoSWhG-mpS2hpQZLSJp~uR7eQXJ7hjKWrkcULpY5VDJmxa6VKnADVu3pfKWTQquM7IzQbS2OAJfIH9P77q6VHLmkIZpwavhlDPcGzX5C3oV08zXtxedwxvmF77xC3HHgzMVuZGFWF~Ixu~W~IQrZkTqalbmqIHvivYV1Hex23L8JVaY-wYFKBMZ-51khlzOzv~kWGioPJd57hElIrkW05aBMq9JxAxR1PFXP5Vc1Mo2DaL2AwG9lDyRHCp2RQ2w8OZE9wlRc4d9uHIivSyLgEt3dh0dBNiIf3DT2wd3p69HWxIv2Q1H5FlMZ7PYYNEx~XnCapmKfsHwWvYBw__&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4',
        
        title: 'maplestory'   },
    {
        id: 2,
        image: 'https://s3-alpha-sig.figma.com/img/9945/33a5/614890d3c056cec00b07ae3b1a6a96ba?Expires=1702252800&Signature=Xrtdb0xoSWhG-mpS2hpQZLSJp~uR7eQXJ7hjKWrkcULpY5VDJmxa6VKnADVu3pfKWTQquM7IzQbS2OAJfIH9P77q6VHLmkIZpwavhlDPcGzX5C3oV08zXtxedwxvmF77xC3HHgzMVuZGFWF~Ixu~W~IQrZkTqalbmqIHvivYV1Hex23L8JVaY-wYFKBMZ-51khlzOzv~kWGioPJd57hElIrkW05aBMq9JxAxR1PFXP5Vc1Mo2DaL2AwG9lDyRHCp2RQ2w8OZE9wlRc4d9uHIivSyLgEt3dh0dBNiIf3DT2wd3p69HWxIv2Q1H5FlMZ7PYYNEx~XnCapmKfsHwWvYBw__&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4',
        
        title: 'maplestory'   },
    {
        id: 3,
        image: 'https://s3-alpha-sig.figma.com/img/9945/33a5/614890d3c056cec00b07ae3b1a6a96ba?Expires=1702252800&Signature=Xrtdb0xoSWhG-mpS2hpQZLSJp~uR7eQXJ7hjKWrkcULpY5VDJmxa6VKnADVu3pfKWTQquM7IzQbS2OAJfIH9P77q6VHLmkIZpwavhlDPcGzX5C3oV08zXtxedwxvmF77xC3HHgzMVuZGFWF~Ixu~W~IQrZkTqalbmqIHvivYV1Hex23L8JVaY-wYFKBMZ-51khlzOzv~kWGioPJd57hElIrkW05aBMq9JxAxR1PFXP5Vc1Mo2DaL2AwG9lDyRHCp2RQ2w8OZE9wlRc4d9uHIivSyLgEt3dh0dBNiIf3DT2wd3p69HWxIv2Q1H5FlMZ7PYYNEx~XnCapmKfsHwWvYBw__&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4',
        
        title: 'maplestory'   },
    {
        id: 4,
        image: 'https://s3-alpha-sig.figma.com/img/9945/33a5/614890d3c056cec00b07ae3b1a6a96ba?Expires=1702252800&Signature=Xrtdb0xoSWhG-mpS2hpQZLSJp~uR7eQXJ7hjKWrkcULpY5VDJmxa6VKnADVu3pfKWTQquM7IzQbS2OAJfIH9P77q6VHLmkIZpwavhlDPcGzX5C3oV08zXtxedwxvmF77xC3HHgzMVuZGFWF~Ixu~W~IQrZkTqalbmqIHvivYV1Hex23L8JVaY-wYFKBMZ-51khlzOzv~kWGioPJd57hElIrkW05aBMq9JxAxR1PFXP5Vc1Mo2DaL2AwG9lDyRHCp2RQ2w8OZE9wlRc4d9uHIivSyLgEt3dh0dBNiIf3DT2wd3p69HWxIv2Q1H5FlMZ7PYYNEx~XnCapmKfsHwWvYBw__&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4',
        
        title: 'maplestory'   },

    {
        id: 5,
        image: 'https://s3-alpha-sig.figma.com/img/9945/33a5/614890d3c056cec00b07ae3b1a6a96ba?Expires=1702252800&Signature=Xrtdb0xoSWhG-mpS2hpQZLSJp~uR7eQXJ7hjKWrkcULpY5VDJmxa6VKnADVu3pfKWTQquM7IzQbS2OAJfIH9P77q6VHLmkIZpwavhlDPcGzX5C3oV08zXtxedwxvmF77xC3HHgzMVuZGFWF~Ixu~W~IQrZkTqalbmqIHvivYV1Hex23L8JVaY-wYFKBMZ-51khlzOzv~kWGioPJd57hElIrkW05aBMq9JxAxR1PFXP5Vc1Mo2DaL2AwG9lDyRHCp2RQ2w8OZE9wlRc4d9uHIivSyLgEt3dh0dBNiIf3DT2wd3p69HWxIv2Q1H5FlMZ7PYYNEx~XnCapmKfsHwWvYBw__&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4',
        
        title: 'maplestory'   },
    {
        id: 6,
        image: 'https://s3-alpha-sig.figma.com/img/9945/33a5/614890d3c056cec00b07ae3b1a6a96ba?Expires=1702252800&Signature=Xrtdb0xoSWhG-mpS2hpQZLSJp~uR7eQXJ7hjKWrkcULpY5VDJmxa6VKnADVu3pfKWTQquM7IzQbS2OAJfIH9P77q6VHLmkIZpwavhlDPcGzX5C3oV08zXtxedwxvmF77xC3HHgzMVuZGFWF~Ixu~W~IQrZkTqalbmqIHvivYV1Hex23L8JVaY-wYFKBMZ-51khlzOzv~kWGioPJd57hElIrkW05aBMq9JxAxR1PFXP5Vc1Mo2DaL2AwG9lDyRHCp2RQ2w8OZE9wlRc4d9uHIivSyLgEt3dh0dBNiIf3DT2wd3p69HWxIv2Q1H5FlMZ7PYYNEx~XnCapmKfsHwWvYBw__&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4',
        
        title: 'maplestory'   },
    {
        id: 7,
        image: 'https://s3-alpha-sig.figma.com/img/9945/33a5/614890d3c056cec00b07ae3b1a6a96ba?Expires=1702252800&Signature=Xrtdb0xoSWhG-mpS2hpQZLSJp~uR7eQXJ7hjKWrkcULpY5VDJmxa6VKnADVu3pfKWTQquM7IzQbS2OAJfIH9P77q6VHLmkIZpwavhlDPcGzX5C3oV08zXtxedwxvmF77xC3HHgzMVuZGFWF~Ixu~W~IQrZkTqalbmqIHvivYV1Hex23L8JVaY-wYFKBMZ-51khlzOzv~kWGioPJd57hElIrkW05aBMq9JxAxR1PFXP5Vc1Mo2DaL2AwG9lDyRHCp2RQ2w8OZE9wlRc4d9uHIivSyLgEt3dh0dBNiIf3DT2wd3p69HWxIv2Q1H5FlMZ7PYYNEx~XnCapmKfsHwWvYBw__&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4',
        
        title: 'maplestory'   },
    {
        id: 8,
        image: 'https://s3-alpha-sig.figma.com/img/9945/33a5/614890d3c056cec00b07ae3b1a6a96ba?Expires=1702252800&Signature=Xrtdb0xoSWhG-mpS2hpQZLSJp~uR7eQXJ7hjKWrkcULpY5VDJmxa6VKnADVu3pfKWTQquM7IzQbS2OAJfIH9P77q6VHLmkIZpwavhlDPcGzX5C3oV08zXtxedwxvmF77xC3HHgzMVuZGFWF~Ixu~W~IQrZkTqalbmqIHvivYV1Hex23L8JVaY-wYFKBMZ-51khlzOzv~kWGioPJd57hElIrkW05aBMq9JxAxR1PFXP5Vc1Mo2DaL2AwG9lDyRHCp2RQ2w8OZE9wlRc4d9uHIivSyLgEt3dh0dBNiIf3DT2wd3p69HWxIv2Q1H5FlMZ7PYYNEx~XnCapmKfsHwWvYBw__&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4',
        
        title: 'maplestory'   },
    
    
]


    return (
        <PageContainer>
            <SideBar>
                <Avatar src={user.profile} />
                <UserName>{user.nickname}</UserName>
                <p>{user.email}</p>
                <EditButton>프로필 수정</EditButton>
                <p>잔고: {formattedBalance} 원</p>
                <p>소유한 게임: {games.length}</p>
            </SideBar>
            <MainContent>
                <Header>
                    <Title>보관함</Title>
                    {/* 필터 버튼 등의 헤더 요소 */}
                </Header>
                <GameList>
                    {games.map((game) => (
                    <GameCard key={game.id}>
                        <GameImage src={game.image} />
                        <GameTitle>{game.title}</GameTitle>
                        {/* 기타 게임 정보 */}
                    </GameCard>
                    ))}
                </GameList>
            </MainContent>
        </PageContainer>
        );
    }
                
const PageContainer = styled.div`
    margin: 200px auto;
    padding: 20px;
    border-radius: 12px;
    box-shadow: 2px 2px 14px 0 rgba(0,164,73,.08);
    border: 1px solid #d09aff;
    background-color: #fff;
    box-sizing: border-box;
    max-width: 2000px;
    display: flex;
`;

const SideBar = styled.div`
    width: 240px; /* 사이드바 너비 */
    background: #fff; /* 사이드바 배경색 */
    padding: 50px;
    box-shadow: 2px 0 5px rgba(0, 0, 0, 0.1); /* 그림자 효과 */
    margin: 40px;
    border-radius: 12px;
`;

const Avatar = styled.img`
    width: 100px;
    height: 100px;
    border-radius: 50%;
    margin-bottom: 0px;
    background: #ccc; /* 아바타 배경 색상 */
`;

const UserName = styled.h1`
    font-size: 2.4em;
    color: #333; /* 글자 색상 */
    margin-bottom: 20px;
`;


const MainContent = styled.main`
    flex-grow: 1; /* 메인 콘텐츠가 남은 공간을 모두 차지하도록 설정 */
`;

const Header = styled.header`
    padding: 20px;
    display: flex;
    align-items: center;
    justify-content: space-between;

`;

const Title = styled.h2`
    font-size: 1.5em;
    color: #333; /* 글자 색상 */
`;

const GameList = styled.section`
    display: flex;
    flex-wrap: wrap; /* 게임 카드가 여러 줄로 표시되도록 설정 */
    padding: 20px;
    
    gap: 40px; /* 카드 간격 */
`;

const GameCard = styled.div`
    width: 180px; /* 카드 너비 */
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1); /* 그림자 효과 */
    border-radius: 8px;
    overflow: hidden; /* 이미지가 넘치는 것을 방지 */
    background: #fff; /* 카드 배경색 */
`;

const GameImage = styled.img`
    width: 100%;
    height: auto; /* 이미지의 높이를 자동으로 설정하여 비율 유지 */
`;

const GameTitle = styled.h3`
    font-size: 1em;
    color: #333; /* 글자 색상 */
    padding: 10px;
`;

const EditButton = styled.button`
    margin: 30px auto;
    background-color: #FFFFFF;
    border-radius: 8px;
    border: #d09aff solid 1px;
    cursor: pointer;
    width: 200px;
    height: 40px;
    color: #8b00ff;
`;
