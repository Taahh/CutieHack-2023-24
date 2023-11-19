import React from 'react';
import ControlButton from "../component/controlButton";
import "./Home.css"

function Home() {
    return (
        <div className="home">
            <div className="control-buttons">
                <ControlButton text={ "register" }/>
                <ControlButton text={ "login" }/>
            </div>
        </div>
    );
}

export default Home;
