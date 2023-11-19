import "./Login.css"
import ControlButton from "../component/controlButton";
import React from "react";
import { useNavigate } from "react-router-dom";

const Login = () => {
    const navigate = useNavigate()
    return (
        <div className="login">
            <div className="login-form">
                <input className="email" type="email" name="email" placeholder="Email"/>
                <input className="password" type="password" name="password" placeholder="Password"/>

                <ControlButton text="login" classes="login-button"/>
                <p>Don't have an account?</p>
                <p className="click-here" onClick={() => navigate("/register")}>Click here</p>
            </div>
        </div>
    )
}

export default Login