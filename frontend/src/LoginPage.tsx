import React, {FormEvent, useState} from 'react';
import axios from "axios";

type LoginPageProps = {
    onLogin: () => void
}

function LoginPage(props: LoginPageProps) {

    const [username, setUsername] = useState<string>("")
    const [password, setPassword] = useState<string>("")

    const login = (e: FormEvent<HTMLFormElement>) => {
        e.preventDefault()
        axios.get("/api/login",{
                        auth: {
                            username,
                            password
                        }
                    })
            .then(props.onLogin)
    }

    return (
        <>
            <div>Please login</div>
            <form onSubmit={login}>
                <label htmlFor="username">Username</label>
                <input type="text" id="username"  onChange={event => setUsername(event.target.value)}/>
                <label htmlFor="password">Password</label>
                <input type="password" id="password" onChange={event => setPassword(event.target.value)}/>
                <button type="submit">Login</button>
            </form>
        </>

    );
}

export default LoginPage;