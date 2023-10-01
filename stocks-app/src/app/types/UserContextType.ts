import React from "react"
import User from "../interfaces/User"

export type UserContextType = {
    isLoggedIn: boolean,
    user: User,
    setUser: (user: User) => any,
    setLoggedIn: (isLoggedIn: boolean) => any,
}