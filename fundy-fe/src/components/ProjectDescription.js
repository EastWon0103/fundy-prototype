import React from 'react'
import styled from 'styled-components'
import formatDate from '../utils/formatDate'

export default function ProjectTop({project}) {

  const formatedStartDate = formatDate(project.startDateTime)
  const formatedEndDate = formatDate(project.endDateTime);
  return (
    <Container>
      <CardSection>
        <CardImage src='https://s3-alpha-sig.figma.com/img/9945/33a5/614890d3c056cec00b07ae3b1a6a96ba?Expires=1702252800&Signature=Xrtdb0xoSWhG-mpS2hpQZLSJp~uR7eQXJ7hjKWrkcULpY5VDJmxa6VKnADVu3pfKWTQquM7IzQbS2OAJfIH9P77q6VHLmkIZpwavhlDPcGzX5C3oV08zXtxedwxvmF77xC3HHgzMVuZGFWF~Ixu~W~IQrZkTqalbmqIHvivYV1Hex23L8JVaY-wYFKBMZ-51khlzOzv~kWGioPJd57hElIrkW05aBMq9JxAxR1PFXP5Vc1Mo2DaL2AwG9lDyRHCp2RQ2w8OZE9wlRc4d9uHIivSyLgEt3dh0dBNiIf3DT2wd3p69HWxIv2Q1H5FlMZ7PYYNEx~XnCapmKfsHwWvYBw__&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4' />
      </CardSection>
      <DetailSection>
        <TitleSection>
          <Title>{project.title}</Title>
        </TitleSection>
        <DescriptionSection>{project.description}</DescriptionSection>
        <DivisionLine />
        <PeriodBox>
          <PeriodSection>
            <PeriodTitle>펀딩 기간</PeriodTitle>
            <PeriodDate>{formatedStartDate} ~ {formatedEndDate}</PeriodDate>
          </PeriodSection>
          <PeriodSection>
            <PeriodTitle>개발노트 업로드</PeriodTitle>
            <PeriodDate>매 {project.devNoteUploadCycle}주 마다, {project.devNoteUploadDay}에.</PeriodDate>
          </PeriodSection>
        </PeriodBox>
      </DetailSection>
    </Container>
  )
}


const Container = styled.div`
  text-align: left;
  display: flex;
  margin: 0 auto;
  height: 800px;
  width: 80%;
  justify-content: space-around;
  align-items: center;
  /* background-color: black; */
`

const CardSection = styled.div`
  width: 588px;
  height: 660px;
`

const CardImage = styled.img`
  width: 100%;
  height: 100%;
  border-radius: 14px;
`

const DetailSection = styled.div`
  /* display: flex;
  flex-direction: column;
  justify-content: space-around; */
  width: 588px;
  height: 660px;
  /* background-color: blueviolet; */
`

const TitleSection = styled.div`
  display: flex;
  background-color: #fff;
  height: 77px;
  align-items: center;
  /* justify-content: center; */
`

const Title = styled.h1`
  margin: 0;
`
const DescriptionSection = styled.div`
  padding-top: 10px;
  height: 116px;
  background-color: #fff;
  color: rgba(6, 39, 59, 0.60);
  text-align: left;
`
const DivisionLine = styled.div`
  border: 1px solid rgba(6, 39, 59, 0.20);
  margin-bottom: 12px;
`
const PeriodBox = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  height: 130px;
  background-color: #fff;
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
