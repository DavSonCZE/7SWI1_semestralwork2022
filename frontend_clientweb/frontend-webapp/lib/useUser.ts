import useSWR from "swr";
import {useEffect} from "react";
import Router from "next/router";

type User = {
    id: number,
    username: string,
    email: string,
    password: string,
}
type Session = {
    isLoggedIn?: boolean,
    user: User | undefined,
}
const useUser = ({redirect = "", redirectIfNotFound = false} = {}) => {
    const {data: user, mutate: mutateUser} = useSWR<User>("http://localhost:8090/user/login");
    let session: Session = user === undefined ? {isLoggedIn: false, user: undefined} : {isLoggedIn: true, user: user};

    useEffect(() => {
        if(!redirect || !user) return;

        //session = {isLoggedIn: true, "user": user};

        if((redirect && !redirectIfNotFound && !session?.isLoggedIn) || (redirectIfNotFound && session?.isLoggedIn)) Router.push(redirect);
    }, [user, redirect, redirectIfNotFound])

    return { session, mutateUser }
}

/*const useUser = async (email: string, password: string) => {
    const response = await fetch("http://localhost:8090/user/all");
    const users: User[] = await response.json();

    const [user] = users.filter((user: User) => user.email === email && user.password === password);

    const now = new Date();

    if (user) return { session: now.setHours(now.getHours() + 1), user: user}
    return {session: null, error: await response.text()}

}*/


export default useUser;
export type {User, Session};