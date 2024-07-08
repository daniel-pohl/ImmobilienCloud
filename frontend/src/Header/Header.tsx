import {Link, useNavigate} from "react-router-dom";
import './Header.css';
import axios from "axios";


function Header() {

    const navigate = useNavigate();

    function logout(){
        axios.post("api/users/logout")
            .then(() =>{
                    console.log("Logged out")
                    navigate('/login')
            })
            .catch((error) => console.log(error))
    }


    return (
        <div className="div-header">
            <Link to={`/`}>
                <button className="immo-Button">
                    🏠Immobilienverwaltung
                </button>
            </Link>
            <button onClick={logout} className="logout-Button">Logout</button>
        </div>
    );
}

export default Header;