import {NextPage} from "next";
import {useRouter} from "next/router";
import {FormEvent, FormEventHandler} from "react";
import {toast, Toaster} from "react-hot-toast";


const RegisterPage: NextPage = () => {
    const router = useRouter();
    const fields = [["Email"],["Username", "text"], ["Password"]];


    const registerUser = async (event: any) => {
        event.preventDefault();

        const payload = {
            username: event.target.username.value,
            email: event.target.email.value,
            password: event.target.password.value,
        }


        const response = await fetch("http://localhost:8090/user/create", {method: "POST",headers: {"Content-Type": "application/json"}, body: JSON.stringify(payload)});
        const result = await response.text();

        toast.success(result);
        await router.push("/");
    }

    return(<>
            <div className="log_form">
                <h2>Zaregistrovat se...</h2>
            <form onSubmit={
                registerUser
            }>
                {fields.map(val => {
                    const [text, type] = val;
                    return (
                            <div key={text.toLowerCase()}>
                                <label htmlFor={text.toString()}>{text}:</label><br/>
                                <input type={type == undefined ? text.toLowerCase() : type } required id={text.toLowerCase()} name={text.toLowerCase()} placeholder={"Enter " + text}/><br/>
                            </div>
                    );
                })}
                <br/><button type="submit">Registrovat se</button>
            </form>
            </div>
    </>
        );
}

export default  RegisterPage;