import React, {ChangeEvent, FormEvent, useEffect, useState} from 'react';
import logo from "./logo.svg";
import axios from "axios";

type SecuredPageProps = {
    fetchUsername: () => void
    username: string
}

function SecuredPage(props: SecuredPageProps) {

    const [username, setUsername] = useState<string>("")
    const [password, setPassword] = useState<string>("")
    const [userRole, setUserRole] = useState<string>("")
    const [newUserRole, setNewUserRole] = useState<string>("BASIC")

    const logout = () => {
        axios.get("/api/logout")
            .then(props.fetchUsername)
    }

    const fetchUserRole = () => {
        axios.get("/api/role")
            .then(response => response.data)
            .then(setUserRole)
    }

    useEffect(fetchUserRole,[])

    const register = (e: FormEvent<HTMLFormElement>) => {
        e.preventDefault()
        axios.post("/api",{
                "username": username,
                "password": password,
                "role": newUserRole
            })
            .then(response => {
                if(response.status === 201) {
                     alert("Benutzer erfolgreich angelegt!")
                    props.fetchUsername()
                }
            })
            .catch(error => {
                if(error.response.status === 409) {
                    alert("Benutzer mit diesem Namen bereits vorhanden")
                } else
                if(error.response.status === 400) {
                    alert("Falsche Eingabewerte")
                }
            })
    }

    const handleOnChange = (event: ChangeEvent<HTMLSelectElement>) => {
        setNewUserRole(event.target.value)
    }

    return (
        <header className="App-header">
            <img src={logo} className="App-logo" alt="logo" />
            <p>Your are logged in!</p>
            <p>Welcome {props.username}</p>
            <button onClick={logout}>Logout</button>
            {
                userRole === "[ROLE_ADMIN]" &&
                <form onSubmit={register}>
                    <label htmlFor="username">Username</label>
                    <input type="text" id="username"  onChange={event => setUsername(event.target.value)}/>
                    <label htmlFor="password">Password</label>
                    <input type="password" id="password" onChange={event => setPassword(event.target.value)}/>
                    <label htmlFor="role">Role</label>
                    <select onChange={handleOnChange}>
                        <option value="BASIC">Basic</option>
                        <option value="ADMIN">Admin</option>
                    </select>
                    <button type="submit">Register</button>
                </form>
            }
        </header>
    );
}

export default SecuredPage;