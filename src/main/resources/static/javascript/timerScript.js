let interrupted;
let inputTime;
let timerIntervals;
let workType;

function setTimer() {
    inputTime = document.querySelector('#inputWorkTime').value;

    if (document.querySelector('#timerTypeWork').checked) {
        workType = true;
        if (inputTime === "") {
            inputTime  = defaultTimeWork;
        }
        startTimer()
    } else if (document.querySelector('#timerTypePause').checked) {
        workType = false;
        if (inputTime === "") {
            inputTime  = defaultTimePause;
        }
        startTimer()
    } else {
        document.querySelector('#timerText').textContent = "Please select a timer";
    }
}

function startTimer() {
    document.querySelector('#startButton').disabled = true;
    document.querySelector('#stopButton').disabled = false;
    let minutes = inputTime - 1;
    let seconds = 59;
    timerIntervals = setInterval(function() {
        document.querySelector('#timerText').textContent = minutes + ":" + seconds;
        if ( seconds == 0) {
            if (minutes == 0) {
                clearInterval(timerIntervals);
                interrupted = false;
                document.querySelector('#startButton').disabled = false;
                document.querySelector('#stopButton').disabled = true;
                saveSession();
            }
            minutes--;
            seconds = 59;
        }
        seconds--;
    },1000);
}

function stopTimer() {
    document.querySelector('#startButton').disabled = false;
    document.querySelector('#stopButton').disabled = true;
    interrupted = true;
    clearInterval(timerIntervals);
    saveSession();
}

function saveSession() {
    const result  = {
        userId : "hej",
        time : inputTime,
        workType :  workType,
        interrupted : interrupted
    };

    $.ajax({
        type : "POST",
        contentType : "application/json",
        url : "/saveSession",
        data : JSON.stringify(result),
        dataType : 'json'
    });
}

function setTimerTextWork()
{
    document.querySelector('#timerText').textContent = defaultTimeWork;
}

function setTimerTextPause()
{
    document.querySelector('#timerText').textContent = defaultTimePause;
}