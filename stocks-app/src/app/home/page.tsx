'use client'

import { useContext } from "react";
import { UserContext } from "../context/userContext";

export default function Home() {
    
    const userContext = useContext(UserContext);
    
    return (
        <>
            {
                userContext?.isLoggedIn 
                    ? <h1>Welcome {userContext?.user.firstName}</h1> 
                    : <h1>Please Login to continue</h1>
            }
            
        </>
    )
}