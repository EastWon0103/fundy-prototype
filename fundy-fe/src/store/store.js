import { create } from 'zustand';
import { signUp, checkNickname, login, getEmailAuthCode, verifyEmailAuthCode, getUser, getProjectsById } from '../apis/API';

const useStore = create(set => ({
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

    setEmail: email => set(() => ({ email })),
    setPassword: password => set(() => ({ password })),
    setNickname: nickname => set(() => ({ nickname })),
    setCode: code => set(() => ({ code })),
    setIsValidNickname: isValid => set(() => ({isValidNickname: isValid})),
    setProject: projectData => set({ project: projectData}),
    setRewards: rewardsData => set({ rewards: rewardsData}),


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

    performEmailAuthCode: async() => {
        const{ email } = useStore.getState();
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

    verifyEmailAuthCode: async() => {
        const { email, token, code } = useStore.getState();
        try {
            const response = await verifyEmailAuthCode(email, token, code);
            const isSuccess = response.result.verify
            set({ isVerifyEmail: isSuccess });
            return isSuccess;

        } catch (error) {
            console.log('인증실패', error);
            throw error;
        }
    },

    performSignUp: async() => {
        const {email, password, nickname, setIsValidNickname} = useStore.getState();
        try {
            const response = await signUp(email, password, nickname);

            console.log('회원가입 성공', response);
            setIsValidNickname(undefined);

            // set({ isLoggedIn: true, user: { nickname }});

            return true;

            
        } catch (error) {
            console.log('회원가입 실패', error.response ? error.response.data : error);
            setIsValidNickname(undefined);

            return false;
            
        }
    },

    performLogin: async() => {
        const {email, password} = useStore.getState();
        try {
            const response = await login(email, password);
            console.log('로그인 성공', response);

            set({ isLoggedIn: true });
            set({ token: response.result.token });

            await useStore.getState().getUserInfo();
            return true;
            
        } catch (error) {
            console.log('로그인 실패', error.response ? error.response.data : error);

            return false;
        }
    },

    getUserInfo: async() => {
        const {token} = useStore.getState();
        try {
            const response = await getUser(token);
            console.log('유저정보 불러오기 성공', response);
            set({ user: response.result });

        } catch (error) {
            console.log('유저정보 불러오기 실패', error)
            
        }
    },

    
}));

export default useStore;

