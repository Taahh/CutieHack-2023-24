import "./Register.css"
import ControlButton from "../component/controlButton";
import React, { useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { createUserWithEmailAndPassword } from "firebase/auth"
import getAuthentication from "../index";

const Register = () => {
    const navigate = useNavigate()

    function register(event: React.MouseEvent<HTMLButtonElement>) {
        const email = (document.getElementsByClassName("email")[0] as HTMLInputElement).value
        const password = (document.getElementsByClassName("password")[0] as HTMLInputElement).value
        createUserWithEmailAndPassword(getAuthentication(), email, password).then(value => {
            fetch("http://127.0.0.1:8080/users/create", {
                method: "POST",
                body: JSON.stringify({
                    email: email,
                    uid: value.user.uid
                })
            }).then(value1 => {
                console.log("Created user")
                navigate("/")
            }).catch(console.error)
        }).catch(console.error)
    }

    useEffect(() => {
        if (getAuthentication().currentUser != null) {
            navigate("/")
        }
    })

    return (
        <div className="register">
            <div className="register-form">
                <input className="email" type="email" name="email" placeholder="Email"/>
                <input className="password" type="password" name="password" placeholder="Password"/>

                <ControlButton text="register" classes="register-button" onClick={ register }/>
                <p>Already have an account?</p>
                <p className="click-here" onClick={ () => navigate("/login") }>Click here</p>
            </div>
        </div>
    )
}

export default Register