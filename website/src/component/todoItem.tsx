import "./todoItem.css"
const TodoItem = (props: {todo: any}) => {
    console.log(props.todo)
    return (
        <div className={"reminder"}>
            <h3>reminder</h3>
            <p>{props.todo.description}</p>
            <p className="deadline">DUE @ {new Date(props.todo.dueDate).toString()}</p>
        </div>
    )
}

export default TodoItem