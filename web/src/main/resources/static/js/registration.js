function ValidatePasswords() {
    var password1 = document.getElementById('Password').value
    var password2 = document.getElementById('ConfirmPassword').value
    console.log(password1);
    console.log(password2);
    if (password1 !== password2) {
        document.getElementById('validation').style.display = 'block';
        document.getElementById('Password').style.outline = '1px solid red';
        document.getElementById('ConfirmPassword').style.outline = '1px solid red';
        return false
    } else {
        document.getElementById('validation').style.display = 'none';
        document.getElementById('Password').style.outline = '1px solid green';
        document.getElementById('ConfirmPassword').style.outline = '1px solid green';
    }
}