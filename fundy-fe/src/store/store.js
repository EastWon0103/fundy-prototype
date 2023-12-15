import { create } from 'zustand';
import { persist } from 'zustand/middleware';
import { login, getUser, funding, getFundings } from '../apis/API';

const useStore = create(persist((set) => ({

    isLoggedIn: false,
    token: null,
    user: {},
    project: null,
    rewards: null,
    fundings: null,

    setIsLoggedIn: (isLoggedIn) => set({ isLoggedIn: isLoggedIn}),
    setToken: (token) => set({ token: token}),
    setProject: (projectData) => set({ project: projectData }),
    setRewards: (rewardsData) => set({ rewards: rewardsData }),

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
    },

    getFundings: async () => {
        const { token } = useStore.getState();
        try {
            const response = await getFundings(token);
            console.log('펀딩 목록 요청 성공', response);
            set({ fundings: response.result });
            
        } catch (error) {
            console.log(error)
        }
    },

}), {
    name: 'user-store', // 로컬 스토리지에 저장될 키 이름
    getStorage: () => localStorage, // 사용할 스토리지 엔진
}));

export default useStore;
