import React from 'react'
import styled from 'styled-components'
import useStore from '../store/store'
import formatCurrency from '../utils/formatCurrency'
import { Link as RouterLink } from 'react-router-dom'

export default function Profile() {

    const { user, fundings } = useStore()

    // const user = {
    //     "id": 6,
    //     "email": "ryanbae94@naver.com",
    //     "nickname": "qweqw123",
    //     "profile": "https://fundy-bucket.s3.ap-northeast-2.amazonaws.com/default/profileImage.png",
    //     "accounts": [
    //       {
    //         "id": 6,
    //         "number": "33416-181-775",
    //         "balance": 0
    //       }
    //     ]
    //   }

    // const fundings = [
    //     {
    //       "fundingTransactionId": 3,
    //       "projectId": 1,
    //       "rewardId": 3,
    //       "accountId": 6,
    //       "rewardTitle": "리워드3",
    //       "rewardItem": "게임 C",
    //       "rewardImage": "https://fundy-bucket.s3.ap-northeast-2.amazonaws.com/default/profileImage.png",
    //       "amount": 100000,
    //       "refund": false
    //     },
    //     {
    //         "fundingTransactionId": 3,
    //         "projectId": 1,
    //         "rewardId": 3,
    //         "accountId": 6,
    //         "rewardTitle": "리워드3",
    //         "rewardItem": "게임 C",
    //         "rewardImage": "https://fundy-bucket.s3.ap-northeast-2.amazonaws.com/default/profileImage.png",
    //         "amount": 100000,
    //         "refund": false
    //       },
    //       {
    //         "fundingTransactionId": 3,
    //         "projectId": 1,
    //         "rewardId": 3,
    //         "accountId": 6,
    //         "rewardTitle": "리워드3",
    //         "rewardItem": "게임 C",
    //         "rewardImage": "https://fundy-bucket.s3.ap-northeast-2.amazonaws.com/default/profileImage.png",
    //         "amount": 100000,
    //         "refund": false
    //       },
    //       {
    //         "fundingTransactionId": 3,
    //         "projectId": 1,
    //         "rewardId": 3,
    //         "accountId": 6,
    //         "rewardTitle": "리워드3",
    //         "rewardItem": "게임 C",
    //         "rewardImage": "https://fundy-bucket.s3.ap-northeast-2.amazonaws.com/default/profileImage.png",
    //         "amount": 100000,
    //         "refund": false
    //       }
    //   ]

    const formattedBalance = formatCurrency(user.accounts[0].balance);

    return (

        <Container>
            <ProfileBox>
                <ProfileImageBox>
                    <ProfileImage src={user.profile}/>
                </ProfileImageBox>
                <Nickname>{ user.nickname }</Nickname>
                <FundingSection>
                    <FundingAmount>
                        {fundings === null ? 0 : fundings.length}개
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
                                <RewardsAmount>{formatCurrency(reward.amount)} 원</RewardsAmount>
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
    height: 800px;
    gap: 20px;
`

const RewardsCard = styled.div`
    display: flex;
    width: 344px;
    height: 340px;
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