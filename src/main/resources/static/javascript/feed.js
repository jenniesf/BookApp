// COOKIES, userid and username - grab user id and user's name
const cookieArr = document.cookie.split("=")
const userId = cookieArr[1];
const userFirstname = cookieArr[2];

// DOM Elements - grab elements from the DOM
    // feed/reviews
const feedContainer = document.getElementById("feed-container")
    // misc
const userName = document.getElementById("userName")

// Modal DOM Elements -- modal used to show requested user's reviews
let requestedUserReviewModalBody = document.getElementById("requestedUserReviewModal")
let closeModalBtn = document.getElementById('close-modal-button')
let modalLabel = document.getElementById('modalLabel')

// HEADERS
const headers = {
    'Content-Type' : 'application/json'
}

// Backend endpoint
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


//   3. GET USER'S FEED -- REVIEWS (IF BOOKSHELF FALSE) AND NOT ASSOCIATED WITH LOGGED IN USERID. RUN ON PAGE LOAD
async function getFeed(userId) {
    await fetch(`${bookUrl}feed/user/${userId}`, {
        method: "GET",
        headers: headers
    })
        .then(response => response.json())
        .then(data => createFeedCards(data))  // run create cards below (helper function)
        .catch(err => console.error(err))
}


//   4. GET REQUESTED USER'S REVIEWS (IF BOOKSHELF FALSE) FOR ASSOCIATED USERID -- results shown in modal
async function handleGetRequestedUserReviews(requestedUserId){
     await fetch(`${bookUrl}review/user/${requestedUserId}`, {
        method: "GET",
        headers: headers
    })
        .then(response => response.json())
        .then(data => populateModal(data))  // run helper function - populate modal with data coming back
        .catch(err => console.error(err))
}



// HELPER FUNCTIONS

// CARDS TO SHOW USER'S FEED OF REVIEWS
const createFeedCards = (array) => {

    console.log(array)

    // if reviews array is empty, display empty text
    if(array.length == 0){
        document.getElementById("feedResultEmptyText").style.display = "block";
        document.getElementById("feedResultEmptyText").innerText = `Feed is empty`
    } else {
        document.getElementById("feedResultEmptyText").innerText = ``
        document.getElementById("feedResultEmptyText").style.display = "none";
    }

    feedContainer.innerHTML = ''      // outer container div, set to blank at each run

    array.forEach( obj => {

        // create a card for each review
        let reviewCard = document.createElement("div")      // first inside div
        reviewCard.classList.add("mb-4")                    // first inside div class
        reviewCard.classList.add("col")                     // first inside div class

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
                    <span class="card-text">
                        By
                        <a class="text-decoration-none" onclick="handleGetRequestedUserReviews(${obj.userDto.user_id})" href="" data-bs-toggle="modal" data-bs-target="#feedModal">
                            ${obj.userDto.firstname}
                        </a>
                    </span>
                </div>

            </div>
        `
        feedContainer.append(reviewCard);
    })
}



// “populateModal” method which accepts an object as an argument and uses that object to populate the fields
     // within the modal as well as assign a custom “data-” tag to the “Save” button element
const populateModal = (array) =>{

    modalLabel.innerText = `Reviews by ${array[0].userDto.firstname}`    // show user's name
    requestedUserReviewModalBody.innerText = ''                          // outer div, set to blank at each run

    array.forEach( obj => {

        // create a card for each review
        let reviewCard = document.createElement("div")      // first inside div
        reviewCard.classList.add("mb-4")                    // first inside div class
        reviewCard.classList.add("col")                     // first inside div class

        reviewCard.innerHTML = `
            <div class="card h-100">

                <a class="text-decoration-none" target="_blank" href="${obj.infoLink}">
                    <img src="${obj.smallThumbnail}" class="card-img-top mx-auto d-block pt-2" alt="book cover">
                </a>

                <div class="card-body">
                    <h5 class="card-title">${obj.title}</h5>
                    <p class="card-text">${obj.review}</p>
                </div>
            </div>
        `
        requestedUserReviewModalBody.append(reviewCard);
    })
}


// RUN ON PAGE LOAD
getFeed(userId)


// *** EVENT LISTENERS ***

// 'Close' book review's modal button -- run form reset to clear text in modal after each close
closeModalBtn.addEventListener("click", (e)=>{
   document.getElementById("modal-form").reset(); // clear modal
})
