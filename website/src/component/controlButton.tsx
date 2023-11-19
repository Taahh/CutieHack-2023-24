import module from "./controlButton.module.css"
import React, { MouseEventHandler } from "react";
const ControlButton = (props: { text: string, width?: number, height?: number, classes?: string, onClick?: MouseEventHandler<HTMLButtonElement> }) => {
    return (
        <button className={module.controlButton + ` ${props.classes ?? ""}`} style={{
            width: props.width ?? 171,
            height: props.height ?? 57
        }} onClick={props.onClick}>{props.text}</button>
    )
}

export default ControlButton;