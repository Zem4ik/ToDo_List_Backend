var currentListId;

window.onload = (function () {
    console.log(lists);
    console.log(tasksMap);
    currentListId = lists[0].id;
    createLists(lists);
    listSelected(lists[0].id, lists[0].name);
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
        newT.setAttribute('onClick', `listSelected(${list.id}, "${list.name}")`);

        newT.text = list.name;
        taskLists.appendChild(newT);
    }
}


function listSelected(id, name) {
    let tasksName = document.querySelector("div.main > h2");
    tasksName.textContent = name;

    let taskList = document.getElementById("tasks");

    while (taskList.firstChild) {
        taskList.removeChild(taskList.firstChild);
    }

    let tasks = tasksMap[id];

    for (let i in tasks) {
        let task = tasks[i];
        let newLI = document.createElement('li');
        newLI.setAttribute('id', task.id);
        newLI.innerHTML = `<div class="line" id=${task.id}><a href="#"><span id="span${task.id}">&#10007;</span></a>\n<label for="span${task.id}">${task.title}</label></div>`;
        taskList.appendChild(newLI);
    }

}