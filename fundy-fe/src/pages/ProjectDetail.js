import React, { useEffect, useState } from 'react'
import { useParams } from 'react-router'
import styled from 'styled-components';
import { getProjectsById } from '../apis/API';
import { ProjectDescription, GoToDevNote, ProjectBottom } from '../components/ProjectDetail';

export default function ProjectDetail() {
    const { id } = useParams();
    const [project, setProject] = useState(null);

    const fetchProject = async () => {
      try {
        const response = await getProjectsById(id);
        setProject(response.result);
        
      } catch (error) {
        console.log('프로젝트 가져오기 실패', error);
      }
    }

    useEffect(() => {
      fetchProject();

    }, [id]);

    if (project === null) {
      return(
        <div>로딩중...</div>
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