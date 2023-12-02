import React, { useState } from 'react'
import {Document, Page, pdfjs} from 'react-pdf'
import 'react-pdf/dist/esm/Page/AnnotationLayer.css';
import styled from 'styled-components';


pdfjs.GlobalWorkerOptions.workerSrc = `//cdnjs.cloudflare.com/ajax/libs/pdf.js/${pdfjs.version}/pdf.worker.js`;


export default function PdfContent({pdf}) {
    const [numPages, setNumPages] = useState(null);

    function onDocumentLoadSuccess({ numPages }) {
      setNumPages(numPages);
    }
  
    return (
        <PdfContainer>
          <Document
            file={pdf}
            onLoadSuccess={onDocumentLoadSuccess}
          >
            {Array.from(new Array(numPages), (el, index) => (
              <StyledPage key={`page_${index + 1}`} pageNumber={index + 1} />
            ))}
          </Document>
        </PdfContainer>
      );

    }

    const PdfContainer = styled.div`
    text-align: center;
    background-color: #f5f5f5;
    padding: 20px;
  `;
  
  const StyledPage = styled(Page)`
    box-shadow: 0 2px 5px rgba(0, 0, 0, 0.3);
    margin-bottom: 20px;
  `;