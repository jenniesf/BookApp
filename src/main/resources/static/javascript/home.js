// COOKIE, userid



// DOM Elements - grab elements from the DOM
const submitForm = document.getElementById("google-form")
const noteContainer = document.getElementById("note-container")

const headers = {
    'Content-Type' : 'application/json'
}

// Backend link to POST Google API url
const baseUrl = 'http://localhost:8080/api/v1/bookapi/'
// POST bookshelf
const bookUrl = 'http://localhost:8080/api/v1/book/'

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

    console.log(result);

    if (response.status == 200) {
        return createNoteCards(result);
    }
}

// ADD POST BOOK TO BOOKSHELF - fix param/arg and user id
async function handleAddToBookshelf(title, publishedDate){

    console.log(title, publishedDate)

//    let obj =

    const response = await fetch(`${bookUrl}user/${userId}`, {
        method: "POST",
        body: JSON.stringify(obj),
        headers: headers
    })
        .catch(err => console.error(err.message))
    if (response.status == 200) {
        return getBookshelf(userId); // run function below
    }
}

// Retrieve all bookshelf that are associated with the user when the page loads,
// create cards for them, and append them to a container to hold them
async function getBookshelf(userId) {
    await fetch(`${bookUrl}user/${userId}`, {
        method: "GET",
        headers: headers
    })
        .then(response => response.json())
        .then(data => createBookshelfCards(data))  // run create notes cards below (helper function)
        .catch(err => console.error(err))
}


// HELPER FUNCTIONS

// Cards to show google search results
const createNoteCards = (array) => {
    noteContainer.innerHTML = ''
    array.forEach( obj => {

        let noteCard = document.createElement("div")
        noteCard.classList.add("m-2")

        noteCard.innerHTML = `
            <div class="card d-flex" style="width: 18rem; height: 18rem;">
                <div class="card-body d-flex flex-column  justify-content-between" style="height: available">

                    <p class="card-text">${obj.title}</p>
                    <p class="card-text">${obj.publishedDate}</p>

                    <div class="d-flex justify-content-between">

                        <button class="btn btn-primary" onclick="handleAddToBookshelf(${obj.title},${obj.publishedDate})">
                            Add to bookshelf
                        </button>

                        <button onclick="getNoteById(${obj.id})" type="button" class="btn btn-primary"
                          data-bs-toggle="modal" data-bs-target="#note-edit-modal">
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

