const titulo = document.getElementById("titulo");
const nameIT = document.getElementById("nameIT");
const studioIT = document.getElementById("studioIT");
const yearOfReleaseIT = document.getElementById("yearOfReleaseIT");
const registerBTN = document.getElementById("registerBTN");
const voteBTN = document.getElementById("voteBTN");
const top3BTN = document.getElementById("top3BTN");
const videogamesContainer = document.getElementById("videogamesContainer");

const localStorage = window.localStorage;

registerBTN.addEventListener( "click",  register );
voteBTN.addEventListener("click", vote);
top3BTN.addEventListener("click", top3);

function register(){
    var id = "";
    var name = nameIT.value;
    var studio = studioIT.value;
    var yearOfRelease = yearOfReleaseIT.value;

    var videogame = new Videogame(id, name, studio, yearOfRelease,0);
    var json = videogame.toJson();
    console.log(json);

    var xhr = new XMLHttpRequest();
    xhr.onloadend = function () {
        console.log("POST Done");
        loadAllVideogames();
    };
    xhr.open("POST", "api/videogame/insert");
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.send(json);

}

function top3() {
    window.location.href = "ranking.html";
}

function vote() {
    window.location.href = "games.html";
}

function loadAllVideogames() {
    videogamesContainer.innerHTML = "";

    var xhr = new XMLHttpRequest();
    xhr.onloadend = function () {
        console.log(xhr.responseText);
        let objects = JSON.parse(xhr.responseText);
        for (let i = 0; i < objects.length; i++) {
            addNVideogame(i+1, objects[i]);
        }

    };
    xhr.open("GET", "api/videogame/getall");
    xhr.send();

}

function addNVideogame(rating, nVideogame) {
    let paragraphElement = document.createElement("p");
    let videogame = document.createElement("h3");
    videogame.innerHTML = rating + ". " + nVideogame.name;
    paragraphElement.appendChild(videogame);
    videogamesContainer.appendChild(paragraphElement);
}

document.addEventListener("DOMContentLoaded", function () {
    loadAllVideogames();
});