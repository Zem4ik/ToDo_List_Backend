window.onload = (function () {
    console.log(lists);
    console.log(tasks);

    createLists(lists);
    task();
});

function createLists(lists) {

    let taskLists = document.getElementById("lists");
    while (taskLists.firstChild) {
        taskLists.removeChild(taskLists.firstChild);
    }

    for (let i in lists) {
        let list = lists[i];
        let newT = document.createElement('a');
        newT.setAttribute('href', "#");
        newT.setAttribute('id', list.id);
        newT.text = list.name;
        taskLists.appendChild(newT);
    }
}

function task(tasks) {
    let taskLists = document.getElementById("tasks");

    for (let task in tasks) {
        let newLI = document.createElement('li');
        newL.setAttribute('id', task.id);
        newLI.innerHTML = `<div class="line" id=${task.id}><a href="#"><span id="span${task.id}">&#10007;</span></a>\n<label for="span${task.id}">${task}</label></div>`;
        tasks.appendChild(newLI);
    }

}