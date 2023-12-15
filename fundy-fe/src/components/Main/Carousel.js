import React, { useState } from 'react';
import styled from 'styled-components';
import truncate from '../../utils/truncate';

export default function Carousel({ images, bannerContents }) {
    const [currentIndex, setCurrentIndex] = useState(0);

    const goToPrevious = () => {
        const isFirstImage = currentIndex === 0;
        const newIndex = isFirstImage ? images.length - 1 : currentIndex - 1;
        setCurrentIndex(newIndex);
    };

    const goToNext = () => {
        const isLastImage = currentIndex === images.length - 1;
        const newIndex = isLastImage ? 0 : currentIndex + 1;
        setCurrentIndex(newIndex);
    };

    return (
        <CarouselContainer>
            <PreviousButton onClick={goToPrevious}>{"<"}</PreviousButton>
            <CarouselImage src={images[currentIndex].url} alt={`Image ${currentIndex}`} />
            <BannerContent>
                <BannerTitle>{bannerContents[currentIndex].title}</BannerTitle>
                <BannerDescription>{truncate(bannerContents[currentIndex].description, 200)}</BannerDescription>
            </BannerContent>
            <NextButton onClick={goToNext}>{">"}</NextButton>
        </CarouselContainer>
    );
}

const CarouselContainer = styled.div`
    width: 100%;
    height: 486px;
    position: relative;
`;

const CarouselImage = styled.img`
    width: 100%;
    height: 100%;
    object-fit: cover;
`;

const Button = styled.button`
    font-size: 2rem;
    position: absolute;
    top: 50%;
    transform: translateY(-50%);
    background-color: rgba(0, 0, 0, 0.5);
    color: white;
    border: none;
    cursor: pointer;
    padding: 10px;
`;

const PreviousButton = styled(Button)`
    left: 10px;
`;

const NextButton = styled(Button)`
    right: 10px;
`;


const BannerContent = styled.div`
    position: absolute;
    top: 20%;  
    left: 15%;  
    color: white;  
    text-align: left;  
`;

const BannerTitle = styled.div`
    font-size: 3rem;
    font-weight: 800;
`;

const BannerDescription = styled.div`
    font-size: 1rem;
    font-weight: 500;
    max-width: 400px;
    margin-top: 10px;
`;