import { Link as RouterLink } from 'react-router-dom'
import React from 'react'
import styled from 'styled-components'

export default function ProjectsRow() {
  return (
    <Container>
        <TitleBox>
            <Title>🔥 신규 프로젝트</Title>
        </TitleBox>
        <ProjectSection>
            <Link to='/project/1'>
                <ProjectCard>
                    <CardImage src='https://s3-alpha-sig.figma.com/img/9945/33a5/614890d3c056cec00b07ae3b1a6a96ba?Expires=1702252800&Signature=Xrtdb0xoSWhG-mpS2hpQZLSJp~uR7eQXJ7hjKWrkcULpY5VDJmxa6VKnADVu3pfKWTQquM7IzQbS2OAJfIH9P77q6VHLmkIZpwavhlDPcGzX5C3oV08zXtxedwxvmF77xC3HHgzMVuZGFWF~Ixu~W~IQrZkTqalbmqIHvivYV1Hex23L8JVaY-wYFKBMZ-51khlzOzv~kWGioPJd57hElIrkW05aBMq9JxAxR1PFXP5Vc1Mo2DaL2AwG9lDyRHCp2RQ2w8OZE9wlRc4d9uHIivSyLgEt3dh0dBNiIf3DT2wd3p69HWxIv2Q1H5FlMZ7PYYNEx~XnCapmKfsHwWvYBw__&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4'/>
                    <GenreSection>
                        <GenreBox>RPG</GenreBox>
                        <GenreBox>액션</GenreBox>
                    </GenreSection>
                    <ProjectTitleBox>
                        <ProjectTitle>던전의 머시깽이 머시깽이</ProjectTitle>
                    </ProjectTitleBox>
                    <DescriptionBox>
                        카드오브던전클래식은 머시깽이하고 머시깽이하는 머시깽이입니당.
                    </DescriptionBox>
                    <AmountBox>
                        <AmountPercentage>1280% 달성</AmountPercentage>
                        <Amount>{'|'}1,400,000원</Amount>
                    </AmountBox>
                    <Progress value={20} max={100} />
                </ProjectCard>
            </Link>
            <Link to='/project/1'>
                <ProjectCard>
                    <CardImage src='https://s3-alpha-sig.figma.com/img/9945/33a5/614890d3c056cec00b07ae3b1a6a96ba?Expires=1702252800&Signature=Xrtdb0xoSWhG-mpS2hpQZLSJp~uR7eQXJ7hjKWrkcULpY5VDJmxa6VKnADVu3pfKWTQquM7IzQbS2OAJfIH9P77q6VHLmkIZpwavhlDPcGzX5C3oV08zXtxedwxvmF77xC3HHgzMVuZGFWF~Ixu~W~IQrZkTqalbmqIHvivYV1Hex23L8JVaY-wYFKBMZ-51khlzOzv~kWGioPJd57hElIrkW05aBMq9JxAxR1PFXP5Vc1Mo2DaL2AwG9lDyRHCp2RQ2w8OZE9wlRc4d9uHIivSyLgEt3dh0dBNiIf3DT2wd3p69HWxIv2Q1H5FlMZ7PYYNEx~XnCapmKfsHwWvYBw__&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4'/>
                    <GenreSection>
                        <GenreBox>RPG</GenreBox>
                        <GenreBox>액션</GenreBox>
                    </GenreSection>
                    <ProjectTitleBox>
                        <ProjectTitle>던전의 머시깽이 머시깽이</ProjectTitle>
                    </ProjectTitleBox>
                    <DescriptionBox>
                        카드오브던전클래식은 머시깽이하고 머시깽이하는 머시깽이입니당.
                    </DescriptionBox>
                    <AmountBox>
                        <AmountPercentage>1280% 달성</AmountPercentage>
                        <Amount>{'|'}1,400,000원</Amount>
                    </AmountBox>
                    <Progress value={20} max={100} />
                </ProjectCard>
            </Link>
            <Link to='/project/1'>
                <ProjectCard>
                    <CardImage src='https://s3-alpha-sig.figma.com/img/9945/33a5/614890d3c056cec00b07ae3b1a6a96ba?Expires=1702252800&Signature=Xrtdb0xoSWhG-mpS2hpQZLSJp~uR7eQXJ7hjKWrkcULpY5VDJmxa6VKnADVu3pfKWTQquM7IzQbS2OAJfIH9P77q6VHLmkIZpwavhlDPcGzX5C3oV08zXtxedwxvmF77xC3HHgzMVuZGFWF~Ixu~W~IQrZkTqalbmqIHvivYV1Hex23L8JVaY-wYFKBMZ-51khlzOzv~kWGioPJd57hElIrkW05aBMq9JxAxR1PFXP5Vc1Mo2DaL2AwG9lDyRHCp2RQ2w8OZE9wlRc4d9uHIivSyLgEt3dh0dBNiIf3DT2wd3p69HWxIv2Q1H5FlMZ7PYYNEx~XnCapmKfsHwWvYBw__&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4'/>
                    <GenreSection>
                        <GenreBox>RPG</GenreBox>
                        <GenreBox>액션</GenreBox>
                    </GenreSection>
                    <ProjectTitleBox>
                        <ProjectTitle>던전의 머시깽이 머시깽이</ProjectTitle>
                    </ProjectTitleBox>
                    <DescriptionBox>
                        카드오브던전클래식은 머시깽이하고 머시깽이하는 머시깽이입니당.
                    </DescriptionBox>
                    <AmountBox>
                        <AmountPercentage>1280% 달성</AmountPercentage>
                        <Amount>{'|'}1,400,000원</Amount>
                    </AmountBox>
                    <Progress value={20} max={100} />
                </ProjectCard>
            </Link>
            <Link to='/project/1'>
                <ProjectCard>
                    <CardImage src='https://s3-alpha-sig.figma.com/img/9945/33a5/614890d3c056cec00b07ae3b1a6a96ba?Expires=1702252800&Signature=Xrtdb0xoSWhG-mpS2hpQZLSJp~uR7eQXJ7hjKWrkcULpY5VDJmxa6VKnADVu3pfKWTQquM7IzQbS2OAJfIH9P77q6VHLmkIZpwavhlDPcGzX5C3oV08zXtxedwxvmF77xC3HHgzMVuZGFWF~Ixu~W~IQrZkTqalbmqIHvivYV1Hex23L8JVaY-wYFKBMZ-51khlzOzv~kWGioPJd57hElIrkW05aBMq9JxAxR1PFXP5Vc1Mo2DaL2AwG9lDyRHCp2RQ2w8OZE9wlRc4d9uHIivSyLgEt3dh0dBNiIf3DT2wd3p69HWxIv2Q1H5FlMZ7PYYNEx~XnCapmKfsHwWvYBw__&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4'/>
                    <GenreSection>
                        <GenreBox>RPG</GenreBox>
                        <GenreBox>액션</GenreBox>
                    </GenreSection>
                    <ProjectTitleBox>
                        <ProjectTitle>던전의 머시깽이 머시깽이</ProjectTitle>
                    </ProjectTitleBox>
                    <DescriptionBox>
                        카드오브던전클래식은 머시깽이하고 머시깽이하는 머시깽이입니당.
                    </DescriptionBox>
                    <AmountBox>
                        <AmountPercentage>1280% 달성</AmountPercentage>
                        <Amount>{'|'}1,400,000원</Amount>
                    </AmountBox>
                    <Progress value={20} max={100} />
                </ProjectCard>
            </Link>
            <Link to='/project/1'>
                <ProjectCard>
                    <CardImage src='https://s3-alpha-sig.figma.com/img/9945/33a5/614890d3c056cec00b07ae3b1a6a96ba?Expires=1702252800&Signature=Xrtdb0xoSWhG-mpS2hpQZLSJp~uR7eQXJ7hjKWrkcULpY5VDJmxa6VKnADVu3pfKWTQquM7IzQbS2OAJfIH9P77q6VHLmkIZpwavhlDPcGzX5C3oV08zXtxedwxvmF77xC3HHgzMVuZGFWF~Ixu~W~IQrZkTqalbmqIHvivYV1Hex23L8JVaY-wYFKBMZ-51khlzOzv~kWGioPJd57hElIrkW05aBMq9JxAxR1PFXP5Vc1Mo2DaL2AwG9lDyRHCp2RQ2w8OZE9wlRc4d9uHIivSyLgEt3dh0dBNiIf3DT2wd3p69HWxIv2Q1H5FlMZ7PYYNEx~XnCapmKfsHwWvYBw__&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4'/>
                    <GenreSection>
                        <GenreBox>RPG</GenreBox>
                        <GenreBox>액션</GenreBox>
                    </GenreSection>
                    <ProjectTitleBox>
                        <ProjectTitle>던전의 머시깽이 머시깽이</ProjectTitle>
                    </ProjectTitleBox>
                    <DescriptionBox>
                        카드오브던전클래식은 머시깽이하고 머시깽이하는 머시깽이입니당.
                    </DescriptionBox>
                    <AmountBox>
                        <AmountPercentage>1280% 달성</AmountPercentage>
                        <Amount>{'|'}1,400,000원</Amount>
                    </AmountBox>
                    <Progress value={20} max={100} />
                </ProjectCard>
            </Link>
            <Link to='/project/1'>
                <ProjectCard>
                    <CardImage src='https://s3-alpha-sig.figma.com/img/9945/33a5/614890d3c056cec00b07ae3b1a6a96ba?Expires=1702252800&Signature=Xrtdb0xoSWhG-mpS2hpQZLSJp~uR7eQXJ7hjKWrkcULpY5VDJmxa6VKnADVu3pfKWTQquM7IzQbS2OAJfIH9P77q6VHLmkIZpwavhlDPcGzX5C3oV08zXtxedwxvmF77xC3HHgzMVuZGFWF~Ixu~W~IQrZkTqalbmqIHvivYV1Hex23L8JVaY-wYFKBMZ-51khlzOzv~kWGioPJd57hElIrkW05aBMq9JxAxR1PFXP5Vc1Mo2DaL2AwG9lDyRHCp2RQ2w8OZE9wlRc4d9uHIivSyLgEt3dh0dBNiIf3DT2wd3p69HWxIv2Q1H5FlMZ7PYYNEx~XnCapmKfsHwWvYBw__&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4'/>
                    <GenreSection>
                        <GenreBox>RPG</GenreBox>
                        <GenreBox>액션</GenreBox>
                    </GenreSection>
                    <ProjectTitleBox>
                        <ProjectTitle>던전의 머시깽이 머시깽이</ProjectTitle>
                    </ProjectTitleBox>
                    <DescriptionBox>
                        카드오브던전클래식은 머시깽이하고 머시깽이하는 머시깽이입니당.
                    </DescriptionBox>
                    <AmountBox>
                        <AmountPercentage>1280% 달성</AmountPercentage>
                        <Amount>{'|'}1,400,000원</Amount>
                    </AmountBox>
                    <Progress value={20} max={100} />
                </ProjectCard>
            </Link>
            <Link to='/project/1'>
                <ProjectCard>
                    <CardImage src='https://s3-alpha-sig.figma.com/img/9945/33a5/614890d3c056cec00b07ae3b1a6a96ba?Expires=1702252800&Signature=Xrtdb0xoSWhG-mpS2hpQZLSJp~uR7eQXJ7hjKWrkcULpY5VDJmxa6VKnADVu3pfKWTQquM7IzQbS2OAJfIH9P77q6VHLmkIZpwavhlDPcGzX5C3oV08zXtxedwxvmF77xC3HHgzMVuZGFWF~Ixu~W~IQrZkTqalbmqIHvivYV1Hex23L8JVaY-wYFKBMZ-51khlzOzv~kWGioPJd57hElIrkW05aBMq9JxAxR1PFXP5Vc1Mo2DaL2AwG9lDyRHCp2RQ2w8OZE9wlRc4d9uHIivSyLgEt3dh0dBNiIf3DT2wd3p69HWxIv2Q1H5FlMZ7PYYNEx~XnCapmKfsHwWvYBw__&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4'/>
                    <GenreSection>
                        <GenreBox>RPG</GenreBox>
                        <GenreBox>액션</GenreBox>
                    </GenreSection>
                    <ProjectTitleBox>
                        <ProjectTitle>던전의 머시깽이 머시깽이</ProjectTitle>
                    </ProjectTitleBox>
                    <DescriptionBox>
                        카드오브던전클래식은 머시깽이하고 머시깽이하는 머시깽이입니당.
                    </DescriptionBox>
                    <AmountBox>
                        <AmountPercentage>1280% 달성</AmountPercentage>
                        <Amount>{'|'}1,400,000원</Amount>
                    </AmountBox>
                    <Progress value={20} max={100} />
                </ProjectCard>
            </Link>
            <Link to='/project/1'>
                <ProjectCard>
                    <CardImage src='https://s3-alpha-sig.figma.com/img/9945/33a5/614890d3c056cec00b07ae3b1a6a96ba?Expires=1702252800&Signature=Xrtdb0xoSWhG-mpS2hpQZLSJp~uR7eQXJ7hjKWrkcULpY5VDJmxa6VKnADVu3pfKWTQquM7IzQbS2OAJfIH9P77q6VHLmkIZpwavhlDPcGzX5C3oV08zXtxedwxvmF77xC3HHgzMVuZGFWF~Ixu~W~IQrZkTqalbmqIHvivYV1Hex23L8JVaY-wYFKBMZ-51khlzOzv~kWGioPJd57hElIrkW05aBMq9JxAxR1PFXP5Vc1Mo2DaL2AwG9lDyRHCp2RQ2w8OZE9wlRc4d9uHIivSyLgEt3dh0dBNiIf3DT2wd3p69HWxIv2Q1H5FlMZ7PYYNEx~XnCapmKfsHwWvYBw__&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4'/>
                    <GenreSection>
                        <GenreBox>RPG</GenreBox>
                        <GenreBox>액션</GenreBox>
                    </GenreSection>
                    <ProjectTitleBox>
                        <ProjectTitle>던전의 머시깽이 머시깽이</ProjectTitle>
                    </ProjectTitleBox>
                    <DescriptionBox>
                        카드오브던전클래식은 머시깽이하고 머시깽이하는 머시깽이입니당.
                    </DescriptionBox>
                    <AmountBox>
                        <AmountPercentage>1280% 달성</AmountPercentage>
                        <Amount>{'|'}1,400,000원</Amount>
                    </AmountBox>
                    <Progress value={20} max={100} />
                </ProjectCard>
            </Link>
            <Link to='/project/1'>
                <ProjectCard>
                    <CardImage src='https://s3-alpha-sig.figma.com/img/9945/33a5/614890d3c056cec00b07ae3b1a6a96ba?Expires=1702252800&Signature=Xrtdb0xoSWhG-mpS2hpQZLSJp~uR7eQXJ7hjKWrkcULpY5VDJmxa6VKnADVu3pfKWTQquM7IzQbS2OAJfIH9P77q6VHLmkIZpwavhlDPcGzX5C3oV08zXtxedwxvmF77xC3HHgzMVuZGFWF~Ixu~W~IQrZkTqalbmqIHvivYV1Hex23L8JVaY-wYFKBMZ-51khlzOzv~kWGioPJd57hElIrkW05aBMq9JxAxR1PFXP5Vc1Mo2DaL2AwG9lDyRHCp2RQ2w8OZE9wlRc4d9uHIivSyLgEt3dh0dBNiIf3DT2wd3p69HWxIv2Q1H5FlMZ7PYYNEx~XnCapmKfsHwWvYBw__&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4'/>
                    <GenreSection>
                        <GenreBox>RPG</GenreBox>
                        <GenreBox>액션</GenreBox>
                    </GenreSection>
                    <ProjectTitleBox>
                        <ProjectTitle>던전의 머시깽이 머시깽이</ProjectTitle>
                    </ProjectTitleBox>
                    <DescriptionBox>
                        카드오브던전클래식은 머시깽이하고 머시깽이하는 머시깽이입니당.
                    </DescriptionBox>
                    <AmountBox>
                        <AmountPercentage>1280% 달성</AmountPercentage>
                        <Amount>{'|'}1,400,000원</Amount>
                    </AmountBox>
                    <Progress value={20} max={100} />
                </ProjectCard>
            </Link>
            <Link to='/project/1'>
                <ProjectCard>
                    <CardImage src='https://s3-alpha-sig.figma.com/img/9945/33a5/614890d3c056cec00b07ae3b1a6a96ba?Expires=1702252800&Signature=Xrtdb0xoSWhG-mpS2hpQZLSJp~uR7eQXJ7hjKWrkcULpY5VDJmxa6VKnADVu3pfKWTQquM7IzQbS2OAJfIH9P77q6VHLmkIZpwavhlDPcGzX5C3oV08zXtxedwxvmF77xC3HHgzMVuZGFWF~Ixu~W~IQrZkTqalbmqIHvivYV1Hex23L8JVaY-wYFKBMZ-51khlzOzv~kWGioPJd57hElIrkW05aBMq9JxAxR1PFXP5Vc1Mo2DaL2AwG9lDyRHCp2RQ2w8OZE9wlRc4d9uHIivSyLgEt3dh0dBNiIf3DT2wd3p69HWxIv2Q1H5FlMZ7PYYNEx~XnCapmKfsHwWvYBw__&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4'/>
                    <GenreSection>
                        <GenreBox>RPG</GenreBox>
                        <GenreBox>액션</GenreBox>
                    </GenreSection>
                    <ProjectTitleBox>
                        <ProjectTitle>던전의 머시깽이 머시깽이</ProjectTitle>
                    </ProjectTitleBox>
                    <DescriptionBox>
                        카드오브던전클래식은 머시깽이하고 머시깽이하는 머시깽이입니당.
                    </DescriptionBox>
                    <AmountBox>
                        <AmountPercentage>1280% 달성</AmountPercentage>
                        <Amount>{'|'}1,400,000원</Amount>
                    </AmountBox>
                    <Progress value={20} max={100} />
                </ProjectCard>
            </Link>
            <Link to='/project/1'>
                <ProjectCard>
                    <CardImage src='https://s3-alpha-sig.figma.com/img/9945/33a5/614890d3c056cec00b07ae3b1a6a96ba?Expires=1702252800&Signature=Xrtdb0xoSWhG-mpS2hpQZLSJp~uR7eQXJ7hjKWrkcULpY5VDJmxa6VKnADVu3pfKWTQquM7IzQbS2OAJfIH9P77q6VHLmkIZpwavhlDPcGzX5C3oV08zXtxedwxvmF77xC3HHgzMVuZGFWF~Ixu~W~IQrZkTqalbmqIHvivYV1Hex23L8JVaY-wYFKBMZ-51khlzOzv~kWGioPJd57hElIrkW05aBMq9JxAxR1PFXP5Vc1Mo2DaL2AwG9lDyRHCp2RQ2w8OZE9wlRc4d9uHIivSyLgEt3dh0dBNiIf3DT2wd3p69HWxIv2Q1H5FlMZ7PYYNEx~XnCapmKfsHwWvYBw__&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4'/>
                    <GenreSection>
                        <GenreBox>RPG</GenreBox>
                        <GenreBox>액션</GenreBox>
                    </GenreSection>
                    <ProjectTitleBox>
                        <ProjectTitle>던전의 머시깽이 머시깽이</ProjectTitle>
                    </ProjectTitleBox>
                    <DescriptionBox>
                        카드오브던전클래식은 머시깽이하고 머시깽이하는 머시깽이입니당.
                    </DescriptionBox>
                    <AmountBox>
                        <AmountPercentage>1280% 달성</AmountPercentage>
                        <Amount>{'|'}1,400,000원</Amount>
                    </AmountBox>
                    <Progress value={20} max={100} />
                </ProjectCard>
            </Link>
            <Link to='/project/1'>
                <ProjectCard>
                    <CardImage src='https://s3-alpha-sig.figma.com/img/9945/33a5/614890d3c056cec00b07ae3b1a6a96ba?Expires=1702252800&Signature=Xrtdb0xoSWhG-mpS2hpQZLSJp~uR7eQXJ7hjKWrkcULpY5VDJmxa6VKnADVu3pfKWTQquM7IzQbS2OAJfIH9P77q6VHLmkIZpwavhlDPcGzX5C3oV08zXtxedwxvmF77xC3HHgzMVuZGFWF~Ixu~W~IQrZkTqalbmqIHvivYV1Hex23L8JVaY-wYFKBMZ-51khlzOzv~kWGioPJd57hElIrkW05aBMq9JxAxR1PFXP5Vc1Mo2DaL2AwG9lDyRHCp2RQ2w8OZE9wlRc4d9uHIivSyLgEt3dh0dBNiIf3DT2wd3p69HWxIv2Q1H5FlMZ7PYYNEx~XnCapmKfsHwWvYBw__&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4'/>
                    <GenreSection>
                        <GenreBox>RPG</GenreBox>
                        <GenreBox>액션</GenreBox>
                    </GenreSection>
                    <ProjectTitleBox>
                        <ProjectTitle>던전의 머시깽이 머시깽이</ProjectTitle>
                    </ProjectTitleBox>
                    <DescriptionBox>
                        카드오브던전클래식은 머시깽이하고 머시깽이하는 머시깽이입니당.
                    </DescriptionBox>
                    <AmountBox>
                        <AmountPercentage>1280% 달성</AmountPercentage>
                        <Amount>{'|'}1,400,000원</Amount>
                    </AmountBox>
                    <Progress value={20} max={100} />
                </ProjectCard>
            </Link>

        </ProjectSection>
    </Container>

  )
}

const Container = styled.div`
    text-align: left;
    display: flex;
    flex-direction: column;
    padding: 10px 200px;
`

const TitleBox = styled.div`
    display: flex;
    align-items: center;
    height: 80px;
`

const Title = styled.span`
    margin-left: 20px;
    font-weight: bold;
    font-size: 24px;
`

const ProjectSection = styled.div`
    display: flex;
    flex-direction: row;
    margin: 0 0 0 20px;
    flex-wrap: wrap;
    gap: 40px;
`

const ProjectCard = styled.div`
    display: flex;
    flex-direction: column;
    width: 300px;
    height: 450px;
`

const CardImage = styled.img`
    width: 300px;
    height: 230px;
`

const GenreSection = styled.div`
    display: flex;
`

const GenreBox = styled.div`
    margin: 10px 10px 8px 0;
    padding: 0 6px 0 6px;
    background: #ebe9ea;
    color: rgba(0, 0, 0, 0.32);
`

const ProjectTitleBox = styled.div`
    height: 29px;
`
const ProjectTitle = styled.span`
    font-size: 18px;
    font-weight: 900;
`

const DescriptionBox = styled.div`
    margin-top: 5px;
    height: 37px;
    color: rgba(51, 51, 51, 0.60);
    font-size: 12px;
    font-weight: 400;
`

const AmountBox = styled.div`
    margin-top: 5px;
`

const AmountPercentage = styled.span`
    height: 26px;
    color: #685bfe;
    font-size: 16px;
    font-weight: 700;
`
const Amount = styled.span`
    height: 26px;
    color: rgba(51, 51, 51, 0.60);
    font-size: 16px;
    font-weight: 700;
`

const Progress = styled.progress`
    margin-top: 10px;
    width: 100%;

    background-color: white;
    color: #685bfe;

    &::-webkit-progress-value {
        background-color: #685bfe;
    }

    &::-webkit-progress-bar {
        background-color: lightgrey;
    }

    &::-moz-progress-bar {
        background-color: #685bfe;
    }
`

const Link = styled(RouterLink)`
    text-decoration: none;
    color: inherit;
`