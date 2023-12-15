import React from 'react'
import styled from 'styled-components'
import formatCurrency from '../../utils/formatCurrency'
import PdfContent from './PdfContent'
import useStore from '../../store/store'
import { funding } from '../../apis/API'


export default function ProjectBottom({ project }) {
    const { user, token } = useStore();

    const performFunding = async (rewardId, amount) => {
        if (user.accounts[0].balance >= amount) {
            try {
                await funding(rewardId, user.id, amount, token);
                return true;
            } catch (error) {
                console.log('후원 에러', error);
                throw error;
                
            }
        } else {
            console.log('잔고 부족');
            return false;
        }

    }

    const handleFundingClick = async (rewardId, amount) => {
        try {
            const isSuccess = await performFunding(rewardId, amount);
            if(isSuccess) {
                alert('후원성공');
                window.location.reload();
            } else {
                alert('잔고 부족')
            }
        } catch (error) {
            console.log('에러', error)
        }
    }


  return (
    <Container>
        <ProjectContent>
            <PdfContent pdf={project.content} scale={1} width={790} />
        </ProjectContent>
        <RewardBox>
            {project.rewards.map((reward) => (
                <RewardCard>
                    <TitleSection>
                        <ImageBox>
                            <Image src={reward.image} />
                        </ImageBox>
                        <RewardTitleBox>
                            <RewardTitle>{reward.title}</RewardTitle>
                            <RewardAmount>{formatCurrency(reward.minimumPrice)}<Currency> 원</Currency></RewardAmount>
                        </RewardTitleBox>
                    </TitleSection>
                    <RewardItemList>
                        <RewardItem>{reward.item}</RewardItem>
                        <RewardItem>{reward.item}</RewardItem>
                        <RewardItem>{reward.item}</RewardItem>
                    </RewardItemList>
                    <FundingButton onClick={() => handleFundingClick(reward.id, reward.minimumPrice)}>{formatCurrency(reward.minimumPrice)}원 후원하기</FundingButton>
                </RewardCard>
            ))}

        </RewardBox>

    </Container>
  )
}

const Container = styled.div`
    display: flex;
    margin: auto;
    flex-direction: row;
    width: 96%;

    background-color: #fff;
`

const ProjectContent = styled.div`
    margin: 50px 30px 0 auto;
    width: 832px;
    background-color: black;
`

const RewardBox = styled.div`
    width: 589px;
    margin: 50px auto 0 0;
    display: flex;
    flex-direction: column;


`
const RewardCard = styled.div`
    display: flex;
    flex-direction: column;
    margin: 6px 0 6px 0;
    border: 3px solid #EBE9EA;
    background-color: #fff;
`
const ImageBox = styled.div`
    height: 150px;
    width: 150px;
    border: 2px solid #ebe9ea;
    margin: 30px 30px 10px 30px;
`

const Image = styled.img`
    height: 150px;
    width: 150px;
`

const TitleSection = styled.div`
    display: flex;
    align-items: center;

    
`

const RewardTitleBox = styled.div`
    display: flex;
    flex-direction: column;
    text-align: left;
`

const RewardTitle = styled.span`
    color: rgba(6, 39, 59, 0.80);
    font-size: 20px;
    margin-bottom: 10px;
`

const RewardAmount = styled.span`
    color: #685bfe;
    font-weight: 700;
    font-size: 24px;
`
const Currency = styled.span`
    color: black;
    font-size: 20px;
`

const RewardItemList = styled.ul`
    text-align: left;
    margin: 20px 0 10px 10px;
    color: rgba(6, 39, 59, 0.40);

`

const RewardItem = styled.li`
`

const FundingButton = styled.button`
  font-family: 'Orbit-Regular';
  display: flex;
  align-items: center;
  justify-content: center;
  margin: 30px auto 40px auto;
  width: 400px;
  height: 50px;
  color: #FFFFFF;
  background-color: #675bfe;
  border: #685bfe solid 1px;
  font-weight: bold;
  font-size: 16px;
  cursor: pointer;

`