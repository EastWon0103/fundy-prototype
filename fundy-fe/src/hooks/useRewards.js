import { useEffect, useState } from "react"
import useStore from "../store/store"
import { getRewards } from "../apis/API";

const useRewards = (id) => {
    const [rewards, setRewards] = useState(null)
    const [isLoading, setIsLoading] = useState(true);

    const updateRewards = useStore((state) => state.setRewards);

    useEffect(() => {
        let isMounted = true;
        const fetchRewards = async () => {
            try {
                const data = await getRewards(id);
                if(isMounted) {
                    setRewards(data.result);
                    updateRewards(data.result);
                    setIsLoading(false);

                }

            } catch (error) {
                console.log('리워드 가져오기 실패', error)
                if(isMounted) setIsLoading(false);
                
            }
        }
        fetchRewards();
        return () => {
            isMounted = false;

        }; 
    }, [id, updateRewards]);

    return { rewards, isLoading };

}

export default useRewards;