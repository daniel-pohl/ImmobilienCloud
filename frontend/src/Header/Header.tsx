import {Link} from "react-router-dom";
import './Header.css';


function Header() {
    return (
        <div>
            <Link to={`/`}>
                <button className="immoButton">
                    🏠Immobilienverwaltung
                </button>
            </Link>
        </div>
    );
}

export default Header;