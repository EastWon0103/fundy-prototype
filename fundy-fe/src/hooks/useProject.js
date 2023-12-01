import { useEffect, useState } from "react";
import useStore from "../store/store";
import { getProjectsById } from "../apis/API";

const useProject = (id) => {
    const [project, setProject] = useState(null);
    const [isLoading, setIsLoading] = useState(true);
    const updateProject = useStore((state) => state.setProject);

    useEffect(() => {
        let isMounted = true;
        const fetchProject = async () => {
            try {
                const data = await getProjectsById(id);
                if (isMounted) {
                    setProject(data);
                    updateProject(data);
                    setIsLoading(false);
                }
            } catch (error) {
                console.log('프로젝트 가져오기 실패', error);
                if (isMounted) setIsLoading(false);
                
            }
        };
        fetchProject();
        return () =>{
            isMounted = false;
        };
    }, [id, updateProject]);

    return { project, isLoading };
}
export default useProject;