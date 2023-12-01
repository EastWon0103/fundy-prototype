import React from 'react'
import styled from 'styled-components'
import useStore from '../store/store'

export default function Profile() {
    const { user } = useStore()
  return (
    <Container>

    </Container>
  )
}


const Container = styled.div`
    margin: 100px;
    padding: 14px 0 14px 0;
    border-radius: 12px;
    box-shadow: 2px 2px 14px 0 rgba(0,164,73,.08);
    border: 1px solid #d09aff;
    background-color: #fff;
    box-sizing: border-box;
    text-align: left;
`;

const MyProfile = styled.div`
    display: block;
`

const InfoTitle = styled.div`
    position: relative;
`

const TitleText = styled.h3`
    display: inline-block;
    font-size: 14px;
    font-weight: 700;
    line-height: 17px;
    letter-spacing: -.4px;
    color: #96a1aa;
`

const MyInfoArea = styled.ul`
    position: relative;
    display: table;
    table-layout: fixed;
    width: 100%;
    padding: 12px 0;
    list-style: none;
    li: nth-child(1) {
        width: 68px;
        text-align: left;
    }
    li: {
        display: table-cell;
        vertical-align: middle;
    }
`

const ImageList = styled.li`
    width: 68px;
    text-align: left;
`

const List = styled.li`
    display: table-cell;
    vertical-align: middle;
`
const MyPhoto = styled.div`
    display: inline-block;
`

const MyAccount = styled.div`
    display: block;
`

const MyName = styled.div`
    position: relative;
    padding-right: 70px;
`

const NameText = styled.div`
    font-size: 24px;
    font-weight: 700;
    line-height: 29px;
    letter-spacing: -.63px;
    word-break: break-all;
`