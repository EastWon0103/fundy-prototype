import React from 'react'
import BannerImage from '../../images/banner.png'
import Carousel from './Carousel'
import styled from 'styled-components'

export default function MainBanner() {
  // dummy data
  const images = [
    {id: 1, url:BannerImage},
    {id: 2, url:BannerImage},
    {id: 3, url:BannerImage},
]

// dummy data
const bannerContents = [
  {title: 'Fundy', description: `Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum`},
  {title: '김동원', description: `Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum`},
  {title: '배준형과김동원', description: `Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum`},
]

return (
    <Container>
        <Carousel images={images} bannerContents={bannerContents}>
        </Carousel>
    </Container>
    )
}

const Container = styled.div`
    width: 100%;
    height: 486px;

    background-color: black;
`