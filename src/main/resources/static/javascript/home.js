// COOKIE, userid
const cookieArr = document.cookie.split("=")
const userId = cookieArr[1];

// DOM Elements - grab elements from the DOM
const submitForm = document.getElementById("google-form")
const noteContainer = document.getElementById("note-container")

const submitBookForm = document.getElementById("book-form")

// HEADERS
const headers = {
    'Content-Type' : 'application/json'
}

// Backend link to POST Google API url
const baseUrl = 'http://localhost:8080/api/v1/bookapi/'    // Google search book
const bookUrl = 'http://localhost:8080/api/v1/books/'

// HANDLE BOOK SEARCH - GOOGLE API
const handleSubmit = async (e) => {
    e.preventDefault() //prevent default behavior of the form
    let bodyObj = document.getElementById('search-input').value  // store search-input value
    console.log(bodyObj); // log user request string
    await bookSearch(bodyObj);  // run function below
    document.getElementById("search-input").value = ''  // update search-input value to empty
}
async function bookSearch(obj) {
    const response = await fetch(`${baseUrl}`, {
        method: "POST",
        body: JSON.stringify(obj),
        headers: headers
    })
        .catch(err => console.error(err.message))

    const result = await response.json()

    console.log(result);    // result is an array of 10 items

    if (response.status == 200) {
        return createNoteCards(result);
    }
}

// ADD/POST BOOK TO DATABASE -- USING FORM
const handleBookSubmit = async (e) => {
    e.preventDefault() //prevent default behavior of the form
    let bodyObj = {
        title: document.getElementById("title-input").value,
        authors: document.getElementById("author-input").value,
        published: document.getElementById("published-input").value,
        description: document.getElementById("description-input").value,
        smallThumbnail: document.getElementById("smallThumbnail-input").value,
        thumbnail: document.getElementById("thumbnail-input").value,
        bookshelf: document.getElementById("bookshelf-input").value,
        review: document.getElementById("review-input").value
    }
    await addBook(bodyObj);  // run addBook function below
    // value to empty
    document.getElementById("title-input").value = ''
    document.getElementById("author-input").value = ''
    document.getElementById("published-input").value = ''
    document.getElementById("description-input").value = ''
    document.getElementById("smallThumbnail-input").value = ''
    document.getElementById("thumbnail-input").value = ''
    document.getElementById("bookshelf-input").value = ''
    document.getElementById("review-input").value = ''
}
async function addBook(obj) {
    const response = await fetch(`${bookUrl}user/${userId}`, {
        method: "POST",
        body: JSON.stringify(obj),
        headers: headers
    })
        .catch(err => console.error(err.message))
    if (response.status == 200) {
          console.log("added to database")
    }
}


// Retrieve all bookshelf that are associated with the user when the page loads,
// create cards for them, and append them to a container to hold them
//async function getBookshelf(userId) {
//    await fetch(`${bookUrl}user/${userId}`, {
//        method: "GET",
//        headers: headers
//    })
//        .then(response => response.json())
//        .then(data => createBookshelfCards(data))  // run create notes cards below (helper function)
//        .catch(err => console.error(err))
//}


// ADD BOOK TO BOOKSHELF
async function handleAddToBookshelf(bookArrData){
    let bodyObj = {
        "title": bookArrData[0],
        "published": bookArrData[1],
        "description": bookArrData[2],
        "smallThumbnail": bookArrData[3],
        "thumbnail": bookArrData[4],
        "bookshelf": true
    }
    const response = await fetch(`${bookUrl}user/${userId}`, {
        method: "POST",
        body: JSON.stringify(bodyObj),
        headers: headers
    })
        .catch(err => console.error(err.message))
    if (response.status == 200) {
          console.log("book added to database")
    }
}



// HELPER FUNCTIONS

// REFACTOR GOOGLE DATA
function escapeQuotesFromGoogleAPI(str){
    str = str.replace(/"/g, "\\\"").replace(/'/g, "\\\'")
    return str
}

// CARDS TO SHOW GOOGLE SEARCH RESULT
const createNoteCards = (array) => {
    noteContainer.innerHTML = ''
    array.forEach( obj => {

        let title = String(obj.title)
        let date = String(obj.publishedDate)
        let description = obj.description.toString()
        let smallImage = String(obj.smallThumbnail)
        let bigImage = String(obj.thumbnail)

        title = escapeQuotesFromGoogleAPI(title)
        description = escapeQuotesFromGoogleAPI(description)

        let noteCard = document.createElement("div")
        noteCard.classList.add("m-2")

        noteCard.innerHTML = `
            <div class="card d-flex" style="width: 18rem; height: 18rem;">
                <div class="card-body d-flex flex-column  justify-content-between" style="height: available">

                    <p class="card-text">${obj.title}</p>
                    <p class="card-text">${obj.publishedDate}</p>

                    <div class="d-flex justify-content-between">

                        <button class="btn btn-primary" onclick="handleAddToBookshelf(['${title}','${date}','${description}','${smallImage}','${bigImage}'])">
                            Add to bookshelf
                        </button>

                        <button class="btn btn-primary" onclick="">
                            Write a review
                        </button>

                    </div>
                </div>
            </div>
        `

        noteContainer.append(noteCard);
    })
}




// EVENT LISTENERS
submitForm.addEventListener("submit", handleSubmit)
submitBookForm.addEventListener("submit", handleBookSubmit)
