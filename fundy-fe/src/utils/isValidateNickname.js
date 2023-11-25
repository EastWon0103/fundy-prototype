function isValidateNickname(nickname) {
    const nicknameRegex = /^.{2,30}$/;
    return nicknameRegex.test(nickname);
}

export default isValidateNickname;