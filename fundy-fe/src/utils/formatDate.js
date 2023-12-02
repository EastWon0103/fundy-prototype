const formatDate = (date) => {
    if (!date) {
        return '날짜 정보 없음'; // 또는 다른 기본값
    }

    const dateObject = new Date(date);
    if (isNaN(dateObject.getTime())) { // 날짜가 유효하지 않은 경우 검사
        return '유효하지 않은 날짜';
    }

    return dateObject.toISOString().split('T')[0];
};

export default formatDate;