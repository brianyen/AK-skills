let answerOptionsToggleEl = document.getElementById("answer-options-toggle");
let answerOptionsDropdownEl = document.getElementById("answer-options-dropdown");
let answerOptionsSelectEl = document.getElementById("answer-options-select");
let answerOptionsFilterEl = document.getElementById("answer-options-filter");

let characters = null;

document.addEventListener("click", (e) => {
    if (!answerOptionsToggleEl.contains(e.target)) {
        answerOptionsSelectEl.style.display = "none";
        answerOptionsDropdownEl.classList.remove("open");
    }
})

answerOptionsDropdownEl.addEventListener("click", () => {
    if (answerOptionsSelect.style.display != "block") {
        answerOptionsSelect.style.display = "block";
        answerOptionsDropdownEl.classList.toggle('open');
        answerOptionsFilterEl.focus();
    } else {
        answerOptionsSelect.style.display = "none";
        answerOptionsDropdownEl.classList.remove('open');
    }
})

answerOptionsFilterEl.addEventListener("input", (e) => {
    toMatch = answerOptionsFilterEl.value.toUpperCase();
    for (let option of answerOptionsSelectEl.getElementsByClassName("options-item")) {
        if (option.getAttribute("value").toUpperCase().startsWith(toMatch)) {
            options.style.display = "block";
        } else {
            options.style.display = "none";
        }
    }
})

function loadCharacters() {
    fetch("/api/characters")
    .then(response => {
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        return response.json()
    })
    .then(data => {
        characters = data;
        let names = data.map(c => c["name"]);
        for (let name of names) {
            let option = document.createElement("div");
            option.className = "options-item";
            option.setAttribute("value", name);
            option.textContent = name;
            option.addEventListener("click", (e) => {
                console.log(e.getAttribute("value"));
                // submit the answer
                answerOptionsSelectEl.style.display = "none";
                answerOptionsFilterEl.textContent = "";
                answerOptionsDropdownEl.classList.remove("open");
            })
            option.tabIndex = 0;
            option.addEventListener('keydown', (e) => {
                if (e.key === "Enter") {
                    e.preventDefault();
                    option.click();
                } else if (e.key === " ") {
                    e.preventDefault();
                }
            })
            option.addEventListener('keyup', (e) => {
                if (e.key === " ") {
                    e.preventDefault();
                    option.click();
                }
            })
            answerOptionsSelectEl.appendChild(option);
        }
    })
    .catch((error) => console.error("Error:", error));
}

loadCharacters();