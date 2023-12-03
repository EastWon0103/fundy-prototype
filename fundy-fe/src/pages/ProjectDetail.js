import React from 'react'
import { useParams } from 'react-router'
import useProject from '../hooks/useProject';
import styled from 'styled-components';
import ProjectDescription from '../components/projectDetail/ProjectDescription';
import GoToDevNote from '../components/projectDetail/GoToDevNote';
import ProjectBottom from '../components/projectDetail/ProjectBottom';
import useRewards from '../hooks/useRewards';

export default function ProjectDetail() {
    const { id } = useParams();
    const { project, isLoading } = useProject(id); 
    const { rewards, isRewardsLoading } = useRewards(id);


    if (isLoading || isRewardsLoading) {
      return(
        <div>로딩중..</div>
      )
    } else {

      return (
        <Body>
  
          <ProjectDescription project={project} />
          <GoToDevNote />
          <ProjectBottom rewards={rewards} project={project}/>
  
        </Body>
  )

    }

};

const Body = styled.body`

  background-color: #ebe9ea;
`