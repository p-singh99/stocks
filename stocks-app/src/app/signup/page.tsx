'use client'

import TextInput from "../components/TextInput";
import Button from "../components/Button";
import Link from "next/link";
import axios from "axios";
import config from "../config.json";
import React, { useContext } from "react";
import { useRouter } from "next/navigation";
import Validator from "../utilities/inputValidator";
import LoginConstants from "../constants/loginConstants";
import { UserContext } from "../context/userContext";
import User from "../interfaces/User";

interface SignupResponseData {
    userId: number,
    email: string,
    token: string,
}

export default function SignUp() {

    const [firstName, setFirstName] = React.useState("");
    const [lastName, setLastName] = React.useState("");
    const [email, setEmail] = React.useState("");
    const [password, setPassword] = React.useState("");
    const [confirmPassword, setConfirmPassword] = React.useState("");
    const [error, setError] = React.useState("");
    
    const router = useRouter();
    const userContext = useContext(UserContext);

    const signUpHandler = async () => {
        if (error === "" && isValidInputs()) {
            const res = await axios
            .post(config["auth-server-base"] + config["signup-endpoint"],
                {
                    firstName: firstName,
                    lastName: lastName,
                    email: email,
                    password: password,
                },
                {
                    headers: {
                        Accept: "application/json",
                        "Content-Type": "application/json",
                    }
                }
            )
            .then((response) => {
                console.log(`Received: ${JSON.stringify(response)}`);   
                const responseData: SignupResponseData = response.data;
                userContext?.setUser({
                    id: Number(responseData.userId),
                    firstName,
                    lastName,
                    email: responseData.email,
                });

                userContext?.setLoggedIn(true);

                router.push("/home");
            }).catch((error) => {
                console.log(`Error: ${JSON.stringify(error)}`);
            })
        }
    }

    const firstNameChange = (name: string): void => {
        setFirstName(name);
        !Validator.isValidName(name) ? setError(LoginConstants.firstNameError) : setError("");
    }

    const lastNameChange = (name: string): void => {
        setLastName(name);
        !Validator.isValidName(name) ? setError(LoginConstants.lastNameError) : setError("");
    }

    const emailChange = (emailInput: string): void => {
        setEmail(emailInput);
        !Validator.isValidEmail(emailInput) ? setError(LoginConstants.emailError) : setError("");
    }

    const passwordChange = (passwordInput: string): void => {
        setPassword(passwordInput);
        !Validator.isValidPassword(passwordInput) ? setError(LoginConstants.passwordError) : setError("");
    }

    const confirmPasswordChange = (passwordInput: string): void => {
        setConfirmPassword(passwordInput);
        !(password === passwordInput) ? setError("Passwords do not match") : setError("");
    }

    const isValidInputs = (): boolean => {
        if (!Validator.isEmptyString(firstName) && 
            !Validator.isEmptyString(lastName) &&
            !Validator.isEmptyString(email)  &&
            !Validator.isEmptyString(password) &&
            !Validator.isEmptyString(confirmPassword)
        ) {
            return true;
        }
        setError("Please enter all the fields");
        return false;
    }

    return (
        <div className="flex items-center h-screen justify-center bg-blue">
            <div className="flex text-base flex-1 sm:mx-0 lg:mx-10 xl:mx-20 2xl:mx-10 justify-center">
                <div className="max-w-xl bg-blue-darkest flex-1 flex flex-col gap-y-8 pt-20 items-center rounded-l-3xl sm:rounded-r-3xl md:rounded-r-3xl">
                    <span className="text-center text-lg font-bold text-white">Create Account</span>
                    <div className="flex flex-col w-9/12">
                        <label htmlFor="first-name">First Name</label>
                        <TextInput htmlId="first-name" setState={firstNameChange}/>
                    </div>
                    <div className="flex flex-col w-9/12">
                        <label htmlFor="last-name">Last Name</label>
                        <TextInput htmlId="last-name" setState={lastNameChange}/>
                    </div>
                    <div className="flex flex-col w-9/12">
                        <label htmlFor="email">Email</label>
                        <TextInput htmlId="email" setState={emailChange}/>
                    </div>
                    <div className="flex flex-col w-9/12">
                        <label htmlFor="password">Password</label>
                        <TextInput inputType="password" htmlId="password" setState={passwordChange}/>
                    </div>
                    <div className="flex flex-col w-9/12">
                        <label htmlFor="confirm-password">Confirm Password</label>
                        <TextInput inputType="password" htmlId="confirm-password" setState={confirmPasswordChange} />
                    </div>
                    <Button w="w-9/12" caption="Sign Up" onClickHandler={signUpHandler}/>
                    <div className="text-sm text-error-red">
                        {error}
                    </div>
                    <div className="text-center text-white mb-10 text-sm">
                        Already have an account? &nbsp; <Link className="text-blue-url font-bold" href="/login">Login</Link>
                    </div>
                </div>
                <div className="max-w-xl items-start justify-center flex flex-col flex-1 bg-blue-darker sm:invisible sm:w-0 sm:flex-none md:invisible md:w-0 md:flex-none lg:rounded-r-3xl xl:rounded-r-3xl 2xl:rounded-r-3xl">
                    <span className="mx-[10%] text-4xl font-bold">Join Our <br /> Community</span>
                    <span className="mx-[10%]">Follow your favourite Investors and be inspired!</span>
                </div>
            </div>
        </div>
    )
}