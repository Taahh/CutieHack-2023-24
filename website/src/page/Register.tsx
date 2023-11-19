import "./Register.css"
import ControlButton from "../component/controlButton";
import React from "react";
import { useNavigate } from "react-router-dom";

const Register = () => {
    const navigate = useNavigate()
    return (
        <div className="register">
            <div className="register-form">
                <input className="email" type="email" name="email" placeholder="Email"/>
                <input className="password" type="password" name="password" placeholder="Password"/>

                <ControlButton text="register" classes="register-button"/>
                <p>Already have an account?</p>
                <p className="click-here" onClick={() => navigate("/login")}>Click here</p>
            </div>
        </div>
    )
}

export default Register