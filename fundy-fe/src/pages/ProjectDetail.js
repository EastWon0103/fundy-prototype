import React from 'react'
import { useParams } from 'react-router'
import useProject from '../hooks/useProject';
import styled from 'styled-components';

export default function ProjectDetail() {
    const { id } = useParams();
    const { project, isLoading } = useProject(id); 


    return (
        <div>
          {isLoading ? (
            <p>로딩 중...</p>
          ) : project ? (
            <div>
                {JSON.stringify(project)}
            </div>
          ) : (
            <p>프로젝트 정보가 없습니다.</p>
          )}
        </div>
      );
};

const Container = styled.div`
    margin-top: 150px;
`
