import React, { useEffect, useState } from 'react'
import { getDevNoteComments, postDevNoteComment } from '../../apis/API';
import styled from 'styled-components';
import formatDate from '../../utils/formatDate';
import useStore from '../../store/store';

export default function DevNoteComment({devNoteId}) {
    const { user, token, isLoggedIn } = useStore();
    const [comments, setComments] = useState([]);
    const [changeComment, setChangeComment] = useState('');
    const [postingComment, setPostingComment] = useState(false); 


    const handleChangeComment = (e) => {
        setChangeComment(e.target.value)
    }
    
    const handleKeyDown = async (e) => {
        if (e.key === 'Enter' && changeComment.trim() !== '') {
            if (postingComment) return; 
            setPostingComment(true); 
    
            try {
                const isCommentPosted = await postDevNoteComment(token, changeComment, devNoteId);
                if (isCommentPosted) {
                    setChangeComment(''); 
                    const data = await getDevNoteComments(devNoteId); 
                    setComments(data.result); 
                } 
            } catch (error) {
                console.log('댓글 달기 실패', error);
            } finally {
                setPostingComment(false); 
            }
        }
    }

    useEffect(() => {
        async function fetchComments() {
            try {
                const data = await getDevNoteComments(devNoteId);
                setComments(data.result);
            } catch (error) {
                console.log('코멘트 못불러와떠', error);
                
            }
        }
        fetchComments();
    }, [devNoteId])

  return (
    <>
        {comments.map(comment => (
            <CommentContainer>
                <ProfileImage src={comment.writer.profile} />
                <CommentContent>
                    <CommentInfo>
                        <CommentAuthor>{comment.writer.nickname}</CommentAuthor>
                        <CommentTimestamp>{formatDate(comment.createdAt)}</CommentTimestamp>
                    </CommentInfo>
                    <CommentText>{comment.content}</CommentText>

                </CommentContent>
            </CommentContainer>
        ))}
        {isLoggedIn ? 
            <CommentContainer>
                <ProfileImage src={user.profile} />
                <InputArea
                    placeholder='댓글을 작성해주시와요'
                    value={changeComment}
                    onChange={handleChangeComment}
                    onKeyDown={handleKeyDown} />
            </CommentContainer>
        :
            null
        }
    </>
  )
}

const CommentContainer = styled.div`
  display: flex;
  align-items: center;
  background-color: #333; 
  color: white;
  padding: 10px 15px;
  margin: 10px 250px;
  border-radius: 4px;
  font-size: 0.9rem;
`;

const ProfileImage = styled.img`
  width: 45px;
  height: 45px;
  border-radius: 50%;
  margin-right: 10px;
`;

const CommentContent = styled.div`
  flex-grow: 1;
  align-items: center;
`;

const CommentText = styled.p`
  margin: 0;
  text-align: left;
`;

const CommentInfo = styled.div`
  display: flex;
  align-items: center;
  
`;

const CommentAuthor = styled.span`
    margin-right: 5px;
  font-weight: bold;
`;

const CommentTimestamp = styled.span`
  color: #aaa;
`;

const InputArea = styled.input`
    border-radius: 6px;
    border: 1px solid #a0a1a3;
    height: 34px;
    width: 668px;

`