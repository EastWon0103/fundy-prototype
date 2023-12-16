import React from 'react'
import styled from 'styled-components'
import formatDate from '../../utils/formatDate'
import formatCurrency from '../../utils/formatCurrency';
import useModal from '../../hooks/useModal';
import { Modal } from '../common';

export default function ProjectDescription({project}) {

  const formattedStartDate = formatDate(project.startDateTime);
  const formattedEndDate = formatDate(project.endDateTime);
  const formattedFundingAmount = formatCurrency(project.totalFundingAmount);
  const formattedTargetAmount = formatCurrency(project.targetAmount);
  const formattedPercentage = project.percentage.toFixed(2);
  const {       
    modalOpen,
    modalDescription,
    setModalDescription,
    setModalAction,
    modalAction,
    openModal,
    closeModalAndRefresh,
    } = useModal();

  const handleFundingClick = async () => {
    try {
      const response = await fetch('http://fundy-game.com/batch/execute?id=1', {
        method: 'GET',
      });
      if(!response.ok) {
        throw new Error('Request failed');
      }
      setModalDescription('배치 프로그램 실행 완료.\n프로젝트가 만료 되었습니다.');
      setModalAction(() => closeModalAndRefresh)
      openModal();

    } catch (error) {
      console.log('Error', error)
      
    }
  }


  return (
    <div>    
      <Container>
        <CardSection>
          <CardImage src={project.thumbnail} />
        </CardSection>
        <DetailSection>
          <GenreSection>
            {project.genres.map((genre) => (
              <GenreCard>{genre}</GenreCard>
            ))}
          </GenreSection>
          <TitleSection>
            <Title>{project.title}</Title>
          </TitleSection>
          <DescriptionSection>{project.description}</DescriptionSection>
          <DivisionLine />
          <PeriodBox>
            <PeriodSection>
              <PeriodTitle>펀딩 기간</PeriodTitle>
              <PeriodDate>{formattedStartDate} ~ {formattedEndDate}</PeriodDate>
              {
                project.ended
                ? <PeriodDate>기간이 만료되었습니다.</PeriodDate>
                : null
              }
            </PeriodSection>
            <PeriodSection>
              <PeriodTitle>개발노트 업로드</PeriodTitle>
              <PeriodDate>매 {project.devNoteUploadCycle}주 마다, {project.devNoteUploadDay}에.</PeriodDate>
            </PeriodSection>
          </PeriodBox>
          <GoalPriceSection>
            <GoalPriceTitle>목표금액</GoalPriceTitle>
            <GoalPrice>
              {formattedTargetAmount}<CurrencyUnit>원</CurrencyUnit>
              </GoalPrice>
            <TotalFundingPriceTitle>총 펀딩 금액</TotalFundingPriceTitle>
            <TotalFundingPrice>
              {formattedFundingAmount}
              <CurrencyUnit>원 </CurrencyUnit>
              <Percentage>{formattedPercentage}{'%'} 달성</Percentage>
            </TotalFundingPrice>
          </GoalPriceSection>
          {
            project.ended
            ? <FundingButton disabled>펀딩이 만료되었습니다.</FundingButton>
            : <FundingButton onClick={handleFundingClick}>프로젝트 마감{'('}자금조달{')'}</FundingButton>
          }
        </DetailSection>
      </Container>
      <Modal
        isOpen={modalOpen}
        action={modalAction}
        description={modalDescription} />
    </div>
  )


}

const Container = styled.div`
  text-align: left;
  display: flex;
  margin: 0px auto;
  height: 880px;
  width: 96%;
  justify-content: space-around;
  align-items: center;
  background-color: #fff;
`
const CardSection = styled.div`
  width: 588px;
  height: 720px;
`
const CardImage = styled.img`
  width: 100%;
  height: 100%;
  border-radius: 14px;
`
const DetailSection = styled.div`
  margin-left: 40px;
  width: 588px;
  height: 720px;
`
const GenreSection = styled.div`
  display: flex;
`
const GenreCard = styled.div`
  height: 26px;
  margin: 0 10px 0 0;
  padding: 0 4px 0 4px;
  background: #ebe9ea;
  color: rgba(0, 0, 0, 0.32);
`
const TitleSection = styled.div`
  display: flex;
  background-color: #fff;
  height: 77px;
  align-items: center;
`
const Title = styled.h1`
  margin: 0;
  font-size: 48px;
`
const DescriptionSection = styled.div`
  padding-top: 10px;
  background-color: #fff;
  color: rgba(6, 39, 59, 0.60);
  text-align: left;
  font-size: 18px;
`
const DivisionLine = styled.div`
  border: 1px solid rgba(6, 39, 59, 0.20);
  margin: 12px 0 16px 0;
`
const PeriodBox = styled.div`
  margin-top: 30px;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  height: 130px;
  background-color: #fff;
  font-size: 16px;
`
const PeriodSection = styled.div`
  display: flex;
  flex-direction: column;
`
const PeriodTitle = styled.span`
  font-weight: bold;
  margin-bottom: 8px;
`
const PeriodDate = styled.span``

const GoalPriceSection = styled.div`
  margin: 50px 0 10px 0;
  display: flex;
  flex-direction: column;
`
const GoalPriceTitle = styled.span`
  font-size: 24px;
`
const GoalPrice = styled.span`
  color: #685bfe;
  font-size: 32px;
  font-weight: bold;
`
const TotalFundingPriceTitle = styled(GoalPriceTitle)`
  margin-top: 20px;
`
const TotalFundingPrice = styled(GoalPrice)``

const CurrencyUnit = styled.span`
  color: black;
  font-size: 24px;
  font-weight: normal;
`

const Percentage = styled(CurrencyUnit)`
  margin-left: 30px;
  font-weight: bold;
`

const FundingButton = styled.button`
  font-family: 'Orbit-Regular';
  display: flex;
  align-items: center;
  justify-content: center;
  margin: 50px auto;
  width: 400px;
  height: 50px;
  color: #FFFFFF;
  background-color: ${props => props.disabled ? '#ccc' : '#675bfe'};
  border: ${props => props.disabled ? '#ccc solid 1px' : '#685bfe solid 1px'};
  cursor: ${props => props.disabled ? 'default' : 'pointer'};
  font-weight: bold;
  font-size: 16px;
`