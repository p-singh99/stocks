'use client';

import { useEffect, useContext } from "react";
import axios from "axios";
import { StatusCodes } from "http-status-codes";
import config from "./config.json";
import { useRouter } from "next/navigation";
import { UserContext } from "./context/userContext";
import { useCookies } from "react-cookie";
import { UserAuthenticationResponse } from "./interfaces/UserAuthenticationResponse";

export default function Home() {

  const { push } = useRouter();
  const [ cookies, setCookie ] = useCookies(['user']);
  const userContext = useContext(UserContext);

  useEffect(() => {
    validateSession();
  }, [])

  const validateSession = async () => {
    const res = await axios
    .post(
        config["auth-server-base"] + config["validate-session"],
        {
            userId: cookies['user'],
        },
        {
            headers: {
                Accept: "application/json",
                "Content-Type": "application/json",
            },
            withCredentials: true
        }
    ).then((response) => {
        if (response.status == StatusCodes.OK) {
            const responseData: UserAuthenticationResponse = response.data;
            userContext?.setUser({
                id: Number(responseData.id),
                firstName: responseData.firstName,
                lastName: responseData.lastName,
                email: responseData.email,
            });
            userContext?.setLoggedIn(true);
            push("/home");
        }
    }).catch((error) => {
        push("/login");
    })
};

  return (
    <></>
  )
}