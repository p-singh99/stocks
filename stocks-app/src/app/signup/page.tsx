import TextInput from "../components/TextInput";
import Button from "../components/Button";
import Link from "next/link";

export default function SignUp() {
    return (
        <div className="flex items-center h-screen justify-center">
            <div className="flex text-base flex-1 sm:mx-0 lg:mx-10 xl:mx-20 2xl:mx-10 justify-center">
                <div className="max-w-xl bg-blue-darkest flex-1 flex flex-col gap-y-8 pt-20 items-center rounded-l-3xl sm:rounded-r-3xl md:rounded-r-3xl">
                    <span className="text-center text-lg font-bold text-white">Create Account</span>
                    <div className="flex flex-col w-9/12">
                        <label htmlFor="name">Name</label>
                        <TextInput htmlId="name"/>
                    </div>
                    <div className="flex flex-col w-9/12">
                        <label htmlFor="email">Email</label>
                        <TextInput htmlId="email"/>
                    </div>
                    <div className="flex flex-col w-9/12">
                        <label htmlFor="password">Password</label>
                        <TextInput htmlId="password"/>
                    </div>
                    <div className="flex flex-col w-9/12">
                        <label htmlFor="confirm-password">Confirm Password</label>
                        <TextInput htmlId="confirm-password"/>
                    </div>
                    <Button w="w-9/12" caption="Sign Up"/>
                    <div className="text-center text-white my-10 text-sm">
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