import module from "./controlButton.module.css"
const ControlButton = (props: { text: string, width?: number, height?: number, classes?: string }) => {
    return (
        <button className={module.controlButton + ` ${props.classes ?? ""}`} style={{
            width: props.width ?? 171,
            height: props.height ?? 57
        }}>{props.text}</button>
    )
}

export default ControlButton;