'use client'

import { useContext, useEffect } from "react";
import { UserContext } from "../context/userContext";
import { useCookies } from "react-cookie";

export default function Home() {
    
    const userContext = useContext(UserContext);
    const [ cookies, setCookie ] = useCookies(['user']);

    useEffect(() => {
        console.log(`Cookie: ${cookies['user']}`);
    }, []);
    
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