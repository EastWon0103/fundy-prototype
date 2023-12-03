import { create } from 'zustand';
import { persist } from 'zustand/middleware';
import { signUp, checkNickname, login, getEmailAuthCode, verifyEmailAuthCode, getUser, funding } from '../apis/API';

const useStore = create(persist((set, get) => ({
    email: '',
    password: '',
    nickname: '',
    isValidNickname: undefined,
    isLoggedIn: false,
    isVerifyEmail: false,
    code: '',
    token: null,
    user: {},
    project: null,
    rewards: null,

    setEmail: (email) => set(() => ({ email })),
    setPassword: (password) => set(() => ({ password })),
    setNickname: (nickname) => set(() => ({ nickname })),
    setCode: (code) => set(() => ({ code })),
    setIsValidNickname: (isValid) => set(() => ({ isValidNickname: isValid })),
    setProject: (projectData) => set({ project: projectData }),
    setRewards: (rewardsData) => set({ rewards: rewardsData }),

    checkValidNickname: async () => {
        const { nickname } = useStore.getState();
        try {
            const response = await checkNickname(nickname);
            const isDuplicate = response.result.duplicate;
            const isValid = !isDuplicate;
            set({ isValidNickname: isValid });
            return isValid;
        } catch (error) {
            console.log('닉네임 중복검사 중 오류 발생', error);
            set({ isValidNickname: false });
            return false;
        }
    },

    performEmailAuthCode: async () => {
        const { email } = useStore.getState();
        try {
            const response = await getEmailAuthCode(email);
            console.log('인증코드 발송 성공', response);
            set({ token: response.result.token })
            return true;
        } catch (error) {
            console.log('인증코드 발송 실패', error.response ? error.response.data : error);
            return false;
        }
    },

    verifyEmailAuthCode: async () => {
        const { email, token, code } = useStore.getState();
        try {
            const response = await verifyEmailAuthCode(email, token, code);
            const isSuccess = response.result.verify;
            set({ isVerifyEmail: isSuccess });
            return isSuccess;
        } catch (error) {
            console.log('인증실패', error);
            throw error;
        }
    },

    performSignUp: async () => {
        const { email, password, nickname, setIsValidNickname } = useStore.getState();
        try {
            const response = await signUp(email, password, nickname);
            console.log('회원가입 성공', response);
            setIsValidNickname(undefined);
            return true;
        } catch (error) {
            console.log('회원가입 실패', error.response ? error.response.data : error);
            setIsValidNickname(undefined);
            return false;
        }
    },

    performLogin: async () => {
        const { email, password } = useStore.getState();
        try {
            const response = await login(email, password);
            console.log('로그인 성공', response);
            set({ isLoggedIn: true, token: response.result.token });
            await useStore.getState().getUserInfo();
            return true;
        } catch (error) {
            console.log('로그인 실패', error.response ? error.response.data : error);
            return false;
        }
    },

    getUserInfo: async () => {
        const { token } = useStore.getState();
        try {
            const response = await getUser(token);
            console.log('유저정보 불러오기 성공', response);
            set({ user: response.result });
        } catch (error) {
            console.log('유저정보 불러오기 실패', error);
        }
    },

    performFunding: async (rewardId, amount) => {
        const { user, token } = useStore.getState();
        if (user.accounts[0].balance >= amount) {
            try {
                await funding(rewardId, user.id, amount, token);
                console.log('후원 성공')
                return true;
                
            } catch (error) {
                console.log('후원 실패', error);
                throw error;
            } 
        } else {
            console.log('잔고 부족');
            throw new Error('Insufficient balance');
            
        }
    }

}), {
    name: 'user-store', // 로컬 스토리지에 저장될 키 이름
    getStorage: () => localStorage, // 사용할 스토리지 엔진
}));

export default useStore;
