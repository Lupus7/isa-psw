export function returnToken(){
    let user = JSON.parse(localStorage.getItem('user'));

    if (user && user.accessToken) {
        console.log(user.accessToken)
        return { Authorization: 'Bearer ' + user.accessToken };
    } else {
        return {};
    }
}