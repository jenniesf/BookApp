// COOKIES, userid and username
const cookieArr = document.cookie.split("=")
const userId = cookieArr[1];
const userFirstname = cookieArr[2];

// DOM Elements - grab elements from the DOM
    // feed/reviews
const reviewContainer = document.getElementById("review-container")
    // misc
const userName = document.getElementById("userName")

// HEADERS
const headers = {
    'Content-Type' : 'application/json'
}

// Backend link to POST Google API url
const bookUrl = 'http://localhost:8080/api/v1/books/'

//   1. HANDLE LOGOUT
function handleLogout(){
    let c = document.cookie.split(";");
    for(let i in c){
        document.cookie = /^[^=]+/.exec(c[i])[0]+"=;expires=Thu, 01 Jan 1970 00:00:00 GMT"
    }
}

//   2. ADD USER FIRST NAME TO HOME PAGE
document.getElementById("userName").innerText = userFirstname +"'s Feed"


// 3. GET USER'S FEED -- REVIEWS (IF BOOKSHELF FALSE) AND NOT ASSOCIATED WITH USERID ON PAGE LOAD
async function getFeed(userId) {
    await fetch(`${bookUrl}feed/user/${userId}`, {
        method: "GET",
        headers: headers
    })
        .then(response => response.json())
        .then(data => createFeedCards(data))  // run create cards below (helper function)
        .catch(err => console.error(err))
}


// HELPER FUNCTIONS

// CARDS TO SHOW USER'S FEED OF REVIEWS
const createFeedCards = (array) => {

    console.log(array)

    // if reviews array is empty
    if(array.length == 0){
        document.getElementById("reviewResultEmptyText").style.display = "block";
        document.getElementById("reviewResultEmptyText").innerText = `Feed is empty`
    } else {
        document.getElementById("reviewResultEmptyText").innerText = ``
        document.getElementById("reviewResultEmptyText").style.display = "none";
    }

    reviewContainer.innerHTML = ''      // outer div

    array.forEach( obj => {
        let reviewCard = document.createElement("div")    // first inside div
        reviewCard.classList.add("mb-4")              // first inside div class
        reviewCard.classList.add("col")              // first inside div class

        reviewCard.innerHTML = `
            <div class="card h-100">

                <a class="text-decoration-none" target="_blank" href="${obj.infoLink}">
                    <img src="${obj.smallThumbnail}" class="card-img-top mx-auto d-block pt-2" alt="book cover">
                </a>

                <div class="card-body">
                    <h5 class="card-title">${obj.title}</h5>
                    <p class="card-text">${obj.review}</p>
                </div>

                <div class="card-footer text-center">
                    <p class="card-text">By ${obj.userDto.firstname}</p>
                </div>

            </div>
        `
        reviewContainer.append(reviewCard);
    })
}






// RUN ON PAGE LOAD
getFeed(userId)

// *** EVENT LISTENERS ***
