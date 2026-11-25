let answersOptionsToggleEl = document.getElementById("answers-options-toggle");
let answersOptionsDropdownEl = document.getElementById("answers-options-dropdown");
let answersOptionsSelectEl = document.getElementById("answers-options-select");
let answersOptionsFilterEl = document.getElementById("answers-options-filter");

document.addEventListener("click", (e) => {
    if (!answersOptionsToggleEl.contains(e.target)) {
        answersOptionsSelectEl.style.display = "none";
        answersOptionsDropdownEl.classList.remove("open");
    }
})

answersOptionsDropdownEl.addEventListener("click", () => {
    if (answersOptionsSelect.style.display != "block") {
        answersOptionsSelect.style.display = "block";
        answersOptionsDropdownEl.classList.toggle('open');
        answersOptionsFilterEl.focus();
    } else {
        answersOptionsSelect.style.display = "none";
        answersOptionsDropdownEl.classList.remove('open');
    }
})

answersOptionsFilterEl.addEventListener("input", (e) => {
    toMatch = answersOptionsFilterEl.value.toUpperCase();
    for (let option of answersOptionsSelectEl.getElementsByClassName("options-item")) {
        if (option.getAttribute("value").toUpperCase().startsWith(toMatch)) {
            options.style.display = "block";
        } else {
            options.style.display = "none";
        }
    }
})

function loadCharacters() {
    // get list of all the characters
    let names = []
    for (let name of names) {
        let option = document.createElement("div");
        option.className = "options-item";
        option.setAttribute("value", name);
        option.textContent = name;
        option.addEventListener("click", (e) => {
            // submit the answer
            answersOptionsSelectEl.style.display = "none";
            answersOptionsFilterEl.textContent = "";
            answersOptionsDropdownEl.classList.remove("open");
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
        answersOptionsSelectEl.appendChild(option);
    }
}

loadCharacters();