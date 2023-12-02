const formatDate = (date) => {
    const dateObject = new Date(date);
    return dateObject.toISOString().split('T')[0];
}

export default formatDate;
