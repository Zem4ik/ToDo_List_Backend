var currentListId;
var currentURL = window.location.origin;


window.onload = (function () {
    console.log(lists);
    console.log(tasksMap);
    currentListId = lists[0].id;
    createLists(lists);
    listSelected(lists[0].id, lists[0].name);
});

function addListHTML(list) {
    let taskLists = document.getElementById("lists");
    let newDivT = document.createElement('div');
    newDivT.setAttribute('class', 'listLine');
    newDivT.setAttribute('id', 'list' + list.id);

    let newT = document.createElement('a');
    newT.setAttribute('href', "#");
    newT.setAttribute('class', 'listNameLine');
    newT.setAttribute('onClick', `listSelected(${list.id}, "${list.name}")`);

    let newButton = document.createElement('a');
    newButton.setAttribute('href', '#');
    newButton.setAttribute('class', 'spanNameLine');
    newButton.setAttribute('onClick', `deleteList(${list.id})`);
    newButton.innerHTML = `<span>&#10007;</span>`;
    newT.text = list.name;
    taskLists.appendChild(newDivT);
    newDivT.appendChild(newT);
    newDivT.appendChild(newButton);
}

function createLists(lists) {
    let taskLists = document.getElementById("lists");
    while (taskLists.firstChild) {
        taskLists.removeChild(taskLists.firstChild);
    }

    for (let i in lists) {
        let list = lists[i];
        addListHTML(list);
    }
}

function addList() {
    let input = document.getElementById("listTextAdd");
    let text = input.value;
    $.ajax({
        url: currentURL + '/api/list',
        type: 'POST',
        data: JSON.stringify({
            "name": text
        }),
        success: function (result, _, xhr) {
            if (xhr.status === 201) {
                addListHTML(result);
                tasksMap[result.id] = [];
                input.value = "";
            } else {
                alert("Что-то пошло не так. Код: " + xhr.status);
            }
        },
        error: function (result, _, xhr) {
            alert("Что-то пошло не так. Код ошибки: " + xhr.status);
        }
    });
}

function deleteList(id) {
    let selectedList = document.getElementById("list" + id);
    let parent = document.getElementById("lists");
    $.ajax({
        url: currentURL + '/api/list/' + id,
        type: 'DELETE',
        success: function (result, status, xhr) {
            if (xhr.status === 204) {
                parent.removeChild(selectedList);
                tasksMap[id] = null;
            } else {
                alert("Что-то пошло не так. Код: " + xhr.status);
            }
        },
        error: function (result, status, xhr) {
            alert("Что-то пошло не так. Код: " + xhr.status);
        }
    });
}

function addTask() {
    let taskInput = document.getElementById("taskTextAdd");
    let text = taskInput.value;
    $.ajax({
        url: currentURL + '/api/task',
        type: 'POST',
        data: JSON.stringify({
            "listId": currentListId,
            "title": text
        }),
        contentType: "application/json",
        success: function (result, status, xhr) {
            if (xhr.status === 201) {
                addTaskHTML(result);
                let array = tasksMap[currentListId];
                array.push(result);
                taskInput.value = "";
            } else {
                alert("Что-то пошло не так. Код: " + xhr.status);
            }
        },
        error: function (result, status, xhr) {
            alert("Что-то пошло не так. Код ошибки: " + xhr.status);
        }
    });
}

function addTaskHTML(elem) {
    let taskList = document.getElementById("tasks");

    let newLI = document.createElement('li');
    newLI.setAttribute('id', elem.id);
    newLI.innerHTML = `<div class="line" id="line${elem.id}"><a href="#" onclick="deleteTask(${elem.id})"` +
        `><span id="span${elem.id}">&#10007;</span></a>\n<label` +
        ` for="span${elem.id}">${elem.title}</label></div>`;
    taskList.appendChild(newLI);
}

function deleteTask(id) {
    let elem = document.getElementById(id);
    let parentElem = document.getElementById("tasks");
    $.ajax({
        url: currentURL + '/api/task/' + id,
        type: 'DELETE',
        success: function (result, status, xhr) {
            if (xhr.status === 204) {
                parentElem.removeChild(elem);
                let array = tasksMap[currentListId];
                for (let i in array) {
                    let task = array[i];
                    if (task.id === id) {
                        array.splice(i, 1);
                        break;
                    }
                }
            } else {
                alert("Что-то пошло не так. Код ответа: " + xhr.status)
            }
        },
        error: function (result, status, xhr) {
            alert("Что-то пошло не так, задача не удалена. Код ошибки: " + xhr.status)
        }
    });
}

function listSelected(id, name) {
    currentListId = id;
    let tasksName = document.querySelector("div.main > h2");
    tasksName.textContent = name;

    let taskList = document.getElementById("tasks");

    while (taskList.firstChild) {
        taskList.removeChild(taskList.firstChild);
    }

    let tasks = tasksMap[id];

    for (let i in tasks) {
        let task = tasks[i];
        addTaskHTML(task);
    }

}