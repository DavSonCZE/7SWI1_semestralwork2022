import Navbar from "./navbar";
import Footer from "./footer";
import {Toaster} from "react-hot-toast";

const Layout = ({children}: any) => {
    return(<>
        <Navbar />
        <div className="content">
        <main>{children}</main>
        </div>
        <Footer />
    </>)
}

export default Layout;