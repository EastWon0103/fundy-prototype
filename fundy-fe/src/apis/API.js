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
        const response = await apiClient.post('/user/sign-up', {
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
        const response = await apiClient.get('/user/check-nickname', {
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
        const response = await apiClient.post('/user/sign-in',{
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