const todoList = [];
renderTodoList();
function renderTodoList(){
    let todoListHTML = '';

    for( let i = 0; i < todoList.length; i++){
        const todoObject = todoList[i];
        const {name, dueDate} = todoObject;
        const html = `
        <div>${name}</div>
        <div>${dueDate}</div>
        <button onclick ="
            todoList.splice(${i},1);
            renderTodoList();
        " class = "delete-todo-button"
        >Delete</button>
        `;
        todoListHTML += html;
    }
    document.querySelector('.js-todo-list').innerHTML = todoListHTML;
}

function addTodo(){
    const todo= getTodoAction();
    todoList.push({
        name: todo[0],
        dueDate: todo[1]});
    
    document.querySelector('.js-name-input').value ='';
    document.querySelector('.js-due-date-input').value = '';

    renderTodoList();
  }

function getTodoAction(){
    const inputelement = document.querySelector('.js-name-input');
    const dateInput = document.querySelector('.js-due-date-input');

    const dueDate = dateInput.value;
    const name = inputelement.value;

    return [name,dueDate];
}