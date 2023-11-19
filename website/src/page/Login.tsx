import "./Login.css"
import ControlButton from "../component/controlButton";
import React, { useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { signInWithEmailAndPassword } from "firebase/auth";
import getAuthentication from "../index";

const Login = () => {
    const navigate = useNavigate()

    function login(event: React.MouseEvent<HTMLButtonElement>) {
        const email = (document.getElementsByClassName("email")[0] as HTMLInputElement).value
        const password = (document.getElementsByClassName("password")[0] as HTMLInputElement).value
        signInWithEmailAndPassword(getAuthentication(), email, password).then(value => {
            navigate("/")
            console.log("signed in")

        }).catch(err => {
            console.log(`Error: ${err.message}`)
        })
    }

    useEffect(() => {
        if (getAuthentication().currentUser != null) {
            navigate("/")
        }
    })

    return (
        <div className="login">
            <div className="login-form">
                <input className="email" type="email" name="email" placeholder="Email"/>
                <input className="password" type="password" name="password" placeholder="Password"/>

                <ControlButton text="login" classes="login-button" onClick={login}/>
                <p>Don't have an account?</p>
                <p className="click-here" onClick={() => navigate("/register")}>Click here</p>
            </div>
        </div>
    )
}

export default Login