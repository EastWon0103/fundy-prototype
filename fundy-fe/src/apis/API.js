import axios from 'axios'

const BASE_URL = 'http://fundy-game.com/api'

const apiClient = axios.create({
    baseURL: BASE_URL
});

/**
* 회원가입을 위한 요청 함수
* @param {string} email 사용자의 이메일 주소
* @param {string} password 사용자의 비밀번호
* @param {string} nickname 사용자의 닉네임
*/

export const signUp = async (email, password, nickname) => {
    try {
        const response = await apiClient.post('/users/sign-up', {
            email,
            password,
            nickname
        });

        console.log('회원가입 요청 성공', response.data);
        return response.data;
        
    } catch (error) {
        console.log('회원가입 요청 실패', error.response ? error.response.data : error)
        throw error;
        
    }
}

/**
* 닉네임 중복체크 함수
* @param {string} nickname 사용자의 닉네임
*/

export const checkNickname = async (nickname) => {
    try {
        const response = await apiClient.get('/users/check-nickname', {
            params: { nickname }
        });
        console.log('체크 성공', response.data)
    
        return response.data
        
    } catch(error) {
        console.log('체크 실패', error.response ? error.response.data : error)
        throw error;
        
    }
}

/**
 * 로그인 요청 함수
 * @param {string} email 
 * @param {string} password 
 * @returns 
 */

export const login = async (email, password) => {
    try {
        const response = await apiClient.post('/users/sign-in',{
            email,
            password
        });
        console.log('로그인 요청 성공', response.data);
        return response.data;
    } catch(error) {
        console.log('로그인 요청 실패', error.response ? error.response.data : error)
        throw error;
    }
}

/**
 * 이메일 인증 코드 발송 함수
 * @param {string} email 
 */

export const getEmailAuthCode = async (email) => {
    try {
        const response = await apiClient.get('/email/auth-code', {
            params: { email }
        });
        console.log('인증코드 발송 요청 성공', response.data);
        return response.data;
        
    } catch(error) {
        console.log('인증코드 발송 요청 실패', error.response ? error.response.data : error);
        throw error;
        
    }
}

/**
 * 이메일 인증 함수
 * @param {string} email 
 * @param {string} token 
 * @param {string} code 
 */

export const verifyEmailAuthCode = async (email, token, code) => {
    const requestBody = { email, token, code };
    try {
        const response = await apiClient.post('/email/verify', requestBody);
        console.log('이메일 인증 요청 성공', response.data);
        return response.data
        
    } catch(error) {
        console.log('이메일 인증 요청 실패', error.response ? error.response.data : error)
        throw error;
        
    }
}

/**
 * 유저 정보 가져오기 함수
 * @param {string} token 
 * @returns 
 */

export const getUser = async (token) => {
    try {
        const response = await apiClient.get('users/info', {
            headers: {
            'Authorization': `Bearer ${token}`
            }
        });
        console.log('유저 정보 요청 성공', response.data);
        return response.data;
        
    } catch (error) {
        console.log('유저 정보 요청 실패', error.response ? error.response.data : error);
        throw error;
        
    }
}

/**
 * 프로젝트 무한스크롤할 때 가져올 프로젝트들
 * @param {int} pageNum 
 * @param {int} pageSize 
 */

export const getProjects = async (pageNum, pageSize) => {
    try {
        const response = await apiClient.get('/project', {
            params: {
                pageNum,
                pageSize
            }
        })
        console.log('스크롤 목록 요청 성공', response.data);
        return response.data;
        
    } catch (error) {
        console.log('스크롤 목록 요청 실패', error);
        throw error;
        
    }


}

/**
 * 프로젝트 가져오기
 * @param {int} id 
 */

export const getProjectsById = async (id) => {
    try {
        const response = await apiClient.get(`/project/${id}`);
        console.log('프로젝트 요청 성공', response.data)
        
        return response.data
    } catch (error) {
        console.log('프로젝트 요청 실패', error);
        throw error;
    }

}

/**
 * 리워드 가져오기
 * @param {int} id 
 * @returns 
 */

export const getRewards = async (id) => {
    try {
        const response = await apiClient.get(`/project/${id}/rewards`);
        console.log('리워드 요청 성공', response.data);
        return response.data
        
    } catch (error) {
        console.log('리워드 요청 실패', error);
        throw error;

    }
}

/**
 * 후원하기
 * @param {int} rewardId 
 * @param {int} accountId 
 * @param {int} amount 
 * @param {string} token
 */

export const funding = async (rewardId, accountId, amount, token) => {
    const requestBody = {rewardId, accountId, amount}
    const config = {
        headers: {
            Authorization: `Bearer ${token}`,
        },
    }
    try {
        const response = await apiClient.post('/fundings', requestBody, config);
        console.log('펀딩 요청 성공', response.data);
        
    } catch (error) {
        console.log('펀딩요청실패', error)
        throw error;
        
    }

}

/**
 * 펀딩 목록 가져오기
 * @param {string} token 
 * @returns 
 */

export const getFundings = async (token) => {
    try {
        const response = await apiClient.get('/fundings', {
            headers: {
                'Authorization': `Bearer ${token}`
            }
        })
        console.log('펀딩 정보 요청 성공', response.data);
        return response.data;
        
    } catch (error) {
        console.log('펀딩 정보 요청 실패', error);
        throw error;
    }

}

/**
 * 환불하기 
 * @param {string} token 
 * @param {int} transactionId
 * @returns 
 */

export const refunding = async (token, transactionId) => {

    const config = {
        headers: {
            Authorization: `Bearer ${token}`
        },
        params: {
            id: transactionId
        }
    }

    try {
        const response = await apiClient.patch('/refunding', null, config)
        console.log('환불요청완료', response.data)
        return true;
         
    } catch (error) {
        console.log('환불요청실패', error)
        throw error
        
    }
}

/**
 * 개발 노트 목록 불러오기
 * @param {int} id 
 * @returns 
 */

export const getDevNotes = async (id) => {
    try {
        const response = await apiClient.get(`/project/${id}/devnotes`);
        console.log('개발노트 목록 요청 성공', response.data);
        return response.data;
        
    } catch(error) {
        console.log('개발노트 목록 요청 실패', error)
        throw error
        
    }

}

/**
 * 개발 노트 불러오기
 * @param {int} projectId 
 * @param {int} devNoteId 
 * @returns 
 */

export const getDevNote = async (projectId, devNoteId) => {

    try {
        const response = await apiClient.get(`devnotes/${devNoteId}`);
        console.log('개발노트 요청 성공', response.data);
        return response.data;
        
    } catch (error) {
        console.log('개발노트 요청 실패', error)
        throw error
        
    }
}

/**
 * 개발노트 댓글 가져오기
 * @param {int} devNoteId 
 * @returns 
 */

export const getDevNoteComments = async (devNoteId) => {

    try {
        const response = await apiClient.get(`/devnotes/${devNoteId}/comments`);
        console.log('댓글 불러오기 성공', response.data);
        return response.data;
        
    } catch (error) {
        console.log('댓글 요청 실패', error);
        throw error;
    }
}

/**
 * 개발노트 댓글 달기
 * @param {string} token 
 * @param {string} content 
 * @param {int} devNoteId 
 * @returns 
 */

export const postDevNoteComment = async (token, content, devNoteId) => {
    const requestBody = { content }
    const config = { 
        headers: {
            Authorization: `Bearer ${token}`
        }
    }

    try {
        const response = await apiClient.post(`/devnotes/${devNoteId}/comments`, requestBody, config);
        console.log('코멘트 달기 성공', response.body);
        return true;
        
    } catch (error) {
        console.log('코멘트 실패', error)
        throw error;
        
    }
}