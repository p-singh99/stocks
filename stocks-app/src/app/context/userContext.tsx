'use client';

import React, { createContext, useContext, Dispatch, useState } from "react";
import { UserContextType } from "../types/UserContextType";
import User from "../interfaces/User";

interface Props {
    children: React.ReactNode,
}

export const UserContext = createContext<UserContextType | null>(null);

const UserProvider: React.FC<Props> = ({ children }: Props) => {

    const [ user, setUser ] = useState(
        {
            id: 0,
            firstName: "",
            lastName: "",
            email: ""
        }
    );

    const [ isLoggedIn, setLoggedIn ] = useState(false);

    return (
        <UserContext.Provider value={{ isLoggedIn, user, setUser, setLoggedIn }}>
            { children }
        </UserContext.Provider>
    );

}

export default UserProvider;
