import React, { Fragment } from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import Home from './page/Home';
import { createBrowserRouter, createRoutesFromElements, Route, RouterProvider } from "react-router-dom";
import notepad from "./assets/notepad.png"
import Login from "./page/Login";
import Register from "./page/Register";
import firebase from "firebase/compat/app";
import { getAuth } from "firebase/auth"
import initializeApp = firebase.initializeApp;

const root = ReactDOM.createRoot(
    document.getElementById('root') as HTMLElement
);

const router = createBrowserRouter(
    [
        {
            path: "/",
            element: <Home />
        },
        {
            path: "/login",
            element: <Login />
        },
        {
            path: "/register",
            element: <Register />
        }
    ]
)

const firebaseConfig = {
    apiKey: "AIzaSyDPqty4GXyQWWo-ec_Q88hvZgK36-hQX5Y",
    authDomain: "cutiehack-2023-24.firebaseapp.com",
    projectId: "cutiehack-2023-24",
    storageBucket: "cutiehack-2023-24.appspot.com",
    messagingSenderId: "141282135380",
    appId: "1:141282135380:web:2ec2aa40bd3d5202334cc1"
};

const app = initializeApp(firebaseConfig)
const auth = getAuth(app)

root.render(
    <React.StrictMode>
        <div className="header">
            <img src={ notepad } className="w-px header-logo"/>
            <p className="header-text">taaha's todo website</p>
        </div>
        <RouterProvider router={ router }/>
    </React.StrictMode>
);

export default function getAuthentication() {
    return auth
}