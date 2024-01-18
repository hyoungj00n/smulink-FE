import React, { useState } from 'react';
import {Link} from "react-router-dom";

export const Login = () => {
    const [login, setLogin] = useState({
        email: '',
        password: '',
    });

    const [registration, setRegistration] = useState({
        email: '',
        password: '',
        confirmPassword: '',
    });

    const handleLoginSubmit = (e) => {
        e.preventDefault();
        // Add your login logic here
        console.log('Login:', login);
    };

    const handleRegistrationSubmit = (e) => {
        e.preventDefault();
        // Add your registration logic here
        console.log('Registration:', registration);
    };


    return (
        <div className="App">
            <div>
                <h2>Login</h2>
                <form onSubmit={handleLoginSubmit}>
                    <input
                        type="email"
                        placeholder="Email"
                        value={login.email}
                        onChange={(e) => setLogin({ ...login, email: e.target.value })}
                    />
                    <input
                        type="password"
                        placeholder="Password"
                        value={login.password}
                        onChange={(e) => setLogin({ ...login, password: e.target.value })}
                    />
                    <button type="submit">Login</button>
                </form>
            </div>

            <div>
                <h2>Register</h2>
                <form onSubmit={handleRegistrationSubmit}>
                    <input
                        type="email"
                        placeholder="Email"
                        value={registration.email}
                        onChange={(e) => setRegistration({ ...registration, email: e.target.value })}
                    />
                    <input
                        type="password"
                        placeholder="Password"
                        value={registration.password}
                        onChange={(e) => setRegistration({ ...registration, password: e.target.value })}
                    />
                    <input
                        type="password"
                        placeholder="Confirm Password"
                        value={registration.confirmPassword}
                        onChange={(e) =>
                            setRegistration({ ...registration, confirmPassword: e.target.value })
                        }
                    />
                    <button type="submit">Register</button>
                </form>
            </div>


            <Link to ="/map"><li>map</li></Link>
        </div>
    );
}

export default Login;