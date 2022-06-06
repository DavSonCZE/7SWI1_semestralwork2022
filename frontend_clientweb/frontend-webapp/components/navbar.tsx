import Link from "next/link";
import useUser from "../lib/useUser";
import {toast, Toaster} from "react-hot-toast";

const public_routes = [["📋 GAME REVIEW DATABASE", "/"],["🎮HRY", "/games"],];

const Navbar = () => {
    const {session, mutateUser} = useUser();

    const Logout = () => {
        if(session) {
            try{
                mutateUser(() => undefined);
            } catch (error) {
                console.error(error);
            }
        }
        toast.success("Uživatel odhlášen.");


    }

    return (<>
        <div className="navbar">
            <ul>
                {public_routes.map((elem) => {
                    const [name, path] = elem;
                    return(<Link key={name} href={path}><a>{name}</a></Link>)
                })}
                {session?.isLoggedIn ?
                    <a onClick={Logout}>〽ODHLÁSIT SE</a> : (<><Link href="/register"><a>REGISTROVAT SE</a></Link><Link href="/login"><a>PŘIHLÁSIT SE</a></Link></>)}
            </ul>
        </div>
        <Toaster position="bottom-right"/>
    </>);
}

export default Navbar;