'use client'

import Image from "next/image";
import Logo from "../assets/images/logo.svg";
import TextInput from "../components/TextInput";
import Button from "../components/Button";
import Link from "next/link";
import { useState } from "react";
import Validator from "../utilities/inputValidator";
import LoginConstants from "../constants/loginConstants";
import axios from "axios";
import config from "../config.json";
import { useRouter } from "next/navigation";

export default function Login() {

    const [error, setError] = useState("");
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const router = useRouter();

    const emailHandler = (email: string): void => {
        setEmail(email);
        !Validator.isValidEmail(email) ? setError(LoginConstants.emailError) : setError("");
    }

    const isValidInputs = (): boolean => {
        if (Validator.isEmptyString(email) || Validator.isEmptyString(password)) {
            setError("Please enter all the fields");
            return false;
        }
        return true;
    }

    const loginHandler = async () => {
        if (error === "" && isValidInputs()) {
            const res = await axios
                .post(config["auth-server-base"] + config["login-endpoint"], 
                {
                    email: email,
                    password: password
                },
                {
                    headers: {
                        Accept: "application/json",
                        "Content-Type": "Application/json",
                    },
                    withCredentials: true
                }).then(() => {
                    router.push("/home");
                }).catch((error) => {
                    console.log(`Error during login: ${error}`);
                })
        }
    }

    return (
        <div className="flex items-center h-screen justify-center">
            <div className="flex text-base flex-1 sm:mx-0 lg:mx-10 xl:mx-20 2xl:mx-10 justify-center">
                <div className="max-w-xl bg-blue-darkest flex-1 flex flex-col gap-y-8 pt-20 items-center rounded-l-3xl sm:rounded-r-3xl md:rounded-r-3xl">
                    <Image 
                        src={Logo} 
                        alt="logo"
                        width={75}
                        height={75}
                    />
                    <span className="text-center text-lg font-bold text-white">Sign In to you account</span>
                    <div className="flex flex-col w-9/12">
                        <label htmlFor="email">Email</label>
                        <TextInput htmlId="email" setState={emailHandler}/>
                    </div>
                    <div className="flex flex-col w-9/12">
                        <label htmlFor="password">Password</label>
                        <TextInput inputType="password" htmlId="password" setState={setPassword}/>
                    </div>
                    <div className="flex w-9/12 grow">
                        <div className="flex-1">
                            <input type="checkbox" id="remember" name="remember-check" />&nbsp;
                            <label htmlFor="remember">Remember me</label><br></br>
                        </div>
                        <div className="flex-1 text-right">
                            <a className="text-blue-url" href="">Reset Password</a>
                        </div>
                    </div>
                    <Button w="w-9/12" caption="Login" onClickHandler={loginHandler}/>
                    <div className="text-sm text-error-red">
                        {error}
                    </div>
                    <div className="text-center text-white mb-10 text-sm">
                        Don&apos;t have an account? &nbsp; <Link className="text-blue-url font-bold" href="/signup">Create one</Link>
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