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


//   3. GET USER'S ALL CURRENT LIST OF BOOKS -- RUN ON PAGE LOAD
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
    await fetch(bookUrl + currentId, {
        method: "DELETE",
        headers: headers
    })
        .catch(err => console.error(err))

    return getCurrent(userId);
}


//   5. GET CURRENT BOOK WITH CURRENT ID
    // PUT/UPDATE CURRENT
async function getCurrentById(currentId, totalPages, currentPage){

    await fetch(bookUrl + currentId, {
        method: "GET",
        headers: headers
    })
        .then(res => res.json())
        .then(data =>   {
                console.log(data)
                handleCurrentPut(data, currentId, totalPages, currentPage)
                })
        .catch(err => console.error(err.message))
}

async function handleCurrentPut(bodyObj, currentId, total, current){

    let currentObj = {
        "current_id": currentId,
        "totalPages": total,
        "currentPage": current
    }

    await fetch(bookUrl, {
        method: "PUT",
        body: JSON.stringify(currentObj),
        headers: headers
    })
        .catch(err => console.error(err))

    //return getCurrent(userId);   // run get current fn
}


// HELPER FUNCTIONS

// CARDS TO SHOW USER'S FEED OF REVIEWS
const createCards = (array) => {

    console.log(array)

    // if reviews array is empty, display empty text
    if(array.length == 0){
        document.getElementById("currentResultEmptyText").style.display = "block";
        document.getElementById("currentResultEmptyText").innerText = `No books found`
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
                    <h5 class="card-title text-center">${obj.bookDto.title}</h5>
                </div>

                <div class="card-footer text-center">

                    <div class="input-group text-center px-4">
                        <input type="text" class="form-control" id="${obj.current_id}CurrentPage" placeholder="${obj.currentPage}">
                        <span class="input-group-text">of</span>
                        <input type="text" class="form-control" id="${obj.current_id}TotalPages" placeholder="${obj.totalPages}">

                        <button type="button" class="btn btn-labeled btn-outline-secondary ml-0" onclick="getCurrentById(${obj.current_id}, document.getElementById('${obj.current_id}TotalPages').value, document.getElementById('${obj.current_id}CurrentPage').value)">
                            <span class="btn-label"><i class="fas fa-pen-to-square"></i></span>
                        </button>

                        <button type="button" class="btn btn-labeled btn-outline-danger ml-0" onclick="handleBookDelete(${obj.current_id})">
                            <span class="btn-label"><i class="fa fa-trash"></i></span>
                        </button>
                    </div>

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


// old buttons
//
// <div class="form-group row">
//    <label for="colFormLabelSm" class="col-sm-2 col-form-label col-form-label-sm">Pages</label>
//    <div class="col-sm-10">
//        <input type="text" class="form-control form-control-sm" id="${obj.current_id}CurrentPage" placeholder="${obj.currentPage}">
//        <input type="text" class="form-control form-control-sm" id="${obj.current_id}TotalPages" placeholder="${obj.totalPages}">
//    </div>
//</div>
//
//<button type="button" class="btn btn-labeled btn-outline-secondary btn-sm" onclick="getCurrentById(${obj.current_id}, document.getElementById('${obj.current_id}TotalPages').value, document.getElementById('${obj.current_id}CurrentPage').value)">
//    <span class="btn-label"><i class="fas fa-pen-to-square"></i></span>
//</button>

//                    <div class="input-group mb-3 text-center">
