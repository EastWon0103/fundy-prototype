import React from 'react'
import { useParams } from 'react-router'
import useProject from '../hooks/useProject';
import styled from 'styled-components';
import ProjectDescription from '../components/ProjectDetail/ProjectDescription';
import GoToDevNote from '../components/ProjectDetail/GoToDevNote';
import ProjectBottom from '../components/ProjectDetail/ProjectBottom';

export default function ProjectDetail() {
    const { id } = useParams();
    const { project, isLoading } = useProject(id); 

    if (isLoading) {
      return(
        <div>로딩중..</div>
      )
    } else {

      return (
        <Body>
  
          <ProjectDescription project={project} />
          <GoToDevNote projectId={id}/>
          <ProjectBottom project={project}/>
  
        </Body>
  )

    }

};

const Body = styled.body`
  background-color: #ebe9ea;
`