import React from 'react';
import ReactDOM from 'react-dom/client';
import { createBrowserRouter, RouterProvider } from "react-router-dom";
import './index.css';
import App from './App';
import {ErrorPage, Home, Rank,Login,SignUp,Loading} from "./pages/index"

const router = createBrowserRouter([
    {
        path: "/",
        element: <App />,
        errorElement: <ErrorPage />,
        children: [
            {
                index: true,
                element: <Home />,
            },
            {
                path: "rank",
                element: <Rank />,

            },
            {
                path: "login",
                element: <Login />,

            },
            {
                path: "signUp",
                element: <SignUp />,

            },

        ],
    },
]);
const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
    <React.StrictMode>
        <RouterProvider router={router} fallbackElement={<Loading />} />
    </React.StrictMode>,
);


