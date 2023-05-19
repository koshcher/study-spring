function updateWord() {
    fetch("/translation/random").then(data => data.text()).then((word) => {
        const resultAttemptInput = document.getElementById("result-attempt");
        const usernameInput = document.getElementById("user-name");
        const dictionaryWordH1 = document.getElementById("dictionary-word");

        resultAttemptInput.value = "";
        usernameInput.value = "";
        dictionaryWordH1.innerText = word;
        console.log(word)
    })
}

function stats(username) {
    fetch(`/stats?username=${username}`).then(data => data.json())
        .then(data => {
            const statsList = document.getElementById("stats");
            statsList.innerHTML = '';
            for (let i = 0; i < data.length; ++i) {
                const attempt = data[i];
                console.log(attempt)
                statsList.innerHTML += `<tr>
    <td>${attempt.id}</td>
    <td>${attempt.dictionary.word}</td>
    <td>${attempt.resultAttempt}</td>
    <td>${attempt.correct ? "correct" : "incorrect"}</td>
</tr>`
            }
        })

}


onload = function () {
     updateWord();

    const form = document.getElementById("attempt-form");
    form.onsubmit = function (ev) {
        ev.preventDefault();

        const word = document.getElementById("dictionary-word").innerText;
        const attempt =  document.getElementById("result-attempt").value;
        const userName = document.getElementById("user-name").value;


        const data = {
            user: {
                name: userName
            },
            dictionary: {
                word: word,
                translation: ""
            },
            resultAttempt: attempt
        }
        console.log(data)

        fetch("/translation/add", {
            method:"POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(data)
        }).then(output => output.json()).then(res => {
            document.getElementById("result-message").innerText = res.correct ? "Correct" : "Wrong"
            console.log(res)
        }).then(() => {
            updateWord();
            stats(userName)
        })
    }
}