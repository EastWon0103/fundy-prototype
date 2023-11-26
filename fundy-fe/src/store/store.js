import { create } from 'zustand';
import { signUp, checkNickname } from '../apis/API';

const useStore = create(set => ({
    email: '',
    password: '',
    nickname: '',
    isValidNickname: undefined,
    isLoggedIn: false,
    user: {},

    setEmail: email => set(() => ({ email })),
    setPassword: password => set(() => ({ password })),
    setNickname: nickname => set(() => ({ nickname })),
    setIsValidNickname: isValid => set(() => ({isValidNickname: isValid})),

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

    performSignUp: async() => {
        const {email, password, nickname, setIsValidNickname} = useStore.getState();
        try {
            const response = await signUp(email, password, nickname);

            console.log('회원가입 성공', response);
            setIsValidNickname(undefined);

            set({ isLoggedIn: true, user: { nickname }});

            return true;

            
        } catch(error) {
            console.log('회원가입 실패', error.response ? error.response.data : error);
            setIsValidNickname(undefined);

            return false;
            
        }
    }
}));

export default useStore;

