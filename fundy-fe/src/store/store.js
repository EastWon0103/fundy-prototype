import { create } from 'zustand';
import { persist } from 'zustand/middleware';
import { getUser } from '../apis/API';

const useStore = create(persist((set) => ({

    isLoggedIn: false,
    token: null,
    user: {},

    setIsLoggedIn: (isLoggedIn) => set({ isLoggedIn: isLoggedIn}),
    setToken: (token) => set({ token: token}),
    setUser: (userData) => set({ user: userData }),

    getUserInfo: async () => {
        const { token, setUser } = useStore.getState();
        try {
            const response = await getUser(token);
            console.log('유저정보 불러오기 성공', response);
            setUser(response.result)
        } catch (error) {
            console.log('유저정보 불러오기 실패', error);
        }
    },

}), {
    name: 'user-store', 
    getStorage: () => localStorage, 
}));

export default useStore;
