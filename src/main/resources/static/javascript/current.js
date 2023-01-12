// COOKIES, userid and username - grab user id and user's name
const cookieArr = document.cookie.split("=")
const userId = cookieArr[1];
const userFirstname = cookieArr[2];

// DOM Elements - grab elements from the DOM
    // feed/reviews
const feedContainer = document.getElementById("current-container")
    // misc
const userName = document.getElementById("userName")


// HEADERS
const headers = {
    'Content-Type' : 'application/json'
}

// Backend endpoint
const bookUrl = 'http://localhost:8080/api/v1/current/'


//   1. HANDLE LOGOUT
function handleLogout(){
    let c = document.cookie.split(";");
    for(let i in c){
        document.cookie = /^[^=]+/.exec(c[i])[0]+"=;expires=Thu, 01 Jan 1970 00:00:00 GMT"
    }
}


//   2. ADD USER FIRST NAME TO HOME PAGE
document.getElementById("userName").innerText = userFirstname +"'s Current Books"


//   3. GET USER'S CURRENT LIST OF BOOKS -- RUN ON PAGE LOAD
async function getCurrent(userId) {
    await fetch(`${bookUrl}user/${userId}`, {
        method: "GET",
        headers: headers
    })
        .then(response => response.json())
        .then(data => createCards(data))  // run create cards below (helper function)
        .catch(err => console.error(err))
}


//   4. DELETE CURRENT BOOK WITH CURRENT ID
async function handleBookDelete(currentId){
    await fetch(bookUrl + bookId, {
        method: "DELETE",
        headers: headers
    })
        .catch(err => console.error(err))

    return getCurrent(userId);
}


// HELPER FUNCTIONS

// CARDS TO SHOW USER'S FEED OF REVIEWS
const createCards = (array) => {

    console.log(array)

    // if reviews array is empty, display empty text
    if(array.length == 0){
        document.getElementById("currentResultEmptyText").style.display = "block";
        document.getElementById("currentResultEmptyText").innerText = `Feed is empty`
    } else {
        document.getElementById("currentResultEmptyText").innerText = ``
        document.getElementById("currentResultEmptyText").style.display = "none";
    }

    feedContainer.innerHTML = ''      // outer container div, set to blank at each run

    array.forEach( obj => {

        // create a card for each review
        let reviewCard = document.createElement("div")      // first inside div
        reviewCard.classList.add("mb-4")                    // first inside div class
        reviewCard.classList.add("col")                     // first inside div class

        reviewCard.innerHTML = `
            <div class="card h-100">

                <a class="text-decoration-none" target="_blank" href="${obj.bookDto.infoLink}">
                    <img src="${obj.bookDto.smallThumbnail}" class="card-img-top mx-auto d-block pt-2" alt="book cover">
                </a>

                <div class="card-body">
                    <h5 class="card-title">${obj.bookDto.title}</h5>
                </div>

                <div class="card-footer text-center">
                    <button class="btn btn-outline-danger btn-sm" onclick="handleBookDelete(${obj.current_id})">
                        Delete
                    </button>
                </div>

            </div>
        `
        feedContainer.append(reviewCard);
    })
}



// RUN ON PAGE LOAD
getCurrent(userId)


// *** EVENT LISTENERS ***

// 'Close' book review's modal button -- run form reset to clear text in modal after each close
//closeModalBtn.addEventListener("click", (e)=>{
//   document.getElementById("modal-form").reset(); // clear modal
//})
