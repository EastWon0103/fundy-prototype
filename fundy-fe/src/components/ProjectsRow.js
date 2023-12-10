import { Link as RouterLink } from 'react-router-dom'
import React, { useEffect, useState } from 'react'
import styled from 'styled-components'
import { getProjects } from '../apis/API';
import InfiniteScroll from 'react-infinite-scroll-component';
import formatCurrency from '../utils/formatCurrency';

export default function ProjectsRow() {
    const [projects, setProjects] = useState([]);
    const [page, setPage] = useState(0);
    const [hasMore, setHasMore] = useState(true);


    // 목업데이터
    // const createMockProjects = (pageNum, pageSize) => {
    //     return Array.from({ length: pageSize }, (_, index) => ({
    //         id: (pageNum - 1) * pageSize + index + 1,
    //         thumbnail: "https://fundy-bucket.s3.ap-northeast-2.amazonaws.com/default/profileImage.png",
    //         title: `프로젝트 ${pageNum}-${index + 1}`,
    //         totalFundingAmount: 10000 * (index + 1),
    //         targetAmount: 500000,
    //         percentage: ((10000 * (index + 1)) / 500000) * 100,
    //     }));
    // };


    const loadProjects = async () => {
        try {
            const pageSize = 8;
            const response = await getProjects(page, pageSize);
            setProjects(prevProjects => [...prevProjects, ...response.result.projectSummarys])
            setHasMore(response.result.hasNext);
            setPage(prevPage => prevPage + 1);
            // 목업데이터
            // const mockData = createMockProjects(page, pageSize);
            // setProjects(prevProjects => [...prevProjects, ...mockData]);
            // setPage(prevPage => prevPage + 1);
        } catch (error) {
            console.log('프로젝트 로드 실패', error);
            
        }
    }

    useEffect(() => {
        loadProjects();
    }, [])

  return (
    <Container>
        <TitleBox>
            <Title>🔥 신규 프로젝트</Title>
        </TitleBox>
        <InfiniteScroll
            dataLength={projects.length}
            next={loadProjects}
            hasMore={hasMore}
            loader={<h4 style={{ textAlign: 'center' }}>Loading...</h4>}
            endMessage={
                <p style={{ textAlign: 'center'}}>
                    <b>모든 프로젝트를 불러왔습니다.</b>
                </p>
            }>
            <ProjectSection>
                {projects.map(project => (
                    <Link key={project.id} to={`/project/${project.id}`}>
                        <ProjectCard>
                            <CardImage src={project.thumbnail}/>
                            <GenreSection>
                                <GenreBox>RPG</GenreBox>
                                <GenreBox>액션</GenreBox>
                            </GenreSection>
                            <ProjectTitleBox>
                                <ProjectTitle>{project.title}</ProjectTitle>
                            </ProjectTitleBox>
                            <DescriptionBox>
                                카드오브던전클래식은 머시깽이하고 머시깽이하는 머시깽이입니당.
                            </DescriptionBox>
                            <AmountBox>
                                <AmountPercentage>{project.percentage.toFixed(2)}{'%'} 달성</AmountPercentage>
                                <Amount>{'|'}{formatCurrency(project.totalFundingAmount)}원</Amount>
                            </AmountBox>
                            <Progress value={project.percentage.toFixed(2)} max={100} />
                        </ProjectCard>
                    </Link>

                ))}

            </ProjectSection>
        </InfiniteScroll>
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