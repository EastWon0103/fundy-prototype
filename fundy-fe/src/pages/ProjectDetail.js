import React from 'react'
import { useParams } from 'react-router'
import useProject from '../hooks/useProject';
import styled from 'styled-components';
import ProjectDescription from '../components/ProjectDescription';

export default function ProjectDetail() {
    const { id } = useParams();
    // const { project, isLoading } = useProject(id); 
    const project = {
      "id": 1,
      "title": "프로젝트 1",
      'content': 'https://s3-alpha-sig.figma.com/img/9945/33a5/614890d3c056cec00b07ae3b1a6a96ba?Expires=1702252800&Signature=Xrtdb0xoSWhG-mpS2hpQZLSJp~uR7eQXJ7hjKWrkcULpY5VDJmxa6VKnADVu3pfKWTQquM7IzQbS2OAJfIH9P77q6VHLmkIZpwavhlDPcGzX5C3oV08zXtxedwxvmF77xC3HHgzMVuZGFWF~Ixu~W~IQrZkTqalbmqIHvivYV1Hex23L8JVaY-wYFKBMZ-51khlzOzv~kWGioPJd57hElIrkW05aBMq9JxAxR1PFXP5Vc1Mo2DaL2AwG9lDyRHCp2RQ2w8OZE9wlRc4d9uHIivSyLgEt3dh0dBNiIf3DT2wd3p69HWxIv2Q1H5FlMZ7PYYNEx~XnCapmKfsHwWvYBw__&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4',
      "description": "카드 오브 던전 클래식은 당신만의 카드르 모아 다양한 던전에 대항하여 주인공에 얽혀있는 수수께끼를 푸는 전략 캐주얼 판타지 게임입니다. 그리고 모시깽이를 하기위 모시깽이 하고자 하고 이를 위해 모시깽이를 할 것입니다. 그리고 모시깽이들을 위하여 모시깽이도 할 예정입니다. 많관부 가나다라마바사아자차차",
      "startDateTime": "2023-10-11T09:00:00",
      "endDateTime": "2024-01-01T09:00:00",
      "devNoteUploadCycle": 1,
      "devNoteUploadDay": "금요일",
      "genres": [
        "액션",
        "퍼즐"
      ]
    };


    return (

        <ProjectDescription project={project} />

)};


