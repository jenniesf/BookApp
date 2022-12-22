// GET DOM ELEMENTS BY IDs FROM HTML
const registerForm = document.getElementById('register-form')
const registerUsername = document.getElementById('register-username')
const registerFirstname = document.getElementById('register-firstname')
const registerLastname = document.getElementById('register-lastname')
const registerPassword = document.getElementById('register-password')

// HEADERS
const headers = {
    'Content-Type' : 'application/json'
}

// USER CONTROLLER API URL
const baseUrl = 'http://localhost:8080/api/v1/users'

// HANDLE REGISTER FORM SUBMISSION
const handleSubmit = async (e) => {

    e.preventDefault()   //prevent default behavior of the form

    // grab the value’s of the inputs and store them inside of an
        // object that can then be used as the body for the POST request
    let bodyObj = {
        username: registerUsername.value,
        firstname: registerFirstname.value,
        lastname: registerLastname.value,
        password: registerPassword.value
    }

    // make the request and handle the response accordingly.
    // For quality of life we can also edit our UserServiceImpl to return a
    // redirect URL string rather than the registration success message.
    // When a User successfully registers, we can send back the URL for the
    // Login page and then in our JavaScript we can redirect the window to the URL we received in the response.
    const response = await fetch( `${baseUrl}/register` , {
        method: "POST",
        body: JSON.stringify(bodyObj),
        headers: headers
    })
        .catch(err => console.error(err.message))

    const responseArr = await response.json()  // response back from UserService

    if (response.status === 200 ) {
        window.location.replace(responseArr[0])  // if response status is OK, run the response page from UserService
    }
}

// EVENT LISTENER
registerForm.addEventListener("submit" , handleSubmit )