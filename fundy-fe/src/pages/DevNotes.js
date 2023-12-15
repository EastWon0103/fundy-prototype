import React, { useEffect, useState } from 'react'
import { useParams } from 'react-router'
import { getDevNotes } from '../apis/API';
import { DevNoteCarrousel } from '../components/DevNotes';

export default function DevNotes() {
    let { id } = useParams();
    const [devNotes, setDevNotes] = useState([]);

    useEffect(() => {
        async function fetchDevNotes() {
            try {
                const data = await getDevNotes(id)
                setDevNotes(data.result);

                
            } catch (error) {
                console.log('개발노트 요청 페이지 실패', error)
                
            }
        }

        fetchDevNotes();
    }, [id]);

  return (
    <DevNoteCarrousel devNotes={devNotes} projectId={id} />
  )
}
