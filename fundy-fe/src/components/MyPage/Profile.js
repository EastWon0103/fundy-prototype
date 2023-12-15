import React, { useEffect, useState } from 'react'
import styled from 'styled-components'
import useStore from '../../store/store'
import formatCurrency from '../../utils/formatCurrency'
import { Link as RouterLink } from 'react-router-dom'
import { getFundings, refunding } from '../../apis/API'

export default function Profile() {

    const { user, token, getUserInfo } = useStore();
    const [len, setLen] = useState(0);
    const formattedBalance = formatCurrency(user.accounts[0].balance);
    const [fundings, setFundings] = useState(null);

    const getFundingList = async () => {
        try {
            const response = await getFundings(token);
            setFundings(response.result);
        } catch (error) {
            throw error
        }
    }
    
    const handleRefund = async (token, transactionId, event) => {
        event.preventDefault();
        try {
            const response = await(refunding(token, transactionId));
            if (response) {
                alert('환불요청완료');
                await getUserInfo();
                await getFundingList();

            }
        } catch (error) {
            alert(error);
        }
    }

    useEffect(() => {
        getFundingList();
        if (fundings) {
            const count = fundings.reduce((acc, reward) => acc + (reward.refund ? 0 : 1), 0);
            setLen(count);
        }
    }, []);


    return (

        <Container>
            <ProfileBox>
                <ProfileImageBox>
                    <ProfileImage src={user.profile}/>
                </ProfileImageBox>
                <Nickname>{ user.nickname }</Nickname>
                <FundingSection>
                    <FundingAmount>
                        {fundings === null ? 0 : len}개
                    </FundingAmount>
                    <FundingDescriptioin>후원한 프로젝트</FundingDescriptioin>
                </FundingSection>
                <BalanceSection>잔고: {formattedBalance}원</BalanceSection>
            </ProfileBox>
            <MyGames>
                <TitleSection>보관함</TitleSection>
                <RewardsSection>
                    {fundings === null ? null : fundings.map((reward) => (
                        <Link to={`/project/${reward.projectId}`}>
                            <RewardsCard>
                                <RewardsImage src={reward.rewardImage}/>
                                <RewardsTitle>{reward.rewardTitle}</RewardsTitle>
                                { 
                                    reward.refund ? 
                                    <RewardsAmount>환불완료</RewardsAmount> :
                                    <> 
                                        <RewardsAmount>{formatCurrency(reward.amount)} 원</RewardsAmount>
                                        <RefundButton onClick={(event) => {
                                            handleRefund(token, reward.fundingTransactionId, event)
                                            }}>환불하기</RefundButton>
                                    </>
                                }
                            </RewardsCard>
                        </Link>
                    ))}
                </RewardsSection>
            </MyGames>
        </Container>

    )

}

const Container = styled.div`
    margin: 100px auto;
    display: flex;
    justify-content: space-around;
    gap: 40px;
    padding: 60px;
    max-width: 1680px;
`

const ProfileBox = styled.div`
    display: flex;
    flex-direction: column;
    align-items: center;
    width: 345px;
    height: 600px;
    padding: 20px;
    background-color: #fff;
`

const ProfileImageBox = styled.div`
    margin: 30px auto 20px auto;
    width: 146px;
    height: 146px;
    border-radius: 50%;
`

const ProfileImage = styled.img`
    width: 146px;
    height: 146px;
    border-radius: 50%;
`

const Nickname = styled.div`
    width: 220px;
    font-size: 28px;
    font-weight: 700;
    margin: 10px auto 30px auto;
`

const FundingSection = styled.div`
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    margin: 20px;
`

const FundingAmount = styled.span`
    color: #685bfe;
    font-size: 20px;
    font-weight: 700;
`

const FundingDescriptioin = styled.span`
    color: rgba(51, 51, 51, 0.60);
    font-size: 16px;
    font-weight: 400;
`

const BalanceSection = styled.div`
margin-top: 10px;
    text-align: center;
`

const MyGames = styled.div`
    display: flex;
    flex-direction: column;
    width: 1072px;
    background-color: #fff;
    padding: 10px 30px 10px 30px;
`

const TitleSection = styled.div`
    margin: 0 auto 20px auto;
    width: 100%;
    text-align: left;
    font-size: 28px;
    font-weight: 700;
`

const RewardsSection = styled.div`
    display: flex;
    flex-direction: row;
    flex-wrap: wrap;
    gap: 20px;
`

const RewardsCard = styled.div`
    display: flex;
    width: 344px;
    flex-direction: column;
    margin-bottom: 20px;
`

const RewardsImage = styled.img`
    width: 344px;
    height: 300px;
`

const RewardsTitle = styled.div`
    height: 22px;
    color: #333333;
    font-size: 18px;
    font-weight: 700;
    text-align: left;
    margin-top: 10px;
`

const RewardsAmount = styled.div`
    color: #686bfe;
    font-size: 18px;
    font-weight: 700;
    text-align: left;
    margin-top: 10px;
    height: auto;
`

const Link = styled(RouterLink)`
    text-decoration: none;
`

const RefundButton = styled.button`
    font-family: 'Orbit-Regular';
    display: flex;
    align-items: center;
    justify-content: center;
    margin-top: 10px;
    width: 100px;
    height: 38px;
    color: #FFFFFF;
    background-color: #675bfe;
    border: #685bfe solid 1px;
    font-weight: bold;
    font-size: 16px;
    cursor: pointer;
`