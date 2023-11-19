import React, { Fragment } from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import Home from './page/Home';
import { createBrowserRouter, createRoutesFromElements, Route, RouterProvider } from "react-router-dom";
import notepad from "./assets/notepad.png"
import Login from "./page/Login";
import Register from "./page/Register";

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

root.render(
    <React.StrictMode>
        <div className="header">
            <img src={ notepad } className="w-px header-logo"/>
            <p className="header-text">taaha's todo website</p>
        </div>
        <RouterProvider router={ router }/>
    </React.StrictMode>
);