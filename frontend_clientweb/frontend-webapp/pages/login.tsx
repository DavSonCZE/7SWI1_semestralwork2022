import {NextPage} from "next";
import {useEffect, useState} from "react";
import useUser, {Session, User} from "../lib/useUser";
import {useRouter} from "next/router";
import {toast, Toaster} from "react-hot-toast";

const LoginPage: NextPage = () => {
    const router = useRouter();

    const {mutateUser} = useUser();

    const LogIn = async (event: any) => {
        event.preventDefault()

        const body = {
            email: event.target.email.value,
            password: event.target.password.value,
        }

        try {
            /*const response = await fetch(`http://localhost:8090/user/login?email=${body.email}&password=${body.password}`)
            const {user}: Session = await response.json();*/
            await mutateUser(await (await fetch(`http://localhost:8090/user/login?email=${body.email}&password=${body.password}`)).json());
            //alert(user);
            toast.success("Uspěšné prihlásení");
            await router.push("/");
        } catch (error) {
            console.error(error);
        }

    }


    return (<>
        <div className="log_form">
            <h2>Přihlásit se...</h2>
    <form onSubmit={LogIn}>
        <label htmlFor="email">Email:</label><br/>
        <input type="email" id="email" name="email"/><br/>
        <label htmlFor="password">Password:</label><br/>
        <input type="password" id="password" name="password"/><br/><br/>
        <button type="submit">Login</button>
    </form>
        </div>
    </>);
}


export default LoginPage;