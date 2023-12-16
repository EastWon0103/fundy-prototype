import { useState } from "react";
import { useNavigate } from "react-router";

export default function useModal() {
    const navigate = useNavigate();

    const [modalOpen, setModalOpen] = useState(false);
    const [modalDescription, setModalDescription] = useState('');

    const openModal = () => setModalOpen(true);
    const closeModal = () => setModalOpen(false);

    const [modalAction, setModalAction] = useState(() => closeModal);

    const closeModalOnly = () => {
        closeModal();
    }
    const closeModalAndNavigate = (path) => {
        closeModal();
        navigate(path);
    }
    const closeModalAndRefresh = () => {
        closeModal();
        window.location.reload();
    }

    return {
        modalOpen,
        setModalOpen,
        modalDescription,
        setModalDescription,
        modalAction,
        setModalAction,
        openModal,
        closeModal,
        closeModalOnly,
        closeModalAndNavigate,
        closeModalAndRefresh,
    }
}