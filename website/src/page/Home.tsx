import React, { useEffect, useRef, useState } from 'react';
import ControlButton from "../component/controlButton";
import "./Home.css"
import { useNavigate } from "react-router-dom";
import getAuthentication from "../index";
import { Button, Modal } from "react-bootstrap";
import TodoItem from '../component/todoItem';

function Home() {
    const navigate = useNavigate()
    const [signedIn, setSignedIn] = useState(false)
    const [showCreateModal, setShowCreateModal] = useState(false)
    const [todos, setTodos] = useState([])
    const deadline = React.createRef<HTMLInputElement>()
    useEffect(() => {
        console.log(todos)
        getAuthentication().onAuthStateChanged(value => {
            if (value) {
                fetch("http://127.0.0.1:8080/todos/list/" + value.uid).then(value1 => {
                    value1.json().then(value1 => {
                        setTodos(value1)
                        setSignedIn(true)
                    })
                })

            }
        })
    }, [signedIn])

    function createTodo() {
        const description = (document.getElementsByClassName("reminder-description")[0] as HTMLTextAreaElement).value
        // const deadline = (document.getElementsByClassName("reminder-deadline")[0] as HTMLInputElement)
        console.log(`${deadline.current?.valueAsNumber} \n ${description}`)
        fetch("http://127.0.0.1:8080/todos/create", {
            method: "POST",
            body: JSON.stringify({
                uid: getAuthentication().currentUser?.uid,
                description: description,
                date: deadline.current?.valueAsNumber
            })
        }).then(value1 => {
            console.log("Created todo")
            setShowCreateModal(false)
        }).catch(console.error)

    }

    if (signedIn) {
        return <div className="home">
            <div className="control-buttons">
                <ControlButton text={ "sign out" } onClick={ event => {
                    getAuthentication().signOut().then(() => {
                        console.log("signed out")
                        setSignedIn(false)
                    })
                } }/>
                <ControlButton text={"create"} onClick={() => setShowCreateModal(true)} />
                <Modal show={showCreateModal} onHide={() => setShowCreateModal(false)}>
                    <Modal.Header closeButton={true}>
                        <Modal.Title>Create Reminder</Modal.Title>
                    </Modal.Header>

                    <Modal.Body>
                        <textarea className="reminder-description w-80 h-20" name="description" placeholder="Description" />
                        <input type="datetime-local" className="reminder-deadline" name="deadline" ref={deadline}/>
                    </Modal.Body>

                    <Modal.Footer>
                        <Button variant="outline-success" onClick={createTodo}>Create</Button>
                        <Button variant="outline-danger" onClick={() => setShowCreateModal(false)}>Cancel</Button>
                    </Modal.Footer>
                </Modal>
            </div>
            <div className="todo-list">
                {todos.map(value => {
                    return <TodoItem todo={value}></TodoItem>
                })}
            </div>

        </div>
    } else {
        return (
            <div className="home">
                <div className="control-buttons">
                    <ControlButton text={ "register" } onClick={ event => navigate("/register") }/>
                    <ControlButton text={ "login" } onClick={ event => navigate("/login") }/>
                </div>
            </div>
        );
    }

}

export default Home;
