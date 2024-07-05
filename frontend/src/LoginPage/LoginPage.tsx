import './LoginPage.css'
import axios from "axios";
import {FormEvent, useState} from "react";
import {useNavigate} from 'react-router-dom';

function LoginPage() {

    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const navigate = useNavigate();

    function login() {
        axios.post("/api/users/login", {}, {
            auth: {
                username: username,
                password: password
            }
        })
            .then(()=>{
                setPassword("");
                setUsername("");
                navigate('/');
            })
            .catch(error => {
                setPassword("");
                console.log(error)
            })
    }

    function handleSubmit(event: FormEvent<HTMLFormElement>) {
        event.preventDefault();
        login();
    }

    return (
        <div className="login-container">
            <form onSubmit={handleSubmit} className="login-form">
                <h2>🏠ImmoCloud</h2>
                <div className="form-group">
                    <input
                        type="text"
                        name="username"
                        placeholder="Username"
                        value={username}
                        onChange={(e) => setUsername(e.target.value)}
                    />
                </div>
                <div className="form-group">
                    <input
                        type="password"
                        name="password"
                        placeholder="Password"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                    />
                </div>
                <button type="submit" className="login-button">Login</button>
            </form>
        </div>
    );
}

export default LoginPage;