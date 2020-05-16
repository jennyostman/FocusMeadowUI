let interrupted;
let inputTime;
let timerIntervals;
let workType;

/**
 * Sets the timer depending on the users input and then starts it
 */
function setTimer() {
    if (document.querySelector('#timerTypeWork').checked) {
        inputTime = document.querySelector('#inputWorkTime').value;
        workType = "WORK";
        if (inputTime === "") {
            inputTime  = defaultTimeWork;
        }
        startTimer()
    } else if (document.querySelector('#timerTypePause').checked) {
        inputTime = document.querySelector('#inputPauseTime').value;
        workType = "PAUSE";
        if (inputTime === "") {
            inputTime  = defaultTimePause;
        }
        startTimer()
    } else {
        document.querySelector('#timerText').textContent = "Please select a timer";
    }
}
/**
 * Updates the timer buttons and starts the timer.
 * If the timer gets to finish it is saved
 */
function startTimer() {
    document.querySelector('#startButton').disabled = true;
    document.querySelector('#stopButton').disabled = false;
    let minutes = inputTime - 1;
    let seconds = 59;
    timerIntervals = setInterval(function() {
        document.querySelector('#timerText').textContent = minutes + ":" + seconds;
        if ( seconds === 0) {
            if (minutes === 0) {
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

/**
 * Stops the timer and saves it as an interrupted timer
 */
function stopTimer() {
    document.querySelector('#startButton').disabled = false;
    document.querySelector('#stopButton').disabled = true;
    setTimerTextWork();
    interrupted = true;
    clearInterval(timerIntervals);
    saveSession();
}

/**
 * Sends the timer session to the WevController
 */
function saveSession() {
    const result  = {
        time : inputTime,
        sessionType :  workType,
        flowerToPlant : flower,
        interrupted : interrupted
    };

    $.ajax({
        type : "POST",
        contentType : "application/json",
        url : "/saveSession",
        data : JSON.stringify(result),
        dataType : 'text',
        success : function(response) {
            if ($(response).find('.has-error').length) {
                //TODO handle error
                location.reload();
            }
            else{
                location.reload();
            }
        }
    });
}

/**
 * Sets the timers text to the number that is in the work input form
 */
function setTimerTextWork() {
    document.querySelector('#timerText').textContent =
        !document.querySelector('#inputWorkTime').value
            ? defaultTimeWork : document.querySelector('#inputWorkTime').value;
}

/**
 * Sets the timers text to the number that is in the pause input form
 */
function setTimerTextPause() {
    document.querySelector('#timerText').textContent =
        !document.querySelector('#inputPauseTime').value
            ? defaultTimePause : document.querySelector('#inputPauseTime').value;
}