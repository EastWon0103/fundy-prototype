function checkPasswordFormat(password) {
    const passwordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{10,30}$/;
    return passwordRegex.test(password);
}
export default checkPasswordFormat;


